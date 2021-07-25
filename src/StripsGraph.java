import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

public class StripsGraph {
	public Graphics dcCurrentGraphics;			//global exemplar of Graphics interface for drawing on this
	public boolean isScanComplete = false;		//when file scanning is finished
	public DrawingComponent dc;					//link to current exemplar of "DrawingComponent"
	public int widthFrame;						//initial frame width
	public char uniqueChar;						//current character for drawing of iteration
	public int widthStrip; 						//Length current drawing line by count of unique characters 
	public int stripNumber; 					//current level for drawing line
	public float uniqueColor;					//current color for drawing line
	public List<ColorChars> fifoListColorChars;	//List contained current and finally result for drawing
	private float saturation = 0.7f;			//1.0 for brilliant, 0.0 for dull
	private float luminance = 0.75f; 			//1.0 for brighter, 0.0 for black
	private int widthMax=0;						//the maximum count of unique characters in the current iteration
	
	///////////////////////////////////////////variant uses in method "stripDraw"//////////////////////////////////////////////////////////////
	final int coefficientDivOrMultiply = 5; 	//multiplicity factor for the correct proportion of bands within the frame
	final int spaceXForStrip = 30;
	final int spaceYForStrip = 5;
	final int spaceXForText = 10;
	final int heightStrip = 10;
	final int spaceXForTextCount = 50;
	final int offSet = 2; 						//OFFSET on vertical alignment text on width Bound of Count character
	private int stripX=0;
	
	public StripsGraph(DrawingComponent dcIn) {
		dc = dcIn;
		dcCurrentGraphics = dc.getGraphics();
	}
	
	//setter
	public void setDataForStrip(char c, int width, float color, int step) {
		uniqueChar = c;
		widthStrip = width;
		uniqueColor = color;
		stripNumber = step;
	}
	//
	public void calcXForStrip() {
		int partFrame = widthStrip/(widthFrame/coefficientDivOrMultiply);
		if ( ( partFrame > (widthFrame/coefficientDivOrMultiply ) ) || ( widthMax != 0 ) ) {
			if  (widthMax < widthStrip ) {
				widthMax = widthStrip;
			}
				partFrame =  widthMax/widthFrame;
				stripX =  ( ( widthStrip * 100)/(partFrame) )/( widthFrame/coefficientDivOrMultiply );
			}else {
				stripX = ( widthStrip/(widthFrame) )*coefficientDivOrMultiply;
			}
	}
	//draw current result from FIFO list of ColorChars
	public void drawFromFIFOResultList(Graphics g){
		if ( fifoListColorChars!=null ) {
			int step=0;
			for ( ColorChars cc : fifoListColorChars ) {
				setDataForStrip(cc.getCharacter(), cc.getCount(),cc.getColor(),step);
				step++;
				calcXForStrip();
				drawStrip(g);
				}
			
	 	}
	}
	//update date about needed to increase Height of DrawingComponent
	public int getHeightForDC(int currentHeightOfDC) {
		int localHeight = ( currentHeightOfDC/heightStrip );
		if ( fifoListColorChars!=null ){
			return ( localHeight < fifoListColorChars.size() )?( fifoListColorChars.size()*heightStrip+spaceYForStrip ):currentHeightOfDC;
		} else return currentHeightOfDC;
	}
	//drawing
	private void drawStrip(Graphics g) {
		if ( widthStrip!=0 ) {
			Font currentFont = dcCurrentGraphics.getFont();
			g.setColor(Color.getHSBColor(uniqueColor, saturation, luminance));
			//clearing space and drawing strip of score
			int stepY = (stripNumber!=1)?stripNumber:1;
			if ( !isScanComplete )
				g.clearRect(spaceXForStrip, spaceYForStrip+heightStrip*stepY, widthFrame, heightStrip);
			//draw strip X
			g.fill3DRect(spaceXForStrip, spaceYForStrip+heightStrip*stepY, stripX, heightStrip, true);
			//draw strip under character
			g.fill3DRect(spaceXForText-5, spaceYForStrip+heightStrip*stepY, 25, heightStrip, true);
			//set font size and font color BLACK for drawing text content
			g.setFont(currentFont.deriveFont( currentFont.getSize() - 2.0F) );
			g.setColor(Color.BLACK);
			//draw black line axis X
			g.drawLine(spaceXForStrip, spaceYForStrip+heightStrip*stepY, spaceXForStrip, spaceYForStrip+heightStrip*stepY+heightStrip);
			//clearing space and drawing text Count of unequal Character
			//drp.clearRect(30, 50+10*(stepY+1), 50, 10);
			g.drawString(Integer.toString(widthStrip), spaceXForTextCount, spaceYForStrip+heightStrip*(stepY+1)-offSet);
			//clearing space and drawing text the unequal Character from TreeMap
			//drp.clearRect(spaceXForText, spaceYForStrip+heightLine*stepY, 15, heightLine);
			g.setColor(Color.WHITE);
			g.drawString("|"+Character.toString(uniqueChar)+"|", spaceXForText, spaceYForStrip+heightStrip*(stepY+1)-offSet);
		}
	}


}
