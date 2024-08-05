package DAO;

import Util.ConnectionUtil;
import Model.Account;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class SocialMediaDao {

    public Account addAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account(username, password) VALUES (?,?)";
            
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            
            if(pkeyResultSet.next()){
                int accountPK = (int) pkeyResultSet.getLong(1);
                return new Account(accountPK, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Account fetchedAccount = new Account(rs.getInt("account_id"), rs.getString("username"), rs.getString("password"));
                return fetchedAccount;
            }
        }catch(SQLException e){
        System.out.println(e.getMessage());
        }
        return null;
    }
}
