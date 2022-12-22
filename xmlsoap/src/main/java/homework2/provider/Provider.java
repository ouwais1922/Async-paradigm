package homework2.provider;
import javax.xml.ws.Endpoint;

public class Provider {

    private static final String URL = "http://localhost:7000/RAT";
    public static void main(String[] args) {
        
        AccessTools tool = new AccessTools();
        System.out.println("Publishing the remote call system");
        Endpoint.publish(URL, tool);
        System.out.println("RAT IS Published");
    }
}
