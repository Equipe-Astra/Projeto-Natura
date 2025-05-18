package dto;

public class ProdutoDTO {
    private int id;
    private String descricao;
    private double valor;
    private int quantidade;
    private String imagemBase64;

    public ProdutoDTO(int id, String descricao, double valor, int quantidade, String imagemBase64) {
        super();
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
        this.imagemBase64 = imagemBase64;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getImagemBase64() {
        return imagemBase64;
    }

    public void setImagemBase64(String imagemBase64) {
        this.imagemBase64 = imagemBase64;
    }

}