package Exercise_Week2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFile {
	public static void copy(String sf, String df) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(sf));
		OutputStream os = new BufferedOutputStream(new FileOutputStream(df));
		int data;
		while ((data = is.read()) != -1)
			os.write(data);
		is.close();
		os.close();
	}
	public static void main(String[] args) {
		String sf="";
		String df="";
	}

}
