package issues;

import issues.Loader.keyBoadLoader;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String readStringNum = "Non";

        System.out.println("--START--");
        System.out.println("1.在庫データの入力");
        System.out.println("2.値洗い");
        System.out.println("3.損益の算出");
        System.out.println("4.取引の取り消し");
        System.out.println("5.締め処理");
        System.out.println("6.終了");

        System.out.print("整数値を入力してください -> ");

        while(scanner.hasNext()){
            readStringNum = scanner.next();
            switch (readStringNum){
                case "1":
                    System.out.println("在庫データの入力");
                    break;
                case "2":
                    System.out.println("値洗い");
                    break;
                case "3":
                    System.out.println("損益の算出");
                    break;
                case "4":
                    System.out.println("取引の取り消し");
                    break;
                case "5":
                    System.out.println("締め処理");
                    break;
                case "6":
                    System.out.println("終了");
                    break;

                default:
                    System.out.println("ERROR");
            }
            if(!(readStringNum.equals("6"))) {
                System.out.print("整数値を入力してください -> ");
            }else break;
        }
        scanner.close();
        System.out.println("--END--");

    }
}
