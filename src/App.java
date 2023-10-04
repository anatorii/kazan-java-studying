import io.github.cdimascio.dotenv.Dotenv;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class App {
    static Dotenv dotenv = null;
    static Image[] image = new Image[21];

    public static void main(String[] args) {
        dotenv = Dotenv.configure()
                .directory("assets")
                .filename(".env")
                .load();

        try {
            String point = "&ll=49.106414,55.796127";
            String pt = "&pt=49.106414,55.796127,flag";
            String apikey = App.dotenv.get("API_KEY");
            for (int i = 0; i < image.length; i++) {
                String zString = "&z=" + i;
                String uri = "https://static-maps.yandex.ru/v1?size=650,450" + "&apikey=" + apikey + point + zString + pt;
                URL url = new URL(uri);
                image[i] = ImageIO.read(url);
                System.out.println("image loaded: " + i);
            }
            System.out.println("image loaded");
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }
}
