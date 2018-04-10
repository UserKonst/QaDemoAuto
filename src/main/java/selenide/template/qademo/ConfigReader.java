package selenide.template.qademo;

import java.util.ResourceBundle;
import org.apache.log4j.Logger;

public class ConfigReader {

    final static Logger LOG = Logger.getLogger(ConfigReader.class);

    private static ConfigReader _instance = null;
    private static ResourceBundle rb;

    private ConfigReader() {
    }

    public synchronized static ConfigReader getInstance() {
        if (_instance == null) {
            _instance = new ConfigReader();
            readProperties();
        }
        return _instance;
    }

    public synchronized String getProperty(String key) {
        return rb.getString(key);
    }

    private static void readProperties() {
        String path = System.getProperty("configPath");
        if (path == null) {
            throw new RuntimeException("Config path not found!");
        }
        rb = ResourceBundle.getBundle(path);
    }

}
