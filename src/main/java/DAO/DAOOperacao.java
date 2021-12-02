package DAO;
import java.sql.*;
import model.operacao;

public class DAOOperacao {
    private Connection conexao;

    public DAOOperacao() {
		conexao = null;
	}

    public boolean conectar() {
        String driverName = "org.postgresql.Driver";
        String serverName = "localhost";
        String mydatabase = "Spare";
        int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
        String username = "ti2cc";
        String password = "ti@cc";
        boolean status = false;

        try {
            Class.forName(driverName);
            conexao = DriverManager.getConnection(url, username, password);
            status = (conexao == null);
            System.out.println("Conex�o efetuada com o postgres!");
        } catch (ClassNotFoundException e) {
            System.err.println("Conex�o n�o efetuada com o postgres -- Driver n�o encontrado -- " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Conex�o n�o efetuada com o postgres -- " + e.getMessage());
        }

        return status;
    }

    public boolean close() {
        boolean status = false;

        try {
            conexao.close();
            status = true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }
    public int getMaxId() {
        int id = 0;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
            ResultSet rs = st.executeQuery("select max(id) as max_id from operacao");
            rs.next();
            id = rs.getInt("max_id");
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
	public boolean inserirOperacao(operacao operacao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO Operacao (data,valor,tipo, descricao, id, usuarioemail) " + "VALUES ('"+ operacao.getData()+ "', '" + operacao.getValor() + "', '"  + operacao.getTipo() + "', '" + operacao.getDescricao() + "', '" + operacao.getID() + "', '" + operacao.getEmail() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarOperacao(operacao operacao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE Operacao SET data = '" + operacao.getData() + "', valor = " + operacao.getValor() + " WHERE id = " + operacao.getID();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirOperacao(int id) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM Operacao WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public operacao[] getOperacao() {
		operacao[] Operacao = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM Operacao");		
	         if(rs.next()){
	             rs.last();
	             Operacao = new operacao[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                Operacao[i] = new operacao(rs.getDate("data"), rs.getInt("valor"), rs.getString("tipo"), rs.getString("descricao"), rs.getInt("id"), rs.getString("usuarioemail"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Operacao;
	}
}