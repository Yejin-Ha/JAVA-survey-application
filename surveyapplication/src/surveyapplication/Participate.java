package surveyapplication;

import java.util.Scanner;

public class Participate {
	Scanner sc = new Scanner(System.in);

	public Participate() {
		System.out.println("회원만 가능한 서비스입니다. 로그인 / 회원가입을 진행해주세요.");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
	}

	public int login(String name, String pwd) {
		return new InfoDAO().login(name, pwd);
	}

	public boolean signUp(String name, String pswd, int age) {
		return new InfoDAO().signUp(name, pswd, age);
	}
}
