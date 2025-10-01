
package util.webservices.fechamentos;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementRef;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOut complex type.</p>
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.</p>
 * 
 * <pre>{@code
 * <complexType name="Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOut">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="erroExecucao" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="log" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tabColaboradores" type="{http://services.senior.com.br}Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOuttabColaboradores" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Synccom.senior.g5.rh.fp.doug.fechamentosColaboradoresVinculadosOut", propOrder = {
    "erroExecucao",
    "log",
    "tabColaboradores"
})
public class SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOut {

    @XmlElementRef(name = "erroExecucao", type = JAXBElement.class, required = false)
    protected JAXBElement<String> erroExecucao;
    @XmlElementRef(name = "log", type = JAXBElement.class, required = false)
    protected JAXBElement<String> log;
    @XmlElement(nillable = true)
    protected List<SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores> tabColaboradores;

    /**
     * Obtém o valor da propriedade erroExecucao.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getErroExecucao() {
        return erroExecucao;
    }

    /**
     * Define o valor da propriedade erroExecucao.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setErroExecucao(JAXBElement<String> value) {
        this.erroExecucao = value;
    }

    /**
     * Obtém o valor da propriedade log.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLog() {
        return log;
    }

    /**
     * Define o valor da propriedade log.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLog(JAXBElement<String> value) {
        this.log = value;
    }

    /**
     * Gets the value of the tabColaboradores property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tabColaboradores property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getTabColaboradores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores }
     * </p>
     * 
     * 
     * @return
     *     The value of the tabColaboradores property.
     */
    public List<SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores> getTabColaboradores() {
        if (tabColaboradores == null) {
            tabColaboradores = new ArrayList<>();
        }
        return this.tabColaboradores;
    }

}
