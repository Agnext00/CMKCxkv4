package issues;

import java.math.BigDecimal;

public class PortfolioTest2 {
	public static void main(String[] args){
		Position postest1 = new Position();
		Portfolio positions = new Portfolio();

		System.out.println("1   在庫データの入力/値洗い/損益");
		System.out.println("1.1 在庫データの入力/値洗い/損益");
		postest1 = postest1.entryPosition("1021", "50" , "200");
		positions.addPosition(postest1);
		System.out.println("1.2 値洗い");
		positions.resetMarketValueForAllPositions();
		System.out.println("1.3  損益表示");
		positions.calcUnrealizedProfit();

	}
}
