package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordem_de_compra")

public class OrdemDeCompra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compra")
	private int id;
	
	@Column(name="data_compra")
	private String dataCompra;
	
	@Column(name="valor_total")
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "codigo_consultor")
	private Consultor codigoConsultor;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente idCliente;
	
	@Column(name="status_pedido")
	private double statusPedido;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Consultor getCodigoConsultor() {
		return codigoConsultor;
	}

	public void setCodigoConsultor(Consultor codigoConsultor) {
		this.codigoConsultor = codigoConsultor;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}
	

	public double getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(double statusPedido) {
		this.statusPedido = statusPedido;
	}
	
}
