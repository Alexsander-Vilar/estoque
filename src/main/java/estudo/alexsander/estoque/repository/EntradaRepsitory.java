package estudo.alexsander.estoque.repository;

import estudo.alexsander.estoque.model.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepsitory extends JpaRepository<Entrada, Long> {
    List<Entrada> findByProduto_Fabricante(String fabricante);

    List<Entrada> findByProduto_Nome(String nome);

    List<Entrada> findByProduto_Classificacao(String classificacao);
}
