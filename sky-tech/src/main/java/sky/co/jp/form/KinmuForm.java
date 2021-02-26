package sky.co.jp.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class KinmuForm {
    public Long kinmuId;
    public String company;
    public String upTime;
    public Long standardtime;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="JST")
    public Date starttime;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="JST")
    public Date endtime;
    public Long upperTime;
    public Double workTime;
    public Double zangyouTime;
    public String fileName;
    public String M_FLG;
    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="JST")
    public Date USED_TIME;
    public String staffName;
    public String buttonClass;
    public String staffBg;
    public String ganbanAddress;
    public String biko;
    public String errorMessage;

    public Integer getUpdateCont() {
        return updateCont;
    }

    public void setUpdateCont(Integer updateCont) {
        this.updateCont = updateCont;
    }

    //zhou 20201106
    private Integer updateCont;

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Date getUSED_TIME() {
        return USED_TIME;
    }

    public void setUSED_TIME(Date USED_TIME) {
        this.USED_TIME = USED_TIME;
    }

    public Long getKinmuId() {
        return kinmuId;
    }

    public void setKinmuId(Long kinmuId) {
        this.kinmuId = kinmuId;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public Long getStandardtime() {
        return standardtime;
    }

    public void setStandardtime(Long standardtime) {
        this.standardtime = standardtime;
    }

    public Long getUpperTime() {
        return upperTime;
    }

    public void setUpperTime(Long upperTime) {
        this.upperTime = upperTime;
    }

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
    }

    public Double getZangyouTime() {
        return zangyouTime;
    }

    public void setZangyouTime(Double zangyouTime) {
        this.zangyouTime = zangyouTime;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    public String getM_FLG() {
        return M_FLG;
    }

    public void setM_FLG(String m_FLG) {
        M_FLG = m_FLG;
    }

    public String getButtonClass() {
        return buttonClass;
    }

    public void setButtonClass(String buttonClass) {
        this.buttonClass = buttonClass;
    }

    public String getStaffBg() {
        return staffBg;
    }

    public void setStaffBg(String staffBg) {
        this.staffBg = staffBg;
    }
    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getGanbanAddress() {
        return ganbanAddress;
    }

    public void setGanbanAddress(String ganbanAddress) {
        this.ganbanAddress = ganbanAddress;
    }

    public String getBiko() {
        return biko;
    }

    public void setBiko(String biko) {
        this.biko = biko;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}