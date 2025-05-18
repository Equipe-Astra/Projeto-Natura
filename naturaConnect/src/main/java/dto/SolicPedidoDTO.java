package dto;

public class SolicPedidoDTO {
    private int idCompra; 
    private int idCliente;
    private Double codigoConsult;
    private String nomeCompleto;
    private String endereco;
    private String telefone;
    private String descricaoProds;
    private Double valorTotal;
    private String datraCompra; 
    
	public SolicPedidoDTO(int idCompra, int idCliente, Double codigoConsult, String nomeCompleto, String endereco,
			String telefone, String descricaoProds, Double valorTotal, String datraCompra) {
		super();
		this.idCompra = idCompra;
		this.idCliente = idCliente;
		this.codigoConsult = codigoConsult;
		this.nomeCompleto = nomeCompleto;
		this.endereco = endereco;
		this.telefone = telefone;
		this.descricaoProds = descricaoProds;
		this.valorTotal = valorTotal;
		this.datraCompra = datraCompra;
	}

	public String getDescricaoProds() {
		return descricaoProds;
	}

	public void setDescricaoProds(String descricaoProds) {
		this.descricaoProds = descricaoProds;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
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

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getDatraCompra() {
		return datraCompra;
	}

	public void setDatraCompra(String datraCompra) {
		this.datraCompra = datraCompra;
	}

}
