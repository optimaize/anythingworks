
package com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.wsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for blame.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="blame">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CLIENT"/>
 *     &lt;enumeration value="SERVER"/>
 *     &lt;enumeration value="NETWORK"/>
 *     &lt;enumeration value="UNKNOWN"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "blame")
@XmlEnum
public enum Blame {

    CLIENT,
    SERVER,
    NETWORK,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static Blame fromValue(String v) {
        return valueOf(v);
    }

}
