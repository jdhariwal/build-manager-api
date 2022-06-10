package in.jdhariwal.expensetrackingapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.jdhariwal.expensetrackingapi.entity.User;
import in.jdhariwal.expensetrackingapi.entity.UserModel;
import in.jdhariwal.expensetrackingapi.exceptions.ItemAlreadyExistsException;
import in.jdhariwal.expensetrackingapi.exceptions.ResourceNotFoundException;
import in.jdhariwal.expensetrackingapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    UserRepository userRepo;

    @Override
    public User createUser(UserModel user) {
        if(userRepo.existsByEmail(user.getEmail())){
            throw new ItemAlreadyExistsException("User is already registered with email" + user.getEmail());
        }

        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(bcryptEncoder.encode(newUser.getPassword()));
        return userRepo.save(newUser);
    }

    @Override
    public User readUser() {
       Long userId = getLoggedInUser().getId();
       return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found."));
    }

    @Override
    public User updateUser(UserModel user) {
        User existingUser = readUser();
        existingUser.setName(user.getName() != null ? user.getName() : existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail() : existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? bcryptEncoder.encode(user.getPassword()) : existingUser.getPassword());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
        return userRepo.save(existingUser);
    } 

    @Override
    public void deleteUser() {
        User existingUser = readUser();
        userRepo.delete(existingUser);
    }

    @Override
    public User getLoggedInUser() {
       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       String email = auth.getName();
       return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
    
}
