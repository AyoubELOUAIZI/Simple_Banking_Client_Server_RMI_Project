package estm.dsic.jee.DataAccessLayer;

import java.rmi.RemoteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAL  {
  
    
   
    public double checkBalance(int userId) throws RemoteException {
        double balance = 0.0;
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT balance FROM account WHERE user_id = ?")) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                balance = resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

   
    public boolean deposit(int userId, double amount) throws RemoteException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET balance = balance + ? WHERE user_id = ?")) {
            statement.setDouble(1, amount);
            statement.setInt(2, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if at least one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

  
    public boolean withdraw(int userId, double amount) throws RemoteException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE account SET balance = balance - ? WHERE user_id = ?")) {
            statement.setDouble(1, amount);
            statement.setInt(2, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Returns true if at least one row was affected
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

   
    public boolean transfer(int senderUserId, String receiverUsername, double amount) throws RemoteException {
        // You need to implement this method according to your business logic
        return true;
    }
}
