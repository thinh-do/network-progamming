package Exercise_Week2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Week2 {
	private static final int bufferSize = 102400;

	public void splitFile(File source, int partSize) throws IOException {

		long fSize = source.length();
		int quantity = (int) (fSize / partSize);
		int remain = (int) (fSize % partSize);
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
		for (int i = 1; i <= quantity; i++) {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(source.getAbsolutePath() + "." + expand(i + "")));
			writeFile(bos, bis, partSize);
			bos.flush();
			bos.close();

		}
		if (remain > 0) {
			BufferedOutputStream bos = new BufferedOutputStream(
					new FileOutputStream(source.getAbsolutePath() + "." + expand(quantity + 1 + "")));
			writeFile(bos, bis, remain);
			bos.flush();
			bos.close();
		}

	}

	private static boolean writeFile(BufferedOutputStream os, BufferedInputStream is, int partSize) throws IOException {
		byte[] buffer = new byte[bufferSize];
		int byteread;
		int remain = partSize;
		int byteMustRead;

		while (remain > 0) {
			byteMustRead = remain > bufferSize ? bufferSize : remain;
			byteread = is.read(buffer, 0, byteMustRead);
			if (byteread == -1)
				return false;
			os.write(buffer, 0, byteread);
			remain -= byteread;
		}

		// int data;
//		for (int i = 0; i < partSize; i++)
//			
//			
////			data = is.read();
////			if (data == -1) {
////				return false;
////			} else
//			os.write(is.read());

//
//		}
		return true;
	}

	private static String expand(String s) {

		if (s.length() >= 2)
			return "0" + s;

		return "00" + s;

	}

	public boolean splitFile(String path, long size) throws IOException {
		File sf = new File(path);
		long fsize = sf.length();
		long count = fsize / size;
		long remain = fsize % size;
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(sf));
		for (int i = 1; i <= count; i++) {
			String destFile = sf + "." + extend(count);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
			writeFile(size, bis, bos);
			bos.flush();
			bos.close();
		}
		if (remain > 0) {
			String destFile = sf + "." + extend(count + 1);
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFile));
			writeFile(remain, bis, bos);
			bos.flush();
			bos.close();

		}

		return true;
	}

	private void writeFile(long size, BufferedInputStream bis, BufferedOutputStream bos) throws IOException {

		for (int i = 0; i < size; i++)

			bos.write(bis.read());

	}

	private String extend(long count) {

		String s = "" + count;
		System.out.println(s.length());
		while (s.length() < 3) {
			s = "0" + s;
		}
		return s;
	}

	private String getDestFileName(String partFile) {
		String res = "";
		File file = new File(partFile);
		String name = file.getName();
		res = name.substring(0, name.lastIndexOf("."));

		return res;
	}

	public void join(String partFile, String destFolder) throws IOException {
		File destFile = new File(destFolder, getDestFileName(partFile));
		FileOutputStream fos = new FileOutputStream(destFile);
		BufferedOutputStream bos = new BufferedOutputStream(fos);
		String source;
		String part = partFile.substring(0, partFile.lastIndexOf("."));
		int fileNo = 1;
		while (true) {
			source = part + expand(fileNo + "");
			File sfile = new File(source);
			if (!sfile.exists())
				break;
			InputStream is = new FileInputStream(sfile);
			BufferedInputStream bis = new BufferedInputStream(is);
			// writeFile(bis, bos, (int)sfile.length());
			writeFile(bos, bis, (int) sfile.length());
			is.close();
			fileNo++;
		}
		fos.close();

	}

	public static void main(String[] args) throws IOException {
		Week2 w = new Week2();
		File source = new File("D:\\Temp\\QuanTri.pptx");
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(source));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(source.getAbsolutePath() + "001"));
		// writeFile(bos, bis, 6500 * 1024);

		w.splitFile(source, 6500 * 1024);

	}
}
