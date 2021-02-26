package sky.co.jp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import sky.co.jp.bean.CorporateTraning;

import java.util.List;

@Mapper
public interface CorporateTraningMapper {

    @Select("select count(*) from CORPORATE_TRAINING " +
            "where corporatId=#{corporateId} " +
            "AND corporateTrainingName=#{corporateTrainingName}" +
            "AND corporateTrainingStatus=#{corporateTrainingStatus}")
    int selectCount(CorporateTraning corporateTraning);

    @Select("select * from CORPORATE_TRAINING " +
            "where corporatId=#{corporateId} " +
            "AND corporateTrainingName=#{corporateTrainingName}" +
            "AND corporateTrainingStatus=#{corporateTrainingStatus}" +
            "Order BY * Limit 100")
    List<CorporateTraning> selectInfoList(CorporateTraning corporateTraning);
}
