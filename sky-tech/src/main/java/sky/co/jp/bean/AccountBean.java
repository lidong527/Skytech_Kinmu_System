package sky.co.jp.bean;

import java.util.Date;

public class AccountBean {
    private String USER_MAIL;

    private Integer ID;

    private String PASSWORD;

    private String PASS_SEC;

    private Boolean INITIALIZATION_FLG;

    private Date CREATE_TIME;

    private String CREATE_USER;

    private Date UPDATE_TIME;

    private String UPDATE_USER;

    private Integer UPDATE_CONT;

    public String getUSER_MAIL() {
        return USER_MAIL;
    }

    public void setUSER_MAIL(String USER_MAIL) {
        this.USER_MAIL = USER_MAIL;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getPASS_SEC() {
        return PASS_SEC;
    }

    public void setPASS_SEC(String PASS_SEC) {
        this.PASS_SEC = PASS_SEC;
    }

    public Boolean getINITIALIZATION_FLG() {
        return INITIALIZATION_FLG;
    }

    public void setINITIALIZATION_FLG(Boolean INITIALIZATION_FLG) {
        this.INITIALIZATION_FLG = INITIALIZATION_FLG;
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
}