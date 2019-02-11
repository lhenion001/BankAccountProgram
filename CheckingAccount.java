package bankaccountdemo;
/**
 *
 * @author Leona Henion <LHenion@mail.dccc.edu>
 */
import java.lang.Exception;

public class CheckingAccount extends Account { // CheckingAccount class derived from the abstract base AccountClass

	// Is overdraft protection enabled
	private boolean overdraftProtectionFlag;
	// The amount currently overdrawn
	private double loanAmount;
	
	/* Accessors and Mutators */
	public boolean isOverdraftProtectionFlag() {
		return overdraftProtectionFlag;
	}
	
	@Override
	public String getAccountType() {
		return this.accountType;
	}

	public TransactionResult setOverdraftProtectionFlag(boolean overdraftProtectionFlag) {
		this.overdraftProtectionFlag = overdraftProtectionFlag;
		return new TransactionResult(this, "Set Overdraft Protection",true, String.format("Overdraft Protection : %b", this.overdraftProtectionFlag));
	}

	// Constructor, takes account number for derived class
	public CheckingAccount(int accountNumber) {
		super(accountNumber);
		this.accountType = "Checking";
		this.overdraftProtectionFlag = false;
		this.loanAmount = 0.0;
	}
	// Process a check, this method allows the account to be overdrawn if overdraft protection is enabled
	public TransactionResult Check(double amount) {
		if(this.accountBalance == 0.0) {
			return new TransactionResult(this, "Process Check", false, "Insufficient Funds");
		}
		if(this.accountBalance < amount) {
			if(this.overdraftProtectionFlag) {		
				this.loanAmount = Math.abs(this.accountBalance - amount);
				this.accountBalance = 0;
				return new TransactionResult(this, "Process Check", true, "Warning, you have overdrawn your account");
			} else {
				return new TransactionResult(this, "Process Check", false, "Insufficient Funds");
			}
		} else {
			this.accountBalance -= amount;
			return new TransactionResult(this, "Process Check", true, "Withdrawal succeeded");
		}
	}
	
	@Override
	public TransactionResult Withdrawal(double amount) {
		if(this.accountBalance < amount) {
			return new TransactionResult(this, "Withdrawal", false, "Insufficient Funds");
		} else {
			this.accountBalance -= amount;
			return new TransactionResult(this, "Withdrawal", true, "Withdrawal succeeded");
		}
	}

	@Override
	public TransactionResult Deposit(double amount) {
		if(this.loanAmount > 0) {
			if(amount <= this.loanAmount) {
				this.loanAmount -= amount;
				
			} else {
				this.loanAmount = 0;
				this.accountBalance = amount - this.loanAmount;
			}
			return new TransactionResult(this, "Deposit", true, "Warning: Deposit applied to loan amount");
		} else {
			this.accountBalance += amount;
			return new TransactionResult(this, "Deposit", true, String.format("Deposited $%.2f", amount));
		}
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(String.format("Overdraft Protection : %b\n", this.overdraftProtectionFlag));
		builder.append(String.format("Outstanding Loans : $%.2f\n", this.loanAmount));
		return builder.toString();
	}
}
