package org.vatalu.map;

import org.apache.ibatis.annotations.*;
import org.vatalu.model.entity.CommonUser;
import org.vatalu.model.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from adminTbl where username=#{userName}")
    public User findByName(@Param("userName") String userName);

    @Select("select id,username,level from adminTbl LIMIT #{start},#{size}")
    public List<CommonUser> findUsers(@Param("start") Integer start, @Param("size") Integer size);

    @Insert("insert into adminTbl(username,password,salt,level) " +
            "values(" +
            "#{username,jdbcType=VARCHAR}," +
            "#{password,jdbcType=VARCHAR}," +
            "#{salt,jdbcType=CHAR}," +
            "#{level,jdbcType=CHAR})")
    public int insert(@Param("username") String userName,
                      @Param("password") String password,
                      @Param("salt") String salt,
                      @Param("level") String level);

    @Delete("delete from adminTbl where username = #{username}")
    public int deleteByName(@Param("username") String userName);

    @Update("update adminTbl set password = #{password},salt = #{salt} where username =#{username}")
    public int updatePasswordByName(@Param("username") String userName,
                                    @Param("password") String password,
                                    @Param("salt") String salt);

}
