package mpeRecreation;

import java.awt.FileDialog;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
//import javafx.scene.media.MediaPlayer;
//import javafx.scene.media.MediaPlayer.Status;



public class uiController implements Initializable{
	private MediaPlayer p;
	private ArrayList<Song> lib;
	private HashMap<String, Song> Album;
	private HashMap yourAlbums;
	private playList selected;
	private String selectedSong;
	private String playing;
	private boolean loop = false;
	private boolean isSelectedPlayling = false;
	private boolean canplay = true;
	private ArrayList<playList> yourPlayLists;
	
	@FXML
	private Button playPause,newList,addToPlayList,playListPlay,loopButton,ImportButton,stop,shuffleButton;
	
	@FXML
	private ListView<String> listOfSongs,listOfPlayList;
	
	
	//this ObservableList contains the Music Libary Collection 
	private ObservableList<String> items = FXCollections.observableArrayList();
	
	//this ObservableList contains the Collections of  PlayLists
	private ObservableList<String> items2 = FXCollections.observableArrayList();
	//this observable list contains the contents of the selected playlist
	private ObservableList<String> items3 = FXCollections.observableArrayList();
	
	
	/*
	 * stops the currently playing mp3
	 */
	@FXML
	protected void stopSong() {
			p.stop();
			playPause.setText("Play");
			playing = "";
	}
	
	/*
	 * creates a new Playlist
	 */
	@FXML
	protected void makeList() {
		playListDialog();
	}
	
	/*
	 * Loops any playList that is Played
	 */
	@FXML
	protected void loopIt() {
		if(loop == false) {
			loop = true;
		}
		else {
			loop = false;
		}
	}
	
	/*
	 * adds a song to a playlist
	 */
	@FXML
	protected void addToPlayList() throws IOException {
		String listSelected = listOfPlayList.getSelectionModel().getSelectedItem();
		String songToAdd = addWindow.display(items);
		if(songToAdd != null) {
			playList tem = (playList) yourAlbums.get(listSelected);
			tem.Add(Album.get(songToAdd));
			FileManager.savePlayList(tem);
			items3.add(songToAdd);
		}
	}
	
	/*
	 * find and imports a mp3 file
	 */
	@FXML
	protected void findFile(ActionEvent event) throws UnsupportedTagException, InvalidDataException, IOException, ClassNotFoundException {
		/*
		 * retrives mp3 file from FileDialog then saves Mp3 to Mp3s and Song object to MusicLibary
		 */
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		File[] f = fd.getFiles();
		if(f.length > 1) {
			for(int x = 0; x<=f.length-1;x++){
				FileManager.saveSong(f[x]);
				Song toAdd = new Song(f[x]);
				Album.put(toAdd.getName(), toAdd);
			}
		}
		else {
			FileManager.saveSong(f[0]);
			Song toAdd = new Song(f[0]);
			Album.put(toAdd.getName(), toAdd);
		}
		
		/*
		 * reloads Libary
		 */
		lib = FileManager.loadLibary();
		items.clear();
		items.addAll(extractNames());
		
	}
	
	
	/*
	 * Play/pause button
	 */
	@FXML
	protected void playSong(ActionEvent event) {
		Media m = null;
		System.out.println(selectedSong);
		if(selectedSong == null) {
			
		}
		else {
			Song current = Album.get(selectedSong);
			File f = current.getFile();
			m = new Media(f.toURI().toString());
		}
		
		if(p == null) {
			p = new MediaPlayer(m);
			p.play();
			playPause.setText("pause");
			playing = selectedSong;
		}
		else {
			System.out.println(p.getMedia().equals(m));
			if(p.getStatus().equals(Status.PLAYING) && selectedSong == playing) {
				p.pause();
				playPause.setText("Resume");
			}
			else if(p.getStatus().equals(Status.PLAYING)) {
				p.stop();
				p = new MediaPlayer(m);
				p.play();
				playing = selectedSong;
			}
			else if(p.getStatus().equals(Status.PAUSED)) {
				p.play();
				playPause.setText("pause");
			}
			else if(p.getStatus().equals(Status.STOPPED)) {
				p = new MediaPlayer(m);
				p.play();
				playPause.setText("pause");
				
			}
				
		}

		
	}
	
	/*
	 * plays a complete playlist
	 */
	@FXML
	protected void playAll() {
		playAList(selected,0);
	}
	
	/*
	 * Shuffles the PlayLIst
	 */
	@FXML
	protected void shuffleIt() {
		playList toShuffle = (playList) yourAlbums.get(listOfPlayList.getSelectionModel().getSelectedItem());
		toShuffle.Shuffle();
	}
	
