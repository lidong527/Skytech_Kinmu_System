package sky.co.jp.business;

/** 
 * @description:  
 * @params:  * @Param null
 * @return:  
 * @author: zhou
 * @date: 2020/11/11
 */

import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.KoutuForm;
import sky.co.jp.form.TatekaeForm;


public interface ITatekaeBusiness {


    TatekaeForm getTatekaeByBGAndDate(CheckStatusForm checkStatusForm);

}
