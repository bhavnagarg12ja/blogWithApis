package com.codeWithBhavna.blogWithApis.services.Impl;

import com.codeWithBhavna.blogWithApis.entities.UserEntity;
import com.codeWithBhavna.blogWithApis.exceptions.ResourceNotFoundException;
import com.codeWithBhavna.blogWithApis.payloads.UserDto;
import com.codeWithBhavna.blogWithApis.repositories.UserRepo;
import com.codeWithBhavna.blogWithApis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
//    UserDto bcoz we will create user from entity
    public UserDto createUser(UserDto userDto) {
        System.out.println("METHOD START ============== UserServiceImpl.createUser=================");
        UserEntity savedUserEntity = new UserEntity();
        try{
            UserEntity userEntity = this.dtoToUser(userDto);
            savedUserEntity  = userRepo.save(userEntity);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }

        System.out.println("METHOD END ============== UserServiceImpl.createUser=================");
        return this.userToDto( savedUserEntity);

    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        System.out.println("METHOD START ============== UserServiceImpl.updateUser=================");
        UserDto userDto1 = new UserDto();
        try{
             //   finding the user by id
            UserEntity userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", userId));
            //   for saving the updated user into the entity
            userEntity.setName(userDto.getName());
            userEntity.setEmail(userDto.getEmail());
            userEntity.setPassword(userDto.getPassword());
            userEntity.setAbout(userDto.getAbout());

            UserEntity updatedUser = this.userRepo.save(userEntity);
             userDto1 = this.userToDto(updatedUser);
        }catch (Exception exception){
            System.out.println(exception.getMessage());
        }
        System.out.println("METHOD END ============== UserServiceImpl.updateUser=================");

        return  userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        System.out.println("METHOD START ============== UserServiceImpl.getUserById=================");
        UserEntity userEntity = new UserEntity();
        try {
            userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", userId));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        System.out.println("METHOD END ============== UserServiceImpl.getUserById=================");
        return this.userToDto(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = this.userRepo.findAll();
        List<UserDto> userDtos = users.stream().map(userEntity -> this.userToDto(userEntity)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        UserEntity userEntity = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("UserEntity", "Id", userId));
        this.userRepo.delete(userEntity);
    }

    //dtoToUser fun is used to convert dataobject into entity
    private UserEntity dtoToUser(UserDto userDto) {
        System.out.println("METHOD START ============== UserServiceImpl.dtoToUser=================");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setAbout(userDto.getAbout());
        System.out.println("METHOD END ============== UserServiceImpl.dtoToUser=================");

        return userEntity;
    }

    //userToDto fun is used to convert entity into dataobject
    public UserDto userToDto(UserEntity userEntity) {
        System.out.println("METHOD START ============== UserServiceImpl.userToDto=================");

        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setAbout(userEntity.getAbout());
        System.out.println("METHOD END ============== UserServiceImpl.userToDto=================");

        return userDto;
    }

}
