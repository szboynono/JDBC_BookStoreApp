import java.sql.Date;

public class Purchase {
	private int oCid;
	private String oClub;
	private String oTitle;
	private int oYear;
	private java.sql.Timestamp oWhen;
	private int oQnty;
	public int getoCid() {
		return oCid;
	}
	public void setoCid(int oCid) {
		this.oCid = oCid;
	}
	public String getoClub() {
		return oClub;
	}
	public void setoClub(String oClub) {
		this.oClub = oClub;
	}
	public String getoTitle() {
		return oTitle;
	}
	public void setoTitle(String oTitle) {
		this.oTitle = oTitle;
	}
	public int getoYear() {
		return oYear;
	}
	public void setoYear(int oYear) {
		this.oYear = oYear;
	}
	
	public java.sql.Timestamp getoWhen() {
		return oWhen;
	}
	public void setoWhen(java.sql.Timestamp oWhen) {
		this.oWhen = oWhen;
	}
	public int getoQnty() {
		return oQnty;
	}
	public void setoQnty(int oQnty) {
		this.oQnty = oQnty;
	}
	@Override
	public String toString() {
		return "Purchase [Cid=" + oCid + ", Club=" + oClub + ", Title=" + oTitle + ", Year=" + oYear + ", When="
				+ oWhen + ", Qnty=" + oQnty + "]";
	}
	
	
	
	
}
