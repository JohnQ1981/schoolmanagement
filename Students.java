package schoolmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Students {

	private static int id = 1000;
	private String studentId;
	private String firstName;
	private String lastName;
	private int gradeLevel;
	private String courses="";
	private static int costOfCourse=600;
	private int tuitionBalance;
	private char fi;
	private char li;
	
	
	//Creating Unique
	public void setStudentId() {
		id++;
		
		this.studentId= fi+""+li+""+gradeLevel+""+id;		
		
		
	}
	
	public Students () {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter Student's First Name");
		this.firstName=scan.nextLine().trim();
		System.out.println("Enter Student's Last Name");
		this.lastName=scan.nextLine().trim();
		System.out.println("Enter Student's Grade Level");
		System.out.println("1- Freshmen");
		System.out.println("2- Sophomore");
		System.out.println("3- Junior");
		System.out.println("4- Senior");
		fi=this.firstName.charAt(0);
		li=this.lastName.charAt(0);
		this.gradeLevel=scan.nextInt();
		setStudentId();
		//System.out.println(this.studentId);
		//System.out.println(fi+"*"+li);
		
	}
	
	public void enroll()throws IOException {
		
		FileInputStream fis = new FileInputStream("src/schoolmanagement/courses.txt");
		
		do {
			Scanner scan = new Scanner(System.in);
			System.out.println("Enter Course to Enroll (Q to quit)");
			
			int k;
			while((k = fis.read())!= -1) {
				System.out.print((char)k);
				
				
			}
			//fis.close();
			String course = scan.nextLine().translateEscapes().toUpperCase();
			if(!course.equals("Q")) {
				courses = courses +"\n"+course;
				tuitionBalance=tuitionBalance+costOfCourse;
				
			}else {
				break;
			}
			
		}while (true);
		
		System.out.println("Enrolled in: "+ courses);
	}
	
	public void paytuition() {
		System.out.println("Your balance: $"+ tuitionBalance);
		Scanner scan= new Scanner(System.in);
		System.out.println("How much do you want to pay?");
		int payment= scan.nextInt();
		tuitionBalance= tuitionBalance-payment;
		System.out.println("Thank you for your payment of $"+payment+"!"+"\n Your new balance is: $"+ tuitionBalance);
		
	}
	
	public String toString() {
		
		return "STIUDENT ID: "+ studentId+
				"\nSTUDENT NAME: "+ firstName+" "+ lastName+
				"\nGRADE LEVEL: "+gradeLevel+
				"\nCOURSE ENROLLED: "+ courses+
				"\nREMAINING BALANCE: "+ tuitionBalance;
			}
	
	
		static Map <String, String> studentInfo= new HashMap<>();
		
		public static void addStudentsToMap() throws IOException {
			Scanner scan = new Scanner(System.in);
			String stop= "";
			while(!stop.equals("S")) {
				Students  s= new Students();
				s.enroll();
				s.paytuition();
				studentInfo.put(s.studentId, s.toString());
				System.out.println("Press 'S' to stop entrance");
				System.out.println("Press Enter to go to entrance");
				stop=scan.nextLine().trim();
							
			}
			selectOptions();
			
		}
		
		public static void getStudentInfo() throws IOException {
			Scanner scan = new Scanner(System.in);
			String idOfStudents = "";
			String result = "";
			do {
				System.out.println("Enter student id to get information from the list below ");


				for (String keys : studentInfo.keySet())  
				{
				   System.out.println(keys + ":"+ studentInfo.get(keys));
				}
				
				
				System.out.println("Enter 'X' to end the program");
				
				idOfStudents= scan.nextLine().trim();
				
				result = studentInfo.get(idOfStudents);
				
				if(!idOfStudents.equals("X")) {
					
					System.out.println(result);
					
				}
				
			}while(!idOfStudents.equals("X"));
			selectOptions();
		}
	
	
		public static void removeStudent() throws IOException {
			Scanner scan = new Scanner(System.in);
			String idOfStudents = "";
			String result = "";
			do {
				System.out.println("Enter student id to remove ");
				for (String keys : studentInfo.keySet())  
				{
				   System.out.println(keys + ":"+ studentInfo.get(keys));
				}
				System.out.println("Enter 'X' to end the program");
				idOfStudents= scan.nextLine().trim();
				result = studentInfo.remove(idOfStudents);
				
				if(!idOfStudents.equals("X")) {
					
					System.out.println(result);
					System.out.println("Removed from the Students List");
					
				}
				
			}while(!idOfStudents.equals("X"));
			selectOptions();
		}
		
		public static void selectOptions() throws IOException {
			System.out.println("Select the options");
			System.out.println("1: Add students");
			System.out.println("2: Get the Student's information by id");
			System.out.println("3: Remove student by id");
			Scanner scan = new Scanner(System.in);
					int option = scan.nextInt();
					switch(option) {
					case 1:
						addStudentsToMap();
						break;
					case 2:
						getStudentInfo();
						break;
					case 3:
						removeStudent();
						break;
					default:
						System.out.println("Plese make valid selection, select one of : 1, 2, or 3 !!!");
					}
				selectOptions();
		}
		
		
	
	
	
}
