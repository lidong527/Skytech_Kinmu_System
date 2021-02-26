package sky.co.jp.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import sky.co.jp.bean.AccountBean;

import javax.servlet.http.HttpSession;

/** 
 * @description: session工具类 
 * @params:  * @Param null
 * @return:  
 * @author: zhou
 * @date: 2020/10/27 
 */
public class SessionUtil {

        public static final String ACCOUNT_IN_SESSION = "ACCOUNT_IN_SESSION";

        public static final String PERMISSION_IN_SESSION = "PERMISSION_IN_SESSION";

        public static final String STAFFBG_IN_SESSION = "STAFFBG_IN_SESSION";

        //zhou 20201104
        //增加staffname存到session
        public static final String STAFF_NAME_IN_SESSION = "STAFF_NAME_IN_SESSION";


        
        //获取session的方法
        public static HttpSession getSession(){
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = requestAttributes.getRequest().getSession();
            //设置session失效时间 30min
            session.setMaxInactiveInterval(30 * 60);

            return session;
        }
        
        //共享当前账户
        public static void setCurrentAccount(AccountBean currentAccount){

            getSession().setAttribute(ACCOUNT_IN_SESSION,currentAccount);
        }

        //获取当前账户
        public static AccountBean getCurrentAccount(){

            return (AccountBean) getSession().getAttribute(ACCOUNT_IN_SESSION);
        }

        //共享当前用户id
        public static void setCurrentStaffBG(String staffBG){

            getSession().setAttribute(STAFFBG_IN_SESSION,staffBG);
        }

        //获取当前用户id
        public static String getStaffBG(){

            return (String) getSession().getAttribute(STAFFBG_IN_SESSION);
        }

        //共享当前用户权限表达式
        public static void setPermission(Integer permission){

            getSession().setAttribute(PERMISSION_IN_SESSION,permission);
        }
        //获取当前用户权限表达式
        public static Integer getPermission(){
            return (Integer) getSession().getAttribute(PERMISSION_IN_SESSION);
        }

        //共享当前用户名字
        public static void setStaffName(String staffName){

            getSession().setAttribute(STAFF_NAME_IN_SESSION,staffName);
        }
        //获取当前用户name
        public static String getStaffName(){
        return (String) getSession().getAttribute(STAFF_NAME_IN_SESSION);
    }

}
