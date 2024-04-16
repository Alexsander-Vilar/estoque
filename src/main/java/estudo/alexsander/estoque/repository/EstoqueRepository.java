package estudo.alexsander.estoque.repository;

import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    List<Estoque> findByProduto_Fabricante(String fabricante);

    List<Estoque> findByProduto_Nome(String nome);

    List<Estoque> findByProduto_Classificacao(String classificacao);

}

