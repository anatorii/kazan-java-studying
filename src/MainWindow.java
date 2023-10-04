import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class MainWindow extends JFrame {
    private static int width = 800;
    private static int height = 600;
    private JPanel panel;
    private JPanel south;
    private JPanel center;
    private JSlider slider1;
    public int zoom = 10;

    public MainWindow() {
        super("Загрузить Казань");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(MainWindow.width, MainWindow.height);
        this.setLocation(d.width / 2 - MainWindow.width / 2, d.height / 2 - MainWindow.height / 2);
        this.getContentPane().add(panel);

        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider slider = (JSlider) e.getSource();
                zoom = slider.getValue();
                center.repaint();
            }
        });
    }

    private void createUIComponents() {
        center = new CenterPanel();
        center.setLayout(new BorderLayout());
    }
}

class CenterPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainWindow frame = (MainWindow) SwingUtilities.getWindowAncestor(this);
        g.clearRect(0, 0, getSize().width, getSize().height);
        g.drawImage(App.image[frame.zoom], (getSize().width - 650) / 2, (getSize().height - 450) / 2, null);
        System.out.println("draw image, zoom " + frame.zoom);
    }
}
