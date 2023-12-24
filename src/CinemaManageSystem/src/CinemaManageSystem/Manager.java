package CinemaManageSystem;
import java.text.SimpleDateFormat;
import java.util.*;

public class Manager extends User{
    private String username;
    private String password;
    public List<Customer> customers = new ArrayList<>();
    public HashMap<Show,Movie> sessions = new HashMap<Show,Movie>();
    public List<Show> allShows = new ArrayList<>();
    public List<Movie> movies = new ArrayList<>();  // 已安排了场次的电影信息
    public List<Movie> allMovies = new ArrayList<>();     // 所有电影信息（包括未排场次的）
    public HashMap<Show,Theater> Hall = new HashMap<Show,Theater>(); // 放映厅及其对应信息
    public List<Theater> theaters = new ArrayList<>();   // 已安排了场次的信息
    public List<Theater> allTheaters = new ArrayList<>();    // 所有信息（包括未排场次的）
	
	public Manager(int ID,String username,String password,String PhoneNumber,String email) {
		super(ID,username,password,"经理",PhoneNumber,email);
		
		Customer customer1 = new Customer(0,"噤若寒蝉1","14579605312","jingruohanchan@ynu.com","Ynu_jing123","铜牌用户",0,0);
		Customer customer2 = new Customer(0,"水波不兴2","15867005329","shuibobuxing@ynu.com","Ynu_shuibobuxing456","金牌用户",100000,50);
		Customer customer3 = new Customer(0,"似水流年3","16249506072","sihsuiliunian@ynu.com","Ynu_sishuiliunian789","银牌用户",15000,100);
		customers.add(customer1);
		customers.add(customer2);
		customers.add(customer3);
		
		Movie movie1 = new Movie("流浪地球","郭帆","吴京、屈楚萧、李光洁、吴孟达、赵今麦","影片根据刘慈欣的同名小说改编，故事背景设定在2075年，讲述了太阳即将毁灭，毁灭之后的太阳系已经不适合人类生存，而面对绝境，人类将开启“流浪地球”计划，试图带着地球一起逃离太阳系，寻找人类新家园的故事。",120);
		Movie movie2 = new Movie("你好李焕英","贾玲","贾玲、张小斐","该片根据2016年的同名小品及贾玲亲身经历改编，讲述了刚考上大学的女孩贾晓玲经历了一次人生的大起大落后情绪失控，随后意外穿越回到了二十世纪八十年代，与20年前正值青春的母亲李焕英相遇的故事。",128);
		Movie movie3 = new Movie("泰坦尼克号","詹姆斯·卡梅隆","莱昂纳多·迪卡普里奥、凯特·温斯莱特","影片以1912年泰坦尼克号在其处女航时触礁冰山而沉没的事件为背景，讲述了处于不同阶层的两个人穷画家杰克和贵族女露丝抛弃世俗的偏见坠入爱河，最终杰克把生存的机会让给了露丝的感人故事。",194);
	    Movie movie4 = new Movie("肖申克的救赎","弗兰克·德拉邦特","蒂姆·罗宾斯、摩根·弗里曼","该片改编自斯蒂芬·埃德温·金1982年的中篇小说《肖申克的救赎》，主要讲述了银行家安迪因被误判为枪杀妻子及其情人的罪名入狱后，他不动声色、步步为营地谋划自我拯救并最终成功越狱，重获自由的故事。",142);
	    Movie movie5 = new Movie("怦然心动","罗伯·莱纳","玛德琳·卡罗尔、卡兰·麦克奥利菲","朱莉·贝克（玛德琳·卡罗尔饰）虔诚地相信三件事：树是圣洁的（特别是她最爱的梧桐树）、她在后院里饲养的鸡生出来的鸡蛋是最卫生的、以及总有一天她会和布莱斯·罗斯基（卡兰·麦克奥利菲饰）接吻。二年级时在看到布莱斯的蓝眼睛那一瞬间，朱莉的心就被他击中了。不幸的是，布莱斯对她从来没有感觉。而且，他认为朱莉有点怪，怎么会有人把养鸡和坐在树上看成乐趣呢。没想到，到了八年级，布莱斯开始觉得朱莉不同寻常的兴趣和对于家庭的自豪感使她显得很有魅力。而朱莉则开始觉得布莱斯漂亮的蓝眼睛也许和他本人一样其实很空洞，毕竟，怎么会有人不把别人对树和鸡的感情当回事呢?",107);
	    Movie movie6 = new Movie("长津湖","陈凯歌","吴京、易烊千玺","该片以抗美援朝战争第二次战役中的长津湖战役为背景，讲述了一段波澜壮阔的历史，在极寒严酷环境下，中国人民志愿军东线作战部队凭着钢铁意志和英勇无畏的战斗精神，扭转战场态势，为长津湖战役胜利作出重要贡献的故事。",176);
		
	    Show show1 = new Show(movie1,"放映厅1","14:00",38.5);
		Show show2 = new Show(movie2,"放映厅2","15:30",38.5);
		Show show3 = new Show(movie3,"放映厅3","20:30",40.5);
		Show show4 = new Show(movie4,"放映厅4","21:00",40.5);
		Show show5 = new Show(movie5,"放映厅5","22:00",38.5);
		Show show6 = new Show(null,"放映厅6","10:30",38.5); 
		Show show7 = new Show(null,"放映厅7","11:30",38.5); 
		Show show8 = new Show(null,"放映厅8","13:30",36.5); 
		Show show9 = new Show(null,"放映厅9","16:30",36.5); 
		Show show10 = new Show(null,"放映厅10","19:30",38.5); 
		
		Theater theater1 = new Theater(show1,84);
	    Theater theater2 = new Theater(show2,84);
	    Theater theater3 = new Theater(show3,84);
	    Theater theater4 = new Theater(show4,84);
	    Theater theater5 = new Theater(show5,84);
	    Theater theater6 = new Theater(show6,84);
	    Theater theater7 = new Theater(show7,84);
	    Theater theater8 = new Theater(show8,84);
	    Theater theater9 = new Theater(show9,84);
	    Theater theater10 = new Theater(show10,84);
	    		
		sessions.put(show1, movie1);
		sessions.put(show2, movie2);
		sessions.put(show3, movie3);
		sessions.put(show4, movie4);
		sessions.put(show5, movie5);
		sessions.put(show5, movie5);
		
		allShows.add(show1);
		allShows.add(show2);
		allShows.add(show3);
		allShows.add(show4);
		allShows.add(show5);
		
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		movies.add(movie4);
		movies.add(movie5);
		
		allMovies.add(movie1);
		allMovies.add(movie2);
		allMovies.add(movie3);
		allMovies.add(movie4);
		allMovies.add(movie5);
		allMovies.add(movie6);
		
		Hall.put(show1, theater1);
		Hall.put(show2, theater2);
		Hall.put(show3, theater3);
		Hall.put(show4, theater4);
		Hall.put(show5, theater5);
		
		theaters.add(theater1);
		theaters.add(theater2);
		theaters.add(theater3);
		theaters.add(theater4);
		theaters.add(theater5);
		
		allTheaters.add(theater1);
		allTheaters.add(theater2);
		allTheaters.add(theater3);
		allTheaters.add(theater4);
		allTheaters.add(theater5);
		allTheaters.add(theater6);
		allTheaters.add(theater7);
		allTheaters.add(theater8);
		allTheaters.add(theater9);
		allTheaters.add(theater10);
		
	}
    // 交互界面
	public void showManagerMenu(Admin admin) {
    	Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        Manager loggedInUser = null;
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
            	System.out.println("请选择你所要进行的操作:");
                System.out.println("1. 密码管理");
                System.out.println("2. 影片管理");
                System.out.println("3. 排片管理");
                System.out.println("4. 用户管理");
                System.out.println("5. 退出登录");
                int choice;
                while(true) {
                	choice = scanner.nextInt();
                	switch (choice) {
	                    case 1:
	                    	passwordManagement(loggedInUser);
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
	public Manager login(Admin admin) {   
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------登录-------------");
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        while(true){
        	for(Manager manager : admin.managers) {
        		if (username.equals(manager.getUsername()) && password.equals(manager.getPassword())) {
	        		System.out.println("登录成功！");
	        		return manager;
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

	
    // 密码管理
    public void passwordManagement(Manager manager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择你所要进行的操作:");
        System.out.println("1. 修改自身密码");
        System.out.println("2. 重置指定用户（消费者）的密码");
        int option = scanner.nextInt();
        
        switch (option) {
            case 1:
                changePassword(manager);
                break;
            case 2:
            	resetCustomerPassword();
                break;
            default:
                do{
                	System.out.println("无效的选择，请重新选择你所要进行的操作:");
                	option = scanner.nextInt();
                	if(option == 1) {
                		changePassword(manager);
                	}else if(option == 2){
                		resetCustomerPassword();
                	}
                }while(option != 1 || option != 2);
        }
    }
    // 修改自身密码
    public void changePassword(Manager manager) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入旧密码：");
        String oldPassword = scanner.nextLine();
        while(true) {
        	if (oldPassword.equals(manager.getPassword())) {
        		System.out.println("请输入新密码：");
        		String newPassword = scanner.nextLine();
        		while(newPassword.equals(oldPassword)) {
        			System.out.println("新密码与原密码相同！");
        			System.out.println("请重新输入: ");
        			newPassword = scanner.nextLine();
        		}
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
        while(true) {
        	if (customer != null) {
	        	String OriginalPassword = "Ynuinfo.123";
	        	customer.setPassword(OriginalPassword);
	     		System.out.println("密码重置成功！");
	     		System.out.println("重置的初始密码为：Ynuinfo.123");
	     		break;
        	} else {
            	System.out.println("用户不存在！");
                System.out.println("请重新输入输入所要指定重置的影城方用户的用户名:");
                username = scanner.nextLine();
                customer = findCustomerByUsername(username);
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
        System.out.println("-------------正在上映的电影-------------");
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
        if(count == allMovies.size()) {
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
        	System.out.println("影片信息添加成功！");
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
	    	
	    	int option;
	    	String ch = "是";
	    	while(true) {
	    		option = scanner.nextInt();
		    	switch (option) {
		    	     case 1:
		    	         System.out.println("请输入新的片名:");
		    	         String newTitle = scanner.nextLine();
		    	         scanner.nextLine(); // 消耗回车键
		    	         movie.setTitle(newTitle);
		    	         break;
		    	     case 2:
		    	    	 System.out.println("请输入新的导演名:");
		    	         String newDirector = scanner.nextLine();
		    	         scanner.nextLine(); // 消耗回车键
		    	         movie.setDirector(newDirector);
		    	         break;
		    	     case 3:
		    	    	 System.out.println("请输入新的主演:");
		    	         String newLeadActor = scanner.nextLine();
		    	         scanner.nextLine(); // 消耗回车键
		    	         movie.setLeadActor(newLeadActor);
		    	         break;
		    	     case 4:
		    	    	 System.out.println("请输入新的剧情简介:");
		    	         String newSynopsis = scanner.nextLine();
		    	         scanner.nextLine(); // 消耗回车键
		    	         movie.setSynopsis(newSynopsis);
		    	         break;
		    	     case 5:
		    	    	 System.out.println("请输入新的时长:");
		    	         int newDuration = scanner.nextInt();
		    	         scanner.nextLine(); // 消耗回车键
		    	         movie.setDuration(newDuration);
		    	         break;
		    	     case 6:
		    	         return;
		    	     default:
		    	    	 System.out.println("无效的选择，请重新输入您的选择:");
		    	    	 break;
		    	}
		    	if(option >= 1 && option <= 5) {
		    		System.out.println("修改成功！");
		    		System.out.println("是否还要继续修改电影信息？（是或否）");
		    	    ch = scanner.nextLine();
		    	}
		    	if(ch.equals("否")) {
		    		break;
		    	}
		    }
	    }else {
            System.out.println("未查询到该电影或是该电影还未安排任何场次！");
        }
    }
    // 删除影片的信息
    private void deleteMovie() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入所要指定删除的电影的片名：");
    	String title = scanner.nextLine();
    	Movie movieToDelete = findMovieByTitle(title);
        movies.remove(movieToDelete);
        for(Show show : allShows) {
        	if(show.getMovie().equals(movieToDelete)) {
        		sessions.remove(show);
        		show.setMovie(null);
        	}
        }
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
	             if(movie != null) {
		             System.out.println("------------------------------");
		             System.out.println("片名:  " + movie.getTitle());
			         System.out.println("导演: " + movie.getDirector());
			         System.out.println("主演: " + movie.getLeadActor());
			         System.out.println("简介: " + movie.getSynopsis());
			         System.out.println("时长: " + movie.getDuration() + "分钟");
			         System.out.println("------------------------------");
	             }else {
	            	 System.out.println("上映的影片中未查询到该影片！");
	             }
	         }else if(option == 2) {
	        	 System.out.println("请输入导演名:");
	        	 String director = scanner.nextLine();
	        	 int count = 0;
	        	 for(Movie movie : movies) {
	        		 if(movie.getDirector().equals(director)) {
	        			 System.out.println("------------------------------");
		        		 System.out.println(movie.getDirector() + "导演的电影清单: ");
			             System.out.println("片名:  " + movie.getTitle());
			             System.out.println("------------------------------");
			             count++;
	        		 } 
	        	 }
	        	 if(count == 0) {
	        		 System.out.println("上映的影片中未查询到该导演的影片！");
	        	 }
	         }
	         else {
	        	 System.out.println("无效的选择，请重新选择你所要进行的操作:");
	        	 option = scanner.nextInt();
	         }
	         System.out.println("是否还要继续查询？（是或否）");
	         str = scanner.nextLine();
        }while(str.equals("是"));
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
                		addSession();
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
        System.out.println("放映厅：（放映厅1-10）");
        String hall = scanner.nextLine();
        while(true) {
        	int count = 0;
        	for(Show show : allShows) {
	        	if(show.getHall().equals(hall)) {
	        		System.out.println("该放映厅已存在！");
	        		System.out.println("请重新输入放映厅：（放映厅1-10）");
	        		hall = scanner.nextLine();
	        		count++;
	        		break;
	        	}
	        }
        	if(count == 0) {
        		break;
        	}
        }
        System.out.println("放映时间：");
        String showTime = scanner.nextLine();
        System.out.println("价格：");
        double price = scanner.nextDouble();
        Movie movie = findMovieByTitle(movieTitle);
        if (movie != null) {
            Show show = new Show(movie, hall, showTime, price);
            sessions.put(show, movie);
            if (!movies.contains(movie)) {
                 movies.add(movie);
            }
            Theater theater = findTheater(show);
            Hall.put(show, theater);
            if (!theaters.contains(theater)) {
                theaters.add(theater);
            }
            System.out.println("场次已成功添加！");
        } else {
            System.out.println("影片不存在！");
        }
    }
    // 修改场次
    private void modifySession() {
    	Scanner scanner = new Scanner(System.in);
    	String ask;
    	System.out.println("请选择要修改的场次的放映厅:");
    	String hall = scanner.nextLine();
    	Show show = findSessionByHall(hall);
    	while(show == null) {
    		System.out.println("该场次还未安排！");
    		System.out.println("请重新输入要修改的场次的放映厅:");
        	hall = scanner.nextLine();
        	show = findSessionByHall(hall);
    	}
	    System.out.println("请输入更换的电影名（也可以不安排电影,若不安排电影，则输入不安排）:");
	    String title = scanner.nextLine();
	    if(title.equals("不安排")) {
	    	sessions.put(show, null);
	    	show.setMovie(null);
	    	System.out.println("该场次已不安排影片！");
	    	return;
	    }
	    Movie movie = findMovieByTitle(title);
	    if(movie != null) {
	    	sessions.put(show, movie);
	    	System.out.println("该场次修改后的信息为: ");
	    	System.out.println("放映厅: " + show.getHall());
    		System.out.println("影片名: " + show.getMovie().getTitle());
    		System.out.println("时段: " + show.getShowTime());
    		System.out.println("价格: " + show.getPrice());
    		System.out.println("------------------------");
	    }
    }
    // 删除场次
    private void deleteSession() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入要删除场次的放映厅: ");
	    String halltodelete = scanner.nextLine();
	    Show show = findSessionByHall(halltodelete);
	    if(show != null) {
	    	sessions.remove(show,show.getMovie());
	    	show.setMovie(null);
	    	System.out.println("删除成功！");
	    }else {
	    	System.out.println("未查询该场次！");
	    }
    }
    // 列出所有场次信息
    private void listAllSession() {
    	for(Show show : allShows) {
    		if(show.getMovie() != null) {
	    		System.out.println("放映厅: " + show.getHall());
	    		System.out.println("影片名: " + show.getMovie().getTitle());
	    		System.out.println("时段: " + show.getShowTime());
	    		System.out.println("价格: " + show.getPrice());
	    		System.out.println("------------------------");
    		}
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
        for (Movie movie : movies) {
        	if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }
    // 通过对应的放映厅找到该场次
    private Show findSessionByHall(String hall) {
    	for(Show show : allShows) {
    		if(show.getHall().equals(hall)) {
    			return show;
    		}
    	}
    	return null;
    }
    // 通过对应的放映厅找到该场次的座位信息
    private Theater findTheater(Show show) {
    	for(Theater theater : allTheaters) {
    		if(theater.getShow().equals(show)) {
    			return theater;
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