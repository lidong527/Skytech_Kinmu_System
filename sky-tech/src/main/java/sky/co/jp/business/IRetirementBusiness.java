package sky.co.jp.business;

import sky.co.jp.bean.StaffBean;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.queryData.StaffReportQueryObject;

/**
 * Description:
 * Author: Lyu Zhongqin
 * Time: 2021-02-18 14:52
 */
public interface IRetirementBusiness {

    PageResult<StaffBean> queryRetirement(StaffReportQueryObject qo);

    int retire(String staff_bg);
}