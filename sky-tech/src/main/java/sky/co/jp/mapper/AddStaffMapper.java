package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface AddStaffMapper {

    @Insert("   insert into " +
            "m_account (USER_MAIL" +
            "        , PASSWORD" +
            "        , PASS_SEC" +
            "        , INITIALIZATION_FLG" +
            "        , CREATE_TIME" +
            "        , CREATE_USER" +
            "        , UPDATE_TIME" +
            "        , UPDATE_USER" +
            "        , UPDATE_CONT)" +
            "   values (#{userMail}" +
            "        , #{password}" +
            "        , #{encodePassword}" +
            "        , 1" +
            "        , NOW()" +
            "        , 'ADD_STAFF_SYS'" +
            "        , NOW()" +
            "        , 'ADD_STAFF_SYS'" +
            "        , 0)")
    int insertAccount(@Param("userMail") String Email,@Param("password") String password, @Param("encodePassword") String encode);

    @Insert(" insert into " +
            "m_staff (STAFF_BG" +
            "      , STAFF_MAILADDRESS" +
            "      , PERMISSION_BG" +
            "      , STAFF_STATUS" +
            "      , IT_TYPE" +
            "      , LM_KINMU_STATUS" +
            "      , LM_KOUTU_STATUS" +
            "      , LM_TATEKAE_STATUS" +
            "      , TM_KINMU_STATUS" +
            "      , TM_KOUTU_STATUS" +
            "      , TM_TATEKAE_STATUS" +
            "      , CONTRACT_TYPE " +
            "      , RESIGN_FLAG "+
            "      , CREATE_TIME" +
            "      , CREATE_USER" +
            "      , UPDATE_TIME" +
            "      , UPDATE_USER" +
            "      , UPDATE_CONT)" +
            " values (#{staffBg}" +
            "      , #{email}" +
            "      , #{permBg}" +
            "      , 0" +
            "      , #{type}" +
            "      , 0" +
            "      , 0" +
            "      , 0" +
            "      , 0" +
            "      , 0" +
            "      , 0" +
            "      , #{contractType} "+
            "      , 1" +
            "      , NOW()" +
            "      , 'ADD_STAFF_SYS'" +
            "      , NOW()" +
            "      , 'ADD_STAFF_SYS'" +
            "      , 0)")
    int insertStaff(@Param("staffBg") Long staffbg,@Param("email") String email,
                    @Param("permBg") int permission,@Param("type") String type,
                    @Param("contractType") int contractType);
}
