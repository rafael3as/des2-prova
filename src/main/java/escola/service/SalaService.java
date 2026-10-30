package escola.service;

import escola.model.Sala;
import escola.repository.SalaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SalaService {

    private final SalaRepository repository;

    public SalaService(SalaRepository repository) {
        this.repository = repository;
    }

    public Sala cadastrar(Sala sala) {
        if (repository.existsByCodigo(sala.getCodigo())) {
            throw new RegraNegocioException("Código já cadastrado");
        }

        sala.setReservada(false);
        return repository.save(sala);
    }

    public List<Sala> listar() {
        return repository.findAll();
    }

    public Sala buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                    new NaoEncontradoException("Sala não encontrada"));
    }

    public Sala reservar(Long id) {
        Sala sala = buscar(id);

        if (sala.isReservada()) {
            throw new RegraNegocioException("Sala já está reservada");
        }

        sala.setReservada(true);
        return repository.save(sala);
    }

    public Sala liberar(Long id) {
        Sala sala = buscar(id);

        if (!sala.isReservada()) {
            throw new RegraNegocioException("Sala não está reservada");
        }

        sala.setReservada(false);
        return repository.save(sala);
    }

    public void excluir(Long id) {
        Sala sala = buscar(id);

        if (sala.isReservada()) {
            throw new RegraNegocioException(
                "Não pode excluir uma sala reservada");
        }

        repository.delete(sala);
    }

    // Exceções internas

    public static class RegraNegocioException extends RuntimeException {
        public RegraNegocioException(String mensagem) {
            super(mensagem);
        }
    }

    public static class NaoEncontradoException extends RuntimeException {
        public NaoEncontradoException(String mensagem) {
            super(mensagem);
        }
    }
}
