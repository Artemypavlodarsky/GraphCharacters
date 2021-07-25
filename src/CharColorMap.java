import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class CharColorMap {
	
	private static TreeMap< Character, ColorChars > charColorMap;	// contain result set(TreeMap) after reading file
	private static Random random = new Random();					// generate values for unique color of unique character
	
	CharColorMap(){
		charColorMap = new TreeMap< Character, ColorChars >();
	}
	
//	Put or update data (chIn - found unique character) - if character contain 
//	in map then count increment on one, else create new "record" Character 
//	and exemplar of class "ColorChars" containing unique color relate for unique character and count set to one.
	public void putterCharColorMap(char caracterhIn) {
		ColorChars colorChars;
		if (!charColorMap.containsKey(caracterhIn)){
			colorChars = new ColorChars(caracterhIn,  random.nextFloat(), 1);
		} else {
			colorChars = new ColorChars(caracterhIn, charColorMap.get(caracterhIn).getColor(), charColorMap.get(caracterhIn).getCount()+1 );
		} 
		if (colorChars!=null) charColorMap.put(caracterhIn, colorChars);
		
	}
//	Convert from TreeMap to HashMap(without default order) for create himself order of Map
	public Map<Character, ColorChars> getMapFromTreeMap() {
			return charColorMap;
	}


}
