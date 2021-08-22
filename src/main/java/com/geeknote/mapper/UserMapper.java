package com.geeknote.mapper;

import com.geeknote.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

	boolean insert(User user) ;

	List<User> findAll();

	User findByName(@Param("name") String name);

	List<User> findByParam(User user);
}
