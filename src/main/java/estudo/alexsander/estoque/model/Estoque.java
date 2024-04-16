package estudo.alexsander.estoque.model;

import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "Estoque_Produtos")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(nullable = false)
    private int quantidade;
    @Column(nullable = false)
    private BigDecimal preco;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
