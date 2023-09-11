public class AccountHolder {
    //user name
    private String userName;

    private String passWord;



    //    annualInterestRate	static / double
    private double interestRate=0.04;
    //    balance	double. Balances cannot start off negative! Include an error message if this is the situation.
    private double Balance;






    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }
//*Method Name	Method (Instance or Static)	Argument	Return Type
//    AccountHolder	Constructor	double	none
//    deposit	Instance	double	void
//    withdrawal	Instance	double	void
//    monthlyInterest	Instance	void	void
}
