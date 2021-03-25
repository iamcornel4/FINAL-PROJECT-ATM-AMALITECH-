package ATM;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class User {
//The first name of the user.	
	
 private String firstName;

 // The last name of the user
 private String lastName;
 
 //The ID number of the user
 private String uuid;
  
 //The MD5hash of the user's pin number
 private byte  pinHash[ ];
 
 //The list of accounts for this user
 private ArrayList<Account>accounts;
 
  
 public User(String firstName, String lastName, String pin, Bank theBank) {
 
	//set user's name
	 this.firstName = firstName;
	 this.lastName = lastName;
	 
	 //For Security Reasons, store the pin's MD5 hash rather than the original
	 try {
		MessageDigest md= MessageDigest.getInstance("MD5");
		this.pinHash = md.digest(pin.getBytes());
	} catch (NoSuchAlgorithmException e) {
		// TODO Auto-generated catch block
		System.err.println("error, caught NoSuchAlgorithmException");
		e.printStackTrace();
		System.exit(1);
		
	}
	 
	 //get a new, unique universal ID for the user
	 this.uuid =theBank.getNewUserUUID();
	 
	 //create an empty list of accounts
	 this.accounts = new ArrayList<Account>();
	 
	 //print log message
	System.out.printf( "New user %s, %s  with ID %s created\n " , lastName,  firstName,  this.uuid); 
}
 
 //Add an account for the user
 //anAcct  is the account to add
 public void addAccount(Account anAcct) {
	 this.accounts.add(anAcct);
	 
 }
 //Returns the user's uuid
 public String getUUID() {
 	 return uuid;
	 //checking whether a given pin matches the true user pin
 }
	 public boolean validatePin(String aPin) {
		 try {
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 return MessageDigest.isEqual(md.digest(aPin.getBytes()), this.pinHash);
		 } catch (NoSuchAlgorithmException e) {
			 System.err.println("error , caught NoSuchAlgorithm");
			 e.printStackTrace();
			 System.exit(1);
			 
	 }
	  
	return false;
   }
	 public  String getfirstName() {
		 return this.firstName;
	  }
	 
	 public void printAccountsSummary() {
		 System.out.printf( "\n\n%s 's accounts summary\n", this.firstName);
		 for(int a = 0; a < this.accounts.size(); a++){
		 System.out.printf("%d)  %s\n",  a+1,
				 this.accounts.get(a). getSummaryLine());
		 
	 } 
    System.out.println();
}	
	 // getting the accounts of the user and return the accounts
	 public int numAccounts() {
		 return this.accounts.size();
	 }
	 //print the transaction history for a particular account
	 // acctIdx is the index of the account to use
	 public void printAcctTransHistory( int acctIdx) {
		 this.accounts.get(acctIdx).printTransHistory();
		 
	 }
	 //  getting the balance of a particular account
	 public double getAcctBalance(int acctIdx) {
		 return this.accounts.get(acctIdx). getBalance();
	 }
	 
	 // get the UUID  of a particular account
	 public String getAcctUUID(int acctIdx) {
		 return this.accounts.get(acctIdx).getUUID();
		 
	 }
	 // add a transaction to a particular account
	 public void addAcctTransaction(int acctIdx, double amount, String  memo) {
			 this.accounts.get(acctIdx).addTransaction( amount, memo);
     } 
	 	 
	 }
	 