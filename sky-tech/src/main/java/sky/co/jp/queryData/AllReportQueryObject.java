package sky.co.jp.queryData;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class AllReportQueryObject extends QueryObject{
    private String STAFF_NAME;
    private String STAFF_BG;
    private String GYOMU_DATE;
    private String STAFF_MAILADDRESS;
    private Integer TM_KINMU_STATUS;
    private Integer TM_KOUTU_STATUS;
    private Integer TM_TATEKAE_STATUS;

    private String START_ST;

    private String END_ST;

    //liuyang 20201103 交通费查询
    private Integer timeStarYear;
    private String timeStarMonth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "JST")
    private Date usedTimeStart;
    private Integer timeEndYear;
    private String timeEndMonth;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "JST")
    private Date usedTimeEnd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "JST")
    private Date USED_TIME;
    private Double USED_MONEY_ALL;
    private Double USED_MONEY_ETC;
    private Double USED_MONEY_TSUKIN;

    //liuyang 20201103 勤务表查询
    private String GENBAN_NAME;
    private Long STANDARD_TIME;
    private Long UPPER_TIME;
    //業務開始日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "JST")
    private Date START_TIME;
    //業務終了日
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "JST")
    private Date END_TIME;
    private Double WORK_TIME;
    private Double ZANGYOU_TIME;
    //現場アドレス；
    private String GENBAN_ADDRESS;
    //備考；
    private String REMARKS;


    // liuyang 20201103 立替金查询
    private String SUB_COMPANY;
    private Double USED_PRICE;

}
