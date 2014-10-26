package localhost.biblioteca.core;

import java.sql.Connection;
import java.sql.SQLException;

public interface Sql
{
	Connection getConnection();
	void closeConnection() throws SQLException;
}
