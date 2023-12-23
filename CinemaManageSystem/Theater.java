package CinemaManageSystem;
import java.util.ArrayList;
import java.util.List;

public class Theater {
		private int[][] seats;
		private Show show;
		public static final int seating_capacity = 7 * 12;
		public static final int SEAT_ROWS = 7;
		public static final int SEAT_COLUMNS = 12;
		public int Available_seats;
		
		public Theater(Show show,int Available_seats) {
			this.show = show;
			this.seats = new int[7][12];
			this.Available_seats = Available_seats;
		}
		public Show getShow(){
			return show;
		}
		public int[][] getSeats(){
			return seats;
		}
		public int getAvailable_seats(){
			return Available_seats;
		}
}