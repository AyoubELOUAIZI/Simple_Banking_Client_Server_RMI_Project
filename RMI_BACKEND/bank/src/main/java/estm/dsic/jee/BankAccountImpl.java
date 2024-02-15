package estm.dsic.jee;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankAccountImpl extends UnicastRemoteObject implements BankAccount{

    protected BankAccountImpl() throws RemoteException {
        super();
        //TODO Auto-generated constructor stub
    }

    private double balance = 40; // Initial balance set to 50

    @Override
    public void deposit(int amount) throws RemoteException {
        // Check if the amount is positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        // Increase the balance by the deposit amount
        balance += amount; 
    }

    @Override
    public void withdraw(int amount) throws RemoteException {
        // Check if the amount is positive
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        // Check if the balance is sufficient for withdrawal
        if (balance < amount) {
            throw new IllegalStateException("Insufficient funds");
        }
        // Decrease the balance by the withdrawal amount
        balance -= amount;
    }

    @Override
    public double getBalance() throws RemoteException {
        // Return the current balance
        return balance;
    }


}
