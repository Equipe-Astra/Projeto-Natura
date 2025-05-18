package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private int id;

    @Column(name = "nome_produto")
    private String nomeProduto;

    private String descricao;
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "id_unid_med")
    private UnidadeDeMedida unidadeId;

    @ManyToOne
    @JoinColumn(name = "id_preco")
    private PrecoVenda precoId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeDeMedida getUnidadeId() {
        return unidadeId;
    }

    public void setUnidadeId(UnidadeDeMedida unidadeId) {
        this.unidadeId = unidadeId;
    }

    public PrecoVenda getPrecoId() {
        return precoId;
    }

    public void setPrecoId(PrecoVenda precoId) {
        this.precoId = precoId;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

}
