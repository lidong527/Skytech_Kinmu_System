package sky.co.jp.mapper;


import org.apache.ibatis.annotations.*;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.bean.StaffStatusBean;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.queryData.StaffReportQueryObject;

import java.util.List;

@Mapper
public interface StaffMapper {
    //zhou 20201023
    //所有staffbg参数 修改为string类型
    int deleteByPrimaryKey(String staffBg);

    @Insert("insert into m_staff (STAFF_MAILADDRESS,STAFF_NAME,IT_TYPE,STAFF_SEX,PERMISSION_BG,STAFF_STATUS,GENBAN_NAME,GENBAN_ADDRESS,STANDARD_TIME,UPPER_TIME,CREATE_TIME,CREATE_USER,STAFF_BG,REMARKS)" +
            " values (#{STAFF_MAILADDRESS},#{STAFF_NAME},#{IT_TYPE},#{STAFF_SEX},1,#{STAFF_STATUS},#{GENBAN_NAME},#{GENBAN_ADDRESS},#{STANDARD_TIME},#{UPPER_TIME},now(),#{CREATE_USER},#{STAFF_BG},#{REMARKS})")
    int insert(StaffBean staff);

    StaffBean selectByPrimaryKey(String staffBg);

    int updateByPrimaryKey(StaffBean record);

    @Select("select * from m_staff where STAFF_MAILADDRESS = #{staffMailaddress}")
    StaffBean selectByByMail(@Param("staffMailaddress") String staffMailaddress);

    @Select("select * from m_staff where STAFF_BG = #{staff_bg}")
    StaffBean selectByStaffBG(@Param("staff_bg") String staff_bg);

    @Select("select STAFF_NAME" +
            "     , IT_TYPE" +
            "     , STAFF_MAILADDRESS" +
            "     , STAFF_SEX" +
            "     , STAFF_STATUS" +
            "     , GENBAN_NAME" +
            "     , GENBAN_ADDRESS" +
            "     , STANDARD_TIME" +
            "     , UPPER_TIME" +
            "     , REMARKS" +
            "  from M_STAFF" +
            " where STAFF_BG = #{staffBg}")
    StaffBean selectMsgByStaffbg(@Param("staffBg") String staffBg);

    @Update("<script>" +
            "update m_staff " +
            "   set " +
            " <if test=\"statusNameFlag != null and statusNameFlag eq 'KINMU'\">" +
            " TM_KINMU_STATUS = 2" +
            "</if>" +
            " <if test=\"statusNameFlag != null and statusNameFlag eq 'KOUTU'\">" +
            " TM_KOUTU_STATUS = 2" +
            "</if>" +
            " <if test=\"statusNameFlag != null and statusNameFlag eq 'TATEKAE'\">" +
            " TM_TATEKAE_STATUS = 2" +
            "</if>" +
            " where STAFF_BG = #{staffBg}" +
            "</script>")
    int updateTmStatus(@Param("staffBg") String  staffBg,@Param("statusNameFlag") String  statusNameFlag);

    @Select("select max(STAFF_BG) from m_staff")
    String selectMaxId();

    @Select("select STAFF_NAME" +
            "  from M_STAFF" +
            " where STAFF_BG = #{staffBg}")
    StaffBean selectNameByBg(String staffBg);

    @Select("select * from m_staff")
    List<StaffBean> selectAll();

