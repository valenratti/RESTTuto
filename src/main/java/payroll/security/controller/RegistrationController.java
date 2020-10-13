package payroll.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import payroll.security.exceptions.UserAlreadyExistException;
import payroll.security.dto.UserDto;
import payroll.security.AuthenticationRequest;
import payroll.security.AuthenticationResponse;
import payroll.security.JwtUtil;
import payroll.security.services.MyUserDetailsService;
import payroll.security.services.UserService;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService myUserDetailsService;

    private final JwtUtil jwtTokenUtil;

    @Autowired
    public RegistrationController(UserService userService, AuthenticationManager authenticationManager, MyUserDetailsService myUserDetailsService, JwtUtil jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/register")
    public String userRegistration(@RequestBody @Valid UserDto user, final BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "The information provided is not correct.";
        }
        try {
            userService.registerNewUserAccount(user);
            return "Registered";
        }catch (UserAlreadyExistException e){
            bindingResult.rejectValue("email", "userData.email","An account already exists for this email.");
        }
        return "Not registered";
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(token);
        }
        catch (Exception e){
            return new ResponseEntity<>("Usuario y/o contraseña invalidos", HttpStatus.FORBIDDEN); //403
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);


        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
