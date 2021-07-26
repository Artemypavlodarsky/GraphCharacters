import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class ColorCharsMap {
	
	private static TreeMap<Character, ColorChars> charColorMap;	// contain result set(TreeMap) after reading file
	private static Random random = new Random();					// generate values for unique color of unique character
	
	ColorCharsMap(){
		charColorMap = new TreeMap<Character, ColorChars>();
	}
//	Put or update data (chIn - found unique character) - if character contain 
//	in map then count increment on one, else create new "record" Character 
//	and exemplar of class "ColorChars" containing unique color relate for unique character and count set to one.
	public void putterCharColorMap(char charIn) {
		ColorChars colorChars;
		if (!charColorMap.containsKey(charIn)){
			colorChars = new ColorChars(charIn,  random.nextFloat(), 1);
		} else {
			colorChars = new ColorChars(charIn, charColorMap.get(charIn).getColor(), charColorMap.get(charIn).getCount()+1 );
		} 
		if (colorChars!=null) charColorMap.put(charIn, colorChars);
	}
//	Convert from TreeMap to HashMap(without default order) for create himself order of Map
	public Map<Character, ColorChars> getMapFromTreeMap() {
			return charColorMap;
	}

}
