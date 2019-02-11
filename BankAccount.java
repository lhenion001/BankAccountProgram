
package bankaccountdemo;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Exception;
import java.io.IOException;


public class BankAccountDemo {

	private Scanner scanner;
	private boolean shouldContinue;
	String accountType;
	
	SavingsAccount savings;
	CheckingAccount checking;
	Account currentAccount;
	
	public void AccountMenu()
	{
		int option = 0;
		while(option != 4) {
		
			System.out.println(String.format("Accounts->%s", this.accountType));
			System.out.println("1) Create New");
			System.out.println("2) Show Current");
			System.out.println("3) Edit Current");
			System.out.println("4) Main Menu");

			try {
				option = scanner.nextInt();
				if(option < 1 || option > 4) throw new Exception();
			} catch(Exception ex) {
				System.out.println("Invalid option");
				option = 0;
			}
			
		
			switch(option) {
			case 1:
				System.out.println("Enter account number");
				int accountNum = 0;
				while(accountNum == 0)
				{
					try { 
						accountNum = scanner.nextInt();
						
						if(this.accountType == "Savings") {
							this.currentAccount = new SavingsAccount(accountNum);
						} else if(this.accountType == "Checking") 	{
							this.currentAccount = new CheckingAccount(accountNum);
						}
						
					}catch (Exception ex) {
						System.out.println("Invalid input. Account Number must be a positive integer > 0");
						scanner.next();

						accountNum = 0;
					}					
				}
				System.out.println(String.format("Created new %s account : #%d", this.currentAccount.accountType, this.currentAccount.getAccountNumber()));
				
				break;
			case 2:
				if(this.currentAccount == null) 
				{
					System.out.println("No account has been created");
					continue;
				}
				System.out.println("Showing current account: ");
				System.out.println(this.currentAccount.toString());
				break;
			case 3:
				if(this.currentAccount == null) 
				{
					System.out.println("No account has been created");
					continue;
				}
				if(this.accountType == "Checking") {
					EditChecking();
				} else if(this.accountType == "Savings") {
					EditSavings();
				}
				break;
			
			case 4:
				return;
			}
		}
	}
	public void EditChecking() {
		System.out.println("Checking Account Options: ");
		System.out.println("1) Deposit");
		System.out.println("2) Withdrawal");
		System.out.println("3) Process Check");
		System.out.println("4) Set Overdraft Protection");
		System.out.println("5) Back");
		
		int option = 0;
		while(option == 0) {
			try {
				option = scanner.nextInt();
				if(option < 1 || option > 5) throw new Exception();
			} catch(Exception ex) {
				System.out.println("Invalid option");
				option = 0;
			}
		}
		
		double amount = 0;
		TransactionResult result = null;
		switch(option) {
		case 1: 
			System.out.print("Enter amount to deposit : ");
			
			try {
				amount = scanner.nextDouble();
			}catch(Exception ex) {
				System.out.println("Invalid amount");
				scanner.next();
				return;
			}
			result = this.currentAccount.Deposit(amount);
			
			break;
		case 2:
			System.out.print("Enter amount to withdrawal: ");
			
			try {
				amount = scanner.nextDouble();
			}catch(Exception ex) {
				System.out.println("Invalid amount");
				scanner.next();
				return;
			}
			result = this.currentAccount.Withdrawal(amount);
			break;
		case 3:
			double checkAmount = 0;
			System.out.print("Enter check amount : ");
			try {
				checkAmount = scanner.nextDouble();
			} catch(Exception ex) {
				System.out.println("Check must be a double");
				scanner.next();
				return;
			}
			try {
				result = ((CheckingAccount)this.currentAccount).Check(checkAmount);	
			} catch(Exception ex) {
				System.out.println("Invalid operation for current account");
				return;
			}
			break;
			
		case 4:
			System.out.print("Turn on overdraft protection? (Y/N): ");
			String answer = "";
			try {
				while(!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
					answer = scanner.next();
					if(!answer.equalsIgnoreCase("Y") && !answer.equalsIgnoreCase("N")) {
						System.out.println("Must be Y or N");
					}
				}
				if(answer.equalsIgnoreCase("Y")) {
					result = ((CheckingAccount)this.currentAccount).setOverdraftProtectionFlag(true);
				} else {
					result = ((CheckingAccount)this.currentAccount).setOverdraftProtectionFlag(false);	
				}
			} catch(Exception ex) {
				System.out.println("Invalid operation for current account");
			}
			break;
			
		case 5:
			return;
			
		}
		System.out.println(result.toString());
	}
	public void EditSavings()
	{
		System.out.println("Savings Account Options: ");
		System.out.println("1) Deposit");
		System.out.println("2) Withdrawal");
		System.out.println("3) Adjust Interest Rate");
		System.out.println("4) Recalculate Interest");
		System.out.println("5) Back");
		
		int option = 0;
		while(option == 0) {
			try {
				option = scanner.nextInt();
				if(option < 1 || option > 5) throw new Exception();
			} catch(Exception ex) {
				System.out.println("Invalid option");
				option = 0;
			}
		}
		
		double amount = 0;
		TransactionResult result = null;
		switch(option) {
		case 1: 
			System.out.print("Enter amount to deposit : ");
			
			try {
				amount = scanner.nextDouble();
			}catch(Exception ex) {
				System.out.println("Invalid amount");
				scanner.next();
				return;
			}
			result = this.currentAccount.Deposit(amount);
			
			break;
		case 2:
			System.out.print("Enter amount to withdrawal: ");
			
			try {
				amount = scanner.nextDouble();
			}catch(Exception ex) {
				System.out.println("Invalid amount");
				scanner.next();
				return;
			}
			result = this.currentAccount.Withdrawal(amount);
			break;
		case 3:
			double interestRate = 0;
			System.out.print("Enter new interest rate (3% - 6.5%) : ");
			try {
				interestRate = scanner.nextDouble()/100;
			} catch(Exception ex) {
				System.out.println("Interest Rate must be a double");
				scanner.next();
				return;
			}
			try {
				result = ((SavingsAccount)this.currentAccount).setInterestRate(interestRate);	
			} catch(Exception ex) {
				System.out.println("Invalid operation for current account");
				return;
			}
			break;
			
		case 4:
			try {
				result = ((SavingsAccount)this.currentAccount).CalculateInterest();	
			} catch(Exception ex) {
				System.out.println("Invalid operation for current account");
				return;
			}
			break;
			
		case 5:
			return;
			
		}
		System.out.println(result.toString());
	}
	
