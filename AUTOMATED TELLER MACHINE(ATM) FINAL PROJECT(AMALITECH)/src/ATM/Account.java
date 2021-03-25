package ATM;
import java.util.ArrayList;

public class Account {

	//The name of the account
  private String name;
  
  //The account ID number
  private String uuid;
  
  //The user object that owns this account
  private  User holder;
  
  //The list of transactions for this account
  private  ArrayList<Transaction>transactions;
  
  //create a new account
  //the name of the account
  //the user object that holds the account
  //the bank that issues the account
  
  public Account (String name, User holder, Bank theBank) {
	  
	  this.name =name;
	  this.holder = holder;
	  
	 
	  //get new  account UUID
	  this.uuid = theBank.getNewAccountUUID();
	  
	  //initialise transaction
	  this.transactions = new ArrayList<Transaction>();
	  
  }
  //get the account ID
  public String getUUID() {
	  return this.uuid;
  }
  
  // Summary line for account 
   public String  getSummaryLine() {
	   
 	   double balance = this.getBalance();
	   
	   if (balance >= 0) {
		   return  String.format(" %s : $%.02f  :%s ", this.uuid,  balance, this.name);
		   
	   } else {
		   return  String.format(" %s : $(%.02f)  :%s ", this.uuid,  balance, this.name);
	   }   
	   }
	    public double getBalance() {
	    	double balance = 0;
	    	for(Transaction t : this.transactions) { 
	    		balance +=  t.getAmount();
	    	}
	    	return balance;
	    }
	    
	    //print the transaction history of the account
	    public void  printTransHistory() {
	    	System.out.printf("\nTransaction history for account %s\n", this.uuid);
	    	for (int t = this.transactions.size()-1;  t >= 0;  t-- ) {
	    		System.out.println(this.transactions.get(t). getSummaryLine());
	    	}
	    	System.out.println();
	    }
	    // add a new transaction in this account
	    public void addTransaction(double amount, String memo) {
	    	
	    	//create  new transaction object and add it the the list
	    	Transaction newTrans = new Transaction(amount, memo, this);
	    	this.transactions.add(newTrans);
	    }
   }


