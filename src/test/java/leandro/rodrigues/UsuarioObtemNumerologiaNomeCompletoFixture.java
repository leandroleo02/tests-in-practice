package leandro.rodrigues;

public class UsuarioObtemNumerologiaNomeCompletoFixture extends FixtureBase {

	public void cadastrarNumerologiaDoNomeDoUsuario() {
		usuario.calcularNumerologiaDoNome();
		repository.save(usuario);
	}

	public int obterNumerologiaNomeCompleto() {
		this.usuario = obterUsuario(usuario.getNome(),
				usuario.getDataNascimento());
		return usuario.getNumeroDoNome();
	}
}
