package ge.ufc.webapps.models;

public class User
{

    private final String lastname;
    private final String firstname;
    private final double balance;
    public User(String lastname, String firstname, double balance) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.balance = balance;
    }

    public String getLastname() {
        return lastname;
    }
    public String getFirstname() {
        return firstname;
    }
    public double getBalance() {
        return balance;
    }


}

