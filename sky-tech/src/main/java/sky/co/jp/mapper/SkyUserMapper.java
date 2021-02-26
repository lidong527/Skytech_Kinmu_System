package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sky.co.jp.bean.SkyUserBean;

@Mapper
public interface SkyUserMapper {
    @Select("select * from skyuser where id=#{id}")
    SkyUserBean selectUserById(Integer id);
}
