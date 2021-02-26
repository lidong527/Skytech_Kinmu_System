package sky.co.jp.queryData;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffReportQueryObject extends QueryObject{


    private String STAFF_NAME;
    private String STAFF_BG;


    private String IT_TYPE;

    private Integer LM_KINMU_STATUS;

    private Integer LM_KOUTU_STATUS;

    private Integer LM_TATEKAE_STATUS;

    private Integer TM_KINMU_STATUS;

    private Integer TM_KOUTU_STATUS;

    private Integer TM_TATEKAE_STATUS;

    private Integer month;

    private Integer contractType;

    private String currentMenu;


}
