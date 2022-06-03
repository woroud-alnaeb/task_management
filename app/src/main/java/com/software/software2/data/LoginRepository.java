package com.software.software2.data;

import com.software.software2.data.model.UserModel;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;
    private UserModel user = null;

    public LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }


    private void setLoggedInUser(UserModel user) {
        this.user = user;
    }

    public Result<UserModel> login(String username, String password) {
        // handle login
        Result<UserModel> result = dataSource.login(username, password);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<UserModel>) result).getData());
        }
        return result;
    }
}