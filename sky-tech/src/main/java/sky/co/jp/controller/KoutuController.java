package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IKoutuBusiness;
import sky.co.jp.business.IQueryBusiness;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.form.*;
import sky.co.jp.mapper.*;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.JsonResult;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class KoutuController {
    private static final String KOUTU_STATUS_FLAG = "KOUTU";
    private final String FILE_PATH_KOUTSU = "FILE_PATH_KOUTSU";
    private static final String FILE_NAME = "fileName";
    private static final String FILE_PATH = "filePath";
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;
    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;
    @Autowired(required = false)
    KoutuMapper koutuMapper;
    @Autowired(required = false)
    StaffMapper staffMapper;
    @Autowired(required = false)
    IQueryBusiness iQueryBusiness;

    @Autowired
    IKoutuBusiness koutuBusiness;
    @Autowired
    IStaffBusiness staffBusiness;

    @RequestMapping(value = "/koutu")
    public String KoutuFirst(FlagForm flag,Model model, HttpServletRequest request, HttpSession session) throws ParseException {
        KoutuForm koutu = new KoutuForm();
        KoutuBean koutuBean = new KoutuBean();
        StaffBean staffBean = new StaffBean();
        ImportForm importForm = new ImportForm();
        HeaderController headerController = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();

        downloadForm.setDownType(FILE_PATH_KOUTSU);
        String staffBg = headerController.CheckCookie(request,session,sessionMapper);
        if(staffBg =="FALSE"){
            return "redirect:sky-tech";
        }

        Date usedDate = DateUtil.getGyomuMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(usedDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);
        //liuyang 20201029 usedTime类型联动修改
        String monthKoutu = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        //更新日を取得する
        String upDate = String.valueOf(year)+"年"+String.valueOf(month)+"月"+String.valueOf(day)+"日";
        System.out.println(upDate+",,,,,");
        System.out.println(staffBg+"=============");
        System.out.println(usedDate+"-------------");
        koutuBean = koutuMapper.selectByBgAndTime(staffBg,usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);

        if(koutuBean != null) {
            koutu.setStartSt(koutuBean.getSTART_ST());
            koutu.setEndSt(koutuBean.getEND_ST());
            koutu.setUsedMoneyEtc(koutuBean.getUSED_MONEY_ETC());
            koutu.setUsedMoneyAll(koutuBean.getUSED_MONEY_ALL());

            koutu.setUsedMoneyTsu(koutuBean.getUSED_MONEY_TSUKIN());
            koutu.setFileName(koutuBean.getFILE_NAME());
        }

        koutu.setStaffName(staffBean.getSTAFF_NAME());
        koutu.setUsedTime(usedDate);
        koutu.setUpTime(upDate);
        koutu.setStaffBg(staffBg);
        downloadForm.setDownTime(monthKoutu);
        model.addAttribute("koutuList",koutu);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        return "staff/KoutuAdd";
    }

    @RequestMapping(value = "/koutuInsert")
    public String insertKoutu(@ModelAttribute KoutuForm koutuList, Model model, HttpServletRequest request,HttpSession session,@RequestParam("fileUpload") MultipartFile file,RedirectAttributes redirectAttributes) throws ParseException {
        StatusController statusController = new StatusController();
        HeaderController headerController = new HeaderController();
        FileController fileUpload = new FileController();
        StaffBean staffBean = new StaffBean();
        KoutuBean koutu = new KoutuBean();
        String result ="FALSE";
        int sqlResult = 0;
        String staffBg = headerController.CheckCookie(request,session,sessionMapper);

        Date usedDate = DateUtil.getGyomuMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(usedDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);

        koutuList.setUsedTime(usedDate);
        System.out.println(koutuList.getUsedTime());
        String monthKoutu = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        if(staffBg =="FALSE"){
            return "redirect:sky-tech";
        }

        if(!file.isEmpty()) {
       /*     System.out.println(file.getOriginalFilename());
            result = fileUpload.singleFileUpload(file,
                    redirectAttributes, FILE_PATH_KOUTSU, getBottonCdMapper, staffBg, monthKoutu);
            System.out.println(result);
        }
        if(result!="FALSE"){
            koutu.setFILE_NAME(result);
        }*/
       try {


        if(!file.isEmpty()) {
            Map<String, String> fileMap = fileUpload.fileUpload(file,
                    redirectAttributes, FILE_PATH_KOUTSU, getBottonCdMapper, staffBg, monthKoutu);
            koutu.setFILE_NAME(fileMap.get(FILE_NAME));
            koutu.setFILE_URL(fileMap.get(FILE_PATH));
        }}catch (Exception e){
            model.addAttribute("msg","...");
            return "error-admin";
        }
        }
        KoutuBean koutuRes = koutuMapper.selectIdByBgAndTime(staffBg,koutuList.getUsedTime());
        //staffbg数据类型修改为string的联动修改
        //zhou 20201023
        koutu.setSTAFF_BG(staffBg);
        koutu.setUP_TIME(koutuList.getUpTime());

        // liuyang 20201029 时间需修改
        koutu.setUSED_TIME(koutuList.getUsedTime());
        System.out.println(koutuList.getUsedTime());
        System.out.println(koutu.getUSED_TIME());

        koutu.setSTART_ST(koutuList.getStartSt());
        koutu.setEND_ST(koutuList.getEndSt());
        koutu.setUSED_MONEY_ALL(koutuList.getUsedMoneyAll());
        koutu.setUSED_MONEY_ETC(koutuList.getUsedMoneyEtc());
        koutu.setUSED_MONEY_TSUKIN(koutuList.getUsedMoneyTsu());
        String [] strs = koutuList.getUpTime().split("[月]");
        String flg = strs[0]+ "月";
        if(koutuRes!= null){
            sqlResult =koutuMapper.updateByBgAndTime(koutu);
        }else{
            sqlResult = koutuMapper.insert(koutu);
        }
        if(sqlResult == 1){
            staffBean.setSTAFF_BG(staffBg);
            staffBean.setTM_KOUTU_STATUS(1);
            sqlResult = statusMapper.updateStatusByBg(staffBean);
        }
        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);
        return result1;

    }
    @PostMapping(value="/koutuCheck" )
    public String kinmuCheck(@ModelAttribute CheckStatusForm checkStatus, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws ParseException {
        KoutuForm koutu = new KoutuForm();
        KoutuBean koutuBean = new KoutuBean();
        StaffBean staffBean = new StaffBean();
        HeaderController header = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();

        downloadForm.setDownType(FILE_PATH_KOUTSU);
        String loginStatus = header.CheckCookie(request,session,sessionMapper);
        if(loginStatus == "FALSE"){
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if (permBg == "FALSE"){
            return "redirect:sky-tech";
        }
        String lFlg = checkStatus.getMonth();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        if(lFlg.equals("TM")){
            month = cal.get(Calendar.MONTH) + 1;
        }
        if (month == 0) {
            year -= 1;
            month = 12;
        }
        String staffBg = checkStatus.getStaffBg();
        String status = checkStatus.getStatus();
        String monthKoutu = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        Date usedDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        usedDate = formatter.parse(monthKoutu);
        koutuBean = koutuMapper.selectByBgAndTime(staffBg,usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);

        if(koutuBean != null) {
            koutu.setStartSt(koutuBean.getSTART_ST());
            koutu.setEndSt(koutuBean.getEND_ST());
            koutu.setUsedMoneyAll(koutuBean.getUSED_MONEY_ALL());
            koutu.setUsedMoneyEtc(koutuBean.getUSED_MONEY_ETC());
            koutu.setUsedMoneyTsu(koutuBean.getUSED_MONEY_TSUKIN());
        }

        koutu.setStaffName(staffBean.getSTAFF_NAME());
        koutu.setUsedTime(usedDate);
        koutu.setUpTime(upDate);
        koutu.setStaffBg(staffBg);
        downloadForm.setDownTime(monthKoutu);
        if(Integer.parseInt(status) == 2){
            koutu.setButtonClass("disableButton");
        }
        model.addAttribute("koutuList",koutu);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        model.addAttribute("staffBg",staffBg);
        return "zaimu/koutuCheck";
    }
    @PostMapping(value="/koutuOK" )
    public String KinmuOk(@ModelAttribute KoutuForm koutu, BindingResult bindingResult,Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        StatusController statusController = new StatusController();
        HeaderController header = new HeaderController();
        FileController fileUpload = new FileController();
        StaffBean staffBean = new StaffBean();
        KoutuBean koutuBean = new KoutuBean();
        String result ="FALSE";
        int sqlResult = 0;
        String msg = "";
        String loginStatus = header.CheckCookie(request,session,sessionMapper);
        if(loginStatus == "FALSE"){
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if (permBg == "FALSE"){
            return "redirect:sky-tech";
        }

        if(koutu != null){
            staffBean.setSTAFF_BG(koutu.getStaffBg());
            String [] strs = koutu.getUpTime().split("[月]");
            String flg = strs[0]+ "月";
            if(flg.equals(koutu.getUsedTime())){
                staffBean.setTM_KOUTU_STATUS(2);
            }else {
                staffBean.setLM_KOUTU_STATUS(2);
            }
            sqlResult = statusMapper.updateStatusByBg(staffBean);
        }
        if(sqlResult == 1){
            msg = "承認成功";

        }else{
            msg = "承認を失敗されました。";
        }
//        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);
        model.addAttribute("koutuList", koutu);
        model.addAttribute("msg",msg);
        model.addAttribute("downType","");
        model.addAttribute("downTime","");
        model.addAttribute("staffBg","");
        return ("zaimu/koutuCheck");
    }
    // liuyang 20201103 管理员根据条件查询
    @RequestMapping(value ="/koutuQueryList")
    public String koutuQueryList(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            return "redirect:sky-tech";
        }
        //zhou 20201112
        String staffBg= request.getParameter("staffBG");


        //zhou 20201112 如果staffBg不为空，已承认页面跳转
        if (staffBg!=null && staffBg !=""){
            qo.setSTAFF_BG(staffBg);
        }
        //liuyang 20201129 系統年份查詢
        List<Date> date = koutuMapper.selectTimeYear();
        Calendar cal = Calendar.getInstance();
        List<Integer> timeYear = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();
        for (Date i:date) {
            cal.setTime(i);
            timeYear.add(cal.get(Calendar.YEAR));
            set.addAll(timeYear);
            timeYear.clear();
            timeYear.addAll(set);
        }
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(qo.getTimeStarYear() == null || qo.getTimeStarMonth() == null || qo.getTimeEndYear() ==null || qo.getTimeEndMonth() == null){
            qo.setUsedTimeStart(null);
            qo.setUsedTimeEnd(null);
        }else {
            qo.setUsedTimeStart(sdf.parse(qo.getTimeStarYear() + "-" + qo.getTimeStarMonth() + "-" + "01"));
            qo.setUsedTimeEnd(sdf.parse(qo.getTimeEndYear() + "-" + qo.getTimeEndMonth() + "-" + "01"));
        }
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryList(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/adminKoutuList";
    }
    // liuyang 20201103 员工根据条件查询
    @RequestMapping(value ="/koutuQueryListByBg")
    public String koutuQueryListByBg(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {

        //获取管理员staffBg
        HeaderController header = new HeaderController();
        String staffBg = header.CheckCookie(request, session, sessionMapper);

        if (staffBg == "FALSE") {
            return "redirect:sky-tech";
        }

        if (staffBg!=null && staffBg !=""){
            qo.setSTAFF_BG(staffBg);
        }
        //liuyang 20201129 系統年份查詢
        List<Date> date = koutuMapper.selectTimeYear();
        Calendar cal = Calendar.getInstance();
        List<Integer> timeYear = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();
        for (Date i:date) {
            cal.setTime(i);
            timeYear.add(cal.get(Calendar.YEAR));
            set.addAll(timeYear);
            timeYear.clear();
            timeYear.addAll(set);
        }
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(qo.getTimeStarYear() == null || qo.getTimeStarMonth() == null || qo.getTimeEndYear() ==null || qo.getTimeEndMonth() == null){
            qo.setUsedTimeStart(null);
            qo.setUsedTimeEnd(null);
        }else {
            qo.setUsedTimeStart(sdf.parse(qo.getTimeStarYear() + "-" + qo.getTimeStarMonth() + "-" + "01"));
            qo.setUsedTimeEnd(sdf.parse(qo.getTimeEndYear() + "-" + qo.getTimeEndMonth() + "-" + "01"));
            //System.out.println(timestart);
        }
        qo.setSTAFF_BG(staffBg);
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryList(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/memberKoutuList";
    }

    // liuyang 20201106 管理员根据条件查询所有交通、勤务、立替
    @RequestMapping(value ="/queryAllListBy")
    public String queryAllListBy(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {
        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            return "redirect:sky-tech";
        }
        //liuyang 20201129 系統年份查詢
        List<Date> date = koutuMapper.selectTimeYear();
        Calendar cal = Calendar.getInstance();
        List<Integer> timeYear = new ArrayList<>();
        Set<Integer> set = new LinkedHashSet<>();
        for (Date i:date) {
            cal.setTime(i);
            timeYear.add(cal.get(Calendar.YEAR));
            set.addAll(timeYear);
            timeYear.clear();
            timeYear.addAll(set);
        }
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(qo.getTimeStarYear() == null || qo.getTimeStarMonth() == null || qo.getTimeEndYear() ==null || qo.getTimeEndMonth() == null){
            qo.setUsedTimeStart(null);
            qo.setUsedTimeEnd(null);
        }else {
            qo.setUsedTimeStart(sdf.parse(qo.getTimeStarYear() + "-" + qo.getTimeStarMonth() + "-" + "01"));
            qo.setUsedTimeEnd(sdf.parse(qo.getTimeEndYear() + "-" + qo.getTimeEndMonth() + "-" + "01"));
        }
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryListByAll(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/adminAllList";
    }




    @RequestMapping(value ="/koutuKakunin")
    @ResponseBody
    public KoutuForm koutuKakunin(CheckStatusForm checkStatusForm,@RequestParam("staffBG") String staffBG,Model model)  throws ParseException {

        KoutuForm koutuForm = new KoutuForm();

        if (SessionUtil.getCurrentAccount()== null){
            koutuForm.setMFlag("false");
            model.addAttribute("koutu", koutuForm);
            return koutuForm;
        }
        if (SessionUtil.getPermission()<2){
            koutuForm.setMFlag("false");
            model.addAttribute("koutu", koutuForm);
            return koutuForm;
        }
        try {
            koutuForm = koutuBusiness.getKoutuByBG(staffBG);
            model.addAttribute("koutu", koutuForm);
            return koutuForm;

        } catch (NullPointerException e){
            koutuForm.setErrorMessage("詳細内容がないので、管理者に連絡してください。");
            koutuForm.setMFlag("false");
            return koutuForm;
        }catch (Exception e){
            model.addAttribute("msg","エラーが発生しました。もう一度お試して下さい。");
            koutuForm.setMFlag("false");
            model.addAttribute("koutu", koutuForm);
            return koutuForm;
        }

    }

    /**
     * @description: 勤務表の承認
    [kinmuForm]
     * @return: sky.co.jp.queryData.JsonResult
     * @author: zhou
     * @date: 2020/11/06
     */
    @PostMapping("/koutuSyonin")
    @ResponseBody
    public JsonResult koutuSyonin(@ModelAttribute KoutuForm koutu) {
        JsonResult result = new JsonResult() ;
        try {
            //service.saveOrUpdate(d);
            staffBusiness.updateKoutuStatus(koutu,KOUTU_STATUS_FLAG);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            result.mark("失败しました。");
        }
        return result;
    }
}
