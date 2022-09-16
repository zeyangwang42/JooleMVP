package com.example.joolemvp.Controller;

import com.example.joolemvp.Entity.Project;
import com.example.joolemvp.Entity.User;
import com.example.joolemvp.Enums.Role;
import com.example.joolemvp.service.ProjectService;
import com.example.joolemvp.service.UserService;
import com.example.joolemvp.service.impl.MyUserDetailsService;
import com.example.joolemvp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AuthenticationManager myauthenticaitonManager;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private MyUserDetailsService userDetailsService;

    private User getCurrentUser(Principal principal){
        UsernamePasswordAuthenticationToken passwordAuthenticationToken = (UsernamePasswordAuthenticationToken) principal;
        return  service.findByUserName(passwordAuthenticationToken.getName());
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user)
            throws Exception {
        if(!service.volidUser(user)){
            return new ResponseEntity<>("you should enter a user with username and password",HttpStatus.BAD_REQUEST);
        }
        String username = service.getUserName(user);
        String password = service.getPassword(user);
        try {
            myauthenticaitonManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)//User.getUsername(), User.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return  new ResponseEntity<>(jwt, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> list(){
        List<User> users = service.findAll();
        if(users == null){
            return new ResponseEntity<>("this table not inited", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody User user){
        if(!service.volidUser(user)){
            return new ResponseEntity<>("you should enter a user with username and password",HttpStatus.BAD_REQUEST);
        }
        service.createUser(user);
        String password = service.getPassword(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("passwordEncoder = "+passwordEncoder.encode(password));
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @PostMapping("/createAdmin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        if(!service.volidUser(user)){
            return new ResponseEntity<>("you should enter a user with username and password",HttpStatus.BAD_REQUEST);
        }
        service.createAdmin(user);
        String password = service.getPassword(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("passwordEncoder = "+passwordEncoder.encode(password));
        return new ResponseEntity(user,HttpStatus.OK);
    }

    @GetMapping("/findById")
    public ResponseEntity<?> findById(@RequestParam("id")Integer id){
        User user = service.findById(id);
        if(user == null){
            return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/findUsersProject")
    public ResponseEntity<?> findUsersProject(@RequestParam("username")String username){
        User user = service.findByUserName(username);
        if(user == null){
            return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(service.getUserProject(user), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("username") String username){
        User user = service.findByUserName(username);

        if(user==null){
            return new ResponseEntity("This user do not exist",HttpStatus.BAD_REQUEST);
        }

        service.delete(user);
        return new ResponseEntity("The user is deleted",HttpStatus.OK);
    }

    @PutMapping("/updateInformation")
    public ResponseEntity<?> update(Principal principal,
                                    @RequestBody User updateUser){
        User user = getCurrentUser(principal);
        if(!service.update(user,updateUser)){
            return new ResponseEntity("you can not update username",HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(user,HttpStatus.OK);
    }

}
