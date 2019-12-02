package issues.Writer;

import issues.Portfolio;
import issues.Position;

public class WriterTest {
    public static void main(String args[]){
        portfolioWriter pfWriter = new portfolioWriter();


        Portfolio positions = new Portfolio();
        Position posTest8 = new Position();
        posTest8 = posTest8.entryPosition("1021", "99", "155.0");
        positions.addPosition(posTest8);

        Position posTest9 = new Position();
        posTest9 = posTest9.entryPosition("3012", "300", "2552.0");
        positions.addPosition(posTest9);

        System.out.println("getPortfolioPositionsの実行");
        pfWriter.getPortfolioPositions(positions);

        System.out.println("portfolioWriterの実行");
        pfWriter.portfolioWriter(positions);

    }
}
