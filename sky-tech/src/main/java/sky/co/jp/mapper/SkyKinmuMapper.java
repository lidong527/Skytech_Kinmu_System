package sky.co.jp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import sky.co.jp.bean.FileBean;
import sky.co.jp.bean.KinmuBean;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.QueryObject;

@Mapper
public interface SkyKinmuMapper {


    //staffbg参数类型修改为string
    //zhou 20201023
    @Select("select * from m_kinmu where STAFF_BG=#{staffName}")
    KinmuBean selectInfoById(Long kinmuId);

    @Select("select CREATE_USER from m_kinmu where STAFF_BG=#{staffName}")
    KinmuBean selectCreatUser(Long kinmuId);

    @Delete("delete file from kinmuhyo where ID=#{id}")
    KinmuBean delete_kinmu(String path);

    @Insert("insert into m_kinmu" +
            "     ( STAFF_BG" +
            "     , UP_TIME" +
            "     , GENBAN_NAME" +
            "     , STANDARD_TIME" +
            "     , START_TIME" +
            "     , END_TIME" +
            "     , UPPER_TIME" +
            "     , WORK_TIME" +
            "     , ZANGYOU_TIME" +
            "     , FILE_NAME" +
            "     , FILE_URL" +
            "     , CREATE_TIME" +
            "     , CREATE_USER" +
            "     , UPDATE_TIME" +
            "     , UPDATE_USER" +
            "     , UPDATE_CONT" +
            "     , GENBAN_ADDRESS" +
            "     , REMARKS" +
            "     , USED_TIME) " +
            "values (#{STAFF_BG}" +
            "     , #{UP_TIME}" +
            "     , #{GENBAN_NAME}" +
            "     , #{STANDARD_TIME}" +
            "     , #{START_TIME}" +
            "     , #{END_TIME}" +
            "     , #{UPPER_TIME}" +
            "     , #{WORK_TIME}" +
            "     , #{ZANGYOU_TIME}" +
            "     , #{FILE_NAME}" +
            "     , #{FILE_URL}" +
            "     , now()" +
            "     , 'KINMU_SYS'" +
            "     , now()" +
            "     , 'KINMU_SYS'" +
            "     , 0" +
            "     , #{GENBAN_ADDRESS}" +
            "     , #{BIKO}" +
            "     , #{USED_TIME})")
    int insert_kinmu(KinmuBean skyKinmuBean);

    @Update("update m_kinmu(" +
            "   set STAFF_BG = #{STAFF_BG}," +
            "   set UP_TIME = #{UP_TIME}," +
            "   set GENBAN_NAME = #{GENBAN_NAME}," +
            "   set STANDARD_TIME = #{STANDARD_TIME}," +
            "   set START_TIME = #{START_TIME}," +
            "   set END_TIME = #{END_TIME}," +
            "   set UPPER_TIME = #{UPPER_TIME}," +
            "   set WORK_TIME = #{WORK_TIME}," +
            "   set ZANGYOU_TIME = #{ZANGYOU_TIME}," +
            "   set FILE_URL = #{FILE_URL}," +
            "   set FILE_NAME = #{FILE_NAME}," +
            "   set CREATE_TIME = #{CREATE_TIME}," +
            "   set CREATE_USER = #{CREATE_USER}," +
            "   set UPDATE_TIME = #{UPDATE_TIME}," +
            "   set UPDATE_USER = #{UPDATE_USER},set UPDATE_CONT = #{UPDATE_CONT})")
    void update_kinmu(KinmuBean skyKinmuBean);

    @Select("select K.UP_TIME        as UP_TIME" +
            "     , K.STAFF_BG     as STAFF_BG" +
            "     , K.GENBAN_NAME    as GENBAN_NAME" +
            "     , K.STANDARD_TIME  as STANDARD_TIME" +
            "     , K.UPPER_TIME     as UPPER_TIME" +
            "     , K.WORK_TIME      as WORK_TIME" +
            "     , K.ZANGYOU_TIME   as ZANGYOU_TIME" +
            "     , K.FILE_NAME      as FILE_NAME" +
            "     , S.STAFF_NAME     as STAFF_NAME" +
            "     , K.START_TIME     as START_TIME" +
            "     , K.END_TIME       as END_TIME" +
            "     , K.GENBAN_ADDRESS as GENBAN_ADDRESS"+
            "     , K.REMARKS        as biko"+
            "  from M_KINMU K" +
            " inner join M_STAFF S" +
            "    on K.STAFF_BG = S.STAFF_BG" +
            " where K.STAFF_BG = #{staffBg}" +
            "   and K.USED_TIME = #{usedTime}")
    KinmuBean selectByBgAndTime(@Param("staffBg") String Staffbg, @Param("usedTime") Date usedTime);

