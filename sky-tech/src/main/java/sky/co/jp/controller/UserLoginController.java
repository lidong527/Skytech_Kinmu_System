package sky.co.jp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import sky.co.jp.bean.AccountBean;
import sky.co.jp.bean.SessionBean;
import sky.co.jp.business.IKojinBusiness;
import sky.co.jp.form.LoginForm;
import sky.co.jp.form.SignUpForm;
import sky.co.jp.mapper.*;
import sky.co.jp.util.SessionUtil;

@Controller
public class UserLoginController {
    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    StatusMapper statusMapper;

    @Autowired(required = false)
    AccountMapper accountMapper;

    @Autowired(required = false)
    SkyKojinMapper skyKojinMapper;

    @Autowired(required = false)
    IKojinBusiness kojinBusiness;

    //    @Autowired
//    IStaffService staffService;
    @RequestMapping("/")
    public String view(){
        return "../static/sky-home";
    }

    @RequestMapping(value = "/sky-tech", method = {RequestMethod.GET})
    public String skyd(Model model) {
        LoginForm account = new LoginForm();
        model.addAttribute("account", account);
        model.addAttribute("msg", null);
        return "skyindex";
    }

    @RequestMapping(value = "/skylogin")
    public String skylogind(Model model, @ModelAttribute LoginForm login, BindingResult bindingResult, HttpServletRequest request, HttpSession session) {
        HeaderController header = new HeaderController();
        String msg, result;
        AccountBean account = new AccountBean();
        String password = login.getPassword();
        String userMail = login.getUserMail();

        if (StringUtils.isEmpty(userMail) || StringUtils.isEmpty(userMail)) {
            msg = "メールアドレスまたはパスワードを入力してください";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyindex";
        }
        String result1 = header.encrypt(password);
        System.out.println(result1);
        String result2 = header.decrypt(result1);
        System.out.println(result2);
        //ユーザーメイルを利用し、暗号化したのパウワードを探す
        account = accountMapper.selectPAsswordByUserMail(userMail);
        if (account == null) {
            msg = "メールアドレス、パスワードの入力に誤りがあるか登録されていません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyindex";
        }
        String decodePassword = header.decrypt(account.getPASS_SEC());
        if (!password.equals(decodePassword)) {
            msg = "メールアドレス、パスワードの入力に誤りがあるか登録されていません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyindex";
        }
        //zhou 20201022
        SessionUtil.setCurrentAccount(account);
        SessionBean sessionBean = sessionMapper.selectstaffBgByMail(login.getUserMail(),login.getPassword());
        if (sessionBean==null) {
            msg = "社員情報に誤りがあるか登録されていません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyindex";
        }
        SessionUtil.setPermission(sessionBean.getPERMISSION_BG());
        //end
        result = header.createCookie(model, request, session, login, sessionMapper);
        System.out.println(result);
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        System.out.println(permBg);
        if(!permBg.equals("FALSE")){
            return "redirect:queryAllListBy";
        }
        if (!result.equals("redirect:sky-tech")) {
            if (account.getINITIALIZATION_FLG()) {
                SignUpForm signUpForm = new SignUpForm();
                signUpForm.setMail(userMail);
                signUpForm.setId(result);

                model.addAttribute("msg", "未初期化");
                model.addAttribute("signUpForm", signUpForm);
                return "signUp";
            }
            String statusRes = kojinBusiness.kojin(model, request, session);
            if (statusRes != null) {
                return statusRes;
            } else {
                return result;
            }
        } else {
            msg = "ユーザーは存在しません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyindex";
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(Model model, HttpSession session) {
        session.invalidate();
        LoginForm account = new LoginForm();
        model.addAttribute("account", account);
        model.addAttribute("msg", null);
        return "skyindex";
    }
}
//     @RequestMapping(value = "/koutuStaff")
//    public String koutuStaff(@ModelAttribute AccountBean account, Model model, HttpServletRequest request){
//        staffService.updateTmStatus(String.valueOf(request.getAttribute("STAFF_BG")));
//         StaffBean staff = staffService.getStaffByBG(String.valueOf(request.getAttribute("STAFF_BG")));
//         model.addAttribute("staff",staff);
//         return "staff/status";
//     }
//}
