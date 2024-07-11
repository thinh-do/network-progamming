package De2_Cau2_TCP;

import java.util.ArrayList;
import java.util.List;

public class ListStudent {
	List<Student> list = new ArrayList<>();

	public List<Student> importList() {
		Student st1 = new Student("Trần Tú Linh", "19/9/1998", 16161616, "Hà Nội");
		Student st2 = new Student("Phan Văn Đức", "15/4/2000", 20201232, "TP HCM");
		Student st3 = new Student("Đoàn Thị Lâm Oanh", "17/6/1998", 16141515, "An Giang");
		Student st4 = new Student("Trần Thị Thanh Thúy", "18/5/2001", 21020101, "Hải Phòng");
		Student st5 = new Student("Nguyễn Khánh Đang", "19/9/1999", 19817161, "Bình Định");
		Student st6 = new Student("Danh Quốc Toàn", "20/4/1999", 19876542, "Quảng Ngãi");
		Student st7 = new Student("Đặng Ngọc Thái", "30/4/2001", 21097642, "Cần Thơ");
		Student st8 = new Student("Danh Trường", "25/6/2003", 23415617, "Cà Mau");
		Student st9 = new Student("Nguyễn Thị Kim Thoa", "23/3/2003", 23415681, "Bình Dương");
		Student st10 = new Student("Nguyễn Đăng Quang", "15/6/2002", 22910129, "TP HCM");
		Student st11 = new Student("Lê Như Thảo", "09/8/2002", 22716252, "TP HCM");

		list.add(st11);
		list.add(st10);
		list.add(st9);
		list.add(st8);
		list.add(st7);
		list.add(st6);
		list.add(st5);
		list.add(st4);
		list.add(st4);
		list.add(st3);
		list.add(st2);
		list.add(st1);

		return list;

	}

	public void addStudent(Student st) {
		list.add(st);
	}

	public List<Student> findByYear(String year) {
		List<Student> listStudent = new ArrayList<>();
		for (Student student : list) {
			// String getBD = student.getdOB();
			String getYear = student.getdOB().substring(student.getdOB().length() - 4, student.getdOB().length());
			//System.out.println(getYear);
			if (getYear.equalsIgnoreCase(year))
				list.add(student);
		}
		return list;

	}

	public static void main(String[] args) {
		ListStudent list = new ListStudent();
		List<Student> res = list.findByYear("1998");
		for (Student student : res) {
			System.out.println(student.toString());
		}
//		String s = "18/6/1998";
//		String res = s.substring(s.length() - 4, s.length());
//		System.out.println(res);
	}
}
