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
    public void setPassword(String password) {this.password = password;}
	public String getPassword() { return password; }
	
	public Receptionist(int ID, String username, String password, String phoneNumber, String email) {
		super(ID, username, "前台", phoneNumber, email);
		this.password = password;
	}
    // 登录
   	public void login(Manager manager1) {   
           Scanner scanner = new Scanner(System.in);

           System.out.println("请输入用户名：");
           String username = scanner.nextLine();

           System.out.println("请输入密码：");
           String password = scanner.nextLine();
           while(true){
	           	if (username.equals(getUsername()) && password.equals(getPassword())) {
	           		System.out.println("登录成功！");
	           		showManagerMenu(manager1);
	           		break;
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
   	// 交互界面
    public void showManagerMenu(Manager manager1) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("请选择你所要进行的操作:（建议在进行3、4操作时先依次进行1和2操作了解正在上映影片及其对应的放映厅）");
            System.out.println("1. 列出所有正在上映影片的信息");
            System.out.println("2. 列出所有正在上映影片的场次信息");
            System.out.println("3. 列出指定电影和场次的信息");
            System.out.println("4. 售票功能");
            System.out.println("5. 退出");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                	listAllMovies(manager1);
                    break;
                case 2:
                	listAllScreenings(manager1);
                    break;
                case 3:
                	listSeatInformation(manager1);
                    break;
                case 4:
                	sellTicket(manager1);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    do{
                    	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                    	option = scanner.nextInt();
                    	if(option == 1) {
                    		listAllMovies(manager1);
                    		break;
                    	}else if(option == 2){
                    		listAllScreenings(manager1);
                    		break;
                    	}else if(option == 3){
                    		listSeatInformation(manager1);
                    		break;
                    	}else if(option == 4){
                    		sellTicket(manager1);
                    		break;
                    	}else if(option == 5){
                    		exit = true;
                    		break;
                    	}
                    }while(option < 1 || option > 5);
            }
        }
    }
    // 列出所有正在上映影片的信息
    private void listAllMovies(Manager manager1) {
    	System.out.println("=== 正在上映的电影 ===");
        for (Movie movie : manager1.movies) {
            System.out.println("片名: " + movie.getTitle());
            System.out.println("导演: " + movie.getDirector());
            System.out.println("主演: " + movie.getLeadActor());
            System.out.println("简介: " + movie.getSynopsis());
            System.out.println("时长: " + movie.getDuration() + "分钟");
            System.out.println("-------------------------------");
        }
    }
    // 列出所有正在上映影片的场次信息
    private static void listAllScreenings(Manager manager1) {
        System.out.println("=== 场次 ===");
        for (Show session : manager1.shows) {
        	System.out.println("影片名: " + session.getMovie().getTitle());
            System.out.println("放映厅: " + session.getHall());
            System.out.println("放映时段: " + session.getShowTime());
            System.out.println("价格: " + session.getPrice());
            System.out.println("-------------------------------");
        }
    }
    // 列出指定电影和场次的信息
    private void listSeatInformation(Manager manager1) {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入片名: ");
    	String movieTitle = scanner.nextLine();
    	System.out.println("请输入放映厅: ");
    	String hall = scanner.nextLine();
    	Show show = findShowbyHall(manager1,hall);
    	Theater theater = manager1.Hall.get(show);
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
	        System.out.println("总座位数: " + theater.seating_capacity);
	        System.out.println("空闲的座位数: " + theater.Available_seats);
	        return;
        }
    }
    // 售票功能
    private void sellTicket(Manager manager1) {
    	String ticketID = generateTicketID();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入片名: ");
    	String movieTitle = scanner.nextLine();
    	System.out.println("请输入放映厅: ");
    	String hall = scanner.nextLine();
    	if(isHallFull(manager1,hall) == 0 || isHallFull(manager1,hall) == 2) {
    		do {
	    		System.out.println("座位已满或未找到符合的放映厅");
	    		System.out.println("请重新输入放映厅: ");
	    		hall = scanner.nextLine();
    		}while(isHallFull(manager1,hall) == 0);
    	}
    	System.out.println("请输入放映时间: ");
    	String screeningTime = scanner.nextLine();
    	System.out.println("请输入支付金额: ");
    	double paymentAmount = scanner.nextDouble();
    	scanner.nextLine(); // 清空缓冲区的换行符
    	System.out.println("请输入要购票的用户的用户名: ");
    	String username = scanner.nextLine();
    	Customer customer = findCustomerByUsername(manager1,username);
    	if(customer != null) {
	    	double CountedPaymentAmount = Discount_paymentAmount(manager1,username,paymentAmount);
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
	    		customer = findCustomerByUsername(manager1,username);
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
    private static Integer isHallFull(Manager manager1,String hall) {
    	int count = 0;
    	Show show = findShowbyHall(manager1,hall);
    	Theater theater = manager1.Hall.get(show);
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
    private Double Discount_paymentAmount(Manager manager1,String username,double paymentAmount){
    	Scanner scanner = new Scanner(System.in);
    	Customer customer = findCustomerByUsername(manager1,username);
    	double count;
		while(customer == null) {
    		System.out.println("请重新输入用户名: ");
    		username = scanner.nextLine();
    		customer = findCustomerByUsername(manager1,username);
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
    private static Customer findCustomerByUsername(Manager manager1,String username) {
            for (Customer customer : manager1.customers) {
            	if (customer.getUsername().equals(username)) {
                    return customer;
                }
            }
            return null;
    }
 // 通过放映厅找到对应的场次
    private static Show findShowbyHall(Manager manager1,String hall) {
    	for(Show show : manager1.shows) {
    		if(show.getHall().equals(hall)) {
    			return show;
    		}
    	}
		return null;
    }
}