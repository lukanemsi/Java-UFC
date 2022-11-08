package ge.ufc.webapps.models;

public class User
{
    private int id;
    private String lastname,firstname,personalNumber;
    private double balance;
    public User(){}
    public User(int id, String lastname, String firstname, String personalNumber, double balance) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.personalNumber = personalNumber;
        this.balance = balance;
    }

    public String responseString()
    {
        if(firstname == null || lastname == null)
            return null;
        return Character.toUpperCase(firstname.charAt(0)) + ". " + Character.toUpperCase(lastname.charAt(0)) + ". " + balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}

