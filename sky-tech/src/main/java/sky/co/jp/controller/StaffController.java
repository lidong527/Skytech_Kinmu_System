package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.bean.StaffStatusBean;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.form.StaffStatusForm;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.StaffReportQueryObject;
import sky.co.jp.util.SessionUtil;
import sky.co.jp.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StaffController {

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    @Autowired(required = false)
    StaffMapper staffMapper;

    @Autowired
    IStaffBusiness staffBusiness;

    private static final String JAVA = "JAVA";
    private static final String SAP = "SAP";
    private static final String VIEW_PERM_ALL = "ALL";
    private static final String PERM_BG = "PERM_BG";


//    @RequestMapping(value = "/test", method = {RequestMethod.POST})
//    public String test(Model model, @ModelAttribute("status") String status, StaffBean staff, StaffReportQueryObject qo) {
//        PageInfo<StaffBean> pageInfo = staffService.query(qo);
//        model.addAttribute("pageInfo",pageInfo);
//        model.addAttribute("qo",qo);
//        return "financial/allStatus-t";
//    }
//
//    @RequestMapping(value ="/input")
//    public String input(Model model, HttpSession session){
//        session.setAttribute("USER_MAIL","admin@email.com");
//        StaffBean staff=new StaffBean();
//        model.addAttribute("staff",staff);
//        return "staff/input";
//    }
//    @PostMapping(value = "/insertStaff")
//    public String insterStaff(@ModelAttribute StaffBean staff, Model model,@ModelAttribute("PASSWORD")String password){
//        //String STAFF_MAILADDRESS=(String) session.getAttribute("USER_MAIL");
//
//        staffService.insertStaff(staff,password);
//       // request.setAttribute("PASSWORD",PASSWORD);
//        staff.setLM_KINMU_STATUS(0);
//        staff.setLM_KOUTU_STATUS(0);
//        staff.setLM_TATEKAE_STATUS(0);
//        staff.setTM_KINMU_STATUS(0);
//        staff.setTM_KOUTU_STATUS(0);
//        staff.setTM_TATEKAE_STATUS(0);
//
//        model.addAttribute("staff",staff);
//        return "staff/status";
//    }
    
    @RequestMapping(value = "/staff")
    public String selectByJava(Model model, HttpServletRequest request,HttpSession session, StaffBean staffBean){
        HeaderController header = new HeaderController();
        GetButtonCdBean permType = new GetButtonCdBean();
        StaffStatusForm map = new StaffStatusForm();
        StaffStatusForm map1 = new StaffStatusForm();
        StaffStatusBean bean = new StaffStatusBean();
        String viewType = null;

        String staffBg = header.CheckCookie(request,session,sessionMapper);
        if(staffBg=="FALSE"){
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if (permBg == "FALSE"){
            return "redirect:sky-tech";
        }

        permType = getBottonCdMapper.select3ByIdAnd1(PERM_BG,permBg);

        if(!permType.getCODE_CONTENT3().equals(JAVA)){
            bean = staffMapper.selectCountStatusByType(SAP);
            map1.setLmKinmuStatus(bean.getLM_KINMU_STATUS());
            map1.setLmKoutuStatus(bean.getLM_KOUTU_STATUS());
            map1.setLmKinmuStatusCommit(bean.getLM_KINMU_STATUS_COM());
            map1.setLmKoutuStatusCommit(bean.getLM_KOUTU_STATUS_COM());
            map1.setLmTatekaeStatusCommit(bean.getLM_TATEKAE_STATUS_COM());

            map1.setTmKinmuStatus(bean.getTM_KINMU_STATUS());
            map1.setTmKoutuStatus(bean.getTM_KOUTU_STATUS());
            map1.setTmKinmuStatusCommit(bean.getTM_KINMU_STATUS_COM());
            map1.setTmKoutuStatusCommit(bean.getTM_KOUTU_STATUS_COM());
            map1.setTmTatekaeStatusCommit(bean.getTM_TATEKAE_STATUS_COM());
            viewType = SAP;
        }
        if(!permType.getCODE_CONTENT3().equals(SAP)) {
            bean = staffMapper.selectCountStatusByType(JAVA);
            map.setLmKinmuStatus(bean.getLM_KINMU_STATUS());
            map.setLmKoutuStatus(bean.getLM_KOUTU_STATUS());
            map.setLmKinmuStatusCommit(bean.getLM_KINMU_STATUS_COM());
            map.setLmKoutuStatusCommit(bean.getLM_KOUTU_STATUS_COM());
            map.setLmTatekaeStatusCommit(bean.getLM_TATEKAE_STATUS_COM());

            map.setTmKinmuStatus(bean.getTM_KINMU_STATUS());
            map.setTmKoutuStatus(bean.getTM_KOUTU_STATUS());
            map.setTmKinmuStatusCommit(bean.getTM_KINMU_STATUS_COM());
            map.setTmKoutuStatusCommit(bean.getTM_KOUTU_STATUS_COM());
            map.setTmTatekaeStatusCommit(bean.getTM_TATEKAE_STATUS_COM());
            if(viewType == null){
                viewType = JAVA;
            }else{
                viewType = "ALL";
            }
        }
        model.addAttribute("map",map);
        model.addAttribute("map1",map1);
        model.addAttribute("viewType", viewType);
        return "zaimu/statusFinance";
    }

    @RequestMapping(value = "/listContract")
    public String selectByJava(Model model, @ModelAttribute("qo") StaffReportQueryObject qo, StaffBean staffBean,HttpServletRequest request) {
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            model.addAttribute("msg","権限がないです。");

            return "error-staff";
        }
        if (!StringUtil.hasLength(qo.getSTAFF_BG())){
            qo.setSTAFF_BG(null);
        }
        String contractType = request.getParameter("CONTRACT_TYPE");
        if (StringUtil.hasLength(contractType)){
            qo.setContractType(Integer.valueOf(contractType));
            switch (Integer.valueOf(contractType)){
                case 0:qo.setCurrentMenu("staffContractList-A");
                break;
                case 1:qo.setCurrentMenu("staffContractList-B");
                break;
                case 2:qo.setCurrentMenu("staffContractList-C");
                break;
            }
        }
        PageResult<StaffBean> result = staffBusiness.queryContractList(qo);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        return "zaimu/staffContractList";

    }

    @RequestMapping(value = "/queryByType")
    public String queryByIttype(Model model, HttpServletRequest request,HttpSession session, StaffBean staffBean){
        GetButtonCdBean permType = new GetButtonCdBean();
        StaffStatusForm map = new StaffStatusForm();
        StaffStatusForm map1 = new StaffStatusForm();
        StaffStatusBean bean = new StaffStatusBean();
        String viewType = null;
        Integer permission = SessionUtil.getPermission();
        String it_type = request.getParameter("IT_TYPE");
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (permission !=null && permission<2){
            model.addAttribute("msg","権限がないです。");
            model.addAttribute("currentMenu","detailCount-"+it_type);

            return "error-staff";
        }
        if (it_type == null){
            model.addAttribute("msg","エラーが発生しました。");
            return "error-admin";
        }



        permType = getBottonCdMapper.select3ByIdAnd1(PERM_BG,String.valueOf(permission));

        if(permType.getCODE_CONTENT3().equals(VIEW_PERM_ALL) || permType.getCODE_CONTENT3().equals(it_type)){
            bean = staffMapper.selectCountStatusByType(it_type);

            map.setTmKinmuStatus(bean.getTM_KINMU_STATUS());
            map.setTmKoutuStatus(bean.getTM_KOUTU_STATUS());
            map.setTmKinmuStatusCommit(bean.getTM_KINMU_STATUS_COM());
            map.setTmKoutuStatusCommit(bean.getTM_KOUTU_STATUS_COM());
            map.setTmTatekaeStatusCommit(bean.getTM_TATEKAE_STATUS_COM());
           // viewType = SAP;
        }
        else{
            model.addAttribute("msg","権限がないです。");
            model.addAttribute("currentMenu","detailCount-"+it_type);
            return "error-admin";
        }
        model.addAttribute("map",map);
        model.addAttribute("IT_TYPE",it_type);
        return "zaimu/detailCount";
    }


}
