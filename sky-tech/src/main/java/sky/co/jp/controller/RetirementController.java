package sky.co.jp.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IRetirementBusiness;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.mapper.RetirementMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.queryData.StaffReportQueryObject;
import sky.co.jp.util.SessionUtil;
import sky.co.jp.util.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 * Author: Lyu Zhongqin
 * Time: 2021-02-18 14:17
 */
@Controller
public class RetirementController {
    @Autowired(required = false)
    StaffMapper staffMapper;

    @Autowired(required = true)
    RetirementMapper retirementMapper;

    @Autowired
    IStaffBusiness staffBusiness;

    @Autowired
    IRetirementBusiness retirementBusiness;

    @RequestMapping(value = "/retirement")
    public String retirementMain(Model model, @ModelAttribute("qo") StaffReportQueryObject qo, StaffBean staffBean, HttpServletRequest request){
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            model.addAttribute("msg","権限がないです。");

            return "error-staff";
        }

        PageResult<StaffBean> result = retirementBusiness.queryRetirement(qo);
        System.out.println("result = " + result);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        return "retirement";
    }

    @RequestMapping(value = "/retirementCheck")
    @ResponseBody
    public int retirementCheck(Model model, @RequestParam("staffBG") String staffBg, StaffBean staffBean, HttpServletRequest request){
        int check = 0;
        check = retirementBusiness.retire(staffBg);
        return check;
    }

}