package in.jdhariwal.expensetrackingapi.service;

import in.jdhariwal.expensetrackingapi.entity.User;
import in.jdhariwal.expensetrackingapi.entity.UserModel;

public interface UserService {
    
    User createUser(UserModel user);

    User readUser();

    User updateUser(UserModel user);

    void deleteUser();

    User getLoggedInUser();
}
