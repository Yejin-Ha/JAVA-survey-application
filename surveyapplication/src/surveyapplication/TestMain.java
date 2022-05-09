package surveyapplication;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("환영");
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("1. 설문 참여하기");
			System.out.println("2. 설문 현황 확인");
			System.out.println("0. 종료");
			int menu = sc.nextInt();
			sc.nextLine();

			switch (menu) {
			case 0:
				break;
			case 1:
				Participate p = new Participate();
				int infoNumber;
				int subMenu = sc.nextInt();
				sc.nextLine();
				if (subMenu == 1) {
					System.out.print("이름 : ");
					String name = sc.nextLine();
					System.out.print("비밀번호: ");
					String pwd = sc.nextLine();
					infoNumber = p.login(name, pwd);
				} else if (subMenu == 2) {
					System.out.print("이름 : ");
					String name = sc.nextLine();
					System.out.print("비밀번호: ");
					String pwd = sc.nextLine();
					System.out.print("나이: ");
					int age = sc.nextInt();
					if (p.signUp(name, pwd, age))
						infoNumber = p.login(name, pwd);
					else {
						System.out.println("이미 같은 회원이 존재합니다.");
						break;
					}
				} else {
					System.out.println("숫자를 잘못 입력하였습니다. 처음부터 다시 실행합니다.");
					break;
				}
				BrandDAO bDao = new BrandDAO();
				int select = sc.nextInt();
				sc.nextLine();
				if (select == 0) {
					System.out.print("새로운 브랜드명을 입력하세요: ");
					String brand = sc.nextLine();
					bDao.insertBrand(brand);
				} else {
					bDao.selectBrand(infoNumber, select);
				}
				break;
			case 2:
			}
			if (menu == 0)
				break;
		}
	}
}
