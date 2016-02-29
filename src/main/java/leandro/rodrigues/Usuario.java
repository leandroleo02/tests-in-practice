package leandro.rodrigues;

import java.time.LocalDate;
import java.util.function.IntUnaryOperator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private LocalDate dataNascimento;
	private int numeroDoNome;
	private int numeroDataNascimento;

	public Usuario() {
	}

	public Usuario(String nome, LocalDate dataNascimento) {
		this();
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public void calcularNumerologiaDoNome() {
		int somaDosCaracteres = nome.chars()
				.map(c -> valorNumericoDoCaractere((char) c)).sum();
		this.numeroDoNome = reducaoParaBaseDecimal(somaDosCaracteres);
	}

	public void calcularNumerologiaDataNascimento() {
		this.numeroDataNascimento = reducaoParaBaseDecimal(dataNascimento
				.getDayOfMonth()
				+ dataNascimento.getMonthValue()
				+ dataNascimento.getYear());
	}

	int valorNumericoDoCaractere(char caractere) {
		return caractere == ' ' ? 0
				: reducaoParaBaseDecimal(valorNumericoComAIniciandoEmUm(caractere));
	}

	int valorNumericoComAIniciandoEmUm(char caractere) {
		return Character.getNumericValue(caractere) - 9;
	}

	int reducaoParaBaseDecimal(int valor) {
		IntUnaryOperator reducao = v -> v <= 9 ? v : this
				.reducaoParaBaseDecimal(v / 10 + v % 10);
		return reducao.applyAsInt(valor);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getNumeroDoNome() {
		return numeroDoNome;
	}

	public int getNumeroDataNascimento() {
		return numeroDataNascimento;
	}
}
