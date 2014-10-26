package localhost.biblioteca.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysql implements Sql
{
	private Connection connection;

	public Mysql() throws ClassNotFoundException, SQLException
	{
		String driverName = "com.mysql.jdbc.Driver";
		String server = "localhost";
		String database = "biblioteca";
		String username = "root";
		String password = "aluno";

		Class.forName(driverName);

		connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", server, database), username, password);
	}

	@Override
	public Connection getConnection()
	{
		return connection;
	}

	@Override
	public void closeConnection() throws SQLException
	{
		connection.close();
	}
}
