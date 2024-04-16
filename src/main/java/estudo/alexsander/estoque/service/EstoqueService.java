package estudo.alexsander.estoque.service;

import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Optional<Estoque> findById(long id) {
        return estoqueRepository.findById(id);
    }

    public List<Estoque> findByNome(String nome) {
        return estoqueRepository.findByProduto_Nome(nome);
    }

    public List<Estoque> findByFabricante(String fabricante) {
        return estoqueRepository.findByProduto_Fabricante(fabricante);
    }

    public List<Estoque> findByClassificacao(String classificacao) {
        return estoqueRepository.findByProduto_Classificacao(classificacao);
    }

    public Page<Estoque> findAll(Pageable pageable) {
        return estoqueRepository.findAll(pageable);
    }

}
