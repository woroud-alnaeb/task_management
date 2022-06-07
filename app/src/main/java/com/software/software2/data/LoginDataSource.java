package com.software.software2.data;

import com.software.software2.data.model.UserModel;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<UserModel> login(String username, String password) {
      if(username.equals("user") && password.equals("user")) {
          try {
              UserModel fakeUser =
                      new UserModel(
                              java.util.UUID.randomUUID().toString(),
                              "Jane Doe");
              return new Result.Success<>(fakeUser);
          } catch (Exception e) {
              return new Result.Error(new IOException("Error logging in", e));
          }
      }else {
          return new Result.Error(new IOException("Invalid username or password"));

      }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}