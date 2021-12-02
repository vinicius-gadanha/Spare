package service;
import spark.Request;
import model.meta;
import spark.Response;
import com.google.gson.Gson;
import DAO.DAOMeta;

public class MetaService {
	private DAOMeta metaDAO;
	
	public MetaService() {
	metaDAO = new DAOMeta();
	metaDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
	String titulo = request.queryParams("titulo");
	String valor = request.queryParams("valor");
	double valor_db = Double.parseDouble(valor);
	int id_int = metaDAO.getMaxId();
    id_int++;
    String email = request.queryParams("emailusuario");
	
	meta metas = new meta(id_int, titulo, valor_db, email);
	metaDAO.inserirMeta(metas);
	Gson gson = new Gson();
    String json = gson.toJson(metas);
    response.status(201);
    return json;
	}
	
	public Object alter(Request request, Response response) {
	String id = request.queryParams("id");
	int id_int = Integer.parseInt(id);
	String valor = request.queryParams("valor");
	double valor_db = Double.parseDouble(valor);
	
	meta metas = new meta(id_int, valor_db);
	metaDAO.atualizarMeta(metas);
	response.status(201);
	return valor;
	}
	
	public Object delete(Request request, Response response) {
	String id = request.queryParams("id");
	int id_int = Integer.parseInt(id);
	
	metaDAO.excluirMeta(id_int);
	response.status(201);
	return id;
	}
}