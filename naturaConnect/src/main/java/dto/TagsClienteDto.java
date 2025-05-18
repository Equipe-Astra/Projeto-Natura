package dto;

public class TagsClienteDto {
	private String descricao;
	private String imagemBase64;

	public TagsClienteDto(String descricao, String imagemBase64) {
		super();
		this.descricao = descricao;
		this.imagemBase64 = imagemBase64;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getImagemBase64() {
		return imagemBase64;
	}

	public void setImagemBase64(String imagemBase64) {
		this.imagemBase64 = imagemBase64;
	}

}
