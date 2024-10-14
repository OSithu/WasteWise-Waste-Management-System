package interfaces;

import java.util.List;
import model.User;

public interface IUserDAO {
    // Retrieves all users from database
    public List<User> selectAllUsers();

    // Retrieves a specific user based on their ID
    public User selectUser(int userID);

    // Inserts a new user record into the database
    public void insertUser(User user);

    // Updates an existing user record in the database
    public void updateUser(User user);

    // Deletes a user record from the database based on their ID
    public void deleteUser(int userID);
}
