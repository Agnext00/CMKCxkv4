package issues.Loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PortfolioLoader {
    PortfolioLoader() {
    }

    public List portfolioLoader() throws IOException {
        //保有銘柄ファイルの名称
        Path pathMasterFilename = Paths.get("HoldPositions.txt");
        BufferedReader readMasterFile
                = Files.newBufferedReader(pathMasterFilename, StandardCharsets.UTF_8);

        //Positionのデータを入れるリスト
        ArrayList<ArrayList<String>> positionDate = new ArrayList<ArrayList<String>>();
        ArrayList<String> sub = new ArrayList<String>();

        try {
            for (String line; (line = readMasterFile.readLine()) != null; ) {
                String linestr[] = line.split(",");
                for (int i = 0; i < 10; i++) {
                    sub.add(linestr[i]);
                }


            }
        }catch (ArrayIndexOutOfBoundsException e) {

        }
        return null;
    }
}
