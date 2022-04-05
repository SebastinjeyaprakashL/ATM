package atm;

enum Languages {
	TAMIL("T"),ENGLISH("E");
	public String lang;
	
	Languages(String langFormat){
		this.lang = langFormat;
	}
	
}

public class IobAtm implements AtmFacilities {
	public static Long cardNumber;
	public static int accountId, transactionFee = 0;
	public static float accountBalance;
	public static boolean ownBankCard ;
	CustomerAccountData customerAccountData = new CustomerAccountData();
	
	public void displayLanguages() {		
		System.out.println("Choose your language :");
		for (Languages value : Languages.values()) {
			System.out.println(value.lang + " - " + value);
		}
	}

	public Languages chooseLanguage (String selectedLanguage) {
		for (Languages value : Languages.values()) {
			if(value.lang.equals(selectedLanguage)) {
				return value;
			}
		}
		return null;	
	}
	
	void getAccountDetails () {
		try {	
			accountId = customerAccountData.getCustomerIdUsingCardNumber (cardNumber);
			accountBalance = customerAccountData.getCustomerAccountAmount(accountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	IobAtm (){
		
		getAccountDetails();
		if (!ownBankCard) {
			transactionFee = 30;
		}
	}
	
	public void checkBalance() {
		System.out.println("Account Balance : "+ accountBalance);
		
	}


	public void withdraw(int amount) {
		
		boolean accountBalExistFlag = checkForAccountBalance(amount);
		if (accountBalExistFlag) {
			System.out.println("Account :" +accountBalance );
			if ((accountBalance - amount) > 0 ) {
				System.out.println("Please take your cash");
				accountBalance -= amount + transactionFee  ;
				customerAccountData.setUpdatedAccountBalance (accountId,accountBalance);
				getAccountDetails();
				checkBalance();
			} 
		}
	}


	public void deposit(int amount) {
		
		accountBalance += amount ;
		customerAccountData.setUpdatedAccountBalance (accountId,accountBalance);
		getAccountDetails();
		System.out.println("Amount "+ amount +" deposited Successfully");
		checkBalance();
	}
	
	public boolean checkForAccountBalance (int amount) {
		if (accountBalance > 100 ) {
			return true;
		}
		return false;
	}
	
}
