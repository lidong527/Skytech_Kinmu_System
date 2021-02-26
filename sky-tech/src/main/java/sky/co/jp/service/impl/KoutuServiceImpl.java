package sky.co.jp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.service.KoutuService;

import java.util.List;

@Service
public class KoutuServiceImpl implements KoutuService {
    @Autowired
    private KoutuMapper koutuMapper;

    @Override
    public int deleteByPrimaryKey(Long KOUTU_ID) {

        return koutuMapper.deleteByPrimaryKey(KOUTU_ID);
    }

    @Override
    public int insert(KoutuBean record) {
        return koutuMapper.insert(record);
    }

    @Override
    public KoutuBean selectByPrimaryKey(Long KOUTU_ID) {
//        KoutuBean koutu = koutuMapper.selectByPrimaryKey(KOUTU_ID);
//        return koutu;
        return null;
    }

    @Override
    public List<KoutuBean> selectByPrimarySt(String START_ST) {
        List<KoutuBean> koutu = koutuMapper.selectByPrimarySt(START_ST);
        return koutu;
    }

    @Override
    public List<KoutuBean> selectAll() {
        List<KoutuBean> koutus = koutuMapper.selectAll();
        return koutus;
    }

    @Override
    public int updateByPrimaryKey(KoutuBean record) {
        return koutuMapper.updateByBgAndTime(record);
    }
}
