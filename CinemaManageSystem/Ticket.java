package CinemaManageSystem;
import java.util.ArrayList;
import java.util.List;

public class Ticket {
	private String movieTitle;
	private String screeningTime;
	private String username;
	private double paymentAmount;
	private String ticketID;
	
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