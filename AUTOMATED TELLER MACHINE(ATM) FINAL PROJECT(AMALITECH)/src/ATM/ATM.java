package ATM;
 import java.util.Scanner;
public class ATM {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Bank theBank = new Bank("Access Bank of Ghana");
		
		User aUser = theBank.addUser( "Adansi","Cornelius-Frank", "9012");
		
		Account newAccount = new Account("Current account", aUser, theBank);
		aUser.addAccount(newAccount);
		theBank.addAccount(newAccount);
		User curUser;
		while (true) {
			
// the login  prompt
		curUser = ATM.mainMenuPrompt(theBank, sc);
		
		ATM.printUserMenu(curUser, sc);
		
				
	}
}
public static User mainMenuPrompt(Bank  theBank, Scanner sc) {

	// initialise
	String UserID;
	String pin;
	User authUser;   // authUser  is the authorise user 
	
	//prompt the user for user ID/pin until a correct one is reached
	
	do {
		System.out.printf("\n\nWelcome to  %s\n\n",  theBank.getName());
		System.out.printf("Enter User ID : ");
		UserID = sc.nextLine();
		System.out.print("Enter pin:  ");
		pin = sc.nextLine();
		
		authUser = theBank.userLogin(UserID,  pin);
		
		if (authUser == null) {
			System.out.println(" Incorrect user ID/pin combination, " + " Please Enter pin again, ");
			
		}
			
	} while (authUser == null); // looping continues until successful login
	return  authUser;
}
public static void printUserMenu(User  theUser, Scanner sc) {

	//print a summary of the user's accounts
	theUser.printAccountsSummary();
	
	//initialise
	int option;
	
	// user menu 
	do {
		System.out.printf("Welcome %s , what transcation would you  like to perform\n?",  theUser.getfirstName());
		System.out.println("   1)  Withdrawal ");
		System.out.println("   2)   Deposit  ");
		System.out.println("   3)   Transfer ");
		System.out.println("   4)   Quit ");
		System.out.println();
		System.out.print("Enter Option:  ");
		option = sc.nextInt();
		 
		if (option < 1 || option > 4) {
			System.out.println("Invalid option. Please choose 1-4");
			
		}
	} while (option < 1 || option > 4 );
	 
	switch (option) {
	case 1 :
	           ATM.withdrawFunds(theUser, sc);
	           break;
	case 2 : 
	          ATM.depositFunds(theUser, sc);
	         break;
	case 3 :
		      ATM.transferFunds(theUser, sc);
	        break;
	case 4:	     
	//gobble up rest of previous input
	 sc.nextLine();
	 break;
		 
	}
	//redisplay this menu unless the user wants to quit
	if (option != 4) {
		ATM.printUserMenu(theUser , sc);
		
	}
}
     public static void showTransHistory(User theUser, Scanner sc) {
	int  theAcct;
	
	// get the account whose transaction history to look at
	do {
		System.out.printf("Enter the number (1-%d)  of the account\n" + 
   "whose transactions you want to see:", theUser.numAccounts());
		theAcct = sc.nextInt()-1;
		
		if(theAcct< 0|| theAcct >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again.");
		}
		
	}  while(theAcct< 0 || theAcct >=  theUser.numAccounts());
	
	//print the transaction history
	theUser.printAcctTransHistory(theAcct);
	
	
		}
      public static void transferFunds(User  theUser, Scanner sc) {
    	 
// initialise
    	 int fromAcct;
    	 int toAcct;
    	 double amount;
    	 double acctBal;
    	 
    	 //To get the account to transfer from
    	 do {
    		 System.out.printf("Enter the number (1-%d) of the account\n" +"to transfer from: " , theUser.numAccounts());
    		fromAcct = sc.nextInt()-1;
    		if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
    			System.out.println("Invalid account. Please try again.");
    			
    		}
    	 } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
    	acctBal = theUser.getAcctBalance(fromAcct);
    	
    	//get the account to transfer to
    	do {
    		System.out.printf("Enter the number (1-%d) of the account\n" +"to transfer to: " ,theUser.numAccounts()); 
    		toAcct = sc.nextInt()-1;
    		if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
    			System.out.println("Invalid account. Please try again.");
    		}
    	}while (toAcct < 0 || toAcct >= theUser.numAccounts());
    	
    	// get the amount to transfer
    	
    	do {
    		System.out.printf("Enter the Amount to transfer( max $%.02f) : $" , acctBal);
    		amount = sc.nextDouble();
    		if(amount < 0) {
    			System.out.println("Amount must be greater than zero. ");
    		} else  if (amount > acctBal);
    		System.out.printf("Amount must not be greater than\n" +"balance of $%.02f.\n", acctBal);
     } while (amount < 0 || amount > acctBal);
    //do the transfer
    	theUser. addAcctTransaction(fromAcct , -1*amount, String.format("Transfer to account %s", theUser.getAcctUUID(toAcct)));
    
    	theUser. addAcctTransaction(toAcct , amount, String.format("Transfer to account %s", theUser.getAcctUUID(fromAcct)));
}
     // process the fund withdrawal from an account
     public static void withdrawFunds(User theUser , Scanner sc) {
  // initialise
	 int fromAcct;
	 double amount;
	 String memo;
	 double acctBal;
	 
	 //To get the account to transfer from
	 do {
		 System.out.printf("Enter the number (1-%d) of the account\n" +"to Withdraw from: " , theUser.numAccounts());
		fromAcct = sc.nextInt()-1;
		if (fromAcct < 0 || fromAcct >= theUser.numAccounts()) {
			System.out.println("Invalid account. Please try again.");
			
		}
	 } while (fromAcct < 0 || fromAcct >= theUser.numAccounts());
	acctBal = theUser.getAcctBalance(fromAcct);
	
	// get the amount to transfer
	do {
		  System.out.printf("Enter the Amount to withdraw( max $%.02f) : $" , acctBal);
		amount = sc.nextDouble();
		if(amount < 0) {
			System.out.println("Amount must be greater than zero. ");
		} else  if (amount > acctBal) {
	       System.out.printf("Amount must not be greater than\n" +"balance of $%.02f.\n", acctBal);
		}
 } while (amount < 0 || amount > acctBal);
 
 //gobble up rest of previous input
 sc.nextLine();
 
 System.out.print("Enter a memo: ");
 memo = sc.nextLine();
 
 // do the withdraw
 theUser.addAcctTransaction(fromAcct, -1*amount , memo);

	}
     // process the fund deposit to an account 
	public static void depositFunds(User theUser, Scanner sc) {
		 // initialise
		 int toAcct;
		 double amount;
		 String memo;
		 double acctBal;
		 
		 //To get the account to transfer from
		 do {
			 System.out.printf("Enter the number (1-%d) of the account\n" +"to deposit in: " , theUser.numAccounts());
			toAcct = sc.nextInt()-1;
			if (toAcct < 0 || toAcct >= theUser.numAccounts()) {
				System.out.println("Invalid account. Please try again.");
				
			}
		 } while (toAcct < 0 || toAcct >= theUser.numAccounts());
		acctBal = theUser.getAcctBalance(toAcct);
		
		// get the amount to transfer
		do {
			  System.out.printf("Enter the Amount to deposit ( max $%.02f) : $" , acctBal);
			amount = sc.nextDouble();
			if(amount < 0) {
				System.out.println("Amount must be greater than zero. ");
			} 
			
	 } while (amount < 0 );
	 
	 //gobble up rest of previous input
	 sc.nextLine();
	 
	 System.out.print("Enter a memo: ");
	 memo = sc.nextLine();
	 
	 // do the withdraw
	 theUser.addAcctTransaction(toAcct, amount , memo);

	}
	
   }
