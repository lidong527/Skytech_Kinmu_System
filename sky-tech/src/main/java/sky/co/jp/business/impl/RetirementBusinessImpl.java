package sky.co.jp.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IRetirementBusiness;
import sky.co.jp.mapper.RetirementMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.queryData.StaffReportQueryObject;
import sky.co.jp.util.StringUtil;

import java.util.List;

/**
 * Description:
 * Author: Lyu Zhongqin
 * Time: 2021-02-18 14:53
 */
@Service
public class RetirementBusinessImpl implements IRetirementBusiness {
    @Autowired
    StaffMapper staffMapper;

    @Autowired
    RetirementMapper retirementMapper;

    @Override
    public PageResult<StaffBean> queryRetirement(StaffReportQueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword()) && !StringUtil.hasLength(qo.getSTAFF_BG())){
            qo.setKeyword(null);
            qo.setSTAFF_BG(null);
            return new PageResult<>(null, qo.getCurrentPage(), qo.getPageSize(), 0);
        }

        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }

        if (!StringUtil.hasLength(qo.getSTAFF_BG())){
            qo.setSTAFF_BG(null);
        }

        //查总条数
        int count = staffMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<>(qo.getCurrentPage(), qo.getPageSize());
        }
        List<StaffBean> list = staffMapper.select4List(qo);

        return new PageResult<>(list, qo.getCurrentPage(), qo.getPageSize(), count);
    }

    @Override
    public int retire(String staff_bg) {
        return retirementMapper.retire(staff_bg);
    }
}