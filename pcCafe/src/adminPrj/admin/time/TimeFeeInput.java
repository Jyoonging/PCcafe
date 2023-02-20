package adminPrj.admin.time;

import pcCafe.main.Main;

public class TimeFeeInput {
	
	public TimeData add() {
		TimeData td = new TimeData();
		System.out.print("추가 시간권 가격 : ");
		int price = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.print("추가 시간권 시간 : ");
		int time = Integer.parseInt(Main.SC.nextLine().trim());
		td.setTimePrice(price);
		td.setTimeAddMin(time);
		return td;
	}
	
	public TimeData edit() {
		TimeData td = new TimeData();
		System.out.print("변경하실 시간권 번호 : ");
		int num = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.print("시간 : ");
		int time = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.print("가격 : ");
		int price = Integer.parseInt(Main.SC.nextLine().trim());
		td.setFeeNum(num);
		td.setTimePrice(price);
		td.setTimeAddMin(time);
		return td;
	}
	
	public TimeData delete() {
		TimeData td = new TimeData();
		System.out.println("구현예정");
		return td;
	}
	

}
