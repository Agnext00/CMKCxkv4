package issues.printer;

import issues.Portfolio;
import issues.Position;

import javax.sound.sampled.Port;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PortfolioPrinter extends MasterfilePrinter {
    PortfolioPrinter(){
    }
    public int[] countMaxByte(String HoldPosFileName) throws IOException {
        int byteCode = 0, byteName = 0, byteBS = 0, byteRate = 0, byteMaturity = 0, byteCoupon = 0;
        int byteAmount = 0, byteBookValue = 0, byteMarketValue = 0, byteProfit = 0;

        //マスターファイルの名称
        Path pathMasterFilename = Paths.get(HoldPosFileName);
        BufferedReader readMasterFile
                = Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);
        int[] maxByteNum = new int[10];
        maxByteNum[0]=4;
        maxByteNum[1]=4;
        maxByteNum[2]=3;
        maxByteNum[3]=4;
        maxByteNum[4]=8;
        maxByteNum[5]=6;
        maxByteNum[6]=6;
        maxByteNum[7]=9;
        maxByteNum[8]=11;
        maxByteNum[9]=6;

        //各要素の最大バイト数を取得
        for (String line; (line = readMasterFile.readLine()) != null; ) {
            String linestr[] = line.split(",");

            byteCode = linestr[0].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteCode > maxByteNum[0]) {
                maxByteNum[0] = byteCode;
            }

            byteName = linestr[1].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteName > maxByteNum[1]) {
                maxByteNum[1] = byteName;
            }

            byteBS = linestr[2].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteBS > maxByteNum[2]) {
                maxByteNum[2] = byteBS;
            }

            byteRate = linestr[3].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteRate > maxByteNum[3]) {
                maxByteNum[3] = byteRate;
            }

            byteMaturity = linestr[4].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteMaturity > maxByteNum[4]) {
                maxByteNum[4] = byteMaturity;
            }

            byteCoupon = linestr[5].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteCoupon > maxByteNum[5]) {
                maxByteNum[5] = byteCoupon;
            }

            byteAmount = linestr[6].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteAmount > maxByteNum[6]) {
                maxByteNum[6] = byteAmount;
            }

            byteBookValue = linestr[7].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteBookValue > maxByteNum[7]) {
                maxByteNum[7] = byteBookValue;
            }

            byteMarketValue = linestr[8].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteMarketValue > maxByteNum[8]) {
                maxByteNum[8] = byteMarketValue;
            }

            byteProfit = linestr[9].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteProfit > maxByteNum[9]) {
                maxByteNum[9] = byteProfit;
            }
        }
        readMasterFile.close();

        return maxByteNum;

    }

    public int[] countMaxByte(Portfolio portfolio){
        String marketValue, profit;

        int byteCode = 0, byteName = 0, byteBS = 0, byteRate = 0, byteMaturity = 0, byteCoupon = 0;
        int byteAmount = 0, byteBookValue = 0, byteMarketValue = 0, byteProfit = 0;

        //ポートフォリオのリスト化
        List<Position> positionList = portfolio.getPositionList();

        int[] maxByteNum = new int[10];
        maxByteNum[0]=4;
        maxByteNum[1]=4;
        maxByteNum[2]=3;
        maxByteNum[3]=4;
        maxByteNum[4]=8;
        maxByteNum[5]=6;
        maxByteNum[6]=6;
        maxByteNum[7]=9;
        maxByteNum[8]=11;
        maxByteNum[9]=6;

        //各要素の最大バイト数を取得
        int i=0;

        for (i=0; i<positionList.size(); i++) {

            byteCode = positionList.get(i).getBond().getCode().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteCode > maxByteNum[0]) {
                maxByteNum[0] = byteCode;
            }

            byteName = positionList.get(i).getBond().getName().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteName > maxByteNum[1]) {
                maxByteNum[1] = byteName;
            }

            byteBS = positionList.get(i).getBS().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteBS > maxByteNum[2]) {
                maxByteNum[2] = byteBS;
            }

            byteRate = positionList.get(i).getBond().getInterestRate().toString().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteRate > maxByteNum[3]) {
                maxByteNum[3] = byteRate;
            }

            byteMaturity = String.valueOf(positionList.get(i).getBond().getMaturity())
                            .getBytes(Charset.forName("Shift_JIS")).length;
            if (byteMaturity > maxByteNum[4]) {
                maxByteNum[4] = byteMaturity;
            }

            byteCoupon = positionList.get(i).getBond().getCoupon().toString().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteCoupon > maxByteNum[5]) {
                maxByteNum[5] = byteCoupon;
            }

            byteAmount = positionList.get(i).getAmount().toString().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteAmount > maxByteNum[6]) {
                maxByteNum[6] = byteAmount;
            }

            byteBookValue = positionList.get(i).getBookValue().toString().getBytes(Charset.forName("Shift_JIS")).length;
            if (byteBookValue > maxByteNum[7]) {
                maxByteNum[7] = byteBookValue;
            }


            if(positionList.get(i).getMarketValue()!=null){
                marketValue = positionList.get(i).getMarketValue().toString();
                byteMarketValue = marketValue.getBytes(Charset.forName("Shift_JIS")).length;
                if (byteMarketValue > maxByteNum[8]) {
                    maxByteNum[8] = byteMarketValue;
                }
            }

            if(positionList.get(i).getProfit()!=null) {
                profit = positionList.get(i).getProfit().toString();
                byteProfit = profit.getBytes(Charset.forName("Shift_JIS")).length;
                if (byteProfit > maxByteNum[9]) {
                    maxByteNum[9] = byteProfit;
                }
            }
        }
        return maxByteNum;

    }

    public void printHeader(int[] maxByteNum) {
        int byteLength = 30;
        int i=0;
        for(i=0; i<maxByteNum.length; i++){
            byteLength += maxByteNum[i];
        }

        for(i=1; i<=byteLength; i++){
            if(i==byteLength){
                System.out.println("-");
            }else System.out.print("-");
        }

        printBlank(4, maxByteNum[0]);
        System.out.print("CODE" + " | ");
        printBlank(4, maxByteNum[1]);
        System.out.print("NAME" + " | ");
        printBlank(3, maxByteNum[2]);
        System.out.print("B/S" + " | ");
        printBlank(4, maxByteNum[3]);
        System.out.print("RATE" + " | ");
        printBlank(8, maxByteNum[4]);
        System.out.print("MATURITY" + " | ");
        printBlank(6, maxByteNum[5]);
        System.out.print("COUPON" + " | ");

        //ここから追記
        printBlank(6, maxByteNum[6]);
        System.out.print("AMOUNT" + " | ");
        printBlank(9, maxByteNum[7]);
        System.out.print("BOOKVALUE" + " | ");
        printBlank(11, maxByteNum[8]);
        System.out.print("MARKETVALUE" + " | ");
        printBlank(6, maxByteNum[9]);
        System.out.println("PROFIT" + " | ");



        for(i=1; i<=byteLength; i++){
            if(i==byteLength){
                System.out.println("-");
            }else System.out.print("-");
        }

    }
    public void printFooter(int[] maxByteNum) {
        int byteLength = 30;
        int i=0;
        for(i=0; i<maxByteNum.length; i++){
            byteLength += maxByteNum[i];
        }

        for(i=1; i<=byteLength; i++){
            if(i==byteLength){
                System.out.println("-");
            }else System.out.print("-");
        }

    }


    public void printPortfolioFile() throws IOException {
        int byteCode=0, byteName=0, byteBS=0, byteRate=0, byteMaturity=0, byteCoupon=0;
        int byteAmount=0, byteBookValue=0, byteMarketValue=0, byteProfit=0;
        int[] maxByteNum =  countMaxByte("HoldPositions.txt");
        /*
        System.out.println("maxByteCode : "     + maxByteNum[0]);
        System.out.println("maxByteName : "     + maxByteNum[1]);
        System.out.println("maxByteRate : "     + maxByteNum[2]);
        System.out.println("maxByteMaturity : " + maxByteNum[3]);
        System.out.println("maxByteCoupon : "   + maxByteNum[4]);
        System.out.println("maxByteAmount : "   + maxByteNum[5]);
        System.out.println("maxByteBookValue : "   + maxByteNum[6]);
        System.out.println("maxByteMarketValue : " + maxByteNum[7]);
        System.out.println("maxByteProfit : "   + maxByteNum[8]);
        */
        //保有銘柄マスターファイルの名称
        Path pathMasterFilename = Paths.get("HoldPositions.txt");
        BufferedReader readMasterFile
                = Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);

        printHeader(maxByteNum);
        try{
            for (String line; (line = readMasterFile.readLine()) != null; ) {
                String linestr[] = line.split(",");

                byteCode = linestr[0].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteCode, maxByteNum[0]);
                System.out.print(linestr[0] + " | ");

                byteName = linestr[1].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteName, maxByteNum[1]);
                System.out.print(linestr[1] + " | ");

                byteBS = linestr[2].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteBS, maxByteNum[2]);
                System.out.print(linestr[2] + " | ");

                byteRate = linestr[3].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteRate, maxByteNum[3]);
                System.out.print(linestr[3] + " | ");

                byteMaturity = linestr[4].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteMaturity, maxByteNum[4]);
                System.out.print(linestr[4] + " | ");

                byteCoupon = linestr[5].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteCoupon, maxByteNum[5]);
                System.out.print(linestr[5] + " | ");

                //----------継承元から追記したコード----------
                byteAmount = linestr[6].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteAmount, maxByteNum[6]);
                System.out.print(linestr[6] + " | ");

                byteBookValue = linestr[7].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteBookValue, maxByteNum[7]);
                System.out.print(linestr[7] + " | ");

                byteMarketValue = linestr[8].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteMarketValue, maxByteNum[8]);
                System.out.print(linestr[8] + " | ");

                byteProfit = linestr[9].getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteProfit, maxByteNum[9]);
                System.out.println(linestr[9] + " | ");


            }
        }catch(ArrayIndexOutOfBoundsException e){
        }
        readMasterFile.close();
        printFooter(maxByteNum);
    }

    public void printCurrentPortfolio(Portfolio portfolio){
        String code, name, BS, rate, maturity, coupon;
        String amount, bookValue, marketValue, profit;

        int byteCode=0, byteName=0, byteBS=0, byteRate=0, byteMaturity=0, byteCoupon=0;
        int byteAmount=0, byteBookValue=0, byteMarketValue=0, byteProfit=0;
        int[] maxByteNum =  countMaxByte(portfolio);

        //ポートフォリオのリスト化
        List<Position> positionList = portfolio.getPositionList();

        printHeader(maxByteNum);
        try{
            int i=0;
            for (i=0; i<positionList.size(); i++) {

                code = positionList.get(i).getBond().getCode();
                byteCode = code.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteCode, maxByteNum[0]);
                System.out.print(code + " | ");

                name = positionList.get(i).getBond().getName();
                byteName = name.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteName, maxByteNum[1]);
                System.out.print(name + " | ");

                BS = positionList.get(i).getBS();
                byteBS = BS.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteBS, maxByteNum[2]);
                System.out.print(BS + " | ");

                rate = positionList.get(i).getBond().getInterestRate().toString();
                byteRate = rate.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteRate, maxByteNum[3]);
                System.out.print(rate + " | ");

                maturity = String.valueOf(positionList.get(i).getBond().getMaturity());
                byteMaturity = maturity.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteMaturity, maxByteNum[4]);
                System.out.print(maturity + " | ");

                coupon = positionList.get(i).getBond().getCoupon().toString();
                byteCoupon = coupon.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteCoupon, maxByteNum[5]);
                System.out.print(coupon + " | ");

                //----------継承元から追記したコード----------
                amount = positionList.get(i).getAmount().toString();
                byteAmount = amount.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteAmount, maxByteNum[6]);
                System.out.print(amount + " | ");

                bookValue = positionList.get(i).getBookValue().toString();
                byteBookValue = bookValue.getBytes(Charset.forName("Shift_JIS")).length;
                printBlank(byteBookValue, maxByteNum[7]);
                System.out.print(bookValue + " | ");

                if(positionList.get(i).getMarketValue()!=null) {
                    marketValue = positionList.get(i).getMarketValue().toString();
                    byteMarketValue = marketValue.getBytes(Charset.forName("Shift_JIS")).length;
                    printBlank(byteMarketValue, maxByteNum[8]);
                }else{
                    marketValue = "N/A";
                    byteMarketValue = marketValue.getBytes(Charset.forName("Shift_JIS")).length;
                    printBlank(byteMarketValue, maxByteNum[8]);
                }
                System.out.print(marketValue + " | ");


                if(positionList.get(i).getProfit()!=null) {
                    profit = positionList.get(i).getProfit().toString();
                    byteProfit = profit.getBytes(Charset.forName("Shift_JIS")).length;
                    printBlank(byteProfit, maxByteNum[9]);
                    System.out.println(profit + " | ");
                }else{
                    profit = "N/A";
                    byteProfit = profit.getBytes(Charset.forName("Shift_JIS")).length;
                    printBlank(byteProfit, maxByteNum[9]);
                }
                System.out.println(profit + " | ");

            }
        }catch(ArrayIndexOutOfBoundsException e){
        }
        printFooter(maxByteNum);
    }

}
