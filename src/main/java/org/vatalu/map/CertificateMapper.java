package org.vatalu.map;

import org.apache.ibatis.annotations.*;
import org.vatalu.model.entity.Certificate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Mapper
public interface CertificateMapper {
    @Select("select * from certificateTbl LIMIT #{start},#{num}  ORDER BY date DESC")
    public List<Certificate> findCertificates(@Param("start") Integer start, @Param("num") Integer num);

    @Insert("insert into certificateTbl(number,email,address,student_Id,isjudged,date)" +
            "values(" +
            "#{number,jdbcType=CHAR}," +
            "#{email,jdbcType=VARCHAR}," +
            "#{address,jdbcType=VARCHAR}," +
            "#{student_Id,jdbcType=CHAR}," +
            "#{isjudged,jdbcType=CHAR}," +
            "#{date,jdbcType=TIMESTAMP  }" +
            ")")
    public int insert(@Param("number") String number,
                      @Param("email") String email,
                      @Param("address") String address,
                      @Param("student_Id") String studentId,
                      @Param("isjudged") String isjudged,
                      @Param("date") Timestamp date);

    @Update("update certificateTbl set isjudged = #{param2} where id =#{param1}")
    public int updateById(@Param("param1") Long id, @Param("param2") String isjudged);
}
