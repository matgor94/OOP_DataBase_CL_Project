package pl;

import java.sql.*;
import java.util.Arrays;

public class UserDao extends User {
    //Zapytania:
    private static final String CREATE_USER_QUERY = "INSERT INTO user(email, username, password) VALUES (?,?,?)";
    private static final String READ_USER_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String UPDATE_USER_QUERY = "UPDATE user SET email = ?, username = ?, password = ? WHERE id = ?;";
    private static final String DELETE_USER_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String READ_ALL_USERS_QUERY = "SELECT * FROM user";


    public UserDao(){
    }

    public UserDao(String email, String userName, String password){
        super(email, userName, password);
    }

    public User createUser(User user){
        try(Connection conn = DbUtil.connect("workshop2")) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
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

    public User readOneUser(int id){
        try(Connection connection = DbUtil.connect("workshop2")){
            PreparedStatement statement = connection.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setUserName(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user){
        try (Connection connection = DbUtil.connect("workshop2")){
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();
            } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteUser(int id){
        try(Connection connection = DbUtil.connect("workshop2")){
            PreparedStatement statement = connection.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public User [] readAllUsers(){
        User[] arrayOfUsers = new User[0];
        try(Connection connection = DbUtil.connect("workshop2")){
            PreparedStatement statement = connection.prepareStatement(READ_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                User newUser = new User();
                newUser.setEmail(resultSet.getString("email"));
                newUser.setUserName(resultSet.getString("username"));
                newUser.setPassword(resultSet.getString("password"));
                newUser.setId(resultSet.getInt("id"));
                User [] temp = Arrays.copyOf(arrayOfUsers, arrayOfUsers.length + 1);
                temp[temp.length -1] = newUser;
                arrayOfUsers = temp;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return arrayOfUsers;
    }

    public void printTab(User tab[]){
        for (User item : tab){
            System.out.println(item);
        }
    }
}
