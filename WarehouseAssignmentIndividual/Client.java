import java.io.*;
import java.io.FileNotFoundException;
import java.util.*;
//Inports the linked list java class
import java.util.LinkedList;

public class Client {
    // Declare all parameters of Client
    private String FirstName;
    private String LastName;
    private String Email;
    private String ClientID;
    private String Address;
    private String PhoneNumber;

    //Constructor
    public Client(String FirstName, String LastName, String Email, String ClientID, String Address, String PhoneNumber) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Email = Email;
        this.ClientID = ClientID;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }

    // Get functions for the parameters
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getClientID() {
        return ClientID;
    }

    public String getAddress() {
        return Address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }
    // Combine all parameters into one string
    @Override
    public String toString() {
        return "Client{" +
                "First Name:'" + FirstName + '\'' +
                ", Last Name:'" + LastName + '\'' +
                ", Email:'" + Email + '\'' +
                ", Client ID:'" + ClientID + '\'' +
                ", Address:'" + Address + '\'' +
                ", Phone Number:'" + PhoneNumber + '\'' +
                '}';

 }
}
 
 