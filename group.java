import java.util.ArrayList;
public class group {
    private String name;
    private ArrayList<event> Events = new ArrayList<event>();
    private ArrayList<String> Members = new ArrayList<String>();

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
}
