import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class Tools {
	
	public HashSet<Character> excludeCharacter = new HashSet<Character>();
	public String fileName;
	public File lastPath;
	
	public DrawingComponent dc;
    private ColorCharsMap charColorMap; //TreeMap for based data of character his color for Graph and count 
    
	public Tools(DrawingComponent dcIn, ColorCharsMap charColorMapIn ) {
		// TODO Auto-generated constructor stub
		dc = dcIn;
		charColorMap = charColorMapIn;
	}
	
	public void setFilePath(JFrame frameIn) {
    	JFileChooser jfcOpenFile = new JFileChooser();
    	jfcOpenFile.setDialogTitle("Choose text file...");
    	jfcOpenFile.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	if (lastPath!=null) {
        	jfcOpenFile.setCurrentDirectory(lastPath);
        }
    	int result = jfcOpenFile.showOpenDialog(frameIn);
        if (result == JFileChooser.APPROVE_OPTION ) {
        	fileName = jfcOpenFile.getSelectedFile().toString();
            lastPath = jfcOpenFile.getCurrentDirectory();      
        }
        
	}
	//Scanning file for character found
	public String startScanFile(boolean isReverOrder) {
		if (fileName!=null) {
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fr);
			String line;
			int step = 0;
			while ((line = reader.readLine()) != null) {
				updateCharColorMapFromFileByString(line);
				updateSortListFromCharColorMap(isReverOrder);
				if (step%400==0)
					dc.sgDraw.drawFromFIFOResultList(dc.getGraphics());
				step++;
			}
			if (!SwingUtilities.isEventDispatchThread()){
				fr.close();
				reader.close();
			}
			if ((line = reader.readLine()) == null) {
				dc.sgDraw.isScanComplete = true;
			}
		 } 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		return "Scanning file has finshed.";
		} else return "";
		
	}
	//update data in CharColorMap (if new character then insert new entry, else increment field "Count" if character contain if CharColorMap)
	public void updateCharColorMapFromFileByString(String inputString){
		char[] charArray = inputString.toCharArray();
		for (char c : charArray) {
			if (!excludeCharacter.contains(c))
				charColorMap.putterCharColorMap(c);
		}
	}
	//update FIFO list a new data
	public void updateSortListFromCharColorMap(boolean isReverOrder){
		dc.sgDraw.fifoListColorChars = new ArrayList<ColorChars>(charColorMap.getMapFromTreeMap().values());
		Collections.sort(dc.sgDraw.fifoListColorChars,(isReverOrder)?Comparator.reverseOrder():null);
		dc.increaseHeight();
	}
	

	
}


