import org.json.simple.JSONObject;

public class Aenderungen {

    public void aendern(){
        JSONHandler jsonHandler = new JSONHandler();
        JSONObject jsonObject = null;

        try{
            jsonObject = (JSONObject)jsonHandler.readJsonSimpleDemo("data_test.json");
            System.out.println(jsonObject);
            double oiltempAlt = (double)jsonObject.get("oil-temperature");
            double oiltemp;
            do {
                oiltemp = oiltempAlt-0.5+Math.random();
            }while(oiltemp > 90 || oiltemp < 70);
            jsonObject.replace("oil-temperature", oiltemp);
            double oilpressAlt = (double) jsonObject.get("oil-pressure");
            double oilpress;
            do {
                oilpress = oilpressAlt-0.1+(Math.random() * 0.5);
            }while (oilpress > 3 || oilpress < 1);
            jsonObject.replace("oil-pressure", oilpress);
            double viscAlt = (double)jsonObject.get("viscosity");
            double visc;
            do{
                visc = viscAlt-0.5+Math.random();
            }while (visc > 10 || visc < 8);
            jsonObject.replace("viscosity", visc);
            double tempAlt = (double)jsonObject.get("temperature");
            double temp;
            do{
                temp = tempAlt-0.5+Math.random();
            }while (temp > 30 || temp < -10);
            jsonObject.replace("temperature", temp);
            System.out.println(jsonObject);
            jsonHandler.writeJsonSimpleDemo("data_test.json" ,jsonObject);
            int err =  (int)(Math.random()*100);
            System.out.println(err);
            if(err==1){
                int errType = (int)(Math.random()*3);
                System.out.println(errType);
                if(errType == 0){
                    jsonObject.replace("oil-temperature", 0.0);
                }else if(errType == 1){
                    jsonObject.replace("oil-pressure", 0.0);
                }else if(errType == 2){
                    jsonObject.replace("viscosity", 0.0);
                }else if(errType == 3){
                    jsonObject.replace("temperature", 0.0);
                }
            }
            jsonHandler.writeJsonSimpleDemo("final_data.json" ,jsonObject);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
