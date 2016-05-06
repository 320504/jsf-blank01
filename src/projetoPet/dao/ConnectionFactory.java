package projetoPet.dao;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactory {
	 static DataSource ds = null;
     static InitialContext ic;
     static Connection conn = null;

 
    private ConnectionFactory() {
    	
    }

    public static Connection getConnection(){
        try {
        	Class.forName("org.postgresql.Driver");
        	Connection connection = null;
        	connection = DriverManager.getConnection(
        	   "jdbc:postgresql://localhost:5432/pet","postgres", "postgres");
      
        	 return connection;
	    
	    } catch (Exception e) {
            e.printStackTrace();
            return null;
	    }
    }
}
