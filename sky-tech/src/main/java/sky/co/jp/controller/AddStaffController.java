package sky.co.jp.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sky.co.jp.form.AddStaffForm;
import sky.co.jp.mapper.AddStaffMapper;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StaffMapper;

@Controller
public class AddStaffController {

    @Autowired(required = false)
    StaffMapper staffMapper;

    @Autowired(required = false)
    AddStaffMapper addStaffMapper;

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    private static final int LENGTH = 10;

    @RequestMapping(value = "/addStaff", method = RequestMethod.GET)
    public String adstaffAdd(Model model, HttpServletRequest request, HttpSession session) {
        AddStaffForm addStaffForm = new AddStaffForm();
        HeaderController header = new HeaderController();
        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg.equals("FALSE")) {
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg.equals("FALSE")) {
            return "redirect:sky-tech";
        }

        String msg = "";

        model.addAttribute("msg", msg);
        model.addAttribute("addStaffForm", addStaffForm);
        return "zaimu/addStaff";
    }


    @RequestMapping(value = "/staffCommitCheck", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String staffCommit(Model model, @RequestBody List<AddStaffForm> addStaffForms, HttpServletRequest request, HttpSession session) {

//        int staffList = staffMapper.insertStaffer(addStaffForm.getSTAFF_BG(), addStaffForm.getSTAFF_MAILADDRESS());
        HeaderController header = new HeaderController();
        String checkStatus = header.CheckCookie(request, session, sessionMapper);
        if (checkStatus.equals("FALSE")) {
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg.equals("FALSE")) {
            return "redirect:sky-tech";
        }

        String msg = "";
        String staffBg = "";
        String email = "";
        String perm = "";
        String type = "";
        int contractType;
        int accountRes = 0;
        int staffRes = 0;

        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();

        String generatedString = null;
        String encode = null;

        for (AddStaffForm staff : addStaffForms) {

                if (staff.getStaffBg() != null && !staff.getStaffBg().equals("") &&
                        staff.getEmail() != null && !staff.getEmail().equals("") &&
                        staff.getPermission() != null && staff.getType() != null) {


                    for(int i=0;i<LENGTH;i++){
                        int number=random.nextInt(62);
                        sb.append(str.charAt(number));
                    }
                    generatedString = sb.toString();
                    encode = header.encrypt(generatedString);

                    staffBg = staff.getStaffBg();
                    email = staff.getEmail();
                    perm = staff.getPermission();
                    type = staff.getType();
                    contractType=staff.getContractType();

                    accountRes = addStaffMapper.insertAccount(email, generatedString, encode);
                    staffRes = addStaffMapper.insertStaff(Long.parseLong(staffBg), email, Integer.parseInt(perm), type.toUpperCase(),contractType);
                    if (accountRes == staffRes && accountRes == 1) {
                        msg += "社員番号:" + staffBg + "を入りました。パスワードは:" + generatedString + "\n";
                    }
                }

        }
        System.out.println(msg);
        model.addAttribute("msg", msg);
        // String jsonStr = "{\"success\":\"" + msg + "\"}";
        return msg;
        //AddStaffForm addStaffForm = new AddStaffForm();
        //model.addAttribute("addStaffForm", addStaffForm);
        //return "zaimu/addStaff";
    }
}
