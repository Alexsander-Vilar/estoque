package estudo.alexsander.estoque.repository;

import estudo.alexsander.estoque.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsProdutoByClassificacao(String classificacao);
    boolean existsProdutoByFabricante(String fabricante);
    boolean existsProdutoByNome(String nome);

    List<Produto> findByClassificacao(String classificacao);

    List<Produto> findByNome(String nome);

    List<Produto> findByFabricante(String fabricante);

}
