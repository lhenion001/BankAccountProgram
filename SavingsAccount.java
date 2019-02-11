package bankaccountdemo;
/**
 *
 * @author Leona Henion <LHenion@mail.dccc.edu>
 */
 
import java.lang.Exception;

//SavingsAccount class derived from the abstract base AccountClass
public class SavingsAccount extends Account{

	private double interestRate;			// Interest rate for Account
	private double accumulatedInterestPaid;		// Accumulated interest paid 

	/* Accessors and Mutators */
	public double getAccumulatedInterestPaid() {
		return accumulatedInterestPaid;
	}
	public double getInterestRate() {
		return interestRate;
	}
	@Override
	public String getAccountType() {
		return this.accountType;
	}
	
	public TransactionResult setInterestRate(double interestRate) {
		// Make sure interest rate is in valid range
		if(interestRate < 0.03 || interestRate > 0.065 ){
			return new TransactionResult(this, "Adjust Interest Rate", false, "Interest rate must be between 3% and 6.5%");
		}
		this.interestRate = interestRate;
		return new TransactionResult(this, "Adjust Interest Rate", true, "Interest Rate updated");
	}
	public TransactionResult CalculateInterest() {
		this.accumulatedInterestPaid += ((this.accountBalance + this.accumulatedInterestPaid) * this.interestRate);
		return new TransactionResult(this, "Calculate Interest", true, "Accumulated Interest updated");
	}
	
	// Constructor, takes account number for derived class
	public SavingsAccount(int accountNumber) {
		super(accountNumber);
		this.interestRate = 0.035;
		this.accountType = "Savings";
	}
	
	@Override
	public TransactionResult Withdrawal(double amount) {
		if(this.accountBalance < amount){
			return new TransactionResult(this, "Withdrawal", false, "The amount requested exceeds available balance");
		}
		this.accountBalance -= amount;
		return new TransactionResult(this, "Withdrawal", true, String.format( "Withdrew $%.2f", amount));

	}

	@Override
	public TransactionResult Deposit(double amount) {
		this.accountBalance += amount;
		return new TransactionResult(this, "Deposit" ,true, String.format( "Deposited $%.2f", amount));
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(String.format("Interest Rate : %.1f%%\n", this.interestRate*100));
		builder.append(String.format("Accumulated Interest : $%.2f\n", this.accumulatedInterestPaid));
		return builder.toString();
	}
		
}
