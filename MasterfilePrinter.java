package issues.printer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MasterfilePrinter {
    public MasterfilePrinter() {
    }


    //マスターファイルの各要素のバイト数を返すメソッド
    public int[] countMaxByte(String masterFileName) throws IOException {
        int byteCode = 0, byteName = 0, byteRate = 0, byteMaturity = 0, byteCoupon = 0;

        //マスターファイルの名称
        Path pathMasterFilename = Paths.get(masterFileName);
        BufferedReader readMasterFile
                = Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);
        int[] maxByteNum = new int[10];
        maxByteNum[0]=4;
        maxByteNum[1]=4;
        maxByteNum[2]=4;
        maxByteNum[3]=3;
        maxByteNum[4]=8;


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

            byteRate = linestr[2].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteRate > maxByteNum[2]) {
                maxByteNum[2] = byteRate;
            }

            byteMaturity = linestr[3].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteMaturity > maxByteNum[3]) {
                maxByteNum[3] = byteMaturity;
            }

            byteCoupon = linestr[4].getBytes(Charset.forName("Shift_JIS")).length;
            if (byteCoupon > maxByteNum[4]) {
                maxByteNum[4] = byteCoupon;
            }
        }
        readMasterFile.close();

        return maxByteNum;

    }

    public void printBlank(int byteNum, int maxByteNum) {
        if (maxByteNum > byteNum) {
            int subByteName = maxByteNum - byteNum;
            for (int i = 0; subByteName > i; i++) {
                System.out.print(" ");
            }
        }
    }


    public void printHeader(int[] maxByteNum) {
        int byteLength = 15;
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
        printBlank(4, maxByteNum[2]);
        System.out.print("RATE" + " | ");
        printBlank(8, maxByteNum[3]);
        System.out.print("MATURITY" + " | ");
        printBlank(6, maxByteNum[4]);
        System.out.println("COUPON" + " | ");

        for(i=1; i<=byteLength; i++){
            if(i==byteLength){
                System.out.println("-");
            }else System.out.print("-");
        }

    }
    public void printFooter(int[] maxByteNum) {
        int byteLength = 15;
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



    public void printMasterFile() throws IOException {
        int byteCode=0, byteName=0, byteRate=0, byteMaturity=0, byteCoupon=0;
        int subByteCode=0, subByteName=0, subByteRate=0, subByteMaturity=0, subByteCoupon=0;
        int[] maxByteNum =  countMaxByte("masterFile.txt");
        /*
        System.out.println("maxByteCode : "     + maxByteNum[0]);
        System.out.println("maxByteName : "     + maxByteNum[1]);
        System.out.println("maxByteRate : "     + maxByteNum[2]);
        System.out.println("maxByteMaturity : " + maxByteNum[3]);
        System.out.println("maxByteCoupon : "   + maxByteNum[4]);
        */
        //マスターファイルの名称
        Path pathMasterFilename = Paths.get("masterFile.txt");
        BufferedReader readMasterFile
                = Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);

        printHeader(maxByteNum);
        for (String line; (line = readMasterFile.readLine()) != null; ) {
            String linestr[] = line.split(",");

            byteCode = linestr[0].getBytes(Charset.forName("Shift_JIS")).length;
            printBlank(byteCode, maxByteNum[0]);
            System.out.print(linestr[0] + " | ");

            byteName = linestr[1].getBytes(Charset.forName("Shift_JIS")).length;
            printBlank(byteName, maxByteNum[1]);
            System.out.print(linestr[1] + " | ");

            byteRate = linestr[2].getBytes(Charset.forName("Shift_JIS")).length;
            printBlank(byteRate, maxByteNum[2]);
            System.out.print(linestr[2] + " | ");

            byteMaturity = linestr[3].getBytes(Charset.forName("Shift_JIS")).length;
            printBlank(byteMaturity, maxByteNum[3]);
            System.out.print(linestr[3] + " | ");

            byteCoupon = linestr[4].getBytes(Charset.forName("Shift_JIS")).length;
            printBlank(byteCoupon, maxByteNum[4]);
            System.out.println(linestr[4] + " | ");


        }

        readMasterFile.close();
        printFooter(maxByteNum);

    }
}



