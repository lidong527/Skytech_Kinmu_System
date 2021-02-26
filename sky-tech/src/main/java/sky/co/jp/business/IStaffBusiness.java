package sky.co.jp.business;


import sky.co.jp.bean.StaffBean;
import sky.co.jp.form.KinmuForm;
import sky.co.jp.form.KoutuForm;
import sky.co.jp.form.TatekaeForm;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.QueryObject;
import sky.co.jp.queryData.StaffReportQueryObject;


public interface IStaffBusiness {

    StaffBean getStaffByMail(String staffMail);

    StaffBean getStaffByBG(String staff_bg);

    //zhou 20201106
    int updateTmStatus(KinmuForm kinmuForm, String statusNameFlag);

    PageResult<StaffBean> queryList(QueryObject qo);

    int updateKoutuStatus(KoutuForm koutu, String koutuStatusFlag);

    int updateTatekaeStatus(TatekaeForm tatekae, String tatekaeStatusFlag);

    PageResult<StaffBean> queryContractList(QueryObject qo);

    //int updateStatus( String statusNameFlag,T t);
}
