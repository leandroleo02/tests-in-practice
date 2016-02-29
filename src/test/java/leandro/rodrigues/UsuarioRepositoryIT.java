package leandro.rodrigues;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import leandro.rodrigues.Application;
import leandro.rodrigues.Usuario;
import leandro.rodrigues.UsuarioRespository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class UsuarioRepositoryIT {

	@Autowired
	private UsuarioRespository	repository;

	@Test
	public void deveCadastrarUsuario() {
		final String nome = "Maria da Silva";
		Usuario usuario = new Usuario(nome, LocalDate.of(1980, Month.JANUARY, 15));
		repository.save(usuario);
		List<Usuario> usuariosDoBanco = repository.findByNome(nome);
		assertThat(usuariosDoBanco.size(), equalTo(1));
	}

	@Test
	public void deveConsultarUsuarioPorNome() {
		final String nome = "Maria da Silva";
		Usuario usuario = new Usuario(nome, LocalDate.of(1980, Month.JANUARY, 15));
		repository.save(usuario);
		assertThat(repository.findByNome(nome).size(), equalTo(1));
	}

	@Test
	public void deveConsultarTodosUsuarios() {
		Usuario maria = new Usuario("Maria da Silva", LocalDate.of(1980, Month.JANUARY, 15));
		repository.save(maria);

		Usuario joao = new Usuario("Joao da Silva", LocalDate.of(1980, Month.MARCH, 12));
		repository.save(joao);

		repository.findAll().forEach(c -> assertThat(c, notNullValue()));
	}

	@Test
	public void deveModificarUsuario() {
		final String nome = "Maria da Silva";
		Usuario maria = new Usuario(nome, LocalDate.of(1980, Month.JANUARY, 15));
		repository.save(maria);
		assertThat(maria.getNumeroDoNome(), equalTo(0));

		maria.calcularNumerologiaDoNome();
		repository.save(maria);

		repository.findByNome(nome).get(0);
		assertThat(maria.getNumeroDoNome(), not(equalTo(0)));
	}

	@Test
	public void deveExcluirUsuario() {
		final String nome = "Maria da Silva";
		Usuario maria = new Usuario(nome, LocalDate.of(1980, Month.JANUARY, 15));
		repository.save(maria);
		repository.delete(maria);
		assertThat(repository.findByNome(nome).size(), equalTo(0));
	}
}
