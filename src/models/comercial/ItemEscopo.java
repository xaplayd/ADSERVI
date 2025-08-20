package models.comercial;

import java.util.Objects;

public class ItemEscopo {
	
	private Integer idCtoInt;
	private String nomeItem;
	private Double valorItem;
	private Integer idMedidaItem;
	private Double qtdItem;
	private Integer idEscopo;
	private String documento;
	
	
	public ItemEscopo () {};
	
	public ItemEscopo(Integer idCtoInt, String nomeItem, Double valorItem, Integer idMedidaItem, Double qtdItem,
			Integer idEscopo, String documento) {
		this.idCtoInt = idCtoInt;
		this.nomeItem = nomeItem;
		this.valorItem = valorItem;
		this.idMedidaItem = idMedidaItem;
		this.qtdItem = qtdItem;
		this.idEscopo = idEscopo;
		this.documento = documento;
	}

	public Integer getIdCtoInt() {
		return idCtoInt;
	}

	public void setIdCtoInt(Integer idCtoInt) {
		this.idCtoInt = idCtoInt;
	}

	public String getNomeItem() {
		return nomeItem;
	}

	public void setNomeItem(String nomeItem) {
		this.nomeItem = nomeItem;
	}

	public Double getValorItem() {
		return valorItem;
	}

	public void setValorItem(Double valorItem) {
		this.valorItem = valorItem;
	}

	public Integer getIdMedidaItem() {
		return idMedidaItem;
	}

	public void setIdMedidaItem(Integer idMedidaItem) {
		this.idMedidaItem = idMedidaItem;
	}

	public Double getQtdItem() {
		return qtdItem;
	}

	public void setQtdItem(Double qtdItem) {
		this.qtdItem = qtdItem;
	}

	public Integer getIdEscopo() {
		return idEscopo;
	}

	public void setIdEscopo(Integer idEscopo) {
		this.idEscopo = idEscopo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(documento, idCtoInt, idEscopo, idMedidaItem, nomeItem, qtdItem, valorItem);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemEscopo other = (ItemEscopo) obj;
		return Objects.equals(documento, other.documento) && Objects.equals(idCtoInt, other.idCtoInt)
				&& Objects.equals(idEscopo, other.idEscopo) && Objects.equals(idMedidaItem, other.idMedidaItem)
				&& Objects.equals(nomeItem, other.nomeItem) && Objects.equals(qtdItem, other.qtdItem)
				&& Objects.equals(valorItem, other.valorItem);
	}

	@Override
	public String toString() {
		return "ItemEscopo [idCtoInt=" + idCtoInt + ", nomeItem=" + nomeItem + ", valorItem=" + valorItem
				+ ", idMedidaItem=" + idMedidaItem + ", qtdItem=" + qtdItem + ", idEscopo=" + idEscopo + ", documento="
				+ documento + "]";
	}

	

}
