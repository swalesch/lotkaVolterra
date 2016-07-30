package lokkta.volterra;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class GuiMain extends JFrame {

    private static final long serialVersionUID = 5845195683189476922L;
    public static JFrame _mainFrame;
    private static MapGraphicsPanel _graphicsPanel;

    public static void main(String[] args) throws InterruptedException {
        _mainFrame = new JFrame();
        _mainFrame.setTitle("Simulation");
        _mainFrame.setSize(800, 600);
        _mainFrame.setResizable(false);
        _mainFrame.setLayout(null);

        _mainFrame.setVisible(true);
        _mainFrame.addWindowListener(getWindowListener());

        _graphicsPanel = new MapGraphicsPanel(0, 0, 600, 300);
        _mainFrame.add(_graphicsPanel);
        paintGraph();

    }

    private static void paintGraph() throws InterruptedException {
        LotkaVolterra lotkaVolterra = new LotkaVolterra();
        double zoomfaktor = 0.5;
        int yStartValue = (int) (_graphicsPanel.getHeight() - 10 - lotkaVolterra.getMaxPop());
        int xStartValue = _graphicsPanel.getWidth() - 20;
        int oldYBeute = yStartValue - (int) (lotkaVolterra.getAnzahlBeute() * zoomfaktor);
        int oldYRauber = yStartValue - (int) (lotkaVolterra.getAnzahlRauber() * zoomfaktor);
        int threadSleepMillis = 20;
        while (true) {
            _graphicsPanel.moveDrawing(-1, 0);
            lotkaVolterra.next();
            double anzahlBeute = lotkaVolterra.getAnzahlBeute() * zoomfaktor;
            double anzahlRauber = lotkaVolterra.getAnzahlRauber() * zoomfaktor;
            int newYBeute = yStartValue - (int) anzahlBeute;
            int newYRauber = yStartValue - (int) anzahlRauber;

            // _graphicsPanel.drawDot(400, newYBeute, Color.BLUE);
            // _graphicsPanel.drawDot(400, newYRauber, Color.RED);
            _graphicsPanel.drawLine(xStartValue, oldYBeute, xStartValue, newYBeute, Color.BLUE);
            _graphicsPanel.drawLine(xStartValue, oldYRauber, xStartValue, newYRauber, Color.RED);

            oldYBeute = newYBeute;
            oldYRauber = newYRauber;
            Thread.sleep(threadSleepMillis);
        }

    }

    private static WindowListener getWindowListener() {
        return new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(JFrame.DISPOSE_ON_CLOSE);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }
        };
    }

}
