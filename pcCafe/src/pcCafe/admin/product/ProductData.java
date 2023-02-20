package pcCafe.admin.product;

public class ProductData {
	private String pName;
	private int pQty;
	private int pPrice;
	private int pNum;
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpQty() {
		return pQty;
	}
	public void setpQty(int pQty) {
		this.pQty = pQty;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	@Override
	public String toString() {
		return "ProductData [pName=" + pName + ", pQty=" + pQty + ", pPrice=" + pPrice + ", pNum=" + pNum + "]";
	}
	public ProductData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductData(String pName, int pQty, int pPrice, int pNum) {
		super();
		this.pName = pName;
		this.pQty = pQty;
		this.pPrice = pPrice;
		this.pNum = pNum;
	}
	
	

}