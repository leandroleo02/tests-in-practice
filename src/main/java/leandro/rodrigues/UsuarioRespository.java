package leandro.rodrigues;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRespository extends CrudRepository<Usuario, Long> {

	List<Usuario> findByNome(String nome);
}
