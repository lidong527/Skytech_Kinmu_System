package sky.co.jp.business.impl;/**
 * @program: kinmu_management_1104
 * @description:
 * @author: zhou
 * @date: 2020-11-11 17:58
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.TatekaeBean;
import sky.co.jp.business.ITatekaeBusiness;
import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.TatekaeForm;
import sky.co.jp.form.TatekaeForm;
import sky.co.jp.mapper.TatekaeMapper;
import sky.co.jp.util.DateUtil;

import java.util.Date;

/**
 * @program: kinmu_management_1104
 * @description:
 * @author: zhou
 * @date: 2020-11-11 17:58
 **/
@Service
public class TatekaeBusinessImpl implements ITatekaeBusiness {
    @Autowired
    TatekaeMapper tatekaeMapper;
    @Override
    public TatekaeForm getTatekaeByBGAndDate(CheckStatusForm checkStatusForm) {
        TatekaeForm tatekaeForm = new TatekaeForm();
        Date gyomuMonth = DateUtil.getGyomuMonth();
        TatekaeBean tatekaeBean = new TatekaeBean();
        tatekaeBean.setUSED_TIME(gyomuMonth);
        tatekaeBean.setSTAFF_BG(checkStatusForm.getStaffBg());

        TatekaeBean tatekaeBeanDao = tatekaeMapper.selectByBgAndUsedTime(tatekaeBean);
        tatekaeForm.setSTAFF_BG(checkStatusForm.getStaffBg());
        tatekaeForm.setUSED_PRICE(tatekaeBeanDao.getUSED_PRICE());

        tatekaeForm.setUSED_TIME(tatekaeBeanDao.getUSED_TIME());
        tatekaeForm.setStaffName(tatekaeBeanDao.getStaff().getSTAFF_NAME());
        tatekaeForm.setUPDATE_CONT(tatekaeBeanDao.getUPDATE_CONT());

        tatekaeForm.setFILE_NAME(tatekaeBeanDao.getFILE_NAME());

        return tatekaeForm;
    }
}
