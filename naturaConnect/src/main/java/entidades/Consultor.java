package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
@Entity
public class Consultor {
	@Id
	@Column(name = "codigo_consultor")
	private Double codigo;
	private String status;

	public Double getCodigo() {
		return codigo;
	}

	public void setCodigo(Double codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
