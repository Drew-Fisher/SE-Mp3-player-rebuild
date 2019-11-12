package mpeRecreation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.scene.shape.Path;

public class FileManager {
	
	
	
	
	
	
	public static void buildApp() {
		checkFoulders();
	}
	
	private static void checkForLibary() {
		File f = new File("./MusicLibary");
		if(f.isDirectory()) {
			
		}else {
			f.mkdir();
		}
	}
	
	private static void checkFoulders() {
		checkForLibary();
		checkPlaylistFoulder();
		checkmp3Foulder();
	}
	
	private static void checkmp3Foulder() {
		File f = new File("./mp3s");
		if(f.isDirectory()) {
			
		}else {
			f.mkdir();
		}
	}
	
	private static void checkPlaylistFoulder() {
		File f = new File("./PlayList");
		if(f.isDirectory()) {
			
		}else {
			f.mkdir();
		}
	}
	
	private static void movetomp3(File f) throws IOException {
		String loc = "./mp3s/"+f.getName();
		File m = new File(loc);
		Files.copy(f.toPath(), m.toPath());
	}
	/*
	 * saves a song object in the MusicLibary folder
	 */
	public static void saveSong(File f) throws IOException, UnsupportedTagException, InvalidDataException {
		movetomp3(f);
		String loc = "./mp3s/"+f.getName();
		Song s = new Song(new File(loc));
		
		FileOutputStream fout = new FileOutputStream("./MusicLibary/"+s.getName());
		ObjectOutputStream oop = new ObjectOutputStream(fout);
		oop.writeObject(s);
		oop.close();
		fout.close();
		
	}
	
	/*
	 * Loads the Music Libary from Directory
	 */
	public static ArrayList<Song> loadLibary() throws IOException, ClassNotFoundException {
		ArrayList<Song> Libary = new ArrayList<Song>();
		File [] libDir = new File("./MusicLibary").listFiles();
		for(int x = 0; x<= libDir.length-1; x++) {
			FileInputStream fin = new FileInputStream(libDir[x]); 
			ObjectInputStream oin = new ObjectInputStream(fin);
			Song s = (Song)oin.readObject();
			Libary.add(s);
			oin.close();
			fin.close();
			
		}
		return Libary;
	}
	/*
	 * saves a playList to Directory
	 */
	public static void savePlayList(playList p) throws IOException {
		FileOutputStream fout = new FileOutputStream("./PlayList/"+p.getName());
		ObjectOutputStream oop = new ObjectOutputStream(fout);
		oop.writeObject(p);
		oop.close();
		fout.close();
	}
	
	/*
	 * returns the playLists from Directory
	 */
	public static ArrayList<playList> loadLists() throws IOException, ClassNotFoundException{
		ArrayList<playList> albums = new ArrayList();
		File[] albDir = new File("./PlayList/").listFiles();
		for(int x =0; x<= albDir.length-1;x++){
			FileInputStream fin = new FileInputStream(albDir[x]);
			ObjectInputStream oin = new ObjectInputStream(fin);
			playList p = (playList)oin.readObject();
			albums.add(p);
			oin.close();
			fin.close();
		}
		return albums;
	}
	
	/*
	 * Builds the hashmap to map song names to SOng objects
	 * 
	 * 
	 */
	
	public static HashMap<String, Song> buildSongmap(ArrayList<Song> lib){
		HashMap<String, Song> smap = new HashMap();
		for(int x =0; x<= lib.size()-1;x++) {
			smap.put(lib.get(x).getName(), lib.get(x));
		}
		return smap;
	}
	/*
	 * builds the hash map for the playlist
	 */
	public static HashMap<String, playList> buildPlayListMap(ArrayList<playList> collection){
		HashMap<String, playList> smap = new HashMap();
		for(int x =0; x<= collection.size()-1;x++) {
			smap.put(collection.get(x).getName(), collection.get(x));
		}
		return smap;
	}
	
	/*
	 * checks if playlist already exists
	 */
	public static boolean checkplayList(String s) throws ClassNotFoundException, IOException {
		ArrayList<playList> cur = loadLists();
		for(int x =0; x<=cur.size()-1;x++) {
			if(cur.get(x).getName() == s) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Deletes PlayList
	 */
	public static void deletePlayList(playList p) {
		String path = "./Playlist/"+p.getName();
		File f = new File(path);
		f.delete();
	}
	
	
	
	/*
	 * Delete from MusicLibary
	 */
	public static void deleteSong(Song s) {
		
	}
}
