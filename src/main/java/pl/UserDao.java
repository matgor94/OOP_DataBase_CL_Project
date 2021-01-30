package pl;

import java.sql.*;

public class UserDao extends User {
    //Zapytania:
    private static final String CREATE_USER_QUERY = "INSERT INTO user(email, username, password) VALUES (?,?,?)";
    private static final String READ_USER_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE users SET email = ?, username = ?, password = ? WHERE id = ?";
    private static final String DELETE_USER_QUERY = "DELETE FROM users WHERE id = ?";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM users";


    public UserDao(){
    }

    public UserDao(String email, String userName, String password){
        super(email, userName, password);
    }

    public User createUser(User user){
        try(Connection conn = DbUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashCode(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
