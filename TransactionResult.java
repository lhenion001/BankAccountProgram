package bankaccountdemo;
/**
 *
 * @author Leona Henion<LHenion@mail.dccc.edu>
 */
public class TransactionResult { // Used as a common object to hold information concerning various transactions
	
	// The toString() of the account the transaction is on
	private String accountString;
	// The result of a given transaction, did it succeed or not
	private boolean result;
	// The transaction being attempted
	private String operation;
	// Additional info about the result
	private String message;
	
	/* Accessors and Mutators */
	public String getAccountString() {
		return accountString;
	}
	public boolean isResult() {
		return result;
	}
	public String getOperation() {
		return operation;
	}
	public String getMessage() {
		return message;
	}
	
	// Constructor
	public TransactionResult(Account account, String operation, boolean result, String message){
		this.accountString = account.toString();
		this.operation = operation;
		this.result = result;
		this.message = message;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("-- TRANSACTION RESULT --\n");
		builder.append(String.format("Operation : %s\n", this.operation));
		builder.append(String.format("Succeeded : %b\n", this.result));
		builder.append(String.format("Info : %s\n", this.message));
		builder.append(this.accountString);
		return builder.toString();
	}
	
	
	
}
