package algorithm.tests;

import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Timer extends JPanel{
    private static long startingTime;
    private static Map<Long, String> events;
    private static List<Long> eventsTime = new ArrayList<>();

    private static final int yOffset = 7;

    public void paint(Graphics g){
        Image dbImage = createImage(getWidth(), getHeight());
        paintOneScene(dbImage.getGraphics());
        g.drawImage(dbImage, 0, 0, this);
        repaint(100);
    }

    public static void startTimer(){
        JFrame frame= new JFrame("Timer");
        frame.getContentPane().add(new Timer());
        frame.setSize(300, 200 + yOffset);
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
        eventsTime.clear();
        eventsTime = new ArrayList<>(events.keySet());
        eventsTime.sort(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            }
        });
        for(Long l: eventsTime){
            g.drawString(Double.toString(((double) (l - startingTime)) / 1000) + ": " + events.get(l), 10, 12 * i + yOffset);
            i++;
        }
    }

    public static void addEvent(String log){
        events.put(System.currentTimeMillis(),log);
    }
}