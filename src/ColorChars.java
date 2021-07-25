
public class ColorChars implements Comparable<ColorChars> {
								//ColorChars class created for Map containing:
	private char character;		//1) character - name of unique character for using in drawing on DrawingComponent
	private float color;		//2) color - representation float of Color
	private Integer count=0;	//3) count - count of unique character in a lots of.
	
	ColorChars(char charIn, float colorIn, int countIn){
		character = charIn;
		color = colorIn;
		count = countIn;
	}
//Comparator for Reverse order by value "count" from each exemplar of class "ColorChars" descending for a drawing.
@Override
	public int compareTo(ColorChars o) {
		return this.count.compareTo(o.count);
	}

	public char getCharacter() {
		return character;
	}
	
	public int getCount() {
		return count;
	}

	public float getColor() {
		return color;
	}
	
}
