package sky.co.jp.business.impl;/**
 * @program: kinmu_management_1104
 * @description:
 * @author: zhou
 * @date: 2020-11-05 21:40
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.business.IKoutuBusiness;
import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.KinmuForm;
import sky.co.jp.form.KoutuForm;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.util.DateUtil;

import java.util.Date;

/**
 * @program: kinmu_management_1104
 * @description:
 * @author: zhou
 * @date: 2020-11-05 21:40
 **/
@Service

public class IKoutuBusinessImpl implements IKoutuBusiness {
    @Autowired
    KoutuMapper koutuMapper;
    
    @Override
    public KoutuForm getKoutuByBG(String staffBG) {
        KoutuForm koutuForm = new KoutuForm();
        Date gyomuMonth = DateUtil.getGyomuMonth();
        KoutuBean koutuBean = new KoutuBean();
        koutuBean.setUSED_TIME(gyomuMonth);
        koutuBean.setSTAFF_BG(staffBG);

        KoutuBean KoutuBeanDao = koutuMapper.selectByBgAndUsedTime(koutuBean);
        koutuForm.setStaffBg(staffBG);
        koutuForm.setUsedTime(KoutuBeanDao.getUSED_TIME());
        koutuForm.setUpTime(KoutuBeanDao.getUP_TIME());
        koutuForm.setStaffName(KoutuBeanDao.getStaff().getSTAFF_NAME());
        koutuForm.setStartSt(KoutuBeanDao.getSTART_ST());
        koutuForm.setEndSt(KoutuBeanDao.getEND_ST());
        koutuForm.setUsedMoneyAll(KoutuBeanDao.getUSED_MONEY_ALL());
        koutuForm.setUsedMoneyEtc(KoutuBeanDao.getUSED_MONEY_ETC());
        koutuForm.setUsedMoneyTsu(KoutuBeanDao.getUSED_MONEY_TSUKIN());

        koutuForm.setFileName(KoutuBeanDao.getFILE_NAME());

        koutuForm.setUpdateCont(KoutuBeanDao.getUPDATE_CONT());
        return koutuForm;
    }
}
