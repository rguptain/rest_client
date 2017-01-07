public class BanJson 
{
	public Status status;
	public CustomerInformation customerInfo;
	public AccountDetail accountDetail;
	
	 @Override
	 public String toString() 
	 {
		 return status + " - " + customerInfo + " (" + accountDetail + ")";
	 }
}
