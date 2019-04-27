package com.han.dao;

import com.han.entity.UserInfo;
import com.han.entity.UserPassword;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 基于注解实现持久化操作
 *
 */
@Mapper
public interface UserPasswordMapper
{

	@Insert("INSERT INTO user_password(userid,password) VALUES(#{userId},#{password})")
	int insert(UserPassword ui);

	@Update("UPDATE user_password SET password=#{password} WHERE username=#{username}")
	void update(UserPassword ui);

	@Select("SELECT * FROM user_password WHERE userid = #{userId}")
	List<UserInfo> findByUserID(@Param("userId") int userId);

	@Delete("DELETE FROM user_password WHERE userid =#{userId}")
	void deleteByUserID(int userId);

}
