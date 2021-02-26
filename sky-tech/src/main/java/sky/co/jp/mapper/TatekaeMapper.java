package sky.co.jp.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import sky.co.jp.bean.FileBean;
import sky.co.jp.bean.TatekaeBean;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.QueryObject;

import java.util.Date;
import java.util.List;

@Mapper
public interface TatekaeMapper {
    @Delete("delete  from m_tatekae where TATEKAE_ID = #{TATEKAE_ID}")
    int deleteByPrimaryKey(Long TATEKAE_ID);

    @Insert(" insert into" +
            "   m_tatekae (STAFF_BG" +
            "      , UP_TIME" +
            "      , USED_PRICE" +
            "      , SUB_COMPANY" +
            "      , CREATE_TIME" +
            "      , CREATE_USER" +
            "      , UPDATE_TIME" +
            "      , UPDATE_USER" +
            "      , UPDATE_CONT" +
            "      , FILE_NAME" +
            "       , FILE_URL" +
            "       ,USED_TIME)" +
            " values (#{STAFF_BG}" +
            "      , #{UP_TIME}" +
            "      , #{USED_PRICE}" +
            "      , #{SUB_COMPANY}" +
            "      , NOW()" +
            "      , 'TATEKAE_SYS'" +
            "      , NOW()" +
            "      , 'TATEKAE_SYS'" +
            "      , 0" +
            "       ,#{FILE_NAME}" +
            "       ,#{FILE_URL}" +
            "       ,#{USED_TIME})")
    @Options(useGeneratedKeys = true, keyProperty = "TATEKAE_ID", keyColumn = "TATEKAE_ID")
    int insert(TatekaeBean record);

    @Select("select K.UP_TIME          as UP_TIME"     +
            "     , K.USED_PRICE       as USED_PRICE"  +
            "     , K.SUB_COMPANY      as SUB_COMPANY" +
            "     , K.FILE_NAME        as FILE_NAME"   +
            "     , S.STAFF_NAME       as STAFF_NAME"  +
            "  from m_tatekae K" +
            " inner join M_STAFF S" +
            "    on K.STAFF_BG = S.STAFF_BG" +
            " where K.STAFF_BG = #{staffBg}" +
            "   and K.USED_TIME = #{usedTime}")
    TatekaeBean selectByBgAndTime(@Param("staffBg") String Staffbg, @Param("usedTime") Date usedTime);

    @Select("select * from m_tatekae")
    List<TatekaeBean> selectAll();
    @Update("<script>" +
            "update m_tatekae" +
            "   set " +
            "<if test='USED_PRICE!=null and USED_PRICE != 0'>" +
            "       USED_PRICE = #{USED_PRICE}," +
            "</if>" +
            "<if test='SUB_COMPANY!=null and SUB_COMPANY != \"\"'>" +
            "       SUB_COMPANY = #{SUB_COMPANY}," +
            "</if>" +
            "<if test='FILE_NAME!=null and FILE_NAME != \"\"'>" +
            "       FILE_NAME = #{FILE_NAME}," +
            "</if>" +
            "<if test='FILE_URL!=null and FILE_URL != \"\"'>" +
            "       FILE_URL = #{FILE_URL}," +
            "</if>" +
            "       UP_TIME = #{UP_TIME}," +
            "       UPDATE_TIME = now()," +
            "       UPDATE_USER = 'TATEKAE_SYS'," +
            "       UPDATE_CONT = UPDATE_CONT + 1" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and USED_TIME = #{USED_TIME}" +
            "</script>")
    int updateByBgAndTime(TatekaeBean record);


    @Select("select TATEKAE_ID" +
            "  from m_tatekae" +
            " where STAFF_BG = #{staffBg}" +
            "   and USED_TIME = #{usedTime}")
    TatekaeBean selectIdByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);

    @Select("select " +
            "FILE_NAME" +
            ",FILE_URL" +
            "  from M_TATEKAE" +
            " where STAFF_BG = #{staffBg}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{usedTime} , '%Y%m%d' )")
    FileBean selectFileByBgAndTime(@Param("staffBg") String staffBg, @Param("usedTime") Date UsedTime);
    //liuyang 20201103 立替金相关查询
    //根据条件查询
    @Select("<script>"
            + "SELECT " +
            "      S.STAFF_BG          as STAFF_BG " +
            "     ,S.STAFF_NAME          as STAFF_NAME " +
            "     ,S.STAFF_MAILADDRESS          as STAFF_MAILADDRESS " +
            "     ,K.SUB_COMPANY          as SUB_COMPANY" +
            "     , K.USED_PRICE            as USED_PRICE" +
            "     , K.USED_TIME            as USED_TIME" +
            "    FROM  `M_STAFF`  S "+
            "  inner join `M_TATEKAE` K" +
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
    List<AllReportQueryObject> selectTateByQuery(QueryObject qo);
    //分页计数
    @Select("<script>"
            + "SELECT " +
            "    count(S.STAFF_BG)" +
            " FROM `M_STAFF` S "+
            " inner join `M_TATEKAE` K" +
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

    @Select("select " +
            "     STAFF_BG          as STAFF_BG" +
            "     , USED_TIME        as USED_TIME" +
            "     , USED_PRICE            as USED_PRICE" +
            "       ,UPDATE_CONT  as UPDATE_CONT" +
            "       , FILE_NAME as FILE_NAME" +
            "  from M_TATEKAE" +
            " where STAFF_BG = #{STAFF_BG}" +
            "  and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )")
    @Results({
            @Result(column="STAFF_BG",property="staff",one=@One(select="sky.co.jp.mapper.StaffMapper.selectByStaffbg",fetchType= FetchType.EAGER))
    })
    TatekaeBean selectByBgAndUsedTime(TatekaeBean tatekaeBean);

    @Update("<script>" +
            "update M_TATEKAE" +
            "   set " +
            "<if test='UPDATE_USER!=null and UPDATE_USER != \"\"'></if>" +
            "       UPDATE_USER=#{UPDATE_USER}," +
            "       UPDATE_TIME = now()," +
            "       USED_PRICE = #{USED_PRICE}," +
            "       UPDATE_CONT = #{UPDATE_CONT}" +
            " where STAFF_BG = #{STAFF_BG}" +
            "   and DATE_FORMAT( USED_TIME, '%Y%m%d' ) = DATE_FORMAT( #{USED_TIME} , '%Y%m%d' )" +
            "</script>")
    void updateCountAndUser(TatekaeBean tatekaeBean);

    @Select("<script>"
            + "select "
            + "FILE_URL "
            + ",FILE_NAME "
            + "from `m_tatekae`"
            + "<where>"
//            + "<if test='flag=1'>  DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )</if>"
//            + "<if test='flag=0'>  AND PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( USED_TIME, '%Y%m' ) ) =1</if>"
            + "DATE_FORMAT( USED_TIME, '%Y%m' ) = DATE_FORMAT( (select MAX(GYOMU_DATE) from m_sys_manager) , '%Y%m' )"
            + " AND STAFF_BG=#{STAFF_BG}"
            + "</where>"
            + "</script>")
    TatekaeBean selectByStaffBGAndMon(@Param("STAFF_BG") String staff_bg,@Param("flag") int flag);
}