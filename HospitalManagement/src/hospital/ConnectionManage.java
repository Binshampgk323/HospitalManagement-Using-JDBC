package hospital;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManage {
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		//register and load driver
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=null;  //create object 
		
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital","root","");
		
		if(con!=null) //check the connection
		{
			return con;
		}
		else
		{
			return null;
		}

	}

}
