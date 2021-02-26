package sky.co.jp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sky.co.jp.bean.KinmuBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.business.IKinmuBusiness;
import sky.co.jp.business.IQueryBusiness;
import sky.co.jp.business.IStaffBusiness;
import sky.co.jp.form.CheckStatusForm;
import sky.co.jp.form.DownloadForm;
import sky.co.jp.form.FlagForm;
import sky.co.jp.form.KinmuForm;
import sky.co.jp.mapper.GetBottonCdMapper;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.mapper.SessionMapper;
import sky.co.jp.mapper.SkyKinmuMapper;
import sky.co.jp.mapper.StaffMapper;
import sky.co.jp.mapper.StatusMapper;
import sky.co.jp.queryData.AllReportQueryObject;
import sky.co.jp.queryData.JsonResult;
import sky.co.jp.queryData.PageResult;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.SessionUtil;

@Controller
public class    KinmuController {
    @Autowired
    SkyKinmuMapper skyKinmuMapper;
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    StaffMapper staffMapper;
    @Autowired(required = false)
    StatusMapper statusMapper;

    @Autowired(required = false)
    private GetBottonCdMapper getBottonCdMapper;

    @Autowired(required = false)
    private IKinmuBusiness kinmuBusiness;

    @Autowired(required = false)
    IQueryBusiness iQueryBusiness;

    @Autowired(required = false)
    private IStaffBusiness staffBusiness;

    @Autowired
    KoutuMapper koutuMapper;
    private final String FILE_PATH_KINMU = "FILE_PATH_KINMU";

    private final String KINMU_STATUS_FLAG = "KINMU";
    private final String KOUTU_STATUS_FLAG = "KOUTU";
    private final String TATKAE_STATUS_FLAG = "TATEKAE";

    private static final String FILE_NAME = "fileName";
    private static final String FILE_PATH = "filePath";

    //DBから全てのデータをFORMに送る
    @RequestMapping(value ="/kinmu", method = {RequestMethod.GET})
    public String kinmuIndex(@ModelAttribute FlagForm flag, Model model, HttpServletRequest request, HttpSession session)  throws ParseException {
        KinmuForm kinmu = new KinmuForm();
        KinmuBean kinmuBean = new KinmuBean();
        StaffBean staffBean = new StaffBean();
        HeaderController headerController = new HeaderController();
        String msg = "";

        DownloadForm downloadForm = new DownloadForm();
        downloadForm.setDownType(FILE_PATH_KINMU);
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
        /*
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
        }*//*
        String usedDate = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        kinmuBean = skyKinmuMapper.selectByBgAndTime(Long.parseLong(staffBg),usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);
        if(kinmuBean != null) {
           kinmu.setCompany(kinmuBean.getGENBAN_NAME());
           kinmu.setStandardtime(kinmuBean.getSTANDARD_TIME());*/
        //20201022,趙追加,str.
        //年月日を取得する
        String monthKubun = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        //更新日を取得する
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        //利用時間を取得する
        //Date usedDate = cal.getTime();
        //SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        //usedDate = formatter.parse(monthKubun);
        System.out.println(usedDate+"+++++++++");
        kinmuBean = skyKinmuMapper.selectByBgAndTime(staffBg,usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);
        if(kinmuBean != null) {
            kinmu.setCompany(kinmuBean.getGENBAN_NAME());
            kinmu.setStandardtime(kinmuBean.getSTANDARD_TIME());
            //20201022,趙 str.
            //作業開始日
            kinmu.setStarttime(kinmuBean.getSTART_TIME());
            //作業終了日
            kinmu.setEndtime(kinmuBean.getEND_TIME());
            //現場アドレス
            kinmu.setGanbanAddress(kinmuBean.getGENBA_ADDRESS());
            //備考
            kinmu.setBiko(kinmuBean.getBIKO());
            //20201022,趙 end.
           kinmu.setUpperTime(kinmuBean.getUPPER_TIME());
           kinmu.setWorkTime(kinmuBean.getWORK_TIME());
           kinmu.setZangyouTime(kinmuBean.getZANGYOU_TIME());
           kinmu.setFileName(kinmuBean.getFILE_NAME());
        }
        kinmu.setStaffName(staffBean.getSTAFF_NAME());
        kinmu.setUSED_TIME(usedDate);
        kinmu.setUpTime(upDate);
        kinmu.setStaffBg(staffBg);
        downloadForm.setDownTime(monthKubun);
        model.addAttribute("kinmu", kinmu);
        model.addAttribute("msg",msg);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        model.addAttribute("gyomuMonth",monthKubun);
        return "kinmuMsgtemp";
    }

