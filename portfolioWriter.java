package issues.Writer;

import issues.Portfolio;
import issues.Position;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class portfolioWriter {
    int i=0;

    public portfolioWriter(){
    }

    //引数？　Portfolio portfolio
    public void portfolioWriter(Portfolio portfolio){
        /*
        portfolio.getPositionList().getAmount();
        portfolio.getPositionList().get(i).getAmount();
         */
        List<Position> positionList = portfolio.getPositionList();

        for(i=0; i<positionList.size(); i++){
            //System.out.println(portfolio.getPositionList().get(i));
            System.out.println(positionList.get(i).getBookValue());
            System.out.println(positionList.get(i).getMarketValue());
            /*
            positionList.get(i).getAmount();
            positionList.get(i).getBookValue();
            positionList.get(i).getMarketValue();
            */

        }
    }

    public String getPortfolioPositions(Portfolio portfolio) {
        //List<Position> listPosition = new ArrayList<Position>(positionMap.values());
        List<Position> positionList = portfolio.getPositionList();

        DecimalFormat decimalFormat = new DecimalFormat("#,###.##;-#,###.##");
        System.out.print("Name");
        System.out.print("\t|\tCode");
        System.out.print("\t|\tName");
        System.out.print("\t|\tRate");
        System.out.print("\t|\tMaturity");
        System.out.print("\t|\tCoupon");
        System.out.print("\t|\tAmount");
        System.out.print("\t|\tbookValue");
        System.out.println("\t|\tmarketValue");
        System.out.println("-----------------------------------------------------------------");

        for (i = 0; i < positionList.size(); i++) {
            // 小数点以下の最小値
            // decimalFormat.setMinimumFractionDigits(0);
            // 小数点以下の最大値
            // decimalFormat.setMaximumFractionDigits(0);
            // 整数値の最小値
            // decimalFormat.setMinimumIntegerDigits(6);
            // 整数値の最大値
            // decimalFormat.setMaximumIntegerDigits(6);
            System.out.print(positionList.get(i).getBond().getName());
            System.out.print("\t\t" + positionList.get(i).getBond().getCode());
            System.out.print("\t\t" + positionList.get(i).getBS());
            System.out.print("\t\t" + positionList.get(i).getBond().getInterestRate());
            System.out.print("\t\t" + positionList.get(i).getBond().getMaturity());
            System.out.print("\t\t" + positionList.get(i).getBond().getCoupon());

            if(positionList.get(i).getAmount()==null) {
                System.out.print("\t\t" + positionList.get(i).getAmount());
            }else System.out.print("\t\t" + decimalFormat.format(positionList.get(i).getAmount()));

            if(positionList.get(i).getBookValue()==null) {
                System.out.print("\t\t" + positionList.get(i).getBookValue());
            }else System.out.print("\t\t" + decimalFormat.format(positionList.get(i).getBookValue()));


            if(positionList.get(i).getMarketValue()==null) {
                System.out.println("\t\t" + positionList.get(i).getMarketValue());
            }else System.out.println("\t\t" +  decimalFormat.format(positionList.get(i).getMarketValue()));
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println();
        return null;
    }
}
