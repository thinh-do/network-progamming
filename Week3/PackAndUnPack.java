package Week3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class PackAndUnPack {
	private static int HEAD_LINE_SIZE = 66;
	private static int NAME_LEN = 50;
	private static String CHARSET = "UTF-16";

	private List<File> getFileByExt(String folder, String ext) {
		List<File> res = new ArrayList<File>();
		File dir = new File(folder);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(ext))
				res.add(file);
		}
		return res;
	}

	private byte[] getByteName(String name) throws IOException {
		byte[] data = name.getBytes(CHARSET);
		byte[] res = new byte[NAME_LEN];
		int len = data.length > NAME_LEN ? NAME_LEN : data.length;
		System.arraycopy(data, 0, res, 0, len);
		return res;
	}

	public void pack(String folder, String ext, String archiveFile) throws IOException {
		List<File> files = getFileByExt(folder, ext);
		RandomAccessFile raf = new RandomAccessFile(archiveFile, "rw");
		// ghi số lượng
		raf.writeLong(files.size());// 8
		// header
		long pos = 8 + HEAD_LINE_SIZE * files.size();// 8 là hàm writeLong ghi 8 bytes
		for (File file : files) {
			raf.writeLong(pos);
			raf.write(getByteName(file.getName()));
			raf.writeLong(file.length());

			pos += file.length();
		}
		// content-data
		for (File file : files) {
			byte[] arr = new byte[1024];
			FileInputStream fis = new FileInputStream(file);
			int data;
			while ((data = fis.read(arr)) != -1) {
				raf.write(arr, 0, data);
			}
			fis.close();
		}

		raf.close();

	}

	private static void writeFile(OutputStream os, RandomAccessFile is, long partSize) throws IOException {
		byte[] buffer = new byte[102400];
		int byteRead;
		int byteMustRead;
		long remain = partSize;

		while (remain > 0) {
			byteMustRead = (int) (remain > buffer.length ? buffer.length : remain);
			byteRead = is.read(buffer, 0, byteMustRead);
			if (byteRead == -1)
				break;
			os.write(buffer, 0, byteRead);
			remain -= byteRead;
		}
	}

	public void unPack(String archive, String extractFile, String destFolder) throws IOException {
		RandomAccessFile raf = new RandomAccessFile(archive, "rw");
		long count = raf.readLong();
		for (long i = 0; i < count; i++) {
			long pos = raf.readLong();
			byte[] data = new byte[NAME_LEN];
			raf.read(data);
			String name = new String(data, CHARSET);
			int index = name.indexOf(0);
			if (index != -1)
				name = name.substring(0, index);
			long size = raf.readLong();
			if (name.equalsIgnoreCase(extractFile)) {// extract
				File file = new File(destFolder, extractFile);
				FileOutputStream fos = new FileOutputStream(file);
				raf.seek(pos);
				// su dung lai ham writeFile bai week2
				writeFile(fos, raf, size);
				fos.close();
				break;
			}

		}
		raf.close();
	}

	public String unPack1(String archive, String extractFile, String destFolder) throws IOException {
		String res = "File: " + extractFile + " khong ton tai";
		RandomAccessFile raf = new RandomAccessFile(archive, "rw");
		long count = raf.readLong();
		for (long i = 0; i < count; i++) {
			long pos = raf.readLong();
			byte[] data = new byte[NAME_LEN];
			raf.read(data);
			String name = new String(data, CHARSET);
			int index = name.indexOf(0);
			if (index != -1)
				name = name.substring(0, index);
			long size = raf.readLong();
			if (name.equalsIgnoreCase(extractFile)) {// extract
				File file = new File(destFolder, extractFile);
				FileOutputStream fos = new FileOutputStream(file);
				raf.seek(pos);
				// su dung lai ham writeFile bai week2
				// writeFile(fos, raf, size);
				for (int j = 0; j < size; j++) {
					fos.write(raf.read());
				}
				fos.close();
				res = "Extract file: " + extractFile + " thanh cong";
				break;
			}

		}
		raf.close();

		return res;
	}

	public static void main(String[] args) throws IOException {
		PackAndUnPack p = new PackAndUnPack();
		String folder = "D:\\Temp";
		String ext = ".docx";
		String archive = "D:\\Temp\\test.pack";
		// p.pack(folder, ext, archive);
		p.unPack(archive, "ABC.docx", "D:\\Temp\\baitap1");
//		System.out.println(p.unPack1(archive, "ABC.docx", "D:\\Temp\\baitap1"));

	}

}
