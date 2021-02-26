package sky.co.jp.business.impl;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sky.co.jp.bean.KinmuBean;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.bean.TatekaeBean;
import sky.co.jp.form.KinmuForm;
import sky.co.jp.form.KoutuForm;
import sky.co.jp.form.TatekaeForm;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.mapper.SkyKinmuMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.mapper.TatekaeMapper;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.SessionUtil;
import sky.co.jp.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StaffBusinessImpl implements IStaffBusiness {

    @Autowired
    StaffMapper staffMapper;

    @Autowired
    SkyKinmuMapper kinmuMapper;



    @Autowired
    KoutuMapper koutuMapper;

    @Autowired
    TatekaeMapper tatekaeMapper;


    @Override
    public StaffBean getStaffByMail(String staffMail) {
        return null;
    }

    @Override
    public StaffBean getStaffByBG(String staff_bg) {
        return null;
    }

    //zhou 更新员工勤务表提交状态 同时设定承认操作用户
    //20201106
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTmStatus(KinmuForm kinmuForm, String statusNameFlag) {

        int ret = staffMapper.updateTmStatus(kinmuForm.getStaffBg(), statusNameFlag);

        if (ret ==1){
            KinmuBean kinmuBean = new KinmuBean();
            kinmuBean.setUPDATE_CONT(kinmuForm.getUpdateCont()+1);
            kinmuBean.setUPDATE_USER(SessionUtil.getStaffBG());
            kinmuBean.setUSED_TIME(kinmuForm.getUSED_TIME());
            kinmuBean.setSTAFF_BG(kinmuForm.getStaffBg());
            kinmuMapper.updateCountAndUser(kinmuBean);
        }
        return ret;
    }

    @Override
    public PageResult<StaffBean> queryList(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }
        int flag = 1;
        //判断操作日期是15号之前还是之后 flag=0 查询上月数据
        String currentDay = DateUtil.getCurrentDay();
        if(currentDay != null){
            int intCurrentDay = Integer.parseInt(currentDay);
            if (intCurrentDay < 16){
                qo.setMonthFlag(0);
                flag=0;
            }else{
                qo.setMonthFlag(1);
                flag=1;
            }

        }

        //查总条数
        int count = staffMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<StaffBean>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<StaffBean> list = staffMapper.select4List(qo);


        List<StaffBean> staffList = new ArrayList();
        for (StaffBean staff : list){
            KinmuBean thisMonth = kinmuMapper.selectByStaffBGAndMon(staff.getSTAFF_BG(),flag);
            KoutuBean koutuBean = koutuMapper.selectByStaffBGAndMon(staff.getSTAFF_BG(),flag);
            TatekaeBean tatekaeBean = tatekaeMapper.selectByStaffBGAndMon(staff.getSTAFF_BG(),flag);
            staff.setKinmuBean(thisMonth);
            staff.setKoutuBean(koutuBean);
            staff.setTatekaeBean(tatekaeBean);
            staffList.add(staff);
        }

        return new PageResult<StaffBean>(staffList,qo.getCurrentPage(),qo.getPageSize(),count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateKoutuStatus(KoutuForm koutuForm, String statusNameFlag) {
        int ret = staffMapper.updateTmStatus(koutuForm.getStaffBg(), statusNameFlag);
        if (ret ==1){
            KoutuBean koutuBean = new KoutuBean();
            koutuBean.setUPDATE_CONT(koutuForm.getUpdateCont()+1);
            koutuBean.setUPDATE_USER(SessionUtil.getStaffBG());
            koutuBean.setUSED_TIME(koutuForm.getUsedTime());
            koutuBean.setSTAFF_BG(koutuForm.getStaffBg());
            koutuBean.setUSED_MONEY_ALL(koutuForm.getUsedMoneyAll());
            koutuBean.setUSED_MONEY_ETC(koutuForm.getUsedMoneyEtc());
            koutuBean.setUSED_MONEY_TSUKIN(koutuForm.getUsedMoneyTsu());
            koutuMapper.updateCountAndUser(koutuBean);
        }
        return ret;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTatekaeStatus(TatekaeForm tatekae, String tatekaeStatusFlag) {

        int ret = staffMapper.updateTmStatus(tatekae.getSTAFF_BG(), tatekaeStatusFlag);
        if (ret ==1){
            TatekaeBean tatekaeBean = new TatekaeBean();
            tatekaeBean.setUPDATE_CONT(tatekae.getUPDATE_CONT()+1);
            tatekaeBean.setUPDATE_USER(SessionUtil.getStaffBG());
            tatekaeBean.setUSED_TIME(tatekae.getUSED_TIME());
            tatekaeBean.setSTAFF_BG(tatekae.getSTAFF_BG());
            tatekaeBean.setUSED_PRICE(tatekae.getUSED_PRICE());
            tatekaeMapper.updateCountAndUser(tatekaeBean);
        }
        return ret;
    }

    @Override
    public PageResult<StaffBean> queryContractList(QueryObject qo) {
        if (!StringUtil.hasLength(qo.getKeyword())){
            qo.setKeyword(null);
        }

        //查总条数
        int count = staffMapper.selectForCount(qo);
        System.out.println("count="+count );
        //总条数不为0的时候查结果集
        if(count == 0){
            return new PageResult<StaffBean>(qo.getCurrentPage(),qo.getPageSize());
        }
        List<StaffBean> list = staffMapper.select4List(qo);


        return new PageResult<StaffBean>(list,qo.getCurrentPage(),qo.getPageSize(),count);
    }


}
