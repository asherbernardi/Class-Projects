
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.lang.reflect.*;

public class ClockProgram extends JFrame implements ClockView {

    private static String[] boxOptions = 
           {"Time", "Set hours", "Set minutes", "Set seconds", "Set AM/PM"};
    private JTextField hours, minutes, seconds, ampm;

    public ClockProgram(Class modelClass) 
    throws NoSuchMethodException, InstantiationException, IllegalAccessException,
    InvocationTargetException {


        setSize(300, 150);
        setLocation(100, 100);
        //setTitle("Clock");
        setLayout(new FlowLayout());

        
        final ClockModel model = (ClockModel)
        	modelClass.getConstructor(ClockView.class).newInstance(this);
        
        JPanel upper = new JPanel();
        upper.setLayout(new FlowLayout());
        hours = new JTextField(2);
        hours.setEditable(false);
        upper.add(hours);
        minutes = new JTextField(2);
        minutes.setEditable(false);
        upper.add(minutes);
        seconds = new JTextField(2);
        seconds.setEditable(false);
        upper.add(seconds);
        ampm = new JTextField(2);
        ampm.setEditable(false);
        upper.add(ampm);
        add(upper);

        JPanel lower = new JPanel();
        lower.setLayout(new FlowLayout());
        JButton left = new JButton("<");
        left.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    model.left();
                }
            });
        lower.add(left);
        JButton center = new JButton("     ");
        center.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    model.center();
                }
            });
        lower.add(center);
        JButton right = new JButton(">");
        right.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    model.right();
                }
            });
        lower.add(right);

        add(lower);
        
        (new Thread(new Runnable() {
        	public void run() {
        		while(true) {
        			for (long time = System.currentTimeMillis();
        				 time + 1000 > System.currentTimeMillis(); ) ;
        			model.pulse();
        		}
        	}
        })).start();
        
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);        

    }

    public void display(String time) {
        StringTokenizer toker = new StringTokenizer(time, ":");
        hours.setText(toker.nextToken());
        minutes.setText(toker.nextToken());
        seconds.setText(toker.nextToken());
        ampm.setText(toker.nextToken());
    }


    public static void main(String[] args) {
    	try {
    		new ClockProgram(ClassLoader.getSystemClassLoader().loadClass(args[0]));
    	} catch (ArrayIndexOutOfBoundsException aioobe) {
    		System.out.println("Please specify a ClockModel class.");
    	} catch (NoSuchMethodException nsme) {
    		System.out.println("Class " + args[0] + " doesn't have an appropriate constructor");
    	} catch (InstantiationException ie) {
    		System.out.println("Can't instantiate class " + args[0]);
    	} catch (ClassNotFoundException cnfe) {
    		System.out.println("Didn't find class " + args[0]);
    	} catch (IllegalAccessException iae) {
    		System.out.println("Class " + args[0] + "'s constructor isn't public");
    	} catch (InvocationTargetException ite) {
    		System.out.println("I don't know what this exception is for.");
    		System.out.println(ite.getMessage());
    		ite.printStackTrace();
    	}
    	
    }
    

}