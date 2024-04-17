package estudo.alexsander.estoque.controller;

import estudo.alexsander.estoque.dto.EntradaDto;
import estudo.alexsander.estoque.model.Entrada;
import estudo.alexsander.estoque.service.EntradaService;
import estudo.alexsander.estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrada")
public class EntradaController {
    @Autowired
    private EntradaService entradaService;
    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/adicionar")
    public ResponseEntity<Entrada> adicionarEntrada(@Valid @RequestBody EntradaDto entradaDto) {
        Entrada novaEntrada = entradaService.adicionarEntrada(entradaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaEntrada);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Entrada>> buscarTodasEntradas() {
        List<Entrada> entradas = entradaService.buscarTodasEntradas();
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/porFabricante/{fabricante}")
    public ResponseEntity<List<Entrada>> buscarEntradasPorFabricante(@PathVariable String fabricante) {
        List<Entrada> entradas = entradaService.buscarEntradasPorFabricante(fabricante);
        return ResponseEntity.ok(entradas);
    }

    @GetMapping("/porNomeProduto/{nome}")
    public ResponseEntity<List<Entrada>> buscarEntradasPorNomeProduto(@PathVariable String nome) {
        List<Entrada> entradas = entradaService.buscarEntradasPorNomeProduto(nome);
        return ResponseEntity.ok(entradas);
    }

        @GetMapping("/porClassificacaoProduto/{classificacao}")
    public ResponseEntity<List<Entrada>> buscarEntradasPorClassificacaoProduto(@PathVariable String classificacao) {
        List<Entrada> entradas = entradaService.buscarEntradasPorClassificacaoProduto(classificacao);
        return ResponseEntity.ok(entradas);
    }
}