    @Insert("insert into m_staff(STAFF_BG,STAFF_MAILADDRESS," +
            "STAFF_NAME,IT_TYPE,STAFF_SEX,STAFF_STATUS,GENBAN_NAME,GENBAN_ADDRESS,STANDARD_TIME," +
            "UPPER_TIME,LM_KINMU_STATUS,LM_KOUTU_STATUS,LM_TATEKAE_STATUS," +
            "TM_KINMU_STATUS,TM_KOUTU_STATUS,TM_TATEKAE_STATUS,PERMISSION_BG,REMARKS)" +
            " values (#{STAFF_BG},#{STAFF_MAILADDRESS},#{STAFF_NAME},#{IT_TYPE},#{STAFF_SEX}," +
            "#{STAFF_STATUS},#{GENBAN_NAME},#{GENBAN_ADDRESS},#{STANDARD_TIME},#{UPPER_TIME},#{LM_KINMU_STATUS}," +
            "#{LM_KOUTU_STATUS},#{LM_TATEKAE_STATUS},#{TM_KINMU_STATUS},#{TM_KOUTU_STATUS},#{TM_TATEKAE_STATUS}," +
            "#{PERMISSION_BG},"+"#{REMARKS})")
    void insertStaff(StaffBean staffBean);

    @Update("<script>" +
            "update M_STAFF" +
            "   set STAFF_NAME = #{staffName}"+
            "     , IT_TYPE = #{itType}" +
            "     , STAFF_SEX = #{staffSex}" +
            "     , STAFF_STATUS = #{staffStatus}" +
            "<if test='standardTime !=null'>" +
            "     , STANDARD_TIME = #{standardTime}" +
            "</if>" +
            "<if test='upperTime !=null'>" +
            "     , UPPER_TIME = #{upperTime}" +
            "</if>" +
            "     , GENBAN_NAME = #{genbanName}" +
            "     , GENBAN_ADDRESS = #{genbanAddress}" +
            "     , REMARKS = #{remarks}" +
            " where STAFF_MAILADDRESS = #{userMail}" +
            "   and STAFF_BG = #{staffBg}" +
            "</script>")
    int updateStaff(@Param("staffName") String staffName,@Param("itType") String itType,@Param("staffSex")String staffSex,@Param
            ("staffStatus")int staffStatus,@Param("standardTime")Long standardtime,@Param
                            ("upperTime")Long upperTime,@Param("genbanName") String ganbanName,@Param("genbanAddress") String genbanAddress,@Param("remarks") String remarks,@Param
                            ("userMail")String userMail,@Param("staffBg")String staffBg);


    @Insert("insert into m_staff(STAFF_BG,STAFF_MAILADDRESS) values (#{STAFF_BG},#{STAFF_MAILADDRESS})" )
    int insertStaffer(@Param("STAFF_BG") String STAFF_BG,@Param("STAFF_MAILADDRESS") String STAFF_MAILADDRESS);

    @Select("<script>"
            +"select *"
            +"  from M_STAFF"
            +"<where>"
            +"       <if test='IT_TYPE !=null and IT_TYPE != &quot;&quot;'>"
            +"   and IT_TYPE = #{IT_TYPE}"
            +"       </if>"
            +"     (       <if test='LM_KOUTU_STATUS !=null'>"
            +"         and LM_KOUTU_STATUS = #{LM_KOUTU_STATUS}"
            +"             </if>"
            +"             <if test='LM_KINMU_STATUS !=null'>"
            +"         and LM_KINMU_STATUS = #{LM_KINMU_STATUS}"
            +"             </if>"
            +"             <if test='LM_TATEKAE_STATUS !=null'>"
            +"         and LM_TATEKAE_STATUS = #{LM_TATEKAE_STATUS}"
            +"             </if>"
            +"   )or ("
            +"             <if test='TM_KOUTU_STATUS !=null'>"
            +"         and TM_KOUTU_STATUS = #{TM_KOUTU_STATUS}"
            +"             </if>"
            +"             <if test='TM_KiNMU_STATUS !=null'> "
            +"         and TM_KiNMU_STATUS = #{TM_KiNMU_STATUS}"
            +"             </if>"
            +"             <if test='TM_TATEKAE_STATUS !=null'>"
            +"         and TM_TATEKAE_STATUS = #{TM_TATEKAE_STATUS}"
            +"             </if>"
            +"   )"
            +"</where>"
            +"</script>")
    List<StaffBean> selectAlla();
    @Update("update M_ACCOUNT" +
            "   set PASSWORD = #{password}" +
            "     , INITIALIZATION_FLG = 0" +
            "     , PASS_SEC = #{passSec}" +
            " where USER_MAIL = #{userMail}")
    int updatePassword(@Param("password") String password, @Param("userMail") String userMail, @Param("passSec") String passSec);

