package br.com.framework.bean.geral;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

public class ObjetoCampoConsulta implements Serializable, Comparator<ObjetoCampoConsulta> {

	private static final long serialVersionUID = 1L;

	private String descricao;
	private String campoBanco;
	private Object tipoClass;
	private Integer principal;

	@Override
	public int compare(ObjetoCampoConsulta o1, ObjetoCampoConsulta o2) {

		return o1.getPrincipal().compareTo(o2.getPrincipal());
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampoBanco() {
		return campoBanco;
	}

	public void setCampoBanco(String campoBanco) {
		this.campoBanco = campoBanco;
	}

	public Object getTipoClass() {
		return tipoClass;
	}

	public void setTipoClass(Object tipoClass) {
		this.tipoClass = tipoClass;
	}

	public Integer getPrincipal() {
		return principal;
	}

	public void setPrincipal(Integer principal) {
		this.principal = principal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(campoBanco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjetoCampoConsulta other = (ObjetoCampoConsulta) obj;
		return Objects.equals(campoBanco, other.campoBanco);
	}

	@Override
	public String toString() {
		return "ObjetoCampoConsulta [descricao=" + descricao + "]";
	}

}