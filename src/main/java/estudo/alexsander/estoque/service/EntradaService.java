package estudo.alexsander.estoque.service;

import estudo.alexsander.estoque.dto.EntradaDto;
import estudo.alexsander.estoque.model.Entrada;
import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.model.Produto;
import estudo.alexsander.estoque.repository.EntradaRepsitory;
import estudo.alexsander.estoque.repository.EstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntradaService {
    @Autowired
    private EntradaRepsitory entradaRepository;
    @Autowired
    private EstoqueService estoqueService;
    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public Entrada adicionarEntrada(EntradaDto entradaDto) {
        // Verificar se o produto existe com base no ID fornecido
        Long produtoId = entradaDto.getProdutoId();
        Produto produto = produtoService.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto com o ID " + produtoId + " não encontrado."));

        // Criar a entrada utilizando o produto encontrado
        Entrada entrada = new Entrada();
        entrada.setProduto(produto);
        entrada.setQuantidade(entradaDto.getQuantidade());
        entrada.setPreco(entradaDto.getPreco());
        entrada.setDataHora(LocalDateTime.now());

        // Salvando a entrada
        Entrada entradaSalva = entradaRepository.save(entrada);

        // Atualizando o estoque
        estoqueService.atualizarEstoque(entrada);

        return entradaSalva;
    }

    // Métodos de consulta para entradas de produto
    public List<Entrada> buscarTodasEntradas() {
        return entradaRepository.findAll();
    }

    public List<Entrada> buscarEntradasPorFabricante(String fabricante) {
        return entradaRepository.findByProduto_Fabricante(fabricante);
    }

    public List<Entrada> buscarEntradasPorNomeProduto(String nome) {
        return entradaRepository.findByProduto_Nome(nome);
    }

    public List<Entrada> buscarEntradasPorClassificacaoProduto(String classificacao) {
        return entradaRepository.findByProduto_Classificacao(classificacao);
    }
}