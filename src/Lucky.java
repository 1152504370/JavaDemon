import java.util.Scanner;

import org.omg.Messaging.SyncScopeHelper;

public class Lucky {
	static int sum = 0, bianHao = -1;
	static int[] shuzi = new int[10];
	static String[] account = new String[10];
	static String[] password = new String[10];
	static boolean flag = true, xinyun = false; // flag表示主方法中的循环条件默认为真xinyun代表幸运数字是否已经取值，避免一次运行程序出现多组幸运数字
	static int[] luckyNumber = new int[5];

	public static boolean choose() {
		Scanner input = new Scanner(System.in);
		System.out.print("继续吗？()Y/N):");
		String yn = input.next();
		if (yn.equalsIgnoreCase("Y"))
			return true;
		return false;
	}

	public static int showMenu() {
		System.out.println("*****欢迎进入奖客富翁系统*****");
		System.out.println("0：退出\r1:注册\r2:登录\r3:抽奖：");
		System.out.println("***************************");
		System.out.print("选择菜单:");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt(); // 输入一个数字
		return num; // 并返回到主方法用于选择模块功能
	}

	public static void regist() { // 用于实现注册的方法
		Scanner input = new Scanner(System.in);
		System.out.println("[奖客富翁系统>注册]");
		System.out.println("请填写个人注册信息：");
		System.out.print("用户名：");
		account[sum] = input.nextLine(); // 用字符串数组变量account来存储每个用户的账号
		if (sum > 0) { // 当sum>0即已存在注册用户时，判断用户名是否重复，是则认为该用户名无效,清空它并返回主菜单
			for (int i = 0; i < sum; i++) {
				if (account[i].equals(account[sum])) {
					System.out.println("该用户名已存在！本次注册无效，将返回主菜单！");
					return;
				}
			}
		}

		System.out.print("密码："); // 其中sum为默认的用户序号，也可以看作已注册用户数-1
		password[sum] = input.nextLine();
		shuzi[sum] = (int) ((int) 10 * Math.random()) + 1; // 随机生成当前用户的幸运数字用数组shuzi存储
		for (int i = 0; i < sum; i++) {
			if (shuzi[i] == shuzi[sum]) { // 如果当前用户卡号与之前用户卡号重复则重新选取卡号，直到不同为止
				shuzi[sum] = (int) ((int) 10 * Math.random()) + 1;
				i = 0;
			}

		}
		System.out.println("注册成功，请记好您的会员卡号：");
		System.out.println("用户名\t密码会员\t卡号");
		System.out.println(account[sum] + "   " + password[sum] + "   " + shuzi[sum]); // 其中sum为默认的用户序号，也可以看作已注册用户数-1
		sum++;
		if (!choose()) {
			flag = false;
		}

	}

	public static void login() { // 用于实现的登录方法
		System.out.println("[奖客富翁系统>登录]");
		Scanner input = new Scanner(System.in);
		bianHao = -1; // 用变量number来表示当前输入账户的编号，默认为-1（表示未输入或输入错误）
		while (bianHao < 0) { // 当number小宇0时即未输入账号或账号无效，循环进行输入
			System.out.print("请输入用户名：");
			String aAccount = input.nextLine(); // 用aAccount来记录当前输入的字符串
			for (int i = 0; i < account.length; i++) { // 循环匹配用aAccount去和已注册的账号比较，相同则得到对应的编号
				if (aAccount.equals(account[i]))
					bianHao = i;
			}
			if (bianHao < 0) {
				System.out.println("该用户名未注册！请重新输入有效用户名。"); // 当匹配结束后仍未找到相应的编号则认为当前输入账号无效
				if (!choose()) {
					bianHao = -1;
					return;
				}
			}
		} // 然后返回继续输入账号
		int count = 0; // count用于记录已输入密码次数，之后没输入一次即会加1
		while (count < 3) { // 密码输入循环次数最多为3
			if (count > 0) // 当count>0表示之前密码输入错误，现在处于再次输入阶段
				System.out.println("密码输入错误！请重新输入，您还有" + (3 - count) + "次机会。");// 剩余输入次数为3-count
			System.out.print("请输入密码：");
			String aPassword = input.nextLine();
			count++;
			if (aPassword.equals(password[bianHao])) { // 用输入的密码与对应编号的密码对比，相同则正确
				System.out.println("欢迎您：" + account[bianHao]);
				if (!choose()) {
					flag = false;
				}
				return; // 当抽奖方法执行完后回到这一行，通过return返回主方法
			}
		}
		System.out.println("输入密码错误三次，将返回主菜单！");
		bianHao = -1;
	}

	public static void choujiang(int bianHao) { // 用于抽奖的方法，调用形参为用户编号
		if (bianHao < 0) {
			System.out.println("您还未登录,或者登录信息已过期！无法抽奖！");
			return;
		} else {
			System.out.println("[奖客富翁系统>抽奖]");
			// 用数组luckyNumber存储5个幸运随机数
			System.out.println("用户" + account[bianHao] + "您的卡号为" + shuzi[bianHao]);// 输出用户的幸运数字
			System.out.print("本日的幸运数字为：");
			if (!xinyun) {
				for (int i = 0; i < luckyNumber.length; i++) { // 为了使抽到的5个随机数不是重复的特意使用循环判断
					luckyNumber[i] = (int) ((int) 10 * Math.random());// 如果之前的随机数与现在的冲突则重新选择当前随机数
					for (int j = 0; j < i; j++) {
						if (luckyNumber[j] == luckyNumber[i]) {
							luckyNumber[i] = (int) ((int) 10 * Math.random()) + 1;
							j = 0;
						}
					}
				}
				xinyun = true; // 在取得幸运数字后将xinyun
								// 改为true，之后再次调用则不会再次生成随机数，保证了同一天的幸运数字不会变
			}
			for (int j = 0; j < luckyNumber.length; j++) {
				System.out.print(luckyNumber[j] + "  "); // 输出所有的幸运随机数
			}
			for (int k = 0; k < luckyNumber.length; k++) { // 循环对比随机数如果有相等的则告知中奖，并直接
				if (shuzi[bianHao] == luckyNumber[k]) { // 跳出循环返回到load方法中
					System.out.println("\r恭喜您中奖了！！！");
					if (!choose())
						flag = false;
					return;

				}
			}
			System.out.println("\r抱歉！您不是本日的辛运会员！"); // 当遍历整个数组都没欧找到匹配项后输出未中奖并返回上一级方法
			if (!choose())
				flag = false;
			return;

		}
	}

	public static void main(String[] args) {
		flag = true; // 定义了一个boolean类型的变量，并使初值为true
//		System.out.println("Hello Kitty!!");
		while (flag) { // 将flag用作循环的判断依据，默认循环
			int choice = showMenu();
			switch (choice) {
			case 0:
				System.out.println("系统退出，谢谢使用!");
				flag = false; // 修改flag的值为false,使循环结束
				break;
			case 1:
				regist();
				break;
			case 2:
				login();
				break;
			case 3:
				choujiang(bianHao); // 密码正确直接调用抽奖方法，参数为用户编号
				bianHao = -1;
				break;
			default:
				System.out.println("输入错误！");
				break;
			}
		}
		System.out.println("系统退出，谢谢使用!");
	}
}
