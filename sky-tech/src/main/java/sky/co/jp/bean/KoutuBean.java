package sky.co.jp.bean;

import java.util.Date;

public class KoutuBean {
    private Long KOUTU_ID;

    //staffbg数据类型修改为string
    //zhou 20201023
    private String STAFF_BG;

    private String UP_TIME;

    private String START_ST;

    private String END_ST;

    private Double USED_MONEY_ALL;

    private Double USED_MONEY_ETC;

    private Double USED_MONEY_TSUKIN;
    //USED_TIME数据类型修改为Date
    //liuyang 20201029
    private Date USED_TIME;

    private String FILE_NAME;

    private String FILE_URL;

    public Integer getUPDATE_CONT() {
        return UPDATE_CONT;
    }

    public void setUPDATE_CONT(Integer UPDATE_CONT) {
        this.UPDATE_CONT = UPDATE_CONT;
    }

    //zhou 改为integer类型 20201109
    private Integer UPDATE_CONT;

    public StaffBean getStaff() {
        return staff;
    }

    public void setStaff(StaffBean staff) {
        this.staff = staff;
    }

    //zhou 20201109
    private StaffBean staff;

    public String getUPDATE_USER() {
        return UPDATE_USER;
    }

    public void setUPDATE_USER(String UPDATE_USER) {
        this.UPDATE_USER = UPDATE_USER;
    }

    //zhou 增加更新者字段 20201109
    private String UPDATE_USER;



    public Long getKOUTU_ID() {
        return KOUTU_ID;
    }

    public void setKOUTU_ID(Long KOUTU_ID) {
        this.KOUTU_ID = KOUTU_ID;
    }

    public String getSTAFF_BG() {
        return STAFF_BG;
    }

    public void setSTAFF_BG(String STAFF_BG) {
        this.STAFF_BG = STAFF_BG;
    }

    public String getUP_TIME() {
        return UP_TIME;
    }

    public void setUP_TIME(String UP_TIME) {
        this.UP_TIME = UP_TIME;
    }

    public String getSTART_ST() {
        return START_ST;
    }

    public void setSTART_ST(String START_ST) {
        this.START_ST = START_ST;
    }

    public String getEND_ST() {
        return END_ST;
    }

    public void setEND_ST(String END_ST) {
        this.END_ST = END_ST;
    }

    public Double getUSED_MONEY_ALL() {
        return USED_MONEY_ALL;
    }

    public void setUSED_MONEY_ALL(Double USED_MONEY_ALL) {
        this.USED_MONEY_ALL = USED_MONEY_ALL;
    }

    public Double getUSED_MONEY_ETC() {
        return USED_MONEY_ETC;
    }

    public void setUSED_MONEY_ETC(Double USED_MONEY_ETC) {
        this.USED_MONEY_ETC = USED_MONEY_ETC;
    }

    public Double getUSED_MONEY_TSUKIN() {
        return USED_MONEY_TSUKIN;
    }

    public void setUSED_MONEY_TSUKIN(Double USED_MONEY_TSUKIN) {
        this.USED_MONEY_TSUKIN = USED_MONEY_TSUKIN;
    }

    public Date getUSED_TIME() { return USED_TIME; }

    public void setUSED_TIME(Date USED_TIME) { this.USED_TIME = USED_TIME; }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public String getFILE_URL() {
        return FILE_URL;
    }

    public void setFILE_URL(String FILE_URL) {
        this.FILE_URL = FILE_URL;
    }


}