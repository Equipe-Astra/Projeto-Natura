package entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="historico_pontos")

public class HistoricoPontos{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_pontos;
	
	@Column(name="pontos")
	private double pontos;
	
	@Column(name="tipo")
	private String tipo;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente idCliente;

	public int getId_pontos() {
		return id_pontos;
	}

	public void setId_pontos(int id_pontos) {
		this.id_pontos = id_pontos;
	}

	public double getPontos() {
		return pontos;
	}

	public void setPontos(double pontos) {
		this.pontos = pontos;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	
	
}
