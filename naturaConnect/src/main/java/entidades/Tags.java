package entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Tags {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_tag;
	private String descricao;
	private byte[] imagem;

	public int getId_tags() {
		return id_tag;
	}

	public void setId_tags(int id_tag) {
		this.id_tag = id_tag;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId_tag() {
		return id_tag;
	}

	public void setId_tag(int id_tag) {
		this.id_tag = id_tag;
	}

	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
}
