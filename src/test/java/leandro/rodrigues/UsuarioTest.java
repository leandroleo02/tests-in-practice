package leandro.rodrigues;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDate;
import java.time.Month;

import leandro.rodrigues.Usuario;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.FromDataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 * Considere a tabela abaixo para validação dos cálculos:
 *
 * 1 2 3 4 5 6 7 8 9
 * -----------------
 * A B C D E F G H I
 * J K L M N O P Q R
 * S T U V W X Y Z
 */
@RunWith(Theories.class)
public class UsuarioTest {

	private static final String NUMEROS_PARA_REDUZIR_BASE_DECIMAL = "numerosParaReduzirBaseDecimal";
	private static final String VALOR_NUMERICO_COM_A_INICIANDO_EM_UM = "valorNumericoComAIniciandoEmUm";
	private static final String VALOR_DO_CARACTERE_NA_TABELA = "valorDoCaractereNaTabela";

	@DataPoints(NUMEROS_PARA_REDUZIR_BASE_DECIMAL)
	@SuppressWarnings("unchecked")
	public static Pair<Integer, Integer>[] numerosParaReduzirBaseDecimal = new Pair[] {
			Pair.of(27, 9), Pair.of(95, 5), Pair.of(88, 7), Pair.of(177, 6) };

	@DataPoints(VALOR_NUMERICO_COM_A_INICIANDO_EM_UM)
	@SuppressWarnings("unchecked")
	public static Pair<Character, Integer>[] valorNumericoComAIniciandoEmUm = new Pair[] {
			Pair.of('a', 1), Pair.of('b', 2), Pair.of('E', 5), Pair.of('H', 8),
			Pair.of('K', 11), Pair.of('o', 15), Pair.of('r', 18),
			Pair.of('s', 19), Pair.of('V', 22), Pair.of('z', 26) };

	@DataPoints(VALOR_DO_CARACTERE_NA_TABELA)
	@SuppressWarnings("unchecked")
	public static Pair<Character, Integer>[] valorDoCaractereNaTabela = new Pair[] {
			Pair.of('A', 1), Pair.of('r', 9), Pair.of('o', 6), Pair.of('Z', 8),
			Pair.of('s', 1), Pair.of('J', 1), Pair.of('f', 6), Pair.of('X', 6) };

	private Usuario usuario;

	@Before
	public void setup() {
		this.usuario = new Usuario("Maria da Silva", LocalDate.of(1980,
				Month.JANUARY, 15));
	}

	@Theory
	public void deveReduzirNUmeroParaBaseDecimal(
			@FromDataPoints(NUMEROS_PARA_REDUZIR_BASE_DECIMAL) Pair<Integer, Integer> item) {
		assertThat(usuario.reducaoParaBaseDecimal(item.getLeft()),
				equalTo(item.getRight()));
	}

	@Theory
	public void deveObterValorNumericoComAIniciandoEmUm(
			@FromDataPoints(VALOR_NUMERICO_COM_A_INICIANDO_EM_UM) Pair<Character, Integer> item) {
		assertThat(usuario.valorNumericoComAIniciandoEmUm(item.getLeft()),
				equalTo(item.getRight()));
	}

	@Theory
	public void deveObterOValorDoCaractereNaTabela(
			@FromDataPoints(VALOR_DO_CARACTERE_NA_TABELA) Pair<Character, Integer> item) {
		assertThat(usuario.valorNumericoDoCaractere(item.getLeft()),
				equalTo(item.getRight()));
	}

	@Test
	public void deveCalcularNumerologiaDoNome() {
		usuario.calcularNumerologiaDoNome();
		assertThat(usuario.getNumeroDoNome(), equalTo(2));
	}

	@Test
	public void deveCalcularNumerologiaDaDataDeNascimento() {
		usuario.calcularNumerologiaDataNascimento();
		assertThat(usuario.getNumeroDataNascimento(), equalTo(7));
	}
}
