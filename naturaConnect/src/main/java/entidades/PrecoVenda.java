package entidades;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="preco_venda")
public class PrecoVenda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_preco")
	private int id;
	private Double preco;
	@Column(name="data_preco")
	private Timestamp data;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public Timestamp getData() {
		return data;
	}
	public void setData(Timestamp data) {
		this.data = data;
	}
	
	
}
