package sky.co.jp.form;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KoutuForm {
    public Long koutuId;

    public String staffBg;

    public String upTime;

    public String startSt;

    public String endSt;

    private Double usedMoneyAll;

    private Double usedMoneyEtc;

    private Double usedMoneyTsu;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="JST")
    public Date usedTime;

    public String fileName;

    public String fileUrl;

    public String staffName;

    public String mFlag;

    public String buttonClass;

    public Integer getUpdateCont() {
        return updateCont;
    }

    public void setUpdateCont(Integer updateCont) {
        this.updateCont = updateCont;
    }

    //zhou 20201106
    private Integer updateCont;

    public String errorMessage;

    public String getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public Long getKoutuId() {
        return koutuId;
    }

    public void setKoutuId(Long koutuId) {
        this.koutuId = koutuId;
    }

    public String getStaffBg() {
        return staffBg;
    }

    public void setStaffBg(String staffBg) {
        this.staffBg = staffBg;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getStartSt() {
        return startSt;
    }

    public void setStartSt(String startSt) {
        this.startSt = startSt;
    }

    public String getEndSt() {
        return endSt;
    }

    public void setEndSt(String endSt) {
        this.endSt = endSt;
    }

    public Double getUsedMoneyAll() {
        return usedMoneyAll;
    }

    public void setUsedMoneyAll(Double usedMoneyAll) {
        this.usedMoneyAll = usedMoneyAll;
    }

    public Double getUsedMoneyEtc() {
        return usedMoneyEtc;
    }

    public void setUsedMoneyEtc(Double usedMoneyEtc) {
        this.usedMoneyEtc = usedMoneyEtc;
    }

    public Double getUsedMoneyTsu() {
        return usedMoneyTsu;
    }

    public void setUsedMoneyTsu(Double usedMoneyTsu) {
        this.usedMoneyTsu = usedMoneyTsu;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getMFlag() {
        return mFlag;
    }

    public void setMFlag(String mFlag) {
        this.mFlag = mFlag;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
