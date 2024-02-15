package estm.dsic.jee.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import estm.dsic.jee.DataAccessLayer.AccountDAL;

public class AccountServiceImpl extends UnicastRemoteObject implements AccountService {
    private AccountDAL accountDAL;

    public AccountServiceImpl(AccountDAL accountDAL) throws RemoteException {
        super();
        this.accountDAL = accountDAL;
    }

    @Override
    public double checkBalance(int userId) throws RemoteException {
        return accountDAL.checkBalance(userId);
    }

    @Override
    public boolean deposit(int userId, double amount) throws RemoteException {
       return accountDAL.deposit(userId, amount);
    }

    @Override
    public boolean withdraw(int userId, double amount) throws RemoteException {
        return accountDAL.withdraw(userId, amount);
    }

    @Override
    public boolean transfer(int senderUserId, String receiverUsername, double amount) throws RemoteException {
        // You need to implement this method according to your business logic
        return true;
    }
}
