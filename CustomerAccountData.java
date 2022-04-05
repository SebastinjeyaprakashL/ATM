package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class CustomerAccountData {
	private HashMap <Long,Integer> CustomerIdData = new HashMap <Long,Integer> ();
	private HashMap <Integer,Float> CustomerAmountData = new HashMap <Integer,Float> ();
	
	public static final String CustomeraccountIdFilePath = "C:\\Users\\test\\eclipse-workspace\\ATM\\src\\atm\\CUSTOMER_CARDNUMBER_ACCOUNTID_FILEPATH.txt", 
							   CustomerAmountDataFilePath = "C:\\Users\\test\\eclipse-workspace\\ATM\\src\\atm\\CUSTOMER_ACCOUNT_BALANCE_DATA.txt";
	CustomerAccountData (){
		loadAccountId_CustomerId ();
	}
	void loadAccountId_CustomerId (){
		
		try {
			File file = new File (CustomeraccountIdFilePath);
			BufferedReader readerForCustomerIDData = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			long cardNumber ;
			int accountId;
			while ((fileReaderByLine = readerForCustomerIDData.readLine()) != null) { 
			lastEntry = fileReaderByLine;
				if (lastEntry  != "") {
					String lastLineArray [] = lastEntry.split(":");
					cardNumber = Long.parseLong(lastLineArray[0]);
					accountId = Integer.parseInt(lastLineArray[1]);
					CustomerIdData.put (cardNumber,accountId);
				}
			}
			readerForCustomerIDData.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void loadCustomerAmountFromAccount () {
		try {
			File file = new File (CustomerAmountDataFilePath);
			BufferedReader readerForCustomerAmountData = new BufferedReader(new FileReader(file));
			String lastEntry = "", fileReaderByLine;
			float amount ;
			int accountId;
			while ((fileReaderByLine = readerForCustomerAmountData.readLine()) != null) { 
			lastEntry = fileReaderByLine;
				if (lastEntry  != "") {
					String lastLineArray [] = lastEntry.split(":");
					amount = Float.parseFloat(lastLineArray[1]);
					accountId = Integer.parseInt(lastLineArray[0]);
					CustomerAmountData.put (accountId,amount);
				}
			}
			readerForCustomerAmountData.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateAccountAmountInBank () {
		try {
			File file = new File (CustomerAmountDataFilePath);
			BufferedWriter writerForAccountAmount = new BufferedWriter( new FileWriter(file) );
			for (Map.Entry<Integer, Float> entry : CustomerAmountData.entrySet()){
				writerForAccountAmount.write(entry.getKey() + ":" + entry.getValue());
				writerForAccountAmount.newLine();
			}
			writerForAccountAmount.flush();
			writerForAccountAmount.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getCustomerIdUsingCardNumber (long cardNumber){
		int currentCustomerId = CustomerIdData.get(cardNumber);
		return currentCustomerId;
	}
	
	public float getCustomerAccountAmount (int accountId) {
		loadCustomerAmountFromAccount();
		float AccountBal = CustomerAmountData.get(accountId);
		return AccountBal;
	}
	
	public void setUpdatedAccountBalance(int accountId, float updatedAmount) {
		CustomerAmountData.put(accountId, updatedAmount);
		updateAccountAmountInBank();
	}
	
}
