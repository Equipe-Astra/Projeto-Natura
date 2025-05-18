package entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Telefone implements Serializable {
	@Column(name = "id_tel")  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    private Usuario usuarioId;
    
    private String telefone;
    
    @ManyToOne
    @JoinColumn(name = "codigo_consultor")
    private Consultor codigoConsultor;
    
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente idCliente;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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
	
}
