package DAO;
import java.sql.*;
import model.pacoteassinatura;

public class DAOPacoteAssinatura {
    private Connection conexao;

    public DAOPacoteAssinatura() {
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
    public boolean inserirPacoteAssinatura(pacoteassinatura pacoteassinatura) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO pacoteassinatura (id, preço, tipo) " + "VALUES (" + pacoteassinatura.getID() + ", '"
					+ pacoteassinatura.getPreco() + "', '" + pacoteassinatura.getTipo() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

    public boolean atualizarPacoteAssinatura(pacoteassinatura PacoteAssinatura) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE pacoteassinatura SET tipo = '" + PacoteAssinatura.getTipo() + "', preço = "
                    + PacoteAssinatura.getPreco() + " WHERE id = " + PacoteAssinatura.getID();
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirPacoteAssinatura(int id) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            st.executeUpdate("DELETE FROM pacoteassinatura WHERE id = " + id);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public pacoteassinatura[] getPacoteAssinaturas() {
        pacoteassinatura[] PacoteAssinaturas = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT * FROM PacoteAssinaturas");
            if (rs.next()) {
                rs.last();
                PacoteAssinaturas = new pacoteassinatura[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    PacoteAssinaturas[i] = new pacoteassinatura(rs.getInt("id"), rs.getString("tipo"),
                            rs.getDouble("preço"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return PacoteAssinaturas;
    }

}
