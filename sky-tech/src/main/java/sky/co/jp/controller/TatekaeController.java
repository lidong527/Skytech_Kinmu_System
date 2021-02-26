package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.bean.TatekaeBean;
import sky.co.jp.business.IQueryBusiness;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.business.ITatekaeBusiness;
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
public class TatekaeController {
    private static final String TATEKAE_STATUS_FLAG = "TATEKAE";
    private final String FILE_PATH_TATEKAE = "FILE_PATH_TATEKAE";
    private static final String FILE_NAME = "fileName";
    private static final String FILE_PATH = "filePath";
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;
    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;
    @Autowired(required = false)
    TatekaeMapper tatekaeMapper;
    @Autowired(required = false)
    StaffMapper staffMapper;
    @Autowired(required = false)
    IQueryBusiness iQueryBusiness;
    @Autowired(required = false)
    ITatekaeBusiness tatekaeBusiness;
    @Autowired(required = false)
    IStaffBusiness staffBusiness;
    @Autowired
    KoutuMapper koutuMapper;
    @RequestMapping(value = "/tatekae")
    public String TatekaeFirst(FlagForm flag,Model model, HttpServletRequest request, HttpSession session) throws ParseException {
        TatekaeForm tatekaeForm = new TatekaeForm();
        TatekaeBean tatekaeBean = new TatekaeBean();
        StaffBean staffBean = new StaffBean();
        HeaderController headerController = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();
        downloadForm.setDownType(FILE_PATH_TATEKAE);
        String staffBg = headerController.CheckCookie(request,session,sessionMapper);
        if(staffBg =="FALSE"){
            return "redirect:sky-tech";
        }
        String lFlg = flag.getLmFlag();
        String tFlg = flag.getTmFlag();
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        if(lFlg == null){
            month = cal.get(Calendar.MONTH) + 1;
        }
        if (month == 0) {
            year -= 1;
            month = 12;
        }
        //20201022,趙追加,str.
        //年月日を取得する
        String monthKubun = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";

        Date usedDate = DateUtil.getGyomuMonth();
        tatekaeBean = tatekaeMapper.selectByBgAndTime(staffBg,usedDate);
        //staffbg修改为string类型的联动修改
        //zhou 20201023
        staffBean = staffMapper.selectNameByBg(staffBg);

        if(tatekaeBean != null) {

            tatekaeForm.setUSED_PRICE(tatekaeBean.getUSED_PRICE());
            tatekaeForm.setFILE_NAME(tatekaeBean.getFILE_NAME());
            tatekaeForm.setSUB_COMPANY(tatekaeBean.getSUB_COMPANY());
        }
        tatekaeForm.setUSER_NAME(staffBean.getSTAFF_NAME());
        tatekaeForm.setUSED_TIME(usedDate);
        tatekaeForm.setUP_TIME(upDate);
        tatekaeForm.setSTAFF_BG(staffBg);
        downloadForm.setDownTime(monthKubun);
        model.addAttribute("tatekae",tatekaeForm);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        return "staff/tatekaekin";
    }

