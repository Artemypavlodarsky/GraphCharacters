import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

class DrawingComponent extends JPanel {
	
	private static final long serialVersionUID = 4902057285471885308L;
	public StripsGraph sgDraw;
	public Color dcBackground;
	private int preferredHeight=500;
	
	public DrawingComponent(){
		sgDraw = new StripsGraph(this);
		sgDraw.dcCurrentGraphics = getGraphics();
	}
	
	public int increaseHeight() {
		int localHeight = sgDraw.getHeightForDC(getHeight());
		if (localHeight>getHeight())
			{
			preferredHeight = localHeight; 
			setPreferredSize(new Dimension(600,localHeight));
			setSize(600, localHeight);
			return localHeight;
			} else return getHeight(); 
	}
	
@Override
	protected void paintComponent(Graphics g) {       
		super.paintComponent(g);
		if (sgDraw.dcCurrentGraphics==null) sgDraw.dcCurrentGraphics = g;
		sgDraw.drawFromFIFOResultList(g);
	 }

@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, preferredHeight);
	}
	  
}