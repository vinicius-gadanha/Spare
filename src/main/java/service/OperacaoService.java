package service;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;
import model.operacao;
import java.text.SimpleDateFormat;
import java.util.Date;

//import java.time.format.DateTimeFormatter;
//import com.google.gson.Gson;
//import DAO.Autores;
import DAO.DAOOperacao;

//Meio de campo entre a aplicacao e a DAO

public class OperacaoService {
	private DAOOperacao operacaoDAO;
	
	public OperacaoService() {
		operacaoDAO = new DAOOperacao();
		operacaoDAO.conectar();
	}
	public Object add(Request request, Response response) throws Exception{
	    Date data = new SimpleDateFormat("yyyy-MM-dd").parse(request.queryParams("data"));
        
	    String valor = request.queryParams("valor");
	    int valor_int = Integer.parseInt(valor);
	    String tipo = request.queryParams("tipo");
	    String descricao = request.queryParams("descricao");
	    int id_int = operacaoDAO.getMaxId();
	    id_int++;
	    String email = request.queryParams("emailusuario");
	    
	    operacao operacao = new operacao(data, valor_int, tipo, descricao, id_int, email);
	    //System.out.println(usuario.getUsername() + " " + usuario.getEmail() + " " + usuario.getSenha());
	    operacaoDAO.inserirOperacao(operacao);
	    response.status(201); // 201 Created
		Gson gson = new Gson();
	    String json = gson.toJson(operacao);
	    return json;
	}
	public Object delete(Request request, Response response) {
	String id = request.queryParams("id");
	int id_int = Integer.parseInt(id);
	
	operacaoDAO.excluirOperacao(id_int);
	response.status(201);
	return id;
	}
}