    @RequestMapping(value = "/tatekaeInsert")
    public String insertTatekae(@ModelAttribute TatekaeForm tatekae, Model model, HttpServletRequest request,HttpSession session,@RequestParam("fileUpload") MultipartFile file,RedirectAttributes redirectAttributes){
        StatusController statusController = new StatusController();
        HeaderController headerController = new HeaderController();
        FileController fileUpload = new FileController();
        StaffBean staffBean = new StaffBean();
        TatekaeBean tatekaeBean = new TatekaeBean();
        String result ="FALSE";
        int sqlResult = 0;
        String staffBg = headerController.CheckCookie(request,session,sessionMapper);
        if(staffBg =="FALSE"){
            return "redirect:sky-tech";
        }
        //20201106　zhao追加 str.
        //usedTimeを取得する
        Date usedDate = DateUtil.getGyomuMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(usedDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);

        String tatekaeUsedTime = String.valueOf(year)+"年"+String.valueOf(month)+"月";
       /* if(!file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
            result = fileUpload.singleFileUpload(file,
                    redirectAttributes, FILE_PATH_TATEKAE, getBottonCdMapper, staffBg, String.valueOf(usedDate));
            System.out.println(result);
        }
        if(result!="FALSE"){
            tatekaeBean.setFILE_NAME(result);
        }*/

       try {


        if(!file.isEmpty()) {
            Map<String, String> fileMap = fileUpload.fileUpload(file,
                    redirectAttributes, FILE_PATH_TATEKAE, getBottonCdMapper, staffBg, tatekaeUsedTime);
            tatekaeBean.setFILE_NAME(fileMap.get(FILE_NAME));
            tatekaeBean.setFILE_URL(fileMap.get(FILE_PATH));

        }

       }catch(Exception e){
           model.addAttribute("msg","...");
           return "error-admin";
       }

        TatekaeBean tateRes = tatekaeMapper.selectIdByBgAndTime(staffBg,usedDate);
        //staffbg数据类型修改为string的联动修改
        //zhou 20201023
        tatekaeBean.setSTAFF_BG(staffBg);
        tatekaeBean.setUP_TIME(tatekae.getUP_TIME());
        tatekaeBean.setUSED_TIME(usedDate);
        tatekaeBean.setUSED_PRICE(tatekae.getUSED_PRICE());
        tatekaeBean.setSUB_COMPANY(tatekae.getSUB_COMPANY());
        String [] strs = tatekae.getUP_TIME().split("[月]");
        String flg = strs[0]+ "月";
        if(tateRes != null){
            sqlResult =tatekaeMapper.updateByBgAndTime(tatekaeBean);
        }else{
            sqlResult = tatekaeMapper.insert(tatekaeBean);
        }
        if(sqlResult == 1){
            //staffbg修改为string类型的联动修改
            //zhou 20201023
            staffBean.setSTAFF_BG(staffBg);
            staffBean.setTM_TATEKAE_STATUS(1);
            sqlResult = statusMapper.updateStatusByBg(staffBean);
        }
        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);
        return result1;
    }

    @PostMapping(value="/tatekaeCheck" )
    public String kinmuCheck(@ModelAttribute CheckStatusForm checkStatus, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws ParseException {
        TatekaeForm tatekaeForm = new TatekaeForm();
        TatekaeBean tatekaeBean = new TatekaeBean();
        StaffBean staffBean = new StaffBean();
        HeaderController header = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();
        downloadForm.setDownType(FILE_PATH_TATEKAE);

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
        //20201022,趙追加,str.
        //年月日を取得する
        String monthKubun = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        Date usedDate = DateUtil.getGyomuMonth();

        String staffBg = checkStatus.getStaffBg();
        String status = checkStatus.getStatus();

        tatekaeBean = tatekaeMapper.selectByBgAndTime(staffBg,usedDate);
        //staffbg修改为string类型的联动修改
        //zhou 20201023
        staffBean = staffMapper.selectNameByBg(staffBg);

        if(tatekaeBean != null) {

            tatekaeForm.setUSED_PRICE(tatekaeBean.getUSED_PRICE());
            tatekaeForm.setFILE_NAME(tatekaeBean.getFILE_NAME());
            tatekaeForm.setSUB_COMPANY(tatekaeBean.getSUB_COMPANY());
        }
        tatekaeForm.setUSER_NAME(staffBean.getSTAFF_NAME());
        tatekaeForm.setUSED_TIME(usedDate);
        tatekaeForm.setUP_TIME(upDate);
        downloadForm.setDownTime(monthKubun);
        tatekaeForm.setSTAFF_BG(staffBg);
        if(Integer.parseInt(status) == 2){
            tatekaeForm.setButtonClass("disableButton");
        }
        model.addAttribute("tatekae",tatekaeForm);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        model.addAttribute("staffBg",staffBg);
        return "zaimu/tatekaeCheck";
    }
    @GetMapping(value="/tatekaeOK" )
    public String KinmuOk(@ModelAttribute TatekaeForm tatekae, BindingResult bindingResult, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        HeaderController header = new HeaderController();
        StaffBean staffBean = new StaffBean();
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

        if(tatekae != null){
            //staffbg修改为string类型的联动修改
            //zhou 20201023
            staffBean.setSTAFF_BG(tatekae.getSTAFF_BG());
            String [] strs = tatekae.getUP_TIME().split("[月]");
            String flg = strs[0]+ "月";
            if(flg.equals(tatekae.getUSED_TIME())){
                staffBean.setTM_TATEKAE_STATUS(2);
            }else {
                staffBean.setLM_TATEKAE_STATUS(2);
            }
            sqlResult = statusMapper.updateStatusByBg(staffBean);
        }
        if(sqlResult == 1){
            msg = "承認成功";

        }else{
            msg = "承認を失敗されました。";
        }
//        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);
        model.addAttribute("tatekae", tatekae);
        model.addAttribute("msg",msg);
        model.addAttribute("downType","");
        model.addAttribute("downTime","");
        model.addAttribute("staffBg","");
        return ("zaimu/tatekaeCheck");
    }

    // liuyang 20201103 管理员根据条件查询
    @RequestMapping(value ="/tateQueryList")
    public String tateQueryList(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {

        if (SessionUtil.getCurrentAccount()== null){
            return "redirect:sky-tech";
        }
        if (SessionUtil.getPermission()<2){
            return "redirect:sky-tech";
        }
        //zhou 20201112
        String staffBg= request.getParameter("staffBG");


        //zhou 20201111 如果staffBg不为空，已承认页面跳转
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
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryListByTate(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/adminTateList";
    }
    // liuyang 20201103 员工根据条件查询
    @RequestMapping(value ="/tateQueryListByBg")
        public String tateQueryListByBg(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {
        HeaderController header = new HeaderController();

        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg == "FALSE") {
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
      /*  String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        if (permBg == "FALSE") {
            return "redirect:sky-tech";
        }*/

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
        if(qo.getTimeStarYear() == null || qo.getTimeStarMonth() == null || qo.getTimeEndYear() ==null || qo.getTimeEndMonth() == null){
            qo.setUsedTimeStart(null);
            qo.setUsedTimeEnd(null);
        }else {
            qo.setUsedTimeStart(sdf.parse(qo.getTimeStarYear() + "-" + qo.getTimeStarMonth() + "-" + "01"));
            qo.setUsedTimeEnd(sdf.parse(qo.getTimeEndYear() + "-" + qo.getTimeEndMonth() + "-" + "01"));
        }
        qo.setSTAFF_BG(staffBg);
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryListByTate(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        return "staff/memberTateList";
    }

    /**
     * @description: 提出したtatekae表を確認画面
     * @params:  * @Param model
     * @Param request
     * @Param session
     * @return: java.lang.String
     * @author: zhou
     * @date: 2020/11/11
     */
    @RequestMapping(value ="/tatekaeKakunin")
    @ResponseBody
    public TatekaeForm tatekaeKakunin(CheckStatusForm checkStatusForm, @RequestParam("staffBG") String staffBG, Model model)  throws ParseException {

        TatekaeForm tatekaeForm = new TatekaeForm();
        if (SessionUtil.getCurrentAccount()== null){
            tatekaeForm.setFlag("false");
            return tatekaeForm;
        }
        if (SessionUtil.getPermission()<2){
            tatekaeForm.setFlag("false");
            return tatekaeForm;
        }
        try {
            checkStatusForm.setStaffBg(staffBG);
            tatekaeForm = tatekaeBusiness.getTatekaeByBGAndDate(checkStatusForm);
            return tatekaeForm;

        }catch (NullPointerException e){
            tatekaeForm.setErrorMessage("詳細内容がないので、管理者に連絡してください。");
            tatekaeForm.setFlag("false");
            return tatekaeForm;
        }catch (Exception e){
            model.addAttribute("msg","エラーが発生しました。もう一度お試して下さい。");
            tatekaeForm.setFlag("false");
            model.addAttribute("tatekae",tatekaeForm);
            return tatekaeForm;
        }

    }

    /**
     * @description: tatekae表の承認
    [kinmuForm]
     * @return: sky.co.jp.queryData.JsonResult
     * @author: zhou
     * @date: 2020/11/06
     */
    @PostMapping("/tatekaeSyonin")
    @ResponseBody
    public JsonResult tatekaeTatekae(@ModelAttribute TatekaeForm tatekae) {
        JsonResult result = new JsonResult() ;
        try {
            staffBusiness.updateTatekaeStatus(tatekae,TATEKAE_STATUS_FLAG);

        } catch (Exception e) {
            e.printStackTrace();
            result.mark("失败しました.");
        }
        return result;
    }
}
