import java.sql.*;  

class MySQL{  
	public static void main(String args[]){  
		try {  

		   String hostname = args[0];
		   String database= args[1];
		   String port = args[2];		
		   String username = args[3];
		   String password = args[4];
		
		   String connectionStr = "jdbc:mysql://" + hostname + ":" 
			+ port + "/" + database;

		   System.out.println ( connectionStr );
		
		   Class.forName("com.mysql.jdbc.Driver");  
	
		   Connection con = DriverManager.getConnection(  
		       connectionStr,username,password);  
		   Statement stmt=con.createStatement();  
		   ResultSet rs=stmt.executeQuery("select * from Training");  

		   while(rs.next())  
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));  
		   con.close();  
		}
		catch(Exception e){ System.out.println(e);}  
	}  
} 
