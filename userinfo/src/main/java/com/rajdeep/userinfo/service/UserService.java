package com.rajdeep.userinfo.service;


import com.rajdeep.userinfo.dto.UserDTO;
import com.rajdeep.userinfo.entity.User;
import com.rajdeep.userinfo.mapper.UserMapper;
import com.rajdeep.userinfo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserDTO addUser(UserDTO userDTO) {
        User savedUser = userRepo.save(UserMapper.INSTANCE.mapUserDTOtoUser(userDTO));
        return UserMapper.INSTANCE.mapUsertoUserDTO(savedUser);
    }

    public ResponseEntity<UserDTO> fetchUserDetailsById(Integer id) {
        Optional<User> fetchedUser = userRepo.findById(id);
        if(fetchedUser.isPresent()){
            return new ResponseEntity<>(UserMapper.INSTANCE.mapUsertoUserDTO(fetchedUser.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
