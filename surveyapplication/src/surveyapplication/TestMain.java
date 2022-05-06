package surveyapplication;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		User user = new User();
		Scanner sc = new Scanner(System.in);

		System.out.println("name");
		String name = sc.nextLine();

		System.out.println("pswd");
		String pswd = sc.nextLine();

		System.out.println("age");
		int age = sc.nextInt();
		sc.nextLine();
		
		System.out.println(user.login(name, pswd));
		System.out.println(user.signUp(name, pswd, age));
	}
}
