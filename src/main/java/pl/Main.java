package pl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try(Connection connection = DbUtil.connect("workshop2")){
            System.out.println("Udało się połączyć z bazą danych");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Bład połączenia z bazą danych!");
        }

        UserDao userDao = new UserDao();
        /*User mateusz = new User("maly316@vp.pl", "maly316", "123qwertyuiop");
        userDao.createUser(mateusz);

        User paulina = new User("paulinatomalpa@onet.pl", "koza", "kozojqa123");
        userDao.createUser(paulina);

        User lukasz = new User("lukaszmaproblem@interia.pl", "talibek", "talibeqwerty");
        userDao.createUser(lukasz);

        User natec = new User("natecpolska@o2.pl", "natecpolska", "1234567uY");
        userDao.createUser(natec);

        User michal = new User("michaliveco@gmail.com", "michalj", "mlodym1qw3");
        userDao.createUser(michal);*/

       //test metody readOneUser dla id znajdujacego sie w tabeli user
        System.out.println(userDao.readOneUser(3));
        //test metody readOneUser dla id, którego nie ma w tabeli user
        System.out.println(userDao.readOneUser(20));


        User updateUser = userDao.readOneUser(5);
        updateUser.setEmail("zmienionyemail@vp.pl");
        updateUser.setUserName("zmienionyusername");
        updateUser.setPassword("123453");
        userDao.update(updateUser);


        //Testowanie metody deleteUser
        userDao.deleteUser(3);

        userDao.printTab(userDao.readAllUsers());
    }
}
