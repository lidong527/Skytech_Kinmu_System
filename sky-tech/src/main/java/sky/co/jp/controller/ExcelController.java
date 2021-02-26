package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sky.co.jp.bean.KinmuBean;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.bean.StaffBean;
import sky.co.jp.bean.TatekaeBean;
import sky.co.jp.form.*;
import sky.co.jp.mapper.*;
import sky.co.jp.business.IExcelBusiness;
import sky.co.jp.queryData.JsonResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 功能：excel读取数据、导出数据
 * 创建人：liuyang
 * 创建时间：2020/10/26
 */
@RestController
public class ExcelController {
    private final String FILE_PATH_KOUTSU = "FILE_PATH_KOUTSU";
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
    @Autowired
    private IExcelBusiness excelService;
    @Autowired
    SkyKinmuMapper kinmuMapper;
    @Autowired
    TatekaeMapper tatekaeMapper;

    /**
     * 方法名：exportExcel
     * 功能：导出功能，项目中还没有用到
     * 描述：文件格式为xls或xlsx
     * 创建人：liuyang
     * 创建时间：2020/10/26
     */
    @PostMapping("/export")
    public String exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
        if (fileName == null || "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls")) {
                Boolean isOk = excelService.exportExcel(response, fileName, 1, 10);
                if (isOk) {
                    return "导出成功！";
                } else {
                    return "导出失败！";
                }
            }
            return "文件格式有误！";
        }
    }

    /**
     * 方法名：readExcelKoutu
     * 功能：读取交通费数据
     * 描述：文件格式为xls或xlsx
     * 创建人：liuyang
     * 创建时间：2020/10/26
     */
    @PostMapping(value = "/readExcelKoutu")
    @ResponseBody
    public KoutuForm readExcelKoutu(@RequestParam(value="fileUpload") MultipartFile file, FlagForm flag, Model model, HttpServletRequest request, HttpSession session) throws Exception {
            //读取Excel并提取数据
            Map<String, Double> map =excelService.readExcelKoutu(file);
            KoutuForm jsonKoutu = new KoutuForm();
            KoutuBean koutuBean = new KoutuBean();
            StaffBean staffBean = new StaffBean();
            HeaderController headerController = new HeaderController();
            DownloadForm downloadForm = new DownloadForm();


            String staffBg = headerController.CheckCookie(request,session,sessionMapper);

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
            String monthKoutu = String.valueOf(year)+"年"+String.valueOf(month)+"月";
            String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
            //liuyang 20201029 时间修改
            Date usedDate = cal.getTime();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
            usedDate = formatter.parse(monthKoutu);
            koutuBean = koutuMapper.selectByBgAndTime(staffBg,usedDate);

            staffBean = staffMapper.selectNameByBg(staffBg);
            try{
                //修改交通费得值
                //liuyang 20201026
                if(!map.isEmpty()  && koutuBean != null){
                    jsonKoutu.setUsedMoneyAll(map.get("moneyAll"));
                    jsonKoutu.setUsedMoneyEtc(map.get("moneyEtc"));
                    jsonKoutu.setUsedMoneyTsu(map.get("moneyTsu"));
                }else if (map.isEmpty() && koutuBean != null){
                    jsonKoutu.setUsedMoneyAll(koutuBean.getUSED_MONEY_ALL());
                    jsonKoutu.setUsedMoneyEtc(koutuBean.getUSED_MONEY_ETC());
                    jsonKoutu.setUsedMoneyTsu(koutuBean.getUSED_MONEY_TSUKIN());
                }else if (!map.isEmpty() && koutuBean == null){
                    jsonKoutu.setUsedMoneyAll(map.get("moneyAll"));
                    jsonKoutu.setUsedMoneyEtc(map.get("moneyEtc"));
                    jsonKoutu.setUsedMoneyTsu(map.get("moneyTsu"));
                }
                downloadForm.setDownTime(monthKoutu);
                //model.addAttribute("koutu",koutu);
                model.addAttribute("downType",downloadForm.getDownType());
                model.addAttribute("downTime",downloadForm.getDownTime());
                return jsonKoutu;
            }catch (Exception e){
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "msg", "title", JOptionPane.PLAIN_MESSAGE);
                e.printStackTrace();
            }
        return jsonKoutu;
    }
    /**
     * 方法名：readExcelKinmu
     * 功能：读取勤务表数据
     * 描述：文件格式为xls或xlsx
     * 创建人：liuyang
     * 创建时间：2020/11/19
     */
    @PostMapping(value = "/readExcelKinmu")
    @ResponseBody
    public KinmuForm readExcelKinmu(@RequestParam(value="fileUpload") MultipartFile file, FlagForm flag, Model model, HttpServletRequest request, HttpSession session) throws Exception {
        //读取Excel并提取数据
        Map<String, Object> map =excelService.readExcelKinmu(file);
        KinmuBean kinmuBean = new KinmuBean();
        KinmuForm jsonKinmu = new KinmuForm();
        StaffBean staffBean = new StaffBean();
        HeaderController headerController = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();


        String staffBg = headerController.CheckCookie(request,session,sessionMapper);

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
        String monthKinmu = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        //liuyang 20201119 时间修改
        Date usedDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        usedDate = formatter.parse(monthKinmu);
        kinmuBean = kinmuMapper.selectByBgAndTime(staffBg,usedDate);

        staffBean = staffMapper.selectNameByBg(staffBg);
        try{
            //修改勤务表得值
            //liuyang 20201119
            if(!map.isEmpty()  && kinmuBean != null){
                jsonKinmu.setCompany((String) map.get("GENBAN_NAME"));
                jsonKinmu.setWorkTime((Double) map.get("WORK_TIME"));
            }else if (map.isEmpty() && kinmuBean != null){
                jsonKinmu.setCompany(kinmuBean.getGENBAN_NAME());
                jsonKinmu.setWorkTime(kinmuBean.getWORK_TIME());
            }else if (!map.isEmpty() && kinmuBean == null){
                jsonKinmu.setCompany((String) map.get("GENBAN_NAME"));
                jsonKinmu.setWorkTime((Double) map.get("WORK_TIME"));
            }
            downloadForm.setDownTime(monthKinmu);
            //model.addAttribute("koutu",koutu);
            model.addAttribute("downType",downloadForm.getDownType());
            model.addAttribute("downTime",downloadForm.getDownTime());
            return jsonKinmu;
        }catch (Exception e){
        Toolkit.getDefaultToolkit().beep();
        JOptionPane.showMessageDialog(null, "msg", "title", JOptionPane.PLAIN_MESSAGE);
        e.printStackTrace();
    }
        return jsonKinmu;

    }
    /**
     * 方法名：readExcelTate
     * 功能：读取立替金数据
     * 描述：文件格式为xls或xlsx
     * 创建人：liuyang
     * 创建时间：2020/11/19
     * @return
     */
    @PostMapping(value = "/readExcelTate")
    @ResponseBody
    public TatekaeForm readExcelTate(@RequestParam(value="fileUpload") MultipartFile file, FlagForm flag, Model model, HttpServletRequest request, HttpSession session) throws Exception {
        //读取Excel并提取数据
        Map<String, Double> map =excelService.readExcelTate(file);
        TatekaeForm jsonTate = new TatekaeForm();
        TatekaeBean tatekaeBean = new TatekaeBean();
        StaffBean staffBean = new StaffBean();
        HeaderController headerController = new HeaderController();
        DownloadForm downloadForm = new DownloadForm();
        String staffBg = headerController.CheckCookie(request,session,sessionMapper);

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
        String monthTate = String.valueOf(year)+"年"+String.valueOf(month)+"月";
        String upDate = String.valueOf(year)+"年"+String.valueOf(cal.get(Calendar.MONTH)+1)+"月"+String.valueOf(day)+"日";
        //liuyang 20201119 时间修改
        Date usedDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        usedDate = formatter.parse(monthTate);
        tatekaeBean = tatekaeMapper.selectByBgAndTime(staffBg,usedDate);

        staffBean = staffMapper.selectNameByBg(staffBg);
        try{
            //修改勤务表得值
            //liuyang 20201119
            if(!map.isEmpty()  && tatekaeBean != null){
                jsonTate.setUSED_PRICE(map.get("USED_PRICE"));
            }else if (map.isEmpty() && tatekaeBean != null){
                jsonTate.setUSED_PRICE(tatekaeBean.getUSED_PRICE());
            }else if (!map.isEmpty() && tatekaeBean == null){
                jsonTate.setUSED_PRICE(map.get("USED_PRICE"));
            }
            downloadForm.setDownTime(monthTate);
            //model.addAttribute("koutu",koutu);
            //model.addAttribute("msg",downloadForm.getDownType());
            model.addAttribute("downTime",downloadForm.getDownTime());
            return jsonTate;
        }catch (Exception e){
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "msg", "title", JOptionPane.PLAIN_MESSAGE);
            e.printStackTrace();
        }
        return jsonTate;
    }
}
