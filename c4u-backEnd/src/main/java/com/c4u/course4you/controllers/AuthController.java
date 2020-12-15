package com.c4u.course4you.controllers;

import com.c4u.course4you.entities.LoginRequest;
import com.c4u.course4you.entities.SignupRequest;
import com.c4u.course4you.services.AuthServices;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/")
public class AuthController {
    private  final AuthServices authServices;

    public AuthController(AuthServices authServices) {
        this.authServices = authServices;
    }
    @PostMapping("signin")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authServices.authenticateUser(loginRequest);
    }
    @PostMapping("signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authServices.registerUser(signUpRequest);
    }
    @PostMapping("persons")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR','ROLE_STUDENT')")
    public  ResponseEntity<?> getPerson(@RequestParam("personId") Long personId){
        return authServices.getPerson(personId);
    }
    @PutMapping("persons")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getPersonsByRoleName(@RequestParam("roleName") String roleName){
        return  authServices.getPersonsByRoleName(roleName);
    }
    @PatchMapping("persons")
    @PreAuthorize("isAuthenticated()")
    public  ResponseEntity<?> updatePerson(@RequestParam("firstName") String firstName,@RequestParam("secondName") String secondName,
                                           @RequestParam("mail") String mail,@RequestParam("oldPassword") String oldPassword,
                                           @RequestParam("newPassword") String newPassword,@RequestParam("biography") String biography,
                                           @RequestParam("personId") Long personId){
        return authServices.updatePerson(firstName,secondName,mail,oldPassword,newPassword,biography,personId);

    }
    @PostMapping("persons/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<?> deletePerson(@RequestParam("personId") Long personId){
        return  authServices.deletePerson(personId);
    }
}
