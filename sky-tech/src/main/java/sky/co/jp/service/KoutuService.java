package sky.co.jp.service;

import org.apache.ibatis.annotations.Param;
import sky.co.jp.bean.KoutuBean;

import java.util.List;

public interface KoutuService {
    int deleteByPrimaryKey(Long KOUTU_ID);

    int insert(KoutuBean record);

    KoutuBean selectByPrimaryKey(@Param("KOUTU_ID") Long KOUTU_ID);

    List<KoutuBean> selectByPrimarySt(String START_ST);

    List<KoutuBean> selectAll();

    int updateByPrimaryKey(KoutuBean record);
}

