package leandro.rodrigues;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import leandro.rodrigues.Application;
import leandro.rodrigues.Usuario;
import leandro.rodrigues.UsuarioRespository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

@RunWith(ConcordionSpringJunit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public abstract class FixtureBase {

	@Autowired
	protected UsuarioRespository repository;
	protected Usuario usuario;

	public void criarUsuario(String nome, String dataNascimento) {
		this.usuario = new Usuario(nome, converter(dataNascimento));
	}

	protected Usuario obterUsuario(String nome, LocalDate dataNascimento) {
		Usuario usuario = repository.findByNome(nome).get(0);

		if (usuario.getDataNascimento().equals(dataNascimento)) {
			return usuario;
		} else {
			throw new IllegalStateException();
		}
	}

	private LocalDate converter(String dataNascimento) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(dataNascimento, formatter);
	}

	public void excluirUsuario() {
		repository.delete(usuario);
	}
}
