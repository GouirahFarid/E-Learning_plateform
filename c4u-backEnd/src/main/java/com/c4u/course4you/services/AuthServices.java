package com.c4u.course4you.services;

import com.c4u.course4you.entities.*;
import com.c4u.course4you.exceptions.ResourceNotFoundException;
import com.c4u.course4you.repositories.PersonRepository;
import com.c4u.course4you.repositories.RoleRepository;
import com.c4u.course4you.security.jwt.JwtUtils;
import com.c4u.course4you.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServices {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    PersonRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getPerson().getId(), userDetails.getUsername(), roles, userDetails.getPerson().getCourses()));
    }

    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }
        if (userRepository.existsByMail(signupRequest.getMail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        if (!signupRequest.getPassword().equals(signupRequest.getRepassword()))
            return  ResponseEntity.badRequest().body(new MessageResponse("you must confirme your password"));
        Person person=new Person();
        Role role=null;
        if (signupRequest.getAccountType().equals("student"))
            role=roleRepository.findByRoleName("ROLE_STUDENT").orElseThrow(()->new ResourceNotFoundException("Role not found"));
        if (signupRequest.getAccountType().equals("professor"))
            role= roleRepository.findByRoleName("ROLE_PROFESSOR").orElseThrow(()->new ResourceNotFoundException("Role not found"));
        person.setUsername(signupRequest.getUsername());
        person.setFirstName(signupRequest.getFirstName());
        person.setSecondName(signupRequest.getSecondName());
        person.setMail(signupRequest.getMail());
        person.setPassword(encoder.encode(signupRequest.getPassword()));
        person.setBiography(signupRequest.getBiography());
        person.getRoles().add(role);
        userRepository.save(person);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    public ResponseEntity<?> getPerson(Long personId){
        return new ResponseEntity<>(userRepository.findById(personId), HttpStatus.OK);
    }
    public ResponseEntity<?> getPersonsByRoleName(String roleName){
        return new ResponseEntity<>(userRepository.findByRoles_RoleNameContaining(roleName),HttpStatus.OK);
    }
    public ResponseEntity<?> updatePerson(String firstName,String secondName,String mail,String oldPassword,String newPassword,String biography,Long personId){
       var person=userRepository.findById(personId).orElseThrow(()->new ResourceNotFoundException("person not found"));
        if (!oldPassword.equals("") && !newPassword.equals("") && !oldPassword.equals(newPassword))
            person.setPassword(encoder.encode(newPassword));
        if (!firstName.equals(""))
            person.setFirstName(firstName);
        if (!secondName.equals(""))
            person.setSecondName(secondName);
        if (!mail.equals(""))
            person.setMail(mail);
        if (!biography.equals(""))
            person.setBiography(biography);
        userRepository.save(person);
        return ResponseEntity.ok(new MessageResponse("Person Updated"));
    }
    public ResponseEntity<?> deletePerson(Long personId){
        userRepository.deleteById(personId);
        return ResponseEntity.ok(new MessageResponse("person deleted"));
    }
}
