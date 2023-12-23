package CinemaManageSystem;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manager extends User{
    private String username;
    private String password;
    public List<Customer> customers = new ArrayList<>();
    
    public HashMap<Show,Movie> sessions = new HashMap<Show,Movie>();
    public Set<Show> shows = sessions.keySet();
    public List<Show> allShows = new ArrayList<>();
    public Collection<Movie> movies = sessions.values();  // 已安排了场次的电影信息
    public List<Movie> allMovies = new ArrayList<>();     // 所有电影信息（包括未排场次的）
    public HashMap<Show,Theater> Hall = new HashMap<Show,Theater>(); // 放映厅及其对应信息
    public Collection<Theater> theaters = Hall.values();   // 已安排了场次的信息
    public List<Theater> allTheaters = new ArrayList<>();    // 所有信息（包括未排场次的）
    
    public void setPassword(String password) {this.password = password;}
	public String getPassword() { return password; }
	
	public Manager(int ID,String username,String password,String PhoneNumber,String email) {
		super(ID,username,"经理",PhoneNumber,email);
		this.password = password;
	}
    // 登录
	public void login() {   
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        while(true){
        	if (username.equals(getUsername()) && password.equals(getPassword())) {
        		System.out.println("登录成功！");
        		showManagerMenu();
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
	private void showManagerMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("请选择你所要进行的操作:");
            System.out.println("1. 密码管理");
            System.out.println("2. 影片管理");
            System.out.println("3. 排片管理");
            System.out.println("4. 用户管理");
            System.out.println("5. 退出登录");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    passwordManagement();
                    break;
                case 2:
                    MovieManagement();
                    break;
                case 3:
                    MovieArrangeManagement();
                    break;
                case 4:
                    userManagement();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    do{
                    	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                    	option = scanner.nextInt();
                    	if(option == 1) {
                    		passwordManagement();
                    		break;
                    	}else if(option == 2){
                    		MovieManagement();
                    		break;
                    	}else if(option == 3){
                    		MovieArrangeManagement();
                    		break;
                    	}else if(option == 4){
                    		userManagement();
                    		break;
                    	}else if(option == 5){
                    		exit = true;
                    		break;
                    	}
                    }while(option < 1 || option > 5);
            }
        }
    }
    

    // 密码管理
    public void passwordManagement() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 修改自身密码");
        System.out.println("2. 重置指定用户（消费者）的密码");
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                changePassword();
                break;
            case 2:
            	resetCustomerPassword();
                break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		changePassword();
                	}else if(option == 2){
                		resetCustomerPassword();
                	}
                }while(option != 1 || option != 2);
        }
    }
    // 修改自身密码
    public void changePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPassword = scanner.nextLine();
        while(true) {
        	if (oldPassword.equals(password)) {
        		System.out.println("请输入新密码：");
        		String newPassword = scanner.nextLine();

        		password = newPassword;

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
    // 重置指定用户的密码
    public void resetCustomerPassword() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请输入所要指定重置的用户的用户名:");
        String username = scanner.nextLine();
        Customer customer = findCustomerByUsername(username);
        if (customer != null) {
        	customer.resetPassword();
        } else {
            while(customer == null){
            	System.out.println("用户不存在！");
            	scanner = new Scanner(System.in);
                System.out.println("请重新输入输入所要指定重置的影城方用户的用户名:");
                username = scanner.nextLine();
                customer = findCustomerByUsername(username);
            }
            if (customer != null) {
            	customer.resetPassword();
            }
        }
    }
    
    
    // 影片管理
    public void MovieManagement() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 列出所有正在上映影片的信息");
        System.out.println("2. 添加影片的信息");
        System.out.println("3. 修改电影的信息");
        System.out.println("4. 删除影片的信息");
        System.out.println("5. 查询影片的信息");
       
        int option = scanner.nextInt();
        switch (option) {
            case 1:
                listAllMovies();
                break;
            case 2:
            	addMovieInfo();
                break;
            case 3:
            	modifyMovieInfo();
                break;
            case 4:
            	deleteMovie();
                break;
            case 5:
            	searchMovieInfo();
            	break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		listAllMovies();
                	}else if(option == 2){
                		addMovieInfo();
                	}else if(option == 3){
                		modifyMovieInfo();
                	}else if(option == 4){
                		deleteMovie();
                	}else if(option == 5){
                		searchMovieInfo();
                	}
                }while(option < 1 || option > 5);
        }
    }
    // 列出所有正在上映影片的信息
    private void listAllMovies() {
    	System.out.println("电影放映信息:");
        System.out.println("------------------------------");
        for (Movie movie : movies) {
        	System.out.println("片名:  " + movie.getTitle());
	         System.out.println("导演: " + movie.getDirector());
	         System.out.println("主演: " + movie.getLeadActor());
	         System.out.println("简介: " + movie.getSynopsis());
	         System.out.println("时长: " + movie.getDuration() + "分钟");
	         System.out.println("------------------------------");
        }
    }
    // 添加影片的信息
    private void addMovieInfo() {
    	Scanner scanner = new Scanner(System.in);
    	int count = 0;
    	System.out.println("请输入影片名: ");
    	String title = scanner.nextLine();
        for (Movie movie : allMovies) {
            if(title.equals(movie.getTitle())) {  // 两部影片不能相同
            	System.out.println("该影片的信息已存在！");
            }
            count++;
        }
        if(count == movies.size()) {
	        System.out.println("请输入导演名: ");
	        String director = scanner.nextLine();
	        System.out.println("请输入主演的名: ");
	        String leadActor = scanner.nextLine();
	        System.out.println("请输入影片简介: ");
	        String synopsis = scanner.nextLine();
	        System.out.println("请输入影片时长: ");
	        int duration = scanner.nextInt();
	        Movie newMovie = new Movie(title,director,leadActor,synopsis,duration);
        	allMovies.add(newMovie);
        	System.out.println("影片信息添加成功");
        }
    }
    // 修改电影的信息
    private void modifyMovieInfo() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请选择要修改的电影的电影名:");
    	String title = scanner.nextLine();
    	Movie movie = findMovieByTitle(title);
    	if (movie != null) {
	    	System.out.println("请选择要修改的信息:");
	    	System.out.println("1. 片名");
	    	System.out.println("2. 导演");
	    	System.out.println("3. 主演");
	    	System.out.println("4. 剧情简介");
	    	System.out.println("5. 时长");
	    	System.out.println("6. 取消");
	    	
	    	int option = scanner.nextInt();
	    	String ch;
	    	do {
		    	switch (option) {
		    	     case 1:
		    	         System.out.println("请输入新的片名:");
		    	         String newTitle = scanner.nextLine();
		    	         movie.setTitle(newTitle);
		    	         break;
		    	     case 2:
		    	    	 System.out.println("请输入新的导演名:");
		    	         String newDirector = scanner.nextLine();
		    	         movie.setDirector(newDirector);
		    	         break;
		    	     case 3:
		    	    	 System.out.println("请输入新的主演:");
		    	         String newLeadActor = scanner.nextLine();
		    	         movie.setLeadActor(newLeadActor);
		    	         break;
		    	     case 4:
		    	    	 System.out.println("请输入新的剧情简介:");
		    	         String newSynopsis = scanner.nextLine();
		    	         movie.setSynopsis(newSynopsis);
		    	         break;
		    	     case 5:
		    	    	 System.out.println("请输入新的时长:");
		    	         int newDuration = scanner.nextInt();
		    	         movie.setDuration(newDuration);
		    	         break;
		    	     case 6:
		    	         return;
		    	     default:
		    	         do {
		    	        	 System.out.println("无效的选择，请重新输入您的选择:");
		    	        	 option = scanner.nextInt();
		    	         }while(option < 1 || option > 6);
		    	}
		    	    System.out.println("修改成功！");
		    	    System.out.println("是否还要继续修改电影信息？（是或否）");
		    	    ch = scanner.nextLine();
	    	}while(ch == "是");
	    }else {
            System.out.println("该电影不存在！");
        }
    }
    // 删除影片的信息
    private void deleteMovie() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入所要指定删除的电影的片名：");
    	String title = scanner.nextLine();
    	Movie movieToDelete = findMovieByTitle(title);
        movies.remove(movieToDelete);
        System.out.println("影片成功删除！");
    }
    // 查询影片的信息
    private void searchMovieInfo() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 根据影片名称查询");
        System.out.println("2. 根据导演查询");
        
        int option = scanner.nextInt();
        scanner.nextLine();
        String str;
        do {
	         if(option == 1) {
	             System.out.println("请输入影片名称:");
	             String title = scanner.nextLine();
	             Movie movie = findMovieByTitle(title);
	             System.out.println("------------------------------");
	             System.out.println("片名:  " + movie.getTitle());
		         System.out.println("导演: " + movie.getDirector());
		         System.out.println("主演: " + movie.getLeadActor());
		         System.out.println("简介: " + movie.getSynopsis());
		         System.out.println("时长: " + movie.getDuration() + "分钟");
		         System.out.println("------------------------------");
	         }else if(option == 2) {
	        	 System.out.println("请输入导演名:");
	        	 String director = scanner.nextLine();
	        	 for(Movie movie : movies) {
	        		 if(movie.getDirector().equals(director)) {
		        		 System.out.println(movie.getDirector() + "导演的电影清单: ");
			             System.out.println("片名:  " + movie.getTitle());
	        		 }
	        		 
	        	 }
	         }
	         else {
	        	 System.out.println("无效的选择，请重新选择你所要进行的操作:");
	        	 option = scanner.nextInt();
	         }
	         System.out.println("是否还要继续查询？（是或否）");
	         str = scanner.nextLine();
        }while(str == "是");
    }
    
	// 排片管理
    private void MovieArrangeManagement() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 增加场次");
        System.out.println("2. 修改场次");
        System.out.println("3. 删除场次");
        System.out.println("4. 列出所有场次信息");
       
        int option = scanner.nextInt();
        Movie newMovie = null;
        switch (option) {
            case 1:
            	addSession();
                break;
            case 2:
            	modifySession();
                break;
            case 3:
            	deleteSession();
                break;
            case 4:
            	listAllSession();
                break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		listAllMovies();
                	}else if(option == 2){
                		modifySession();
                	}else if(option == 3){
                		deleteSession();
                	}else if(option == 4){
                		listAllSession();
                	}
                }while(option < 1 || option > 4);
        }
    }
    // 增加场次
    private void addSession() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请输入电影场次的信息：");
        System.out.println("片名：");
        String movieTitle = scanner.nextLine();
        System.out.println("放映厅：");
        String hall = scanner.nextLine();
        System.out.println("放映时间：");
        String showTime = scanner.nextLine();
        System.out.println("价格：");
        double price = scanner.nextDouble();
        Movie movie = findMovieByTitle(movieTitle);
        if (movie != null) {
            Show session = new Show(movie, hall, showTime, price);
            sessions.put(session, movie);
            Theater theater = findTheaterByHall(hall);
            Hall.put(session, theater);
            System.out.println("场次已成功添加");
        } else {
            System.out.println("影片不存在");
        }
    }
    // 修改场次
    private void modifySession() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请选择要修改的场次的放映厅:");
    	String hall = scanner.nextLine();
    	Show show = findSessionByHall(hall);
	    System.out.println("请输入更换的电影名（也可以不安排电影,若不安排电影，则输入不安排）:");
	    String title = scanner.nextLine();
	    if(title == "不安排") {
	    	sessions.put(show, null);
	    	return;
	    }
	    Movie movie = findMovieByTitle(title);
	    if(movie != null) {
	    	sessions.put(show, movie);
	    }
    }
    // 删除场次
    private void deleteSession() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入要删除场次的放映厅: ");
	    String halltodelete = scanner.nextLine();
	    Show show = findSessionByHall(halltodelete);
	    sessions.remove(show,show.getMovie());
	    System.out.println("删除成功！");
    }
    // 列出所有场次信息
    private void listAllSession() {
    	for(Show show : shows) {
    		System.out.println("放映厅: " + show.getHall());
    		System.out.println("影片名: " + show.getMovie().getTitle());
    		System.out.println("时段: " + show.getShowTime());
    		System.out.println("价格: " + show.getPrice());
    		System.out.println("------------------------");
    	}
    }
    // 用户管理
    private void userManagement() {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 列出所有用户（消费者）信息");
        System.out.println("2. 查询用户信息");
       
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
            	listAllCustomers();
                break;
            case 2:
            	searchCustomer();
                break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		listAllCustomers();
                	}else if(option == 2){
                		searchCustomer();
                	}
                }while(option != 1 || option != 2);
        }
    }
	// 列出所有用户（消费者）信息
    public void listAllCustomers() {
        for (Customer customer : customers) {
            System.out.println("用户ID: " + customer.getID());
            System.out.println("用户名: " + customer.getUsername());
            System.out.println("用户级别: " + customer.getRank());
            System.out.println("用户注册时间: " + customer.getRegistrationTime());
            System.out.println("用户累计消费总金额: " + customer.getAccuamount());
            System.out.println("用户累计消费次数: " + customer.getAccucount());
            System.out.println("用户手机号: " + customer.getPhoneNumber());
            System.out.println("用户邮箱: " + customer.getEmail());
            System.out.println("------------------------");
        }
    }
    // 查询用户信息
    private void searchCustomer() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("您要根据什么方式查询: ");
    	System.out.println("1.ID查询");
    	System.out.println("2.用户名查询");
    	int choice = scanner.nextInt();
    	if(choice == 1) {
    		System.out.println("请输入要查找的用户的ID: ");
    		scanner.nextLine();
    		int ID = scanner.nextInt();
    		findCustomerByID(ID);
    	} else if(choice == 2) {
    		System.out.println("请输入要查找的用户的用户名: ");
    		scanner.nextLine();
    		String username = scanner.nextLine();
    		Customer customer = findCustomerByUsername(username);
    		System.out.println("用户ID: " + customer.getID());
            System.out.println("用户名: " + customer.getUsername());
            System.out.println("用户级别: " + customer.getRank());
            System.out.println("用户注册时间: " + customer.getRegistrationTime());
            System.out.println("用户累计消费总金额: " + customer.getAccuamount());
            System.out.println("用户累计消费次数: " + customer.getAccucount());
            System.out.println("用户手机号: " + customer.getPhoneNumber());
            System.out.println("用户邮箱: " + customer.getEmail());
            System.out.println("------------------------");
    	}
    }
	// 通过片名来找到对应的电影
    private Movie findMovieByTitle(String title) {
        for (Movie movie : allMovies) {
        	if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
    // 通过对应的放映厅找到该场次
    private Show findSessionByHall(String hall) {
    	for(Show show : shows) {
    		if(show.getHall().equals(hall)) {
    			return show;
    		}
    	}
    	return null;
    }
    // 通过对应的放映厅找到该场次的座位信息
    private Theater findTheaterByHall(String hall) {
    	for(Show show : allShows) {
    		if(show.getHall().equals(hall)) {
    			for(Theater theater : allTheaters) {
    				if(theater.getShow().equals(show)) {
    					return theater;
    				}
    			}
    			
    		}
    	}
    	return null;
    }
    // 通过ID来找到对应的用户
    private Customer findCustomerByID(int ID) {
        for (Customer user : customers) {
        	if (user.getID().equals(ID)) {
                return user;
            }
        }
        return null;
    }
    // 通过用户名来找到对应的用户
    private Customer findCustomerByUsername(String username) {
        for (Customer user : customers) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}