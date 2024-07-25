package Week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Lab3 {
	public void pack(String folder, String ext, String archive) throws IOException {
		File sFile = new File(folder);
		File[] listFile = sFile.listFiles();
		List<File> list = new ArrayList<File>();

		for (File file : listFile) {
			if (file.getName().endsWith(ext)) {
				list.add(file);
			}
		}
		RandomAccessFile rafOut = new RandomAccessFile(archive, "rw");
		rafOut.writeInt(list.size());
		byte[] byte50 = new byte[50];
		int position = 0;
		for (File file : list) {
			rafOut.writeLong(position);
			String name = file.getName();
			byte50 = name.getBytes();
			rafOut.writeUTF(name);
			rafOut.writeLong(file.length());
			RandomAccessFile rafIn = new RandomAccessFile(file, "rw");
			byte[] arr = new byte[1024];
			int data;
			while ((data = rafIn.read(arr)) != -1) {
				rafOut.write(data);
			}
			position += file.length();
		}
		rafOut.close();

	}

	public void unPack(String archive, String extractFile, String destFolder) throws IOException {
		RandomAccessFile rafIn = new RandomAccessFile(archive, "rw");
		rafIn.seek(0);
		int count = rafIn.readInt();
		for (int i = 0; i < count; i++) {
			long position = rafIn.readLong();
			String name = rafIn.readUTF();
			if (name.equalsIgnoreCase(extractFile)) {
				rafIn.seek(position);
				RandomAccessFile rafOut=new RandomAccessFile(destFolder, "rw");
				
				
			}

		}

	}

	public static void main(String[] args) throws IOException {
		Lab3 lab = new Lab3();
		String folder = "D:\\Temp";
		String ext = "docx";
		String archive = "D:\\Temp\\pack.rar";
		lab.pack(folder, ext, archive);
	}

}
