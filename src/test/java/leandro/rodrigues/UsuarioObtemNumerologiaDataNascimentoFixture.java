package leandro.rodrigues;

public class UsuarioObtemNumerologiaDataNascimentoFixture extends FixtureBase {

	public void cadastrarNumerologiaDaDataDeNascimentoDoUsuario() {
		usuario.calcularNumerologiaDataNascimento();
		repository.save(usuario);
	}

	public int obterNumerologiaDataNascimentoDoBanco() {
		this.usuario = obterUsuario(usuario.getNome(),
				usuario.getDataNascimento());
		return usuario.getNumeroDataNascimento();
	}
}
