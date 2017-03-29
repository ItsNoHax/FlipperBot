package fg.flipperbot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads properties from config.properties file.
 * Created by Alberto on 11/20/2015.
 */
public class PropertiesLoader {
    InputStream inputStream;
    private String propFileName = "config.properties";

    public Properties getPropValues() throws IOException {
            Properties prop = new Properties();
        try {
            inputStream = getClass().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return prop;
    }
}
