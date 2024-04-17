package estudo.alexsander.estoque.service;

import estudo.alexsander.estoque.model.Entrada;
import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.model.Produto;
import estudo.alexsander.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public Optional<Estoque> findByProduto(Produto produto) {
        return estoqueRepository.findByProduto(produto);
    }

    public Page<Estoque> findAll(Pageable pageable) {
        return estoqueRepository.findAll(pageable);
    }

    public void adicionarEstoque(Estoque estoque) {
        estoqueRepository.save(estoque);
    }

    // Método para atualizar o estoque com base em uma entrada fornecida
    public void atualizarEstoque(Entrada entrada) {
        Produto produto = entrada.getProduto();
        int quantidade = entrada.getQuantidade();
        BigDecimal preco = entrada.getPreco();

        Optional<Estoque> optionalEstoque = estoqueRepository.findByProduto(produto);

        if (optionalEstoque.isPresent()) {
            Estoque estoque = optionalEstoque.get();
            estoque.setQuantidade(estoque.getQuantidade() + quantidade);
            BigDecimal novoPreco = preco.multiply(BigDecimal.valueOf(1.30)); // Aumento de 30%
            estoque.setPreco(novoPreco);
            estoqueRepository.save(estoque); // Atualiza o estoque no banco de dados
        } else {
            // Se o produto ainda não estiver no estoque, adicione uma nova entrada
            Estoque novoEstoque = new Estoque();
            novoEstoque.setProduto(produto);
            novoEstoque.setQuantidade(quantidade);
            BigDecimal novoPreco = preco.multiply(BigDecimal.valueOf(1.30)); // Aumento de 30%
            novoEstoque.setPreco(novoPreco);
            estoqueRepository.save(novoEstoque); // Salva o novo estoque no banco de dados
        }
    }

}



