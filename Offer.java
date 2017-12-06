import java.text.DecimalFormat;

public class Offer {
	private String oClub;
	private String oTitle;
	private int oYear;
	private float oPrice;
	DecimalFormat df = new DecimalFormat("#.00");
	
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
	public float getoPrice() {
		return oPrice;
	}
	public void setoPrice(float oPrice) {
		this.oPrice = oPrice;
	}
	@Override
	public String toString() {
		return "Offer [Club=" + oClub + ", Title=" + oTitle + ", Year=" + oYear + ", Price=" + df.format(oPrice) + "]";
	}
	
}
