package CinemaManageSystem;
import java.util.Scanner;
public class Main {
	static Admin admin = new Admin(123456789, "admin", "18074236591", "admin@ynu.com");
	static Customer customer = new Customer(0,null,null,null,null,"铜牌用户",0,0);
	static Manager manager1 = new Manager(123456,"刘经理","ynu_liu","13569405132","liujingli@yun.com");
	static Receptionist receptionist1 = new Receptionist(124783,"杨前台","ynu_yang","15698406175","yangqiantai@ynu.com");
	static Customer customer1 = new Customer(1478965,"噤若寒蝉1","14579605312","jingruohanchan@ynu.com","Ynu_jing123","铜牌用户",0,0);
	static Customer customer2 = new Customer(1569504,"水波不兴2","15867005329","shuibobuxing@ynu.com","Ynu_shuibobuxing456","金牌用户",100000,50);
	static Customer customer3 = new Customer(1379524,"似水流年3","16249506072","sihsuiliunian@ynu.com","Ynu_sishuiliunian789","银牌用户",15000,100);
	
	static Movie movie1 = new Movie("流浪地球","郭帆","吴京、屈楚萧、李光洁、吴孟达、赵今麦","影片根据刘慈欣的同名小说改编，故事背景设定在2075年，讲述了太阳即将毁灭，毁灭之后的太阳系已经不适合人类生存，而面对绝境，人类将开启“流浪地球”计划，试图带着地球一起逃离太阳系，寻找人类新家园的故事。",120);
	static Movie movie2 = new Movie("你好，李焕英","贾玲","贾玲、张小斐","该片根据2016年的同名小品及贾玲亲身经历改编，讲述了刚考上大学的女孩贾晓玲经历了一次人生的大起大落后情绪失控，随后意外穿越回到了二十世纪八十年代，与20年前正值青春的母亲李焕英相遇的故事。",128);
	static Movie movie3 = new Movie("泰坦尼克号","詹姆斯·卡梅隆","莱昂纳多·迪卡普里奥、凯特·温斯莱特","影片以1912年泰坦尼克号在其处女航时触礁冰山而沉没的事件为背景，讲述了处于不同阶层的两个人穷画家杰克和贵族女露丝抛弃世俗的偏见坠入爱河，最终杰克把生存的机会让给了露丝的感人故事。",194);
    static Movie movie4 = new Movie("肖申克的救赎","弗兰克·德拉邦特","蒂姆·罗宾斯、摩根·弗里曼","该片改编自斯蒂芬·埃德温·金1982年的中篇小说《肖申克的救赎》，主要讲述了银行家安迪因被误判为枪杀妻子及其情人的罪名入狱后，他不动声色、步步为营地谋划自我拯救并最终成功越狱，重获自由的故事。",142);
    static Movie movie5 = new Movie("怦然心动","罗伯·莱纳","玛德琳·卡罗尔、卡兰·麦克奥利菲","朱莉·贝克（玛德琳·卡罗尔饰）虔诚地相信三件事：树是圣洁的（特别是她最爱的梧桐树）、她在后院里饲养的鸡生出来的鸡蛋是最卫生的、以及总有一天她会和布莱斯·罗斯基（卡兰·麦克奥利菲饰）接吻。二年级时在看到布莱斯的蓝眼睛那一瞬间，朱莉的心就被他击中了。不幸的是，布莱斯对她从来没有感觉。而且，他认为朱莉有点怪，怎么会有人把养鸡和坐在树上看成乐趣呢。没想到，到了八年级，布莱斯开始觉得朱莉不同寻常的兴趣和对于家庭的自豪感使她显得很有魅力。而朱莉则开始觉得布莱斯漂亮的蓝眼睛也许和他本人一样其实很空洞，毕竟，怎么会有人不把别人对树和鸡的感情当回事呢?",107);
    static Movie movie6 = new Movie("长津湖","陈凯歌","吴京、易烊千玺","该片以抗美援朝战争第二次战役中的长津湖战役为背景，讲述了一段波澜壮阔的历史，在极寒严酷环境下，中国人民志愿军东线作战部队凭着钢铁意志和英勇无畏的战斗精神，扭转战场态势，为长津湖战役胜利作出重要贡献的故事。",176);
	