    @Select("select count(if(LM_KOUTU_STATUS=0,true,null)) as LM_KOUTU_STATUS" +
            "     , count(if(LM_KINMU_STATUS=0,true,null)) as LM_KINMU_STATUS" +
            "     , count(if(TM_KOUTU_STATUS=0,true,null)) as TM_KOUTU_STATUS" +
            "     , count(if(TM_KINMU_STATUS=0,true,null)) as TM_KINMU_STATUS" +
            "     , count(if(LM_KINMU_STATUS=1 or LM_KINMU_STATUS = 2,true,null)) as LM_KINMU_STATUS_COM" +
            "     , count(if(LM_KOUTU_STATUS=1 or LM_KOUTU_STATUS=2 ,true,null)) as LM_KOUTU_STATUS_COM" +
            "     , count(if(LM_TATEKAE_STATUS=1 or LM_TATEKAE_STATUS=2,true,null)) as LM_TATEKAE_STATUS_COM" +
            "     , count(if(TM_KOUTU_STATUS=1 or TM_KOUTU_STATUS=2 ,true,null)) as TM_KOUTU_STATUS_COM" +
            "     , count(if(TM_KINMU_STATUS=1 or TM_KINMU_STATUS=2,true,null)) as TM_KINMU_STATUS_COM" +
            "     , count(if(TM_TATEKAE_STATUS=1 or TM_TATEKAE_STATUS=2,true,null)) as TM_TATEKAE_STATUS_COM " +
            "from m_staff where IT_TYPE=#{tpye} ")
    StaffStatusBean selectCountStatusByType(String type);

