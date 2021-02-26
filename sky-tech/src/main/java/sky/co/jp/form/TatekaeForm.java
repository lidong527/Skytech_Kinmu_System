package sky.co.jp.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TatekaeForm {
    public Long TATEKAE_ID;

    public String STAFF_BG;

    public String UP_TIME;

    public Double USED_PRICE;

    public String SUB_COMPANY;

    private String FILE_NAME;

    public Date CREATE_TIME;

    public String CREATE_USER;

    public Date UPDATE_TIME;

    public String UPDATE_USER;

    public Long UPDATE_CONT;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="JST")
    public Date USED_TIME;

    public String USER_NAME;

    public String buttonClass;

    public String errorMessage;

    //zhou 20201111
    private String staffName;

    private String flag;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }


    public String getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public Long getTATEKAE_ID() {
        return TATEKAE_ID;
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        this.FILE_NAME = FILE_NAME;
    }

    public void setTATEKAE_ID(Long TATEKAE_ID) {
        this.TATEKAE_ID = TATEKAE_ID;
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

    public void setSTAFF_BG(String STAFF_BG) {
        this.STAFF_BG = STAFF_BG;
    }

    public String getSTAFF_BG() {
        return STAFF_BG;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}