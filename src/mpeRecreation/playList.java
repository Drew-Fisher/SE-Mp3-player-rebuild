package mpeRecreation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class playList implements Serializable{
	private String Name,Duration;
	private boolean isshuffeled = false;
	private int secs;
	private ArrayList<Object> List,ShuffledList;
	
	
	

	public playList(String name) {
		this.Name = name;
		this.Duration = "";
		this.secs = 0;
		this.List = new ArrayList<Object>();
	}
	
	public void Add(Object toAdd) {
		this.List.add(toAdd);
		this.isshuffeled = false;
	}
	
	public void remove(Object toRemove) {
		this.List.remove(toRemove);
		this.isshuffeled = false;
	}
	
	
	/*
	 * Traverses the playlist
	 */
	
	
	public void Shuffle() {
		int z = this.List.size()-1;
		if(this.List.size() > 0) {
			ArrayList<Object>temp = this.List;
			this.ShuffledList = new ArrayList();
			ArrayList tem = (ArrayList) temp.clone();
			for(int x = 0; x<=this.List.size()-1;x++){
				if(z == 0) {
					this.ShuffledList.add(tem.remove(0));
				}
				else {
					Random rand = new Random();
					int y = rand.nextInt(z);
					System.out.println(y);
					this.ShuffledList.add(tem.remove(y));
					z--;
				}
				
			}
			this.isshuffeled = true;
		}
	}
	
	/*
	 * Delete from play list
	 */
	public void deleteFromList(Song s) throws IOException {
		this.isshuffeled = false;
		this.List.remove(s);
		FileManager.savePlayList(this);
	}
	
	
	
	/*
	 * getter and setters
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

	public boolean isIsshuffeled() {
		return isshuffeled;
	}

	public void setIsshuffeled(boolean isshuffeled) {
		this.isshuffeled = isshuffeled;
	}

	public int getSecs() {
		return secs;
	}

	public void setSecs(int secs) {
		this.secs = secs;
	}

	public ArrayList<Object> getList() {
		return List;
	}

	public void setList(ArrayList<Object> list) {
		List = list;
	}

	public ArrayList<Object> getShuffledList() {
		return ShuffledList;
	}

	public void setShuffledList(ArrayList<Object> shuffledList) {
		ShuffledList = shuffledList;
	}
}
