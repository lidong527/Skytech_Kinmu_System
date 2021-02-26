package sky.co.jp.mapper;

import org.apache.ibatis.annotations.*;
import sky.co.jp.bean.InfoBean;

import java.util.List;

@Mapper
public interface InfoMapper {

    @Select("select * from info where id=#{id}")
    InfoBean selectInfoById(Integer id);

    @Select("select * from info")
    List<InfoBean> selectInfoAll();

    @Delete("delete from info where id=#{id}")
    int deleteByID(Integer id);

    @Update("update info set name=#{name} where id=#{id}")
    void updateNameById(InfoBean infoBean);

    @Insert("insert into info(id,startdate,enddate,name) values (#{id},#{startdate},#{enddate},#{name})")
    void insertInfo(InfoBean infoBean);

}