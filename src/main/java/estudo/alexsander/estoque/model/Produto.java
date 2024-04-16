package estudo.alexsander.estoque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Produtos")
public class Produto {
    @Id
   @Column(nullable = false)
    //Numeração do codigo de barras do produto
    private long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String fabricante;
    @Column(nullable = false)
    private String classificacao;
    @Column(nullable = false)
    private String descricao;

   public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
