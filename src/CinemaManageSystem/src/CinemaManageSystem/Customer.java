package CinemaManageSystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Customer extends User{
        private String username;   // 用户名
        private String password;   // 密码
        private String rank;       // 用户等级
        private double accuamount; // 用户累计消费总金额
        private int accucount;     // 用户累计消费次数
        private Movie movie;
        private static List<Ticket> PurchaseHistory = new ArrayList<>();
        private boolean locked;
        private static int userCount = 0;
        private static final int MAX_LOGIN_ATTEMPTS = 5;  // 密码连续输入错误5次就锁定账户
        private static final int LOCKED_OUT_TIME = 5;    // 锁定账户的时长（分钟）
        static final double count_Gold = 0.88;
        static final double count_Silver = 0.95;
        static final double count_Copper = 1;
        
        public Customer(int ID,String username,String phoneNumber,String email,String password,String rank,double accuamount,int accucount) {
        	super(ID,username,password,"消费者", phoneNumber,email);
            this.rank = rank;
            this.accuamount = accuamount;
            this.accucount = accucount;
        }

        public void setRank(String rank) { this.rank = rank; }
        public String getRank() { return rank; }
        public void setAccuamount(double accuamount) { this.accuamount = accuamount; }
        public Double getAccuamount() { return accuamount; }
        public void setAccucount(int accucount) { this.accucount = accucount; }
        public Integer getAccucount() { return accucount; }
        public boolean isLocked() { 
        	return locked; 
        }
        public void lock() { 
        	locked = true; 
        }  
        public void unlock() { 
        	locked = false; 
        }
        // 交互界面
        public void showCustomerMenu(Manager manager) {
        	Scanner scanner = new Scanner(System.in);
            boolean loggedIn = false;
            Customer loggedInUser = null;
            boolean exit = false;
            
	        while (!exit) {
	            if (!loggedIn) {
	                System.out.println("1. 注册");
	                System.out.println("2. 登录");
	                System.out.println("3. 退出");
	                System.out.println("请输入您的选择: ");
	                int choice = scanner.nextInt();
	                scanner.nextLine(); // 使用换行符
	
	                switch (choice) {
	                    case 1:
	                        register(manager);
	                        break;
	                    case 2:
	                        loggedInUser = login(manager);
	                        loggedIn = true;
	                        break;
	                    case 3:
	                    	System.out.println("感谢您使用我们的系统。再见!");
	                        System.out.println("");
	                        return;
	                    default:
	                        System.out.println("无效的选择！请您再试一次。");
	                        break;
	                }
	            } else {
	                System.out.println("1. 修改密码");
	                System.out.println("2. 忘记密码");
	                System.out.println("3. 查看所有电影放映信息");
	                System.out.println("4. 查看指定电影放映信息");
	                System.out.println("5. 购票");
	                System.out.println("6. 查看历史记录");
	                System.out.println("7. 退出登录");
	                System.out.print("请输入您的选择: ");
	                int choice;
	                while(true) {
	                	choice = scanner.nextInt();
	                	switch (choice) {
		                    case 1:
		                        changePassword(manager,loggedInUser);
		                        break;
		                    case 2:
		                        forgetPassword(manager);
		                        break;
		                    case 3:
		                        viewMovieSchedule(manager);
		                        break;
		                    case 4:
		                    	viewSelectedMovie(manager);
		                        break;
		                    case 5:
		                        purchaseTickets(manager,loggedInUser);
		                        break;
		                    case 6:
		                        viewPurchaseHistory(loggedInUser);
		                        break;
		                    case 7:
		                    	loggedIn = false;
		                        loggedInUser = null;
		                        System.out.println("感谢您使用我们的系统。再见!");
		                        System.out.println("");
		                        exit = true;
		                        break;
		                    default:
		                    		System.out.println("无效的选择！请您再试一次。");
		                    		System.out.print("请输入您的选择: ");
		        	                continue;
		                }
	                	break;
	                }
	            }
	        }
        }
        // 注册
	    private static void register(Manager manager) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("请输入用户名(用户名长度不少于五个字符): ");
	        String username = scanner.nextLine();
	        if (username.length() < 5) {
	            System.out.println("用户名长度不少于五个字符！");
	            return;
	        }
	        for(Customer user : manager.customers) {
	        	if(user.getUsername().equals(username)) {
	        		System.out.println("该用户名已存在！");
	        		return;
	        	}
	        }
	        System.out.println("请输入密码(密码须长度大于8个字符，且必须是大小写字母、数字和特殊符号（@$!%*?&._）的组合！): ");
	        String password = scanner.nextLine();
	        
	        if (password.length() < 8 || !isPasswordValid(password)) {
	            System.out.println("密码长度须大于8个字符，且必须是大小写字母、数字和特殊符号（@$!%*?&._）的组合！");
	            return;
	        }
			int ID = 0;
			System.out.println("请输入电话号码: ");
			String phoneNumber = scanner.nextLine();
	        System.out.println("请输入邮箱: ");
	        String email = scanner.nextLine();
	        Customer customer = new Customer(ID,username,phoneNumber,email,password,"铜牌用户",0,0);
	        manager.customers.add(customer);
	        System.out.println("成功注册!");
	        customer.showCustomerMenu(manager);
	    }
	    // 登录
	    public static Customer login(Manager manager) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("-----------登录----------");
	        System.out.println("请输入用户名: ");
	        String username = scanner.nextLine();
	        Customer customer = findCustomer(manager,username);
	        if (customer == null) {
	            System.out.println("无效的用户名！");
	            return null;
	        }
	        if (customer.isLocked()) {
	            System.out.println("此帐户已被锁定，请稍后再试！");
	            return null;
	        }
	        int loginAttempts = 0;
	        while (loginAttempts < MAX_LOGIN_ATTEMPTS) {
	            System.out.println("请输入密码: ");
	            String password = scanner.nextLine();
	            if (password.equals(customer.getPassword())) {
	                System.out.println("成功登录！");
	                return customer;
	            } else {
	                loginAttempts++;
	                System.out.println("密码错误，还剩余的登录次数: " + (MAX_LOGIN_ATTEMPTS - loginAttempts) + "次");
	            }
	        }
	        customer.lock();
	        System.out.println("尝试次数已用完，账户锁定 " + LOCKED_OUT_TIME + " 分钟");
	        return null;
	    }
	    // 修改自身密码
	    private static void changePassword(Manager manager,Customer customer) {
	        Scanner scanner = new Scanner(System.in);
	        String newPassword;
	        System.out.println("请输旧密码: ");
	        String passPassword = scanner.nextLine();
	        if(passPassword.equals(customer.getPassword())) {
		        System.out.println("请输新密码: ");
		        newPassword = scanner.nextLine();
		        while (newPassword.length() < 8 || !isPasswordValid(newPassword)) {
		            System.out.println("新密码长度须大于8个字符，且必须是大小写字母、数字和特殊符号（@$!%*?&._）的组合！");
		            System.out.println("重新输入: ");
		            newPassword = scanner.nextLine();
		        }
		        while(newPassword.equals(passPassword)) {
		        	System.out.println("新密码与原密码相同！");
		            System.out.println("请重新输入: ");
		            newPassword = scanner.nextLine();
		        }
		        customer.unlock();
		        customer.password = newPassword;
		        System.out.println("密码修改成功！");
	        }else {
	        	while (true) {
		            System.out.println("请重新输入旧密码: ");
		            passPassword = scanner.nextLine();
		            if(passPassword.equals(customer.getPassword())) {
		            	System.out.println("请输新密码（新密码须长度大于8个字符，且必须是大小写字母、数字和特殊符号（@$!%*?&._）的组合！）: ");
				        newPassword = scanner.nextLine();
				        while (newPassword.length() < 8 || !isPasswordValid(newPassword)) {
				            System.out.println("新密码须长度大于8个字符，且必须是大小写字母、数字和特殊符号（@$!%*?&._）的组合！");
				            System.out.println("重新输入: ");
				            newPassword = scanner.nextLine();
				        }
				        while(newPassword.equals(passPassword)) {
				        	System.out.println("新密码与原密码相同！");
				            System.out.println("请重新输入: ");
				            newPassword = scanner.nextLine();
				        }
				        customer.unlock();
				        customer.password = newPassword;
				        System.out.println("密码修改成功！");
		            }
		        }
	        }
	    }
	    // 忘记密码
	    public void forgetPassword(Manager manager) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("请输入用户名: ");
	        String username = scanner.nextLine();
	        Customer customer = findCustomer(manager,username);
	        if (customer == null) {
	            System.out.println("无效的用户名！");
	            return;
	        }
	        System.out.print("请输入邮箱地址: ");
	        String email = scanner.nextLine();
	        // 用随机生成的密码发送电子邮件
	        String randomPassword = generateRandomPassword();
	        System.out.println("密码重置邮件已发送至 " + customer.getEmail());
	        System.out.println("请检查您的电子邮件以获取新密码！");
	        customer.password = randomPassword;
	    }
	    // 查看所有电影放映信息
	    private static void viewMovieSchedule(Manager manager) {
	        System.out.println("电影放映信息:");
	        System.out.println("------------------------------");
	        for (Movie movie : manager.movies) {
	            System.out.println("片名:  " + movie.getTitle());
	            System.out.println("导演: " + movie.getDirector());
	            System.out.println("主演: " + movie.getLeadActor());
	            System.out.println("简介: " + movie.getSynopsis());
	            System.out.println("时长: " + movie.getDuration() + "分钟");
                for (Show show : manager.allShows) {
                	if(show.getMovie().equals(movie)) {
                      System.out.println("放映厅: " + show.getHall());
                      System.out.println("放映时段: " + show.getShowTime());
                      System.out.println("价格: " + show.getPrice());
                      System.out.println("------------------------------");
                    }
                }
	        }
	    }
	    // 查看指定电影放映信息
	    private static void viewSelectedMovie(Manager manager){
	    	Scanner scanner = new Scanner(System.in);
	    	System.out.println("请输入所要查看的指定的影片: ");
	        String title = scanner.nextLine();
	        System.out.println("电影"+title+"的放映信息如下: ");
	        System.out.println("------------------------------");
	        for(Movie movie : manager.movies) {
	        	if(movie.getTitle().equals(title)) {
	        		System.out.println("片名:  " + movie.getTitle());
		            System.out.println("导演: " + movie.getDirector());
		            System.out.println("主演: " + movie.getLeadActor());
		            System.out.println("简介: " + movie.getSynopsis());
		            System.out.println("时长: " + movie.getDuration() + "分钟");
	                for (Show show : manager.allShows) {
	                    if ( movie.equals(manager.sessions.get(show)) ) {
	                        System.out.println("放映厅: " + show.getHall());
	                        System.out.println("放映时段: " + show.getShowTime());
	                        System.out.println("价格: " + show.getPrice());
	                        System.out.println("------------------------------");
	                    }
	                }
	        	}
	        }
	    }
	    // 购票
	    private void purchaseTickets(Manager manager,Customer customer) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("电影放映信息:");
	        System.out.println("------------------------------");
	        for (Movie movie : manager.movies) {
	        	 System.out.println("片名:  " + movie.getTitle());
		         System.out.println("导演: " + movie.getDirector());
		         System.out.println("主演: " + movie.getLeadActor());
		         System.out.println("简介: " + movie.getSynopsis());
		         System.out.println("时长: " + movie.getDuration() + "分钟");
		         System.out.println("------------------------------");
	        }
	        System.out.print("请输入您要选择的放映厅: ");
	        String hall = scanner.nextLine();
	        Show show = findShowbyHall(manager,hall);
	        while(show == null) {
	        	System.out.print("请重新输入您要选择的放映厅: ");
		        hall = scanner.nextLine();
		        show = findShowbyHall(manager,hall);
	        }
	        Theater theater = manager.Hall.get(show);
	        System.out.print("请输入要购买的电影票的票数: ");
	        int ticketCount = scanner.nextInt(); 
	        
	        double totalCost = show.getPrice() * ticketCount;
	        double discount = count_Copper;
	
	        if (customer.getRank().equals("金牌用户")) {
	            discount = count_Gold;
	        } else if (customer.getRank().equals("银牌用户")) {
	            discount = count_Silver;
	        }
	        
	        double countedPaymentAmount = discount * totalCost;
			String PaymentAmount = String.format("%.2f",countedPaymentAmount);
			double discountedCost = Double.parseDouble(PaymentAmount);

	        System.out.println("总金额: " + totalCost + "元");
	        System.out.println("打折后的金额: " + discountedCost + "元");
	
	        System.out.println("请选择支付渠道:");
	        System.out.println("1. 支付宝");
	        System.out.println("2. 微信支付");
	        System.out.println("3. 银行卡");
	        int paymentMethod = scanner.nextInt();
	        scanner.nextLine(); 
	
	        System.out.println("支付成功！");
	        String selected_seats =  selectSeats(theater,ticketCount);
	        String TicketID = generateTicketID();
	        customer.setAccuamount(customer.getAccuamount()+ discountedCost);
	        customer.setAccucount(customer.getAccucount() + 1);
	        
	        System.out.println("已购票的信息:");
	        System.out.println("------------------------------");
	        System.out.println("影片: " + show.getMovie().getTitle());
	        System.out.println("放映厅: " + show.getHall());
	        System.out.println("时长: " + show.getShowTime());
	        System.out.println("座位: " + selected_seats);
	        System.out.println("电影票编号: " + TicketID);
	        System.out.println("------------------------------");
	        Ticket ticket = new Ticket(show.getMovie().getTitle(),show.getShowTime(),customer.getUsername(),discountedCost,TicketID);	
		    PurchaseHistory.add(ticket);
	    }
	 
	    // 查看购票历史
	    private static void viewPurchaseHistory(Customer customer) {
	        System.out.println("-------------购票历史------------");
	        System.out.println("用户: " + customer.getUsername());
	        int count = 0;
	        for(Ticket ticket : PurchaseHistory) {
	        	System.out.println("影片: " + ticket.getMovieTitle());
	        	System.out.println("时长: " + ticket.getScreeningTime());
	        	System.out.println("价格: " + ticket.getPaymentAmount());
	        	System.out.println("电影票编号: " + ticket.getTicketID());
	        	count++;
	        }
	        if(count == 0) {
		        System.out.println("未找到购票历史！"); 
	        }
	        System.out.println("------------------------------");
	    }
	    // 选座
	    private static String selectSeats(Theater theater,int ticketCount) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("请进行选座:");
	        for (int row = 1; row <= 7; row++) {
	            System.out.print(row + " ");
	            for (int col = 1; col <= 12; col++) {
	                 if (theater.getSeats()[row - 1][col - 1] == 0) {
	                      System.out.print("  O");
	                 } else {
	                      System.out.print("  X");
	                 }
	            }
	            System.out.println();
	        }
	        System.out.println("请选择座位: " + "一共要选择" + ticketCount + " 个座位 (e.g., 1,2 3,4 5,6 【注】:必须是英文输入法输入): ");
	        String[] seatSelections = scanner.nextLine().split(" ");
	        
	        StringBuilder seatNumbers = new StringBuilder();
	        for (String seatSelection : seatSelections) {
	            String[] seatRange = seatSelection.split(",");
	            int row = Integer.parseInt(seatRange[0]) - 1;
	            int column = Integer.parseInt(seatRange[1]) - 1;
	
	            if (row < 0 || row >= theater.SEAT_ROWS || column < 0 || column >= theater.SEAT_COLUMNS) {
	                System.out.println("选择座位无效: " + seatSelection);
	                continue;
	            }
	
	            if (theater.getSeats()[row][column] == 0) {
	            	theater.getSeats()[row][column] = 1;
	                seatNumbers.append((row + 1) + "-" + (column + 1)).append(" ");
	            } else {
	                System.out.println("座位已被占用: " + seatSelection);
	            }
	        }
	        return seatNumbers.toString().trim();
	    }
	    // 密码是否合法
	    private static boolean isPasswordValid(String password) {
	        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._])[A-Za-z\\d@$!%*?&._]+$");
	    }
	    // 通过用户名查找用户
	    private static Customer findCustomer(Manager manager,String username) {
	        for (Customer customer : manager.customers) {
	            if (customer.getUsername().equals(username)) {
	                return customer;
	            }
	        }
	        return null;
	    }
	    // 通过放映厅找到对应的场次
	    private static Show findShowbyHall(Manager manager,String hall) {
	    	for(Show show : manager.allShows) {
	    		if(show.getHall().equals(hall)) {
	    			return show;
	    		}
	    	}
			return null;
	    }
	    // 生成随机密码
	    private static String generateRandomPassword() {
	    	 String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
	    	    int length = 12;

	    	    StringBuilder password = new StringBuilder();

	    	    Random random = new Random();
	    	    for (int i = 0; i < length; i++) {
	    	        int index = random.nextInt(characters.length());
	    	        password.append(characters.charAt(index));
	    	    }

	    	    return password.toString();
	    }
	    // 生成电影票的电子ID编号
	    private static String generateTicketID() {
	        return "XAD-123-423DGGUID";
	    }
}