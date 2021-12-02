package DAO;
import java.sql.*;
import model.meta;

public class DAOMeta {
	private Connection conexao;

	public DAOMeta() {
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
            ResultSet rs = st.executeQuery("select max(id) as max_id from meta");
            rs.next();
            id = rs.getInt("max_id");
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return id;
    }
	public boolean inserirMeta(meta meta) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO meta (id, titulo, progresso, valor, usuarioemail) " + "VALUES (" + meta.getID() + ", '"
					+ meta.getTitulo() + "', '" + meta.getProgresso() + "', '" + meta.getValor() + "', '" + meta.getEmail() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean atualizarMeta(meta meta) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			String sql = "UPDATE meta SET titulo = '" + meta.getTitulo() + "', progresso = '" + meta.getProgresso()
					+ "', valor = '" + meta.getValor() + "'" + " WHERE id = " + meta.getID();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public boolean excluirMeta(int id) {
		boolean status = false;
		try {
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM meta WHERE id = " + id);
			st.close();
			status = true;
		} catch (SQLException u) {
			throw new RuntimeException(u);
		}
		return status;
	}

	public meta[] getMetas() {
		meta[] Metas = null;

		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM meta");
			if (rs.next()) {
				rs.last();
				Metas = new meta[rs.getRow()];
				rs.beforeFirst();

				for (int i = 0; rs.next(); i++) {
					Metas[i] = new meta(rs.getInt("id"), rs.getString("titulo"), rs.getFloat("progresso"),
							rs.getFloat("valor"));
				}
			}
			st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return Metas;
	}

}
