
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
            	try {
                    UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
                  } catch (Exception useDefault) {}
            	CreateAndShowGUI casGUI = new CreateAndShowGUI();
            	casGUI.showGUI();
            }
        });
        }

}

