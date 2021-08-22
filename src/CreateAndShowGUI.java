import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class CreateAndShowGUI {
	
	public CreateAndShowGUI() {
	 	
	}
	
	public void showGUI() {
		createAndShowGUI();
	}
	
	private static void createAndShowGUI() {
	 	JFrame frame = new JFrame("Count of character");
	  	JPanel jcp = new JPanel(new BorderLayout());
	  	JPanel jpTop = new JPanel(new GridLayout());
	  	JLabel jlExclude = new JLabel("Exclude symbol:");
	  	JTextField jtfExclude = new JTextField("SPACE as splitter");
	  	JButton btnObenFile = new JButton("Open file...");
	  	DrawingComponent dc = new DrawingComponent();
	  	JScrollPane jspDC = new JScrollPane(dc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
	  											JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	  	Tools tfdc = new Tools(dc, new ColorCharsMap());
	  	JButton btnStartScan = new JButton("Scann file");
	  	JCheckBox jcbIsReversOrder = new JCheckBox("Is Revers");
	  	jcbIsReversOrder.setSelected(true);
	  	JCheckBox jcbIsExcludeSpace = new JCheckBox("Exclude space");
	  	jcbIsExcludeSpace.setSelected(false);
	  	jpTop.add(jcbIsReversOrder);
	  	jpTop.add(jcbIsExcludeSpace);
	  	jpTop.add(jlExclude);
	  	jpTop.add(jtfExclude);
	  	jpTop.add(btnObenFile);
	  	jpTop.add(btnStartScan);
	  	frame.setContentPane(jcp);
	  	jcp.add(jpTop, BorderLayout.NORTH);
	    jcp.add(jspDC, BorderLayout.CENTER); 
	    frame.setSize(600, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLocationRelativeTo(null);
	    frame.setResizable(false);
	    frame.pack();
	    dc.dcBackground = frame.getBackground();
	    frame.setVisible(true);
	    dc.sgDraw.widthFrame = (int) frame.getBounds().getWidth();
	    
	    btnObenFile.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	  	    	tfdc.setFilePath(frame);
	  	    }
	  	});
	    
	  	btnStartScan.addActionListener(new ActionListener() {
	  		@Override
	  		public void actionPerformed(ActionEvent e) {
	  	    	dc.sgDraw.isScanComplete = false;
	  	    	frame.setTitle((tfdc.fileName!="")?tfdc.startScanFile(jcbIsReversOrder.isSelected()):"");
	  	    	System.gc();
	  	    }
	  	});
	  	
	  	jcbIsReversOrder.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                tfdc.updateSortListFromCharColorMap(jcbIsReversOrder.isSelected());
                dc.repaint();
            }
        });
	  	
	  	jcbIsExcludeSpace.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ( jcbIsExcludeSpace.isSelected() ) {
                	tfdc.excludeCharacter.add(' ');
                } else {
                	if (tfdc.excludeCharacter.contains(' ')) tfdc.excludeCharacter.remove(' ');
                }
            }
        });
	  	
	  	jtfExclude.addKeyListener((KeyListener) new KeyAdapter() {
	  	    public void keyReleased(KeyEvent e) {
	  	    	JTextField textField = (JTextField) e.getSource();
	  	    	if (!(textField.getText().equals("SPACE as splitter")) && (!(textField.getText().equals(""))) ) {
		  	        String[] text = textField.getText().split(" ");
		  	        for (String str : text) {
						tfdc.excludeCharacter.add(str.charAt(0));
					}
	  	    	}
	  	    }
	  	});
    } 

}
