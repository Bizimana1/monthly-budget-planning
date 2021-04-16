package budget;
import java.util.*;
import budget.*;
import budget.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class transaction {
	String password,status,login;
	public static String  email;
	String email1,password1,login_status="";
	
	 int income=0,expense=0;
	ResultSet rs;
	Connection con;
	Statement st;
	Scanner abc=new Scanner(System.in);


public void start() {
	
dashbord n=new dashbord();
budget.transaction.email=n.user();
	System.out.println("\n your email is :"+email);
	
	System.out.println("\n 1.Make Transaction");
	System.out.println("\n 2.Transaction History");
	System.out.println("\n 3.Dashboard");
	System.out.println("\n 4.Logout");
	System.out.println("\n Enter your choice: ");
	int choice=abc.nextInt();
	
	transaction act=new transaction();
	if(choice==1) {
		
		act.activity();
	}
	else if(choice==2) {
		act.history();
	}
	else if(choice==3) {
		dashbord log=new dashbord();
		log.display();
	}
	else if(choice==4) {
		dashbord log=new dashbord();
		log.login();
	}
	else {
		System.out.println(" `````incorrect choice````````");
		start();
	}
}

public void activity() {
	
	System.out.println("\n 1.Add income");
	System.out.println("\n 2.Add expense");
	System.out.println("\n 3.back");
	System.out.println("\n 4.Logout");
	System.out.println("\n Enter your choice: ");
	int choice=abc.nextInt();
	
	transaction add=new transaction();
	if(choice==1) {
		add.income();
	}	
	else if(choice==2) {
		add.expense();
	}
	else if(choice==3) {
		dashbord log=new dashbord();
		log.display();
	}
	else if(choice==4) {
		dashbord act=new dashbord();
		add.start();
	}
	else {
		System.out.println(" `````incorrect choice````````");
		activity();
	}
	

}

public void history() {
	

	try
	{

	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");

    st=con.createStatement();
    
    System.out.println("\nNO   DATE   	 TYPE   AMOUNT\n");
    
    rs=st.executeQuery("select * from transaction where user='"+email+"'");
    int id=1;
    while(rs.next())
     {
	
	String type=rs.getString("category");
	String amount=rs.getString("amount");
	String date=rs.getString("date");
     if(id<10) {
    	 System.out.println(id+"  "+date+"   "+type+"  "+amount);
     }
     else {
    	 System.out.println(id+" "+date+"   "+type+"  "+amount);
     }
    id++;
   }
    transaction disp=new transaction();
    disp.start();
System.out.println("\n \nthank you for using our appllication");

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public void income() {

	try
	{
	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");

st=con.createStatement();


System.out.println("\n ENTER Amount:_ ");
int amount1=abc.nextInt();
String  str1="insert into transaction(category,amount,user)  values('income','"+amount1+"' , '"+email+"')";

st.execute(str1);
System.out.println("your income is increased by "+amount1+"₹");	

rs=st.executeQuery("select user,amount from income where user like'"+email+"'");
int i=0;
while(rs.next())
{
	String user=rs.getString("user");
	String number=rs.getString("amount");
	int number1=Integer.parseInt(number);
	if(user.equals(email)) {

		   String  str2="update income set amount=amount+"+amount1+" where user='"+email+"'";
			st.execute(str2);
			System.out.println("\n----------Updated value of Income is: "+(number1+amount1));
		i++;
		break;
	}
}
if(i==0) {
	String  str3="insert into income(user,amount)  values('"+email+"','"+amount1+"')";	
	st.execute(str3);
	}
transaction add=new transaction();
	add.activity();
	

	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


public void expense() {

	try
	{


	Class.forName("com.mysql.cj.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");

st=con.createStatement();

System.out.println("\n ENTER Amount:_ ");
int amount1=abc.nextInt();


String  str1="insert into transaction(category,amount,user)  values('expense','"+amount1+"' , '"+email+"')";

st.execute(str1);
System.out.println("your expenses have been increased by "+amount1+"₹");	

rs=st.executeQuery("select user,amount from expenses where user like'"+email+"'");
int i=0;
while(rs.next())
{
	String user=rs.getString("user");
	String number=rs.getString("amount");
	int number1=Integer.parseInt(number);
	if(user.equals(email)) {

		   String  str2="update expenses set amount=amount+"+amount1+" where user='"+email+"'";
			st.execute(str2);
			System.out.println("\n----------Updated value of Expense is: "+(number1+amount1));
		i++;
		break;
	}
   }
      if(i==0) {
	String  str3="insert into expenses(user,amount)  values('"+email+"','"+amount1+"')";	
	st.execute(str3);
	}
      transaction add=new transaction();
  	add.activity();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}


	
	  public void activity1() {
						
		  
						try
						{

						Class.forName("com.mysql.cj.jdbc.Driver");
			  
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_budget?useTimezone=true&serverTimezone=UTC","root","");
	   
					System.out.println("Enter your email");
					String email=abc.next();

					System.out.println("Enter password");
					String password=abc.next();

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

	public static void main(String[] args) {
		
		transaction a=new transaction();
		a.start();

	}

}