    @RequestMapping(value="/kinmuSubmit" )
    public String kinmuSave(@ModelAttribute KinmuForm kinmu, BindingResult  bindingResult,Model model, HttpServletRequest request, HttpSession session, @RequestParam("fileUpload") MultipartFile file, RedirectAttributes redirectAttributes) {
        StatusController statusController = new StatusController();
        HeaderController headerController = new HeaderController();
        FileController fileUpload = new FileController();
        StaffBean staffBean = new StaffBean();
        KinmuBean kinmuBean =new KinmuBean();
        String result ="FALSE";
        int sqlResult = 0;

        String staffBg = headerController.CheckCookie(request,session,sessionMapper);
        if(staffBg =="FALSE"){
            return "redirect:sky-tech";
        }

       /* if(!file.isEmpty()) {
            System.out.println(file.getOriginalFilename());
            result = fileUpload.singleFileUpload(file,
                    redirectAttributes, FILE_PATH_KINMU, getBottonCdMapper, staffBg, String.valueOf(kinmu.getUSED_TIME()));
            System.out.println(result);
        }
        if(result!="FALSE"){
            kinmuBean.setFILE_NAME(result);
        }*/

        Date usedDate = DateUtil.getGyomuMonth();
        Calendar cal = Calendar.getInstance();
        cal.setTime(usedDate);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DATE);
        //liuyang 20201029 usedTime类型联动修改
        String kinmuUsedTime = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        try {


            if(!file.isEmpty()) {
                Map<String, String> fileMap = fileUpload.fileUpload(file,
                        redirectAttributes, FILE_PATH_KINMU, getBottonCdMapper, staffBg, kinmuUsedTime);
                kinmuBean.setFILE_NAME(fileMap.get(FILE_NAME));
                kinmuBean.setFILE_URL(fileMap.get(FILE_PATH));
            }}catch (Exception e){
            model.addAttribute("msg","...");
            return "error-admin";
        }

