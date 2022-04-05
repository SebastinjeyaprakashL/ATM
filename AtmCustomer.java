package atm;
import userInputs.UserInput;

public class AtmCustomer {

	public int getAmount(String action) {
		try {
			System.out.println("Enter the amount to "+ action);
			int amount = UserInput.getIntUserInput();
			return amount;
		}
		catch (Exception e) {
			System.out.println("Invalid amount !");
			return 0 ;
		}
		
	}
	
	public boolean getUserOptionToStayLoggedIn () {
		System.out.println("Do you want to continue transaction :"
				+ "\n1 - YES"
				+ "\n2 - NO");
		int userOption = UserInput.getIntUserInput();
		if (userOption == 1) {
			return false ;
		}
		else {
			return true;
		}
		
	}
	
	public static void main(String[] args) {
		boolean cancelFlag = false;
		AtmCustomer atmCustomer = new AtmCustomer();
		System.out.println("Welcome, please insert your card.");
		System.out.println( "\n =======AFTER INSERTING========");
		//Hard coded card number and bank type(Here IOB ==> true, Other Bank ==> false)  
		IobAtm.cardNumber =  3L;
		IobAtm.ownBankCard = false;
		////////////////////////
		IobAtm iobAtm = new IobAtm();
		iobAtm.displayLanguages();		
		String languageOption = UserInput.getStringUserInput();
		String languageName = iobAtm.chooseLanguage(languageOption).name();
		if (languageName != null) {
			System.out.println("LANGUAGE : "+ languageName);
			
			while (!cancelFlag) {
				System.out.println("Enter the action to do :"
						+ "\n1 - WITHDRAW"
						+ "\n2 - DEPOSIT"
						+ "\n3 - BALANCE ENQUIRY"
						+ "\n4 - CANCEL");
				int customerAction = UserInput.getIntUserInput();
				switch (customerAction) {
				case 1 :
					iobAtm.withdraw(atmCustomer.getAmount("WITHDRAW"));
					cancelFlag = atmCustomer.getUserOptionToStayLoggedIn();
					break;
				case 2 : 
					iobAtm.deposit(atmCustomer.getAmount("DEPOSIT"));
					cancelFlag = atmCustomer.getUserOptionToStayLoggedIn();
					break;
				case 3 : 
					iobAtm.checkBalance();
					cancelFlag = atmCustomer.getUserOptionToStayLoggedIn();
					break;
				case 4 : 
					cancelFlag = true;
					System.out.println("LAST TRANSACTION CANCELLED!");	
					break;
				default :
					break;
						
				
				}
			}
			
		}

	}

}
