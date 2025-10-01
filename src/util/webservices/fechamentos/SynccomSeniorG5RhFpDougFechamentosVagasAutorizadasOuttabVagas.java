
package util.webservices.fechamentos;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Synccom.senior.g5.rh.fp.doug.fechamentosVagasAutorizadasOuttabVagas complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType name="Synccom.senior.g5.rh.fp.doug.fechamentosVagasAutorizadasOuttabVagas">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="codPlvTabVaga" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="datQpoTabVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="desPosTabVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estPosTabVaga" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="posTraTabVaga" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="qtTpPoTabVaga" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="qtdQpoTabVaga" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Synccom.senior.g5.rh.fp.doug.fechamentosVagasAutorizadasOuttabVagas", propOrder = {
    "codPlvTabVaga",
    "datQpoTabVaga",
    "desPosTabVaga",
    "estPosTabVaga",
    "posTraTabVaga",
    "qtTpPoTabVaga",
    "qtdQpoTabVaga"
})
public class SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas {

    @XmlElementRef(name = "codPlvTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codPlvTabVaga;
    @XmlElementRef(name = "datQpoTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<String> datQpoTabVaga;
    @XmlElementRef(name = "desPosTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desPosTabVaga;
    @XmlElementRef(name = "estPosTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> estPosTabVaga;
    @XmlElementRef(name = "posTraTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<String> posTraTabVaga;
    @XmlElementRef(name = "qtTpPoTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> qtTpPoTabVaga;
    @XmlElementRef(name = "qtdQpoTabVaga", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> qtdQpoTabVaga;

    /**
     * Obtém o valor da propriedade codPlvTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCodPlvTabVaga() {
        return codPlvTabVaga;
    }

    /**
     * Define o valor da propriedade codPlvTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCodPlvTabVaga(JAXBElement<Integer> value) {
        this.codPlvTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade datQpoTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDatQpoTabVaga() {
        return datQpoTabVaga;
    }

    /**
     * Define o valor da propriedade datQpoTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDatQpoTabVaga(JAXBElement<String> value) {
        this.datQpoTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade desPosTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesPosTabVaga() {
        return desPosTabVaga;
    }

    /**
     * Define o valor da propriedade desPosTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesPosTabVaga(JAXBElement<String> value) {
        this.desPosTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade estPosTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getEstPosTabVaga() {
        return estPosTabVaga;
    }

    /**
     * Define o valor da propriedade estPosTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setEstPosTabVaga(JAXBElement<Integer> value) {
        this.estPosTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade posTraTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPosTraTabVaga() {
        return posTraTabVaga;
    }

    /**
     * Define o valor da propriedade posTraTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPosTraTabVaga(JAXBElement<String> value) {
        this.posTraTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade qtTpPoTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getQtTpPoTabVaga() {
        return qtTpPoTabVaga;
    }

    /**
     * Define o valor da propriedade qtTpPoTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setQtTpPoTabVaga(JAXBElement<Integer> value) {
        this.qtTpPoTabVaga = value;
    }

    /**
     * Obtém o valor da propriedade qtdQpoTabVaga.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getQtdQpoTabVaga() {
        return qtdQpoTabVaga;
    }

    /**
     * Define o valor da propriedade qtdQpoTabVaga.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setQtdQpoTabVaga(JAXBElement<Integer> value) {
        this.qtdQpoTabVaga = value;
    }

}
