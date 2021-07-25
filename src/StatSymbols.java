
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class StatSymbols {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

