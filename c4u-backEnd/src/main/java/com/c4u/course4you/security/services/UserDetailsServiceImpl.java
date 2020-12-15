package com.c4u.course4you.security.services;

import com.c4u.course4you.entities.Person;
import com.c4u.course4you.repositories.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private  final PersonRepository personRepository;

    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person=personRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Person with username: "+username+" not found"));
        return new UserDetailsImpl(person);
    }
}
