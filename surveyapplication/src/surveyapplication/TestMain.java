package surveyapplication;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("ȯ��");
		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.println("1. ���� �����ϱ�");
			System.out.println("2. ���� ��Ȳ Ȯ��");
			System.out.println("0. ����");
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
					System.out.print("�̸� : ");
					String name = sc.nextLine();
					System.out.print("��й�ȣ: ");
					String pwd = sc.nextLine();
					infoNumber = p.login(name, pwd);
				} else if (subMenu == 2) {
					System.out.print("�̸� : ");
					String name = sc.nextLine();
					System.out.print("��й�ȣ: ");
					String pwd = sc.nextLine();
					System.out.print("����: ");
					int age = sc.nextInt();
					if (p.signUp(name, pwd, age))
						infoNumber = p.login(name, pwd);
					else {
						System.out.println("�̹� ���� ȸ���� �����մϴ�.");
						break;
					}
				} else {
					System.out.println("���ڸ� �߸� �Է��Ͽ����ϴ�. ó������ �ٽ� �����մϴ�.");
					break;
				}
				BrandDAO bDao = new BrandDAO();
				int select = sc.nextInt();
				sc.nextLine();
				if (select == 0) {
					System.out.print("���ο� �귣����� �Է��ϼ���: ");
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
