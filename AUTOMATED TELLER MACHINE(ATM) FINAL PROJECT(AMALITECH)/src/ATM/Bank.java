package ATM;
 import java.util.ArrayList;
 import java.util.Random;
  
public class Bank {

	private String name;
	
	private ArrayList<User>users;
	
	private ArrayList<Account>accounts;
	
	
	//method for the bank name 
	public Bank(String name) {
		
	this.name = name;
	this.users = new ArrayList<User>();
	this.accounts = new ArrayList<Account>();
	
	}
	// Generate a new universally unique ID for a user
	//return the uuid
	public String getNewUserUUID() {
		//initialise
		String uuid;
		Random rng = new  Random();
		int len = 6;
		boolean nonUnique;
		//continue looping until we get a Unique ID
		do {
			//generate the number
			uuid = "";
			for (int c =0; c< len; c++) {
				uuid += ((Integer)rng.nextInt(10)).toString() ;
				
			}
			//validating the uniqueness the number
			nonUnique = false;
			for (User u : this.users) {
				if (uuid.compareTo(u.getUUID())  == 0) {
					nonUnique = true;
					break;
					
				}
			}
		}while (nonUnique);
		 
		return uuid;
		 
	}
	// generate a new universally unique ID for an account
	//return the uuid
	public String getNewAccountUUID() {
		//initialise
				String uuid;
				Random rng = new  Random();
				int len = 10;
				boolean nonUnique;
				//continue looping until we get a Unique ID
				do {
					//generate the number
					uuid = "";
					for (int c =0; c< len; c++) { 
						uuid += ((Integer)rng.nextInt(10)).toString() ;
						
					}
					//validating the uniqueness the number
					nonUnique = false;
					for (Account a : this.accounts) {
						if (uuid.compareTo(a .getUUID())  == 0) {
							nonUnique = true;
							break;
							
						}
					}
				}while (nonUnique);
				 
				return uuid;
		
	}
	//Add an account
	public void addAccount(Account anAcct) {
		this.accounts.add(anAcct);
	}
	// create a new user of the bank
	//firstName of the user
	//the lastName of the user
	// the users pin
	// and return the new users object
	

	public User addUser(String firstName, String lastName, String pin) {
		
		//create a new User object  and add it to our list of users
		User newUser  = new User(firstName, lastName, pin, this);
		this.users.add(newUser);
		
		// create a savings account for the user and add the user and the bank accounts lists
	Account newAccount = new Account("Savings", newUser, this);	
    newUser.addAccount(newAccount);
	this.accounts.add(newAccount);
		   
	return newUser;
	}
	//validate the login process of the user
	public User userLogin(String userID, String pin) {
		//search through list of users 
		for (User u : this.users) {
			// check user ID is correct 
			if (u.getUUID().compareTo(userID)  == 0 && u.validatePin(pin)){
				return u;
			}
		}
		//if we haven't found the user or have an incorrect pin
		
		return null;
	}
	
		
	
	// Get the name of the bank
	public String getName() {
		return this.name;
	}
}

