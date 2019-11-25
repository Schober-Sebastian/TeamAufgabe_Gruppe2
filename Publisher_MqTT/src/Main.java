import org.json.simple.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            Publisher publisher = new Publisher();
            publisher.startConn();
            Sender sender1 = new Sender(1000, publisher, new JSONObject(), "topic1");
            Sender sender2 = new Sender(1000, publisher, new JSONObject(), "topic2");
            Sender sender3 = new Sender(1000, publisher, new JSONObject(), "topic3");
            Aenderungen aenderungen1 = new Aenderungen(sender1, "data.json");
            Aenderungen aenderungen2 = new Aenderungen(sender2, "data1.json");
            Aenderungen aenderungen3 = new Aenderungen(sender3, "data2.json");
            sender1.setAenderungen(aenderungen1);
            sender2.setAenderungen(aenderungen2);
            sender3.setAenderungen(aenderungen3);
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(sender1);
            service.execute(sender2);
            service.execute(sender3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
