package issues;

import java.math.BigDecimal;

public class PortfolioTest{
	public static void main(String[] args){
		System.out.println("(1) create PortfolioTest");


		System.out.println("(2) Create Bond instancese");
		Bond testBond1 = new Bond("1021", "US  ", 20190920, 5.0);
		Bond testBond2 = new Bond("1011", "JPN ", 20201220, 4.0);
		Bond testBond3 = new Bond("1041", "UK  ", 20240313, 1.0);
		System.out.println("---complete---");

		//Position
		System.out.println("(3) create Position");
		String pos1BookValue = "100.0";
		String pos1MarketValue = "120.0";
		String pos2BookValue = "1000.0";
		String pos2MarketValue = "1100.0";
		String pos3BookValue = "300.";
		String pos3MarketValue = "400.0";

		BigDecimal pos1BV = new BigDecimal(pos1BookValue);
		BigDecimal pos1MV = new BigDecimal(pos1MarketValue);

		Position posTest1 = new Position(testBond1,100,pos1BookValue,pos1MarketValue);
		Position posTest2 = new Position(testBond2,300,pos2BookValue,pos2MarketValue);
		Position posTest3 = new Position(testBond3,50.4,pos3BookValue,pos3MarketValue);
		System.out.println();


		//Add Position to Portfolio
		System.out.println("(4) Add Positions to Portfolio");
		Portfolio positions = new Portfolio();
		positions.addPosition(posTest1);
		positions.addPosition(posTest2);
		positions.addPosition(posTest3);
		System.out.println();

		//create newBond instance which code,name,maturity,coupon are same.
		//findPosition this instance
		System.out.println("(6) test of findPosition() for Bond");
		Bond testBond4 = new Bond("123456", "米国債", 20190920, 5.0);
		System.out.println(positions.findPosition(testBond4));
		if(positions.findPosition(testBond4)==null){
			System.out.println("TEST OK");
		}else System.out.println("TEST NG");
		positions.calcUnrealizedProfit();
		System.out.println();



		System.out.println("(8)check bookValue and marketValue");
		if(posTest1.getBookValue().equals(pos1BV)&&posTest1.getMarketValue().equals(pos1MV)){
			System.out.println("posTest1　BookValue & MarketValue -> test OK");
		}else {
			System.out.println("posTest1　BookValue & MarketValue -> test NG!!!!!!");
			System.out.println("posTest1.getBookValue().equals(pos1BookValue) = " + posTest1.getBookValue().equals(pos1BV));
			System.out.println("posTest1.getBookValue().equals(pos1MarketValue) = " + posTest1.getMarketValue().equals(pos1MV));
		}
		System.out.println();



		System.out.println("(9)Redefine marketValue using setter ");
		pos1MarketValue = "150.0";
		posTest1.setMarketValue(pos1MarketValue);

		if(posTest1.getBookValue().equals(pos1BV)&&posTest1.getMarketValue().equals(pos1MV)){
			System.out.println("posTest1　BookValue & MarketValue -> test NG!!!!");
		}else {
			System.out.println("posTest1　BookValue & MarketValue -> test OK");
			System.out.println("posTest1.getBookValue().equals(pos1BookValue) = " + posTest1.getBookValue().equals(pos1BV));
			System.out.println("posTest1.getBookValue().equals(pos1MarketValue) = " + posTest1.getMarketValue().equals(pos1MV));
			System.out.println("posTest1.getBookValue() = " + posTest1.getMarketValue());
		}
		System.out.println();

/*
		System.out.println("(10)Redefine marketValue for all positions using resetMarketValueForAllPositions ");
		System.out.println("* Current Portfolio");
		positions.getPortfolioPositions();
		System.out.println("\n* Input recent market value");
		positions.resetMarketValueForAllPositions();
		System.out.println("\n* Recent Portfolio");
		positions.getPortfolioPositions();
		System.out.println();


 */
		System.out.println("(11)Calc profit");
		positions.calcUnrealizedProfit();
		System.out.println();


		System.out.println("(12) searchPositionのテスト");
		System.out.println("① 銘柄が存在する場合の結果");
		Position posTest8 = new Position();
		posTest8 = posTest8.entryPosition("1021", "99", "155.0");
		positions.addPosition(posTest8);
		System.out.println(" * * * * Current Portfolio * * * *");
		//positions.getPortfolioPositions();

		System.out.println("② 銘柄が存在しない場合の結果");
		Position posTest9 = new Position();
		posTest8 = posTest9.entryPosition("1234", "5", "10000.0");
		positions.addPosition(posTest8);
		System.out.println(" * * * * Current Portfolio * * * *");
		//positions.getPortfolioPositions();

		System.out.println("(13) removePositionのテスト");
		positions.removePosition("1021", "154.0","99");
		positions.calcUnrealizedProfit();
		System.out.println();
	}
}
