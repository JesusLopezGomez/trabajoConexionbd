package dbconnect.main.java.api;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import dbconnect.main.java.model.Cliente;

public class Connector {

	private static String USER;
	private final static String DB_USER_KEY = "db.user";

	private static String PASS;
	private final static String DB_PASS_KEY = "db.pass";

	private static String JDBC_URL;
	private final static String DB_URL_KEY = "db.url";

	private final static String PROPERTIES_URI = "./resources/db.properties";

	public Connector() {
		super();
		loadProperties();
	}

	public void loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(PROPERTIES_URI));
			USER = properties.getProperty(DB_USER_KEY);
			PASS = properties.getProperty(DB_PASS_KEY);
			JDBC_URL = properties.getProperty(DB_URL_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String mostrarInfoCliente() throws SQLException, ClassNotFoundException {
		StringBuilder sb = new StringBuilder();

		Connection connection = conect();

		Statement statement = statement(connection);

		ResultSet rs = statement.executeQuery("select * from Cliente");
		sb.append("nombre---apellidos---email---fechaNacimiento---genero").append(System.lineSeparator());
		while (rs.next()) {// Avanza de posición en el listado de registros y devuelve true si existe tal
			Cliente alumno = new Cliente(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3),
					rs.getString(5), rs.getString(4));
			sb.append(rs.getString(2) + "---" + rs.getString(3) + "---" + rs.getString(4) + "---"
					+ (LocalDate.now().getYear() - Integer.valueOf(rs.getString(5).substring(0, 4))) + "---"
					+ rs.getString(6)).append(System.lineSeparator());
		}
		connection.close();

		return sb.toString();
	}

	public String mostrarInfoPedidos() throws SQLException {
		StringBuilder sb = new StringBuilder();
		Connection connection = conect();

		Statement statement = statement(connection);

		ResultSet rs = statement.executeQuery(
				"SELECT P.id,P.status,C.nombre,C.apellido,L.cantidad,L.precio FROM Pedido P ,Linea L,Cliente C WHERE P.idCliente = C.id AND L.idPedido = P.id");
		sb.append("codigo---status---nombreCompletoCliente---numProducto---Importe").append(System.lineSeparator());
		while (rs.next()) {
			sb.append(String.format("%s---%s---%s %s---%s---%s", rs.getString(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getString(6))).append(System.lineSeparator());
		}

		return sb.toString();
	}

	private Statement statement(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();
		return statement;
	}

	private Connection conect() throws SQLException {
		Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
		return connection;
	}

	public void anniadirCliente(String nombre, String apellido, String email, String fechaNacimiento, String genero)
			throws SQLException {
		Connection connection = conect();
		Statement statement = statement(connection);
		PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO Cliente (nombre, apellido, email, fechaNacimiento, genero) " + " VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, nombre);
		ps.setString(2, apellido);
		ps.setString(3, email);
		ps.setString(4, fechaNacimiento);
		ps.setString(5, genero);
		ps.executeUpdate();
		ps.close();

	}

	public void actualizarCliente(int id, String nombre, String apellido) throws SQLException {
		Connection connection = conect();
		Statement statement = statement(connection);
		PreparedStatement ps = connection
				.prepareStatement("UPDATE Cliente c SET c.nombre = ?, c.apellido = ? WHERE c.id = ?");

		ps.setString(1, nombre);
		ps.setString(2, apellido);
		ps.setInt(3, id);
		ps.executeUpdate();
		ps.close();

	}

	public void eliminarCliente(int id) throws SQLException {
		Connection connection = conect();
		Statement statement = statement(connection);

		int idPedido = 0;

		ResultSet rs = statement.executeQuery("SELECT id from Pedido p where p.idCliente =" + id);
		while (rs.next()) {
			idPedido = rs.getInt(1);
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Linea l WHERE l.idPedido = ? ");
			ps.setInt(1, idPedido);
			ps.executeUpdate();
			ps.close();
		}

		PreparedStatement ps1 = connection.prepareStatement("DELETE FROM Pedido p WHERE p.idCliente = ? ");
		ps1.setInt(1, id);
		ps1.executeUpdate();
		ps1.close();
		PreparedStatement ps2 = connection.prepareStatement("DELETE FROM Cliente c WHERE c.id = ?");
		ps2.setInt(1, id);
		ps2.executeUpdate();
		ps2.close();

	}

	public void anniadirPedido(int id, String codigo, String status, int idCliente) throws SQLException {
		Connection connection = conect();
		Statement statement = statement(connection);
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO Pedido (id, codigo, status, idCliente) " + " VALUES (?, ?, ?, ?)");
		ps.setInt(1, id);
		ps.setString(2, codigo);
		ps.setString(3, status);
		ps.setInt(4, idCliente);
		ps.executeUpdate();
		ps.close();

	}

	public void anniadirLinea(int id, String codigo, String nombreProducto, int idPedido, int cantidad, int precio)
			throws SQLException {
		Connection connection = conect();
		Statement statement = statement(connection);
		PreparedStatement ps = connection
				.prepareStatement("INSERT INTO Linea (id, codigo, nombreProducto, idPedido, cantidad, precio) "
						+ " VALUES (?, ?, ?, ?,?,?)");
		ps.setInt(1, id);
		ps.setString(2, codigo);
		ps.setString(3, nombreProducto);
		ps.setInt(4, idPedido);
		ps.setInt(5, cantidad);
		ps.setInt(6, precio);
		ps.executeUpdate();
		ps.close();

	}

}
