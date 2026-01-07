package SpaServices;

import Users.Role;
import Users.User;

public class Customer extends User{
    private String phoneNumber;
    private String email;
    
    public Customer(String name, String phoneNumber, String email){
        super(name, Role.CUSTOMER);
        this.phoneNumber = phoneNumber;
        this.email = email;
        
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getEmail(){
        return email;
    }
}
