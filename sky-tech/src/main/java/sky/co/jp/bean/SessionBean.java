package sky.co.jp.bean;

public class SessionBean {

    //zhou 20201023
    //修改public为private
    //staffbg类型修改为string
    private String STAFF_BG;
    private int PERMISSION_BG;
    private String CODE_CONTENT2;


    //zhou 20201104 增加staffname。start
    private String STAFF_NAME;


    public String getSTAFF_NAME() {
        return STAFF_NAME;
    }

    public void setSTAFF_NAME(String STAFF_NAME) {
        this.STAFF_NAME = STAFF_NAME;
    }
    //end

    public String getSTAFF_BG() {
        return STAFF_BG;
    }

    public void setSTAFF_BG(String STAFF_BG) {
        this.STAFF_BG = STAFF_BG;
    }

    public int getPERMISSION_BG() {
        return PERMISSION_BG;
    }

    public void setPERMISSION_BG(int PERMISSION_BG) {
        this.PERMISSION_BG = PERMISSION_BG;
    }

    public String getCODE_CONTENT2() {
        return CODE_CONTENT2;
    }

    public void setCODE_CONTENT2(String CODE_CONTENT2) {
        this.CODE_CONTENT2 = CODE_CONTENT2;
    }
}
