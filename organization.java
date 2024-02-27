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
