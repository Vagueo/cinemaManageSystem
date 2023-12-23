package CinemaManageSystem;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
	private String movieTitle;   // 影片名
	private String screeningTime; // 放映时段
	private String username;    // 用户名
	private double paymentAmount; // 实付金额
	private String ticketID;    // 电影编号
	
	public Ticket(String movieTitle, String screeningTime, String username, double paymentAmount,String ticketID) {
        this.movieTitle = movieTitle;
        this.screeningTime = screeningTime;
        this.username = username;
        this.paymentAmount = paymentAmount;
        this.ticketID = ticketID;
    }
	public String getMovieTitle() {
        return movieTitle;
    }

    public String getScreeningTime() {
        return screeningTime;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getUserName() {
        return username;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }
}