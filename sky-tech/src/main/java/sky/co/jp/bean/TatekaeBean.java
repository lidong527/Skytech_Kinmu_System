package sky.co.jp.bean;

import java.util.Date;

public class TatekaeBean {
    private Long TATEKAE_ID;

    //staffbg数据类型修改为string
    //zhou 20201023
    private String STAFF_BG;

    private String UP_TIME;

    private Double USED_PRICE;

    private String SUB_COMPANY;

    private String FILE_NAME;

    private Date CREATE_TIME;

    private String CREATE_USER;

    private Date UPDATE_TIME;

    private String UPDATE_USER;

    private Long UPDATE_CONT;

    private Date USED_TIME;

    public String getFILE_URL() {
        return FILE_URL;
    }

    public void setFILE_URL(String FILE_URL) {
        this.FILE_URL = FILE_URL;
    }

    private String FILE_URL;


    //zhou 20201111
    private StaffBean staff;

    public StaffBean getStaff() {
        return staff;
    }

    public void setStaff(StaffBean staff) {
        this.staff = staff;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public Long getTATEKAE_ID() {
        return TATEKAE_ID;
    }

    public void setTATEKAE_ID(Long TATEKAE_ID) {
        this.TATEKAE_ID = TATEKAE_ID;
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

    public Double getUSED_PRICE() {
        return USED_PRICE;
    }

    public void setUSED_PRICE(Double USED_PRICE) {
        this.USED_PRICE = USED_PRICE;
    }

    public String getSUB_COMPANY() {
        return SUB_COMPANY;
    }

    public void setSUB_COMPANY(String SUB_COMPANY) {
        this.SUB_COMPANY = SUB_COMPANY;
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

    public Long getUPDATE_CONT() {
        return UPDATE_CONT;
    }

    public void setUPDATE_CONT(Long UPDATE_CONT) {
        this.UPDATE_CONT = UPDATE_CONT;
    }

    public Date getUSED_TIME() {
        return USED_TIME;
    }

    public void setUSED_TIME(Date USED_TIME) {
        this.USED_TIME = USED_TIME;
    }
}