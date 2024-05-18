package com.blogapp.services.impl;

import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.UserDto;
import com.blogapp.repositories.UserRepository;
import com.blogapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto) {

        //create User object from User DTO
        User user = dtoToUser(userDto);
        User savedUser = userRepository.save(user);  //save using repo
        
        return userToDto(savedUser);      //return dto object
    }

    @Override
    public UserDto updateuser(UserDto userDto, Integer userId) {

        //gt the user with specific Id
        User user = userRepository.findById(userId).
                    orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        //build user object to update data
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());

        User updatedUser = userRepository.save(user);    //save using repo

        return userToDto(updatedUser);       //return dto object
    }

    @Override
    public UserDto getUserByUserId(Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        return userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        //get all users in users list
        List<User> users = userRepository.findAll();

        //convert all users from list into userDto list
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        userRepository.delete(user);
    }

    //convert User Dto to User Entity
    private User dtoToUser(UserDto userDto){
//        User user = new User();
//        user.setUserId(userDto.getId());
//        user.setUserName(userDto.getUserName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());

        //use model mapper to map object
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    //convert User Entity to User Dto
    private UserDto userToDto(User user){
//        UserDto userDto = UserDto.of()
//                            .id(user.getUserId())
//                            .userName(user.getUserName())
//                            .email(user.getEmail())
//                            .password(user.getPassword())
//                            .about(user.getAbout()).build();

        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