	/*
	 * Deletes the PlayList
	 */
	@FXML
	protected void deleteAList() {
		boolean delete = deleteConfirmation.display();
		if(delete == true) {
			String toDelete = listOfPlayList.getSelectionModel().getSelectedItem();
			FileManager.deletePlayList((playList) yourAlbums.get(toDelete));
			yourAlbums.remove(toDelete);
			items2.remove(toDelete);
		}
		
	}
	
	/*
	 * delete a Song from MusicLibary or playList
	 */
	@FXML
	protected void delete_s() throws IOException {
		Boolean delete = deleteConfirmation.display();
		if(delete == true) {
			if(listOfPlayList.getSelectionModel().getSelectedItem() == "Music Libary") {
				String deletesong = listOfSongs.getSelectionModel().getSelectedItem();
				
			}
			else {
				String toDelete = listOfPlayList.getSelectionModel().getSelectedItem();
				playList selectedList = (playList) yourAlbums.get(toDelete);
				selectedList.deleteFromList(Album.get(listOfSongs.getSelectionModel().getSelectedItem()));
				items3.remove(listOfSongs.getSelectionModel().getSelectedItem());
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		//checks the state of the app folders and builds if one is missing
		FileManager.buildApp();
		
		//loads the all saved song objects 
		try {
			lib = FileManager.loadLibary();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//builds the hash map of string to song objects with the song name used as the String key
		Album = FileManager.buildSongmap(lib);
		
		
		//sets items to the ListOfSongs ListView and extracts the names of all song objects to be used in items
		listOfSongs.setItems(items);
		
		//adds MusicLibary to list
		items2.add("Music Library");
		try {
			items.addAll(extractNames());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//loads the saved playlist
		try {
			yourPlayLists = FileManager.loadLists();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//builds the hashmpa for the playlists
		yourAlbums = FileManager.buildPlayListMap(yourPlayLists);
		
		// sets items 2 to the names of the play lists
		listOfPlayList.setItems(items2);
		
		try {
			items2.addAll(extractNames2());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * selection changed listener for listOfSongs
		 * 
		 */
		listOfSongs.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				// TODO Auto-generated method stub
				selectedSong = Album.get(arg2).getName();
			}
			
		});
		
		/*
		 * selections changed listner for listOfPlayList
		 */
		listOfPlayList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				items3.clear();
				selected = (playList) yourAlbums.get(newValue);
				if(newValue == "Music Library") {
					listOfSongs.setItems(items);
				}
				else {
					playList tem = (playList) yourAlbums.get(newValue);
					for(int x =0;x<=tem.getList().size()-1;x++) {
						Song t = (Song)tem.getList().get(x);
						items3.add(t.getName());
					}
					listOfSongs.setItems(items3);
				}
			}
			
		});
	}
	
	/*
	 * extracts the names of the songs in the musicLibary to display them in the list view
	 */
	private ArrayList extractNames() throws ClassNotFoundException, IOException {
		
		ArrayList<String> names = new ArrayList();
		for(int x =0;x<=lib.size()-1;x++) {
			names.add(lib.get(x).getName());
		}
		return names;
		}
	
	/*
	 * extracts the names of teh playlists
	 */
private ArrayList extractNames2() throws ClassNotFoundException, IOException {
		
		ArrayList<String> names = new ArrayList();
		for(int x =0;x<=yourPlayLists.size()-1;x++) {
			names.add(yourPlayLists.get(x).getName());
		}
		return names;
		}
	
	/*
	 * plays a playList
	 */
	private void playAList(playList List,int x) {
		System.out.println(loop+", "+x);
		//checks if x is out of bounds
		if(x > List.getList().size()-1) {
			if(loop == true) {
				playAList(List,0);
			}
		}
		
		
		else {
			//checks if the list is shuffled if it is it plays the contents of the shuffled list
			if(List.isIsshuffeled() == true) {
						Song tem = (Song) List.getShuffledList().get(x);
						System.out.println(tem.getName()+", "+ List.getList().size());
						p = new MediaPlayer(tem.getMedia());
						p.play();
						p.setOnEndOfMedia(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								p.stop();
								playAList(List,x+1);
							}
							
						});
				
			}
			//plays a non shuffled playlist
			else {
						Song tem = (Song) List.getList().get(x);
						p = new MediaPlayer(tem.getMedia());
						p.play();
						p.setOnEndOfMedia(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								p.stop();
								playAList(List,x+1);
							}
							
						});
			}
		}
		
	}
	
	
	/*
	 * 
	 */
	private void playListDialog() {
		playList newp = makeListWindoe.display();
		yourPlayLists.add(newp);
		yourAlbums.put(newp.getName(), newp);
		items2.add(newp.getName());
	}
	
}
