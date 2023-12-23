package CinemaManageSystem;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User{
	private static final String ADMIN_USERNAME = "admin";
    private static String ADMIN_PASSWORD = "ynuinfo#777";
    
    public List<User> users;
    public Admin(int ID,String username,String phoneNumber,String email) {
    	super(ID,ADMIN_PASSWORD, "管理员",phoneNumber,email);
    	users = new ArrayList<>();
    }
    // 登录
    public void login() {   
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入管理员用户名：");
        String username = scanner.nextLine();

        System.out.println("请输入管理员密码：");
        String password = scanner.nextLine();
        while(true){
        	if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
        		System.out.println("登录成功！");
        		showAdminMenu();
        		break;
        	} else {
        		System.out.println("用户名或密码错误，登录失败！");
        		System.out.println("请重新输入管理员用户名：");
                username = scanner.nextLine();

                System.out.println("请重新输入管理员密码：");
                password = scanner.nextLine();
        		continue;
        	}
        }
    }
    // 交互界面
    private void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("请选择你所要进行的操作:");
            System.out.println("1. 密码管理");
            System.out.println("2. 用户管理");
            System.out.println("3. 退出登录");
            int option = scanner.nextInt();
            scanner.nextLine(); // 消耗掉剩余的回车符
            switch (option) {
                case 1:
                    passwordManagement();
                    break;
                case 2:
                    userManagement();
                    break;
                case 3:
                    exit = true;
                    System.out.println("退出成功！");
                    break;
                default:
                    do{
                    	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                    	option = scanner.nextInt();
                    	if(option == 1) {
                    		changeAdminPassword();
                    	}else if(option == 2){
                    		resetUserPassword();
                    	}else if(option == 3){
                    		resetUserPassword();
                    	}
                    }while(option != 1 || option != 2 || option != 3);
            }
        }
		return;
    }
    // 密码管理
    public void passwordManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 修改自身密码");
        System.out.println("2. 重置指定影城方用户（经理、前台）的密码");
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                changeAdminPassword();
                break;
            case 2:
                resetUserPassword();
                break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		changeAdminPassword();
                	}else if(option == 2){
                		resetUserPassword();
                	}
                }while(option != 1 || option != 2);
        }
    }
    // 修改自身密码
    public void changeAdminPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入旧密码：");
        String oldPassword = scanner.nextLine();
        while(true) {
        	if (oldPassword.equals(ADMIN_PASSWORD)) {
        		System.out.println("请输入新密码：");
        		String newPassword = scanner.nextLine();

        		ADMIN_PASSWORD = newPassword;

        		System.out.println("密码修改成功！");
        		break;
        	} else {
        		System.out.println("旧密码错误，密码修改失败！");
        		System.out.println("请重新输入旧密码：");
        	    oldPassword = scanner.nextLine();
        	    continue;
        	}
        }
    }
    // 重置指定影城方用户的密码
    public void resetUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入所要指定重置的影城方用户的用户名:");
        String username = scanner.nextLine();

        User user = findUserByUsername(username);
        if (user != null) {
        	user.resetPassword();
        } else {
            System.out.println("用户不存在！");
            do{
            	scanner = new Scanner(System.in);
                System.out.println("请重新输入所要指定重置的影城方用户的用户名:");
                username = scanner.nextLine();
                user = findUserByUsername(username);
            }while(user == null);
        }
    }
    // 对影城用户管理
    public void userManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 列出所有影城方用户（经理、前台）信息");
        System.out.println("2. 删除影城方用户信息");
        System.out.println("3. 查询影城方用户信息");
        System.out.println("4. 增加影城方用户（经理、前台）信息");
        System.out.println("5. 修改影城方用户（经理、前台）信息");
        int option = scanner.nextInt();
        User user;
        switch (option) {
            case 1:
                listAllUsers();
                break;
            case 2:
                deleteUser();
                break;
            case 3:
                searchUser();
                break;
            case 4:
                addUser();
                break;
            case 5:
            	modifyUserInformation();
                break;
            default:
            	do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		listAllUsers();
                	}else if(option == 2){
                		deleteUser();
                	}else if(option == 3){
                		searchUser();
                	}else if(option == 4){
                		addUser();
                	}else if(option == 5){
                		modifyUserInformation();
                	}
                }while(option < 1 || option > 5);
        }
    }
    // 列出所有影城方用户（经理、前台）信息
    public void listAllUsers() {
        for (User user : users) {
            System.out.println("用户ID: " + user.getID());
            System.out.println("用户名: " + user.getUsername());
            System.out.println("用户注册时间: " + user.getRegistrationTime());
            System.out.println("用户类型（经理、前台）: " + user.getUserType());
            System.out.println("用户手机号: " + user.getPhoneNumber());
            System.out.println("用户邮箱: " + user.getEmail());
            System.out.println("------------------------");
        }
    }
    // 删除影城方用户
    public void deleteUser() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入所要指定删除的用户名：");
    	String username = scanner.nextLine();
    	User user = findUserByUsername(username);
        if (user != null) {
        	System.out.println("是否要删除该影城方的用户信息?(是或否)");
        	scanner = new Scanner(System.in);
        	String delete = scanner.nextLine();
        	if(delete == "是") {
        		users.remove(user);
        		System.out.println("该影城方用户删除成功！");
        	}else {
        		return;
        	}
        } else {
            System.out.println("该影城方用户不存在！");
        }
    }
    // 查询影城方用户信息
    public void searchUser() {
    	 Scanner scanner = new Scanner(System.in);
         System.out.println("请选择你所要进行的操作:");
         System.out.println("1. 根据用户ID查询");
         System.out.println("2. 根据用户的用户名查询");
         int option = scanner.nextInt();
         scanner.nextLine();
         while(true) {
	         if(option == 1) {
	             System.out.println("请输入用户的ID:");
	             int userId = scanner.nextInt();
	             displayUserInformation(userId);
	             break;
	         }
	         if(option == 2) {
	             System.out.println("请输入用户的用户名:");
	             String username = scanner.nextLine();
	             displayUserInformation(username);
	             break;
	         }
	         else {
	        	 System.out.println("无效的选择，请重新选择你所要进行的操作:");
	        	 option = scanner.nextInt();
	         }
         }
    }
    // 增加影城方用户的信息
    public void addUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入用户的ID:");
        int userId = Integer.parseInt(scanner.nextLine());
        while (findUserById(userId) != null) {
            System.out.println("用户ID已存在！");
            System.out.println("请重新输入用户的ID:");
            userId = Integer.parseInt(scanner.nextLine());
        }

        System.out.println("请输入用户的用户名:");
        String username = scanner.nextLine();
        while (findUserByUsername(username) != null) {
            System.out.println("用户名已存在！");
            System.out.println("请重新输入用户的用户名:");
            username = scanner.nextLine();
        }
        
        System.out.println("请输入用户的类型:");
        String userType = scanner.nextLine();

        System.out.println("请输入用户的手机号:");
        String phoneNumber = scanner.nextLine();
        while (findUserByPhoneNumber(phoneNumber) != null) {
            System.out.println("手机号已存在！");
            System.out.println("请重新输入用户的手机号:");
            phoneNumber = scanner.nextLine();
        }
      
        System.out.println("请输入用户的邮箱:");
        String email = scanner.nextLine();
        while (findUserByEmail(email) != null) {
            System.out.println("邮箱已存在！");
            System.out.println("请重新输入用户的邮箱:");
            email = scanner.nextLine();
        }

        User user = new User(userId, username, userType, phoneNumber, email);
        users.add(user);
        System.out.println("新的影城方用户添加成功！");
    }
    // 修改影城方用户信息
    public void modifyUserInformation() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请选择要修改的用户的用户名:");
    	String username = scanner.nextLine();
    	User user = findUserByUsername(username);
    	if(user == null) {
    		System.out.println("该用户不存在！");
    		return;
    	}
    	System.out.println("请选择要修改的信息:");
    	System.out.println("1. 用户类型（经理、前台）");
    	System.out.println("2. 用户手机号");
    	System.out.println("3. 用户邮箱");
    	System.out.println("4. 取消");

    	int option = scanner.nextInt();
    	scanner.nextLine(); // 消耗掉剩余的回车符
    	switch (option) {
    	     case 1:
    	         System.out.println("请输入新的用户类型(1.经理 2.前台):");
    	         int userType = scanner.nextInt();
    	         while(true) {
	    	         if (userType == 1) {
	    	             user.setUserType("经理");
	    	             break;
	    	         } else if (userType == 2) {
	    	        	 user.setUserType("前台");
	    	        	 break;
	    	         } else {
	    	        	 System.out.println("无效的用户类型，请重新输入新的用户类型:");
	    	        	 userType = scanner.nextInt();
	    	         }
    	         }
    	         break;
    	     case 2:
    	         System.out.println("请输入新的电话号码:");
    	         String phoneNumber = scanner.nextLine();
    	         user.setPhoneNumber(phoneNumber);
    	         break;
    	     case 3:
    	         System.out.println("请输入新的邮箱:");
    	         String email = scanner.nextLine();
    	         user.setEmail(email);
    	         break;
    	     case 4:
    	         return;
    	     default:
    	         do {
    	        	 System.out.println("无效的选择，请重新输入您的选择:");
    	        	 option = scanner.nextInt();
    	         }while(option < 1 || option > 4);
    	}
    	    System.out.println("用户的信息被成功修改！");
    } 
    // 将根据ID查找到的用户信息输出
    private void displayUserInformation(int userId) {
    	int count = 0;
        for (User user : users) {
                if (user.getID().equals(userId)) {
            	System.out.println("用户ID: " + user.getID());
                System.out.println("用户名: " + user.getUsername());
                System.out.println("用户注册时间: " + user.getRegistrationTime());
                System.out.println("用户类型（经理、前台）: " + user.getUserType());
                System.out.println("用户手机号: " + user.getPhoneNumber());
                System.out.println("用户邮箱: " + user.getEmail());
                System.out.println("------------------------");
                break;
            }
            count++;
        }
        if (count == users.size()) {
        	System.out.println("未找到该用户！");
        }
    }
    // 将根据用户名查找到的用户信息输出
    private void displayUserInformation(String username) {
    	int count = 0;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
            	System.out.println("用户ID: " + user.getID());
                System.out.println("用户名: " + user.getUsername());
                System.out.println("用户注册时间: " + user.getRegistrationTime());
                System.out.println("用户类型（经理、前台）: " + user.getUserType());
                System.out.println("用户手机号: " + user.getPhoneNumber());
                System.out.println("用户邮箱: " + user.getEmail());
                System.out.println("------------------------");
                break;
            }
            count++;
        }
        if (count == users.size()) {
        	System.out.println("未找到该用户！");
        }
    }
    // 通过ID来找到对应的用户
    private User findUserById(int id) {
        for (User user : users) {
            if (user.getID().equals(id)) {
                return user;
            }
        }
        return null;
    }
    // 通过用户名来找到对应的用户
    private User findUserByUsername(String username) {
        for (User user : users) {
        	if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
    // 通过电话号码来找到对应的用户
    private User findUserByPhoneNumber(String phoneNumber) {
        for (User user : users) {
            if (user.getPhoneNumber().equals(phoneNumber)) {
                return user;
            }
        }
        return null;
    }
    // 通过邮箱来找到对应的用户
    private User findUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}