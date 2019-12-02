package issues;


import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Portfolio {
	int i = 0;
	List<Position> positionList;
	List<String> realizedProfitList;

	public Portfolio() {
		positionList = new ArrayList<Position>();
	}

	public List getPositionList(){
		return positionList;
	}

	//2 各値の出力メソッド

	public Position findPosition(Issue issue) {
		try{
			for (int i = 0; i < positionList.size(); i++) {
				if (positionList.get(i).getBond().getCode().equals(issue.getCode())) {
					System.out.println("Position is Found");
					return positionList.get(i);
				}
			}
			return null;
		}catch (NullPointerException e) {
			System.out.println("Position が見つかりませんでした");
			return null;

		}

	}
	public Position findPosition(String  code) {
		try{
			for (int i = 0; i < positionList.size(); i++) {
				if (positionList.get(i).getBond().getCode().equals(code)) {
					System.out.println("Position is Found");
					return positionList.get(i);
				}
			}
			return null;
		}catch (NullPointerException e) {
			System.out.println("Position が見つかりませんでした");
			System.out.println(" -> Portfolio への追加が失敗しました");
			return null;
		}

	}

	public void addPosition(Position p) {
		Position foundPosition = findPosition(p.getBond());
		try{
			if(p.getBond().getCode()!="defaultCode" && p.getBond().getName()!="defaultName"){
				if (foundPosition == null) {
					System.out.print(p.getBond().getName());
					//System.out.println("position is not Found");
					//if position is not found, save this position as new position
					positionList.add(p);
					System.out.println("\t-> save position as new position");
					//System.out.println(positionList.size());
					//System.out.println("Amount of this position: " + foundPosition.getAmount());
				} else {
					//if position is found, save this position as new position
					//calc MovAve bookValue
					BigDecimal renewBookValue
							= (p.getBookValue().multiply(p.getAmount())
							.add(foundPosition.getBookValue().multiply(foundPosition.getAmount())))
							//-----------------------------------------------------------------------------------------------
							.divide(p.getAmount().add(foundPosition.getAmount()),7, RoundingMode.HALF_UP);

					System.out.println("renewBookValue  -> " + renewBookValue);
					System.out.println(" renewBookValue の計算完了");
					foundPosition.setAmount(p.getAmount().add(foundPosition.getAmount()));
					System.out.println(" Amountの更新 完了");
					foundPosition.setBookValue(renewBookValue);
					System.out.println(" BookValue 完了");
					System.out.println("*** save as exisiting position ***");
					System.out.println("　Amount of this position: " + foundPosition.getAmount());
					System.out.println("　bookValue of this position: " + foundPosition.getBookValue());
				}
			}

		}catch (NullPointerException e) {
			System.out.println(" -> Portfolio への追加が失敗しました");
		}


	}


	//全銘柄の値洗い
	public void resetMarketValueForAllPositions() {
		//List<Position> listPortfolio = new ArrayList<>(positionMap.values());
		Scanner scan = new Scanner(System.in);
		for (int i = 0; i < positionList.size(); i++) {
			System.out.println("Type recent market value :" + positionList.get(i).getBond().getCode()
					+ "," + positionList.get(i).getBond().getName());
			System.out.println("Current marketValue = " + positionList.get(i).getMarketValue());

			//Redefine recent market value
			String strMarketValue = scan.nextLine();
			positionList.get(i).setMarketValue(strMarketValue);
		}
		scan.close();

	}

	//評価損益計算
	public BigDecimal calcUnrealizedProfit() {
		DecimalFormat decimalFormat = new DecimalFormat("+#,###.##;-#,###.##");
		BigDecimal portfolioProfit = new BigDecimal("0.0");
		for (i = 0; i < positionList.size(); i++) {
			BigDecimal profit
					=(positionList.get(i).getMarketValue().subtract(positionList.get(i).getBookValue()))
					.multiply(positionList.get(i).getAmount());

			portfolioProfit = portfolioProfit.add(profit);
		}


		//Calculate portfolio profit.
		decimalFormat.setMinimumFractionDigits(0);
		decimalFormat.setMaximumFractionDigits(0);
		decimalFormat.setMinimumIntegerDigits(0);
		System.out.println("評価損益 合計\t"+ decimalFormat.format(portfolioProfit)+ " 円");

		return null;
	}

	//実現損益計算
	public BigDecimal calcRealizedProfit(String code, String marketValue, String amount) {
		/*
		* 売買履歴のリストを作る
		* 各リスト[code name bookValue marketValue amount profit]要素の保存
		*
		* */

		DecimalFormat decimalFormat = new DecimalFormat("#,###.##;- #,###.##");
		BigDecimal portfolioProfit = new BigDecimal("0.0");

		return null;
	}

	//Positionの売却
	public void removePosition(String code, String marketVal, String amo) {
		Position foundPosition = findPosition(code);


		try{
			if(code!="defaultCode"){
				if (foundPosition == null) {
					//If position is not found
					System.out.println("Position is not found");
				} else {
					//if position is found, save this position as new position
					//calc MovAve bookValue

					//売る数量とその時価
					BigDecimal marketValue = new BigDecimal(marketVal);
					BigDecimal amount = new BigDecimal(amo);
					System.out.println(marketValue + "円で" + amount + "個　売却を行います");

					//平均簿価の再計算
					BigDecimal renewBookValue
							= (foundPosition.getBookValue().multiply(foundPosition.getAmount())
							.add(marketValue.multiply(amount)))
							//-----------------------------------------------------------------------------------------------
							.divide(foundPosition.getAmount().add(amount),7, RoundingMode.HALF_UP);

					//System.out.println(" renewBookValue の計算完了");
					foundPosition.setAmount(foundPosition.getAmount().subtract(amount));
					//System.out.println(" Amountの更新 完了");
					foundPosition.setBookValue(renewBookValue);
					//System.out.println(" BookValue 完了");
					//System.out.println("*** save as exisiting position ***");
					System.out.println("・Amount of this position: " + foundPosition.getAmount());
					System.out.println("・bookValue of this position: " + foundPosition.getBookValue() + "円");
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Position が見つかりませんでした");
			System.out.println(" -> Portfolio への追加が失敗しました");
		}
	}


}
