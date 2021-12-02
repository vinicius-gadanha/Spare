package app;
import static spark.Spark.*;
import service.MetaService;
import service.OperacaoService;
import service.UsuarioService;
public class Aplicacao {
	
	private static UsuarioService usuarioservice = new UsuarioService();
	private static OperacaoService operacaoservice = new OperacaoService();
	private static MetaService metaservice = new MetaService();
	
	public static void main (String[] args) {
		port (6789);
		staticFiles.location("/src");
		
		post ("/usuario", (request, response) -> usuarioservice.add(request, response));
		post ("/usuario/login", (request, response) -> usuarioservice.getAllLogin(request, response));
		//get ("/usuario/update/:id", (request, response) -> usuarioservice.update(request, response));
		get ("/usuario/delete/:email", (request, response) -> usuarioservice.remove(request, response));
		get ("/usuario", (request, response) -> usuarioservice.getAll(request, response));
		post ("/operacao/usuario", (request, response) -> usuarioservice.addUsuarioOperacao(request, response));
		post ("/operacao-saida/usuario", (request, response) -> usuarioservice.addUsuarioOperacao(request, response));
		
		post ("/operacao", (request, response) -> operacaoservice.add(request, response));
		post ("/operacao-saida", (request, response) -> operacaoservice.add(request, response));
		post ("/operacao-apagar", (request, response) -> operacaoservice.delete(request, response));
		
		post ("/pacote", (request, response) -> operacaoservice.add(request, response));
		
		post ("/meta", (request, response) -> metaservice.add(request, response));
		post ("/meta/delete", (request, response) -> metaservice.delete(request, response));
		post ("/meta/alter", (request, response) -> metaservice.alter(request, response));
	}
}
