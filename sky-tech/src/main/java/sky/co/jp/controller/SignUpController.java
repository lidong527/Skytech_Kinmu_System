package sky.co.jp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sky.co.jp.bean.AccountBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IKojinBusiness;
import sky.co.jp.form.SignUpForm;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.mapper.StatusMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SignUpController {

    @Autowired(required = false)
    StaffMapper staffMapper;
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;
    @Autowired(required = false)
    IKojinBusiness kojinBusiness;

    /**
     * 初期画面表示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/signUp")
    public String signUp(Model model, HttpServletRequest request, HttpSession session) {
        HeaderController header = new HeaderController();
        StaffBean staffBean = new StaffBean();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }
        // 画面情報設定
        SignUpForm signUpForm = new SignUpForm();

        //staffbg修改为string类型的联动修改
        //zhou 20201023
        staffBean = staffMapper.selectMsgByStaffbg(staffBg);

        signUpForm.setId(staffBg);
        signUpForm.setName(staffBean.getSTAFF_NAME());
        signUpForm.setItType(staffBean.getIT_TYPE());
        signUpForm.setMail(staffBean.getSTAFF_MAILADDRESS());
        signUpForm.setGenbanName(staffBean.getGENBAN_NAME());
        signUpForm.setGenbanAddress(staffBean.getGENBAN_ADDRESS());
        signUpForm.setSex(staffBean.getSTAFF_SEX());
        signUpForm.setStandardTime(String.valueOf(staffBean.getSTANDARD_TIME()));
        signUpForm.setUpperTime(String.valueOf(staffBean.getUPPER_TIME()));
        signUpForm.setStatus(String.valueOf(staffBean.getSTAFF_STATUS()));
        signUpForm.setRemarks(staffBean.getREMARKS());

        model.addAttribute("msg", null);
        model.addAttribute("signUpForm", signUpForm);

        return "signUp";
    }

    /**
     * 社員情報登録
     *
     * @param
     * @return
     */
    @PostMapping(value = "/signUp/insert")
    public String update(Model model, @ModelAttribute SignUpForm signUpForm,HttpServletRequest request,HttpSession session) {
        HeaderController header = new HeaderController();
        AccountBean bean = new AccountBean();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }
        //zhou 20201023
        bean = statusMapper.selectFlgByBg(staffBg);
        Boolean flag = bean.getINITIALIZATION_FLG();
        String msg;
        // パスワード関連チェック
        if (!signUpForm.getPassword().equals(signUpForm.getPasswordAgain())) {
            msg = "パスワード再入力誤り";

            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("msg", msg);
            return "signUp";
        }else if(flag&&(signUpForm.getPassword()==null||signUpForm.getPassword()=="")){
            msg = "パスワード未入力";

            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("msg", msg);
            return "signUp";
        }else if(signUpForm.getSex() == null){
            msg = "性別を入力してください";

            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("msg", msg);
            return "signUp";
        }else if(signUpForm.getStatus() == null){
            msg = "社員状態を入力してください";

            model.addAttribute("signUpForm", signUpForm);
            model.addAttribute("msg", msg);
            return "signUp";
        } else if(signUpForm.getItType() == null){
        msg = "業務タイプを入力してください";

        model.addAttribute("signUpForm", signUpForm);
        model.addAttribute("msg", msg);
        return "signUp";
    }
//        // 社員番号自動採番
//        String maxId = staffMapper.selectMaxId();
//        Integer max = Integer.parseInt(maxId) + 1;
//        signUpForm.setId(String.valueOf(max));

        StaffBean staffBean = new StaffBean();
        AccountBean accountBean = new AccountBean();
        //zhou 20201023
        staffBean.setSTAFF_BG(staffBg);
        staffBean.setSTAFF_MAILADDRESS(signUpForm.getMail());
        staffBean.setSTAFF_NAME(signUpForm.getName());
        staffBean.setIT_TYPE(signUpForm.getItType());
        staffBean.setSTAFF_SEX(signUpForm.getSex());
        staffBean.setSTAFF_STATUS(Integer.parseInt(signUpForm.getStatus()));
        staffBean.setGENBAN_NAME(signUpForm.getGenbanName());
        staffBean.setGENBAN_ADDRESS(signUpForm.getGenbanAddress());
        staffBean.setREMARKS(signUpForm.getRemarks());
        if(signUpForm.getStandardTime() != ""&&signUpForm.getStandardTime() != null){
            staffBean.setSTANDARD_TIME(Long.parseLong(signUpForm.getStandardTime()));
        }
        //UpperTimeは値があるなら、DBにインサートする
        if(signUpForm.getUpperTime() != "" &&signUpForm.getUpperTime() != null && "!null".equals(signUpForm.getUpperTime()) ){
            staffBean.setUPPER_TIME(Long.parseLong(signUpForm.getUpperTime()));
        }
        staffBean.setPERMISSION_BG(0);
        staffBean.setLM_KINMU_STATUS(0);
        staffBean.setLM_KOUTU_STATUS(0);
        staffBean.setLM_TATEKAE_STATUS(0);
        staffBean.setTM_KINMU_STATUS(0);
        staffBean.setTM_KOUTU_STATUS(0);
        staffBean.setTM_TATEKAE_STATUS(0);
        accountBean.setPASSWORD(signUpForm.getPassword());
        accountBean.setUSER_MAIL(signUpForm.getMail());


//        staffMapper.insertStaff(staffBean);
        accountBean.setPASS_SEC(header.encrypt(signUpForm.getPassword()));
        int updateResult = 0;
        //zhou 20201023
        int updateResult2 = staffMapper.updateStaff(staffBean.getSTAFF_NAME(),staffBean.getIT_TYPE(),staffBean.getSTAFF_SEX(),staffBean.getSTAFF_STATUS()
                ,staffBean.getSTANDARD_TIME(),staffBean.getUPPER_TIME(),staffBean.getGENBAN_NAME(),staffBean.getGENBAN_ADDRESS(),staffBean.getREMARKS(),accountBean.getUSER_MAIL(),staffBean.getSTAFF_BG());
        String password = accountBean.getPASSWORD();
        if(password != null&&password != "") {
            updateResult = staffMapper.updatePassword(accountBean.getPASSWORD(), accountBean.getUSER_MAIL(),accountBean.getPASS_SEC());
        }
        System.out.println(updateResult2+"&&"+updateResult);
        model.addAttribute("msg", null);
        StatusController statusController = new StatusController();
//        String statusRes = statusController.status(model, request, session, statusMapper, sessionMapper);
        String statusRes = kojinBusiness.kojin(model, request, session);
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
    @PostMapping(value = "/signUpBack")
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
