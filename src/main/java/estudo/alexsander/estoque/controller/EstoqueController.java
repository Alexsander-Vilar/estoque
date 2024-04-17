package estudo.alexsander.estoque.controller;

import estudo.alexsander.estoque.model.Entrada;
import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.service.EntradaService;
import estudo.alexsander.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueService estoqueService;
    @Autowired
    EntradaService entradaService;

    @GetMapping("/all")
    public ResponseEntity<Page<Estoque>> estoqueFindAll(Pageable pageable) {
        Page<Estoque> estoquePage = estoqueService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estoquePage);
    }
    @GetMapping("/porFabricante/{fabricante}")
    public ResponseEntity<List<Estoque>> buscarEstoquePorFabricante(@PathVariable String fabricante) {
        List<Estoque> estoques = estoqueService.findByFabricante(fabricante);
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/porNomeProduto/{nome}")
    public ResponseEntity<List<Estoque>> buscarEstoquePorNomeProduto(@PathVariable String nome) {
        List<Estoque> estoques = estoqueService.findByNome(nome);
        return ResponseEntity.ok(estoques);
    }

    @GetMapping("/porClassificacaoProduto/{classificacao}")
    public ResponseEntity<List<Estoque>> buscarEstoquePorClassificacaoProduto(@PathVariable String classificacao) {
        List<Estoque> estoques = estoqueService.findByClassificacao(classificacao);
        return ResponseEntity.ok(estoques);
    }

}
