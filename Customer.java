
public class Customer {
	private int oCid;
	private String oName;
	private String oCity;
	public int getoCid() {
		return oCid;
	}
	public void setoCid(int oCid) {
		this.oCid = oCid;
	}
	public String getoName() {
		return oName;
	}
	public void setoName(String oName) {
		this.oName = oName;
	}
	public String getoCity() {
		return oCity;
	}
	public void setoCity(String oCity) {
		this.oCity = oCity;
	}
	@Override
	public String toString() {
		return "Customer [Cid=" + oCid + ", Name=" + oName + ", City=" + oCity + "]";
	}
	
}
