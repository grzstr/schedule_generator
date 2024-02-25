import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class organization {
    private String name;
    private ArrayList<group> Groups = new ArrayList<group>();
    private ArrayList<event> AllEvents = new ArrayList<event>();
    private ArrayList<String> AllLecturers = new ArrayList<String>();
    private ArrayList<String> AllLocations = new ArrayList<String>();
    public organization(String name) {
        this.name = name;
    }

    public int convertTime(String time){
        String[] timeArray = time.split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        return hours*60 + minutes;
    }
    public String convertTime(int time){
        int hours = time/60;
        int minutes = time%60;
        return hours + ":" + minutes;
    }

    public int getEventsNumber()
    {
        int number = 0;
        for (int i = 0; i < AllEvents.size(); i++){
            number += AllEvents.get(i).getRepeatPerWeek();
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
                choice = rand.nextInt(AllEvents.size());
            } while (chosenNumbers.contains(choice) && AllEvents.get(choice).getRepeatPerWeek() == AllEvents.get(choice).getIs_repeated());
            AllEvents.get(choice).updateIs_repeated();
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
            }while(value + begin + AllEvents.get(choice).getDuration() > end);
            AllEvents.get(choice).setWeekday(day_choice);
            weekdayZerosList.set(day_choice, value + begin + AllEvents.get(choice).getDuration());
            AllEvents.get(choice).setTime(convertTime(value + begin));
        }
    }

    public void showSchedule(){
        for (int i = 0; i < AllEvents.size(); i++){
            System.out.println(AllEvents.get(i).getName() + " " + AllEvents.get(i).getWeekday() + " " + AllEvents.get(i).getTime());
        }
    }

    // Accessor methods
    public void addGroup(group g) {
        Groups.add(g);
    }
    public void removeGroup(int choice) {
        // Remove group from list by number in list
        Groups.remove(choice);

    }
    public ArrayList<group> getGroups() {
        return Groups;
    }
    public void addLecturer(String Lecturer) { AllLecturers.add(Lecturer);}
    public void removeLecturer(String Lecturer) {AllLecturers.remove(Lecturer);}
    public void addEvent(event Event){
        AllEvents.add(Event);
    }
    public void removeEvent(event Event){
        AllEvents.remove(Event);
    }
    public String getName() {
        return name;
    }
    public ArrayList<event> getAllEvents() {
        return AllEvents;
    }
    public ArrayList<String> getAllLecturers() {
        return AllLecturers;
    }
    public ArrayList<String> getAllLocations() {
        return AllLocations;
    }

}
