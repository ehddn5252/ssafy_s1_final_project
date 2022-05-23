package com.ssafy.vue.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.mapper.UserMapper;
import com.ssafy.vue.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	

	@Override
	public int idCheck(String id) throws Exception {
		return userMapper.idCheck(id);
	}
	
	@Override
	public int register(UserDto userDto) throws Exception {
		return userMapper.register(userDto);
	}

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		return userMapper.login(userDto);
	}

	@Override
	public int updateUser(UserDto userDto) throws Exception {
		return userMapper.updateUser(userDto);
	}
	

	@Override
	public void deleteUser(String id) throws Exception {
		userMapper.deleteUser(id);
	}

	
	 @Override 
	 public UserDto searchById(String id) throws Exception { 
		 return userMapper.searchById(id); 
	 }
	 
	

	@Override
	public String searchPwdById(UserDto userDto) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.searchPwdById(userDto);
	}


}
