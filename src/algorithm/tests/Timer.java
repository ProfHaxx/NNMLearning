package algorithm.tests;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Timer extends JPanel{
    private Image dbImage;
    private Graphics dbg;
    private static long startingTime;
    private static Map<Long, String> events;

    private static final int yOffset = 7;

    public void paint(Graphics g){
        dbImage = createImage(getWidth(), getHeight());
        dbg = dbImage.getGraphics();
        paintOneScene(dbg);
        g.drawImage(dbImage, 0, 0, this);
        repaint(100);
    }

    public static void startTimer(){
        JFrame frame= new JFrame("Timer");
        frame.getContentPane().add(new Timer());
        frame.setSize(200, 200 + yOffset);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        events = new HashMap<>();
        events.put(System.currentTimeMillis(), "Start");
        startingTime = System.currentTimeMillis();
    }

    private static void paintOneScene(Graphics g){
        g.drawString(Double.toString( ((double) System.currentTimeMillis() - startingTime) / 1000), 10,12 + yOffset);
        int i = 2;
        for(Long l: events.keySet()){
            g.drawString(Double.toString(((double) (l - startingTime)) / 1000) + ": " + events.get(l), 10, 12 * i + yOffset);
            i++;
        }
    }

    public static void addEvent(String log){
        events.put(System.currentTimeMillis(),log);
    }
}