package De2_Cau2_TCP.Exercise_Week1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CMD {
	private String request, cmd, param;
	private File defaultDir;
	private boolean quit;
	private BufferedReader userIn;

	public CMD(String path) {
		this.defaultDir = new File(path);
		this.userIn = new BufferedReader(new InputStreamReader(System.in));
		this.quit = false;

	}

	private String getPromt() {
		return defaultDir.getAbsolutePath() + "\\>";
	}

	private String execute(String req) {
		String res = "";
		request = req.trim();
		if (this.request.isEmpty())
			return res;
		StringTokenizer st = new StringTokenizer(request);
		cmd = st.nextToken().toUpperCase();
		param = "";
		if (st.hasMoreTokens())
			param += st.nextToken();
		switch (cmd) {
		case "CD":
			res = changeDir(param);
			break;
		case "DIR":
			res = displayDir();
			break;
		case "DELETE":
			res = deleteDir();
		case "EXIT":
			quit = true;
		default:
			break;
		}

		return res;
	}

	private String deleteDir() {
		String res = "";
		File[] listFile = defaultDir.listFiles();
		for (File file : listFile) {
			if (file.isDirectory()) {
				deleteDir();
			} else {
				file.delete();
			}
			
		}

		return res;
	}

	private String displayDir() {
		String res = "";
		List<String> listFile = new ArrayList<>();
		List<String> listDir = new ArrayList<>();
		File[] content = defaultDir.listFiles();
		for (File file : content) {
			if (file.isFile())
				listFile.add(file.getName());
			if (file.isDirectory())
				listDir.add(file.getName().toUpperCase());
		}
		for (String s : listDir)
			res += s + "\n";
		for (String s : listFile)
			res += s + "\n";
		return res;

	}

	private String changeDir(String param2) {
		String res = "";
		if (param2.equals("..")) {
			if (defaultDir.getParentFile() == null) {
				res = "Không chuyển được thư mục";
			} else {
				defaultDir = defaultDir.getParentFile();
			}
		} else {
			defaultDir = new File(defaultDir, param2);

		}

		return res;
	}

	public void run() throws IOException {
		while (!quit) {
			System.out.print(getPromt());
			String request = execute(userIn.readLine());
			System.out.println(request);
			if (request.equals(""))
				continue;

		}
	}

	public static void main(String[] args) throws IOException {
		String path = "D:\\Temp\\baitap1";
		CMD cmd = new CMD(path);
		// System.out.println(cmd.displayDir());
		// System.out.println(cmd.defaultDir.getName());
		cmd.run();
	}

}
