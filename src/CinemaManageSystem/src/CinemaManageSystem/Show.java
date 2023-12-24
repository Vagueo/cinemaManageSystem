package CinemaManageSystem;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Show{
	    private Movie movie;
	    private String hall;        // 放映厅
	    private String showTime;   // 放映时段 
	    double price;             // 价格
	    
	    public Show(Movie movie,String hall,String showTime,double price) {
	        this.movie = movie;
	        this.hall = hall;
	        this.showTime = showTime;
	        this.price = price;
	   }
	   
	   public void setMovie(Movie movie) { this.movie = movie;}
	   public Movie getMovie() { return movie;}
	   public void setHall(String hall) { this.hall= hall; }
	   public String getHall() { return hall; }
	   public void setShowTime(String showTime) { this.showTime = showTime;}
	   public String getShowTime() { return showTime;}
	   public void setPrice(double price) { this.price = price;}
	   public Double getPrice() { return price; } 
	   
}

