package dto;

import java.time.LocalDate;

public class ClientesDTO {
	
	private String nomeCompleto;
    private String logradouro;
    private String numeroCasa;
    private String dataCadastro;
    private String telefone;
    private String status;
    private String dataCompra;
    
    public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroCasa() {
		return numeroCasa;
	}

	public void setNumeroCasa(String numeroCasa) {
		this.numeroCasa = numeroCasa;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public String getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}

	public ClientesDTO(String nomeCompleto, String logradouro, String numeroCasa, String dataCadastro, String telefone, String status, String dataCompra) {
        this.nomeCompleto = nomeCompleto;
        this.logradouro = logradouro;
        this.numeroCasa = numeroCasa;
        this.dataCadastro = dataCadastro;
        this.telefone = telefone;
        this.status = status;
        this.dataCompra = dataCompra;
    }
}

