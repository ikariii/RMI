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
public abstract class PromotionClient extends UnicastRemoteObject implements PromotionInterface, Serializable{

	protected PromotionClient() throws RemoteException {
		
	}

	public static void main(String args[]) {

		try {
			// lookup for the object
			//PromotionInterface obj = (PromotionInterface) Naming.lookup("rmi://localhost:12345/student_server");
			PromotionInterface obj = (PromotionInterface) Naming.lookup("rmi://localhost:12345/student_server");
			String read = "";
			ArrayList<Student> stu = new ArrayList<Student>();
			
			while (read.equals("end") == false) {

				// call methods
				System.out.println("---------------------");
				System.out.println("Please choose the function:");
				System.out.println("1)Add student");
				System.out.println("2)Find a student");
				System.out.println("3)Calculate average");
				System.out.println("Or input 'end' to end.");

				@SuppressWarnings("resource")
				Scanner scan = new Scanner(System.in);
				read = scan.nextLine();
				System.out.println("choose:" + read);

				if (read.equals("1")) {
					//to create a new student(given the name)
					System.out.println("Read file add_student.txt? Y/N");
					String read2 = scan.nextLine();

					// ensure to read file add_student.txt
					if (read2.equals("Y")) {
						File file = new File("add_student.txt");
						String newstudent=null;
						newstudent = readFile(file);
						stu=obj.add_student(stu, newstudent);
					}
				} else if (read.equals("2")) {

					//to calculate average score of one given student
					File file = new File("get_student.txt");
					String name=null;
					name = readFile(file);
					float avg = obj.get_student(stu,name);
					String Stravg = "The average of this student :" + avg;
					printFile(Stravg, "get_student.txt");
				} else if (read.equals("3")) {

					//to calculate average score of all promotions
					float avg = obj.promotion_score(stu);
					String Stravg = "The average is :" + avg;
					printFile(Stravg, "promotion_score.txt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(File file) throws Exception {

		String name=null;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(file);
		while (input.hasNext()) {
			name=input.next();
		}
		System.out.println("***Read File Success.***");
		return name;
	}
	
	public static void printFile(String str, String filename) throws Exception {

		FileWriter fw = null;
		fw = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(str);
		bw.close();
		fw.close();
		System.out.println("Print Complete.");
	}

	@Override
	public ArrayList<Student> add_student(ArrayList<Student> stu, String student) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float get_student(ArrayList<Student> stu, String name) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float promotion_score(ArrayList<Student> stu) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
