package budget;

import java.sql.*;
import java.util.*;
import budget.connect;

public class register 
{
  public void reg() {
					Connection con;
					Statement st;
					Scanner scan=new Scanner(System.in);

					try
					{

					Class.forName("com.mysql.cj.jdbc.Driver");
		  
					con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");
   
				System.out.println("Enter your email");
				String email=scan.next();

				System.out.println("Enter password");
				String password=scan.next();

				String str1="insert into users(email,password) values('"+email+"','"+password+"')";
				
				st=con.createStatement();
				st.execute(str1);
				System.out.println("Record Inserted Successfully");
				con.close();

				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
public static void main(String args[])
{
register abc=new register();
abc.reg();
	
}

}





