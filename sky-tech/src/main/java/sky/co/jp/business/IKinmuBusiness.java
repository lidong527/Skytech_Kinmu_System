package sky.co.jp.business;

/**
 * @description:
 * @params:  * @Param null
 * @return:
 * @author: zhou
 * @date: 2020/11/04
 */

import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.KinmuForm;


public interface IKinmuBusiness {

    /** 
     * @description: 回显示勤务表数据
     [checkStatusForm]
     * @return: sky.co.jp.form.KinmuForm 
     * @author: zhou
     * @date: 2020/11/04 
     */
    KinmuForm getKinmuByBGAndDate(CheckStatusForm checkStatusForm);

}
