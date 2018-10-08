import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class StudentServer extends UnicastRemoteObject implements StudentInterface, PromotionInterface, Serializable{

	private String msg;

	public StudentServer(String msg) throws java.rmi.RemoteException {
		super();
		this.msg = msg;
	}

	// to add exams:add ArrayList stu1 to ArrayList stu
	public ArrayList<Student> add_exam(ArrayList<Student> stu, ArrayList<Student> stu1)
			throws java.rmi.RemoteException {

		stu.addAll(stu1);
		System.out.println("Add exam success.");
		return stu;
	}

	// to print exams:read ArrayList stu and return the String
	public String print_exams(ArrayList<Student> stu) throws java.rmi.RemoteException {

		String str = "";
		for (int i = 0; i < stu.size(); i++) {
			str = str + "|" + stu.get(i).getSname();
			str = str + "," + stu.get(i).getScore();
			str = str + "," + stu.get(i).getCoefficient();
			// String.join(",",stu.get(i).getSname(),String.valueOf(stu.get(i).getScore()),String.valueOf(stu.get(i).getCoefficient()));
		}
		return str;
	}

	// to calculate the average:read the ArrayList stu and return the avg
	public float calculate_average(ArrayList<Student> stu) throws java.rmi.RemoteException {

		int sum = 0;
		float avg = 0;
		for (int i = 0; i < stu.size(); i++) {
			sum = sum + stu.get(i).getScore();
		}
		avg = sum / stu.size();
		return avg;

	}

	// to create a new student
	public ArrayList<Student> add_student(ArrayList<Student> stu, String student) throws java.rmi.RemoteException {

		Student newstudent = new Student();
		newstudent.setSname(student);
		newstudent.setScore(0);
		newstudent.setCoefficient(0);
		stu.add(newstudent);
		return stu;
	}

	// to calculate the average score of on given student
	public float get_student(ArrayList<Student> stu, String student) throws java.rmi.RemoteException {

		int sum = 0;
		float avg = 0;

		for (int i = 0; i < stu.size(); i++) {
			if (stu.get(i).getSname().equals(student))
				sum = sum + stu.get(i).getScore();
		}
		avg = sum / stu.size();
		return avg;
	}

	// to calculate the average score of all the promotion
	public float promotion_score(ArrayList<Student> stu) throws java.rmi.RemoteException {

		int sum = 0;
		float avg = 0;
		for (int i = 0; i < stu.size(); i++) {
			sum = sum + stu.get(i).getScore();
		}
		avg = sum / stu.size();
		return avg;

	}

	public static void main(String args[]) {

		try {
			// new instance HelloServeur
			StudentServer obj = new StudentServer("I'm the Student Serveur");

			// start RMIRegistry: port 12345
			// Alternative: start rmiregistry in terminal !
			java.rmi.registry.LocateRegistry.createRegistry(12345);
			// register the object
			Naming.rebind("rmi://localhost:12345/student_server", obj);
			System.out.println("StudentServer bound in registry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