    @Update("<script>" +
            "update M_KINMU" +
            "   set " +
            "<if test='GENBAN_NAME!=null and GENBAN_NAME != \"\"'>" +
            "       GENBAN_NAME=#{GENBAN_NAME}," +
            "</if>" +
            "<if test='STANDARD_TIME!=null and STANDARD_TIME != \"\"'>" +
            "       STANDARD_TIME = #{STANDARD_TIME}," +
            "</if>" +
            "<if test='START_TIME!=null '>" +
            "       START_TIME = #{START_TIME}," +
            "</if>" +
            "<if test='END_TIME!=null'>" +
            "       END_TIME = #{END_TIME}," +
            "</if>" +
            "<if test='UPPER_TIME!=null and UPPER_TIME != \"\"'>" +
            "       UPPER_TIME = #{UPPER_TIME}," +
            "</if>" +
            "<if test='WORK_TIME!=null and WORK_TIME != \"\"'>" +
            "       WORK_TIME = #{WORK_TIME}," +
            "</if>" +
            "<if test='ZANGYOU_TIME!=null and ZANGYOU_TIME != \"\"'>" +
            "       ZANGYOU_TIME = #{ZANGYOU_TIME}," +
            "</if>" +
            "<if test='FILE_NAME!=null and FILE_NAME != \"\"'>" +
            "       FILE_NAME = #{FILE_NAME}," +
            "</if>" +
            "<if test='FILE_URL!=null and FILE_URL != \"\"'>" +
            "       FILE_URL = #{FILE_URL}," +
            "</if>" +
            "<if test='GENBAN_ADDRESS!=null and GENBAN_ADDRESS != \"\"'>" +
            "       GENBAN_ADDRESS = #{GENBAN_ADDRESS}," +
            "</if>" +
            "<if test='BIKO!=null and BIKO != \"\"'>" +
            "       REMARKS = #{BIKO}," +
            "</if>" +
            "       UP_TIME = #{UP_TIME}," +
            "       UPDATE_TIME = now()," +
            "       UPDATE_USER = 'KINMU_SYS'," +
            "       UPDATE_CONT = UPDATE_CONT + 1" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and  USED_TIME = #{USED_TIME} " +
            "</script>")
    int updateByBgAndTime(KinmuBean record);

    @Select("select KINMU_ID" +
            "  from M_KINMU" +
            " where STAFF_BG = #{staffBg}" +
            "   and USED_TIME = #{usedTime}")
    KinmuBean selectIdByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);

    @Select("select " +
            "  FILE_NAME" +
            "  ,FILE_URL" +

            "  from M_KINMU" +
            " where STAFF_BG = #{staffBg}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{usedTime} , '%Y%m%d' )")
    FileBean selectFileByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);

