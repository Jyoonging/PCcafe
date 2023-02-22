package pcCafe.product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import pcCafe.main.JdbcTemplate;
import pcCafe.main.Main;
import pcCafe.useStatus.ServiceManager;

public class PurchaseProduct {
	
	public static final Scanner SC = new Scanner(System.in);

	
	//상품 보여주기
	public void showProduct() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		String sql = "SELECT * FROM PRODUCT_LIST";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String p_num = rs.getString("P_NUM");
			String p_name = rs.getString("P_NAME");
			String p_qty = rs.getString("P_QTY");
			String p_price = rs.getString("P_PRICE");
			System.out.println(p_num + ". " + p_name + " / " + p_qty + "개 / " + p_price + "원");
		}
	}
	
	
	//주문 생성
	public void createOrder() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
	
		//
		//결제내역번호 생성
		String sql1 = "INSERT INTO PRODUCT_PAYMENT(PP_NUM, USE_NUM) VALUES(PP_NUM_SEQ.NEXTVAL,?)";
		
		PreparedStatement pstmt1 = conn.prepareStatement(sql1);
		pstmt1.setInt(1, ServiceManager.useNum);
		pstmt1.executeUpdate();
		conn.commit();

	}
	
	
	//상품번호 선택
	public int selectPnum() {
		while(true) {
			try {
				System.out.print("\n원하시는 상품의 번호를 입력해주세요 : ");
				String pnum = SC.nextLine();
				int selectPnum = Integer.parseInt(pnum);
				return selectPnum;
			}catch (Exception e) {
				System.out.println("\n정상적인 숫자를 입력해주세요.");
			}
		}
	}
	
	//상품수량 입력
	public int inputPqty() {
		while(true) {
			try {
				System.out.print("\n원하시는 수량을 입력해주세요 : ");
				String pqty = SC.nextLine();
				int selectPqty = Integer.parseInt(pqty);
				return selectPqty;
			}catch (Exception e) {
				System.out.println("\n정상적인 숫자를 입력해주세요.");
			}
		}
	}
	
	//장바구니(주문내역)
	public void addCart() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		
		//상품 및 수량 선택
		boolean selectProduct = true;
		int selectPnum = 0;
		int selectPqty = 0;
		while(selectProduct) {
			//상품번호 선택 (없는 번호 처리)
			selectPnum = selectPnum();
			if (selectPnum < 1 || selectPnum > exportPnum()) {
				System.out.println("\n없는 번호입니다.");
				continue;
			}
			
			//상품수량 입력 (재고부족 처리)
			selectPqty = inputPqty();
			if (selectPqty > exportPqty(selectPnum)) {
				System.out.println("\n재고가 부족합니다. 다시 입력해주세요.");
				continue;
			}
			selectProduct = false;
		}
		
		//결제내역번호 추출
		int ppNum = exportPpNum();
		
		//주문내역번호 생성, 장바구니담기
		String sql3 = "INSERT INTO ORDERED_PRODUCT(O_NUM, P_NUM, PP_NUM, O_QTY) VALUES(O_NUM_SEQ.NEXTVAL, ?,?,?)";
		PreparedStatement pstmt3 = conn.prepareStatement(sql3);
		pstmt3.setInt(1, selectPnum);
		pstmt3.setInt(2, ppNum);
		pstmt3.setInt(3, selectPqty);
		pstmt3.executeUpdate();
		conn.commit();
	
		
		System.out.println("\n=====장바구니=====");
		
		//담은 물건 표시
		String sql4 = "SELECT O.P_NUM, P_NAME, O_QTY, P_PRICE*O_QTY "
				+ "FROM ORDERED_PRODUCT O JOIN PRODUCT_LIST P "
				+ "ON O.P_NUM = P.P_NUM "
				+ "WHERE PP_NUM = ? "
				+ "ORDER BY O_NUM";
		PreparedStatement pstmt4 = conn.prepareStatement(sql4);
		pstmt4.setInt(1, exportPpNum());
		ResultSet rs2 = pstmt4.executeQuery();
		while(rs2.next()) {
			//String oNum = rs2.getString("O_NUM");
			String pNum = rs2.getString("P_NUM");
			String pName = rs2.getString("P_NAME");
			String oQty = rs2.getString("O_QTY");
			String subtotal = rs2.getString("P_PRICE*O_QTY");
			System.out.println(pNum + "." + pName + " / " + oQty + "개 / " + subtotal + "원");
		}
		
		
		//합계
		int total = showTotal();
		System.out.println("합계 : " + total + "원");
		
		//재고 수정
		//상품테이블에서 재고 뽑아오기
		int pQty = exportPqty(selectPnum);
		//재고 업뎃
		String sql7 = "UPDATE PRODUCT_LIST SET P_QTY = ? WHERE P_NUM = ?";
		PreparedStatement pstmt7 = conn.prepareStatement(sql7);
		pstmt7.setInt(1, pQty-selectPqty); //재고 - 입력한 수량
		pstmt7.setInt(2, selectPnum); //선택한 상품번호
		pstmt7.executeUpdate();
		conn.commit();
	}
	
	
	//추가 구매
	public void addPurchase() throws Exception {
		
		boolean selecting = true;
		while(selecting) {
			System.out.println("\n상품을 추가하시겠습니까? 1.예 2.아니요");
			String addOrPay = SC.nextLine();
			if(addOrPay.equals("1")) {
				//상품목록다시
				showProduct();
				addCart();
			}else if(addOrPay.equals("2")) {
				selecting = false;
			}else {
				System.out.println("\n잘못 입력하셨습니다.");
				continue;
			}
		}
	}
	
	//결제
	public void payProduct() throws Exception {
		boolean notPay = true;
		while(notPay) {
			System.out.println("\n=====결제=====");
			System.out.println("1.카드 2.현금");
			System.out.print("결제방법을 선택해주세요 :");
			String payMethod = SC.nextLine();
			if(payMethod.equals("1")) {
				System.out.println("\n결제 완료. 감사합니다.");
				break;
			}else if(payMethod.equals("2")) {
				int change = payCash();
				System.out.println("\n결제 완료. 감사합니다.");
				System.out.println("거스름돈 : "+ change +"원");
				break;
			}
			else {
				System.out.println("\n잘못 누르셨습니다.");
				continue;
			}
		}
	}
	
	//현금결제
	public int payCash() throws Exception {
		//현금받기
		int cash = 0;
		//총 금액
		int totalPrice = showTotal();
		while(true) {
			try {
				System.out.print("\n지불하실 금액을 입력해주세요 :");
				String payCash = Main.SC.nextLine();
				cash = Integer.parseInt(payCash);
				if(cash < totalPrice) {
					System.out.println("\n금액이 무족합니다. 다시 입력해주세요.");
					continue;
				}
				break;
			}catch (Exception e) {
				System.out.println("\n정상적인 숫자를 입력해주세요.");
			}
		}
		//거스름돈
		int change = cash - totalPrice;
		return change;
	}
	
	
	//결제 종료 - 가격. 결제시간 인서트
	public void finishPay() throws Exception {
		
		Connection conn = JdbcTemplate.getConnection();
		
		//합계 업데이트
		String sql8 = "UPDATE PRODUCT_PAYMENT SET TOTAL_PRICE = ? WHERE P_PAY_DATE IS NULL";
		PreparedStatement pstmt8 = conn.prepareStatement(sql8);
		pstmt8.setInt(1, showTotal());
		pstmt8.executeUpdate();
		conn.commit();
		
		//결제시간 업데이트
		String sql9 = "UPDATE PRODUCT_PAYMENT SET P_PAY_DATE = SYSDATE WHERE P_PAY_DATE IS NULL";
		PreparedStatement pstmt9 = conn.prepareStatement(sql9);
		pstmt9.executeUpdate();
		conn.commit();
		
		System.out.println("\n상품을 준비하는 중입니다......");
	}

	
	//결제내역번호 추출
	public int exportPpNum() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		String sql2 = "SELECT PP_NUM FROM PRODUCT_PAYMENT WHERE P_PAY_DATE IS NULL";
		PreparedStatement pstmt2 = conn.prepareStatement(sql2);
		ResultSet rs1 = pstmt2.executeQuery();
		rs1.next();
		int ppNum = rs1.getInt("PP_NUM");
		return ppNum;
	}
	
	//합계
	public int showTotal() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		String sql5 = "SELECT SUM(P_PRICE*O_QTY) 합계 "
				+ "FROM ORDERED_PRODUCT O JOIN PRODUCT_LIST P "
				+ "ON O.P_NUM = P.P_NUM "
				+ "WHERE PP_NUM = ?";
		PreparedStatement pstmt5 = conn.prepareStatement(sql5);
		pstmt5.setInt(1, exportPpNum());
		ResultSet rs3 = pstmt5.executeQuery();
		rs3.next();
		int total = rs3.getInt("합계");
		return total;
	}
	
	//상품테이블에서 재고 뽑기
	public int exportPqty(int selectPnum) throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		String sql6 = "SELECT P_QTY FROM PRODUCT_LIST WHERE P_NUM = ?";
		PreparedStatement pstmt6 = conn.prepareStatement(sql6);
		pstmt6.setInt(1, selectPnum);
		ResultSet rs4 = pstmt6.executeQuery();
		rs4.next();
		int pQty = rs4.getInt("P_QTY");
		return pQty;
	}
	
	//상품테이블에서 항목 개수 세기
	public int exportPnum() throws Exception {
		Connection conn = JdbcTemplate.getConnection();
		
		String sql10 = "SELECT COUNT(*) 품목개수 FROM PRODUCT_LIST";
		PreparedStatement pstmt10 = conn.prepareStatement(sql10);
		ResultSet rs5 = pstmt10.executeQuery();
		rs5.next();
		int pNum = rs5.getInt("품목개수");
		return pNum;
	}

}
	