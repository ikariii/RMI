import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("serial")
public abstract class StudentClient extends UnicastRemoteObject implements StudentInterface, Serializable {

	public StudentClient() throws RemoteException {

	}

	public static void main(String args[]) {

		try {
			// lookup for the object
			StudentInterface obj = (StudentInterface) Naming.lookup("rmi://localhost:12345/student_server");
			String read = "";
			ArrayList<Student> stu = new ArrayList<Student>();
			ArrayList<Student> stu1 = new ArrayList<Student>();
			while (read.equals("end") == false) {

				// call methods
				System.out.println("---------------------");
				System.out.println("Please choose the function:");
				System.out.println("1)Add exam");
				System.out.println("2)Print exams");
				System.out.println("3)Calculate average");
				System.out.println("Or input 'end' to end.");

				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				read = scan.nextLine();
				System.out.println("choose:" + read);

				if (read.equals("1")) {
					// call the method to add exam
					System.out.println("Read file add_exam.txt? Y/N");
					String read2 = scan.nextLine();

					// ensure to read file add_exam.txt
					if (read2.equals("Y")) {
						File file = new File("add_exam.txt");
						stu1 = readFile(file);
						stu = obj.add_exam(stu, stu1);
						System.out.println("The result of add_exam:");
						System.out.println("---------------------");
						for (int i = 0; i < stu.size(); i++) {
							System.out.println(stu.get(i).getSname() + ' ' + stu.get(i).getScore() + ' '
									+ stu.get(i).getCoefficient());
						}
						System.out.println("---------------------");
					}
				} else if (read.equals("2")) {
					// call method to print the exams in file
					String exams = obj.print_exams(stu);
					System.out.println(exams);
					printFile(exams, "print_exam.txt");
				} else if (read.equals("3")) {
					// call method to calculate average and print in file
					float avg = obj.calculate_average(stu);
					String Stravg = "The average is :" + avg;
					printFile(Stravg, "average.txt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// print file method
	public static void printFile(String str, String filename) throws Exception {

		FileWriter fw = null;
		fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str);
		bw.close();
		fw.close();
		System.out.println("Print Complete.");
	}

	// read file method
	public static ArrayList<Student> readFile(File file) throws Exception {

		ArrayList<Student> list = new ArrayList<Student>();
		@SuppressWarnings("resource")
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			Student stu = new Student();
			stu.setSname(input.next());
			stu.setScore(input.nextInt());
			stu.setCoefficient(input.nextInt());
			list.add(stu);
		}
		System.out.println("***Read File Success.***");
		return list;
	}
}
