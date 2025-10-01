
package util.webservices.fechamentos;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Synccom.senior.g5.rh.fp.doug.fechamentosAfastamentosOuttabAfastamentos complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType name="Synccom.senior.g5.rh.fp.doug.fechamentosAfastamentosOuttabAfastamentos">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="datAfaTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="datTerTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="desSitTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horAfaTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horTerTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nomFunTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numCadTabAfast" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="qhrAfaTabAfast" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="sitAfaTabAfast" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Synccom.senior.g5.rh.fp.doug.fechamentosAfastamentosOuttabAfastamentos", propOrder = {
    "datAfaTabAfast",
    "datTerTabAfast",
    "desSitTabAfast",
    "horAfaTabAfast",
    "horTerTabAfast",
    "nomFunTabAfast",
    "numCadTabAfast",
    "qhrAfaTabAfast",
    "sitAfaTabAfast"
})
public class SynccomSeniorG5RhFpDougFechamentosAfastamentosOuttabAfastamentos {

    @XmlElementRef(name = "datAfaTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datAfaTabAfast;
    @XmlElementRef(name = "datTerTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datTerTabAfast;
    @XmlElementRef(name = "desSitTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desSitTabAfast;
    @XmlElementRef(name = "horAfaTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> horAfaTabAfast;
    @XmlElementRef(name = "horTerTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> horTerTabAfast;
    @XmlElementRef(name = "nomFunTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nomFunTabAfast;
    @XmlElementRef(name = "numCadTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> numCadTabAfast;
    @XmlElementRef(name = "qhrAfaTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<String> qhrAfaTabAfast;
    @XmlElementRef(name = "sitAfaTabAfast", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> sitAfaTabAfast;

    /**
     * Obtém o valor da propriedade datAfaTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatAfaTabAfast() {
        return datAfaTabAfast;
    }

    /**
     * Define o valor da propriedade datAfaTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatAfaTabAfast(JAXBElement<String> value) {
        this.datAfaTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade datTerTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatTerTabAfast() {
        return datTerTabAfast;
    }

    /**
     * Define o valor da propriedade datTerTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatTerTabAfast(JAXBElement<String> value) {
        this.datTerTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade desSitTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesSitTabAfast() {
        return desSitTabAfast;
    }

    /**
     * Define o valor da propriedade desSitTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesSitTabAfast(JAXBElement<String> value) {
        this.desSitTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade horAfaTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHorAfaTabAfast() {
        return horAfaTabAfast;
    }

    /**
     * Define o valor da propriedade horAfaTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHorAfaTabAfast(JAXBElement<String> value) {
        this.horAfaTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade horTerTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHorTerTabAfast() {
        return horTerTabAfast;
    }

    /**
     * Define o valor da propriedade horTerTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHorTerTabAfast(JAXBElement<String> value) {
        this.horTerTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade nomFunTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomFunTabAfast() {
        return nomFunTabAfast;
    }

    /**
     * Define o valor da propriedade nomFunTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomFunTabAfast(JAXBElement<String> value) {
        this.nomFunTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade numCadTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumCadTabAfast() {
        return numCadTabAfast;
    }

    /**
     * Define o valor da propriedade numCadTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumCadTabAfast(JAXBElement<Integer> value) {
        this.numCadTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade qhrAfaTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getQhrAfaTabAfast() {
        return qhrAfaTabAfast;
    }

    /**
     * Define o valor da propriedade qhrAfaTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setQhrAfaTabAfast(JAXBElement<String> value) {
        this.qhrAfaTabAfast = value;
    }

    /**
     * Obtém o valor da propriedade sitAfaTabAfast.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSitAfaTabAfast() {
        return sitAfaTabAfast;
    }

    /**
     * Define o valor da propriedade sitAfaTabAfast.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSitAfaTabAfast(JAXBElement<Integer> value) {
        this.sitAfaTabAfast = value;
    }

}
