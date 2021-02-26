package sky.co.jp.queryData;

import lombok.Getter;
/** 
 * @description: ajaxのために 
 * @params:  * @Param null
 * @return:
 * @author: zhou
 * @date: 2020/11/06 
 */
@Getter
public class JsonResult {
    private boolean success = true;
    private String eMsg;
    public void mark(String eMsg){
        this.success=false;
        this.eMsg=eMsg;
    }
}