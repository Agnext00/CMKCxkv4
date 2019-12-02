package issues;


import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Position{
	private Bond bond;
	private String BS;
	private BigDecimal amount;
	private BigDecimal bookValue;
	private BigDecimal marketValue;
	private BigDecimal profit;

	//----------コンストラクタ----------
	public Position(){
		super();
		BigDecimal amount = new BigDecimal("0");
		this.BS="B";
		this.amount = amount;
		this.bookValue = null;
		this.marketValue = null;
	}
	public Position(Bond bond, String strAmount, String strBookValue){

		BigDecimal amount = new BigDecimal(strAmount);
		BigDecimal bookValue = new BigDecimal(strBookValue);
		this.bond = bond;
		this.BS="B";
		this.amount = amount;
		this.bookValue = bookValue;
		this.marketValue = null;
		this.profit = null;
	}
	public Position(Bond bond, int intAmount,
					String strBookValue, String strMarketValue){

		BigDecimal amount = new BigDecimal(String.valueOf(intAmount));
		BigDecimal bookValue = new BigDecimal(strBookValue);
		BigDecimal marketValue = new BigDecimal(strMarketValue);
		this.bond = bond;
		this.amount = amount;
		this.bookValue = bookValue;
		this.marketValue = marketValue;
		this.profit = null;
	}
	public Position(Bond bond, double doubleAmount,
					String strBookValue, String strMarketValue){

		BigDecimal amount = new BigDecimal(String.valueOf(doubleAmount));
		BigDecimal bookValue = new BigDecimal(strBookValue);
		BigDecimal marketValue = new BigDecimal(strMarketValue);
		this.bond = bond;
		this.amount = amount;
		this.bookValue = bookValue;
		this.marketValue = marketValue;
		this.profit = null;

	}
	public Position(Bond bond, String strAmount,
					String strBookValue, String strMarketValue){

		BigDecimal amount = new BigDecimal(strAmount);
		BigDecimal bookValue = new BigDecimal(strBookValue);
		BigDecimal marketValue = new BigDecimal(strMarketValue);
		this.bond = bond;
		this.amount = amount;
		this.bookValue = bookValue;
		this.marketValue = marketValue;
		this.profit = null;

	}


	//----------getter----------
	public Bond getBond(){
		return bond;
	}
	public String getBS(){
		return BS;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public BigDecimal getBookValue(){
		return bookValue;
	}
	public BigDecimal getMarketValue(){
		return marketValue;
	}
	public BigDecimal getProfit(){
		return profit;
	}

	//----------setter----------
	public void setBond(Bond bond){
		this.bond = bond;
	}
	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public void setBookValue(String strBookValue){
		BigDecimal bookValue = new BigDecimal(strBookValue);
		this.bookValue = bookValue;
	}
	public void setBookValue(BigDecimal bdBookValue){
		this.bookValue = bdBookValue;
	}
	public void setMarketValue(String strMarketValue){
		BigDecimal marketValue = new BigDecimal(strMarketValue);
		this.marketValue = marketValue;
	}

	//新規ポジションをCodeから検索するメソッド
	public Position entryPosition(String code, String strAmount, String strBookValue){
		/*Bond testBond1 = new Bond("1234", "米国債", 20190920, 5.0);
		codeはローカルから、NameとMaturity,CouponはmasterFileから入手

		Position posTest1 = new Position(testBond1,100,pos1BookValue);
		作成済みインスタンスと、strAmount,strBookValueを用いてPositionインスタンスを生成
		*/

		int i=0;
		DecimalFormat decimalFormat = new DecimalFormat("#,###.##;-#,###.##");
		// 小数点以下の最小値
		decimalFormat.setMinimumFractionDigits(2);
		// 小数点以下の最大値
		decimalFormat.setMaximumFractionDigits(2);

		//マスターファイルの名称
		final String masterFileName = "masterFile.txt";
		Path pathMasterFilename = Paths.get(masterFileName);


		try{
			BufferedReader readIssueFromMasterFile
					= Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);

			for(String line; (line = readIssueFromMasterFile.readLine())!=null;){
				//System.out.println(line);
				String[] listSplitedissue = line.split(",", 0);

				if(listSplitedissue[0].equals(code)){

					System.out.print(listSplitedissue[0]);
					System.out.println(" -> 銘柄が存在します");
					//新規インスタンスの生成
					Bond newBond = new Bond(code, listSplitedissue[1], listSplitedissue[2],
							Integer.parseInt(listSplitedissue[3]), listSplitedissue[4]);
					Position newlyEntryPosition = new Position(newBond, strAmount, strBookValue);

					return newlyEntryPosition;
				}
			}
		}catch (IOException e) {
			System.out.println("ERROR : 入力値に誤りがあります");
			System.out.println(e);
			Position newlyEntryPosition = new Position();
			return newlyEntryPosition;
		}catch (NullPointerException e) {
			System.out.println("ERROR : 銘柄が見つかりませんでした");
			Position newlyEntryPosition = new Position();
			return newlyEntryPosition;
		}
		Position newlyEntryPosition = new Position();

		return newlyEntryPosition;
	}

}