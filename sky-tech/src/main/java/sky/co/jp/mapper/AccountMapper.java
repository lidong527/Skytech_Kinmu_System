package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import sky.co.jp.bean.AccountBean;

import java.util.List;
@Mapper
public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountBean record);

    AccountBean selectByPrimaryKey(Integer id);

    List<AccountBean> selectAll();

    int updateByPrimaryKey(AccountBean record);

    @Select("select * from m_account where USER_MAIL = #{userMail} and PASSWORD =#{password}")
    AccountBean selectByuserMailAndPassword(@Param("userMail") String userMail, @Param("password") String password);

    @Select("select PASS_SEC" +
            "     , INITIALIZATION_FLG" +
            "  from M_ACCOUNT" +
            " where USER_MAIL = #{userMail}")
    AccountBean selectPAsswordByUserMail(@Param("userMail") String userMail);
}