package helpers;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import exceptions.ConfigFileNotFound;

public class IniFileReader {

    public static String getBaseApiUrl() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.ini")) {
            properties.load(input);
    
            String port = properties.getProperty("port");
            String server = properties.getProperty("server");
        
            return server+":"+port;
        } catch (IOException e) {
        	throw new ConfigFileNotFound("config.ini file not found! Please contact the developer if this error persists.");
            //e.printStackTrace();
        }
    }
}
