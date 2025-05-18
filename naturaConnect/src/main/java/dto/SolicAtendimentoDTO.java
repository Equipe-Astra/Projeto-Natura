package dto;

public class SolicAtendimentoDTO {
    private int idContato; 
    private int idCliente;
    private Double codigoConsult;
    private String nomeCompleto;
    private String endereco;
    private String telefone;
    private String tags; // Adicione este campo

    public SolicAtendimentoDTO(int idContato, int idCliente, Double codigoConsult, String nomeCompleto, String endereco, String telefone, String tags) {
        this.idContato = idContato;
        this.idCliente = idCliente;
        this.codigoConsult = codigoConsult;
        this.nomeCompleto = nomeCompleto;
        this.endereco = endereco;
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Double getCodigoConsult() {
		return codigoConsult;
	}

	public void setCodigoConsult(Double codigoConsult) {
		this.codigoConsult = codigoConsult;
	}
}
