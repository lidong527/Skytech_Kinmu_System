package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.form.FlagForm;
import sky.co.jp.form.LoginForm;
import sky.co.jp.form.StaffForm;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StatusMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class StatusController {

    private static final String STATUS = "STAFF_STATUS";
    private static final String LAST_MONTH = "LM";
    private static final String THIS_MONTH = "TM";
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;

    public String status(Model model, HttpServletRequest request, HttpSession session,StatusMapper statusMapper,SessionMapper sessionMapper) {
        HeaderController header = new HeaderController();
        StaffBean staffBean = new StaffBean();
        GetButtonCdBean getButtonCdBean = new GetButtonCdBean();
        StaffForm staff = new StaffForm();
        FlagForm flag = new FlagForm();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }

        //zhou 20201023
        //staffbg修改为string类型的联动修改
        staffBean = statusMapper.selectStatusByStaffBg(staffBg);

        getButtonCdBean = sessionMapper.selectContent2ByIdAndContent(STATUS,String.valueOf(staffBean.getSTAFF_STATUS()));
        String status = getButtonCdBean.getCODE_CONTENT2();
        staff.setStaffStatus(status);
        staff.setGanbanName(staffBean.getGENBAN_NAME());
        staff.setGenbanAddress(staffBean.getGENBAN_ADDRESS());
        staff.setItType(staffBean.getIT_TYPE());
        staff.setRemarks(staffBean.getREMARKS());
        staff.setLmKinmu(staffBean.getLM_KINMU_STATUS());
        staff.setLmKoutu(staffBean.getLM_KOUTU_STATUS());
        staff.setLmTate(staffBean.getLM_TATEKAE_STATUS());
        staff.setStaffBg(staffBg);
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
        return "staff/status";
    }

    @RequestMapping(value = "/backStatus", method = {RequestMethod.POST})
    public String setOhtersForm(Model model, HttpServletRequest request, HttpSession session) {
        HeaderController header = new HeaderController();
        StaffBean staffBean = new StaffBean();
        GetButtonCdBean getButtonCdBean = new GetButtonCdBean();
        StaffForm staff = new StaffForm();
        FlagForm flag = new FlagForm();
        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }

        //zhou 20201023
        //staffbg修改为string类型的联动修改
        staffBean = statusMapper.selectStatusByStaffBg(staffBg);


        getButtonCdBean = sessionMapper.selectContent2ByIdAndContent(STATUS,String.valueOf(staffBean.getSTAFF_STATUS()));
        String status = getButtonCdBean.getCODE_CONTENT2();
        staff.setStaffStatus(status);
        staff.setGanbanName(staffBean.getGENBAN_NAME());
        staff.setItType(staffBean.getIT_TYPE());
        staff.setRemarks(staffBean.getREMARKS());
        staff.setGenbanAddress(staffBean.getGENBAN_ADDRESS());
        staff.setLmKinmu(staffBean.getLM_KINMU_STATUS());
        staff.setLmKoutu(staffBean.getLM_KOUTU_STATUS());
        staff.setLmTate(staffBean.getLM_TATEKAE_STATUS());
        staff.setStaffBg(staffBg);
        staff.setStaffName(staffBean.getSTAFF_NAME());
        staff.setStaffSex(staffBean.getSTAFF_SEX());
        staff.setStandardTime(staffBean.getSTANDARD_TIME());
        staff.setTmkinmu(staffBean.getTM_KINMU_STATUS());
        staff.setTmKoutu(staffBean.getTM_KOUTU_STATUS());
        staff.setTmTate(staffBean.getTM_TATEKAE_STATUS());
        staff.setUpperTime(staffBean.getUPPER_TIME());
        flag.setLmFlag(LAST_MONTH);
        flag.setTmFlag(THIS_MONTH);

        model.addAttribute("staff", staff);
        model.addAttribute("flag", flag);
        model.addAttribute("msg", null);
        return "staff/status";
    }

    @RequestMapping(value = "/windowClose", method = {RequestMethod.GET})
    public String close(Model model,HttpSession session) {
        LoginForm account = new LoginForm();
        HeaderController head = new HeaderController();
        model.addAttribute("account", account);
        model.addAttribute("msg", null);
        String result = head.deleteCookie(session);
        return result;
    }
}

