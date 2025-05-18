package dto;


public class ClientesProximosDTO {
	private int idContato;
	private String nomeCompleto;
	private String telefone;
	private String tags;
	public ClientesProximosDTO( String nomeCompleto, String telefone, String tags) {
		super();

		this.nomeCompleto = nomeCompleto;
		this.telefone = telefone;
		this.tags = tags;
	}
	public int getIdContato() {
		return idContato;
	}
	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}

	

}
