package sky.co.jp.business.impl;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sky.co.jp.bean.ExcelData;
import sky.co.jp.bean.KoutuBean;
import sky.co.jp.form.ImportForm;
import sky.co.jp.mapper.KoutuMapper;
import sky.co.jp.business.IExcelBusiness;
import sky.co.jp.util.ExcelUtil;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;

/**
 * 创建人：liuyang
 * 创建时间：2020/10/26
 */
@Service
public class ExcelBusinessImpl implements IExcelBusiness {

    @Autowired
    private KoutuMapper koutuMapper;

    /**
     * 方法名：
     * 功能：导出
     * 描述：目前项目中该功能还未使用，使用时请修改
     * 创建人：liuyang
     * 创建时间：2020/10/26
     */
    @Override
    public Boolean exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
        //log.info("导出数据开始。。。。。。");
        //查询数据并赋值给ExcelData

        List<KoutuBean> koutuBeanList = koutuMapper.selectAll();
        List<String[]> list = new ArrayList<String[]>();
        for (KoutuBean koutuBean : koutuBeanList){
            String[] arrs = new String[koutuBeanList.size()];
            arrs[0] = String.valueOf(koutuBean.getUSED_MONEY_ALL());
            arrs[1] = String.valueOf(koutuBean.getUSED_MONEY_ETC());
            arrs[2] = String.valueOf(koutuBean.getUSED_MONEY_TSUKIN());
            list.add(arrs);
        }

        //表头赋值
        String[] head = {"序列", "总金额", "file金额", "通勤金额"};
        ExcelData data = new ExcelData();
        data.setHead(head);
        data.setData(list);
        data.setFileName(fileName);
        //实现导出
//        try {
//            //ExcelUtil.exportExcel(response, data);
//            //log.info("导出数据结束。。。。。。");
//            return true;
//        } catch (Exception e) {
//            //log.info("导出数据失败。。。。。。");
//            return false;
//        }
        return true;
    }

    /**
     * 方法名：readExcelKoutu
     * 功能：读取交通费数据
     * 创建人：liuyang
     * 创建时间：2020/10/26
     */
    @Override
    public Map<String, Double> readExcelKoutu(MultipartFile file) throws Exception{
        //log.info("导入数据开始。。。。。。");
        int flag = 0;
        Map<String, Double> map = new HashMap();
        Sheet sheet = ExcelUtil.readExcel(file);
        if(null != sheet){
            for(int line = 2; line <= sheet.getLastRowNum();line++){
                Row row = sheet.getRow(line);
                Cell cell1 =  row.getCell(0);
                if(row == null && row.equals("")){
                    continue;
                }
                Object value = ExcelUtil.getCellValue(cell1);
                System.out.println(value+"__------");

                //row.getCell(0).setCellType((CellType) ExcelUtil.getCellValue(cell1));
                //读取文件并获取数据
                if(value.equals("小計") && flag == 0){
                    Double moneyEtc = row.getCell(1).getNumericCellValue();
                    flag = 1;
                    map.put("moneyEtc",moneyEtc);
                    System.out.println(moneyEtc+"++++");
                }else if(value.equals("小計") && flag == 1){
                    Double moneyTsu = row.getCell(1).getNumericCellValue();
                    map.put("moneyTsu",moneyTsu);
                    System.out.println(moneyTsu+",,,");
                }else if (value.equals("合計")){
                    Double moneyAll = row.getCell(1).getNumericCellValue();
                    map.put("moneyAll",moneyAll);
                    System.out.println(moneyAll+"=====");
                    break;
                }
            }
            }
        return map;
    }
    /**
     * 方法名：readExcelKinmu
     * 功能：读取勤务表数据
     * 创建人：liuyang
     * 创建时间：2020/11/19
     */
    @Override
    public Map<String, Object> readExcelKinmu(MultipartFile file) throws Exception {
        //log.info("导入数据开始。。。。。。");
        Map<String, Object> map = new HashMap();
        Sheet sheet = ExcelUtil.readExcel(file);
        if(null != sheet){
            for(int line = 2; line <= sheet.getLastRowNum();line++){
                Row row = sheet.getRow(line);
                if(row == null || row.equals("")){
                    continue;
                }
                if (row.getCell(0) !=null && !row.getCell(0).equals("")){
                    Cell cell1 = row.getCell(0);
                    Object value = ExcelUtil.getCellValue(cell1);
                    System.out.println(value+"__------");

                    //row.getCell(0).setCellType((CellType) ExcelUtil.getCellValue(cell1));
                    //读取文件并获取数据
                    if(value.equals("会 社 名")){
                        String GENBAN_NAME = row.getCell(1).getStringCellValue();
                        map.put("GENBAN_NAME",GENBAN_NAME);
                        System.out.println(GENBAN_NAME+"++++");
                    }else if(value.equals("稼働時間") ){
                        Date workDate = row.getCell(1).getDateCellValue();
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(workDate);
                        Double WORK_TIME = Double.valueOf((calendar.get(Calendar.DATE)*24 + calendar.get(Calendar.HOUR) + (float)calendar.get(Calendar.MINUTE)/60));
                        map.put("WORK_TIME",WORK_TIME);
                        System.out.println(WORK_TIME+",,,");
                    }
                }else {
                    continue;
                }
            }
        }
        return map;
    }
    /**
     * 方法名：readExcelKinmu
     * 功能：读取立替金数据
     * 创建人：liuyang
     * 创建时间：2020/11/19
     */
    @Override
    public Map<String, Double> readExcelTate(MultipartFile file) throws Exception {
        //log.info("导入数据开始。。。。。。");
        Map<String, Double> map = new HashMap();
        Sheet sheet = ExcelUtil.readExcelTate(file);
        if(null != sheet){
            for(int line = 2; line <= sheet.getLastRowNum();line++){
                Row row = sheet.getRow(line);
                if(row == null || row.equals("")){
                    continue;
                }
                if (row.getCell(0) !=null && !row.getCell(0).equals("")){
                    Cell cell1 = row.getCell(0);
                    Object value = ExcelUtil.getCellValue(cell1);
                    System.out.println(value+"__------");

                    //row.getCell(0).setCellType((CellType) ExcelUtil.getCellValue(cell1));
                    //读取文件并获取数据
                    if(value.equals("合　　　計")){
                        Double USED_PRICE = row.getCell(6).getNumericCellValue();
                        map.put("USED_PRICE",USED_PRICE);
                        System.out.println(USED_PRICE+"+++");
                    }
                }else {
                    continue;
                }
            }
        }
        return map;
    }
}
