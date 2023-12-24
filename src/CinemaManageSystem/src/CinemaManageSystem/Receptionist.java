package CinemaManageSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Receptionist extends User{
	private String username;
	private String password;
    private static List<Ticket> tickets = new ArrayList<>();
    
    static final double count_Gold = 0.88;
    static final double count_Silver = 0.95;
    static final double count_Copper = 1;
	
	public Receptionist(int ID, String username, String password, String phoneNumber, String email) {
		super(ID, username,password,"前台", phoneNumber, email);
	}
	
	// 交互界面
    public void showReceptionistMenu(Admin admin,Manager manager) {
    	Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        Receptionist loggedInUser = null;
        boolean exit = false;
        
        while (!exit) {
            if (!loggedIn) {
                System.out.println("1. 登录");
                System.out.println("2. 退出");
                System.out.println("请输入您的选择: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 使用换行符

                switch (choice) {
                    case 1:
                        loggedInUser = login(admin);
                        loggedIn = true;
                        break;
                    case 2:
                    	System.out.println("感谢您使用我们的系统。再见!");
                        System.out.println("");
                    default:
                        System.out.println("无效的选择！请您再试一次。");
                }
            } else {
            	System.out.println("请选择你所要进行的操作:（建议在进行3、4操作时先依次进行1和2操作了解正在上映影片及其对应的放映厅）");
                System.out.println("1. 列出所有正在上映影片的信息");
                System.out.println("2. 列出所有正在上映影片的场次信息");
                System.out.println("3. 列出指定电影和场次的信息");
                System.out.println("4. 售票功能");
                System.out.println("5. 退出");
                int choice;
                while(true) {
                	choice = scanner.nextInt();
                	switch (choice) {
		                case 1:
		                	listAllMovies(manager);
		                    break;
		                case 2:
		                	listAllScreenings(manager);
		                    break;
		                case 3:
		                	listSeatInformation(manager);
		                    break;
		                case 4:
		                	sellTicket(manager);
		                    break;
	                    case 5:
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
    
    // 登录
   	public Receptionist login(Admin admin) {   
           Scanner scanner = new Scanner(System.in);
           System.out.println("--------------登录-------------");
           System.out.println("请输入用户名：");
           String username = scanner.nextLine();

           System.out.println("请输入密码：");
           String password = scanner.nextLine();
           while(true){
        	   	for(Receptionist receptionist : admin.receptionists) {
	        	   	if (username.equals(receptionist.getUsername()) && password.equals(receptionist.getPassword())) {
		           		System.out.println("登录成功！");
		           		return receptionist;
		           	} else {
		           		System.out.println("用户名或密码错误，登录失败！");
		           		System.out.println("请重新输入用户名：");
		                   username = scanner.nextLine();
		
		                   System.out.println("请重新输入密码：");
		                   password = scanner.nextLine();
		           		continue;
		           	}
        	   	}	
           }
    }
   	
    // 列出所有正在上映影片的信息
    private void listAllMovies(Manager manager) {
    	System.out.println("=== 正在上映的电影 ===");
        for (Movie movie : manager.movies) {
            System.out.println("片名: " + movie.getTitle());
            System.out.println("导演: " + movie.getDirector());
            System.out.println("主演: " + movie.getLeadActor());
            System.out.println("简介: " + movie.getSynopsis());
            System.out.println("时长: " + movie.getDuration() + "分钟");
            System.out.println("-------------------------------");
        }
    }
    // 列出所有正在上映影片的场次信息
    private static void listAllScreenings(Manager manager) {
        System.out.println("=== 场次 ===");
        for (Show session : manager.allShows) {
	        	System.out.println("影片名: " + session.getMovie().getTitle());
	            System.out.println("放映厅: " + session.getHall());
	            System.out.println("放映时段: " + session.getShowTime());
	            System.out.println("价格: " + session.getPrice());
	            System.out.println("-------------------------------");
        }
    }
    // 列出指定电影和场次的信息
    private void listSeatInformation(Manager manager) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入片名: ");
    	String movieTitle = scanner.nextLine();
    	System.out.println("请输入放映厅: ");
    	String hall = scanner.nextLine();
    	Show show = findShowbyHall(manager,hall);
    	Theater theater = manager.Hall.get(show);
    	if(show == null && theater == null) {
    		System.out.println("没有发现与给定的电影和时间放映!");
    	}else {
	        int[][] seats = theater.getSeats();
	        System.out.print(" ");
	        for (int col = 1; col <= 12; col++) {
	            System.out.print("  " + col);
	        }
	        System.out.println();
	        for (int row = 1; row <= 7; row++) {
	            System.out.print(row + " ");
	            for (int col = 1; col <= 12; col++) {
	                 if (seats[row - 1][col - 1] == 0) {
	                      System.out.print("  O");
	                      theater.Available_seats++;
	                 } else {
	                      System.out.print("  X");
	                 }
	            }
	            System.out.println();
	        }
	        theater.setAvailable_seats(theater.calculateVacantSeats(theater));
	        System.out.println("总座位数: " + theater.seating_capacity);
	        System.out.println("空闲的座位数: " + theater.calculateVacantSeats(theater));
	        return;
        }
    }
    // 售票功能
    private void sellTicket(Manager manager) {
    	String ticketID = generateTicketID();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入片名: ");
    	String movieTitle = scanner.nextLine();
    	System.out.println("请输入放映厅: ");
    	String hall = scanner.nextLine();
    	if(isHallFull(manager,hall) == 0 || isHallFull(manager,hall) == 2) {
    		do {
	    		System.out.println("座位已满或未找到符合的放映厅");
	    		System.out.println("请重新输入放映厅: ");
	    		hall = scanner.nextLine();
    		}while(isHallFull(manager,hall) == 0);
    	}
    	System.out.println("请输入放映时间: ");
    	String screeningTime = scanner.nextLine();
    	System.out.println("请输入支付金额: ");
    	double paymentAmount = scanner.nextDouble();
    	scanner.nextLine(); // 清空缓冲区的换行符
    	System.out.println("请输入要购票的用户的用户名: ");
    	String username = scanner.nextLine();
    	Customer customer = findCustomerByUsername(manager,username);
    	if(customer != null) {
	    	double CountedPaymentAmount = Discount_paymentAmount(manager,username,paymentAmount);
	    	Ticket ticket = new Ticket(movieTitle,screeningTime,username,CountedPaymentAmount,ticketID);
	        tickets.add(ticket);
	    	// 输出需要的信息
	    	System.out.println("------------电影票信息----------");
	    	System.out.println("用户名: " + username);
	        System.out.println("影片名: " + movieTitle);
	        System.out.println("放映时间: " + screeningTime);
	        System.out.println("电影票的电子ID: " + ticketID);
	        System.out.println("打折后应支付金额: " + CountedPaymentAmount + "元");
    	}else {
    		while(true) {
    			System.out.println("该用户名不存在，请重新输入！ ");
	    		System.out.println("请重新输入用户名: ");
	    		username = scanner.nextLine();
	    		customer = findCustomerByUsername(manager,username);
	    		if(customer != null) {
	    			break;
	    		}else {
	    			continue;
	    		}
    		}
    	}
    }
    // 生成电影票的电子ID编号
    private static String generateTicketID() {
        return "XAD-123-423DG";
    }
    // 判断该放映厅是否满了
    private static Integer isHallFull(Manager manager,String hall) {
    	int count = 0;
    	Show show = findShowbyHall(manager,hall);
    	Theater theater = manager.Hall.get(show);
    	if(show == null) {
    		return 2; // 未找到符合的放映厅
    	}else {
	    	if(theater.getAvailable_seats() == 0) {
	        	return 0;   // 座位已满，无法选座须更换放映厅
	        }else {
	        	return 1;   // 座位未满
	        } 
    	}
    }
    // 返回不同级别的用户打折后的票价
    private Double Discount_paymentAmount(Manager manager,String username,double paymentAmount){
    	Scanner scanner = new Scanner(System.in);
    	Customer customer = findCustomerByUsername(manager,username);
    	double count;
		while(customer == null) {
    		System.out.println("请重新输入用户名: ");
    		username = scanner.nextLine();
    		customer = findCustomerByUsername(manager,username);
    	}
        if(customer.getRank().equals("金牌用户")) {
        	count = count_Gold;
        }else if(customer.getRank().equals("银牌用户")) {
        	count = count_Silver;
        }else {
        	count = count_Copper;   // 铜牌用户
        }
        double countedPaymentAmount = count * paymentAmount;
		String PaymentAmount = String.format("%.2f",countedPaymentAmount);
		double CountedPaymentAmount = Double.parseDouble(PaymentAmount);
		return CountedPaymentAmount;
    }
    // 通过用户名来找到对应的用户
    private static Customer findCustomerByUsername(Manager manager,String username) {
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
}