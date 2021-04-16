package budget;
import budget.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class dashbord {
	public String password,status,login;
	public static String email="";
	String email1,password1,login_status="";
	
	 int income=0,expense=0;
Statement st;
ResultSet rs;
Connection con;
Scanner a=new Scanner(System.in);



	public void login() {
		
		try {
			
			dashbord n=new dashbord();
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");
		st=con.createStatement();
		
System.out.println("...............Welcome...................");
	
	System.out.println("\n Do you have an account? yes/no ");
	login=a.nextLine();
	if(login.equals("no")) {
		System.out.println("................Create account...................");
		
		System.out.println("\n Enter your email:");
	    budget.dashbord.email=a.nextLine();
		System.out.println("\n Enter your password:");
		password=a.nextLine();
		String str1="insert into users(email,password) values('"+email+"','"+password+"')";
		
		st=con.createStatement();
		st.execute(str1);
		System.out.println("********Account created successfully**************");
		
		login();	
	   }

	else if(login.equals("yes")) {
		System.out.println("................lOGIN...................");
		
		System.out.println("\n Enter your email:");
		email=a.nextLine();
		System.out.println("\n Enter your password:");
		password=a.nextLine();
		rs=st.executeQuery("select * from users");

		while(rs.next())
		{
			
			 email1=rs.getString("email");
			 password1=rs.getString("password");
		if(email.equals(email1) && password.equals(password1)) {
			
		email=email1;
			login_status="true";
			break;
		}
		}
		if(login_status.equals("true")) {
			dashbord disp=new dashbord();
			disp.display();
		}
		else {
			System.out.println("\n*********Incorrect password/Email**********\n");
			login();
		}
	  
	}
}
	catch(Exception e)
	{


	e.printStackTrace();
	}
	
	}
	

public String user() {
	
return email;
}

public void display() {
		
try {

// database connection 
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");
	//System.out.println("database exist");
	st=con.createStatement();
	System.out.println("\n*********Login Successfull**********\n");
	
	dashbord n=new dashbord();
	
	//System.out.println("\n your email is :"+n.user());
	// finding the total income
	rs=st.executeQuery("select * from income where  user like'"+email+"'");
	
	while(rs.next())
	{
		income+=rs.getInt("amount");
	}
	
	// calculating total expenses
	rs=st.executeQuery("select * from expenses where user like'"+email+"'");
	
	while(rs.next())
	{
		expense+=rs.getInt("amount");
	}
	System.out.println("\n \t Current Total Income is: "+income+"₹");
	System.out.println("\n \t Current Total  Expenses is: "+expense+"₹");
	System.out.println("\n@@@@@@@ Current budget is: "+(income-expense)+"₹");
	transaction ab=new transaction();
	ab.start();
}
catch(Exception e)
{


e.printStackTrace();
}
	}
	
	public static void main(String[] args) {
		
		dashbord av=new dashbord();
		av.login();
	
		
	}
}