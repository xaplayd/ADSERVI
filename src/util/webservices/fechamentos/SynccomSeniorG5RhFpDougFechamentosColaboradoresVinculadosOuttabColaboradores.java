
package util.webservices.fechamentos;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOuttabColaboradores complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType name="Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOuttabColaboradores">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="datAltTabEfe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nomFunTabEfe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="numCadTabEfe" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="optaVtTabEfe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="posTraTabEfe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOuttabColaboradores", propOrder = {
    "datAltTabEfe",
    "nomFunTabEfe",
    "numCadTabEfe",
    "optaVtTabEfe",
    "posTraTabEfe"
})
public class SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores {

    @XmlElementRef(name = "datAltTabEfe", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datAltTabEfe;
    @XmlElementRef(name = "nomFunTabEfe", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nomFunTabEfe;
    @XmlElementRef(name = "numCadTabEfe", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> numCadTabEfe;
    @XmlElementRef(name = "optaVtTabEfe", type = JAXBElement.class, required = false)
    protected JAXBElement<String> optaVtTabEfe;
    @XmlElementRef(name = "posTraTabEfe", type = JAXBElement.class, required = false)
    protected JAXBElement<String> posTraTabEfe;

    /**
     * Obtém o valor da propriedade datAltTabEfe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatAltTabEfe() {
        return datAltTabEfe;
    }

    /**
     * Define o valor da propriedade datAltTabEfe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatAltTabEfe(JAXBElement<String> value) {
        this.datAltTabEfe = value;
    }

    /**
     * Obtém o valor da propriedade nomFunTabEfe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomFunTabEfe() {
        return nomFunTabEfe;
    }

    /**
     * Define o valor da propriedade nomFunTabEfe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomFunTabEfe(JAXBElement<String> value) {
        this.nomFunTabEfe = value;
    }

    /**
     * Obtém o valor da propriedade numCadTabEfe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumCadTabEfe() {
        return numCadTabEfe;
    }

    /**
     * Define o valor da propriedade numCadTabEfe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumCadTabEfe(JAXBElement<Integer> value) {
        this.numCadTabEfe = value;
    }

    /**
     * Obtém o valor da propriedade optaVtTabEfe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOptaVtTabEfe() {
        return optaVtTabEfe;
    }

    /**
     * Define o valor da propriedade optaVtTabEfe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOptaVtTabEfe(JAXBElement<String> value) {
        this.optaVtTabEfe = value;
    }

    /**
     * Obtém o valor da propriedade posTraTabEfe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosTraTabEfe() {
        return posTraTabEfe;
    }

    /**
     * Define o valor da propriedade posTraTabEfe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosTraTabEfe(JAXBElement<String> value) {
        this.posTraTabEfe = value;
    }

}
