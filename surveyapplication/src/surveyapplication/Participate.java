package surveyapplication;

import java.util.Scanner;

public class Participate {
	Scanner sc = new Scanner(System.in);

	public Participate() {
		System.out.println("ȸ���� ������ �����Դϴ�. �α��� / ȸ�������� �������ּ���.");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
	}

	public int login(String name, String pwd) {
		return new InfoDAO().login(name, pwd);
	}

	public boolean signUp(String name, String pswd, int age) {
		return new InfoDAO().signUp(name, pswd, age);
	}
}
