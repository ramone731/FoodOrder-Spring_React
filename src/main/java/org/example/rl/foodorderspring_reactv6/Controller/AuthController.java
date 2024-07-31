package org.example.rl.foodorderspring_reactv6.Controller;

import org.example.rl.foodorderspring_reactv6.Config.JwtProvider;
import org.example.rl.foodorderspring_reactv6.Model.Cart;
import org.example.rl.foodorderspring_reactv6.Model.CartRepository;
import org.example.rl.foodorderspring_reactv6.Model.USER_ROLE;
import org.example.rl.foodorderspring_reactv6.Model.User;
import org.example.rl.foodorderspring_reactv6.Repository.UserRepository;
import org.example.rl.foodorderspring_reactv6.Request.LoginRequest;
import org.example.rl.foodorderspring_reactv6.Response.AuthResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")//Sometimes IntelliJ cannot resolve if a bean has been declared
    @Autowired
    private CustomerUserDetailsService customerUserDetailsService; //in case of error - suppressed by ide

    @Autowired
    private CartRepository cartRepository;



    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {

        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if(isEmailExist!=null){
            throw new Exception("Duplicate email address found.");
        }

        /*creating new user instance to retrieve email,
        full and password from controller to service (DTO) data transfer obj*/
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(createdUser);

        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getEmail(), user.getFullName());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.set(jwt);
        authResponse.setMessage("Account Active");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }



    //login method + auth implementation
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> singin(@RequestBody LoginRequest req){

        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(username, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        //generate token
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.set(jwt);
        authResponse.setMessage("Account Active");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }



    private Authentication authenticate(String username, String password) {

        //CustomerUserDetailsService customerUserDetailsService = null;
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        //if user cant be detected in db throw exception
        if(userDetails == null){
            throw new BadCredentialsException("Username is not registered, please try again.");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Password entry is incorrect or expired, please try again.");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

    }
}



