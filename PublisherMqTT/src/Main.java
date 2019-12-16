import org.json.simple.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            Publisher publisher = new Publisher();
            publisher.startConn();
            Sender sender1 = new Sender(1000, publisher, new JSONObject(), "bhit5/wels", "data.json");
            Sender sender2 = new Sender(1000, publisher, new JSONObject(), "bhit5/fischlham", "data1.json");
            Sender sender3 = new Sender(1000, publisher, new JSONObject(), "bhit5/linz", "data2.json");
            ExecutorService service = Executors.newCachedThreadPool();
            service.execute(sender1);
            service.execute(sender2);
            service.execute(sender3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
