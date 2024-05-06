package com.ra.service;

import com.ra.exception.UserException;
import com.ra.model.dto.request.UserRequestDTO;
import com.ra.model.dto.response.UserResponseDTO;
import com.ra.model.entity.User;



public interface UserService {
    User register(User user);
    UserResponseDTO login (UserRequestDTO userRequestDTO);

    User findById(Long id) throws UserException;

    User findUserProfileByJwt(String jwt) throws UserException;
}
