
package bankaccountdemo;
/**
 *
 * @author Leona Henion <LHenion@gmail.com>
 */
public abstract class Account {
    
	// Used by derived classes to show what type they are
	protected String accountType;
	// The number of the account
	protected int accountNumber;
	// Current account balance
	protected double accountBalance;
	

	// Constructor, takes the account number
	protected Account(int accountNumber)
	{
		this.accountNumber = accountNumber;
		this.accountBalance = 0.0;
	}
	
	/* Accessors and mutators */
	public double getAccountBalance(){
		return accountBalance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}
	
	/* Abstract methods */
	public abstract String getAccountType();
	public abstract TransactionResult Withdrawal(double amount);
	public abstract TransactionResult Deposit(double amount);
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("-- ACCOUNT SUMMARY --\n");
		builder.append(String.format("Account # : %d\n", this.accountNumber));
		builder.append(String.format("Type : %s\n", this.accountType));
		builder.append(String.format("Balance : $%.2f\n", this.accountBalance));
		return builder.toString();
	}
		
}
