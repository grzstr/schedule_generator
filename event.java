import java.util.ArrayList;
public class event {
    private String name;
    private String date;

    private String weekday;
    private String time;
    private int repeatPerWeek = 1;
    private int is_repeated = 0;
    private int duration;
    private String location;
    private String lecturer;
    private ArrayList<String> lecturers = new ArrayList<String>();

    public event(String name, int duration, String location) {
        this.name = name;
        this.location = location;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setWeekday(int dayNumber)
    {
        if (dayNumber == 0) {
            this.weekday = "Monday";
        }
        else if(dayNumber == 1) {
            this.weekday = "Tuesday";
        }
        else if (dayNumber == 2){
            this.weekday = "Wednesday";
        }
        else if (dayNumber == 3){
            this.weekday = "Thursday";
        }
        else if (dayNumber == 4){
            this.weekday = "Friday";
        }
        else if (dayNumber == 5){
            this.weekday = "Saturday";
        }
        else if (dayNumber == 6){
            this.weekday = "Sunday";
        }else
        {
            System.out.println("Invalid day number");
        }
    }

    public void setTime(String time) {
        this.time = time;
    }
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
    public void addLecturer(String lecturer) {
        lecturers.add(lecturer);
    }

    public int getRepeatPerWeek() {
        return repeatPerWeek;
    }
    public void setRepeatPerWeek(int repeatPerWeek) {
        this.repeatPerWeek = repeatPerWeek;
    }
    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getLecturer() {
        return lecturer;
    }
    public int getDuration() {
        return duration;
    }
    public String getLocation() {
        return location;
    }
    public ArrayList<String> getLecturers() {
        return lecturers;
    }
    public void updateIs_repeated()
    {
        is_repeated++;
    }

    public int getIs_repeated()
    {
        return is_repeated;
    }
    public String getWeekday() {
        return weekday;
    }
}
