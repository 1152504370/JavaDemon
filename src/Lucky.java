import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Lucky {
	static int sum = 0, bianHao = -1;
	static int[] shuzi = new int[10];
	static String[] account = new String[10];
	static String[] password = new String[10];
	static boolean flag = true, xinyun = false; // flag��ʾ�������е�ѭ������Ĭ��Ϊ��xinyun�������������Ƿ��Ѿ�ȡֵ������һ�����г�����ֶ�����������
	static int[] luckyNumber = new int[5];

	public static boolean choose() {
		Scanner input = new Scanner(System.in);
		System.out.print("������()Y/N):");
		String yn = input.next();
		if (yn.equalsIgnoreCase("Y"))
			return true;
		return false;
	}

	public static int showMenu() {
		System.out.println("*****��ӭ���뽱�͸���ϵͳ*****");
		System.out.println("0���˳�\r1:ע��\r2:��¼\r3:�齱��");
		System.out.println("***************************");
		System.out.print("ѡ��˵�:");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt(); // ����һ������
		return num; // �����ص�����������ѡ��ģ�鹦��
	}

	public static void regist() { // ����ʵ��ע��ķ���
		Scanner input = new Scanner(System.in);
		System.out.println("[���͸���ϵͳ>ע��]");
		System.out.println("����д����ע����Ϣ��");
		System.out.print("�û�����");
		account[sum] = input.nextLine(); // ���ַ����������account���洢ÿ���û����˺�
		if (sum > 0) { // ��sum>0���Ѵ���ע���û�ʱ���ж��û����Ƿ��ظ���������Ϊ���û�����Ч,��������������˵�
			for (int i = 0; i < sum; i++) {
				if (account[i].equals(account[sum])) {
					System.out.println("���û����Ѵ��ڣ�����ע����Ч�����������˵���");
					return;
				}
			}
		}

		System.out.print("���룺"); // ����sumΪĬ�ϵ��û���ţ�Ҳ���Կ�����ע���û���-1
		password[sum] = input.nextLine();
		shuzi[sum] = (int) ((int) 10 * Math.random()) + 1; // ������ɵ�ǰ�û�����������������shuzi�洢
		for (int i = 0; i < sum; i++) {
			if (shuzi[i] == shuzi[sum]) { // �����ǰ�û�������֮ǰ�û������ظ�������ѡȡ���ţ�ֱ����ͬΪֹ
				shuzi[sum] = (int) ((int) 10 * Math.random()) + 1;
				i = 0;
			}

		}
		System.out.println("ע��ɹ�����Ǻ����Ļ�Ա���ţ�");
		System.out.println("�û���\t�����Ա\t����");
		System.out.println(account[sum] + "   " + password[sum] + "   " + shuzi[sum]); // ����sumΪĬ�ϵ��û���ţ�Ҳ���Կ�����ע���û���-1
		sum++;
		if (!choose()) {
			flag = false;
		}

	}

	public static void login() { // ����ʵ�ֵĵ�¼����
		System.out.println("[���͸���ϵͳ>��¼]");
		Scanner input = new Scanner(System.in);
		bianHao = -1; // �ñ���number����ʾ��ǰ�����˻��ı�ţ�Ĭ��Ϊ-1����ʾδ������������
		while (bianHao < 0) { // ��numberС��0ʱ��δ�����˺Ż��˺���Ч��ѭ����������
			System.out.print("�������û�����");
			String aAccount = input.nextLine(); // ��aAccount����¼��ǰ������ַ���
			for (int i = 0; i < account.length; i++) { // ѭ��ƥ����aAccountȥ����ע����˺űȽϣ���ͬ��õ���Ӧ�ı��
				if (aAccount.equals(account[i]))
					bianHao = i;
			}
			if (bianHao < 0) {
				System.out.println("���û���δע�ᣡ������������Ч�û�����"); // ��ƥ���������δ�ҵ���Ӧ�ı������Ϊ��ǰ�����˺���Ч
				if (!choose()) {
					bianHao = -1;
					return;
				}
			}
		} // Ȼ�󷵻ؼ��������˺�
		int count = 0; // count���ڼ�¼���������������֮��û����һ�μ����1
		while (count < 3) { // ��������ѭ���������Ϊ3
			if (count > 0) // ��count>0��ʾ֮ǰ��������������ڴ����ٴ�����׶�
				System.out.println("��������������������룬������" + (3 - count) + "�λ��ᡣ");// ʣ���������Ϊ3-count
			System.out.print("���������룺");
			String aPassword = input.nextLine();
			count++;
			if (aPassword.equals(password[bianHao])) { // ��������������Ӧ��ŵ�����Աȣ���ͬ����ȷ
				System.out.println("��ӭ����" + account[bianHao]);
				if (!choose()) {
					flag = false;
				}
				return; // ���齱����ִ�����ص���һ�У�ͨ��return����������
			}
		}
		System.out.println("��������������Σ����������˵���");
		bianHao = -1;
	}

	public static void choujiang(int bianHao) { // ���ڳ齱�ķ����������β�Ϊ�û����
		if (bianHao < 0) {
			System.out.println("����δ��¼,���ߵ�¼��Ϣ�ѹ��ڣ��޷��齱��");
			return;
		} else {
			System.out.println("[���͸���ϵͳ>�齱]");
			// ������luckyNumber�洢5�����������
			System.out.println("�û�" + account[bianHao] + "���Ŀ���Ϊ" + shuzi[bianHao]);// ����û�����������
			System.out.print("���յ���������Ϊ��");
			if (!xinyun) {
				for (int i = 0; i < luckyNumber.length; i++) { // Ϊ��ʹ�鵽��5������������ظ�������ʹ��ѭ���ж�
					luckyNumber[i] = (int) ((int) 10 * Math.random());// ���֮ǰ������������ڵĳ�ͻ������ѡ��ǰ�����
					for (int j = 0; j < i; j++) {
						if (luckyNumber[j] == luckyNumber[i]) {
							luckyNumber[i] = (int) ((int) 10 * Math.random()) + 1;
							j = 0;
						}
					}
				}
				xinyun = true; // ��ȡ���������ֺ�xinyun
								// ��Ϊtrue��֮���ٴε����򲻻��ٴ��������������֤��ͬһ����������ֲ����
			}
			for (int j = 0; j < luckyNumber.length; j++) {
				System.out.print(luckyNumber[j] + "  "); // ������е����������
			}
			for (int k = 0; k < luckyNumber.length; k++) { // ѭ���Ա�������������ȵ����֪�н�����ֱ��
				if (shuzi[bianHao] == luckyNumber[k]) { // ����ѭ�����ص�load������
					System.out.println("\r��ϲ���н��ˣ�����");
					if (!choose())
						flag = false;
					return;

				}
			}
			System.out.println("\r��Ǹ�������Ǳ��յ����˻�Ա��"); // �������������鶼ûŷ�ҵ�ƥ��������δ�н���������һ������
			if (!choose())
				flag = false;
			return;

		}
	}

	public static void main(String[] args) {
		flag = true; // ������һ��boolean���͵ı�������ʹ��ֵΪtrue
//		System.out.println("Hello Kitty!!");
		while (flag) { // ��flag����ѭ�����ж����ݣ�Ĭ��ѭ��
			int choice = showMenu();
			switch (choice) {
			case 0:
				System.out.println("ϵͳ�˳���ллʹ��!");
				flag = false; // �޸�flag��ֵΪfalse,ʹѭ������
				break;
			case 1:
				regist();
				break;
			case 2:
				login();
				break;
			case 3:
				choujiang(bianHao); // ������ȷֱ�ӵ��ó齱����������Ϊ�û����
				bianHao = -1;
				break;
			default:
				System.out.println("�������");
				break;
			}
		}
		System.out.println("ϵͳ�˳���ллʹ��!");
	}
}
