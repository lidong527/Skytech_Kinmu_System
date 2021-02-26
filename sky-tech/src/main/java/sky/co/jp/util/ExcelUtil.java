package sky.co.jp.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * 功能：Excel处理数据
 * 创建人：liuyang
 * 创建时间：20201027
 */
@Slf4j
public class ExcelUtil {
    private Workbook workbook;
    private Sheet sheet;
    /**
     * 方法名：readExcel
     * 功能：读取Excel中的数据
     * 创建人：liuyang
     * 创建时间：20201027
     */
    public static Sheet readExcel(MultipartFile file ) throws Exception{
        //log.info("导入解析开始，fileName:{}",fileName);
        try {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            InputStream ins = file.getInputStream();
            Workbook wb = null;
            Sheet sheet = null;
            //判断Excel格式是xlsx还是xls
            if(suffix.equals("xlsx")){
                wb = new XSSFWorkbook(ins);
            }else{
                wb = new HSSFWorkbook(ins);
            }
            sheet = wb.getSheetAt(0);
          /*  if (wb.getNumberOfSheets()>1 && wb.getSheetName(1).equals("経費明細書")){
                 sheet = wb.getSheetAt(1);
            }else {*/
                 //sheet = wb.getSheetAt(0);
            //}
           // log.info("导入文件解析成功！");
            return sheet;
        }catch (Exception e){
            //log.info("导入文件解析失败！");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "msg", "title", JOptionPane.PLAIN_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    public static Sheet readExcelTate(MultipartFile file ) throws Exception{
        //log.info("导入解析开始，fileName:{}",fileName);
        try {
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
            InputStream ins = file.getInputStream();
            Workbook wb = null;
            Sheet sheet = null;
            //判断Excel格式是xlsx还是xls
            if(suffix.equals("xlsx")){
                wb = new XSSFWorkbook(ins);
            }else{
                wb = new HSSFWorkbook(ins);
            }
            sheet = wb.getSheetAt(1);
          /*  if (wb.getNumberOfSheets()>1 && wb.getSheetName(1).equals("経費明細書")){
                 sheet = wb.getSheetAt(1);
            }else {*/
            //sheet = wb.getSheetAt(0);
            //}
            // log.info("导入文件解析成功！");
            return sheet;
        }catch (Exception e){
            //log.info("导入文件解析失败！");
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "msg", "title", JOptionPane.PLAIN_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }

    public static  Object getCellValue(Cell cell) {
        Object value = "";

        DecimalFormat df = new DecimalFormat("0");//格式化number String字符串
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//日期格式化
        if(cell != null && !cell.getCellTypeEnum().equals(HSSFCell.CELL_TYPE_STRING)){
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case NUMERIC:
                    if("General".equals(cell.getCellStyle().getDataFormatString())){
                        value = df.format(cell.getNumericCellValue());
                    }else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                        value = sdf.format(cell.getDateCellValue());
                    }else{
                        value = df.format(cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case BLANK:
                    value = " ";
                    break;
                case _NONE:
                    value = " ";
                    break;
                case ERROR:
                    value =" ";
                    break;
                case FORMULA:
                    value = " ";
                    break;
                default:
                    value = cell.toString();
                    break;
            }
        }
        return value;
    }

    public Boolean nextSheet() {
        Boolean state = true;

        if(sheet == null) {
            sheet = workbook.getSheetAt(0);
        } else {
            int index = workbook.getSheetIndex(sheet) + 1;
            if(index >= workbook.getNumberOfSheets()) {
                sheet = null;
            } else {
                sheet = workbook.getSheetAt(index);
            }

            if(sheet == null) {
                state = false;
            }
        }
        return state;
    }
}
