import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONHandler {
    public Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }
}