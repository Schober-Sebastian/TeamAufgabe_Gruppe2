import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) {
        for(int i = 0; i<1000; i++) {
            Aenderungen ae = new Aenderungen();
            ae.aendern();
        }
    }
}