	public void MainMenu()
	{
		this.currentAccount = null;
		System.out.println("Welcome to Account Management");
		System.out.println("Select an option: ");
		
		System.out.println("1: Checking");
		System.out.println("2: Savings");
		System.out.println("3: Quit");
		
		int option = 0;
		while(option == 0) 
		{
			try {
				option = scanner.nextInt();
				if(option < 1 || option > 3) throw new Exception();
			} catch(Exception e) {
				System.out.println("Invalid input, please try again");
				option = 0;
			}		
		}
		
		switch(option) {
		
		case 1:
			this.accountType = "Checking";
			break;
		case 2: 
			this.accountType = "Savings";
			break;
		case 3:
			System.out.println("Goodbye");
			this.shouldContinue = false;
			return;
		}
		
		AccountMenu();
	}
	
	public BankAccountDemo() {
		scanner = new Scanner(System.in);
		shouldContinue = true;
	}
	private void Run() {
		while(shouldContinue) {
			MainMenu();
		}
	}
	public static void main(String[] args) {
		
		SavingsAccount sa = new SavingsAccount(12345);
		TransactionResult t = sa.CalculateInterest();
		System.out.println(t.toString());
		
		BankAccountDemo demo = new BankAccountDemo();
		demo.Run();	
	}

}
 