        //staffbg数据类型修改为string的联动修改
        //zhou 20201023
       // Date usedDate = DateUtil.getGyomuMonth();
        /*//カレンダーから年月日を取得する
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        //何月の勤務表を判断する
        if(day > 16){
            Calendar calendar = Calendar.getInstance();
            //今月の月数を設定する
            calendar.add(Calendar.MONTH, 0);
            //今月の第一日を設定する
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //今日の時間を設定する
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            usedDate = calendar.getTime();
            //時間戳はBeanにinsertする
            kinmuBean.setUSED_TIME(usedDate);
        }else{
            //来月の第一日を取得する
            Calendar calendar = Calendar.getInstance();
            //来月の月数を設定する
            calendar.add(Calendar.MONTH, -1);
            //来月の第一日を設定する
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            //今日の時間を設定する
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            usedDate = calendar.getTime();
            //時間戳はBeanにinsertする
            kinmuBean.setUSED_TIME(usedDate);
        }*/
        //20201022,趙 end.
      //時間戳はBeanにinsertする
        kinmuBean.setUSED_TIME(usedDate);
        KinmuBean kinmuRes = skyKinmuMapper.selectIdByBgAndTime(staffBg,kinmuBean.getUSED_TIME());
        kinmuBean.setSTAFF_BG(staffBg);
        kinmuBean.setUP_TIME(kinmu.getUpTime());
        //kinmuBean.setUSED_TIME(kinmu.getUSED_TIME());
        kinmuBean.setGENBAN_NAME(kinmu.getCompany());
        kinmuBean.setSTANDARD_TIME(kinmu.getStandardtime());
        //20201022,趙 str.
        //作業開始日はFORMから取得する
        kinmuBean.setSTART_TIME(kinmu.getStarttime());
        //作業終了日はFORMから取得する
        kinmuBean.setEND_TIME(kinmu.getEndtime());
        //現場アドレスはFORMから取得する
        kinmuBean.setGENBAN_ADDRESS(kinmu.getGanbanAddress());
        //備考はFORMから取得する
       // kinmuBean.setEND_TIME(kinmu.getEndtime());
        kinmuBean.setBIKO(kinmu.getBiko());
        kinmuBean.setUPPER_TIME(kinmu.getUpperTime());
        kinmuBean.setWORK_TIME(kinmu.getWorkTime());
        if(kinmuBean.getWORK_TIME() > kinmuBean.getUPPER_TIME()){
            kinmuBean.setZANGYOU_TIME(kinmuBean.getWORK_TIME() - kinmuBean.getUPPER_TIME());
        }else if(kinmuBean.getWORK_TIME() < kinmuBean.getSTANDARD_TIME()){
            kinmuBean.setZANGYOU_TIME(kinmuBean.getWORK_TIME() - kinmuBean.getSTANDARD_TIME());
        }else{
            kinmuBean.setZANGYOU_TIME(0.0);
        }
        String [] strs = kinmu.getUpTime().split("[月]");
        String flg = strs[0]+ "月";
        if(kinmuRes!= null){
            sqlResult = skyKinmuMapper.updateByBgAndTime(kinmuBean);
        }else{
            sqlResult = skyKinmuMapper.insert_kinmu(kinmuBean);
        }
        if(sqlResult == 1){
            //staffbg修改为string类型的联动修改
            //zhou 20201023
            staffBean.setSTAFF_BG(staffBg);
            staffBean.setTM_KINMU_STATUS(1);
            staffBean.setGENBAN_NAME(kinmu.getCompany());
            staffBean.setSTANDARD_TIME(kinmu.getStandardtime());
            staffBean.setSTAFF_STATUS(2);
            staffBean.setUPPER_TIME(kinmu.getUpperTime());
            statusMapper.updateStatusByBg(staffBean);
        }
        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);

        return result1;
    }
    @PostMapping(value="/kinmuCheck" )
    public String kinmuCheck(@ModelAttribute CheckStatusForm checkStatus, Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        KinmuForm kinmu = new KinmuForm();
        KinmuBean kinmuBean = new KinmuBean();
        StaffBean staffBean = new StaffBean();
        HeaderController header = new HeaderController();
        String msg = "";

        DownloadForm downloadForm = new DownloadForm();
        downloadForm.setDownType(FILE_PATH_KINMU);
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
        //20201022,趙追加　str
        String monthKubun = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        Date usedDate = cal.getTime();
        kinmuBean = skyKinmuMapper.selectByBgAndTime(staffBg,usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);
        if(kinmuBean != null) {
            kinmu.setCompany(kinmuBean.getGENBAN_NAME());
            kinmu.setStandardtime(kinmuBean.getSTANDARD_TIME());
            //20201022,趙 str.
            //作業開始日はFORMに設定する
            kinmu.setStarttime(kinmuBean.getSTART_TIME());
            //作業終了日はFORMに設定する
            kinmu.setEndtime(kinmuBean.getEND_TIME());
            //現場アドレスはFORMに設定する
            kinmu.setEndtime(kinmuBean.getEND_TIME());
            //備考はFORMに設定する
            kinmu.setEndtime(kinmuBean.getEND_TIME());
            //20201022,趙 end.
            kinmu.setUpperTime(kinmuBean.getUPPER_TIME());
            kinmu.setWorkTime(kinmuBean.getWORK_TIME());
            kinmu.setZangyouTime(kinmuBean.getZANGYOU_TIME());
            kinmu.setFileName(kinmuBean.getFILE_NAME());
        }
        kinmu.setStaffBg(staffBg);
        kinmu.setStaffName(staffBean.getSTAFF_NAME());
        //何月の勤務表を判断する
        if(day >= 16){
            //今月の第一日を取得する
            Calendar cale = Calendar.getInstance();
            //今月の月数を設定する
            cale.add(Calendar.MONTH, 0);
            //今月の第一日を設定する
            cale.set(Calendar.DAY_OF_MONTH, 1);
            //今日の時間を設定する
            cale.set(Calendar.HOUR_OF_DAY, 0);
            cale.set(Calendar.MINUTE, 0);
            cale.set(Calendar.SECOND, 0);
            usedDate = cale.getTime();
            //時間戳はBeanにinsertする
            kinmu.setUSED_TIME(usedDate);
        }else{
            //来月の第一日を取得する
            Calendar cale = Calendar.getInstance();
            //来月の月数を設定する
            cale.add(Calendar.MONTH, 1);
            //来月の第一日を設定する
            cale.set(Calendar.DAY_OF_MONTH, 1);
            //今日の時間を設定する
            cale.set(Calendar.HOUR_OF_DAY, 0);
            cale.set(Calendar.MINUTE, 0);
            cale.set(Calendar.SECOND, 0);
            //今日の時間を設定する
            usedDate = cale.getTime();
            //時間戳はDBにinsertする
            kinmu.setUSED_TIME(usedDate);

        /*String usedDate = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        kinmuBean = skyKinmuMapper.selectByBgAndTime(Long.parseLong(staffBg),usedDate);
        staffBean = staffMapper.selectNameByBg(staffBg);
        if(kinmuBean != null) {
            kinmu.setCompany(kinmuBean.getGENBAN_NAME());
            kinmu.setStandardtime(kinmuBean.getSTANDARD_TIME());*/
            kinmu.setUpperTime(kinmuBean.getUPPER_TIME());
            kinmu.setWorkTime(kinmuBean.getWORK_TIME());
            kinmu.setZangyouTime(kinmuBean.getZANGYOU_TIME());
            kinmu.setFileName(kinmuBean.getFILE_NAME());
        }
        kinmu.setStaffBg(staffBg);
        kinmu.setStaffName(staffBean.getSTAFF_NAME());
        kinmu.setUSED_TIME(usedDate);
        kinmu.setUpTime(upDate);
        downloadForm.setDownTime(monthKubun);
        if(Integer.parseInt(status) == 2){
            kinmu.setButtonClass("disableButton");
        }
        model.addAttribute("kinmu", kinmu);
        model.addAttribute("msg",msg);
        model.addAttribute("downType",downloadForm.getDownType());
        model.addAttribute("downTime",downloadForm.getDownTime());
        model.addAttribute("staffBg",staffBg);
        return "zaimu/kinmuCheck";
    }
    @PostMapping(value="/KinmuOk" )
    public String KinmuOk(@ModelAttribute KinmuForm kinmu, BindingResult  bindingResult,Model model, HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) {
        StatusController statusController = new StatusController();
        HeaderController header = new HeaderController();
        FileController fileUpload = new FileController();
        StaffBean staffBean = new StaffBean();
        KinmuBean kinmuBean =new KinmuBean();
        String msg = "";
        int sqlResult = 0;
        String result ="FALSE";
        String loginStatus = header.CheckCookie(request,session,sessionMapper);
        if(loginStatus == "FALSE"){
            return "redirect:sky-tech";
        }
        String permBg = header.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if (permBg == "FALSE"){
            return "redirect:sky-tech";
        }

        if(kinmu != null){
            //staffbg修改为string类型的联动修改
            //zhou 20201023
            staffBean.setSTAFF_BG(kinmu.getStaffBg());
            String [] strs = kinmu.getUpTime().split("[月]");
            String flg = strs[0]+ "月";
            if(flg.equals(kinmu.getUSED_TIME())){
                staffBean.setTM_KINMU_STATUS(2);
            }else {
                staffBean.setLM_KINMU_STATUS(2);
            }
            sqlResult = statusMapper.updateStatusByBg(staffBean);
        }
        if(sqlResult == 1){
            msg = "承認成功";

        }else{
            msg = "承認を失敗されました。";
        }
//        String result1 = statusController.status(model,request,session,statusMapper,sessionMapper);
        model.addAttribute("kinmu", kinmu);
        model.addAttribute("msg",msg);
        model.addAttribute("downType","");
        model.addAttribute("downTime","");
        model.addAttribute("staffBg","");
        return ("zaimu/kinmuCheck");
    }

    /**
     * @description: 提出した勤務表を確認画面
     * @params:  * @Param model
     * @Param request
     * @Param session
     * @return: java.lang.String
     * @author: zhou
     * @date: 2020/11/04
     */
    @RequestMapping(value ="/kinmuKakunin")
    @ResponseBody
    public KinmuForm kinmuKakunin(CheckStatusForm checkStatusForm,@RequestParam("staffBG") String staffBG,Model model)  throws ParseException {

        KinmuForm kinmuForm = new KinmuForm();
        if (SessionUtil.getCurrentAccount()== null){
            kinmuForm.setM_FLG("false");
            model.addAttribute("kinmu", kinmuForm);
            return kinmuForm;
        }
        if (SessionUtil.getPermission()<2){
            kinmuForm.setM_FLG("false");
            model.addAttribute("kinmu", kinmuForm);
            return kinmuForm;
        }
        try {
            checkStatusForm.setStaffBg(staffBG);
            kinmuForm = kinmuBusiness.getKinmuByBGAndDate(checkStatusForm);
            model.addAttribute("kinmu", kinmuForm);
            return kinmuForm;

        }catch (NullPointerException e){
            kinmuForm.setErrorMessage("詳細内容がないので、管理者に連絡してください。");
            kinmuForm.setM_FLG("false");
            model.addAttribute("kinmu", kinmuForm);
            return kinmuForm;
        } catch (Exception e){
            model.addAttribute("msg","エラーが発生しました。もう一度お試して下さい。");
            kinmuForm.setM_FLG("false");
            model.addAttribute("kinmu", kinmuForm);
            return kinmuForm;
        }

    }

    /**
     * @description: 勤務表の承認
     [kinmuForm]
     * @return: sky.co.jp.queryData.JsonResult
     * @author: zhou
     * @date: 2020/11/06
     */
    @RequestMapping("/kinmuSyonin")
    @ResponseBody
    public JsonResult kinmuSyonin(KinmuForm kinmu) {
        JsonResult result = new JsonResult() ;
        try {
            staffBusiness.updateTmStatus(kinmu,KINMU_STATUS_FLAG);
            //staffBusiness.updateStatus(KINMU_STATUS_FLAG,kinmu);

        } catch (Exception e) {
            e.printStackTrace();
            result.mark("失败しました。");
        }
        return result;
    }

    // liuyang 20201103 管理员根据条件查询
    @RequestMapping(value ="/kinmuQueryList")
    public String kinmuQueryList(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {
        //zhou 20201112
        String staffBg= request.getParameter("staffBG");


        //zhou 20201111 如果staffBg不为空，已承认页面跳转
        if (staffBg!=null && staffBg !=""){
            qo.setSTAFF_BG(staffBg);
        }

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
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryListByKinmu(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/adminKinmuList";
    }
    // liuyang 20201103 员工根据条件查询
    //@PostMapping("/kinmuQueryListByBg")

    @RequestMapping(value ="/kinmuQueryListByBg")
    public String kinmuQueryListByBg(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("qo")AllReportQueryObject qo,BindingResult bindingResult) throws ParseException {
        HeaderController header = new HeaderController();

        String staffBg = header.CheckCookie(request, session, sessionMapper);
        if (staffBg == "FALSE") {
            return "redirect:sky-tech";
        }
        //String permBg = header.CheckPermission(request, session, sessionMapper, getBottonCdMapper);
        //if (permBg == "FALSE") {
        //    return "redirect:sky-tech";
        //}
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
        qo.setSTAFF_BG(staffBg);
        PageResult<AllReportQueryObject> result = iQueryBusiness.queryListByKinmu(qo);
        model.addAttribute("timeYear",timeYear);
        model.addAttribute("result",result);
        model.addAttribute("qo",qo);
        // System.out.println("那么="+qo.getKeyword());
        return "staff/memberKinmuList";
    }

}