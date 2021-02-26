package sky.co.jp.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.mapping.FetchType;

import sky.co.jp.bean.FileBean;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.QueryObject;

@Mapper
public interface KoutuMapper {
    @Delete("delete  from m_koutu where KOUTU_ID = #{KOUTU_ID}")
    int deleteByPrimaryKey(Long koutuId);

    @Insert(" insert into" +
            "  m_koutu (STAFF_BG" +
            "      , UP_TIME" +
            "      , START_ST" +
            "      , END_ST" +
            "      , USED_MONEY_ALL" +
            "      , USED_MONEY_ETC" +
            "      , USED_MONEY_TSUKIN" +
            "      , USED_TIME" +
            "      , FILE_NAME" +
            "      , FILE_URL" +
            "      , CREATE_TIME" +
            "      , CREATE_USER" +
            "      , UPDATE_TIME" +
            "      , UPDATE_USER" +
            "      , UPDATE_CONT)" +
            " values (#{STAFF_BG}" +
            "      , #{UP_TIME}" +
            "      , #{START_ST}" +
            "      , #{END_ST}" +
            "      , #{USED_MONEY_ALL}" +
            "      , #{USED_MONEY_ETC}" +
            "      , #{USED_MONEY_TSUKIN}" +
            "      , #{USED_TIME}" +
            "      , #{FILE_NAME}" +
            "      , #{FILE_URL}" +
            "      , NOW()" +
            "      , 'KOUTU_SYS'" +
            "      , NOW()" +
            "      , 'KOUTU_SYS'" +
            "      , 0)")
    @Options(useGeneratedKeys = true, keyProperty = "KOUTU_ID", keyColumn = "KOUTU_ID")
    int insert(KoutuBean record);

    @Select("select " +
            "     K.START_ST          as START_ST" +
            "     , K.END_ST            as END_ST" +
            "     , K.USED_MONEY_ALL    as USED_MONEY_ALL" +
            "     , K.USED_MONEY_ETC    as USED_MONEY_ETC" +
            "     , K.USED_MONEY_TSUKIN as USED_MONEY_TSUKIN   " +
            "     , K.UPDATE_CONT as UPDATE_CONT" +
            "     , K.FILE_NAME as FILE_NAME" +
            "     , S.STAFF_NAME        as STAFF_NAME" +
            "  from M_KOUTU K" +
            " inner join M_STAFF S" +
            "    on K.STAFF_BG = S.STAFF_BG" +
            " where K.STAFF_BG = #{staffBg}" +
            "  and K.USED_TIME = #{usedTime}")
    KoutuBean selectByBgAndTime(@Param("staffBg") String Staffbg, @Param("usedTime") Date usedTime);

    @Select("select * from m_koutu")
    List<KoutuBean> selectAll();
    @Update("<script>" +
            "update M_KOUTU" +
            "   set " +
            "<if test='START_ST!=null and START_ST != \"\"'>" +
            "       START_ST=#{START_ST}," +
            "</if>" +
            "<if test='END_ST!=null and END_ST != \"\"'>" +
            "       END_ST=#{END_ST}," +
            "</if>" +
            "<if test='USED_MONEY_ALL!=null and USED_MONEY_ALL != 0'>" +
            "       USED_MONEY_ALL = #{USED_MONEY_ALL}," +
            "</if>" +
            "<if test='USED_MONEY_ETC!=null and USED_MONEY_ETC != 0'>" +
            "       USED_MONEY_ETC = #{USED_MONEY_ETC}," +
            "</if>" +
            "<if test='USED_MONEY_TSUKIN!=null and USED_MONEY_TSUKIN != 0'>" +
            "       USED_MONEY_TSUKIN = #{USED_MONEY_TSUKIN}," +
            "</if>" +
            "<if test='FILE_NAME!=null and FILE_NAME != \"\"'>" +
            "       FILE_NAME = #{FILE_NAME}," +
            "</if>" +
            "<if test='FILE_URL!=null and FILE_URL != \"\"'>" +
            "       FILE_URL = #{FILE_URL}," +
            "</if>" +
            "       UP_TIME = #{UP_TIME}," +
            "       UPDATE_TIME = now()," +
            "       UPDATE_USER = 'KOUTU_SYS'," +
            "       UPDATE_CONT = UPDATE_CONT + 1" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and USED_TIME = #{USED_TIME}" +
            "</script>")
    int updateByBgAndTime(KoutuBean record);

