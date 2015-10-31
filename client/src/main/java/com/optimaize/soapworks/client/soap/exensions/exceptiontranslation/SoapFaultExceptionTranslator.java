package com.optimaize.soapworks.client.soap.exensions.exceptiontranslation;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.client.soap.exception.*;
import com.optimaize.soapworks.common.soap.exception.Blame;
import com.optimaize.soapworks.common.soap.exception.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.soap.Detail;
import javax.xml.soap.DetailEntry;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Translates exceptions that arrive in the form of a SOAPFaultException from the server.
 *
 * <p>This happens when the server services fail to provide a better custom exception, or when
 * something with the marshalling doesn't work correctly.</p>
 *
 * @author Fabian Kessler
 */
public class SoapFaultExceptionTranslator implements ExceptionTranslator {

    private static final Logger log = LoggerFactory.getLogger(SoapFaultExceptionTranslator.class);

    private static final FaultInfoToExceptionConverter converter = new FaultInfoToExceptionConverter();


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
                Detail detail = fault.getDetail();
                if (detail != null) {
                    FaultInfo faultInfo = extractFaultInfo(detail);
                    exToThrow = converter.makeException(e.getMessage(), faultInfo);
                }
            }
        } catch (RuntimeException exWhileTryingToMakeEx) {
            //this ex should never hide the original one.
            log.error("Failed translating exception: "+exWhileTryingToMakeEx.getMessage(), exWhileTryingToMakeEx);
            throw e; //keep the original, or throw a general one. that's the best we can do here.
        }
        if (exToThrow!=null) {
            //good.
            throw exToThrow;
        } else {
            //arriving here means we could not translate it. this should not happen.
            //let's keep what we had and rethrow.
            log.error("Failed translating exception!");
            throw e;
        }
    }



//    /**
//     * @return something like "AccessDeniedWebServiceException", or <code>null</code> if not in the supported format.
//     */
//    private String extractExceptionClassName(@NotNull Detail detail) {
//        DetailEntry firstDetailEntry = getFirstDetailEntry(detail);
//        if (firstDetailEntry!=null) {
//            String localName = firstDetailEntry.getElementName().getLocalName();
//            if (localName.endsWith("Exception")) {
//                return localName;
//            }
//        }
//        return null;
//    }


    /**
     * It can either e within an extra tag, or directly.
     */
    private Iterator detailChildrenIterator(Detail detail) {
        /*
        sb.append("<ns2:AccessDeniedWebServiceException xmlns:ns2=\"http://exceptionthrower.system.services.v4_0.soap.server.nameapi.org/\">");
        sb.append("<blame>CLIENT</blame>");
        sb.append("<errorCode>2101</errorCode>");
        sb.append("<faultCause>AccessDenied</faultCause>");
         */

        DetailEntry firstDetailEntry = getFirstDetailEntry(detail);
        if (firstDetailEntry!=null) {
            String localName = firstDetailEntry.getElementName().getLocalName();
            if (localName.endsWith("Exception")) {
                //got a subtag
                return firstDetailEntry.getChildElements();
            }
        }
        return detail.getDetailEntries();
    }
    private List<SOAPElement> getFaultInfoElements(@NotNull Detail detail) {
        List<SOAPElement> elements = new ArrayList<>();
        Iterator iterator = detailChildrenIterator(detail);
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof SOAPElement) {
                elements.add((SOAPElement)next);
            }
        }
        return elements;
    }

    /**
     * @return in case there is no such info in the SOAPFault then an object with null values and the UNKNOWN
     *         enum values is returned.
     */
    @NotNull
    private FaultInfo extractFaultInfo(@NotNull Detail detail) {
        List<SOAPElement> entries = getFaultInfoElements(detail);

        Blame blame = null;
        String faultCause = null;
        Retry retrySameServer = null;
        Retry retryOtherServers = null;
        Boolean problemReported = null;
        Integer errorCode = null;
        String message = null;

        for (SOAPElement entry : entries) {
            String tagName = entry.getTagName();
            Node node = entry.getFirstChild();
            if (tagName.equalsIgnoreCase("blame")) {
                try {
                    blame = Blame.valueOf(((Text) node).getWholeText());
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Unknown Blame value!");
                }
            } else if (tagName.equalsIgnoreCase("faultCause")) {
                try {
                    faultCause = ((Text)node).getWholeText();
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Unknown faultCause value!");
                }
            } else if (tagName.equalsIgnoreCase("message")) {
                try {
                    message = ((Text)node).getWholeText();
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Unknown message value!");
                }
            } else if (tagName.equalsIgnoreCase("errorCode")) {
                try {
                    errorCode = Integer.parseInt(((Text) node).getWholeText(), 10);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Unknown errorCode value!");
                }
            } else if (tagName.equalsIgnoreCase("retrySameServer")) {
                retrySameServer = readRetry(entry);
            } else if (tagName.equalsIgnoreCase("retryOtherServers")) {
                retryOtherServers = readRetry(entry);
            } else if (tagName.equals("problemReported")) {
                String boolText = ((Text)node).getWholeText();
                //while testing this was "true" or "false". doing defensive translation here, worst case i leave null (unknown).
                if (boolText.equalsIgnoreCase("true")) {
                    problemReported = true;
                } else if (boolText.equalsIgnoreCase("false")) {
                    problemReported = false;
                } else {
                    throw new RuntimeException("Unknown problemReported value: "+boolText);
                }
            } else {
                log.warn("Unknown new field in fault info from server: "+tagName);
            }
        }

        if (errorCode==null) throw new RuntimeException("Missing errorCode");
        if (blame==null) throw new RuntimeException("Missing blame");
        if (faultCause==null) throw new RuntimeException("Missing faultCause");
        if (message==null) throw new RuntimeException("Missing message");
        if (problemReported==null) throw new RuntimeException("Missing problemReported");

        return new FaultInfo(errorCode, blame, faultCause, message,
                retrySameServer, retryOtherServers,
                problemReported);
    }

    private Retry readRetry(Node node) {
        //<retryOtherServers><retryType>NO</retryType></retryOtherServers>
        RetryType retryType = null;
        Long retryInSeconds = null; //optional
        NodeList childNodes = node.getChildNodes();
        for (int i=0; i<childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName.equalsIgnoreCase("retryType")) {
                retryType = RetryType.valueOf(item.getFirstChild().getNodeValue().toUpperCase(Locale.ENGLISH));
            } else if (nodeName.equalsIgnoreCase("retryInSeconds")) {
                if (!item.getNodeValue().equals("null")) {
                    retryInSeconds = Long.parseLong(item.getFirstChild().getNodeValue());
                }
            } else {
                log.warn("Unknown retry property: "+ nodeName);
            }
        }
        if (retryType==null) {
            throw new RuntimeException("Missing retryType");
        }
        return new Retry(retryType, retryInSeconds);
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
