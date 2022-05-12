package surveyapplication;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

//import java.
public class Test {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("☆☆☆☆환영합니다☆☆☆☆");

		while (true) {
			System.out.println("1. 설문 참여하기");
			System.out.println("2. 설문 현황 확인");
			System.out.println("0. 종료");
			int menu = -1;
			try {
				menu = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException e) {
				sc.nextLine();
				System.out.println("『숫자를 입력하세요.』");
				continue;
			}

			if (menu == 0)
				break;

			switch (menu) {
			case 1:
				InfoDao iDao = new InfoDao();
				int infoNumber;
				int subMenu;
				try {
					subMenu = sc.nextInt();
					sc.nextLine();
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("『숫자를 입력하세요.』");
					continue;
				}

				if (subMenu == 1) {
					System.out.print("이름 : ");
					String name = sc.nextLine();
					System.out.print("비밀번호: ");
					String pwd = sc.nextLine();
					infoNumber = iDao.login(name, pwd);
					if (infoNumber == 0) {
						System.out.println("『정보를 잘못 입력하였습니다. 처음부터 다시 실행합니다.』");
						break;
					}
					voteBrand(infoNumber);

				} else if (subMenu == 2) {
					System.out.print("이름 : ");
					String name = sc.nextLine();
					System.out.print("비밀번호: ");
					String pwd = sc.nextLine();
					System.out.print("나이: ");
					int age = sc.nextInt();
					if (iDao.signUp(name, pwd, age))
						infoNumber = iDao.login(name, pwd);
					else {
						System.out.println("『동일한 정보의 회원이 존재합니다. 처음부터 다시 실행합니다.』");
						break;
					}
					voteBrand(infoNumber);
				} else {
					System.out.println("『메뉴 번호를 잘못 입력하였습니다. 처음부터 다시 실행합니다.』");
					break;
				}
				break;
			case 2:
				VoteDao vDao = new VoteDao();
				int voteMenu;
				try {
					voteMenu = sc.nextInt();
					sc.nextLine();
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("『숫자를 입력하세요.』");
					continue;
				}

				if (voteMenu == 1)
					vDao.selectAll();
				else if (voteMenu == 2) {
					vDao.selectAge();
				} else
					System.out.println("『메뉴 번호를 잘못 입력하였습니다. 처음부터 다시 실행합니다.』");
				break;
			default:
				System.out.println("『입력한 메뉴는 존재하지 않습니다. 다시 입력하세요.』");
			}
		}
	}

	public static void voteBrand(int infoNumber) {
		BrandDao bDao = new BrandDao();
		ArrayList<BrandVo> ls = bDao.selectAll();
		for (BrandVo b : ls) {
			System.out.println(b);
		}
		boolean flag = false;
		do {
			int select = sc.nextInt();
			sc.nextLine();
			if (select == 0) {
				System.out.print("새로운 브랜드명을 입력하세요: ");
				String brand = sc.nextLine();
				flag = bDao.insertBrand(infoNumber, brand);
			} else {
				flag = bDao.selectBrand(infoNumber, select);
			}
		} while (!flag);
		System.out.println("『투표하였습니다.』");
	}

	public static void checkInputMisMatch(Object o) {

	}
}
