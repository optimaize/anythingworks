package com.optimaize.soapworks.client.exensions.exceptiontranslation;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.client.exception.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.Iterator;

/**
 * Translates exceptions that arrive in the form of a SOAPFaultException from the server.
 *
 * <p>This happens when the server services fail to provide a better custom exception, or when
 * something with the marshalling doesn't work correctly (as was the case for me).</p>
 *
 * @author Fabian Kessler
 */
public class SoapFaultExceptionTranslator implements ExceptionTranslator {

    public boolean canTranslate(@NotNull Throwable t) {
        return (t instanceof SOAPFaultException);
    }

    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        SOAPFaultException e = (SOAPFaultException) t;
        ServiceException exToThrow = null;
        try {
            SOAPFault fault = e.getFault();
            if (fault != null) {
                //not needed. String faultCode = fault.getFaultCode();
                Detail detail = fault.getDetail();
                if (detail != null) {
                    String className = extractExceptionClassName(detail);
                    if (className!=null) {
                        FaultInfo faultInfo = extractFaultInfo(detail);
                        exToThrow = makeException(className, e.getMessage(), faultInfo);
                    }
                }
            }
        } catch (RuntimeException exWhileTryingToMakeEx) {
            //this ex should never hide the original one.
            //TODO log this ex.
            throw e; //keep the original, or throw a general one. that's the best we can do here.
        }
        if (exToThrow!=null) {
            //good.
            throw exToThrow;
        } else {
            //arriving here means we could not translate it. this should not happen.
            //let's keep what we had and rethrow.
            //TODO log the problem.
            throw e;
        }
    }

    @NotNull
    private ServiceException makeException(@NotNull String className, @Nullable String msg, @NotNull FaultInfo faultInfo) {
        switch (className) {
            case "AccessDeniedWebServiceException":
                return new AccessDeniedServiceException(msg, faultInfo);
            case "InvalidInputWebServiceException":
                return new InvalidInputServiceException(msg, faultInfo);
            case "InternalServerErrorWebServiceException":
            case "InternalWebServiceException": //old name
                return new InternalServiceException(msg, faultInfo);
            default:
                //TODO log problem that we don't have a mapping.
                return new UnknownServiceException(msg, faultInfo);
        }
    }

    /**
     * @return something like "AccessDeniedWebServiceException", or <code>null</code> if not in the supported format.
     */
    private String extractExceptionClassName(@NotNull Detail detail) {
        DetailEntry firstDetailEntry = getFirstDetailEntry(detail);
        if (firstDetailEntry!=null) {
            String localName = firstDetailEntry.getElementName().getLocalName();
            if (localName.endsWith("Exception")) {
                return localName;
            }
        }
        return null;
    }

    /**
     * @return in case there is no such info in the SOAPFault then an object with null values and the UNKNOWN
     *         enum values is returned.
     */
    @NotNull
    private FaultInfo extractFaultInfo(@NotNull Detail detail) {
        Blame blame = Blame.UNKNOWN;
        Retry retry = Retry.UNKNOWN;
        Boolean problemLoggedOnServer = null;

        DetailEntry firstDetailEntry = getFirstDetailEntry(detail);
        assert firstDetailEntry!=null;
        Iterator childElements = firstDetailEntry.getChildElements();
        while (childElements.hasNext()) {
            Object next = childElements.next();
            if (next instanceof Element) {
                Element element = (Element)next;
                String tagName = element.getTagName();
                Node node = element.getFirstChild();
                if (tagName.equals("blame")) {
                    try {
                        blame = Blame.valueOf( ((Text)node).getWholeText() );
                    } catch (IllegalArgumentException e) {
                        //ugh, new unsupported value. we leave it as unknown.
                        //TODO log problem.
                    }
                } else if (tagName.equals("retry")) {
                    try {
                        retry = Retry.valueOf( ((Text)node).getWholeText() );
                    } catch (IllegalArgumentException e) {
                        //ugh, new unsupported value. we leave it as unknown.
                        //TODO log problem.
                    }
                } else if (tagName.equals("problemLogged")) {
                    String boolText = ((Text)node).getWholeText();
                    //while testing this was "true" or "false". doing defensive translation here, worst case i leave null (unknown).
                    if (boolText.equalsIgnoreCase("true")) {
                        problemLoggedOnServer = true;
                    } else if (boolText.equalsIgnoreCase("false")) {
                        problemLoggedOnServer = false;
                    } else if (boolText.equalsIgnoreCase("null")) {
                        //keep what we have
                    } else {
                        //TODO log problem, unexpected boolean string value!
                    }
                } else {
                    //TODO log the problem. the server added a new field of information.
                    //this client should be updated to use it also.
                }
            }
        }
        return new FaultInfo(blame, retry, problemLoggedOnServer);
    }

    @Nullable
    private DetailEntry getFirstDetailEntry(@NotNull Detail detail) {
        Iterator detailEntries = detail.getDetailEntries();
        while (detailEntries.hasNext()) {
            Object next = detailEntries.next();
            if (next instanceof DetailEntry) {
                return (DetailEntry)next;
            }
        }
        return null;
    }

}
