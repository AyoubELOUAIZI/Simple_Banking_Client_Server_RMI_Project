package estm.dsic.jee;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import estm.dsic.jee.Models.User;
import estm.dsic.jee.services.AuthenticationService;

public class Main {
    public final static int AMOUNT = 10;

    public static void main(String[] args) {
        try {
            // Connect to the AuthenticationService
            String authUrl = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/AuthenticationService";
            AuthenticationService authenticationService = (AuthenticationService) Naming.lookup(authUrl);

            // Authenticate the user
            User user = authenticationService.authenticateUser("user1", "password1");
            if (user != null) {
                System.out.println("Authentication successful for user: " + user);
                
                // Proceed with accessing the BankAccount
                String bankAccountUrl = "rmi://" + InetAddress.getLocalHost().getHostAddress() + ":52369/BankAccount";
                BankAccount bankAccount = (BankAccount) Naming.lookup(bankAccountUrl);
                
                // Perform operations on the BankAccount
                System.out.println("Current amount in the bank account: " + bankAccount.getBalance());
                System.out.println("Deposit --> " + AMOUNT);
                bankAccount.deposit(AMOUNT);
                System.out.println("Amount after the deposit: " + bankAccount.getBalance());
                bankAccount.withdraw(3);
                System.out.println("Withdraw ....");
                System.out.println("Amount after the withdraw: " + bankAccount.getBalance());
            } else {
                System.out.println("Authentication failed. Please check your username and password.");
            }
        } catch (UnknownHostException | NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
            System.out.println("Error while trying to access the server remotely ");
        }
    }
}
