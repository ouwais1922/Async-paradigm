package homework2.provider;
import javax.jws.WebService;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.lang.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.io.*;
import java.util.*;
import java.awt.List;
@WebService
public class AccessTools{
   
    public boolean reboot(){
        try{
            Runtime runtime = Runtime.getRuntime();
            String opsName = System.getProperty("os.name").toLowerCase();
            if(opsName.contains("windows")){
                runtime.exec("shutdown -r -t 0");
            }else if(opsName.contains("linux") || opsName.contains("mac os x")){
                runtime.exec("shutdown -r now"); 
            }
            return true;
        } catch(Exception e){
            return false;
        }
    }
    public byte[] getScreenShot(){
        try{
            Rectangle myRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage myCapture = new Robot().createScreenCapture(myRectangle);

            ByteArrayOutputStream myBaos = new ByteArrayOutputStream();

            ImageIO.write(myCapture, "png", myBaos );

            byte[] bytes = myBaos.toByteArray();
            
            return bytes;
        }catch(Exception e){
            return null;
        }
    }

    
    public  String[] getRunningProcess() throws Exception {

        int max_n_porcesses= 10000;
    
        String[] processes = new String[max_n_porcesses];
        String os = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
    
        if(os.contains("win")){
            os = "windows";
        }else if (os.contains("mac")){
            os = "MacOS";
        }else if (os.contains("nux")){
            os = "Linux";
        }else{
            os = "other";
        }
        if(os.equals("windows")){
            try{
                Process process = Runtime.getRuntime().exec("tasklist.exe");
                Scanner scanner = new Scanner(new InputStreamReader(process.getInputStream()));
                int i = 0;
                while (scanner.hasNext()) {
                    processes[i++]=scanner.nextLine();
                }
                scanner.close();
            }catch( Exception err){
                err.printStackTrace();
            }
        }else if(os.equals("Linux")){
    
            try {
                String line;
                Process p = Runtime.getRuntime().exec("ps -e");
                BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
                int i = 0;
                while ((line = input.readLine()) != null) {
                    processes[i++]=line;
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
        }else if(os.equals("MacOS")){
    
            try {
                String line;
                Process p = Runtime.getRuntime().exec("ps -e -o command");
                BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
                int i = 0;
                while ((line = input.readLine()) != null) {
                    processes[i++]=line;
                }
                input.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
    
        }else{
            processes= null;
        }
        int i =0;
        while(true){
            if( processes[i]== null){
                break;
            }else{
                i++;
            }
        }
    
        String[] output= new String[i];
        for(int k=0;k!=i;k++){
            output[k]=processes[k]+"\n";
        }
        return output;
    
    }

 
}