    static Show show1 = new Show(movie1,"放映厅1","14:00",38.5);
	static Show show2 = new Show(movie2,"放映厅2","15:30",38.5);
	static Show show3 = new Show(movie3,"放映厅3","20:30",40.5);
	static Show show4 = new Show(movie4,"放映厅4","21:00",40.5);
	static Show show5 = new Show(movie5,"放映厅5","22:00",38.5);
	static Show show6 = new Show("放映厅6","10:30",38.5); 
	static Show show7 = new Show("放映厅7","11:30",38.5); 
	static Show show8 = new Show("放映厅8","13:30",36.5); 
	static Show show9 = new Show("放映厅9","16:30",36.5); 
	static Show show10 = new Show("放映厅10","19:30",38.5); 
	static Theater theater1 = new Theater(show1,84);
    static Theater theater2 = new Theater(show2,84);
    static Theater theater3 = new Theater(show3,84);
    static Theater theater4 = new Theater(show4,84);
    static Theater theater5 = new Theater(show5,84);
    static Theater theater6 = new Theater(show6,84);
    static Theater theater7 = new Theater(show7,84);
    static Theater theater8 = new Theater(show8,84);
    static Theater theater9 = new Theater(show9,84);
    static Theater theater10 = new Theater(show10,84);
    
	public static void main(String[] args) {
		admin.users.add(manager1);
		admin.users.add(receptionist1);
		
		manager1.customers.add(customer1);
		manager1.customers.add(customer2);
		manager1.customers.add(customer3);
		
		manager1.sessions.put(show1, movie1);
		manager1.sessions.put(show2, movie2);
		manager1.sessions.put(show3, movie3);
		manager1.sessions.put(show4, movie4);
		manager1.sessions.put(show5, movie5);
		
		manager1.allMovies.add(movie1);
		manager1.allMovies.add(movie2);
		manager1.allMovies.add(movie3);
		manager1.allMovies.add(movie4);
		manager1.allMovies.add(movie5);
		manager1.allMovies.add(movie6);
		
		manager1.Hall.put(show1, theater1);
		manager1.Hall.put(show2, theater2);
		manager1.Hall.put(show3, theater3);
		manager1.Hall.put(show4, theater4);
		manager1.Hall.put(show5, theater5);
		
		manager1.allTheaters.add(theater1);
		manager1.allTheaters.add(theater2);
		manager1.allTheaters.add(theater3);
		manager1.allTheaters.add(theater4);
		manager1.allTheaters.add(theater5);
		manager1.allTheaters.add(theater6);
		manager1.allTheaters.add(theater7);
		manager1.allTheaters.add(theater8);
		manager1.allTheaters.add(theater9);
		manager1.allTheaters.add(theater10);
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要进行操作的类型(如若要结束则输入0):");
		System.out.println("1.管理员");
		System.out.println("2.经理");
		System.out.println("3.前台");
		System.out.println("4.用户");
		int userType = scanner.nextInt();
		scanner.nextLine();
		boolean over = false;
		while(true) {
			switch (userType){
				case 1:
					// 管理员登录
					admin.login();
					break;
				case 2:
		        	// 经理登录
		            manager1.login();
		            break;
		        case 3:
		        	// 前台登录
		        	receptionist1.login(manager1);
		            break;
		        case 4:
		        	// 用户的交互界面
		        	customer.showCustomerMenu(manager1,customer);
		            break;
				default:
					over = true;
					break;
			}
			if(over) {
				System.out.println("-----------结束-----------");
				break;
			}
			System.out.println("请输入要进行操作的类型(如若要结束则输入0):");
			System.out.println("1.管理员");
			System.out.println("2.经理");
			System.out.println("3.前台");
			System.out.println("4.用户");
			userType = scanner.nextInt();
		}
	}
}