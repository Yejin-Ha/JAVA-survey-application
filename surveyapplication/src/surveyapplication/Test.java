package surveyapplication;

import java.util.InputMismatchException;
import java.util.Scanner;

//import java.
public class Test {
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("�١١١�ȯ���մϴ١١١١�");

		while (true) {
			System.out.println("1. ���� �����ϱ�");
			System.out.println("2. ���� ��Ȳ Ȯ��");
			System.out.println("0. ����");
			int menu = -1;
			try {
				menu = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("���ڸ� �Է��ϼ���.");
				continue;
			} finally {
				sc.nextLine();
			}

			if (menu == 0)
				break;

			switch (menu) {
			case 1:
				InfoDAO iDao = new InfoDAO();
				int infoNumber;
				int subMenu = sc.nextInt();
				sc.nextLine();
				if (subMenu == 1) {
					System.out.print("�̸� : ");
					String name = sc.nextLine();
					System.out.print("��й�ȣ: ");
					String pwd = sc.nextLine();
					infoNumber = iDao.login(name, pwd);
					if (infoNumber == 0) {
						System.out.println("������ �߸� �Է��Ͽ����ϴ�. ó������ �ٽ� �����մϴ�.");
						break;
					}
					voteBrand(infoNumber);

				} else if (subMenu == 2) {
					System.out.print("�̸� : ");
					String name = sc.nextLine();
					System.out.print("��й�ȣ: ");
					String pwd = sc.nextLine();
					System.out.print("����: ");
					int age = sc.nextInt();
					if (iDao.signUp(name, pwd, age))
						infoNumber = iDao.login(name, pwd);
					else {
						System.out.println("������ ������ ȸ���� �����մϴ�. ó������ �ٽ� �����մϴ�.");
						break;
					}
					voteBrand(infoNumber);
				} else {
					System.out.println("�޴� ��ȣ�� �߸� �Է��Ͽ����ϴ�. ó������ �ٽ� �����մϴ�.");
					break;
				}
				break;
			case 2:
				VoteDAO vDao = new VoteDAO();
				int voteMenu = sc.nextInt();
				sc.nextLine();
				if (voteMenu == 1)
					vDao.selectAll();
				else if (voteMenu == 2) {
					vDao.selectAge();
				} else
					System.out.println("�޴� ��ȣ�� �߸� �Է��Ͽ����ϴ�. ó������ �ٽ� �����մϴ�.");
				break;
			default:
				System.out.println("�Է��� �޴��� �������� �ʽ��ϴ�. �ٽ� �Է��ϼ���.");
			}
		}
	}

	public static void voteBrand(int infoNumber) {
		BrandDAO bDao = new BrandDAO();
		boolean flag = false;
		do {
			int select = sc.nextInt();
			sc.nextLine();
			if (select == 0) {
				System.out.print("���ο� �귣����� �Է��ϼ���: ");
				String brand = sc.nextLine();
				flag = bDao.insertBrand(infoNumber, brand);
			} else {
				flag = bDao.selectBrand(infoNumber, select);
			}
		} while (!flag);
	}
}
