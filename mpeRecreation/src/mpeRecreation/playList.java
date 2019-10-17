package mpeRecreation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
	
	public void play() {
		if(this.isshuffeled == true) {
			
		}
		else {
			
		}
	}
	
	public void Shuffle() {
		if(this.List.size() >0) {
			ArrayList<Object>temp = this.List;
			this.ShuffledList = new ArrayList();
			for(int x = 0; x<=this.List.size()-1;x++){
				Random rand = new Random(this.List.size()-1);
				int y = rand.nextInt();
				this.ShuffledList.add(temp.remove(y));
			}
			this.isshuffeled = true;
		}
	}
	
	
}