//zhou 20201022

    @Select("<script>"
            + "select WORK_TIME,ZANGYOU_TIME "
            + ",FILE_URL "
            + ",FILE_NAME "
            + "from `m_kinmu`"
            + "<where>"
//            + "<if test='flag=1'>  DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )</if>"
//            + "<if test='flag=0'>  AND PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( USED_TIME, '%Y%m' ) ) =1</if>"
            +"DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( (select MAX(GYOMU_DATE) from m_sys_manager) , '%Y%m' )"
            + "<if test='STAFF_BG !=null'> AND STAFF_BG=#{STAFF_BG}</if>"
            + "</where>"
            + "</script>")
    KinmuBean selectByStaffBGAndMon(@Param("STAFF_BG")String staff_bg,@Param("flag")int flag);

    @Select("select UP_TIME        as UP_TIME" +
            "     , GENBAN_NAME    as GENBAN_NAME" +
            "     , STANDARD_TIME  as STANDARD_TIME" +
            "     , UPPER_TIME     as UPPER_TIME" +
            "     , WORK_TIME      as WORK_TIME" +
            "     , ZANGYOU_TIME   as ZANGYOU_TIME" +
            "     , FILE_NAME      as FILE_NAME" +
            "     , START_TIME     as START_TIME" +
            "     , END_TIME       as END_TIME" +
            "     , GENBAN_ADDRESS as GENBAN_ADDRESS"+
            "     , REMARKS        as biko"+
            "     , USED_TIME      as USED_TIME" +
            "     , STAFF_BG      as STAFF_BG" +
            "     , UPDATE_CONT      as UPDATE_CONT" +
            "     , FILE_NAME       as FILE_NAME" +
            "  from M_KINMU" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )")
    @Results({
            @Result(column="STAFF_BG",property="staff",one=@One(select="sky.co.jp.mapper.StaffMapper.selectByStaffbg",fetchType= FetchType.EAGER))
    })
    KinmuBean selectByBgAndUsedTime(KinmuBean kinmuBean);

    //zhou 20201106

    @Update("<script>" +
            "update M_KINMU" +
            "   set " +
            "<if test='UPDATE_USER!=null and UPDATE_USER != \"\"'></if>" +
            "       UPDATE_USER=#{UPDATE_USER}," +
            "       UPDATE_TIME = now()," +
            "       UPDATE_CONT = #{UPDATE_CONT}" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )" +
            "</script>")
    void updateCountAndUser(KinmuBean kinmuBean);

    //liuyang 20201103 勤务表
    //根据条件查询
    @Select("<script>"
            + "SELECT " +
            "      S.STAFF_BG          as STAFF_BG " +
            "     ,S.STAFF_NAME          as STAFF_NAME " +
            "     ,S.STAFF_MAILADDRESS          as STAFF_MAILADDRESS " +
            "     ,K.GENBAN_NAME          as GENBAN_NAME" +
            "     , K.STANDARD_TIME            as STANDARD_TIME" +
            "     , K.UPPER_TIME            as UPPER_TIME" +
            "     , K.START_TIME    as START_TIME" +
            "     , K.END_TIME    as END_TIME" +
            "     , K.WORK_TIME as WORK_TIME" +
            "     , K.ZANGYOU_TIME    as ZANGYOU_TIME" +
            "     , K.GENBAN_ADDRESS    as GENBAN_ADDRESS" +
            "     , K.REMARKS as REMARKS" +
            "    FROM  `M_STAFF`  S "+
            "  inner join `M_KINMU` K" +
            "    on K.STAFF_BG = S.STAFF_BG"
            + "<where>"
            + "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND K.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test=\"STAFF_NAME != null and STAFF_NAME != ''\"> AND S.STAFF_NAME like #{STAFF_NAME}</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND K.USED_TIME &gt;= #{usedTimeStart} </if>"
            + "<if test=\"usedTimeEnd != null \">  AND K.USED_TIME &lt;= #{usedTimeEnd} </if>"+
            "</where>"+
            "ORDER BY USED_TIME "
            + " LIMIT #{start}, #{pageSize}"
            + "</script>")
    List<AllReportQueryObject> selectKinmuByQuery(QueryObject qo);
    // liuyang 20201103 分页计数
    @Select("<script>"
            + "SELECT " +
            "    count(S.STAFF_BG)" +
            " FROM `M_STAFF` S "+
            " inner join `M_KINMU` K" +
            "    on K.STAFF_BG = S.STAFF_BG"
            + "<where>" +
            "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND K.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test=\"STAFF_NAME != null and STAFF_NAME != ''\"> AND S.STAFF_NAME like #{STAFF_NAME}</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND K.USED_TIME &gt;= #{usedTimeStart} </if>"
            + "<if test=\"usedTimeEnd != null\">  AND K.USED_TIME &lt;= #{usedTimeEnd} </if>"
            + "</where>"
            + "</script>")
    Integer selectForCount(QueryObject qo);
}



















