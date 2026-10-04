package com.baozi.baozistore.model;

import com.baozi.baozistore.model.Pedido;
import com.baozi.baozistore.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {
	 private final PedidoRepository repository;

	    public PedidoController(PedidoRepository repository) {
	        this.repository = repository;
	    }

	    @PostMapping
	    public Pedido criar(@RequestBody Pedido pedido) {
	        return repository.save(pedido);
	    }

	    @GetMapping
	    public List<Pedido> listar() {
	        return repository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
	        return repository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }


@DeleteMapping("/{id}")
public ResponseEntity<Void> apagar(@PathVariable Long id) {
    if (!repository.existsById(id)) return ResponseEntity.notFound().build();
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
    }
}