    @Select("<script>"
            +"select S.STAFF_BG as STAFF_BG"
            +"     , S.STAFF_NAME as STAFF_NAME"
            +"     , S.IT_TYPE as IT_TYPE"
            +"     , S.STAFF_STATUS as STAFF_STATUS"
            +"     , S.LM_KOUTU_STATUS as LM_KOUTU_STATUS"
            +"     , S.LM_KINMU_STATUS as LM_KINMU_STATUS"
            +"     , S.LM_TATEKAE_STATUS as LM_TATEKAE_STATUS"
            +"     , S.TM_KOUTU_STATUS as TM_KOUTU_STATUS"
            +"     , S.TM_KINMU_STATUS as TM_KINMU_STATUS"
            +"     , S.TM_TATEKAE_STATUS as TM_TATEKAE_STATUS"
            +"     , KL.WORK_TIME as LM_WORK_TIME"
            +"     , KL.ZANGYOU_TIME as LM_ZANGYOU_TIME"
            +"     , KT.WORK_TIME as TM_WORK_TIME"
            +"     , KT.ZANGYOU_TIME as TM_ZANGYOU_TIME"
            +"  from M_STAFF S"
            +"  left join M_KINMU KL"
            +"    on S.STAFF_BG = KL.STAFF_BG"
            +"   and KL.USED_TIME = #{usedTimeL}"
            +"  left join M_KINMU KT"
            +"    on S.STAFF_BG = KT.STAFF_BG"
            +"   and KT.USED_TIME = #{usedTimeT}"
            +"<where> "
            +"       <if test=\"staffName != null and staffName != ''\">"
            +"   and S.STAFF_NAME = #{StaffName}"
            +"       </if>"
            +"       <if test=\"itType != null and itType != ''\">"
            +"   and S.IT_TYPE = #{itType}"
            +"       </if>"
            +"       <if test=\"statusNameKou != null and month eq 'LM' and statusKou eq 1\">"
            +"   and (S.LM_KOUTU_STATUS = 1"
            +"        or S.LM_KOUTU_STATUS = 2) "
            +"       </if>"
            +"       <if test=\"statusNameKou != null and month eq 'LM' and statusKou eq 0\">"
            +"   and S.LM_KOUTU_STATUS = 0"
            +"       </if>"
            +"       <if test=\"statusNameKin != null and month eq 'LM' and statusKin eq 1\">"
            +"   and (S.LM_KINMU_STATUS = 1"
            +"        or S.LM_KINMU_STATUS = 2 ) "
            +"       </if>"
            +"       <if test=\"statusNameKin != null and month eq 'LM' and statusKin eq 0\">"
            +"   and S.LM_KINMU_STATUS = 0"
            +"       </if>"
            +"       <if test=\"statusNameTat != null and month eq 'LM' and statusTat eq 1\">"
            +"   and (S.LM_TATEKAE_STATUS = 1"
            +"        or S.LM_TATEKAE_STATUS = 2) "
            +"       </if>"
            +"       <if test=\"statusNameTat != null and month eq 'LM' and statusTat eq 0\">"
            +"   and S.LM_TATEKAE_STATUS = 0"
            +"       </if>"
            +"       <if test=\"statusNameKou != null and month eq 'TM' and statusKou eq 1\">"
            +"   and (S.TM_KOUTU_STATUS = 1"
            +"        or S.TM_KOUTU_STATUS = 2) "
            +"       </if>"
            +"       <if test=\"statusNameKou != null and month eq 'TM' and statusKou eq 0\">"
            +"   and S.TM_KOUTU_STATUS = 0"
            +"       </if>"
            +"       <if test=\"statusNameKin != null and month eq 'TM' and statusKin eq 1\">"
            +"   and (S.TM_KINMU_STATUS = 1"
            +"        or S.TM_KINMU_STATUS = 2) "
            +"       </if>"
            +"       <if test=\"statusNameKin != null and month eq 'TM' and statusKin eq 0\">"
            +"   and S.TM_KINMU_STATUS = 0"
            +"       </if>"
            +"       <if test=\"statusNameTat != null and month eq 'TM' and statusTat eq 1\">"
            +"   and (S.TM_TATEKAE_STATUS = 1"
            +"        or S.TM_TATEKAE_STATUS = 2) "
            +"       </if>"
            +"       <if test=\"statusNameTat != null and month eq 'TM' and statusTat eq 0\">"
            +"   and S.TM_TATEKAE_STATUS = 0"
            +"       </if>"
            +"</where>"
//            +" limit #{limit} "
//            +"offset #{offset}"
            +"</script>")
    List<StaffBean> selectStatusByLot(@Param("staffName") String staffName, @Param("statusNameKou") String statusNameKou, @Param("statusNameKin") String statusNameKin
            , @Param("statusNameTat") String statusNameTat, @Param("statusKou") String statusKou, @Param("statusKin") String statusKin
            , @Param("statusTat") String statusTat, @Param("month") String month, @Param("usedTimeL")String usedTimeL
            , @Param("usedTimeT") String usedTimeT, @Param("limit") int limit, @Param("offset") int offset, @Param("itType") String itType);

    //zhou 20201022 listall
    //zhou 20201028 增加退职标识
    @Select(
            "<script>"
                    + "SELECT count(STAFF_BG) FROM m_staff"
                    + "<where>"
                    + "<if test='IT_TYPE !=null'> AND IT_TYPE = #{IT_TYPE}</if>"
                    + "<if test='LM_KOUTU_STATUS != null'> AND LM_KOUTU_STATUS = #{LM_KOUTU_STATUS}</if>"
                    + "<if test='TM_KOUTU_STATUS != null'>  AND TM_KOUTU_STATUS = #{TM_KOUTU_STATUS}</if>"
                    + "<if test='LM_KINMU_STATUS != null'> AND LM_KINMU_STATUS = #{LM_KINMU_STATUS}</if>"
                    + "<if test='TM_KINMU_STATUS != null'>  AND TM_KINMU_STATUS = #{TM_KINMU_STATUS}</if>"
                    + "<if test='LM_TATEKAE_STATUS != null'> AND LM_TATEKAE_STATUS = #{LM_TATEKAE_STATUS}</if>"
                    + "<if test='TM_TATEKAE_STATUS != null'> AND TM_TATEKAE_STATUS = #{TM_TATEKAE_STATUS}</if>"
                    + "<if test='STAFF_BG !=null '> AND STAFF_BG = #{STAFF_BG}</if>"
                    + "<if test='resignFlag !=null '> AND RESIGN_FLAG = #{resignFlag}</if>"
                    + "<if test='contractType !=null '> AND CONTRACT_TYPE = #{contractType}</if>"
                    + "<if test='keyword !=null'> AND STAFF_NAME LIKE CONCAT('%',#{keyword},'%')</if>"
                    + " AND RESIGN_FLAG = 1"
                    + "</where>"
                    + "</script>"
    )
    int selectForCount(QueryObject qo);

