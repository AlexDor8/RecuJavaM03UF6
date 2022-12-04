package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import model.Genero;
import model.Pelicula;

public class Dao {
	public static final String SCHEMA_NAME = "filmout";
	public static final String USER_CONNECTION = "root";
	public static final String PASS_CONNECTION = "";
	public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String INSERT_PELICULA = "INSERT INTO PELICULA (codigo, titulo, codigoGenero, segundaParte) values (?, ?, ?, ?)";
	public static final String GET_PELICULA_BY_TITULO = "SELECT * FROM PELICULA WHERE titulo = ?";
	public static final String UPDATE_PELICULA = "UPDATE PELICULA SET codigoGenero = ? WHERE titulo = ?";
	public static final String SELECT_PELICULAS = "SELECT * FROM PELICULA";
	public static final String SELECT_GENEROS = "SELECT * FROM GENERO";
	public static final String DELETE_PELICULA = "DELETE FROM PELICULA WHERE codigo = ?";

	private Connection conexion;

	public void connectar() throws SQLException {
		conexion = DriverManager.getConnection(CONNECTION, USER_CONNECTION, PASS_CONNECTION);
	}

	public void desconectar() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}

	public ArrayList<Pelicula> todasPeliculas() throws SQLException {
		ArrayList<Pelicula> todasPeliculas = new ArrayList<>();

		try (Statement st = conexion.createStatement()) {
			try (ResultSet rs = st.executeQuery(SELECT_PELICULAS)) {
				while (rs.next()) {
					Pelicula pelicula = new Pelicula(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
					todasPeliculas.add(pelicula);
				}
			}
		}
		return todasPeliculas;
	}

	public ArrayList<Genero> todosGeneros() throws SQLException {
		ArrayList<Genero> todosGeneros = new ArrayList<>();

		try (Statement st = conexion.createStatement()) {
			try (ResultSet rs = st.executeQuery(SELECT_GENEROS)) {
				while (rs.next()) {
					Genero genero = new Genero(rs.getInt(1), rs.getString(2));
					todosGeneros.add(genero);
				}
			}
		}
		return todosGeneros;
	}

	public Pelicula peliculaPorTitulo(String titulo) throws SQLException {
		Pelicula pelicula = null;
		try (PreparedStatement ps = conexion.prepareStatement(GET_PELICULA_BY_TITULO)) {
			ps.setString(1, titulo);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					pelicula = new Pelicula();
					pelicula.setCodigo(rs.getInt("codigo"));
					pelicula.setTitulo(rs.getString("titulo"));
					pelicula.setCodigoGenero(rs.getInt("codigoGenero"));
					pelicula.setSegundaParte(rs.getInt("segundaParte"));
				}
			}
		}
		return pelicula;
	}
	
	public void insetarPelicula(Pelicula pelicula) throws SQLException {
		try(PreparedStatement ps = conexion.prepareStatement(INSERT_PELICULA)) {
			ps.setInt(1, pelicula.getCodigo());
			ps.setString(2, pelicula.getTitulo());
			ps.setInt(3, pelicula.getCodigoGenero());
			if (pelicula.getSegundaParte()==null) {
				ps.setNull(4, Types.INTEGER);
			}else {
				ps.setInt(4, pelicula.getSegundaParte());
			}
			
			ps.execute();
		}
	}
	
	public void eliminarPelicula(int codigo) throws SQLException {
		try(PreparedStatement ps = conexion.prepareStatement(DELETE_PELICULA)) {
			ps.setInt(1, codigo);
			ps.execute();
		}
	}
	
	public void actualizarPelicula(int nuevoCodigoGenero, String titulo) throws SQLException {
		try(PreparedStatement ps = conexion.prepareStatement(UPDATE_PELICULA)) {
			ps.setInt(1, nuevoCodigoGenero);
			ps.setString(2, titulo);
			ps.execute();
			}
		}

}
