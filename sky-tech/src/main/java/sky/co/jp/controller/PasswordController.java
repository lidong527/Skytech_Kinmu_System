package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sky.co.jp.bean.AccountBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.form.SignUpForm;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.mapper.StatusMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Description:
 * Author: Lyu Zhongqin
 * Time: 2021-02-18 11:51
 */
@Controller
public class PasswordController {
    @Autowired(required = false)
    StaffMapper staffMapper;
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;

    /**
     * 初期画面表示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/passwordChange")
    public String passwordChange(Model model, HttpServletRequest request, HttpSession session) {
        HeaderController header = new HeaderController();
        StaffBean staffBean = new StaffBean();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg.equals("FALSE")){
            return "redirect:sky-tech";
        }
        // 画面情報設定
        SignUpForm signUpForm = new SignUpForm();

        staffBean = staffMapper.selectMsgByStaffbg(staffBg);

        signUpForm.setId(staffBg);
        signUpForm.setMail(staffBean.getSTAFF_MAILADDRESS());
        signUpForm.setName(staffBean.getSTAFF_NAME());

        model.addAttribute("msg", null);
        model.addAttribute("signUpForm", signUpForm);
        model.addAttribute("id", signUpForm.getId());
        model.addAttribute("name", signUpForm.getName());

        return "password-change";
    }

    /**
     * 社員情報登録
     *
     * @param
     * @return
     */
    @PostMapping(value = "/passwordChange/update")
    public String update(Model model, @ModelAttribute SignUpForm signUpForm, HttpServletRequest request, HttpSession session) {

        HeaderController header = new HeaderController();
        AccountBean bean = new AccountBean();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg.equals("FALSE")){
            return "redirect:sky-tech";
        }
        //zhou 20201023
        bean = statusMapper.selectFlgByBg(staffBg);
        Boolean flag = bean.getINITIALIZATION_FLG();
        String msg;
        // パスワード関連チェック
        if (signUpForm.getPasswordOld().equals(signUpForm.getPassword())){
            msg = "パスワードをチェンジしてください";
            model.addAttribute("signUpForm", signUpForm);
            System.out.println("signUpForm = " + signUpForm.getId());
            model.addAttribute("id", signUpForm.getId());
            model.addAttribute("name", signUpForm.getName());
            model.addAttribute("msg", msg);
            return "password-change";
        } else if( !signUpForm.getPasswordOld().equals(staffMapper.selectPasswordByMail(signUpForm.getMail()))){
            msg = "元のパスワード入力誤り";
            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("id", signUpForm.getId());
            model.addAttribute("name", signUpForm.getName());
            model.addAttribute("msg", msg);
            return "password-change";
        } else if (!signUpForm.getPassword().equals(signUpForm.getPasswordAgain())) {
            msg = "パスワード再入力誤り";
            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("id", signUpForm.getId());
            model.addAttribute("name", signUpForm.getName());
            model.addAttribute("msg", msg);
            return "password-change";
        }else if(flag&&(signUpForm.getPassword()==null|| signUpForm.getPassword().equals(""))){
            msg = "パスワード未入力";
            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("id", signUpForm.getId());
            model.addAttribute("name", signUpForm.getName());
            model.addAttribute("msg", msg);
            return "password-change";
        }

        StaffBean staffBean = new StaffBean();
        staffBean.setSTAFF_BG(staffBg);
        staffBean.setSTAFF_NAME(signUpForm.getName());
        //UpperTimeは値があるなら、DBにインサートする
        staffBean.setPERMISSION_BG(0);

        AccountBean accountBean = new AccountBean();
        accountBean.setUSER_MAIL(signUpForm.getMail());
        accountBean.setPASSWORD(signUpForm.getPassword());
        accountBean.setPASS_SEC(header.encrypt(signUpForm.getPassword()));

        int updateResult = 0;
        String password = accountBean.getPASSWORD();
        if(password != null&& !password.equals("")) {
            updateResult = staffMapper.updatePassword(accountBean.getPASSWORD(), accountBean.getUSER_MAIL(),accountBean.getPASS_SEC());
        }
        System.out.println(updateResult);
        model.addAttribute("msg", null);
        StatusController statusController = new StatusController();
        String statusRes = statusController.status(model, request, session, statusMapper, sessionMapper);
        if (statusRes != null) {

            return statusRes;
        }else{
            return "redirect:sky-tech";
        }
    }

    /**
     * 前の画面に戻る
     * @param model
     * @return
     */
    @PostMapping(value = "/passwordBack")
    public String back(Model model,HttpServletRequest request,HttpSession session) {

        SignUpForm signUpForm = new SignUpForm();
        HeaderController header = new HeaderController();
        AccountBean bean = new AccountBean();
        System.out.println("      ");
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }
        //zhou 20201023
        bean = statusMapper.selectFlgByBg(staffBg);
        Boolean flag = bean.getINITIALIZATION_FLG();
        if(flag){
            return "redirect:sky-tech";
        }

        return "redirect:backStatus";
    }
}