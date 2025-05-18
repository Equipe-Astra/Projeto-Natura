package dto;

public class ConsultorasProximasDTO {
    private double id;
    private String nomeCompleto;
    private String telefone;
    private String tags;
    public ConsultorasProximasDTO(double id, String nomeCompleto, String telefone, String tags) {
        super();
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.tags = tags;
    }
    public double getId() {
        return id;
    }
    public void setIdContato(double id) {
        this.id = id;
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