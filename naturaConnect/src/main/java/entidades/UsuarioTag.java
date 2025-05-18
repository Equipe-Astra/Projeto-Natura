package entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Embeddable
@Table(name = "usuario_tag")

public class UsuarioTag implements Serializable {
	@Id
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuarioId;

	@Id
	@ManyToOne
	@JoinColumn(name = "id_tag")
	private Tags idTag;

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Tags getIdTag() {
		return idTag;
	}

	public void setIdTag(Tags idTag) {
		this.idTag = idTag;
	}
	
	

}
