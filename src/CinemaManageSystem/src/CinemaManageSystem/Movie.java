package CinemaManageSystem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Movie {
    private String title;     // 片名
    private String director;  // 导演
    private String leadActor; // 主演
    private String synopsis;  // 剧情简介
    private int duration;     // 时长（用分钟表示）
    public Movie(String title, String director, String leadActor, String synopsis, int duration) {
        this.title = title;
        this.director = director;
        this.leadActor = leadActor;
        this.synopsis = synopsis;
        this.duration = duration;
    }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title;}
    public void setDirector(String director) { this.director = director; }
    public String getDirector() { return director;}
    public void setLeadActor(String leadActor) { this.leadActor = leadActor; }
    public String getLeadActor() { return leadActor;}
    public void setSynopsis(String synopsis) { this.synopsis = synopsis; }
    public String getSynopsis() { return synopsis;}
    public void setDuration(int duration) { this.duration = duration; }
    public Integer getDuration() { return duration;}
    // 创建电影
    public Movie CreateMovie() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("请输入片名:");
    	String title = scanner.nextLine();
    	System.out.println("请输入导演名:");
    	String director = scanner.nextLine();
    	System.out.println("请输入主演名:");
    	String leadActor = scanner.nextLine();
    	System.out.println("请输入简介:");
    	String synopsis = scanner.nextLine();
    	System.out.println("请输入时长:");
    	int duration = scanner.nextInt();
    	Movie movie = new Movie(title,director,leadActor,synopsis,duration);
        movie.setTitle(title);
    	return movie;
    }
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", leadActor='" + leadActor + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", duration=" + duration +
                '}';
    }
}