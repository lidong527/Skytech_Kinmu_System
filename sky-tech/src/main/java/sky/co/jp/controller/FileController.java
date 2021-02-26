package sky.co.jp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import sky.co.jp.bean.FileBean;
import sky.co.jp.bean.GetButtonCdBean;
import sky.co.jp.mapper.*;
import sky.co.jp.util.DateUtil;
import sky.co.jp.util.SessionUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FileController {
    private static final String FILE_PATH_KINMU = "FILE_PATH_KINMU";
    private static final String FILE_PATH_KOUTSU = "FILE_PATH_KOUTSU";
    private static final String FILE_PATH_TATEKAE = "FILE_PATH_TATEKAE";
    private static final String FILE_NAME = "fileName";
    private static final String FILE_PATH = "filePath";
    private static final String DOWN_TYPE_KINMU = "DOWN_TYPE_KINMU";
    private static final String DOWN_TYPE_KOUTU = "DOWN_TYPE_KOUTU";
    private static final String  DOWN_TYPE_TATEKAE = "DOWN_TYPE_TATEKAE";

    private static String UPLOADED_FOLDER;

    @Autowired(required = false)
    GetBottonCdMapper getBottonCdMapper;
    @Autowired(required = false)
    SessionMapper sessionMapper;
    @Autowired(required = false)
    SkyKinmuMapper kinmuMapper;
    @Autowired(required = false)
    KoutuMapper koutuMapper;
    @Autowired(required = false)
    TatekaeMapper tatekaeMapper;





    public String singleFileUpload(@RequestParam("fileUpload") MultipartFile file,
                                  RedirectAttributes redirectAttributes, String type,
                                   GetBottonCdMapper getBottonCdMapper_1,String staffBg,String usedTime) {

        GetButtonCdBean filePathResult = getBottonCdMapper_1.selectContentById(type);
        String fileNameZen = staffBg +"_"+usedTime;
        String fileName = "FALSE";

        if (filePathResult != null){
            UPLOADED_FOLDER = filePathResult.getCODE_CONTENT();
        }
        System.out.println(file);
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:koutu";
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            String [] strs = file.getOriginalFilename().split("[.]");
            fileName = fileNameZen + "." + strs[1];
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            System.out.println(file.getOriginalFilename());
            System.out.println(fileName+"|||"+fileNameZen);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileName;
    }

    @RequestMapping(value = "/download")
    public void download(HttpServletResponse res, HttpServletRequest request, HttpSession session, Model model, String downType, Date downTime, String staffBg) throws IOException {

        GetButtonCdBean filePathResult = new GetButtonCdBean();
        FileBean fileNameResult = new FileBean();
        HeaderController headerController = new HeaderController();

        String fileName = null;
        String statusCheck = headerController.CheckCookie(request,session,sessionMapper);
        if(statusCheck =="FALSE"){
            UPLOADED_FOLDER = "../fileTemp/";
            fileName=downType+".xslx";
        }
        String permBg = headerController.CheckPermission(request,session,sessionMapper,getBottonCdMapper);
        if(!permBg.equals("FALSE")){
            statusCheck = staffBg;
        }

        filePathResult = getBottonCdMapper.selectContentById(downType);
        switch (downType){
            //staffbg数据类型修改为string的联动修改
            //zhou 20201023
            case FILE_PATH_KINMU:
                fileNameResult = kinmuMapper.selectFileByBgAndTime(statusCheck,downTime);
                break;
            case FILE_PATH_KOUTSU:
                fileNameResult = koutuMapper.selectFileByBgAndTime(statusCheck,downTime);
                break;
            case FILE_PATH_TATEKAE:
                fileNameResult = tatekaeMapper.selectFileByBgAndTime(statusCheck,downTime);
                break;
            default:
                fileNameResult.setFILE_NAME(downType+".xslx");
        }

        if (filePathResult != null){
            UPLOADED_FOLDER = filePathResult.getCODE_CONTENT();
        }else{
            UPLOADED_FOLDER = "../fileTemp/";
        }
        if(fileNameResult != null){
            fileName = fileNameResult.getFILE_NAME();
        }else{
            UPLOADED_FOLDER = "../fileTemp/";
            fileName=downType+".xslx";
        }

        System.out.println(downType);

        File file = new File(UPLOADED_FOLDER + fileName);
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    //20201112

    public Map<String,String> fileUpload(@RequestParam("fileUpload") MultipartFile file,
                                         RedirectAttributes redirectAttributes, String type,
                                         GetBottonCdMapper getBottonCdMapper_1, String staffBg, String usedTime) throws IOException {

        GetButtonCdBean filePathResult = getBottonCdMapper_1.selectContentById(type);
        String fileNameZen = staffBg +"_"+usedTime;
        String fileName = "FALSE";

        if (filePathResult != null){
            UPLOADED_FOLDER = filePathResult.getCODE_CONTENT();
        }


            byte[] bytes = file.getBytes();
            String [] strs = file.getOriginalFilename().split("[.]");
            fileName = fileNameZen + "." + strs[1];
            Path path = Paths.get(UPLOADED_FOLDER + fileName);
            System.out.println(file.getOriginalFilename());
            System.out.println(fileName+"|||"+fileNameZen);
            Files.write(path, bytes);
        HashMap<String,String> map= new HashMap<>();
        map.put(FILE_NAME,fileName);
        map.put(FILE_PATH,UPLOADED_FOLDER + fileName);

        return map;
    }

    @RequestMapping(value = "/downloadFile")
    @ResponseBody
    public String downloadFile( HttpServletRequest request, Model model,HttpServletResponse response, RedirectAttributes redirectAttributes,@RequestParam String staffBG,@RequestParam String downType){
        if (SessionUtil.getCurrentAccount() == null)
        {
            return "redirect:sky-tech";
        }        //shareFile = fileService.get(id);
        if (SessionUtil.getPermission() <2){

            if (!staffBG.equals(SessionUtil.getStaffBG()) ){
                return "redirect:sky-tech";
            }
        }
        Date gyomuMonth = DateUtil.getGyomuMonth();
        FileBean fileBean = new FileBean();
        switch (downType){
            //zhou 20201023
            case DOWN_TYPE_KINMU:
                fileBean = kinmuMapper.selectFileByBgAndTime(staffBG,gyomuMonth);
                break;
            case DOWN_TYPE_KOUTU:
                fileBean = koutuMapper.selectFileByBgAndTime(staffBG,gyomuMonth);
                break;
            case DOWN_TYPE_TATEKAE:
                fileBean = tatekaeMapper.selectFileByBgAndTime(staffBG,gyomuMonth);
                break;

        }

        if(fileBean.getFILE_URL() != null){
            String fileName = fileBean.getFILE_NAME();
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            String agent = request.getHeader("USER-AGENT");    // 获取浏览器类型
            String downLoadName = null;
            try {
                // Edge
                if (null != agent && -1 != agent.indexOf("Edge")) {
                    downLoadName = java.net.URLEncoder.encode(fileName, "UTF-8");
                    // Firefox
                } else if (null != agent && -1 != agent.indexOf("Firefox")) {
                    downLoadName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
                    // Chrome或360
                } else if (null != agent && -1 != agent.indexOf("Chrome")) {
                    downLoadName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
                } else {
                    downLoadName = java.net.URLEncoder.encode(fileName, "UTF-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setHeader("Content-Disposition", "attachment; filename="+downLoadName);

            //得到该文件
            File file = new File(fileBean.getFILE_URL());
            if(!file.exists()){
                //System.out.println("Have no such file!");
                return "0";
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                //设置Http响应头告诉浏览器下载这个附件,下载的文件名也是在这里设置的
                OutputStream outputStream = response.getOutputStream();
                byte[] bytes = new byte[2048];
                int len = 0;
                while ((len = fileInputStream.read(bytes)) > 0) {
                    outputStream.write(bytes, 0, len);
                }
                fileInputStream.close();
                outputStream.close();
            }catch (IOException e) {
                e.printStackTrace();
                return "0";
            }

        }
        return null;

    }

 



    }
