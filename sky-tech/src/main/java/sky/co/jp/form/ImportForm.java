package sky.co.jp.form;

public class ImportForm {
    /**
     * Excel读取数据实体
     * 创建人：liuyang
     * 创建时间：2020/10/26
     */
    Double usedMoneyEtc;//交通费file文件金额
    Double usedMoneyAll;//交通费总金额
    Double usedMoneyTsukin;//交通费定期金额
    Boolean flag;

    public Double getUsedMoneyEtc() {
        return usedMoneyEtc;
    }

    public void setUsedMoneyEtc(Double usedMoneyEtc) {
        this.usedMoneyEtc = usedMoneyEtc;
    }

    public Double getUsedMoneyAll() {
        return usedMoneyAll;
    }

    public void setUsedMoneyAll(Double usedMoneyAll) {
        this.usedMoneyAll = usedMoneyAll;
    }

    public Double getUsedMoneyTsukin() {
        return usedMoneyTsukin;
    }

    public void setUsedMoneyTsukin(Double usedMoneyTsukin) {
        this.usedMoneyTsukin = usedMoneyTsukin;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }
}
