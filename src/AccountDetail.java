public class AccountDetail 
{
	public String accountNumber;
	public String accountStatus;
	public String accountType;
	public String accountSubType;
	
	 @Override
	 public String toString() 
	 {
		 return accountNumber + " - " + accountStatus + " (" + accountType + ")";
	 }
}
