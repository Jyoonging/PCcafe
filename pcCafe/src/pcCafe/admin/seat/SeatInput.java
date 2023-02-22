package pcCafe.admin.seat;

import pcCafe.admin.AdminMain;
import pcCafe.main.Main;

public class SeatInput {

	public SeatData addSeat() {
		
		System.out.println("==================================");
		System.out.println("||       신규 좌석을 추가합니다      ||");
		System.out.println("==================================");
		System.out.println("==================================");
		System.out.print("  모니터 사이즈 : ");
		SeatData data = new SeatData();
		try {
			int mSize = Integer.parseInt(Main.SC.nextLine().trim());
			System.out.println("==================================");
			System.out.print("  좌석타입 (흡연/금연) : ");
			String seat_Type = Main.SC.nextLine().trim();
			System.out.println("==================================");
			System.out.println(mSize);
			System.out.println(seat_Type);
			data.setMonitorType(mSize);
			data.setSeatType(seat_Type);
			return data;
			
		}catch(Exception e){
			AdminMain.Exception();
			return data;
			
		}
	}
	
	public SeatData editSeat() {
		System.out.println("==================================");
		System.out.println("||      좌석의 상태를 변경합니다      ||");
		System.out.println("==================================");;
		System.out.println("==================================");
		System.out.print  ("  상태 변경할 좌석 번호 : ");
		SeatData data = new SeatData();
		try{
			int no = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		System.out.print("  고장상태(Y/N) : ");
		String b = Main.SC.nextLine().trim();
		String broken = b.toUpperCase();
		System.out.println("==================================");
		System.out.print("  모니터 사이즈 : ");
		int mType  = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		System.out.print("  흡연/금연 : ");
		String smoke = Main.SC.nextLine().trim();
		System.out.println("==================================");
		System.out.print("  사용중 여부(Y/N) : ");
		String use = Main.SC.nextLine().trim();
		String u = use.toUpperCase();
		System.out.println("==================================");
		data.setBrokenYN(broken);
		data.setMonitorType(mType);
		data.setSeatType(smoke);
		data.setUsageYN(u);
		data.setSeatNum(no);
		return data;
		}catch(Exception e) {
			System.out.println("==================================");
			System.out.println("||          숫자만 입력하세요       ||");
			System.out.println("==================================");
		}
		return  data;
	}

}
