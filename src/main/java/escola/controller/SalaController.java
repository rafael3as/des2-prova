package escola.controller;

import escola.model.Sala;
import escola.service.SalaService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService service;

    public SalaController(SalaService service) {
        this.service = service;
    }

    // Cadastrar
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sala cadastrar(@Valid @RequestBody Sala sala) {
        return service.cadastrar(sala);
    }

    // Listar
    @GetMapping
    public List<Sala> listar() {
        return service.listar();
    }

    // Buscar por id
    @GetMapping("/{id}")
    public Sala buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    // Reservar
    @PostMapping("/{id}/reservar")
    public Sala reservar(@PathVariable Long id) {
        return service.reservar(id);
    }

    // Liberar
    @PostMapping("/{id}/liberar")
    public Sala liberar(@PathVariable Long id) {
        return service.liberar(id);
    }

    // Excluir
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}
