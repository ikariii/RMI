import java.util.ArrayList;

public interface PromotionInterface extends java.rmi.Remote {

	public ArrayList<Student> add_student(ArrayList<Student> stu, String student) throws java.rmi.RemoteException;

	public float get_student(ArrayList<Student> stu, String name) throws java.rmi.RemoteException;

	public float promotion_score(ArrayList<Student> stu) throws java.rmi.RemoteException;
}