    @Select("select * from m_koutu where START_ST = #{START_ST}")
    List<KoutuBean> selectByPrimarySt(String START_ST);

    @Select("select KOUTU_ID" +
            "  from M_KOUTU" +
            " where STAFF_BG = #{staffBg}" +
            "   and USED_TIME = #{usedTime}")
    KoutuBean selectIdByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);

    @Select("select " +
            "FILE_NAME" +
            ",FILE_URL" +
            "  from M_KOUTU" +
            " where STAFF_BG = #{staffBg}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{usedTime} , '%Y%m%d' )")
    FileBean selectFileByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);

    @Select("<script>"
            + "SELECT " +
            "      S.STAFF_BG          as STAFF_BG " +
            "     ,S.STAFF_NAME          as STAFF_NAME " +
            "     ,S.STAFF_MAILADDRESS          as STAFF_MAILADDRESS " +
            "     ,K.START_ST          as START_ST" +
            "     , K.END_ST            as END_ST" +
            "     , K.USED_TIME            as USED_TIME" +
            "     , K.USED_MONEY_ALL    as USED_MONEY_ALL" +
            "     , K.USED_MONEY_ETC    as USED_MONEY_ETC" +
            "     , K.USED_MONEY_TSUKIN as USED_MONEY_TSUKIN" +
            "    FROM  `M_STAFF`  S "+
            "  inner join `M_KOUTU` K" +
            "    on K.STAFF_BG = S.STAFF_BG"
            + "<where>"
            + "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND K.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test='STAFF_NAME !=null'> AND STAFF_NAME LIKE CONCAT('%',#{STAFF_NAME},'%')</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND K.USED_TIME &gt;= #{usedTimeStart} </if>"
            + "<if test=\"usedTimeEnd != null \">  AND K.USED_TIME &lt;= #{usedTimeEnd} </if>"+
            "</where>"+
            "ORDER BY USED_TIME "
            + " LIMIT #{start}, #{pageSize}"
            + "</script>")
    List<AllReportQueryObject>  selectKoutuByQuery(QueryObject qo);
    // liuyang 20201103 分页计数
    @Select("<script>"
            + "SELECT " +
            "    count(S.STAFF_BG)" +
            " FROM `M_STAFF` S "+
            " inner join `M_KOUTU` K" +
            "    on K.STAFF_BG = S.STAFF_BG"
            + "<where>" +
            "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND K.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test='STAFF_NAME !=null'> AND STAFF_NAME LIKE CONCAT('%',#{STAFF_NAME},'%')</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND K.USED_TIME &gt;= #{usedTimeStart}  </if>"
            + "<if test=\"usedTimeEnd != null\">  AND K.USED_TIME &lt;= #{usedTimeEnd} </if>"
            + "</where>"
            + "</script>")
    Integer selectForCount(QueryObject qo);

    @Select("select USED_TIME FROM M_KOUTU")
    List<Date>  selectTime();


    // liuyang 20201106 综合根据条件查询
    @Select("<script>"
            + "SELECT " +
            "      S.STAFF_BG  " +
            "     ,S.GYOMU_DATE " +
            "     ,S.STAFF_NAME  " +
            "     ,S.STAFF_MAILADDRESS   " +
            "     ,S.TM_KINMU_STATUS  " +
            "     ,S.TM_KOUTU_STATUS " +
            "     ,S.TM_TATEKAE_STATUS  " +

            "     , KOU.USED_TIME            as USED_TIME" +
            "     , KOU.USED_MONEY_ALL    as USED_MONEY_ALL" +
            "     , KIN.GENBAN_NAME          as GENBAN_NAME" +
            "     , KIN.UPPER_TIME            as UPPER_TIME" +
            "     , KIN.START_TIME    as START_TIME" +
            "     , KIN.END_TIME    as END_TIME" +
            "     , KIN.GENBAN_ADDRESS    as GENBAN_ADDRESS" +
            "     , KIN.REMARKS as REMARKS" +
            "     , TA.USED_PRICE            as USED_PRICE" +
            "    FROM  " +
            "(SELECT STA.STAFF_BG  as STAFF_BG " +
            ",STA.STAFF_NAME  as STAFF_NAME " +
            ",STA.STAFF_MAILADDRESS as STAFF_MAILADDRESS " +
            ",STA.TM_KINMU_STATUS as TM_KINMU_STATUS " +
            ",STA.TM_KOUTU_STATUS as TM_KOUTU_STATUS" +
            ",STA.TM_TATEKAE_STATUS as TM_TATEKAE_STATUS" +
            ",M.GYOMU_DATE as GYOMU_DATE " +
            "   FROM `M_STAFF` STA,`M_SYS_MANAGER` M) as S "+
            "  LEFT   JOIN `M_KOUTU` KOU" +
            "    on KOU.STAFF_BG = S.STAFF_BG AND KOU.USED_TIME = S.GYOMU_DATE"+
            "     LEFT   JOIN `M_KINMU` KIN" +
            "    on KIN.STAFF_BG = S.STAFF_BG   AND KIN.USED_TIME = S.GYOMU_DATE"+
            "     LEFT   JOIN `M_TATEKAE` TA " +
            "    on TA.STAFF_BG = S.STAFF_BG AND TA.USED_TIME = S.GYOMU_DATE"
            + "<where>"
            + "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND KOU.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test='STAFF_NAME !=null'> AND STAFF_NAME LIKE CONCAT('%',#{STAFF_NAME},'%')</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND KOU.USED_TIME &gt;= #{usedTimeStart} </if>"
            + "<if test=\"usedTimeEnd != null \">  AND KOU.USED_TIME &lt;= #{usedTimeEnd} </if>"+
            "</where>"+
            " ORDER BY  S.STAFF_BG,S.GYOMU_DATE DESC "
            + "  LIMIT #{start}, #{pageSize}"
            + "</script>")
    List<AllReportQueryObject>  selectAllByQuery(QueryObject qo);
    // liuyang 20201106 综合分页计数
    @Select("<script>"
            + "SELECT " +
            "    count(S.STAFF_BG)" +
            "    FROM  " +
            "(SELECT STA.STAFF_BG  as STAFF_BG " +
            ",STA.STAFF_NAME  as STAFF_NAME " +
            ",STA.STAFF_MAILADDRESS as STAFF_MAILADDRESS " +
            ",STA.TM_KINMU_STATUS as TM_KINMU_STATUS " +
            ",STA.TM_KOUTU_STATUS as TM_KOUTU_STATUS" +
            ",STA.TM_TATEKAE_STATUS as TM_TATEKAE_STATUS" +
            ",M.GYOMU_DATE as GYOMU_DATE " +
            "   FROM `M_STAFF` STA,`M_SYS_MANAGER` M) as S "+
            "  LEFT   JOIN `M_KOUTU` KOU" +
            "    on KOU.STAFF_BG = S.STAFF_BG AND KOU.USED_TIME = S.GYOMU_DATE"+
            "     LEFT   JOIN `M_KINMU` KIN" +
            "    on KIN.STAFF_BG = S.STAFF_BG   AND KIN.USED_TIME = S.GYOMU_DATE"+
            "     LEFT   JOIN `M_TATEKAE` TA " +
            "    on TA.STAFF_BG = S.STAFF_BG AND TA.USED_TIME = S.GYOMU_DATE"
            + "<where>" +
            "<if test=\"STAFF_BG != null and STAFF_BG != ''\"> AND KOU.STAFF_BG = #{STAFF_BG}</if>"
            + "<if test='STAFF_NAME !=null'> AND STAFF_NAME LIKE CONCAT('%',#{STAFF_NAME},'%')</if>"
            + "<if test=\"STAFF_MAILADDRESS != null and STAFF_MAILADDRESS != ''\">  AND S.STAFF_MAILADDRESS = #{STAFF_MAILADDRESS}</if>"
            + "<if test=\"usedTimeStart != null\"> AND KOU.USED_TIME &gt;= #{usedTimeStart}  </if>"
            + "<if test=\"usedTimeEnd != null\">  AND KOU.USED_TIME &lt;= #{usedTimeEnd} </if>"
            + "</where>"
            + "</script>")
    Integer selectForAllCount(QueryObject qo);


    //zhou 20201110
    @Update("<script>" +
            "update M_KOUTU" +
            "   set " +
            "<if test='UPDATE_USER!=null and UPDATE_USER != \"\"'></if>" +
            "       UPDATE_USER=#{UPDATE_USER}," +
            "       UPDATE_CONT = #{UPDATE_CONT}," +
            "<if test='USED_MONEY_ALL!=null and USED_MONEY_ALL != 0'>" +
            "       USED_MONEY_ALL = #{USED_MONEY_ALL}," +
            "</if>" +
            "<if test='USED_MONEY_ETC!=null and USED_MONEY_ETC != 0'>" +
            "       USED_MONEY_ETC = #{USED_MONEY_ETC}," +
            "</if>" +
            "<if test='USED_MONEY_TSUKIN!=null and USED_MONEY_TSUKIN != 0'>" +
            "       USED_MONEY_TSUKIN = #{USED_MONEY_TSUKIN}, " +
            "</if>" +
            "       UPDATE_TIME = now()" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )" +
            "</script>")
    void updateCountAndUser(KoutuBean koutuBean);


    @Select("select " +
            "     START_ST          as START_ST" +
            "     ,STAFF_BG          as STAFF_BG" +
            "     , END_ST            as END_ST" +
            "     , USED_MONEY_ALL    as USED_MONEY_ALL" +
            "     , USED_MONEY_ETC    as USED_MONEY_ETC" +
            "     , USED_MONEY_TSUKIN as USED_MONEY_TSUKIN   " +
            "     , UPDATE_CONT as UPDATE_CONT" +
            "     , UP_TIME        as UP_TIME" +
            "     , USED_TIME        as USED_TIME" +
            "     , FILE_NAME       as FILE_NAME" +
            "  from M_KOUTU" +
            " where STAFF_BG = #{STAFF_BG}" +
            "  and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )")
    @Results({
            @Result(column="STAFF_BG",property="staff",one=@One(select="sky.co.jp.mapper.StaffMapper.selectByStaffbg",fetchType= FetchType.EAGER))
    })
    KoutuBean selectByBgAndUsedTime(KoutuBean koutuBean);

    @Select("<script>"
            + "select "
            + "FILE_URL "
            + ",FILE_NAME "
            + "from `m_koutu`"
            + "<where>"
//            + "<if test='flag=1'>  DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )</if>"
//            + "<if test='flag=0'>  AND PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( USED_TIME, '%Y%m' ) ) =1</if>"
            +"DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( (select MAX(GYOMU_DATE) from m_sys_manager) , '%Y%m' )"
            + " AND STAFF_BG=#{STAFF_BG}"
            + "</where>"
            + "</script>")
    KoutuBean selectByStaffBGAndMon(@Param("STAFF_BG") String staff_bg,@Param("flag") int flag);
    //liuyang 20201128 查询系统使用年份
    @Select("<script>"
            + "select "
            + "GYOMU_DATE "
            + "from `m_sys_manager`"
            + "</script>")
    List<Date> selectTimeYear();
}