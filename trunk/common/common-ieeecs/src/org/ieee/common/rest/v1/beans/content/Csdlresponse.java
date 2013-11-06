
package org.ieee.common.rest.v1.beans.content;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="status">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="hits" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="openurlconfiguration" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="versionnumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="imageheight" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="imagewidth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="imagealttext" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="baseurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="contentlist" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="content" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="contentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="categorylist" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="packagelist" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="package" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="packageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="productlist" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                                                                     &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="productmetadata" minOccurs="0">
 *                                                                       &lt;complexType>
 *                                                                         &lt;complexContent>
 *                                                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                             &lt;sequence>
 *                                                                               &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
 *                                                                                 &lt;complexType>
 *                                                                                   &lt;simpleContent>
 *                                                                                     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                                                                       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                                                     &lt;/extension>
 *                                                                                   &lt;/simpleContent>
 *                                                                                 &lt;/complexType>
 *                                                                               &lt;/element>
 *                                                                             &lt;/sequence>
 *                                                                           &lt;/restriction>
 *                                                                         &lt;/complexContent>
 *                                                                       &lt;/complexType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                                                 &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="mediatypes" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="packagetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                                                 &lt;element name="packagemetadata" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
 *                                                             &lt;complexType>
 *                                                               &lt;simpleContent>
 *                                                                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                                                   &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                                 &lt;/extension>
 *                                                               &lt;/simpleContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="doi" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="contenttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="mediaformats" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="defaultprice" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="xplore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="peerreview" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                             &lt;element name="creatorlist" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="referencelist" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="reference" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="referenceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="associatedcontentlist" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="associatedcontent" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="associatedcontentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="displaystring" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="linkstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="linktype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="mediatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="creatorlist" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                     &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="associatedcontentmetadata" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
 *                                                             &lt;complexType>
 *                                                               &lt;simpleContent>
 *                                                                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                                                   &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                                 &lt;/extension>
 *                                                               &lt;/simpleContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "status",
    "openurlconfiguration",
    "contentlist"
})
@XmlRootElement(name = "csdlresponse")
public class Csdlresponse {

