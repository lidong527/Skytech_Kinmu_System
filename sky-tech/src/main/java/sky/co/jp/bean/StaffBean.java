package sky.co.jp.bean;

import java.util.Date;

public class StaffBean {
    private Long ID;
    //zhou 20201023 修改员工编号为string类型 str
    private String STAFF_BG;

    private String STAFF_NAME;

    private String STAFF_MAILADDRESS;

    private String STAFF_SEX;

    private Integer PERMISSION_BG;

    private Integer STAFF_STATUS;

    private Long STANDARD_TIME;

    private Long UPPER_TIME;

    private Integer GROUP_ID;

    private String GENBAN_NAME;
    //liuyang 20201024 增加现场地址
    private String GENBAN_ADDRESS;

    private Integer LM_KINMU_STATUS;

    private Integer LM_KOUTU_STATUS;

    private Integer LM_TATEKAE_STATUS;

    private Integer TM_KINMU_STATUS;

    private Integer TM_KOUTU_STATUS;

    private Integer TM_TATEKAE_STATUS;

    private String CREATE_USER;

    private Date CREATE_TIME;

    private Date UPDATE_TIME;

    private String UPDATE_USER;

    private Integer UPDATE_CONT;

    private Boolean IS_LEADER;

    private Long LEADER_ID;

    private String LM_WORK_TIME;

    private String LM_ZANGYOU_TIME;

    private String TM_WORK_TIME;

    private String TM_ZANGYOU_TIME;

    private String IT_TYPE;
    //liuyang 20201024 增加备注
    private String REMARKS;

    //zhou 20201022
    private KinmuBean kinmuBean;
    //liuyang 20201027
    private Integer RESIGN_FLAG;//退職マーク,退職:0

    private String RESIGN_TYPE;//退職の種類

    private Date RESIGN_TIME;//退職時間

    private String RESIGN_REASON;//退職理由


    //zhou 20201118
    private KoutuBean koutuBean;

    private TatekaeBean tatekaeBean;

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    //zhou 20201119
    private  Integer contractType;

    private  String contractTypeName;

    public TatekaeBean getTatekaeBean() {
        return tatekaeBean;
    }

    public void setTatekaeBean(TatekaeBean tatekaeBean) {
        this.tatekaeBean = tatekaeBean;
    }
    public KoutuBean getKoutuBean() {
        return koutuBean;
    }

    public void setKoutuBean(KoutuBean koutuBean) {
        this.koutuBean = koutuBean;
    }

    public KinmuBean getKinmuBean() {
        return kinmuBean;
    }

    public void setKinmuBean(KinmuBean kinmuBean) {
        this.kinmuBean = kinmuBean;
    }
    //end

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getSTAFF_BG() {
        return STAFF_BG;
    }

    public void setSTAFF_BG(String STAFF_BG) {
        this.STAFF_BG = STAFF_BG;
    }

    public String getSTAFF_NAME() {
        return STAFF_NAME;
    }

    public void setSTAFF_NAME(String STAFF_NAME) {
        this.STAFF_NAME = STAFF_NAME;
    }

    public String getSTAFF_MAILADDRESS() {
        return STAFF_MAILADDRESS;
    }

    public void setSTAFF_MAILADDRESS(String STAFF_MAILADDRESS) {
        this.STAFF_MAILADDRESS = STAFF_MAILADDRESS;
    }

    public String getSTAFF_SEX() {
        return STAFF_SEX;
    }

    public void setSTAFF_SEX(String STAFF_SEX) {
        this.STAFF_SEX = STAFF_SEX;
    }

    public Integer getPERMISSION_BG() {
        return PERMISSION_BG;
    }

    public void setPERMISSION_BG(Integer PERMISSION_BG) {
        this.PERMISSION_BG = PERMISSION_BG;
    }

    public Integer getSTAFF_STATUS() {
        return STAFF_STATUS;
    }

