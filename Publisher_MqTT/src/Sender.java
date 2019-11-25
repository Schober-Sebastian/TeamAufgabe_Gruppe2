import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.simple.JSONObject;

public class Sender implements Runnable {
    private int interval;
    private Publisher publisher;
    private JSONObject jsonObject;
    private Aenderungen aenderungen;
    private String topic;

    public Sender(int interval, Publisher publisher, JSONObject jsonObject, String topic) {
        this.interval = interval;
        this.publisher = publisher;
        this.jsonObject = jsonObject;
        this.topic = topic;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public void setAenderungen(Aenderungen aenderungen) {
        this.aenderungen = aenderungen;
    }

    @Override
    public void run() {
        while (true) {
            try {
                aenderungen.aendern();
                publisher.send(jsonObject, topic);
                System.out.println(Thread.currentThread().getName() + " sent: " + jsonObject.toJSONString());
                Thread.sleep(interval);
            } catch (MqttException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
