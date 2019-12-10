package hospital;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hopstaff {

	ConnectionManage con=new ConnectionManage();
	Scanner s=new Scanner(System.in);
	int ch=0,op=0;
	
	//Login
	
	public void login() throws ClassNotFoundException, SQLException {
		System.out.println("Username");
		String user=s.next();
		System.out.println("Password");
		String pass=s.next();
		Statement st=con.getConnection().createStatement();
		ResultSet rs=st.executeQuery("select * from opstaff");
		while(rs.next())
		{
			String u=rs.getString(1);
			String p=rs.getString(2);
			if(u.contentEquals(user)&&p.contentEquals(pass))
			{
				System.out.println("OP Staff Page");
				staff();
			}
			else
			{
				System.out.println("Incorrect username and password");
				System.out.println("-----------------");
				login();
			}
		}
	}
	
	private void staff() throws ClassNotFoundException, SQLException {
		System.out.println("1 : Emergency\n2 : Outpatient(OP)\n3 : Logout");
		System.out.println("Enter your choice");
		ch=s.nextInt();
		opStaff();
		
	}
	private void opStaff() throws ClassNotFoundException, SQLException {
		if(ch==1)    //emergency
		{
			do
			{
				System.out.println("1 : Available doctors\n2 : Add Patient\n3 : Exit");
				op=s.nextInt();
				if(op==1)  //display available doctors
				{
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select * from doctor");
					while(rs.next())
					{
						System.out.println("******Details***********");
						System.out.println("Doctor id ->"+rs.getInt(1));
						System.out.println("Doctor Name ->"+rs.getString(2));
						System.out.println("Department ->"+rs.getString(3));
						System.out.println("Duty time ->"+rs.getString(4)+" to "+rs.getString(5));
						System.out.println("Fee ->"+rs.getInt(6));
						System.out.println("-------------------");
					}	
				}
				else if(op==2)  //add patient and display the bill
				{
					System.out.println("Enter patient id");
					int id=s.nextInt();
					System.out.println("Enter patient Name");
					String name=s.next();
					System.out.println("Age");
					int age=s.nextInt();
					System.out.println("Gender");
					String gen=s.next();
					System.out.println("Address");
					String add=s.next();
					PreparedStatement pr=con.getConnection().prepareStatement("insert into patient(pid,pname,age,gender,address)values(?,?,?,?,?)");
					pr.setInt(1, id);
					pr.setString(2, name);
					pr.setInt(3, age);
					pr.setString(4, gen);
					pr.setString(5, add);
					pr.execute();
					System.out.println("Details added");
				
				
					System.out.println("Department");
					String depm=s.next();
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select dname,department,stime,etime,fee from doctor");
					while(rs.next())
					{
						String d=rs.getString(2);
						if(d.contentEquals(depm))
						{
							System.out.println("*****Details******");
							System.out.println("Doctor Name ->"+rs.getString(1));
							System.out.println("Duty time ->"+rs.getString(3)+" to "+rs.getString(4));
							System.out.println("Fee->"+rs.getInt(5));
							System.out.println("-------------------");
						}
							
					}
					System.out.println("Which doctor do you want");
					String doct=s.next();int feee=0;String dn="",dp="";
					Statement st1=con.getConnection().createStatement();
					ResultSet rs1=st1.executeQuery("select dname,department,fee from doctor");
					while(rs1.next())
					{
						 dn=rs1.getString(1);
						 dp=rs1.getString(2);
						if(dn.contentEquals(doct)&&dp.contentEquals(depm))
						{
							dn=rs1.getString(1);
							 dp=rs1.getString(2);
							feee=rs1.getInt(3);
						}
					
					}
					System.out.println("****************");
					System.out.println("Id -> "+id);
					System.out.println("Name ->"+name);
					System.out.println("Name of doctor ->"+dn);
					System.out.println("Fee ->"+(feee));
					System.out.println("-----------------------");
				}
				else if(op==3)  //go back to menu
				{
					staff();
				}
				else
				{
					System.out.println("Invalid option");
					System.out.println("--------------");
					opStaff();
				}
			}
			while(op==1||op==2);
			
		}
		else if(ch==2) //OP
		{
			do
			{
				System.out.println("1 : Available doctors\n2 : Add Patient\n3 : Exit");
				op=s.nextInt();
				if(op==1)  //Display available doctors
				{
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select * from opdoctor");
					while(rs.next())
					{
						System.out.println("******Details***********");
						System.out.println("Doctor id ->"+rs.getInt(1));
						System.out.println("Doctor Name ->"+rs.getString(2));
						System.out.println("Department ->"+rs.getString(3));
						System.out.println("Duty time ->"+rs.getString(4)+" to "+rs.getString(5));
						System.out.println("Fee ->"+rs.getInt(6));
						System.out.println("-------------------");
					}	
				}
				else if(op==2) //add patient and print bill
				{
					System.out.println("Enter patient id");
					int id=s.nextInt();
					System.out.println("Enter patient Name");
					String name=s.next();
					System.out.println("Age");
					int age=s.nextInt();
					System.out.println("Gender");
					String gen=s.next();
					System.out.println("Address");
					String add=s.next();
					PreparedStatement pr=con.getConnection().prepareStatement("insert into oppatient(pid,pname,age,gender,address)values(?,?,?,?,?)");
					pr.setInt(1, id);
					pr.setString(2, name);
					pr.setInt(3, age);
					pr.setString(4, gen);
					pr.setString(5, add);
					pr.execute();
					System.out.println("Details added");
				
				
					System.out.println("Department");
					String depm=s.next();
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select dname,department,stime,etime,fee from opdoctor");
					while(rs.next())
					{
						String d=rs.getString(2);
						if(d.contentEquals(depm))
						{
							System.out.println("*****Details******");
							System.out.println("Doctor Name ->"+rs.getString(1));
							System.out.println("Duty time ->"+rs.getString(3)+" to "+rs.getString(4));
							System.out.println("Fee ->"+rs.getInt(5));
							System.out.println("-------------------");
						}
						int opticket=100;	
					}
					System.out.println("Which doctor do you want");
					String doct=s.next();int feee=0;String dn="",dp="";
					Statement st1=con.getConnection().createStatement();
					ResultSet rs1=st1.executeQuery("select dname,department,fee from opdoctor");
					while(rs1.next())
					{
						 dn=rs1.getString(1);
						 dp=rs1.getString(2);
						if(dn.contentEquals(doct)&&dp.contentEquals(depm))
						{
							dn=rs1.getString(1);
							 dp=rs1.getString(2);
							feee=rs1.getInt(3);
						}
					
					}
					System.out.println("****************");
					System.out.println("Patient Id -> "+id);
					System.out.println("Patient Name ->"+name);
					System.out.println("Name of doctor ->"+dn);
					System.out.println("Fee + OP ticket charge ->"+(feee+100));
					System.out.println("-----------------------");
				}
				else if(op==3) //back to menu
				{
					staff();
				}
				else
				{
					System.out.println("Invalid option");
					System.out.println("-------------");
					opStaff();
				}
			}
			while(op==1||op==2);
			
		}
		else if(ch==3)  //go back to main menu
		{
			System.out.println("Staff logout");
			Hmain ha=new Hmain();
			ha.reg();
		}
		else
		{
			System.out.println("Invalid input");
			System.out.println("--------------");
			staff();
		}
	}

}
