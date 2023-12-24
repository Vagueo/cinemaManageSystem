package CinemaManageSystem;
import java.util.ArrayList;
import java.util.List;

public class Theater {
		private int[][] seats; // 座位信息 
		private Show show;
		public static final int seating_capacity = 7 * 12;  // 放映厅容量，即座位总数
		public static final int SEAT_ROWS = 7;  // 座位行数
		public static final int SEAT_COLUMNS = 12; // 座位列数
		public int Available_seats;    // 空余座位数
		
		public Theater(Show show,int Available_seats) {
			this.show = show;
			this.seats = new int[7][12];
			this.Available_seats = Available_seats;
		}
		public void setShow(Show show){
			this.show = show;
		}
		public Show getShow(){
			return show;
		}
		public int[][] getSeats(){
			return seats;
		}
		public void setAvailable_seats(int Available_seats){
			this.Available_seats = Available_seats;
		}
		public int getAvailable_seats(){
			return Available_seats;
		}
		

		// 计算空闲座位数
		public Integer calculateVacantSeats(Theater theater) {
		    int vacantSeats = 0;
		    for (int i = 0; i < theater.getSeats().length; i++) {
		        for (int j = 0; j < theater.getSeats()[i].length; j++) {
		            if (theater.getSeats()[i][j] == 0) {
		                vacantSeats++;
		            }
		        }
		    }
		    return vacantSeats;
		}
}