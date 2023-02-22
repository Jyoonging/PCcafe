package pcCafe.admin.seat;

public class SeatData {
	private int seatNum;
	private String brokenYN;
	private int monitorType;
	private String seatType;
	private String usageYN;
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public String getBrokenYN() {
		return brokenYN;
	}
	public void setBrokenYN(String brokenYN) {
		this.brokenYN = brokenYN;
	}
	public int getMonitorType() {
		return monitorType;
	}
	public void setMonitorType(int monitorType) {
		this.monitorType = monitorType;
	}
	public String getSeatType() {
		return seatType;
	}
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}
	public String getUsageYN() {
		return usageYN;
	}
	public void setUsageYN(String usageYN) {
		this.usageYN = usageYN;
	}
	public SeatData(int seatNum, String brokenYN, int monitorType, String seatType, String usageYN) {
		super();
		this.seatNum = seatNum;
		this.brokenYN = brokenYN;
		this.monitorType = monitorType;
		this.seatType = seatType;
		this.usageYN = usageYN;
	}
	public SeatData() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "SeatData [seatNum=" + seatNum + ", brokenYN=" + brokenYN + ", monitorType=" + monitorType
				+ ", seatType=" + seatType + ", usageYN=" + usageYN + "]";
	}
	
}
