package mpeRecreation;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

public class test {

	public static void main(String [] args) throws UnsupportedTagException, InvalidDataException, IOException {
//		Mp3File m = new Mp3File("C:\\Users\\Drew\\Desktop\\OscarVerdandi-master\\songs\\mine.mp3");
//		ID3v1 i = m.getId3v1Tag();
		Song s = new Song("C:\\Users\\Drew\\Desktop\\OscarVerdandi-master\\songs\\mine.mp3");
		System.out.println(s);
	}
}
