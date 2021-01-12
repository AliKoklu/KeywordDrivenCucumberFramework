package Driver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertiesReader {


    public java.util.Properties prop;

    public java.util.Properties init_properties(){

        prop = new java.util.Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/java/config/config.properties");

            prop.load(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }
}
