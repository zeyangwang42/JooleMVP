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

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestParam(name="username") String username,
                                                       @RequestParam(name="password") String password)

            throws Exception {

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
    public ResponseEntity<?> create(@RequestParam(value = "firstName",required = false,defaultValue = "default") String firstName,
                                    @RequestParam(value = "lastName", required = false,defaultValue = "default") String lastName,
                                    @RequestParam(value = "email",required = false,defaultValue = "default") String email,
                                    @RequestParam(value = "phone",required = false,defaultValue = "default") String phone,
                                    @RequestParam(value = "username") String username,
                                    @RequestParam(value = "password") String password){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setCreateTime(new Date());
        user.setUserName(username);
        user.setPassword(password);
        user.setRole(Role.USER);
        service.createUser(user);
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
    public ResponseEntity<?> findUsersProject(@RequestParam("id")Integer id){
        User user = service.findById(id);
        if(user == null||user.getProjects()==null){
            return new ResponseEntity<>("this Id is not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user.getProjects(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id){
        User user = service.findById(id);

        if(user==null){
            return new ResponseEntity("This user do not exist",HttpStatus.BAD_REQUEST);
        }

        service.deleteById(id);
        return new ResponseEntity("The user deleted",HttpStatus.OK);
    }
    @PostMapping("/createProject")
    public ResponseEntity<?> createProject(@RequestParam(value = "user") Integer userid,
                                           @RequestParam(value = "projectName",required = false,defaultValue = "default") String projectName,
                                           @RequestParam(value ="projectAddress",required = false,defaultValue = "default") String projectAddress,
                                           @RequestParam(value ="projectSize",required = false,defaultValue = "default") String projectSize,
                                           @RequestParam(value ="clientName",required = false,defaultValue = "default") String clientName) {

        User user = service.findById(userid);
        if(user==null){
            return new ResponseEntity("the given userid not exist",HttpStatus.BAD_REQUEST);
        }
        Project project = new Project();
        project.setUser(user);
        project.setProjectSize(projectSize);
        project.setProjectName(projectName);
        project.setProjectAddress(projectAddress);
        project.setClientName(clientName);




        projectService.createProjectForUser(user,project);

        return new ResponseEntity(project,HttpStatus.OK);
    }
    @PutMapping("/updateInformation")
    public ResponseEntity<?> put(@RequestParam(value = "user") Integer userid,
                                 @RequestParam(value = "firstName",required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName,
                                 @RequestParam(value = "email",required = false) String email,
                                 @RequestParam(value = "phone",required = false) String phone){
        User user = service.findById(userid);
        if(user==null){
            return new ResponseEntity("the given userid not exist",HttpStatus.BAD_REQUEST);
        }
        if(firstName!=null){
            user.setFirstName(firstName);
        }
        if(lastName!=null){
            user.setLastName(lastName);
        }
        if(email!=null){
            user.setEmail(email);
        }
        if(phone!=null){
            user.setPhone(phone);
        }
        return new ResponseEntity(user,HttpStatus.OK);
    }

}
