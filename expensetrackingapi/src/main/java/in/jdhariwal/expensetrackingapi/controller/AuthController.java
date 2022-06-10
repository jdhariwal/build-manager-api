package in.jdhariwal.expensetrackingapi.controller;

import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.jdhariwal.expensetrackingapi.entity.AuthModel;
import in.jdhariwal.expensetrackingapi.entity.User;
import in.jdhariwal.expensetrackingapi.entity.UserModel;
import in.jdhariwal.expensetrackingapi.service.UserService;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody AuthModel model){
       org.springframework.security.core.Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(model.getEmail(), model.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(auth);
       return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }
}
