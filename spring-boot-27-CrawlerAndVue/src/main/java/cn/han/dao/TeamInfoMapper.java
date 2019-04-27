package cn.han.dao;

import cn.han.model.TeamInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 基于注解实现持久化操作
 *
 */
@Mapper
public interface TeamInfoMapper
{

	@Select("SELECT * FROM t_team WHERE username = #{name}")
	List<TeamInfo> findByName(@Param("name") String name);

	@Select("SELECT * FROM t_team WHERE id = #{id}")
	List<TeamInfo> findById(@Param("id") int id);

	@Insert("INSERT INTO t_team(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insert(TeamInfo ui);

	// 两个语句实现效果一致
	// @Insert("INSERT INTO t_team(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR},#{password,jdbcType=VARCHAR}, #{usertype,jdbcType=VARCHAR},#{enabled,jdbcType=INTEGER}, #{realname,jdbcType=VARCHAR},#{email,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR})")
	@Insert("INSERT INTO t_team(Id,username,password,usertype,enabled,realname,email,tel) VALUES(#{id}, #{username},#{password}, #{usertype},#{enabled}, #{realname},#{email}, #{tel})")
	int insertByMap(Map<String, Object> map);

	@Select("SELECT * FROM t_team WHERE 1=1 ")
	List<TeamInfo> findAll();

	@Update("UPDATE t_team SET password=#{password} WHERE username=#{username}")
	void update(TeamInfo ui);

	@Delete("DELETE FROM t_team WHERE id =#{id}")
	void delete(int id);

	@Results({ @Result(property = "username", column = "username"), @Result(property = "realname", column = "realname") })
	@Select("SELECT username,realname FROM t_team WHERE 1=1")
	List<TeamInfo> queryById();
}
