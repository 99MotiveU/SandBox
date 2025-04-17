/*
 * �л������������α׷�(ver 0.20.0)
 * �л��ο� ���� ����
 * ������ ����, ����, ����, 3�� ����
 * �̵��� �հ�� ��� �׸��� ��ũ�� ���(��������) *
 * �й��� 20250001���� �ڵ� �ο� *
 * �ߺ��� �й��� �������� ������ 
 * ���_���� ����
 * ��ŷ_�հ� ��� ���� ��� *����
 * �׿� ���� ���� ����
 * ex) 1.��� 2.��ŷ 3.�Է� 4.���� 5.���� 0.����> 
 * 
 * ������ �̸��Ϸ�
 *  ���� : 1.�ڹ�_ȫ�浿.java
 *  ÷�� : ���޴��� ������ ��� �̹����� �ҽ�����
 * */

package test01;

import java.util.*;

class Stu implements Comparable<Stu> {
	int num;
	int kor;
	int eng;
	int math;
	int tot;
	int avg;
	int rank;

	@Override
	public int compareTo(Stu ord) {
		return ord.num - this.num;
	}
}

public class test01 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean boo = true;
		String[] arr = { "�й�", "����", "����", "����" };
		Set<Stu> data = new TreeSet<>();

		System.out.println("�л������������α׷� (ver 0.20.0)");
		
		while (boo) {
			System.out.println();
			System.out.print("1.��� 2.��ŷ 3.�Է� 4.���� 5.���� 0.����> ");
			
			String choice = sc.nextLine();

			if (choice.equals("0")) {// ����
				System.out.println("���α׷��� �����մϴ�. \t �̿��� �ּż� �����մϴ�.");
				boo = false;
				break;
			}

			
			if (choice.equals("1")) {// ���
				System.out.println("------------------------------------");
				System.out.println("�й�\t����\t����\t����");
				System.out.println("------------------------------------");
				Iterator<Stu> ite = data.iterator();
				while (ite.hasNext()) {
					Stu stu = ite.next();
					System.out.println(String.format("%d\t%d\t%d\t%d", stu.num, stu.kor, stu.eng, stu.math));
				}
			}

			
			if (choice.equals("2")) {// ��ŷ
				System.out.println("------------------------------------");
				System.out.println("�հ�\t���\t����");
				System.out.println("------------------------------------");
				System.out.println();
				Iterator<Stu> ite = data.iterator();
				while(ite.hasNext()) {
					Stu stu = ite.next();
					System.out.println(String.format("%d\t%f\t%d", stu.tot, stu.avg, stu.rank));
				}
			}

			
			if (choice.equals("3")) { // �Է�
				Stu stu = new Stu();
				System.out.print(arr[0] + "> ");
				stu.num = sc.nextInt();

				boolean exists = false;
				Iterator<Stu> ite = data.iterator();
				while (ite.hasNext()) {
					if (ite.next().num == stu.num) {
						exists = true;
						break;
					}
				}

				if (exists) {
					System.out.println("�����ϴ� �л��Դϴ�.");
				} else {
					System.out.print(arr[1] + "> ");
					stu.kor = sc.nextInt();
					System.out.print(arr[2] + "> ");
					stu.eng = sc.nextInt();
					System.out.print(arr[3] + "> ");
					stu.math = sc.nextInt();
					stu.tot = stu.kor+stu.eng+stu.math;
					stu.avg = stu.tot/3;
					data.add(stu);
					System.out.println("�Է¿Ϸ�");
				}
			}
			

			if (choice.equals("4")) { // ����
				System.out.print("������ �л��� �й� �Է�> ");
				int num = sc.nextInt();

				Iterator<Stu> ite = data.iterator();
				while (ite.hasNext()) {
					Stu stu = ite.next();
					if (stu.num == num) {
						System.out.println(arr[1] + "> ");
						stu.kor = sc.nextInt();
						System.out.println(arr[2] + "> ");
						stu.eng = sc.nextInt();
						System.out.println(arr[3] + "> ");
						stu.math = sc.nextInt();
						System.out.println("���� �Ϸ�.");
						break;
					}
				}
			}
			

			if (choice.equals("5")) { // ����
				System.out.print("������ �л��� �й� �Է�> ");
				int num = sc.nextInt();

				Iterator<Stu> ite = data.iterator();
				while (ite.hasNext()) {
					if (ite.next().num == num) {
						ite.remove();
						System.out.println("���� �Ϸ�.");
						break;
					}
				}
			}
			
			
		}
	}

}