    @XmlElement(required = true)
    protected Csdlresponse.Status status;
    protected Csdlresponse.Openurlconfiguration openurlconfiguration;
    protected Csdlresponse.Contentlist contentlist;

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link Csdlresponse.Status }
     *     
     */
    public Csdlresponse.Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link Csdlresponse.Status }
     *     
     */
    public void setStatus(Csdlresponse.Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the openurlconfiguration property.
     * 
     * @return
     *     possible object is
     *     {@link Csdlresponse.Openurlconfiguration }
     *     
     */
    public Csdlresponse.Openurlconfiguration getOpenurlconfiguration() {
        return openurlconfiguration;
    }

    /**
     * Sets the value of the openurlconfiguration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Csdlresponse.Openurlconfiguration }
     *     
     */
    public void setOpenurlconfiguration(Csdlresponse.Openurlconfiguration value) {
        this.openurlconfiguration = value;
    }

    /**
     * Gets the value of the contentlist property.
     * 
     * @return
     *     possible object is
     *     {@link Csdlresponse.Contentlist }
     *     
     */
    public Csdlresponse.Contentlist getContentlist() {
        return contentlist;
    }

    /**
     * Sets the value of the contentlist property.
     * 
     * @param value
     *     allowed object is
     *     {@link Csdlresponse.Contentlist }
     *     
     */
    public void setContentlist(Csdlresponse.Contentlist value) {
        this.contentlist = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="content" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="contentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="categorylist" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="packagelist" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="package" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="packageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="productlist" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                                                           &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="productmetadata" minOccurs="0">
     *                                                             &lt;complexType>
     *                                                               &lt;complexContent>
     *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                                   &lt;sequence>
     *                                                                     &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
     *                                                                       &lt;complexType>
     *                                                                         &lt;simpleContent>
     *                                                                           &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                                                             &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                                                           &lt;/extension>
     *                                                                         &lt;/simpleContent>
     *                                                                       &lt;/complexType>
     *                                                                     &lt;/element>
     *                                                                   &lt;/sequence>
     *                                                                 &lt;/restriction>
     *                                                               &lt;/complexContent>
     *                                                             &lt;/complexType>
     *                                                           &lt;/element>
     *                                                         &lt;/sequence>
     *                                                       &lt;/restriction>
     *                                                     &lt;/complexContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                                       &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="mediatypes" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="packagetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                                       &lt;element name="packagemetadata" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
     *                                                   &lt;complexType>
     *                                                     &lt;simpleContent>
     *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                                         &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                                       &lt;/extension>
     *                                                     &lt;/simpleContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="doi" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="contenttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="mediaformats" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="defaultprice" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="xplore" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="peerreview" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *                   &lt;element name="creatorlist" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="referencelist" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="reference" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="referenceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="associatedcontentlist" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="associatedcontent" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="associatedcontentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="displaystring" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="linkstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="linktype" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="mediatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="creatorlist" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
     *                                                   &lt;complexType>
     *                                                     &lt;complexContent>
     *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                                         &lt;sequence>
     *                                                           &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                           &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                                         &lt;/sequence>
     *                                                       &lt;/restriction>
     *                                                     &lt;/complexContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                       &lt;element name="associatedcontentmetadata" minOccurs="0">
     *                                         &lt;complexType>
     *                                           &lt;complexContent>
     *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                               &lt;sequence>
     *                                                 &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
     *                                                   &lt;complexType>
     *                                                     &lt;simpleContent>
     *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                                         &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                                       &lt;/extension>
     *                                                     &lt;/simpleContent>
     *                                                   &lt;/complexType>
     *                                                 &lt;/element>
     *                                               &lt;/sequence>
     *                                             &lt;/restriction>
     *                                           &lt;/complexContent>
     *                                         &lt;/complexType>
     *                                       &lt;/element>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "content"
    })
    public static class Contentlist {

        protected List<Csdlresponse.Contentlist.Content> content;

        /**
         * Gets the value of the content property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the content property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContent().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Csdlresponse.Contentlist.Content }
         * 
         * 
         */
        public List<Csdlresponse.Contentlist.Content> getContent() {
            if (content == null) {
                content = new ArrayList<Csdlresponse.Contentlist.Content>();
            }
            return this.content;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="contentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="categorylist" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="packagelist" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="package" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="packageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="productlist" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
         *                                         &lt;complexType>
         *                                           &lt;complexContent>
         *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                               &lt;sequence>
         *                                                 &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *                                                 &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="productmetadata" minOccurs="0">
         *                                                   &lt;complexType>
         *                                                     &lt;complexContent>
         *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                                         &lt;sequence>
         *                                                           &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
         *                                                             &lt;complexType>
         *                                                               &lt;simpleContent>
         *                                                                 &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                                                                   &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                                                 &lt;/extension>
         *                                                               &lt;/simpleContent>
         *                                                             &lt;/complexType>
         *                                                           &lt;/element>
         *                                                         &lt;/sequence>
         *                                                       &lt;/restriction>
         *                                                     &lt;/complexContent>
         *                                                   &lt;/complexType>
         *                                                 &lt;/element>
         *                                               &lt;/sequence>
         *                                             &lt;/restriction>
         *                                           &lt;/complexContent>
         *                                         &lt;/complexType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *                             &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="mediatypes" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="packagetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *                             &lt;element name="packagemetadata" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
         *                                         &lt;complexType>
         *                                           &lt;simpleContent>
         *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                             &lt;/extension>
         *                                           &lt;/simpleContent>
         *                                         &lt;/complexType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="doi" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="summary" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="copyright" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="contenttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="filename" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="mediaformats" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="uri" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="defaultprice" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="xplore" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="peerreview" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
         *         &lt;element name="creatorlist" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="referencelist" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="reference" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="referenceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="associatedcontentlist" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="associatedcontent" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="associatedcontentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="displaystring" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="linkstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="linktype" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="mediatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="creatorlist" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
         *                                         &lt;complexType>
         *                                           &lt;complexContent>
         *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                               &lt;sequence>
         *                                                 &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                                 &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                                               &lt;/sequence>
         *                                             &lt;/restriction>
         *                                           &lt;/complexContent>
         *                                         &lt;/complexType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                             &lt;element name="associatedcontentmetadata" minOccurs="0">
         *                               &lt;complexType>
         *                                 &lt;complexContent>
         *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                                     &lt;sequence>
         *                                       &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
         *                                         &lt;complexType>
         *                                           &lt;simpleContent>
         *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                                             &lt;/extension>
         *                                           &lt;/simpleContent>
         *                                         &lt;/complexType>
         *                                       &lt;/element>
         *                                     &lt;/sequence>
         *                                   &lt;/restriction>
         *                                 &lt;/complexContent>
         *                               &lt;/complexType>
         *                             &lt;/element>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "contentid",
            "categorylist",
            "packagelist",
            "doi",
            "title",
            "subtitle",
            "publicationdate",
            "length",
            "summary",
            "copyright",
            "contenttype",
            "keywords",
            "filename",
            "mediaformats",
            "uri",
            "price",
            "defaultprice",
            "xplore",
            "peerreview",
            "updatetime",
            "creatorlist",
            "referencelist",
            "associatedcontentlist"
        })
        public static class Content {

            @XmlElement(required = true)
            protected String contentid;
            protected Csdlresponse.Contentlist.Content.Categorylist categorylist;
            protected Csdlresponse.Contentlist.Content.Packagelist packagelist;
            @XmlElement(required = true)
            protected String doi;
            @XmlElement(required = true)
            protected String title;
            @XmlElement(required = true)
            protected String subtitle;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar publicationdate;
            @XmlElement(required = true)
            protected String length;
            @XmlElement(required = true)
            protected String summary;
            @XmlElement(required = true)
            protected String copyright;
            @XmlElement(required = true)
            protected String contenttype;
            @XmlElement(required = true)
            protected String keywords;
            @XmlElement(required = true)
            protected String filename;
            @XmlElement(required = true)
            protected String mediaformats;
            @XmlElement(required = true)
            protected String uri;
            @XmlElement(required = true)
            protected String price;
            @XmlElement(required = true)
            protected String defaultprice;
            @XmlElement(required = true)
            protected String xplore;
            @XmlElement(required = true)
            protected String peerreview;
            @XmlElement(required = true)
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar updatetime;
            protected Csdlresponse.Contentlist.Content.Creatorlist creatorlist;
            protected Csdlresponse.Contentlist.Content.Referencelist referencelist;
            protected Csdlresponse.Contentlist.Content.Associatedcontentlist associatedcontentlist;

            /**
             * Gets the value of the contentid property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContentid() {
                return contentid;
            }

            /**
             * Sets the value of the contentid property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContentid(String value) {
                this.contentid = value;
            }

            /**
             * Gets the value of the categorylist property.
             * 
             * @return
             *     possible object is
             *     {@link Csdlresponse.Contentlist.Content.Categorylist }
             *     
             */
            public Csdlresponse.Contentlist.Content.Categorylist getCategorylist() {
                return categorylist;
            }

            /**
             * Sets the value of the categorylist property.
             * 
             * @param value
             *     allowed object is
             *     {@link Csdlresponse.Contentlist.Content.Categorylist }
             *     
             */
            public void setCategorylist(Csdlresponse.Contentlist.Content.Categorylist value) {
                this.categorylist = value;
            }

            /**
             * Gets the value of the packagelist property.
             * 
             * @return
             *     possible object is
             *     {@link Csdlresponse.Contentlist.Content.Packagelist }
             *     
             */
            public Csdlresponse.Contentlist.Content.Packagelist getPackagelist() {
                return packagelist;
            }

            /**
             * Sets the value of the packagelist property.
             * 
             * @param value
             *     allowed object is
             *     {@link Csdlresponse.Contentlist.Content.Packagelist }
             *     
             */
            public void setPackagelist(Csdlresponse.Contentlist.Content.Packagelist value) {
                this.packagelist = value;
            }

            /**
             * Gets the value of the doi property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDoi() {
                return doi;
            }

            /**
             * Sets the value of the doi property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDoi(String value) {
                this.doi = value;
            }

            /**
             * Gets the value of the title property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getTitle() {
                return title;
            }

            /**
             * Sets the value of the title property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setTitle(String value) {
                this.title = value;
            }

            /**
             * Gets the value of the subtitle property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSubtitle() {
                return subtitle;
            }

            /**
             * Sets the value of the subtitle property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSubtitle(String value) {
                this.subtitle = value;
            }

            /**
             * Gets the value of the publicationdate property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getPublicationdate() {
                return publicationdate;
            }

            /**
             * Sets the value of the publicationdate property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setPublicationdate(XMLGregorianCalendar value) {
                this.publicationdate = value;
            }

            /**
             * Gets the value of the length property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLength() {
                return length;
            }

            /**
             * Sets the value of the length property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLength(String value) {
                this.length = value;
            }

            /**
             * Gets the value of the summary property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSummary() {
                return summary;
            }

            /**
             * Sets the value of the summary property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSummary(String value) {
                this.summary = value;
            }

            /**
             * Gets the value of the copyright property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCopyright() {
                return copyright;
            }

            /**
             * Sets the value of the copyright property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCopyright(String value) {
                this.copyright = value;
            }

            /**
             * Gets the value of the contenttype property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContenttype() {
                return contenttype;
            }

            /**
             * Sets the value of the contenttype property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContenttype(String value) {
                this.contenttype = value;
            }

            /**
             * Gets the value of the keywords property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKeywords() {
                return keywords;
            }

            /**
             * Sets the value of the keywords property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKeywords(String value) {
                this.keywords = value;
            }

            /**
             * Gets the value of the filename property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getFilename() {
                return filename;
            }

            /**
             * Sets the value of the filename property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setFilename(String value) {
                this.filename = value;
            }

            /**
             * Gets the value of the mediaformats property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMediaformats() {
                return mediaformats;
            }

            /**
             * Sets the value of the mediaformats property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMediaformats(String value) {
                this.mediaformats = value;
            }

            /**
             * Gets the value of the uri property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getUri() {
                return uri;
            }

            /**
             * Sets the value of the uri property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setUri(String value) {
                this.uri = value;
            }

            /**
             * Gets the value of the price property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrice() {
                return price;
            }

            /**
             * Sets the value of the price property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrice(String value) {
                this.price = value;
            }

            /**
             * Gets the value of the defaultprice property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDefaultprice() {
                return defaultprice;
            }

            /**
             * Sets the value of the defaultprice property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDefaultprice(String value) {
                this.defaultprice = value;
            }

            /**
             * Gets the value of the xplore property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getXplore() {
                return xplore;
            }

            /**
             * Sets the value of the xplore property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setXplore(String value) {
                this.xplore = value;
            }

            /**
             * Gets the value of the peerreview property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPeerreview() {
                return peerreview;
            }

            /**
             * Sets the value of the peerreview property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPeerreview(String value) {
                this.peerreview = value;
            }

            /**
             * Gets the value of the updatetime property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getUpdatetime() {
                return updatetime;
            }

            /**
             * Sets the value of the updatetime property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setUpdatetime(XMLGregorianCalendar value) {
                this.updatetime = value;
            }

            /**
             * Gets the value of the creatorlist property.
             * 
             * @return
             *     possible object is
             *     {@link Csdlresponse.Contentlist.Content.Creatorlist }
             *     
             */
            public Csdlresponse.Contentlist.Content.Creatorlist getCreatorlist() {
                return creatorlist;
            }

            /**
             * Sets the value of the creatorlist property.
             * 
             * @param value
             *     allowed object is
             *     {@link Csdlresponse.Contentlist.Content.Creatorlist }
             *     
             */
            public void setCreatorlist(Csdlresponse.Contentlist.Content.Creatorlist value) {
                this.creatorlist = value;
            }

            /**
             * Gets the value of the referencelist property.
             * 
             * @return
             *     possible object is
             *     {@link Csdlresponse.Contentlist.Content.Referencelist }
             *     
             */
            public Csdlresponse.Contentlist.Content.Referencelist getReferencelist() {
                return referencelist;
            }

            /**
             * Sets the value of the referencelist property.
             * 
             * @param value
             *     allowed object is
             *     {@link Csdlresponse.Contentlist.Content.Referencelist }
             *     
             */
            public void setReferencelist(Csdlresponse.Contentlist.Content.Referencelist value) {
                this.referencelist = value;
            }

            /**
             * Gets the value of the associatedcontentlist property.
             * 
             * @return
             *     possible object is
             *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist }
             *     
             */
            public Csdlresponse.Contentlist.Content.Associatedcontentlist getAssociatedcontentlist() {
                return associatedcontentlist;
            }

            /**
             * Sets the value of the associatedcontentlist property.
             * 
             * @param value
             *     allowed object is
             *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist }
             *     
             */
            public void setAssociatedcontentlist(Csdlresponse.Contentlist.Content.Associatedcontentlist value) {
                this.associatedcontentlist = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="associatedcontent" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="associatedcontentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="displaystring" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="linkstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="linktype" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="mediatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="creatorlist" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
             *                               &lt;complexType>
             *                                 &lt;complexContent>
             *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                                     &lt;sequence>
             *                                       &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                     &lt;/sequence>
             *                                   &lt;/restriction>
             *                                 &lt;/complexContent>
             *                               &lt;/complexType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="associatedcontentmetadata" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
             *                               &lt;complexType>
             *                                 &lt;simpleContent>
             *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                                   &lt;/extension>
             *                                 &lt;/simpleContent>
             *                               &lt;/complexType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "associatedcontent"
            })
            public static class Associatedcontentlist {

                protected List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent> associatedcontent;

                /**
                 * Gets the value of the associatedcontent property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the associatedcontent property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getAssociatedcontent().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent }
                 * 
                 * 
                 */
                public List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent> getAssociatedcontent() {
                    if (associatedcontent == null) {
                        associatedcontent = new ArrayList<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent>();
                    }
                    return this.associatedcontent;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="associatedcontentid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="displaystring" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="linkstring" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="linktype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="mediatype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="creatorlist" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="associatedcontentmetadata" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;simpleContent>
                 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                         &lt;/extension>
                 *                       &lt;/simpleContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "associatedcontentid",
                    "displaystring",
                    "linkstring",
                    "linktype",
                    "link",
                    "price",
                    "mediatype",
                    "creatorlist",
                    "associatedcontentmetadata"
                })
                public static class Associatedcontent {

                    @XmlElement(required = true)
                    protected String associatedcontentid;
                    @XmlElement(required = true)
                    protected String displaystring;
                    @XmlElement(required = true)
                    protected String linkstring;
                    @XmlElement(required = true)
                    protected String linktype;
                    @XmlElement(required = true)
                    protected String link;
                    @XmlElement(required = true)
                    protected String price;
                    @XmlElement(required = true)
                    protected String mediatype;
                    protected Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist creatorlist;
                    protected Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata associatedcontentmetadata;

                    /**
                     * Gets the value of the associatedcontentid property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getAssociatedcontentid() {
                        return associatedcontentid;
                    }

                    /**
                     * Sets the value of the associatedcontentid property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setAssociatedcontentid(String value) {
                        this.associatedcontentid = value;
                    }

                    /**
                     * Gets the value of the displaystring property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDisplaystring() {
                        return displaystring;
                    }

                    /**
                     * Sets the value of the displaystring property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDisplaystring(String value) {
                        this.displaystring = value;
                    }

                    /**
                     * Gets the value of the linkstring property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLinkstring() {
                        return linkstring;
                    }

                    /**
                     * Sets the value of the linkstring property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLinkstring(String value) {
                        this.linkstring = value;
                    }

                    /**
                     * Gets the value of the linktype property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLinktype() {
                        return linktype;
                    }

                    /**
                     * Sets the value of the linktype property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLinktype(String value) {
                        this.linktype = value;
                    }

                    /**
                     * Gets the value of the link property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getLink() {
                        return link;
                    }

                    /**
                     * Sets the value of the link property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setLink(String value) {
                        this.link = value;
                    }

                    /**
                     * Gets the value of the price property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPrice() {
                        return price;
                    }

                    /**
                     * Sets the value of the price property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPrice(String value) {
                        this.price = value;
                    }

                    /**
                     * Gets the value of the mediatype property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMediatype() {
                        return mediatype;
                    }

                    /**
                     * Sets the value of the mediatype property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMediatype(String value) {
                        this.mediatype = value;
                    }

                    /**
                     * Gets the value of the creatorlist property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist }
                     *     
                     */
                    public Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist getCreatorlist() {
                        return creatorlist;
                    }

                    /**
                     * Sets the value of the creatorlist property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist }
                     *     
                     */
                    public void setCreatorlist(Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist value) {
                        this.creatorlist = value;
                    }

                    /**
                     * Gets the value of the associatedcontentmetadata property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata }
                     *     
                     */
                    public Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata getAssociatedcontentmetadata() {
                        return associatedcontentmetadata;
                    }

                    /**
                     * Sets the value of the associatedcontentmetadata property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata }
                     *     
                     */
                    public void setAssociatedcontentmetadata(Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata value) {
                        this.associatedcontentmetadata = value;
                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;simpleContent>
                     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *               &lt;/extension>
                     *             &lt;/simpleContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "value"
                    })
                    public static class Associatedcontentmetadata {

                        protected List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata.Value> value;

                        /**
                         * Gets the value of the value property.
                         * 
                         * <p>
                         * This accessor method returns a reference to the live list,
                         * not a snapshot. Therefore any modification you make to the
                         * returned list will be present inside the JAXB object.
                         * This is why there is not a <CODE>set</CODE> method for the value property.
                         * 
                         * <p>
                         * For example, to add a new item, do as follows:
                         * <pre>
                         *    getValue().add(newItem);
                         * </pre>
                         * 
                         * 
                         * <p>
                         * Objects of the following type(s) are allowed in the list
                         * {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata.Value }
                         * 
                         * 
                         */
                        public List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata.Value> getValue() {
                            if (value == null) {
                                value = new ArrayList<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Associatedcontentmetadata.Value>();
                            }
                            return this.value;
                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;simpleContent>
                         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                         *     &lt;/extension>
                         *   &lt;/simpleContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class Value {

                            @XmlValue
                            protected String value;
                            @XmlAttribute(name = "name")
                            protected String name;

                            /**
                             * Gets the value of the value property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getValue() {
                                return value;
                            }

                            /**
                             * Sets the value of the value property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setValue(String value) {
                                this.value = value;
                            }

                            /**
                             * Gets the value of the name property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getName() {
                                return name;
                            }

                            /**
                             * Sets the value of the name property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setName(String value) {
                                this.name = value;
                            }

                        }

                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "creator"
                    })
                    public static class Creatorlist {

                        protected List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist.Creator> creator;

                        /**
                         * Gets the value of the creator property.
                         * 
                         * <p>
                         * This accessor method returns a reference to the live list,
                         * not a snapshot. Therefore any modification you make to the
                         * returned list will be present inside the JAXB object.
                         * This is why there is not a <CODE>set</CODE> method for the creator property.
                         * 
                         * <p>
                         * For example, to add a new item, do as follows:
                         * <pre>
                         *    getCreator().add(newItem);
                         * </pre>
                         * 
                         * 
                         * <p>
                         * Objects of the following type(s) are allowed in the list
                         * {@link Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist.Creator }
                         * 
                         * 
                         */
                        public List<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist.Creator> getCreator() {
                            if (creator == null) {
                                creator = new ArrayList<Csdlresponse.Contentlist.Content.Associatedcontentlist.Associatedcontent.Creatorlist.Creator>();
                            }
                            return this.creator;
                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "givenname",
                            "surname",
                            "sponsor",
                            "role"
                        })
                        public static class Creator {

                            @XmlElement(required = true)
                            protected String givenname;
                            @XmlElement(required = true)
                            protected String surname;
                            @XmlElement(required = true)
                            protected String sponsor;
                            @XmlElement(required = true)
                            protected String role;

                            /**
                             * Gets the value of the givenname property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getGivenname() {
                                return givenname;
                            }

                            /**
                             * Sets the value of the givenname property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setGivenname(String value) {
                                this.givenname = value;
                            }

                            /**
                             * Gets the value of the surname property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getSurname() {
                                return surname;
                            }

                            /**
                             * Sets the value of the surname property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setSurname(String value) {
                                this.surname = value;
                            }

                            /**
                             * Gets the value of the sponsor property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getSponsor() {
                                return sponsor;
                            }

                            /**
                             * Sets the value of the sponsor property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setSponsor(String value) {
                                this.sponsor = value;
                            }

                            /**
                             * Gets the value of the role property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getRole() {
                                return role;
                            }

                            /**
                             * Sets the value of the role property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setRole(String value) {
                                this.role = value;
                            }

                        }

                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "title"
            })
            public static class Categorylist {

                protected List<String> title;

                /**
                 * Gets the value of the title property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the title property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTitle().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link String }
                 * 
                 * 
                 */
                public List<String> getTitle() {
                    if (title == null) {
                        title = new ArrayList<String>();
                    }
                    return this.title;
                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="creator" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "creator"
            })
            public static class Creatorlist {

                protected List<Csdlresponse.Contentlist.Content.Creatorlist.Creator> creator;

                /**
                 * Gets the value of the creator property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the creator property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCreator().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Csdlresponse.Contentlist.Content.Creatorlist.Creator }
                 * 
                 * 
                 */
                public List<Csdlresponse.Contentlist.Content.Creatorlist.Creator> getCreator() {
                    if (creator == null) {
                        creator = new ArrayList<Csdlresponse.Contentlist.Content.Creatorlist.Creator>();
                    }
                    return this.creator;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="givenname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="role" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "givenname",
                    "surname",
                    "sponsor",
                    "role"
                })
                public static class Creator {

                    @XmlElement(required = true)
                    protected String givenname;
                    @XmlElement(required = true)
                    protected String surname;
                    @XmlElement(required = true)
                    protected String sponsor;
                    @XmlElement(required = true)
                    protected String role;

                    /**
                     * Gets the value of the givenname property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getGivenname() {
                        return givenname;
                    }

                    /**
                     * Sets the value of the givenname property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setGivenname(String value) {
                        this.givenname = value;
                    }

                    /**
                     * Gets the value of the surname property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSurname() {
                        return surname;
                    }

                    /**
                     * Sets the value of the surname property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSurname(String value) {
                        this.surname = value;
                    }

                    /**
                     * Gets the value of the sponsor property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSponsor() {
                        return sponsor;
                    }

                    /**
                     * Sets the value of the sponsor property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSponsor(String value) {
                        this.sponsor = value;
                    }

                    /**
                     * Gets the value of the role property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getRole() {
                        return role;
                    }

                    /**
                     * Sets the value of the role property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setRole(String value) {
                        this.role = value;
                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="package" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="packageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="productlist" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
             *                               &lt;complexType>
             *                                 &lt;complexContent>
             *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                                     &lt;sequence>
             *                                       &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
             *                                       &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                                       &lt;element name="productmetadata" minOccurs="0">
             *                                         &lt;complexType>
             *                                           &lt;complexContent>
             *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                                               &lt;sequence>
             *                                                 &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
             *                                                   &lt;complexType>
             *                                                     &lt;simpleContent>
             *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                                                         &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                                                       &lt;/extension>
             *                                                     &lt;/simpleContent>
             *                                                   &lt;/complexType>
             *                                                 &lt;/element>
             *                                               &lt;/sequence>
             *                                             &lt;/restriction>
             *                                           &lt;/complexContent>
             *                                         &lt;/complexType>
             *                                       &lt;/element>
             *                                     &lt;/sequence>
             *                                   &lt;/restriction>
             *                                 &lt;/complexContent>
             *                               &lt;/complexType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
             *                   &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="mediatypes" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="packagetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
             *                   &lt;element name="packagemetadata" minOccurs="0">
             *                     &lt;complexType>
             *                       &lt;complexContent>
             *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                           &lt;sequence>
             *                             &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
             *                               &lt;complexType>
             *                                 &lt;simpleContent>
             *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                                   &lt;/extension>
             *                                 &lt;/simpleContent>
             *                               &lt;/complexType>
             *                             &lt;/element>
             *                           &lt;/sequence>
             *                         &lt;/restriction>
             *                       &lt;/complexContent>
             *                     &lt;/complexType>
             *                   &lt;/element>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "_package"
            })
            public static class Packagelist {

                @XmlElement(name = "package")
                protected List<Csdlresponse.Contentlist.Content.Packagelist.Package> _package;

                /**
                 * Gets the value of the package property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the package property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPackage().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Csdlresponse.Contentlist.Content.Packagelist.Package }
                 * 
                 * 
                 */
                public List<Csdlresponse.Contentlist.Content.Packagelist.Package> getPackage() {
                    if (_package == null) {
                        _package = new ArrayList<Csdlresponse.Contentlist.Content.Packagelist.Package>();
                    }
                    return this._package;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="packageid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="productlist" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;complexContent>
                 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                           &lt;sequence>
                 *                             &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
                 *                             &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *                             &lt;element name="productmetadata" minOccurs="0">
                 *                               &lt;complexType>
                 *                                 &lt;complexContent>
                 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                                     &lt;sequence>
                 *                                       &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                 *                                         &lt;complexType>
                 *                                           &lt;simpleContent>
                 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *                                               &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                                             &lt;/extension>
                 *                                           &lt;/simpleContent>
                 *                                         &lt;/complexType>
                 *                                       &lt;/element>
                 *                                     &lt;/sequence>
                 *                                   &lt;/restriction>
                 *                                 &lt;/complexContent>
                 *                               &lt;/complexType>
                 *                             &lt;/element>
                 *                           &lt;/sequence>
                 *                         &lt;/restriction>
                 *                       &lt;/complexContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="publicationdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
                 *         &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="mediatypes" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="packagetype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
                 *         &lt;element name="packagemetadata" minOccurs="0">
                 *           &lt;complexType>
                 *             &lt;complexContent>
                 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *                 &lt;sequence>
                 *                   &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                 *                     &lt;complexType>
                 *                       &lt;simpleContent>
                 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *                         &lt;/extension>
                 *                       &lt;/simpleContent>
                 *                     &lt;/complexType>
                 *                   &lt;/element>
                 *                 &lt;/sequence>
                 *               &lt;/restriction>
                 *             &lt;/complexContent>
                 *           &lt;/complexType>
                 *         &lt;/element>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "packageid",
                    "productlist",
                    "title",
                    "subtitle",
                    "description",
                    "publicationdate",
                    "publisher",
                    "mediatypes",
                    "packagetype",
                    "price",
                    "updatetime",
                    "packagemetadata"
                })
                public static class Package {

                    @XmlElement(required = true)
                    protected String packageid;
                    protected Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist productlist;
                    @XmlElement(required = true)
                    protected String title;
                    @XmlElement(required = true)
                    protected String subtitle;
                    @XmlElement(required = true)
                    protected String description;
                    @XmlElement(required = true)
                    @XmlSchemaType(name = "dateTime")
                    protected XMLGregorianCalendar publicationdate;
                    @XmlElement(required = true)
                    protected String publisher;
                    @XmlElement(required = true)
                    protected String mediatypes;
                    @XmlElement(required = true)
                    protected String packagetype;
                    @XmlElement(required = true)
                    protected String price;
                    @XmlElement(required = true)
                    @XmlSchemaType(name = "dateTime")
                    protected XMLGregorianCalendar updatetime;
                    protected Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata packagemetadata;

                    /**
                     * Gets the value of the packageid property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPackageid() {
                        return packageid;
                    }

                    /**
                     * Sets the value of the packageid property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPackageid(String value) {
                        this.packageid = value;
                    }

                    /**
                     * Gets the value of the productlist property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist }
                     *     
                     */
                    public Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist getProductlist() {
                        return productlist;
                    }

                    /**
                     * Sets the value of the productlist property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist }
                     *     
                     */
                    public void setProductlist(Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist value) {
                        this.productlist = value;
                    }

                    /**
                     * Gets the value of the title property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTitle() {
                        return title;
                    }

                    /**
                     * Sets the value of the title property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTitle(String value) {
                        this.title = value;
                    }

                    /**
                     * Gets the value of the subtitle property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSubtitle() {
                        return subtitle;
                    }

                    /**
                     * Sets the value of the subtitle property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSubtitle(String value) {
                        this.subtitle = value;
                    }

                    /**
                     * Gets the value of the description property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDescription() {
                        return description;
                    }

                    /**
                     * Sets the value of the description property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDescription(String value) {
                        this.description = value;
                    }

                    /**
                     * Gets the value of the publicationdate property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getPublicationdate() {
                        return publicationdate;
                    }

                    /**
                     * Sets the value of the publicationdate property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setPublicationdate(XMLGregorianCalendar value) {
                        this.publicationdate = value;
                    }

                    /**
                     * Gets the value of the publisher property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPublisher() {
                        return publisher;
                    }

                    /**
                     * Sets the value of the publisher property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPublisher(String value) {
                        this.publisher = value;
                    }

                    /**
                     * Gets the value of the mediatypes property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMediatypes() {
                        return mediatypes;
                    }

                    /**
                     * Sets the value of the mediatypes property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMediatypes(String value) {
                        this.mediatypes = value;
                    }

                    /**
                     * Gets the value of the packagetype property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPackagetype() {
                        return packagetype;
                    }

                    /**
                     * Sets the value of the packagetype property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPackagetype(String value) {
                        this.packagetype = value;
                    }

                    /**
                     * Gets the value of the price property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPrice() {
                        return price;
                    }

                    /**
                     * Sets the value of the price property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPrice(String value) {
                        this.price = value;
                    }

                    /**
                     * Gets the value of the updatetime property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getUpdatetime() {
                        return updatetime;
                    }

                    /**
                     * Sets the value of the updatetime property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setUpdatetime(XMLGregorianCalendar value) {
                        this.updatetime = value;
                    }

                    /**
                     * Gets the value of the packagemetadata property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata }
                     *     
                     */
                    public Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata getPackagemetadata() {
                        return packagemetadata;
                    }

                    /**
                     * Sets the value of the packagemetadata property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata }
                     *     
                     */
                    public void setPackagemetadata(Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata value) {
                        this.packagemetadata = value;
                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;simpleContent>
                     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                     *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *               &lt;/extension>
                     *             &lt;/simpleContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "value"
                    })
                    public static class Packagemetadata {

                        protected List<Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata.Value> value;

                        /**
                         * Gets the value of the value property.
                         * 
                         * <p>
                         * This accessor method returns a reference to the live list,
                         * not a snapshot. Therefore any modification you make to the
                         * returned list will be present inside the JAXB object.
                         * This is why there is not a <CODE>set</CODE> method for the value property.
                         * 
                         * <p>
                         * For example, to add a new item, do as follows:
                         * <pre>
                         *    getValue().add(newItem);
                         * </pre>
                         * 
                         * 
                         * <p>
                         * Objects of the following type(s) are allowed in the list
                         * {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata.Value }
                         * 
                         * 
                         */
                        public List<Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata.Value> getValue() {
                            if (value == null) {
                                value = new ArrayList<Csdlresponse.Contentlist.Content.Packagelist.Package.Packagemetadata.Value>();
                            }
                            return this.value;
                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;simpleContent>
                         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                         *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                         *     &lt;/extension>
                         *   &lt;/simpleContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "value"
                        })
                        public static class Value {

                            @XmlValue
                            protected String value;
                            @XmlAttribute(name = "name")
                            protected String name;

                            /**
                             * Gets the value of the value property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getValue() {
                                return value;
                            }

                            /**
                             * Sets the value of the value property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setValue(String value) {
                                this.value = value;
                            }

                            /**
                             * Gets the value of the name property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getName() {
                                return name;
                            }

                            /**
                             * Sets the value of the name property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setName(String value) {
                                this.name = value;
                            }

                        }

                    }


                    /**
                     * <p>Java class for anonymous complex type.
                     * 
                     * <p>The following schema fragment specifies the expected content contained within this class.
                     * 
                     * <pre>
                     * &lt;complexType>
                     *   &lt;complexContent>
                     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *       &lt;sequence>
                     *         &lt;element name="product" maxOccurs="unbounded" minOccurs="0">
                     *           &lt;complexType>
                     *             &lt;complexContent>
                     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                 &lt;sequence>
                     *                   &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
                     *                   &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
                     *                   &lt;element name="productmetadata" minOccurs="0">
                     *                     &lt;complexType>
                     *                       &lt;complexContent>
                     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                     *                           &lt;sequence>
                     *                             &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                     *                               &lt;complexType>
                     *                                 &lt;simpleContent>
                     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                     *                                     &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                     *                                   &lt;/extension>
                     *                                 &lt;/simpleContent>
                     *                               &lt;/complexType>
                     *                             &lt;/element>
                     *                           &lt;/sequence>
                     *                         &lt;/restriction>
                     *                       &lt;/complexContent>
                     *                     &lt;/complexType>
                     *                   &lt;/element>
                     *                 &lt;/sequence>
                     *               &lt;/restriction>
                     *             &lt;/complexContent>
                     *           &lt;/complexType>
                     *         &lt;/element>
                     *       &lt;/sequence>
                     *     &lt;/restriction>
                     *   &lt;/complexContent>
                     * &lt;/complexType>
                     * </pre>
                     * 
                     * 
                     */
                    @XmlAccessorType(XmlAccessType.FIELD)
                    @XmlType(name = "", propOrder = {
                        "product"
                    })
                    public static class Productlist {

                        protected List<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product> product;

                        /**
                         * Gets the value of the product property.
                         * 
                         * <p>
                         * This accessor method returns a reference to the live list,
                         * not a snapshot. Therefore any modification you make to the
                         * returned list will be present inside the JAXB object.
                         * This is why there is not a <CODE>set</CODE> method for the product property.
                         * 
                         * <p>
                         * For example, to add a new item, do as follows:
                         * <pre>
                         *    getProduct().add(newItem);
                         * </pre>
                         * 
                         * 
                         * <p>
                         * Objects of the following type(s) are allowed in the list
                         * {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product }
                         * 
                         * 
                         */
                        public List<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product> getProduct() {
                            if (product == null) {
                                product = new ArrayList<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product>();
                            }
                            return this.product;
                        }


                        /**
                         * <p>Java class for anonymous complex type.
                         * 
                         * <p>The following schema fragment specifies the expected content contained within this class.
                         * 
                         * <pre>
                         * &lt;complexType>
                         *   &lt;complexContent>
                         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *       &lt;sequence>
                         *         &lt;element name="productid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="producttype" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="updatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
                         *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}string"/>
                         *         &lt;element name="productmetadata" minOccurs="0">
                         *           &lt;complexType>
                         *             &lt;complexContent>
                         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                         *                 &lt;sequence>
                         *                   &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                         *                     &lt;complexType>
                         *                       &lt;simpleContent>
                         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                         *                           &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                         *                         &lt;/extension>
                         *                       &lt;/simpleContent>
                         *                     &lt;/complexType>
                         *                   &lt;/element>
                         *                 &lt;/sequence>
                         *               &lt;/restriction>
                         *             &lt;/complexContent>
                         *           &lt;/complexType>
                         *         &lt;/element>
                         *       &lt;/sequence>
                         *     &lt;/restriction>
                         *   &lt;/complexContent>
                         * &lt;/complexType>
                         * </pre>
                         * 
                         * 
                         */
                        @XmlAccessorType(XmlAccessType.FIELD)
                        @XmlType(name = "", propOrder = {
                            "productid",
                            "title",
                            "producttype",
                            "subject",
                            "description",
                            "updatetime",
                            "price",
                            "productmetadata"
                        })
                        public static class Product {

                            @XmlElement(required = true)
                            protected String productid;
                            @XmlElement(required = true)
                            protected String title;
                            @XmlElement(required = true)
                            protected String producttype;
                            @XmlElement(required = true)
                            protected String subject;
                            @XmlElement(required = true)
                            protected String description;
                            @XmlElement(required = true)
                            @XmlSchemaType(name = "dateTime")
                            protected XMLGregorianCalendar updatetime;
                            @XmlElement(required = true)
                            protected String price;
                            protected Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata productmetadata;

                            /**
                             * Gets the value of the productid property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getProductid() {
                                return productid;
                            }

                            /**
                             * Sets the value of the productid property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setProductid(String value) {
                                this.productid = value;
                            }

                            /**
                             * Gets the value of the title property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getTitle() {
                                return title;
                            }

                            /**
                             * Sets the value of the title property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setTitle(String value) {
                                this.title = value;
                            }

                            /**
                             * Gets the value of the producttype property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getProducttype() {
                                return producttype;
                            }

                            /**
                             * Sets the value of the producttype property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setProducttype(String value) {
                                this.producttype = value;
                            }

                            /**
                             * Gets the value of the subject property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getSubject() {
                                return subject;
                            }

                            /**
                             * Sets the value of the subject property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setSubject(String value) {
                                this.subject = value;
                            }

                            /**
                             * Gets the value of the description property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getDescription() {
                                return description;
                            }

                            /**
                             * Sets the value of the description property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setDescription(String value) {
                                this.description = value;
                            }

                            /**
                             * Gets the value of the updatetime property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link XMLGregorianCalendar }
                             *     
                             */
                            public XMLGregorianCalendar getUpdatetime() {
                                return updatetime;
                            }

                            /**
                             * Sets the value of the updatetime property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link XMLGregorianCalendar }
                             *     
                             */
                            public void setUpdatetime(XMLGregorianCalendar value) {
                                this.updatetime = value;
                            }

                            /**
                             * Gets the value of the price property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link String }
                             *     
                             */
                            public String getPrice() {
                                return price;
                            }

                            /**
                             * Sets the value of the price property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link String }
                             *     
                             */
                            public void setPrice(String value) {
                                this.price = value;
                            }

                            /**
                             * Gets the value of the productmetadata property.
                             * 
                             * @return
                             *     possible object is
                             *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata }
                             *     
                             */
                            public Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata getProductmetadata() {
                                return productmetadata;
                            }

                            /**
                             * Sets the value of the productmetadata property.
                             * 
                             * @param value
                             *     allowed object is
                             *     {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata }
                             *     
                             */
                            public void setProductmetadata(Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata value) {
                                this.productmetadata = value;
                            }


                            /**
                             * <p>Java class for anonymous complex type.
                             * 
                             * <p>The following schema fragment specifies the expected content contained within this class.
                             * 
                             * <pre>
                             * &lt;complexType>
                             *   &lt;complexContent>
                             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                             *       &lt;sequence>
                             *         &lt;element name="value" maxOccurs="unbounded" minOccurs="0">
                             *           &lt;complexType>
                             *             &lt;simpleContent>
                             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                             *                 &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                             *               &lt;/extension>
                             *             &lt;/simpleContent>
                             *           &lt;/complexType>
                             *         &lt;/element>
                             *       &lt;/sequence>
                             *     &lt;/restriction>
                             *   &lt;/complexContent>
                             * &lt;/complexType>
                             * </pre>
                             * 
                             * 
                             */
                            @XmlAccessorType(XmlAccessType.FIELD)
                            @XmlType(name = "", propOrder = {
                                "value"
                            })
                            public static class Productmetadata {

                                protected List<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata.Value> value;

                                /**
                                 * Gets the value of the value property.
                                 * 
                                 * <p>
                                 * This accessor method returns a reference to the live list,
                                 * not a snapshot. Therefore any modification you make to the
                                 * returned list will be present inside the JAXB object.
                                 * This is why there is not a <CODE>set</CODE> method for the value property.
                                 * 
                                 * <p>
                                 * For example, to add a new item, do as follows:
                                 * <pre>
                                 *    getValue().add(newItem);
                                 * </pre>
                                 * 
                                 * 
                                 * <p>
                                 * Objects of the following type(s) are allowed in the list
                                 * {@link Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata.Value }
                                 * 
                                 * 
                                 */
                                public List<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata.Value> getValue() {
                                    if (value == null) {
                                        value = new ArrayList<Csdlresponse.Contentlist.Content.Packagelist.Package.Productlist.Product.Productmetadata.Value>();
                                    }
                                    return this.value;
                                }


                                /**
                                 * <p>Java class for anonymous complex type.
                                 * 
                                 * <p>The following schema fragment specifies the expected content contained within this class.
                                 * 
                                 * <pre>
                                 * &lt;complexType>
                                 *   &lt;simpleContent>
                                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                                 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
                                 *     &lt;/extension>
                                 *   &lt;/simpleContent>
                                 * &lt;/complexType>
                                 * </pre>
                                 * 
                                 * 
                                 */
                                @XmlAccessorType(XmlAccessType.FIELD)
                                @XmlType(name = "", propOrder = {
                                    "value"
                                })
                                public static class Value {

                                    @XmlValue
                                    protected String value;
                                    @XmlAttribute(name = "name")
                                    protected String name;

                                    /**
                                     * Gets the value of the value property.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link String }
                                     *     
                                     */
                                    public String getValue() {
                                        return value;
                                    }

                                    /**
                                     * Sets the value of the value property.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link String }
                                     *     
                                     */
                                    public void setValue(String value) {
                                        this.value = value;
                                    }

                                    /**
                                     * Gets the value of the name property.
                                     * 
                                     * @return
                                     *     possible object is
                                     *     {@link String }
                                     *     
                                     */
                                    public String getName() {
                                        return name;
                                    }

                                    /**
                                     * Sets the value of the name property.
                                     * 
                                     * @param value
                                     *     allowed object is
                                     *     {@link String }
                                     *     
                                     */
                                    public void setName(String value) {
                                        this.name = value;
                                    }

                                }

                            }

                        }

                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="reference" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="referenceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                 &lt;/sequence>
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "reference"
            })
            public static class Referencelist {

                protected List<Csdlresponse.Contentlist.Content.Referencelist.Reference> reference;

                /**
                 * Gets the value of the reference property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the reference property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getReference().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Csdlresponse.Contentlist.Content.Referencelist.Reference }
                 * 
                 * 
                 */
                public List<Csdlresponse.Contentlist.Content.Referencelist.Reference> getReference() {
                    if (reference == null) {
                        reference = new ArrayList<Csdlresponse.Contentlist.Content.Referencelist.Reference>();
                    }
                    return this.reference;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;sequence>
                 *         &lt;element name="referenceid" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *       &lt;/sequence>
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "referenceid",
                    "display"
                })
                public static class Reference {

                    @XmlElement(required = true)
                    protected String referenceid;
                    @XmlElement(required = true)
                    protected String display;

                    /**
                     * Gets the value of the referenceid property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getReferenceid() {
                        return referenceid;
                    }

                    /**
                     * Sets the value of the referenceid property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setReferenceid(String value) {
                        this.referenceid = value;
                    }

                    /**
                     * Gets the value of the display property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDisplay() {
                        return display;
                    }

                    /**
                     * Sets the value of the display property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDisplay(String value) {
                        this.display = value;
                    }

                }

            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="versionnumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="imageheight" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="imagewidth" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="imagealttext" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="baseurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "versionnumber",
        "image",
        "imageheight",
        "imagewidth",
        "imagealttext",
        "baseurl"
    })
    public static class Openurlconfiguration {

        @XmlElement(required = true)
        protected String versionnumber;
        @XmlElement(required = true)
        protected String image;
        @XmlElement(required = true)
        protected String imageheight;
        @XmlElement(required = true)
        protected String imagewidth;
        @XmlElement(required = true)
        protected String imagealttext;
        @XmlElement(required = true)
        protected String baseurl;

        /**
         * Gets the value of the versionnumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVersionnumber() {
            return versionnumber;
        }

        /**
         * Sets the value of the versionnumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVersionnumber(String value) {
            this.versionnumber = value;
        }

        /**
         * Gets the value of the image property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImage() {
            return image;
        }

        /**
         * Sets the value of the image property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImage(String value) {
            this.image = value;
        }

        /**
         * Gets the value of the imageheight property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImageheight() {
            return imageheight;
        }

        /**
         * Sets the value of the imageheight property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImageheight(String value) {
            this.imageheight = value;
        }

        /**
         * Gets the value of the imagewidth property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImagewidth() {
            return imagewidth;
        }

        /**
         * Sets the value of the imagewidth property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImagewidth(String value) {
            this.imagewidth = value;
        }

        /**
         * Gets the value of the imagealttext property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImagealttext() {
            return imagealttext;
        }

        /**
         * Sets the value of the imagealttext property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImagealttext(String value) {
            this.imagealttext = value;
        }

        /**
         * Gets the value of the baseurl property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBaseurl() {
            return baseurl;
        }

        /**
         * Sets the value of the baseurl property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBaseurl(String value) {
            this.baseurl = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="hits" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "code",
        "message",
        "hits"
    })
    public static class Status {

        @XmlElement(required = true)
        protected String code;
        @XmlElement(required = true)
        protected String message;
        @XmlElement(required = true)
        protected BigInteger hits;

        /**
         * Gets the value of the code property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCode() {
            return code;
        }

        /**
         * Sets the value of the code property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCode(String value) {
            this.code = value;
        }

        /**
         * Gets the value of the message property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMessage() {
            return message;
        }

        /**
         * Sets the value of the message property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMessage(String value) {
            this.message = value;
        }

        /**
         * Gets the value of the hits property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getHits() {
            return hits;
        }

        /**
         * Sets the value of the hits property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setHits(BigInteger value) {
            this.hits = value;
        }

    }

}
