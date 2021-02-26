package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.SessionBean;
import sky.co.jp.bean.StaffBean;

import java.util.List;

public interface SessionMapper {

    //zhou 2020-11-04
    //增加查询名字
    @Select("select STAFF.STAFF_BG" +
            "     , STAFF.PERMISSION_BG" +
            "     , STAFF.STAFF_NAME"+
            "  from M_STAFF STAFF" +
            " inner join M_ACCOUNT ACCOUNT" +
            "    on STAFF.STAFF_MAILADDRESS = ACCOUNT.USER_MAIL" +
            " where ACCOUNT.USER_MAIL = #{userMail}" +
            "   and ACCOUNT.PASSWORD = #{password}")
    SessionBean selectstaffBgByMail(@Param("userMail")String userMail,@Param("password") String password);

    @Select("select PERMISSION_BG" +
            "  from M_STAFF" +
            " where STAFF_BG = #{staffBg}")
    SessionBean selectPerByBg(@Param("staffBg")String staffBg);

    @Select("select CODE_CONTENT2            " +
            "  from M_CODE                   " +
            " where CODE_ID = #{id}" +
            "   and CODE_CONTENT = #{content} " +
            "   and DELETE_FLG = 0           " )
    GetButtonCdBean selectContent2ByIdAndContent(@Param("id") String id, @Param("content") String content);

}
