import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DatabaseConnection {
    public static Connection connect() {
        try {
            Properties config = loadConfig();
            String url = getValue(config, "DB_URL");
            String user = getValue(config, "DB_USER");
            String password = getValue(config, "DB_PASSWORD");

            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Properties loadConfig() {
        Properties config = new Properties();

        try (FileInputStream file = new FileInputStream(".env")) {
            config.load(file);
        } catch (Exception e) {
            System.out.println("No .env file found. Reading system environment variables instead.");
        }

        return config;
    }

    private static String getValue(Properties config, String key) {
        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            value = config.getProperty(key);
        }

        return value;
    }
}
