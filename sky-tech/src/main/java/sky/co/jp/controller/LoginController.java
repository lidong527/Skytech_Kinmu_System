package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;
import sky.co.jp.bean.AccountBean;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.SessionBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.form.FlagForm;
import sky.co.jp.form.LoginForm;
import sky.co.jp.form.SignUpForm;
import sky.co.jp.form.StaffForm;
import sky.co.jp.mapper.AccountMapper;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StatusMapper;
import sky.co.jp.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private static final String LAST_MONTH = "LM";
    private static final String THIS_MONTH = "TM";

    private static final String STATUS = "STAFF_STATUS";

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    AccountMapper accountMapper;

    @Autowired(required = false)
    StatusMapper statusMapper;

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(Model model) {
        LoginForm account = new LoginForm();
        model.addAttribute("account", account);
        model.addAttribute("msg", null);
        return "skyLogin";
    }

    @PostMapping("/getInfo")
    public String getLoginForm(Model model, @ModelAttribute LoginForm login,HttpServletRequest request, HttpSession session) {
        HeaderController header = new HeaderController();
        String password = login.getPassword();
        String userMail = login.getUserMail();
        String msg;

        if (StringUtils.isEmpty(userMail) || StringUtils.isEmpty(userMail)) {
            msg = "メールアドレスまたはパスワードを入力してください";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyLogin";
        }
        //ユーザーメイルを利用し、暗号化したのパウワードを探す
        AccountBean account = accountMapper.selectPAsswordByUserMail(userMail);
        if (account == null){
            msg = "メールアドレス、パスワードの入力に誤りがあるか登録されていません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyLogin";
        }
        String decodePassword = header.decrypt(account.getPASS_SEC());
        if (!password.equals(decodePassword)) {
            msg = "メールアドレス、パスワードの入力に誤りがあるか登録されていません";
            model.addAttribute("account", login);
            model.addAttribute("msg", msg);
            return "skyLogin";
        }
        header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        SessionUtil.setCurrentAccount(account);
        SessionBean sessionBean = sessionMapper.selectstaffBgByMail(login.getUserMail(),login.getPassword());
        SessionUtil.setPermission(sessionBean.getPERMISSION_BG());
        SessionUtil.setCurrentStaffBG(sessionBean.getSTAFF_BG());
        if (account.getINITIALIZATION_FLG() == true) {
            SignUpForm signUpForm = new SignUpForm();
            signUpForm.setMail(userMail);

            signUpForm.setId(SessionUtil.getStaffBG());

            model.addAttribute("msg", "未初期化");
            model.addAttribute("signUpForm", signUpForm);
            return "signUp-t";
        }

        if (SessionUtil.getPermission() > 2){
            return "redirect:staff-t";
        }

        StaffBean staffBean = statusMapper.selectStatusByStaffBg(SessionUtil.getStaffBG());

        GetButtonCdBean getButtonCdBean = sessionMapper.selectContent2ByIdAndContent(STATUS,String.valueOf(staffBean.getSTAFF_STATUS()));
        String status = getButtonCdBean.getCODE_CONTENT2();
        StaffForm staff = new StaffForm();
        FlagForm flag = new FlagForm();

        staff.setStaffStatus(status);
        staff.setGanbanName(staffBean.getGENBAN_NAME());
        staff.setLmKinmu(staffBean.getLM_KINMU_STATUS());
        staff.setLmKoutu(staffBean.getLM_KOUTU_STATUS());
        staff.setLmTate(staffBean.getLM_TATEKAE_STATUS());
        staff.setStaffBg(String.valueOf(SessionUtil.getStaffBG()));
        staff.setStaffName(staffBean.getSTAFF_NAME());
        staff.setStaffSex(staffBean.getSTAFF_SEX());
        staff.setTmkinmu(staffBean.getTM_KINMU_STATUS());
        staff.setTmKoutu(staffBean.getTM_KOUTU_STATUS());
        staff.setTmTate(staffBean.getTM_TATEKAE_STATUS());
        staff.setStandardTime(staffBean.getSTANDARD_TIME());
        staff.setUpperTime(staffBean.getUPPER_TIME());
        flag.setLmFlag(LAST_MONTH);
        flag.setTmFlag(THIS_MONTH);


        model.addAttribute("staff", staff);
        model.addAttribute("flag", flag);
        model.addAttribute("msg", null);
        return "staff/status-t";

    }


}
