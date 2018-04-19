package persistence;

import java.sql.*;

class DataSource {
	
	//INFO connette con il database
	
	final private String dbURI;// = "jdbc:postgresql://localhost:5432/FantacalcioSiw";
	final private String userName;// = "postgres";
	final private String password;// = "root";
	
	

	public DataSource(String dbURI, String userName, String password) {
		this.dbURI=dbURI;
		this.userName=userName;
		this.password=password;
	}

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    connection = DriverManager.getConnection(dbURI,userName, password);
			
		
		} catch(SQLException e) {
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
