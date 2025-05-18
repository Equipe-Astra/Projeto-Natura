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
@Table(name="itens_compra")

public class ItensCompra{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_compra")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto idProduto;

	@Column(name="preco_venda")
	private double precoVenda;
	
	@Column(name="quantidade")
	private double quantidade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Produto idProduto) {
		this.idProduto = idProduto;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

}
