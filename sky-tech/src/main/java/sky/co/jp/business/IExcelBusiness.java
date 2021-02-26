package sky.co.jp.business;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * 功能：读取数据、导出
 * 备注：导出功能暂未添加
 * 创建人：liuyang
 * 创建时间：20201026
 */
public interface IExcelBusiness {
    //导出功能
    Boolean exportExcel(HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize);
    //交通费读取功能
    Map<String, Double> readExcelKoutu(MultipartFile file) throws Exception;
    //20201119 勤务表读取功能
    Map<String, Object> readExcelKinmu(MultipartFile file) throws Exception;
    // 20201119 立替金读取功能
    Map<String, Double> readExcelTate(MultipartFile file) throws Exception;
}
