import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.simple.JSONObject;

import static org.eclipse.paho.client.mqttv3.MqttClient.generateClientId;

public class Publisher {
    MqttClient client;
    MqttConnectOptions connOpts = setUpConnectionOptions("bhit5", "niceTime");

    public Publisher() throws MqttException {
        client = new MqttClient("tcp://docker.htl-wels.at", generateClientId());
    }

    public void startConn() throws MqttException {
        client.connect(connOpts);
    }

    public void send(JSONObject jsonObject, String topic) throws MqttException {
        MqttMessage message = new MqttMessage(jsonObject.toJSONString().getBytes());
        client.publish(topic, message);
        System.out.println("published");
    }

    public void endConn() throws MqttException {
        client.disconnect();
    }

    private MqttConnectOptions setUpConnectionOptions(String username, String password) {
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(username);
        connOpts.setPassword(password.toCharArray());
        return connOpts;
    }
}
