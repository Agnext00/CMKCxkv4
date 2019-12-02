package issues.Loader;


import java.util.NoSuchElementException;
import java.util.Scanner;

public class keyBoadLoader {
    public keyBoadLoader(){
    }
    //整数のキーボード入力を読む
    public int readIntegerNum() {
        int readNum = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("整数値を入力してください");

        try {
            String readStringNum = scanner.nextLine();
            readNum = Integer.parseInt(readStringNum);
            scanner.close();
        }catch (NumberFormatException e){

        }catch (NoSuchElementException e){

        }


        return readNum;
    }

}
