
public class Book {
	private String oTitle;
	private int oYear;
	private String oLanguage;
	private String oCat;
	private int oWeight;
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
	public String getoLanguage() {
		return oLanguage;
	}
	public void setoLanguage(String oLanguage) {
		this.oLanguage = oLanguage;
	}
	public String getoCat() {
		return oCat;
	}
	public void setoCat(String oCat) {
		this.oCat = oCat;
	}
	public int getoWeight() {
		return oWeight;
	}
	public void setoWeight(int oWeight) {
		this.oWeight = oWeight;
	}
	@Override
	public String toString() {
		return "Book [Title=" + oTitle + ", Year=" + oYear + ", Language=" + oLanguage + ", Cat=" + oCat
				+ ", Weight=" + oWeight + "]";
	}
	
	
	
	
}
