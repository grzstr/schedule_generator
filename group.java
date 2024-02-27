import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class group {
    private String name;
    private ArrayList<event> Events = new ArrayList<event>();
    private ArrayList<String> Members = new ArrayList<String>();
    
    public int convertTime(String time){
        String[] timeArray = time.split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        return hours*60 + minutes;
    }
    public String convertTime(int time){
        int hours = time/60;
        int minutes = time%60;
        if (hours < 10){
            if (minutes < 10){
                return "0" + hours + ":0" + minutes;
            }
            return "0" + hours + ":" + minutes;
        }
        return hours + ":" + minutes;
    }
    public int getEventsNumber()
    {
        int number = 0;
        for (int i = 0; i < Events.size(); i++){
            number += Events.get(i).getRepeatPerWeek();
        }
        return number;
    }
    
    public void generateSchedule(String beginTime, String endTime, int breakTime, boolean withoutWeekend){
        Random rand = new Random();
        int day_choice, choice, value;
        int begin = convertTime(beginTime);
        int end = convertTime(endTime);
        int eventsNumber = getEventsNumber();

        ArrayList<Integer> weekdayZerosList = new ArrayList<>(Collections.nCopies(7, 0));
        ArrayList<Integer> chosenNumbers = new ArrayList<Integer>();

        for (int i = 0; i < eventsNumber; i++) {
            // Wybierz wydarzenie
            do {
                choice = rand.nextInt(Events.size());
            } while (chosenNumbers.contains(choice) && Events.get(choice).getRepeatPerWeek() == Events.get(choice).getIs_repeated());
            Events.get(choice).updateIs_repeated();
            chosenNumbers.add(choice);

            // Jeżeli czas trwania wydarzenia jest większy niż czas do końca dnia to wybierz inny dzień
            do
            {
                if (withoutWeekend) {
                    day_choice = rand.nextInt(5);
                } else {
                    day_choice = rand.nextInt(7);
                }
                value = weekdayZerosList.get(day_choice);
                if (value == 0)
                {
                    value = begin;
                }
            }while((value + Events.get(choice).getDuration()) > end);
            Events.get(choice).setWeekday(day_choice);
            weekdayZerosList.set(day_choice, value + Events.get(choice).getDuration());
            Events.get(choice).setTime(convertTime(value));
        }
    }

    public void showSchedule(){
        if (Events.size() == 0){
            System.out.println("No events found");
        }
        for (int i = 0; i < Events.size(); i++){
            System.out.println(Events.get(i).getName() + " - " + Events.get(i).getWeekday() + " - " + Events.get(i).getTime());
        }
    }
    
    public group(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<event> getEvents() {
        return Events;
    }

    public void addEvent(event e) {
        Events.add(e);
    }
    public void addMember(String member) {
        Members.add(member);
    }
    public void removeMember(String member) {
        Members.remove(member);
    }
    public ArrayList<String> getMembers() {
        return Members;
    }

    public void loadEvents(ArrayList<event> events) {
        Events = events;
    }
}
