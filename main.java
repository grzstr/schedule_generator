import java.util.Scanner;
import java.util.ArrayList;

public class main {
    private static ArrayList<organization> Organizations = new ArrayList<organization>();

    public static void main(String[] args) {
        organization Liceum = new organization("Liceum");
        Liceum.addLecturer("Antoni Markiewicz");
        Liceum.addLecturer("John Smith");
        Liceum.addLecturer("Maria Skłodowska-Curie");
        Liceum.addLecturer("Martyna Janoszek");
        Liceum.addLecturer("Marian Pendolino");

        Liceum.addEvent(new event ("Matematyka", 45, "s.103"));
        Liceum.addEvent(new event ("Chemia", 45, "s.102"));
        Liceum.addEvent(new event ("Biologia", 45, "s.101"));
        Liceum.addEvent(new event ("Fizyka", 45, "s.100"));
        Liceum.addEvent(new event ("Informatyka", 45, "s.104"));
        Liceum.addEvent(new event ("J.Angielski", 45, "s.105"));
        Liceum.addEvent(new event ("J.Niemiecki", 45, "s.106"));
        Liceum.addEvent(new event ("J.Polski", 45, "s.107"));
        Liceum.addEvent(new event ("Historia", 45, "s.108"));
        Liceum.addEvent(new event ("Przyroda", 45, "s.109"));

        Liceum.addGroup(new group ("Klasa 1B"));

        ArrayList<group> Groups = Liceum.getGroups();
        Groups.get(0).loadEvents(Liceum.getAllEvents());
        Organizations.add(Liceum);

        main_menu();
    }

    public static void generate_schedule(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        showGroups(selected_organization);
        System.out.println("Enter group number to generate schedule: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        group selected_group = selected_organization.getGroups().get(choice);
        System.out.println("Enter begin time (format 00:00): ");
        String begin_time = scanner.nextLine();
        System.out.println("Enter end time (format 00:00): ");
        String end_time = scanner.nextLine();
        System.out.println("Generate schedule without weekend? (Y/N):");
        String without_weekend = scanner.nextLine();
        if (without_weekend == "Y") {
            selected_group.generateSchedule(begin_time, end_time, 15, false);
        } else
        {
            selected_group.generateSchedule(begin_time, end_time, 15, true);
        }
        selected_group.showSchedule();
        manage_organization_menu(selected_organization);
    }
    // MENU FUNCTIONS
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Dla systemu Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Dla systemów Unix/Linux
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showOrganizations() {
        System.out.println("\nOrganizations: \n");
        if (Organizations.size() > 0) {
            for (int i = 0; i < Organizations.size(); i++) {
                System.out.println("[" + i + "] " + Organizations.get(i).getName());
            }
        } else {
            System.out.println("No organizations found");
        }
    }
    public static void showGroups(organization selected_organization) {
        System.out.println("\nGroups: \n");
        if (selected_organization.getGroups().size() > 0) {
            for (int i = 0; i < selected_organization.getGroups().size(); i++) {
                System.out.println("[" + i + "] " + selected_organization.getGroups().get(i).getName());
            }
        } else {
            System.out.println("No groups found");
        }
    }
    public static void add_organization(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter organization name: ");
        String name = scanner.nextLine();
        organization new_organization = new organization(name);
        Organizations.add(new_organization);
        System.out.println("Organization added successfully");
        main_menu();
    }
    public static void remove_organization(){
        showOrganizations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter organization number to remove: ");
        int choice = scanner.nextInt();
        Organizations.remove(choice);
        System.out.println("Organization removed successfully");
        main_menu();
    }
    public static void manage_organization(){
        showOrganizations();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter organization number to manage: ");
        int choice = scanner.nextInt();
        organization selected_organization = Organizations.get(choice);
        manage_organization_menu(selected_organization);
    }
    public static void add_group(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter group name: ");
        String name = scanner.nextLine();
        System.out.println("Group added successfully");
        selected_organization.addGroup(new group(name));
        manage_organization_menu(selected_organization);
    }
    public static void remove_group(organization selected_organization){
        showGroups(selected_organization);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter group number to remove: ");
        int choice = scanner.nextInt();
        selected_organization.removeGroup(choice);
        System.out.println("Organization removed successfully");
        manage_organization_menu(selected_organization);
    }
    public static void add_lecturer(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter lecturer name: ");
        String name = scanner.nextLine();
        selected_organization.addLecturer(name);
        System.out.println("Lecturer added successfully");
        manage_organization_menu(selected_organization);
    }
    public static void remove_lecturer(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter lecturer name to remove: ");
        String name = scanner.nextLine();
        selected_organization.removeLecturer(name);
        System.out.println("Lecturer removed successfully");
        manage_organization_menu(selected_organization);
    }
    public static void add_event(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name: ");
        String name = scanner.nextLine();
        System.out.println("Enter event duration: ");
        int duration = scanner.nextInt();
        System.out.println("Enter event location: ");
        String location = scanner.nextLine();
        selected_organization.addEvent(new event(name, duration, location));
        System.out.println("Event added successfully");
        manage_organization_menu(selected_organization);
    }
    public static void remove_event(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter event name to remove: ");
        String name = scanner.nextLine();
        selected_organization.removeEvent(new event(name, 0, ""));
        System.out.println("Event removed successfully");
        manage_organization_menu(selected_organization);
    }
    public static void manage_organization_menu(organization selected_organization){
        Scanner scanner = new Scanner(System.in);
        //clearScreen();
        System.out.println("Organization: " + selected_organization.getName() + "\n");
        System.out.println("1. Add group");
        System.out.println("2. Remove group");
        System.out.println("3. Add lecturer");
        System.out.println("4. Remove lecturer");
        System.out.println("5. Add event");
        System.out.println("6. Remove event");
        System.out.println("7. Generate schedule");
        System.out.println("8. Back\n");
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                add_group(selected_organization);
                break;
            case 2:
                remove_group(selected_organization);
                break;
            case 3:
                add_lecturer(selected_organization);
                break;
            case 4:
                remove_lecturer(selected_organization);
                break;
            case 5:
                add_event(selected_organization);
                break;
            case 6:
                remove_event(selected_organization);
                break;

            case 7:
                generate_schedule(selected_organization);
                break;

            case 8:
                main_menu();
                break;
            default:
                System.out.println("Invalid choice");
                manage_organization_menu(selected_organization);
                break;
        }
    }
    public static void main_menu(){
        Scanner scanner = new Scanner(System.in);
        //clearScreen();
        System.out.println("\n|=================================|");
        System.out.println("|======= Schedule Manager ========|");
        System.out.println("|=================================|");
        System.out.println("\n1. Add organization");
        System.out.println("2. Remove organization");
        System.out.println("3. Manage organization");
        System.out.println("4. Exit\n");
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                add_organization();
                break;
            case 2:
                remove_organization();
                break;
            case 3:
                manage_organization();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                main_menu();
                break;
        }
    }

}
