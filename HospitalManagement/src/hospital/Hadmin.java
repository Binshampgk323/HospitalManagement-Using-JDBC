package hospital;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Hadmin {
	ConnectionManage con=new ConnectionManage();
	Scanner s=new Scanner(System.in);
	int ch=0,op=0;
		
	//login
	
	public void login() throws ClassNotFoundException, SQLException
	{
		System.out.println("Username");
		String user=s.next();
		System.out.println("Password");
		String pass=s.next();
		Statement st=con.getConnection().createStatement();
		ResultSet rs=st.executeQuery("select * from admin");
		while(rs.next())
		{
			String uname=rs.getString(1);
			String pa=rs.getString(2);
			if(uname.contentEquals(user)&&pa.contentEquals(pass))
			{
				System.out.println("Admin page\n");
				option();
			}
			else
			{
				System.out.println("Incorrect username and password");
				System.out.println("--------------------");
				login();
			}
		}
	}
	public void option() throws ClassNotFoundException, SQLException {
	
		System.out.println("1 : Emergency\n2 : Outpatient(OP)\n3 : Logout");
		System.out.println("Enter your choice");
		ch=s.nextInt();
		admin();	
	}
	
	
	public void admin() throws ClassNotFoundException, SQLException {
			if(ch==1)
			{
			 do
			 {
				System.out.println("1)Add(doctor)\n2)Delete(doctor)\n3)Update(doctor)\n4)Display\n5)Exit");
				op=s.nextInt();
				if(op==1) //Add details
				{
					System.out.println("Enter id of the doctor");
					int did=s.nextInt();
					System.out.println("Enter name of the doctor");
					String dname=s.next();
					System.out.println("Enter department");
					String depmt=s.next();
					System.out.println("Duty Starting time");
					String stime=s.next();
					System.out.println("Duty ending time");
					String etime=s.next();
					System.out.println("Enter fee");
					int fee=s.nextInt();
					PreparedStatement pr=con.getConnection().prepareStatement("insert into doctor(did,dname,department,stime,etime,fee)values(?,?,?,?,?,?);");
					pr.setInt(1, did);
					pr.setString(2, dname);
					pr.setString(3, depmt);
					pr.setString(4, stime);
					pr.setString(5, etime);
					pr.setInt(6, fee);
					pr.execute();
					System.out.println("Details added");
				}
				else if(op==2) //Remove details
				{
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select did,dname from doctor");
					while(rs.next())
					{
						System.out.println("*********Details*******");
						System.out.println("Doctor id -> "+rs.getInt(1));
						System.out.println("Doctor Name -> "+rs.getString(2));
						System.out.println("-----------------------");
					}
					System.out.println("Enter doctor id");
					int id=s.nextInt();
					PreparedStatement pr=con.getConnection().prepareStatement("DELETE FROM doctor WHERE did=?");
					pr.setInt(1, id);
					pr.execute();
					System.out.println("Details Removed");
				}
				else if(op==3)  //update
				{
					System.out.println("Enter doctor id");
					int id=s.nextInt();
					System.out.println("No of field want to change");
					int no=s.nextInt();
					int n[]=new int[no];
					System.out.println("1)Staring time\n2)Ending time\n3)Fee");
					System.out.println("Which you want to change");
					for(int i=0;i<no;i++)
					{
						n[i]=s.nextInt();
					}
					if(no==1)
					{
						for(int i=0;i<no;i++)
						{
							if(n[i]==1) //update start time
							{
							System.out.println("Duty Starting time");
							String stime=s.next();
							PreparedStatement pr=con.getConnection().prepareStatement("update doctor set stime=? where did=?");
							pr.setString(1, stime);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							else if(n[i]==2) //update end time
							{
							System.out.println("Duty ending time");
							String etime=s.next();
							PreparedStatement pr=con.getConnection().prepareStatement("update doctor set etime=? where did=?");
							pr.setString(1, etime);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							else if(n[i]==3) //update fee
							{
							System.out.println("Enter fee");
							int fee=s.nextInt();
							PreparedStatement pr=con.getConnection().prepareStatement("update doctor set fee=? where did=?");
							pr.setInt(1, fee);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							
						}
					}
					else if(no==2)
					{
						for(int i=0;i<no-1;i++)
						{
							 if(n[i]==1&&n[i+1]==2) //update start time and end time
								{
								System.out.println("Duty Starting time");
								String stime=s.next();
								System.out.println("Duty ending time");
								String etime=s.next();
								PreparedStatement pr=con.getConnection().prepareStatement("update doctor set stime=?,etime=? where did=?");
								pr.setString(1, stime);
								pr.setString(2, etime);
								pr.setInt(3, id);
								pr.execute();
								System.out.println("Details updated");
								break;
								}
								else if(n[i]==2&&n[i+1]==3)//update end time and fee
								{
								System.out.println("Duty ending time");
								String etime=s.next();
								System.out.println("Enter fee");
								int fee=s.nextInt();
								PreparedStatement pr=con.getConnection().prepareStatement("update doctor set etime=?,fee=? where did=?");
								pr.setString(1, etime);
								pr.setInt(2, fee);
								pr.setInt(3, id);
								pr.execute();
								System.out.println("Details updated");
								break;
								}
								else if(n[i]==1&&n[i+1]==3) //update start time and fee
								{
									System.out.println("Duty starting time");
									String stime=s.next();
									System.out.println("Enter fee");
									int fee=s.nextInt();
									PreparedStatement pr=con.getConnection().prepareStatement("update doctor set stime=?,fee=? where did=?");
									pr.setString(1, stime);
									pr.setInt(2, fee);
									pr.setInt(3, id);
									pr.execute();
									System.out.println("Details updated");	
								}
								
						}
					}
					else if(no==3)
					{
						for(int i=0;i<no-1;i++)
						{
						 	if(n[i]==1&&n[i+1]==2&&n[i+2]==3)
						 	{
							System.out.println("Duty Starting time");
							String stime=s.next();
							System.out.println("Duty ending time");
							String etime=s.next();
							System.out.println("Enter fee");
							int fee=s.nextInt();
							PreparedStatement pr=con.getConnection().prepareStatement("update doctor set stime=?,etime=?,fee=? where did=?");
							pr.setString(1, stime);
							pr.setString(2, etime);
							pr.setInt(3, fee);
							pr.setInt(4, id);
							pr.execute();
							System.out.println("Details updated");
							}
						}	
						
					}
				}
			else if(op==4) //display details
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
			else if(op==5)
			{
				option();
			}
			else
			{
				System.out.println("Invalid input");
				admin();
			}
		 }
		 while(op==1||op==2||op==3||op==4);
	}
		else if(ch==2)
		{

			 do
			 {
				System.out.println("1)Add(doctor)\n2)Delete(doctor)\n3)Update(doctor)\n4)Display\n5)Exit");
				op=s.nextInt();
				if(op==1) //Add details
				{
					System.out.println("Enter id of the doctor");
					int did=s.nextInt();
					System.out.println("Enter name of the doctor");
					String dname=s.next();
					System.out.println("Enter department");
					String depmt=s.next();
					System.out.println("Duty Starting time");
					String stime=s.next();
					System.out.println("Duty ending time");
					String etime=s.next();
					System.out.println("Enter fee");
					int fee=s.nextInt();
					PreparedStatement pr=con.getConnection().prepareStatement("insert into opdoctor(did,dname,department,stime,etime,fee)values(?,?,?,?,?,?);");
					pr.setInt(1, did);
					pr.setString(2, dname);
					pr.setString(3, depmt);
					pr.setString(4, stime);
					pr.setString(5, etime);
					pr.setInt(6, fee);
					pr.execute();
					System.out.println("Details added");
				}
				else if(op==2) //remove details
				{
					Statement st=con.getConnection().createStatement();
					ResultSet rs=st.executeQuery("select did,dname from opdoctor");
					while(rs.next())
					{
						System.out.println("*********Details*******");
						System.out.println("Doctor id -> "+rs.getInt(1));
						System.out.println("Doctor Name -> "+rs.getString(2));
						System.out.println("-----------------------");
					}
					System.out.println("Enter doctor id");
					int id=s.nextInt();
					PreparedStatement pr=con.getConnection().prepareStatement("DELETE FROM opdoctor WHERE did=?");
					pr.setInt(1, id);
					pr.execute();
					System.out.println("Details Removed");
				}
				else if(op==3) // update
				{
					System.out.println("Enter doctor id");
					int id=s.nextInt();
					System.out.println("No of field want to change");
					int no=s.nextInt();
					int n[]=new int[no];
					System.out.println("1)Staring time\n2)Ending time\n3)Fee");
					System.out.println("Which you want to change");
					for(int i=0;i<no;i++)
					{
						n[i]=s.nextInt();
					}
					if(no==1)
					{
						for(int i=0;i<no;i++)
						{
							if(n[i]==1)       //update starting time
							{
							System.out.println("Duty Starting time");
							String stime=s.next();
							PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set stime=? where did=?");
							pr.setString(1, stime);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							else if(n[i]==2)    //update ending time
							{
							System.out.println("Duty ending time");
							String etime=s.next();
							PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set etime=? where did=?");
							pr.setString(1, etime);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							else if(n[i]==3) //update fee
							{
							System.out.println("Enter fee");
							int fee=s.nextInt();
							PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set fee=? where did=?");
							pr.setInt(1, fee);
							pr.setInt(2, id);
							pr.execute();
							System.out.println("Details updated");
							}
							
						}
					}
					else if(no==2)
					{
						for(int i=0;i<no-1;i++)
						{
							if(n[i]==1&&n[i+1]==2) //update start time and end time
							{
							System.out.println("Duty Starting time");
							String stime=s.next();
							System.out.println("Duty ending time");
							String etime=s.next();
							PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set stime=?,etime=? where did=?");
							pr.setString(1, stime);
							pr.setString(2, etime);
							pr.setInt(3, id);
							pr.execute();
							System.out.println("Details updated");
							break;
							}
							else if(n[i]==2&&n[i+1]==3) //update end time and fee
							{
							System.out.println("Duty ending time");
							String etime=s.next();
							System.out.println("Enter fee");
							int fee=s.nextInt();
							PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set etime=?,fee=? where did=?");
							pr.setString(1, etime);
							pr.setInt(2, fee);
							pr.setInt(3, id);
							pr.execute();
							System.out.println("Details updated");
							break;
							}
							else if(n[i]==1&&n[i+1]==3) //update start time and fee
							{
								System.out.println("Duty starting time");
								String stime=s.next();
								System.out.println("Enter fee");
								int fee=s.nextInt();
								PreparedStatement pr=con.getConnection().prepareStatement("update opdoctor set stime=?,fee=? where did=?");
								pr.setString(1, stime);
								pr.setInt(2, fee);
								pr.setInt(3, id);
								pr.execute();
								System.out.println("Details updated");	
							}
						

						}
					}
				if(no==3)
				{
					for(int i=0;i<no-1;i++)
					{
						 if(n[i]==1&&n[i+1]==2&&n[i+2]==3) // update start time, end time and fee
						{
						System.out.println("Duty Starting time");
						String stime=s.next();
						System.out.println("Duty ending time");
						String etime=s.next();
						System.out.println("Enter fee");
						int fee=s.nextInt();
						PreparedStatement pr=con.getConnection().prepareStatement("update doctor set stime=?,etime=?,fee=? where did=?");
						pr.setString(1, stime);
						pr.setString(2, etime);
						pr.setInt(3, fee);
						pr.setInt(4, id);
						pr.execute();
						System.out.println("Details updated");
						}
						
					}
				}
			}
				else if(op==4) //display details
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
				else if(op==5) //exit from this menu
				{
					option();
				}
				else
				{
					System.out.println("Invalid input");
					admin();
				}
			 }
			 while(op==1||op==2||op==3||op==4);	
		}
		else if(ch==3) //go back to main menu
		{
			System.out.println("Admin logout");
			Hmain ha=new Hmain();
			ha.reg();
		}
		else
		{
			System.out.println("Invalid input");
			option();
		}


		
	}

}
