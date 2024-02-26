package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import model.AuthData;
import model.UserData;

import java.util.UUID;

public class UserService {

    UserDAO userDAO;
    AuthDAO authDAO;

    public UserService(UserDAO userDAO, AuthDAO authDAO) {
        this.userDAO = userDAO;
        this.authDAO = authDAO;
    }

    public AuthData createUser(UserData userData) throws DataAccessException {
        userDAO.createUser(userData);
        String authToken = UUID.randomUUID().toString();
        AuthData authData = new AuthData(userData.username(), authToken);
        authDAO.addAuth(authData);

        return authData;
    }

    // throws DataAccessException if the username does not exist or the password is incorrect
    public AuthData loginUser(UserData userData) throws DataAccessException {
        boolean userAuthenticated = userDAO.authenticateUser(userData.username(), userData.password());

        if (userAuthenticated) {
            String authToken = UUID.randomUUID().toString();
            return new AuthData(userData.username(), authToken);
        }
        else {
            throw new DataAccessException("Password is incorrect");
        }
    }

    public void logoutUser(String authToken) throws DataAccessException {
        authDAO.getAuth(authToken); // Exception will be thrown if the auth is not valid
        authDAO.deleteAuth(authToken);
    }


    public void clear() {
        userDAO.clear();
        authDAO.clear();
    }
}
