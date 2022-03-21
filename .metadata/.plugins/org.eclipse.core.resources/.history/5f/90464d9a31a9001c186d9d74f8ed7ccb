package com.carpetaciudadana.users.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carpetaciudadana.users.domain.Users;
import com.carpetaciudadana.users.dto.UserDTO;
import com.carpetaciudadana.users.mapper.UserMapper;
import com.carpetaciudadana.users.service.UsersService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	UsersService usersService;

	@Autowired
	UserMapper userMapper;

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws Exception {
//		cbFactory.create("users").run(() -> usersService.deleteById(id));
		usersService.deleteById(id);

	}

	@PutMapping
	public UserDTO update(@Valid @RequestBody UserDTO userDTO) throws Exception {
		Users user = userMapper.userDTOtoUser(userDTO);
		user = usersService.update(user);
		userDTO = userMapper.userstoUserDTO(user);

		return userDTO;
	}

	@PostMapping
	public UserDTO save(@Valid @RequestBody UserDTO userDTO) throws Exception {
		Users user = userMapper.userDTOtoUser(userDTO);
		user = usersService.save(user);
		userDTO = userMapper.userstoUserDTO(user);
		return userDTO;
	}

	@GetMapping("/{id}")
	public UserDTO findById(@PathVariable("id") String id) throws Exception {
		Users user = (usersService.findById(id).isPresent() == true) ? usersService.findById(id).get() : null;
		UserDTO userDTO = userMapper.userstoUserDTO(user);
		return userDTO;
	}

	@GetMapping
	public List<UserDTO> findAll() throws Exception {
		List<Users> users = usersService.findAll();
		List<UserDTO> userDTOs = userMapper.usersListTouserDTOList(users);

		return userDTOs;
	}

}
