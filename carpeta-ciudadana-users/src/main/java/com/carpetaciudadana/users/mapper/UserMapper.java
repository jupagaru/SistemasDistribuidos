package com.carpetaciudadana.users.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.carpetaciudadana.users.domain.Users;
import com.carpetaciudadana.users.dto.UserDTO;

@Mapper
public interface UserMapper {

	public UserDTO userstoUserDTO(Users users);

	public Users userDTOtoUser(UserDTO userDTO);

	public List<UserDTO> usersListTouserDTOList(List<Users> users);

	public List<Users> userDTOListToUserList(List<UserDTO> userDTOs);
}
