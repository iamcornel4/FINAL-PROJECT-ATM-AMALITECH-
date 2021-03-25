package ATM;

public class Transaction {

	//The amount of this transaction
	private double amount;

	
	//A memo for this transaction 
	private String memo;
	
	//The account in which the transaction was performed 
	private Account inAccount;
	
	//create a new transaction
	// amount  is the amount transacted
	// inAccount is the account the transaction belongs to
	
	public Transaction (double amount, Account inAccount) {
	
		this.amount = amount;
		this.inAccount = inAccount;
		this.memo = " ";
	}
	public Transaction (double amount, String memo, Account inAccount) {
		 
		//call the two-argument constructor first
		this(amount, inAccount);
		 
		//set the memo
		this.memo = memo;
		 
	}
	// getting the amount of the transaction
	public double getAmount() {
		return this.amount; 
	}
	// get a string summarising the transaction
	public String  getSummaryLine() {
		
		if(this.amount >= 0) {
			return String.format("%s : $%.02f : %s ", toString(), this.amount, this.memo);
		
		}else {
			return String.format("%s  : $(%.02f) : %s ", toString(), - this.amount, this.memo);
		}
		
		
	}
}
