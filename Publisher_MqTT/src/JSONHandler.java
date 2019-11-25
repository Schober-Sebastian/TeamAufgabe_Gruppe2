import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {
    public Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);
    }

    public void writeJsonSimpleDemo(String filename, JSONObject object) throws Exception {
        Files.write(Paths.get(filename) ,object.toJSONString().getBytes());
    }
}
