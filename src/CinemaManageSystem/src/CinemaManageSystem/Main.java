package CinemaManageSystem;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Admin admin = new Admin(123456789, "admin", "18074236591", "admin@ynu.com");
		Manager manager1 = new Manager(0,"刘经理","ynu_liu","13569405132","liujingli@yun.com");
        Receptionist receptionist1 = new Receptionist(0,"杨前台","ynu_yang","15698406175","yangqiantai@ynu.com");
        admin.users.add(manager1);
        admin.managers.add(manager1);
        admin.users.add(receptionist1);
        admin.receptionists.add(receptionist1);
        
		Manager manager = new Manager(0,null,null,null,null);
		Receptionist receptionist = new Receptionist(0,null,null,null,null);
		Customer customer = new Customer(0,null,null,null,null,"铜牌用户",0,0);
		
		Scanner scanner = new Scanner(System.in);
		boolean over = true;
		while(over) {
			System.out.println("请输入要进行操作的类型:(输入0则结束整个程序)");
			System.out.println("1.管理员");
			System.out.println("2.经理");
			System.out.println("3.前台");
			System.out.println("4.用户");
			int userType = scanner.nextInt();
			scanner.nextLine(); // 消耗掉剩余的回车符
			switch (userType){
				case 1:
					// 管理员登录
					admin.login();
					break;
				case 2:
		        	// 经理交互界面
					System.out.println("请输入要登录的经理的用户名: ");
					String username = scanner.nextLine();
					for(Manager manager_ : admin.managers) {
						if(manager_.getUsername().equals(username)) {
							manager = manager_;
							manager.showManagerMenu(admin);
						}
					}
		            break;
		        case 3:
		        	// 前台交互界面
					System.out.println("请输入要登录的前台的用户名: ");
					String userName = scanner.nextLine();
					for(Receptionist receptionist_ : admin.receptionists) {
						if(receptionist_.getUsername().equals(userName)) {
							receptionist = receptionist_;
							receptionist_.showReceptionistMenu(admin,manager);
						}
					}
		            break;
		        case 4:
		        	// 用户的交互界面
		        	System.out.println("您是否已有账号？（是/否）");
		        	String choice = scanner.nextLine();
		        	if(choice == "是") {
		        		System.out.println("请输入要进行登录的用户名: ");
						String UserName = scanner.nextLine();
			        	for(Customer customer_ : manager.customers) {
							if(customer_.getUsername().equals(UserName)) {
								customer = customer_;
								customer.login(manager);
							}
						}
		        	}else {
		        		customer.showCustomerMenu(manager);
		        	}
		            break;
				default:
					over = false;
					System.out.println("-----------结束-----------");
					break;
			}
		}
	}
}