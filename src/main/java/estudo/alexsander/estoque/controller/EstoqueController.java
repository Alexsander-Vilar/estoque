package estudo.alexsander.estoque.controller;

import estudo.alexsander.estoque.model.Estoque;
import estudo.alexsander.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    @Autowired
    EstoqueService estoqueService;

    @GetMapping("/all")
    public ResponseEntity<Page<Estoque>> estoqueFindAll(Pageable pageable) {
        Page<Estoque> estoquePage = estoqueService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(estoquePage);
    }

}
