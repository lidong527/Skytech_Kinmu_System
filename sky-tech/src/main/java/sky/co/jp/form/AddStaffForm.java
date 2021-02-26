package sky.co.jp.form;

public class AddStaffForm {

    private String staffBg;

    private String email;

    private String permission;

    private String type;
    // 契約種類
    private int contractType;

    public String getStaffBg() {
        return staffBg;
    }

    public void setStaffBg(String staffBg) {
        this.staffBg = staffBg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

	public int getContractType() {
		return contractType;
	}

	public void setContractType(int contractType) {
		this.contractType = contractType;
	}
}
