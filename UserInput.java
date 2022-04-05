package userInputs;

import java.util.Scanner;

public class UserInput {
	public static Scanner sc = new Scanner (System.in);
	public static int getIntUserInput () {
		try {
			int input = sc.nextInt();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");
			return 0;
		}
		finally {
			sc.nextLine();
		}
		
		
	}
	public static String getStringUserInput ()
	{
		try {
			String input = sc.nextLine().toUpperCase();
			return input;
		}
		catch (Exception e) {
			System.out.println("Invalid input for the current action ");;
			return null;
		}
	}
}
