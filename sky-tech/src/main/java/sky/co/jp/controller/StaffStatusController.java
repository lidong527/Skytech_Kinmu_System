package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.form.SearchForm;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.StaffReportQueryObject;
import sky.co.jp.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@Controller
public class StaffStatusController {

    @Autowired(required = false)
    SessionMapper sessionMapper;

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;

    @Autowired(required = false)
    StaffMapper staffMapper;

    @Autowired(required = false)
    IStaffBusiness staffBusiness;

    private static final int LIMIT = 15;
    private static final String PERM_BG = "PERM_BG";
    private static final String ALL = "ALL";


    @PostMapping("/list")
    public String list(Model model, @ModelAttribute("searchForm") SearchForm searchForm, HttpServletRequest request, HttpSession session, StaffBean staffBean) {
        HeaderController header = new HeaderController();
        String statusNameTat = null;
        String statusNameKin = null;
        String statusNameKou = null;
        String statusTat = null;
        String statusKin = null;
        String statusKou = null;
        String month = "TM";
        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg == "FALSE") {
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg == "FALSE") {
            return "redirect:sky-tech";
        }

        GetButtonCdBean permType = getBottonCdMapper.select3ByIdAnd1(PERM_BG, permBg);
        String perm = permType.getCODE_CONTENT3();
        if (perm.equals(ALL)) {
            perm = null;
        }
        String statusNameZen = searchForm.getStatusName().substring(2, 7);
        statusNameZen = statusNameZen.toUpperCase();
        if (statusNameZen.equals("TATEK")) {
            statusNameTat = "TATEKAE";
            statusTat = searchForm.getStatus();
        } else if (statusNameZen.equals("KINMU")) {
            statusNameKin = statusNameZen;
            statusKin = searchForm.getStatus();
        } else if (statusNameZen.equals("KOUTU")) {
            statusNameKou = statusNameZen;
            statusKou = searchForm.getStatus();
        }
        System.out.println(statusNameZen);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int usedMonthL = cal.get(Calendar.MONTH);
        int usedMonthT = cal.get(Calendar.MONTH) + 1;
        if (Integer.parseInt(searchForm.getMonth()) == 0) {
            month = "LM";
        }
//        else{
//            usedMonth = cal.get(Calendar.MONTH) + 1;
//        }
        if (usedMonthL == 0) {
            year -= 1;
            usedMonthL = 12;
        }

        searchForm.setStatusNameKin(statusNameKin);
        searchForm.setStatusNameKou(statusNameKou);
        searchForm.setStatusNameTat(statusNameTat);

        searchForm.setStatusKin(statusKin);
        searchForm.setStatusKou(statusKou);
        searchForm.setStatusTat(statusTat);

        String usedDateL = String.valueOf(year) + "年" + String.valueOf(usedMonthL) + "月";
        String usedDateT = String.valueOf(year) + "年" + String.valueOf(usedMonthT) + "月";
        List<StaffBean> staff = staffMapper.selectStatusByLot(null, statusNameKou, statusNameKin,
                statusNameTat, statusKou, statusKin,
                statusTat, month, usedDateL, usedDateT, LIMIT, 0, perm);

        int totalCount = staff.size();
        System.out.println(totalCount / LIMIT);
        if (totalCount / LIMIT == 0) {
            searchForm.setTotalPage("1");
        } else {
            searchForm.setTotalPage(String.valueOf(totalCount / LIMIT));
        }
        searchForm.setCurrentPage("1");

