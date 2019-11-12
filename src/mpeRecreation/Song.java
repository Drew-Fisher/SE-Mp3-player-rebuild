package mpeRecreation;


import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;





/*
 * this class is the Song class essentially it defines several useful elements and stores them for easy access. it implements Mp3magic repository
 */
public class Song implements Serializable{
	private String Name,Duration,filePath,Artist;
	private int durInsec;
	private File file;
	
	
	/*
	 * Constructor for song that takes the file path as a String and creates a Song object
	 */
	public Song(File f) throws IOException, UnsupportedTagException, InvalidDataException {
		this.file = f;
		
		//movetoDir(fpath);
		
		getmetatdata();
		
		
	}
	
	
	
	public void Play() {
		 
	}
	
	private void save() {
		
	}
	
	



	/*
	 * gets the meta data from an .mp3 file using Mp3agic and sets the values of the song element
	 */
	private void getmetatdata() throws UnsupportedTagException, InvalidDataException, IOException {
		int check = IDcheck();
		System.out.println(check);
		if(check == 0) {
			ID3v2 i = getm().getId3v2Tag();
			if(i.getTitle() == null) {
				this.Name = this.file.getName().replace(".mp3", "");
			}
			else {
				this.Name = i.getTitle();
			}
			if(i.getArtist() == null) {
				this.Artist = "n/a";
			}
			else {
				this.Artist = i.getArtist();
			}
			this.durInsec = (int)getm().getLengthInSeconds();
			this.Duration = getDur();
		}
		else if(check == 1) {
			ID3v1 i = getm().getId3v1Tag();
			if(i.getTitle() == null) {
				this.Name = this.file.getName().replace(".mp3", "");
			}
			else {
				this.Name = i.getTitle();
			}
			if(i.getArtist() == null) {
				this.Artist = "n/a";
			}
			else {
				this.Artist = i.getArtist();
			}
			this.durInsec = (int)getm().getLengthInSeconds();
			this.Duration = getDur();
			
		}
		else {
			this.Name = this.file.getName();
			this.Artist ="n/a";
			this.Duration = "n/a";
		}
		
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
	
	/*
	 * Plays the mp3 file by passing in the Mediaplayer
	 */
	public void play(MediaPlayer p) {
		p = new MediaPlayer(getMedia());
		p.play();
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String toString() {
		return this.Name+this.Duration+this.Artist;
		
	}
	
	
	
	/*
	 * ID3 check
	 * returns int depending on the ID3v tag the file contains
	 * 0 = ID3v2
	 * 1 = ID3v1
	 * 2 = other
	 */
	private int IDcheck() throws UnsupportedTagException, InvalidDataException, IOException {
		if(getm().hasId3v2Tag()) {
			return 0;
		}
		else if(getm().hasId3v1Tag()) {
			return 1;
		}
		else {
			return 2;
		}
	}
	
	
	/*
	 * getter for Mp3File m
	 */
	
	public Mp3File getm() throws UnsupportedTagException, InvalidDataException, IOException {
		return new Mp3File(this.file.getAbsolutePath().toString());
	}
	
	/*
	 * getter for media
	 */
	public Media getMedia() {
		return new Media(this.file.toURI().toString());
	}
	
	
	/*
	 * Getters and Setters
	 */
	
	public String getName() {
		return Name;
	}



	public void setName(String name) {
		Name = name;
	}



	public String getDuration() {
		return Duration;
	}



	public void setDuration(String duration) {
		Duration = duration;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public String getArtist() {
		return Artist;
	}



	public void setArtist(String artist) {
		Artist = artist;
	}



	public int getDurInsec() {
		return durInsec;
	}



	public void setDurInsec(int durInsec) {
		this.durInsec = durInsec;
	}



	public File getFile() {
		return file;
	}



	public void setFile(File file) {
		this.file = file;
	}



	


	
}
