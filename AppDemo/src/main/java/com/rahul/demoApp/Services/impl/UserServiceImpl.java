package com.rahul.demoApp.Services.impl;

import com.rahul.demoApp.Entity.User;
import com.rahul.demoApp.Payload.UserDto;
import com.rahul.demoApp.Payload.UserResponse;
import com.rahul.demoApp.Repositories.UserRepo;
import com.rahul.demoApp.Services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User save = this.userRepo.save(user);
        return this.modelMapper.map(save, UserDto.class);
    }

    @Override
    public UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<User> userPage = userRepo.findAll(pageable);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtos = userList.stream()
                .map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return UserResponse.builder()
                .content(userDtos)
                .pageNumber(userPage.getNumber())
                .pageSize(userPage.getSize())
                .totalElements(userPage.getTotalElements())
                .totalPages(userPage.getTotalPages())
                .lastPage(userPage.isLast())
                .build();
    }

    @Override
    public UserResponse searchUser(String firstName, Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<User> userPage = userRepo.findByFirstNameContaining(firstName, pageable);
        List<User> userList = userPage.getContent();
        List<UserDto> userDtoList = userList.stream().map(user -> this.modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        return UserResponse.builder()
                                .content(userDtoList)
                .pageNumber(userPage.getNumber())
                .pageSize(userPage.getSize())
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .lastPage(userPage.isLast())
                .build();
    }
}
