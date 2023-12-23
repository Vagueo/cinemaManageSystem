package CinemaManageSystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class User {
	public int ID;   // 用户ID
    public String username;     // 用户名
    public String registrationTime;   // 用户注册时间
    public String userType;           // 用户类型
    public String phoneNumber;        // 用户手机号
    public String email;              // 用户邮箱
	
    public User(int ID,String username,String userType,String phoneNumber,String email) {
        this.ID = ID;
        this.username = username;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String formattedTime = dateFormat.format(currentTime);
        this.registrationTime = formattedTime;
    }
    // 重置用户密码
	public void resetPassword() {
		String OriginalPassword = "Ynuinfo.123";
		String password = OriginalPassword;
		System.out.println("密码重置成功！");
		System.out.println("重置的初始密码为：ynuinfo123");
	}
	
	public void setID(Integer ID) { this.ID = ID; }
	public Integer getID() { return ID; }		
	public Integer generateId() {
		 Random id = new Random();
		 int ID = id.nextInt();
		 return ID;
	}
	public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }
    public void setRegistrationTime(String registrationTime) { this.registrationTime = registrationTime;}
    // 获得当前的注册时间
    public String getRegistrationTime() { return registrationTime; }
    public void setUserType(String userType) { this.userType = userType; }
	public String getUserType() { return userType; }
	public void setEmail(String email) { this.email = email; }
	public String getEmail() { return email; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public String getPhoneNumber() { return phoneNumber; }
}