    //zhou 20201022 listall
    //zhou 20201028 增加退职标识
    @Select("<script>"
            + "SELECT STAFF_BG,STAFF_NAME,IT_TYPE,STAFF_STATUS,TM_KINMU_STATUS,TM_KOUTU_STATUS,STAFF_SEX,"
            + "STAFF_MAILADDRESS, CONTRACT_TYPE as contractType, mc.CODE_CONTENT2 as contractTypeName,PERMISSION_BG,"
            + "TM_TATEKAE_STATUS FROM `m_staff` "
            + " left join m_code mc on mc.CODE_CONTENT=CONTRACT_TYPE "
            + " and mc.CODE_ID='CONTRACT_TYPE' and mc.DELETE_FLG='0' "
            + "<where>"
            + "<if test='IT_TYPE !=null'>AND IT_TYPE = #{IT_TYPE}</if>"
            + "<if test='LM_KOUTU_STATUS != null'> AND LM_KOUTU_STATUS = #{LM_KOUTU_STATUS}</if>"
            + "<if test='TM_KOUTU_STATUS != null'>  AND TM_KOUTU_STATUS = #{TM_KOUTU_STATUS}</if>"

            + "<if test='LM_KINMU_STATUS != null'> AND LM_KINMU_STATUS = #{LM_KINMU_STATUS}</if>"
            + "<if test='TM_KINMU_STATUS != null'>  AND TM_KINMU_STATUS = #{TM_KINMU_STATUS}</if>"

            + "<if test='LM_TATEKAE_STATUS != null'> AND LM_TATEKAE_STATUS = #{LM_TATEKAE_STATUS}</if>"
            + "<if test='TM_TATEKAE_STATUS != null'> AND TM_TATEKAE_STATUS = #{TM_TATEKAE_STATUS}</if>"
            + "<if test='keyword !=null'> AND STAFF_NAME LIKE CONCAT('%',#{keyword},'%')</if>"
            + "<if test='resignFlag !=null '> AND RESIGN_FLAG = #{resignFlag}</if>"
            + "<if test='STAFF_BG !=null '> AND STAFF_BG = #{STAFF_BG}</if>"
            + "<if test='contractType !=null '> AND CONTRACT_TYPE = #{contractType}</if>"
            + "</where>"
            + "LIMIT #{start}, #{pageSize}"
            + "</script>")
    List<StaffBean> select4List(QueryObject qo);

    //zhou 20201105
    @Select("select STAFF_NAME" +
            "     , IT_TYPE" +
            "     , STAFF_MAILADDRESS" +
            "     , STAFF_SEX" +
            "     , STAFF_STATUS" +
            "     , GENBAN_NAME" +
            "     , GENBAN_ADDRESS" +
            "     , STANDARD_TIME" +
            "     , UPPER_TIME" +
            "     , REMARKS" +

            "  from M_STAFF" +
            " where STAFF_BG = #{STAFF_BG}")
    StaffBean selectByStaffbg(String STAFF_BG);

    @Select("select PASSWORD FROM m_account where USER_MAIL = #{userMail}")
    String selectPasswordByMail(@Param("userMail") String mail);
}