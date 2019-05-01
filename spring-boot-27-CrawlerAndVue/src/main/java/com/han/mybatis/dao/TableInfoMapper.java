package com.han.mybatis.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 基于Mybatis注解自定义查询
 *
 */
@Mapper
public interface TableInfoMapper
{

	/**
	 * 使用注解构造复杂的自定义语句
	 *
	 *      <script></script>
	 *      <if></if>
	 * @param map  查询参数
	 * @return
	 */
	@Select("<script>select team.*, "
			        + "(select count(*) from t_picture where team_id = team.id ) picture_num,"
			        + "(select count(*) from t_video where team_id = team.id)   video_num , "
			        + "(select count(*) from t_picture where team_id = team.id and path is null) picture_woking_num,"
			        + "(select count(*) from t_video where team_id = team.id and path is null)   video_woking_num,  "
			        + "CASE (select count(*) from t_picture where team_id = team.id and path is null) +(select count(*) from t_video where team_id = team.id and path is null) when 0 then 'success' ELSE 'working' END status "
			        + "from t_team team where 1=1"
			        + " <if test=\"name != null\">AND team.name like  CONCAT('%',#{name},'%') </if>"
			        + "</script>")
	List<Map<String,Object>> query(Map<String, Object> map);
}
