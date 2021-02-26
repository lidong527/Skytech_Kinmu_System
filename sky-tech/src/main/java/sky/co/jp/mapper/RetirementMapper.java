package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import sky.co.jp.queryData.QueryObject;

/**
 * Description:
 * Author: Lyu Zhongqin
 * Time: 2021-02-18 14:16
 */
@Mapper
public interface RetirementMapper {
    @Update("update m_staff set RESIGN_FLAG = 0, "
            +"RESIGN_TIME = DATE_FORMAT( CURDATE( ) , '%Y%m%d' ) "
            +"where STAFF_BG = #{staffBg} ")
    int retire(@Param("staffBg") String staffBg);

}