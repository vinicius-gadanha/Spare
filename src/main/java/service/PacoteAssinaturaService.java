package service;
import spark.Request;
import model.pacoteassinatura;
import model.usuarios;
import spark.Response;
import com.google.gson.Gson;
import DAO.DAOPacoteAssinatura;

public class PacoteAssinaturaService {
private DAOPacoteAssinatura assinaturaDAO;
	
	public PacoteAssinaturaService() {
		assinaturaDAO = new DAOPacoteAssinatura();
		assinaturaDAO.conectar();
	}
	public Object add(Request request, Response response) {
	    String preco = request.queryParams("preço");
	    double preco_db = Double.parseDouble(preco);
	    String tipo = request.queryParams("tipo");
	    int id = assinaturaDAO.getMaxId();
	    id++;
	    
	    pacoteassinatura pacote = new pacoteassinatura(id, tipo, preco_db);
	    //System.out.println(usuario.getUsername() + " " + usuario.getEmail() + " " + usuario.getSenha());
	    assinaturaDAO.inserirPacoteAssinatura(pacote);
	    response.status(201); // 201 Created
	    return id;
	}
}
