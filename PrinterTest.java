package issues.printer;

import issues.Portfolio;
import issues.Position;

import java.io.IOException;

public class PrinterTest{
    public static void main(String[] args) throws IOException {
        MasterfilePrinter masterfile = new MasterfilePrinter();
        PortfolioPrinter portfolio = new PortfolioPrinter();
        Portfolio positions = new Portfolio();

        //テスト用銘柄の作成エリア
        Position posTest1 = new Position();
        posTest1 = posTest1.entryPosition("1021", "99", "155.0");
        positions.addPosition(posTest1);

        Position posTest2 = new Position();
        posTest2 = posTest2.entryPosition("3012", "123", "2345.0");
        positions.addPosition(posTest2);

        Position posTest3 = new Position();
        posTest3 = posTest3.entryPosition("2021", "9", "10000.0");
        positions.addPosition(posTest3);

        Position posTest4 = new Position();
        posTest4 = posTest4.entryPosition("1061", "100000", "15511231231.00");
        positions.addPosition(posTest4);

        Position posTest5 = new Position();
        posTest5 = posTest5.entryPosition("3042", "1312312", "312312321321312.0");
        positions.addPosition(posTest5);



        //masterfile.printMasterFile();
        //portfolio.printPortfolioFile();
        portfolio.printCurrentPortfolio(positions);
    }
}
