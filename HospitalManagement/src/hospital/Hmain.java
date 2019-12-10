package hospital;

import java.sql.SQLException;
import java.util.Scanner;

public class Hmain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Hmain h=new Hmain();
		h.reg();
		
	}

	void reg() throws ClassNotFoundException, SQLException {
		Scanner s=new Scanner(System.in);
		System.out.println("1 : Admin\n2 : Opstaff\n3 : Exit");
		System.out.println("Enter your option");
		int ch=s.nextInt();
		switch(ch)
		{
		case 1:Hadmin ha=new Hadmin(); //admin
			ha.login();
		break;
		case 2:Hopstaff op=new Hopstaff(); //staff
			op.login();
		break;
		case 3:System.out.println("Thank you..");
				System.exit(0);
		break;
		default:System.out.println("Invalid input");
		System.out.println("------------------");
				reg();
		
		}

	}

}
