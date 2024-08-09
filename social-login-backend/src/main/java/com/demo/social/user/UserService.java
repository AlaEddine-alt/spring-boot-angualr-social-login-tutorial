package com.demo.social.user;

import com.demo.social.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;


    public User findUserById(UUID id){
        return userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(NotFoundException.NotFoundExceptionType.USER_NOT_FOUND)
        );
    }
}
