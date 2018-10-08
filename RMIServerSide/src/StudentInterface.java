import java.util.ArrayList;

public interface StudentInterface extends java.rmi.Remote {

	public ArrayList<Student> add_exam(ArrayList<Student> stu, ArrayList<Student> stu1) throws java.rmi.RemoteException;

	public String print_exams(ArrayList<Student> stu) throws java.rmi.RemoteException;

	public float calculate_average(ArrayList<Student> stu) throws java.rmi.RemoteException;

}
