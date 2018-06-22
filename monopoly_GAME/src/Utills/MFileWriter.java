package Utills;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * A utility class for logging output text to a file
 * note: final means that we can't extends this class
 * @author Amjad Nassar
 * @author Aiman Younis
 */
public final class MFileWriter{
	
	/** output file */
	static private File outputLogFile;
	
	/** a file writer buffer */
	static private FileWriter writer; 
	
	/**
	 * Creates a file and a writer for logging
	 */
	public static void initializeMyFileWriter(){
		 /* If we don't want to override the same file each time then
		  * we can use SimpleDateFormat by adding it 
		  * to the name of the file. 
		  */
		File dr = new File("Logs");
		SimpleDateFormat date = new SimpleDateFormat("MM-dd-yy hh-mm-ss");
		date.setCalendar(Calendar.getInstance());
		String str = date.format(new Date());
		
		dr.mkdir();
		
		outputLogFile = new File(dr,"log-"+str+".txt");
		
		try {
            writer = new FileWriter(outputLogFile);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Writes given text message to the log file.
	 * @param message
	 * @param isSeparatorNeeded
	 */
	public static void writeToLogFile(String message, boolean isSeparatorNeeded){
		try {
			writer.write(message);
			if(isSeparatorNeeded)
				writeAsteriskSeparatorToLogFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes separator to log file.
	 */
	public static void writeHyphenSeparatorToLogFile(){
		try {
			writer.write("\n-----------------------------------------------------------------------------\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes separator to log file.
	 */
	public static void writeAsteriskSeparatorToLogFile(){
		try {
			writer.write("\n****************************************************************************\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Saves the log file (by closing the file writer)
	 */
	public static void saveLogFile(){
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}//END OF ~ MFileWriter

