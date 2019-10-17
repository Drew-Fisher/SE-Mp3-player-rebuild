package mpeRecreation;

import java.awt.MediaTracker;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import javax.print.attribute.standard.Media;


import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;




/*
 * this class is the Song class essentially it defines several useful elements and stores them for easy access. it implements Mp3magic repository
 */
public class Song implements Serializable{
	private String Name,Duration,filePath,Artist;
	private int durInsec;
	private File file;
	private Mp3File m;
	private ID3v1 i;
	
	/*
	 * Constructor for song that takes the file path as a String and creates a Song object
	 */
	public Song(String fpath) throws IOException, UnsupportedTagException, InvalidDataException {
		File f = new File(fpath);
		//movetoDir(fpath);
		this.m = new Mp3File(f.getAbsolutePath().toString());
		this.i = m.getId3v1Tag();
		getmetatdata(f);
		
	}
	
	
	
	public void Play() {
		 
	}
	
	private void save() {
		
	}
	
	/*
	 * gets the meta data from an .mp3 file using Mp3agic and sets teh values of the song element
	 */
	private void getmetatdata(File f) throws UnsupportedTagException, InvalidDataException, IOException {
		this.Name = i.getTitle();
		this.Artist = i.getArtist();
		this.durInsec = (int)m.getLengthInSeconds();
		this.Duration = getDur();
	}
	
	/*
	 * takes the duration in seconds and converts it to a minet second format
	 */
	private String getDur() {
		int x = this.durInsec;
		int y = x/60;
		int z = x-(y*60);
		String s = "("+y+":"+z+")";
		return s;
	}
	
	public String toString() {
		return this.Name+this.Duration+this.Artist;
		
	}
}