        model.addAttribute("qo", searchForm);
        model.addAttribute("result", staff);
        return "zaimu/allStatus-d";
    }

    @PostMapping("/allList")
    public String allList(Model model, HttpServletRequest request, HttpSession session, StaffBean staffBean) {
        HeaderController header = new HeaderController();
        SearchForm searchForm = new SearchForm();
        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg == "FALSE") {
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg == "FALSE") {
            return "redirect:sky-tech";
        }

        GetButtonCdBean permType = getBottonCdMapper.select3ByIdAnd1(PERM_BG, permBg);
        String perm = permType.getCODE_CONTENT3();
        if (perm.equals(ALL)) {
            perm = null;
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int usedMonthL = cal.get(Calendar.MONTH);
        int usedMonthT = cal.get(Calendar.MONTH) + 1;
        if (usedMonthL == 0) {
            year -= 1;
            usedMonthL = 12;
        }
        String usedDateL = String.valueOf(year) + "年" + String.valueOf(usedMonthL) + "月";
        String usedDateT = String.valueOf(year) + "年" + String.valueOf(usedMonthT) + "月";

        List<StaffBean> staff = staffMapper.selectStatusByLot(null, null, null,
                null, null, null,
                null, null, usedDateL, usedDateT, LIMIT, 0, perm);

        int totalCount = staff.size();
        System.out.println(totalCount / LIMIT);
        if (totalCount / LIMIT == 0) {
            searchForm.setTotalPage("1");
        } else {
            searchForm.setTotalPage(String.valueOf(totalCount / LIMIT));
        }
        searchForm.setCurrentPage("1");

        model.addAttribute("qo", searchForm);
        model.addAttribute("result", staff);
        return "zaimu/allStatus-d";
    }

    @PostMapping("/staffSearch")
    public String staffList(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("searchForm") SearchForm searchForm) {
        HeaderController header = new HeaderController();

        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg == "FALSE") {
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg == "FALSE") {
            return "redirect:sky-tech";
        }
        int month = Integer.parseInt(searchForm.getMonth());

        GetButtonCdBean permType = getBottonCdMapper.select3ByIdAnd1(PERM_BG, permBg);
        String perm = permType.getCODE_CONTENT3();
        if (perm.equals(ALL)) {
            perm = null;
        }


        if (month == 0) {
            searchForm.setMonth("LM");
            searchForm.setStatusKou(searchForm.getLmStatusKou());
            searchForm.setStatusKin(searchForm.getLmStatusKin());
            searchForm.setStatusTat(searchForm.getLmStatusTat());
        } else if (month == 1) {
            searchForm.setMonth("TM");
            searchForm.setStatusKou(searchForm.getTmStatusKou());
            searchForm.setStatusKin(searchForm.getTmStatusKin());
            searchForm.setStatusTat(searchForm.getTmStatusTat());
        }
        if (searchForm.getStatusKin().equals("")) {
            searchForm.setStatusKin(null);
        }
        if (searchForm.getStatusKou().equals("")) {
            searchForm.setStatusKou(null);
        }
        if (searchForm.getStatusTat().equals("")) {
            searchForm.setStatusTat(null);
        }

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int usedMonthL = cal.get(Calendar.MONTH);
        int usedMonthT = cal.get(Calendar.MONTH) + 1;
        if (usedMonthL == 0) {
            year -= 1;
            usedMonthL = 12;
        }
        String usedDateL = String.valueOf(year) + "年" + String.valueOf(usedMonthL) + "月";
        String usedDateT = String.valueOf(year) + "年" + String.valueOf(usedMonthT) + "月";

        List<StaffBean> staff = staffMapper.selectStatusByLot(searchForm.getStaffName(), searchForm.getStatusKou(), searchForm.getStatusKin(), searchForm.getStatusTat(), searchForm.getStatusKou(), searchForm.getStatusKin(), searchForm.getStatusTat(), searchForm.getMonth(), usedDateL, usedDateT, LIMIT, 0, perm);

        int totalCount = staff.size();
        System.out.println(totalCount / LIMIT);
        if (totalCount / LIMIT == 0) {
            searchForm.setTotalPage("1");
        } else {
            searchForm.setTotalPage(String.valueOf(totalCount / LIMIT));
        }
        searchForm.setCurrentPage("1");

        model.addAttribute("qo", searchForm);
        model.addAttribute("result", staff);
        return "zaimu/allStatus-d";
    }

    //zhou 修改成员状态确认页面controller
    //@PostMapping("/listAll")
    @RequestMapping(value ="/listAll")
    public String listAll(Model model, @ModelAttribute("qo") StaffReportQueryObject qo, StaffBean staffBean) {
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            model.addAttribute("msg","権限がないです。");
            return "error-staff";
        }

        qo.setTM_KINMU_STATUS(qo.getTM_KINMU_STATUS());
        qo.setTM_KOUTU_STATUS(qo.getTM_KOUTU_STATUS());
        qo.setTM_TATEKAE_STATUS(qo.getTM_TATEKAE_STATUS());

        PageResult<StaffBean> result = staffBusiness.queryList(qo);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        return "zaimu/allStatus";
    }


}
