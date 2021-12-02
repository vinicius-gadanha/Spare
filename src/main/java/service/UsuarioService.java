package service;
import spark.Request;
import model.usuarios;
import spark.Response;
import com.google.gson.Gson;
import DAO.DAOUsuarios;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Meio de campo entre a aplicacao e a DAO

public class UsuarioService {
	private DAOUsuarios usuarioDAO;
	
	public UsuarioService() {
		usuarioDAO = new DAOUsuarios();
		usuarioDAO.conectar();
	}
	/*public Object getAll(Request request, Response response) {
		Gson gson = new Gson();
		Autores[] tmp = produtoDAO.getAutores();
		String json = gson.toJson(tmp);
		System.out.print(request);
		return json;
	}*/
	public Object getAll(Request request, Response response) {
		Gson gson = new Gson();
		usuarios[] tmp = usuarioDAO.getUsuarios();
		String json = gson.toJson(tmp);
		System.out.print(request);
		return json;
	}
	public Object getAllLogin(Request request, Response response) {
		usuarios[] tmp = usuarioDAO.getUsuarios();
		Gson gson = new Gson();
		System.out.print(request);
		
		String nome_usuario = request.queryParams("nomeusuario");
		String senha_usuario = request.queryParams("senha");
				
		for (int i=0; i<tmp.length; i++) {
			if (tmp[i].getNomeUsuario().equals(nome_usuario) && tmp[i].getSenha().equals(senha_usuario)) {
				String json = gson.toJson(tmp[i]);
				response.status(200);
				return json;
			}
			else {
				System.out.print("fracasso!");
			}
		}
		
		response.status(400);
		return null;
	}

	public Object add(Request request, Response response) {
	    String nomeusuario = request.queryParams("nomeusuario");
	    String nome = request.queryParams("nome");
	    String sobrenome = request.queryParams("sobrenome");
	    String email = request.queryParams("email");
	    String senha = request.queryParams("senha");
	    
	    usuarios usuario = new usuarios(nome, sobrenome, nomeusuario, email, senha);
	    //System.out.println(usuario.getUsername() + " " + usuario.getEmail() + " " + usuario.getSenha());
	    usuarioDAO.inserirUsuarios2(usuario);
	    response.status(201); // 201 Created
	    return nomeusuario;
	}
	public Object addUsuarioOperacao(Request request, Response response) {
	    String nomeusuario = request.queryParams("nomeusuario");
	    String nome = request.queryParams("nome");
	    String sobrenome = request.queryParams("sobrenome");
	    String email = request.queryParams("email");
	    String senha = request.queryParams("senha");
	    String id = request.queryParams("id");
	    int id_aux = Integer.parseInt(id);
	    
	    usuarios usuario = new usuarios(nome, sobrenome, nomeusuario, email, senha, id_aux);
	    //System.out.println(usuario.getUsername() + " " + usuario.getEmail() + " " + usuario.getSenha());
	    usuarioDAO.inserirUsuarios3(usuario);
	    response.status(201); // 201 Created
	    return nomeusuario;
	}
	public Object remove(Request request, Response response) {
        String str = request.params(":email");
        usuarios usuario = (usuarios) usuarioDAO.getUsuario(str);

        if (usuario != null) {

        	usuarioDAO.excluirUsuario(usuario.getEmail());

            response.status(200); // success
        	return usuario.getEmail();
        } else {
            response.status(404); // 404 Not found
            return "Produto n�o encontrado.";
        }
	}
}
