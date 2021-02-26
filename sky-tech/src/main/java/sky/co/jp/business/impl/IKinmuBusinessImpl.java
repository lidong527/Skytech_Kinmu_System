package sky.co.jp.business.impl;
/**
 * @program: kinmu_management_1104
 * @description:
 * @author: zhou
 * @date: 2020-11-04 17:14
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sky.co.jp.bean.KinmuBean;
import sky.co.jp.business.IKinmuBusiness;
import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.KinmuForm;
import sky.co.jp.mapper.SkyKinmuMapper;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.SessionUtil;

import java.util.Date;

/**
 * @program: kinmu_management_1104
 * @description: 回显示勤务表数据
 * @author: zhou
 * @date: 2020-11-04 17:14
 **/
@Service

public class IKinmuBusinessImpl implements IKinmuBusiness {
    @Autowired
    SkyKinmuMapper skyKinmuMapper;
    @Override
    public KinmuForm getKinmuByBGAndDate(CheckStatusForm checkStatusForm) {
        KinmuBean kinmuBean = new KinmuBean();
        KinmuForm kinmuForm = new KinmuForm();
        kinmuBean.setSTAFF_BG(checkStatusForm.getStaffBg());
        Date gyomuMonth = DateUtil.getGyomuMonth();
        kinmuBean.setUSED_TIME(gyomuMonth);
        KinmuBean kinmuBeanDao = skyKinmuMapper.selectByBgAndUsedTime(kinmuBean);
        kinmuForm.setStaffBg(kinmuBeanDao.getSTAFF_BG());
        kinmuForm.setUSED_TIME(kinmuBeanDao.getUSED_TIME());
        kinmuForm.setUpTime(kinmuBeanDao.getUP_TIME());
        kinmuForm.setCompany(kinmuBeanDao.getGENBAN_NAME());
        kinmuForm.setUpperTime(kinmuBeanDao.getUPPER_TIME());
        kinmuForm.setStaffName(kinmuBeanDao.getStaff().getSTAFF_NAME());
        kinmuForm.setStaffBg(kinmuBean.getSTAFF_BG());
        kinmuForm.setStandardtime(kinmuBeanDao.getSTANDARD_TIME());
        kinmuForm.setWorkTime(kinmuBeanDao.getWORK_TIME());
        kinmuForm.setEndtime(kinmuBeanDao.getEND_TIME());
        kinmuForm.setStarttime(kinmuBeanDao.getSTART_TIME());
        kinmuForm.setUpdateCont(kinmuBeanDao.getUPDATE_CONT());

        kinmuForm.setFileName(kinmuBeanDao.getFILE_NAME());

        return kinmuForm;
    }
}
