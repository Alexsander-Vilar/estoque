package estudo.alexsander.estoque.controller;

import estudo.alexsander.estoque.dto.ProdutoDto;
import estudo.alexsander.estoque.model.Produto;
import estudo.alexsander.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Object> produtoSave(@RequestBody @Valid ProdutoDto produtoDto) {
        // Verifica se o produto já existe pelo ID
        if (produtoService.existeProdutoPorId(produtoDto.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Código de produto já existente.");
        }

        // Cria um novo produto e copia os dados do DTO para a entidade
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDto, produto);

        // Salva o produto no banco de dados
        produto = produtoService.saveProduto(produto);

        // Retorna o produto criado com o código de status 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> produtoFindById(@PathVariable(value = "id") long id) {
        // Busca o produto pelo ID
        Optional<Produto> produtoOptional = produtoService.findById(id);

        // Verifica se o produto foi encontrado
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }

        // Retorna o produto encontrado com o código de status 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
    }

    @GetMapping(path = "/nome/find")
    public ResponseEntity<List<Produto>> findByName(@RequestParam String name) {
        if (!produtoService.existeProdutoPorNome(name)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(produtoService.findByNome(name));
    }

    @GetMapping(path = "/fabricante/find")
    public ResponseEntity<List<Produto>> findByFabricante(@RequestParam String fabricante) {
        if (!produtoService.existeProdutoPorFabricante(fabricante)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(produtoService.findByFabricante(fabricante));
    }

    @GetMapping(path = "/classificacao/find")
    public ResponseEntity<List<Produto>> findByClassificacao(@RequestParam String classificacao) {
        if (!produtoService.existeProdutoPorClassificacao(classificacao)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(produtoService.findByClassificacao(classificacao));
    }


    @GetMapping("/all")
    public ResponseEntity<Page<Produto>> produtoFindAll(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAllProdutos(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> produtoDelet(@PathVariable(value = "id") long id) {
        Optional<Produto> produtoOptional = produtoService.findById(id);
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado.");
        }
        produtoService.produtoDelet(produtoOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> produtoUpdate(@PathVariable(value = "id") long id, @RequestBody @Valid ProdutoDto produtoDto) {
        Optional<Produto> produtoOptional = produtoService.findById(id);

        // Verifica se o produto existe
        if (!produtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
        }

        // Obtém o produto existente
        Produto produto = produtoOptional.get();

        // Atualiza os campos do produto com base nos dados do DTO
        produto.setNome(produtoDto.getNome());
        produto.setFabricante(produtoDto.getFabricante());
        produto.setClassificacao(produtoDto.getClassificacao());
        produto.setDescricao(produtoDto.getDescricao());

        // Salva o produto atualizado no banco de dados
        produto = produtoService.saveProduto(produto);

        // Retorna o produto atualizado com o código de status 200 OK
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    // Métodos para atualizar e deletar produtos podem ser adicionados conforme necessário

}
