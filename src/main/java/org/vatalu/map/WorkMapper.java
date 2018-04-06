package org.vatalu.map;

import org.apache.ibatis.annotations.*;
import org.vatalu.model.entity.Work;

import java.util.List;

@Mapper
public interface WorkMapper {

    @Select("<script>" +
            "select * from workTbl where year=#{param1}" +
            "<if test='param3 != \"\" '>" +
            "and workname = #{param3}" +
            "</if>" +
            "<if test='param5 != \"\" and param6 != \"\" and param7 !=\"\" '>" +
            "and (student1 in (#{param5},#{param6},#{param7}) or" +
            "student2 in" +
            "(#{param5},#{param6},#{param7}) or" +
            "student3 in" +
            "(#{param5},#{param6},#{param7}))\n" +
            "</if>" +
            "<if test='param2 != \"\" '>" +
            "and number = #{param2}" +
            "</if>" +
            "<if test=' param4 !=\"\"'>" +
            "and school = #{param4}" +
            "</if>" +
            "</script>")
    public List<Work> findWork(@Param("param1") int year, @Param("param2") String number,
                         @Param("param3") String workName, @Param("param4") String school,
                         @Param("param5") String student1, @Param("param6") String student2,
                         @Param("param7") String student3);

    @Select("select * from workTbl LIMIT #{start},#{num}")
    public List<Work> findWorks(@Param("start") int start,
                                @Param("num") int num);

    @Select("select * from workTbl where number = #{number}")
    public Work findByNumber(@Param("number") String number);

    @Insert("<script>" +
            "insert into workTbl(" +
            "year,month," +
            "area,level,school," +
            "number,work_id,work_name," +
            "student1,student1_Id," +
            "student2,student2_id," +
            "student3,student3_Id," +
            "teacher1,teacher1_Id," +
            "teacher2,teacher2_Id) values" +
            "<foreach collection=\"list\" item=\"item\" index=\"index\" separator=\",\">" +
            "(#{item.year,jdbcType=INTEGER},#{item.month,jdbcType=INTEGER}," +
            "#{item.area,jdbcType=VARCHAR},#{item.level,jdbcType=CHAR},#{item.school,jdbcType=VARCHAR}," +
            "#{item.number,jdbcType=CHAR},#{item.workid,jdbcType=CHAR},#{item.workname,jdbcType=VARCHAR}," +
            "#{item.student1,jdbcType=VARCHAR},#{item.student1Id,jdbcType=CHAR}," +
            "#{item.student2,jdbcType=VARCHAR},#{item.student2Id,jdbcType=CHAR}," +
            "#{item.student3,jdbcType=VARCHAR},#{item.student3Id,jdbcType=CHAR}," +
            "#{item.teacher1,jdbcType=VARCHAR},#{item.teacher1Id,jdbcType=CHAR}," +
            "#{item.teacher2,jdbcType=VARCHAR},#{item.teacher2Id,jdbcType=CHAR})" +
            "</foreach>" +
            "</script>")
    public int insertWorks(@Param("list") List<Work> works);

    @Insert("insert into workTbl(" +
            "year,month," +
            "area,level,school," +
            "number,work_id,work_name," +
            "student1,student1_Id," +
            "student2,student2_id," +
            "student3,student3_Id," +
            "teacher1,teacher1_Id," +
            "teacher2,teacher2_Id) values" +
            "(#{year,jdbcType=INTEGER},#{month,jdbcType=INTEGER}," +
            "#{area,jdbcType=VARCHAR},#{level,jdbcType=CHAR},#{school,jdbcType=VARCHAR}," +
            "#{number,jdbcType=CHAR},#{workid,jdbcType=VARCHAR},#{workname,jdbcType=VARCHAR}," +
            "#{student1,jdbcType=VARCHAR},#{student1Id,jdbcType=CHAR}," +
            "#{student2,jdbcType=VARCHAR},#{student2Id,jdbcType=CHAR}," +
            "#{student3,jdbcType=VARCHAR},#{student3Id,jdbcType=CHAR}," +
            "#{teacher1,jdbcType=VARCHAR},#{teacher1Id,jdbcType=CHAR}," +
            "#{teacher2,jdbcType=VARCHAR},#{teacher2Id,jdbcType=CHAR})")
    public int insert(Work work);

    @Update("update workTbl" +
            "set" +
            "year=#{year,jdbcType=INTEGER},month=#{month,jdbcType=INTEGER}," +
            "area=#{area,jdbcType=VARCHAR},level=#{level,jdbcType=VARCHAR},school=#{school,jdbcType=VARCHAR}," +
            "work_id=#{workid,jdbcType=VARCHAR},work_name=#{workname,jdbcType=VARCHAR}," +
            "student1=#{student1,jdbcType=VARCHAR},student1_Id=#{student1Id,jdbcType=CHAR}," +
            "student2=#{student2,jdbcType=VARCHAR},student2_Id=#{student2Id,jdbcType=CHAR}," +
            "student3=#{student3,jdbcType=VARCHAR},student3_Id=#{student3Id,jdbcType=CHAR}," +
            "teacher1=#{teacher1,jdbcType=VARCHAR},teacher1_Id=#{teacher1Id,jdbcType=CHAR}," +
            "teacher2=#{teacher2,jdbcType=VARCHAR},teacher2_Id=#{teacher2Id,jdbcType=CHAR}" +
            "where id=#{id,jdbcType=INTEGER}")
    public int updateByNumber(Work work);

    @Delete("delete from workTbl where id = #{param1}")
    public int deleteByNumber(@Param("param1")int id);
}
