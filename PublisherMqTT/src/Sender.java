import org.eclipse.paho.client.mqttv3.MqttException;
import org.json.simple.JSONObject;

import java.util.concurrent.Semaphore;

public class Sender implements Runnable {
    private int interval;
    private Publisher publisher;
    private JSONObject jsonObject;
    private String topic;
    private String filename;
    private JSONHandler jsonHandler = new JSONHandler();

    public Sender(int interval, Publisher publisher, JSONObject jsonObject, String topic, String filename) {
        this.interval = interval;
        this.publisher = publisher;
        this.jsonObject = jsonObject;
        this.topic = topic;
        this.filename = filename;
    }

    @Override
    public void run() {
        while (true) {
            try {
                jsonObject = (JSONObject) jsonHandler.readJsonSimpleDemo(filename);
                double oiltempAlt = (double) jsonObject.get("oil-temperature");
                double oiltemp;
                do {
                    oiltemp = Math.round((oiltempAlt - 0.5 + Math.random()) * 100.0) / 100.0;
                } while (oiltemp > 90 || oiltemp < 70);
                jsonObject.replace("oil-temperature", oiltemp);
                double oilpressAlt = (double) jsonObject.get("oil-pressure");
                double oilpress;
                do {
                    oilpress = Math.round((oilpressAlt - 0.1 + (Math.random() * 0.5)) * 100.0) / 100.0;
                } while (oilpress > 3 || oilpress < 1);
                jsonObject.replace("oil-pressure", oilpress);
                double viscAlt = (double) jsonObject.get("viscosity");
                double visc;
                do {
                    visc = Math.round((viscAlt - 0.5 + Math.random()) * 100.0) / 100.0;
                } while (visc > 10 || visc < 8);
                jsonObject.replace("viscosity", visc);
                double tempAlt = (double) jsonObject.get("temperature");
                double temp;
                do {
                    temp = Math.round((tempAlt - 0.5 + Math.random()) * 100.0) / 100.0;
                } while (temp > 30 || temp < -10);
                jsonObject.replace("temperature", temp);
                int err = (int) (Math.random() * 100);
                double save;
                int errType;
                if (err == 1) {
                    errType = (int) (Math.random() * 3);
                    if (errType == 0) {
                        save = (double) jsonObject.get("oil-temperature");
                        jsonObject.replace("oil-temperature", 0.0);
                        publisher.send(jsonObject, topic);
                        jsonObject.replace("oil-temperature", save);
                    } else if (errType == 1) {
                        save = (double) jsonObject.get("oil-pressure");
                        jsonObject.replace("oil-pressure", 0.0);
                        publisher.send(jsonObject, topic);
                        jsonObject.replace("oil-pressure", save);
                    } else if (errType == 2) {
                        save = (double) jsonObject.get("viscosity");
                        jsonObject.replace("viscosity", 0.0);
                        publisher.send(jsonObject, topic);
                        jsonObject.replace("viscosity", save);
                    } else if (errType == 3) {
                        save = (double) jsonObject.get("temperature");
                        jsonObject.replace("temperature", 0.0);
                        publisher.send(jsonObject, topic);
                        jsonObject.replace("temperature", save);
                    }
                } else {
                    publisher.send(jsonObject, topic);
                }
                jsonHandler.writeJsonSimpleDemo(filename, jsonObject);
                Thread.sleep(interval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
