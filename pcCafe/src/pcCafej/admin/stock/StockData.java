package pcCafej.admin.stock;

public class StockData {	
	private int adminNum;
	private String stockDate;
	private int stockNum;
	private int pNum;
	public int getAdminNum() {
		return adminNum;
	}
	public void setAdminNum(int adminNum) {
		this.adminNum = adminNum;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	public int getStockNum() {
		return stockNum;
	}
	public void setStockNum(int stockNum) {
		this.stockNum = stockNum;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	@Override
	public String toString() {
		return "StockData [adminNum=" + adminNum + ", stockDate=" + stockDate + ", stockNum=" + stockNum + ", pNum="
				+ pNum + "]";
	}
	public StockData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StockData(int adminNum, String stockDate, int stockNum, int pNum) {
		super();
		this.adminNum = adminNum;
		this.stockDate = stockDate;
		this.stockNum = stockNum;
		this.pNum = pNum;
	}
	
}
