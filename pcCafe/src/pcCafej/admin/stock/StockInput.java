package pcCafej.admin.stock;

import pcCafe.admin.product.ProductData;
import pcCafe.main.Main;

public class StockInput {

	public ProductData volume() {
		ProductData pd = new ProductData();
		System.out.println("==================================");
		System.out.print  (" 추가할 상품 이름 :  ");
		String name = Main.SC.nextLine().trim();
		System.out.println("==================================");
		System.out.print  (" 상품 수량 :        ");
		int plusNum = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		pd.setpName(name);
		pd.setpQty(pd.getpQty()+plusNum);
		return pd;
	}
	
	public ProductData price() {
		ProductData pd = new ProductData();
		System.out.println("==================================");
		System.out.print  (" 가격 변경할 상품 이름 :           ");
		String name = Main.SC.nextLine().trim();
		System.out.println("==================================");
		System.out.print  (" 변경 가격 :                    ");
		int plusPrice = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		pd.setpName(name);
		pd.setpPrice(plusPrice);
		return pd;
	}
	
	public ProductData add() {
		ProductData pd = new ProductData();
		System.out.println("==================================");
		System.out.print("  추가할 상품 이름 : ");
		String setName = Main.SC.nextLine().trim();
		System.out.println("==================================");
		System.out.print("  상품 가격 : ");
		int firstPrice = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		System.out.print("  초기 상품 수량 : ");
		int firstQty = Integer.parseInt(Main.SC.nextLine().trim());
		System.out.println("==================================");
		pd.setpName(setName);
		pd.setpPrice(firstPrice);
		pd.setpQty(firstQty);
		return pd;
	}
	
}