    public void setSTAFF_STATUS(Integer STAFF_STATUS) {
        this.STAFF_STATUS = STAFF_STATUS;
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

    public Integer getGROUP_ID() {
        return GROUP_ID;
    }

    public void setGROUP_ID(Integer GROUP_ID) {
        this.GROUP_ID = GROUP_ID;
    }

    public String getGENBAN_NAME() {
        return GENBAN_NAME;
    }

    public void setGENBAN_NAME(String GENBAN_NAME) {
        this.GENBAN_NAME = GENBAN_NAME;
    }

    public Integer getLM_KINMU_STATUS() {
        return LM_KINMU_STATUS==null?0:LM_KINMU_STATUS;
    }

    public void setLM_KINMU_STATUS(Integer LM_KINMU_STATUS) {
        this.LM_KINMU_STATUS = LM_KINMU_STATUS;
    }

    public Integer getLM_KOUTU_STATUS() {
        return LM_KOUTU_STATUS==null?0:LM_KOUTU_STATUS;
    }

    public void setLM_KOUTU_STATUS(Integer LM_KOUTU_STATUS) {
        this.LM_KOUTU_STATUS = LM_KOUTU_STATUS;
    }

    public Integer getLM_TATEKAE_STATUS() {
        return LM_TATEKAE_STATUS==null?0:LM_TATEKAE_STATUS;
    }

    public void setLM_TATEKAE_STATUS(Integer LM_TATEKAE_STATUS) {
        this.LM_TATEKAE_STATUS = LM_TATEKAE_STATUS;
    }

    public Integer getTM_KINMU_STATUS() {
        return TM_KINMU_STATUS==null?0:TM_KINMU_STATUS;
    }

    public void setTM_KINMU_STATUS(Integer TM_KINMU_STATUS) {
        this.TM_KINMU_STATUS = TM_KINMU_STATUS;
    }

    public Integer getTM_KOUTU_STATUS() {
        return TM_KOUTU_STATUS==null?0:TM_KOUTU_STATUS;
    }

    public void setTM_KOUTU_STATUS(Integer TM_KOUTU_STATUS) {
        this.TM_KOUTU_STATUS = TM_KOUTU_STATUS;
    }

    public Integer getTM_TATEKAE_STATUS() {
        return TM_TATEKAE_STATUS;
    }

    public void setTM_TATEKAE_STATUS(Integer TM_TATEKAE_STATUS) {
        this.TM_TATEKAE_STATUS = TM_TATEKAE_STATUS;
    }

    public String getCREATE_USER() {
        return CREATE_USER;
    }

    public void setCREATE_USER(String CREATE_USER) {
        this.CREATE_USER = CREATE_USER;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
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

    public Boolean getIS_LEADER() {
        return IS_LEADER;
    }

    public void setIS_LEADER(Boolean IS_LEADER) {
        this.IS_LEADER = IS_LEADER;
    }

    public Long getLEADER_ID() {
        return LEADER_ID;
    }

    public void setLEADER_ID(Long LEADER_ID) {
        this.LEADER_ID = LEADER_ID;
    }

    public String getLM_WORK_TIME() {
        return LM_WORK_TIME;
    }

    public void setLM_WORK_TIME(String LM_WORK_TIME) {
        this.LM_WORK_TIME = LM_WORK_TIME;
    }

    public String getLM_ZANGYOU_TIME() {
        return LM_ZANGYOU_TIME;
    }

    public void setLM_ZANGYOU_TIME(String LM_ZANGYOU_TIME) {
        this.LM_ZANGYOU_TIME = LM_ZANGYOU_TIME;
    }

    public String getTM_WORK_TIME() {
        return TM_WORK_TIME;
    }

    public void setTM_WORK_TIME(String TM_WORK_TIME) {
        this.TM_WORK_TIME = TM_WORK_TIME;
    }

    public String getTM_ZANGYOU_TIME() {
        return TM_ZANGYOU_TIME;
    }

    public void setTM_ZANGYOU_TIME(String TM_ZANGYOU_TIME) {
        this.TM_ZANGYOU_TIME = TM_ZANGYOU_TIME;
    }

    public String getIT_TYPE() {
        return IT_TYPE;
    }

    public void setIT_TYPE(String IT_TYPE) {
        this.IT_TYPE = IT_TYPE;
    }

    public String getGENBAN_ADDRESS() { return GENBAN_ADDRESS; }

    public void setGENBAN_ADDRESS(String GENBAN_ADDRESS) { this.GENBAN_ADDRESS = GENBAN_ADDRESS; }

    public String getREMARKS() { return REMARKS; }

    public void setREMARKS(String REMARKS) { this.REMARKS = REMARKS; }

	public String getContractTypeName() {
		return contractTypeName;
	}

	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}
}