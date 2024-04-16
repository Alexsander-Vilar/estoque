package estudo.alexsander.estoque.service;

import estudo.alexsander.estoque.model.Produto;
import estudo.alexsander.estoque.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Optional<Produto> findById(long id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> findByNome(String nome) {
        return produtoRepository.findByNome(nome);
    }

    public List<Produto> findByFabricante(String fabricante) {
        return produtoRepository.findByFabricante(fabricante);
    }

    public List<Produto> findByClassificacao(String classificacao) {
        return produtoRepository.findByClassificacao(classificacao);
    }

    public Page<Produto> findAllProdutos(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    public void produtoDelet(Produto produto) {
        produtoRepository.delete(produto);
    }

    @Transactional
    public Produto saveProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public boolean existeProdutoPorId(long id) {
        return produtoRepository.existsById(id);
    }

    public boolean existeProdutoPorNome(String nome) {
        return produtoRepository.existsProdutoByNome(nome);
    }

    public boolean existeProdutoPorFabricante(String fabricante) {
        return produtoRepository.existsProdutoByFabricante(fabricante);
    }
    public boolean existeProdutoPorClassificacao(String classificacao) {
        return produtoRepository.existsProdutoByClassificacao(classificacao);
    }
}
