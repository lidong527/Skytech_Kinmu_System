package sky.co.jp.bean;

import java.util.Date;

public class KinmuBean {
    private Long KINMU_ID;

    //zhou 2020-11-04 修改为string类型
    private String STAFF_BG;

    private String UP_TIME;

    private String GENBAN_NAME;

    private Long STANDARD_TIME;

    private Long UPPER_TIME;
    //業務開始日
    private Date START_TIME;
    //業務終了日
    private Date END_TIME;

    private Double WORK_TIME;

    private Double ZANGYOU_TIME;

    private String FILE_NAME;

    private Date CREATE_TIME;

    private String CREATE_USER;

    private Date UPDATE_TIME;

    private String UPDATE_USER;

    private Integer UPDATE_CONT;

    private Date USED_TIME;

    //20201022,zhao,str.
    //現場アドレス；
    private String GENBAN_ADDRESS;
    //備考；
    private String BIKO;
    //20201022,zhao,end.

    //zhou 20201104
    private StaffBean staff;

    public String getGENBAN_ADDRESS() {
        return GENBAN_ADDRESS;
    }

    public String getFILE_URL() {
        return FILE_URL;
    }

    public void setFILE_URL(String FILE_URL) {
        this.FILE_URL = FILE_URL;
    }

    //end
    private String FILE_URL;
    public StaffBean getStaff() {
        return staff;
    }

    public void setStaff(StaffBean staff) {
        this.staff = staff;
    }

    public Date getUSED_TIME() {
        return USED_TIME;
    }

    public void setUSED_TIME(Date USED_TIME) {
        this.USED_TIME = USED_TIME;
    }

    public String getUP_TIME() {
        return UP_TIME;
    }

    public void setUP_TIME(String UP_TIME) {
        this.UP_TIME = UP_TIME;
    }

    public Long getKINMU_ID() {
        return KINMU_ID;
    }

    public void setKINMU_ID(Long KINMU_ID) {
        this.KINMU_ID = KINMU_ID;
    }

    public String getSTAFF_BG() {
        return STAFF_BG;
    }

    public void setSTAFF_BG(String STAFF_BG) {
        this.STAFF_BG = STAFF_BG;
    }


    public String getGENBAN_NAME() {
        return GENBAN_NAME;
    }

    public void setGENBAN_NAME(String GENBAN_NAME) {
        this.GENBAN_NAME = GENBAN_NAME;
    }

    public Long getSTANDARD_TIME() {
        return STANDARD_TIME;
    }

    public void setSTANDARD_TIME(Long STANDARD_TIME) {
        this.STANDARD_TIME = STANDARD_TIME;
    }

    public Long getUPPER_TIME() {
        return UPPER_TIME;
    }

    public void setUPPER_TIME(Long UPPER_TIME) {
        this.UPPER_TIME = UPPER_TIME;
    }

    public Double getWORK_TIME() {
        return WORK_TIME;
    }

    public void setWORK_TIME(Double WORK_TIME) {
        this.WORK_TIME = WORK_TIME;
    }

    public Double getZANGYOU_TIME() {
        return ZANGYOU_TIME;
    }

    public void setZANGYOU_TIME(Double ZANGYOU_TIME) {
        this.ZANGYOU_TIME = ZANGYOU_TIME;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public String getCREATE_USER() {
        return CREATE_USER;
    }

    public void setCREATE_USER(String CREATE_USER) {
        this.CREATE_USER = CREATE_USER;
    }

    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public String getUPDATE_USER() {
        return UPDATE_USER;
    }

    public void setUPDATE_USER(String UPDATE_USER) {
        this.UPDATE_USER = UPDATE_USER;
    }

    public Integer getUPDATE_CONT() {
        return UPDATE_CONT;
    }

    public void setUPDATE_CONT(Integer UPDATE_CONT) {
        this.UPDATE_CONT = UPDATE_CONT;
    }

    public Date getSTART_TIME() {
        return START_TIME;
    }

    public void setSTART_TIME(Date START_TIME) {
        this.START_TIME = START_TIME;
    }

    public Date getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(Date END_TIME) {
        this.END_TIME = END_TIME;
    }

    public String getGENBA_ADDRESS() {
        return GENBAN_ADDRESS;
    }

    public void setGENBAN_ADDRESS(String GENBAN_ADDRESS) {
        this.GENBAN_ADDRESS = GENBAN_ADDRESS;
    }

    public String getBIKO() {
        return BIKO;
    }

    public void setBIKO(String BIKO) {
        this.BIKO = BIKO;
    }


}