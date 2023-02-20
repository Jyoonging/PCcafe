package adminPrj.admin.time;

public class TimeData {
	
	private int feeNum;
	private int timeAddMin;
	private int timeMin;
	private int timePrice;

	private int tNum;
	private int tPrice;
	private int tTime;
	public int gettNum() {
		return tNum;
	}
	public void settNum(int tNum) {
		this.tNum = tNum;
	}
	public int gettPrice() {
		return tPrice;
	}
	public void settPrice(int tPrice) {
		this.tPrice = tPrice;
	}
	public int gettTime() {
		return tTime;
	}
	public void settTime(int tTime) {
		this.tTime = tTime;
	}
	public TimeData(int tNum, int tPrice, int tTime) {
		super();
		this.tNum = tNum;
		this.tPrice = tPrice;
		this.tTime = tTime;
	}
	public TimeData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TimeData [tNum=" + tNum + ", tPrice=" + tPrice + ", tTime=" + tTime + "]";
	}


}
