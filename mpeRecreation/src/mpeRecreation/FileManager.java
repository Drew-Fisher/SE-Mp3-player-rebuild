package mpeRecreation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileManager {
	
	
	
	
	
	
	public void buildApp() {
		
	}
	
	private void checkForLibary() {
		
	}
	
	private void checkFoulders() {
		
	}
	
	private void checkmp3Foulder() {
		
	}
	
	private void checkPlaylistFoulder() {
		
	}
	
	private void movetoDir(File f) throws IOException {	
		String loc = "./MusicLibary/"+f.getName();
		File m = new File(loc);
		Files.copy(f.toPath(),m.toPath());
	}
}
