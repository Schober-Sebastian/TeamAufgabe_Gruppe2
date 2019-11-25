import org.json.simple.JSONObject;

public class Aenderungen {

    private Sender sender;
    private String filename;

    public Aenderungen(Sender sender, String filename) {
        this.sender = sender;
        this.filename = filename;
    }

    public void aendern(){
        JSONHandler jsonHandler = new JSONHandler();
        JSONObject jsonObject = null;

        try{
            jsonObject = (JSONObject)jsonHandler.readJsonSimpleDemo(filename);
            double oiltempAlt = (double)jsonObject.get("oil-temperature");
            double oiltemp = Math.random();
            jsonObject.replace("oil-temperature", Math.round((oiltempAlt-0.5+oiltemp) * 100.0) / 100.0);
            double oilpressAlt = (double)jsonObject.get("oil-pressure");
            double oilpress = Math.random()*0.5;
            jsonObject.replace("oil-pressure", Math.round((oilpressAlt-0.25+oilpress) * 100.0) / 100.0);
            double viscAlt = (double)jsonObject.get("viscosity");
            double visc = Math.random();
            jsonObject.replace("viscosity", Math.round((viscAlt-0.5+visc) * 100.0) / 100.0);
            double tempAlt = (double)jsonObject.get("temperature");
            double temp = Math.random();
            jsonObject.replace("temperature", Math.round((tempAlt-0.5+temp) * 100.0) / 100.0);
            jsonHandler.writeJsonSimpleDemo(filename, jsonObject);
            sender.setJsonObject(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
