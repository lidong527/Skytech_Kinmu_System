package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import sky.co.jp.bean.AccountBean;
import sky.co.jp.bean.StaffBean;

public interface StatusMapper {
    //zhou 20201023
    //参数staffbg修改为string类型

    @Select("select STAFF_NAME" +
            "     , IT_TYPE" +
            "     , STAFF_SEX" +
            "     , STAFF_STATUS" +
            "     , GENBAN_NAME" +
            "     , GENBAN_ADDRESS" +
            "     , STANDARD_TIME" +
            "     , UPPER_TIME" +
            "     , LM_KINMU_STATUS" +
            "     , LM_KOUTU_STATUS" +
            "     , LM_TATEKAE_STATUS" +
            "     , TM_KINMU_STATUS" +
            "     , TM_KOUTU_STATUS" +
            "     , TM_TATEKAE_STATUS" +
            "     , REMARKS" +
            "  from M_STAFF              " +
            " where STAFF_BG = #{staffBg}")
    StaffBean selectStatusByStaffBg(String staffBg);

    @Select("select A.INITIALIZATION_FLG" +
            "  from M_ACCOUNT A" +
            " inner join M_STAFF S" +
            "    on A.USER_MAIL = S.STAFF_MAILADDRESS" +
            " where S.STAFF_BG = #{staffBg}")
    AccountBean selectFlgByBg( String staffBg);

    @Update("<script>" +
            "update M_STAFF" +
            "<set>" +
            "<if test=\"TM_KINMU_STATUS!=null and TM_KINMU_STATUS!='' \">" +
            "      TM_KINMU_STATUS = #{TM_KINMU_STATUS}" +
            "</if>" +
            "<if test=\"TM_KOUTU_STATUS!=null and TM_KOUTU_STATUS!='' \">" +
            "     ,  TM_KOUTU_STATUS = #{TM_KOUTU_STATUS}" +
            "</if>" +
            "<if test=\"TM_TATEKAE_STATUS!=null and TM_TATEKAE_STATUS!='' \">" +
            "      , TM_TATEKAE_STATUS = #{TM_TATEKAE_STATUS}" +
            "</if>" +
            "<if test=\"STAFF_STATUS!=null and STAFF_STATUS!='' \">" +
            "    ,   STAFF_STATUS = #{STAFF_STATUS}" +
            "</if>" +
            "<if test=\"GENBAN_NAME!=null and GENBAN_NAME!='' \">" +
            "    ,   GENBAN_NAME = #{GENBAN_NAME}" +
            "</if>" +
            "<if test='STANDARD_TIME!=null'>" +
            "     ,  STANDARD_TIME = #{STANDARD_TIME}" +
            "</if>" +
            "<if test='UPPER_TIME!=null'>" +
            "     ,  UPPER_TIME = #{UPPER_TIME}" +
            "</if>" +
            "</set>" +
            " where STAFF_BG = #{STAFF_BG}" +
            "</script>")
    int updateStatusByBg(StaffBean record);
}
