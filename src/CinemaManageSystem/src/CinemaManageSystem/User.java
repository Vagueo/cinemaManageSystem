package CinemaManageSystem;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class User {
	public int ID;   // 用户ID
    public String username;     // 用户名
    public String password;     // 密码
    public String registrationTime;   // 用户注册时间
    public String userType;           // 用户类型
    public String phoneNumber;        // 用户手机号
    public String email;              // 用户邮箱
	
    public User(int ID,String username,String password,String userType,String phoneNumber,String email) {
        this.ID = ID = generateId();
        this.password = password;
        this.username = username;
        this.userType = userType;
        this.phoneNumber = phoneNumber;
        this.email = email;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = new Date();
        String formattedTime = dateFormat.format(currentTime);
        this.registrationTime = formattedTime;
    }
    
	public void setID(Integer ID) { this.ID = ID; }
	public Integer getID() { return ID; }		
	public Integer generateId() {
		 Random id = new Random();
		 int ID = id.nextInt();
		 if(ID < 0) {
			 return -ID;
		 }
		 return ID;
	}
	public void setUsername(String username) { this.username = username; }
    public String getUsername() { return username; }
    public void setPassword(String password) {this.password = password;}
	public String getPassword() { return password; }
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
