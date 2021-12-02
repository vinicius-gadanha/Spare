package DAO;
import java.sql.*;
import model.usuarios;

public class DAOUsuarios {
    private Connection conexao;

    public DAOUsuarios() {
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

	public boolean inserirUsuarios(usuarios usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO Usuario (nome, sobrenome, email, senha, saldo, pacoteassinaturaid, metaid, operacaoid) "
					       + "VALUES ('"+usuario.getNome()+ "', '" + usuario.getSobrenome() + "', '"  
					       + usuario.getEmail() + "', '" + usuario.getSenha() + "', '" + usuario.getSaldo() + "', '" + usuario.getPacoteassinaturaid() + "', '" + usuario.getMetaid() + "', '" + usuario.getOperacaoid() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean inserirUsuarios2(usuarios usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO Usuario (nome, sobrenome, nomeusuario, email, senha) "
					       + "VALUES ('"+usuario.getNome()+ "', '" + usuario.getSobrenome() + "', '"  
					       + usuario.getNomeUsuario() + "', '" + usuario.getEmail() + "', '" + usuario.getSenha() +"');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	public boolean inserirUsuarios3(usuarios usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO Usuario (nome, sobrenome, nomeusuario, email, senha, operacaoid) "
					       + "VALUES ('"+usuario.getNome()+ "', '" + usuario.getSobrenome() + "', '"  
					       + usuario.getNomeUsuario() + "', '" + usuario.getEmail() + "', '" + usuario.getSenha() +"', '" + usuario.getOperacaoid() +"');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarUsuario(usuarios usuario) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE usuario SET nome = '" + usuario.getNome() + "', sobrenome = '"  
				       + usuario.getSobrenome() + "', nomeusuario = '" + usuario.getNomeUsuario() + "'"
					   + " WHERE email = " + usuario.getEmail() + "'" + " WHERE senha = " + usuario.getSenha();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirUsuario(String email) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM usuario WHERE email = " + email);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
    public usuarios[] getUsuarios() {
        usuarios[] usuarios = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT DISTINCT * FROM Usuario");
            if (rs.next()) {
                rs.last();
                usuarios = new usuarios[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    usuarios[i] = new usuarios(rs.getString("nome"), rs.getString("sobrenome"),
                            rs.getString("nomeusuario"), rs.getString("email"), rs.getString("senha"),
                            rs.getDouble("saldo"), rs.getInt("pacoteassinaturaid"), rs.getInt("metaid"),
                            rs.getInt("operacaoid"));
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuarios;
    }

    public usuarios getUsuario(String email) {
        usuarios[] usuario = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery("SELECT FROM Usuario WHERE email = " + email);
            if (rs.next()) {
                rs.last();
                usuario = new usuarios[rs.getRow()];
                rs.beforeFirst();

                for (int i = 0; rs.next(); i++) {
                    if (rs.getString("email").compareTo(email) == 0) {
                        usuario[i] = new usuarios(rs.getString("nome"), rs.getString("sobrenome"),
                                rs.getString("nomeusuario"), rs.getString("email"), rs.getString("senha"),
                                rs.getDouble("saldo"), rs.getInt("pacoteassinaturaid"), rs.getInt("metaid"),
                                rs.getInt("operacaoid"));
                    }
                }
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        usuarios usuario1 = usuario[0];
        return usuario1;
    }
}
