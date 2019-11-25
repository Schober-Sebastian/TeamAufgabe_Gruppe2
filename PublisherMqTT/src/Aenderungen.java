import org.json.simple.JSONObject;

public class Aenderungen {

    public void aendern(){
        JSONHandler jsonHandler = new JSONHandler();
        JSONObject jsonObject = null;

        try{
            jsonObject = (JSONObject)jsonHandler.readJsonSimpleDemo("data_test.json");
            System.out.println(jsonObject);
            double oiltempAlt = (double)jsonObject.get("oil-temperature");
            double oiltemp = Math.random();
            jsonObject.replace("oil-temperature", oiltempAlt-0.5+oiltemp);
            double oilpressAlt = (double)jsonObject.get("oil-pressure");
            double oilpress = Math.random()*0.5;
            jsonObject.replace("oil-pressure", oilpressAlt-0.25+oilpress);
            double viscAlt = (double)jsonObject.get("viscosity");
            double visc = Math.random();
            jsonObject.replace("viscosity", viscAlt-0.5+visc);
            double tempAlt = (double)jsonObject.get("temperature");
            double temp = Math.random();
            jsonObject.replace("temperature", tempAlt-0.5+temp);
            System.out.println(jsonObject);
            jsonHandler.writeJsonSimpleDemo("data_test.json" ,jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
