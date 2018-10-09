package notepad;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public final static String DATE_FORMAT = "dd.MM.yyyy";
    public final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    static Scanner scanner = new Scanner(System.in);
    static List<Record> recordList = new ArrayList<>();
    public final static String TIME_FORMAT = "HH:mm";
    public final static DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(TIME_FORMAT);


    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter command ('help' for help):");
            String cmd = scanner.next();
            switch (cmd) {
                case "createPerson":
                case "cp":
                    createPerson();
                    break;
                case "createNote":
                case "cn":
                    createNote();
                    break;
                case "list":
                    printList();
                    break;
                case "createReminder":
                case "cr":
                    createReminder();
                    break;
                case "remove":
                    removeById();
                    break;
                case "find":
                    find();
                    break;
                case "help":
                    showHelp();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("It isn't a command");
            }
        }
    }


    private static void createReminder() {

        var reminder = new Reminder();
        addRecord(reminder);
    }

    private static void createNote() {
        Note note = new Note();
        addRecord(note);
    }

    private static void find() {
        System.out.println("Find what?");
        String str = askString();
        for (Record r : recordList) {
            if (r.hasSubstring(str)) {
                System.out.println(r);
            }
        }
    }

    private static void showHelp() {
        System.out.println("createPerson - bla bla bla bla");
        System.out.println("remove - remove by ID");
        System.out.println("find - bla bla bla bla");
        System.out.println("createReminder - bla bla bla bla");
        System.out.println("createNote - bla bla bla bla");
        System.out.println("list - bla bla bla bla");
        System.out.println("exit - bla bla bla bla");
    }

    private static void removeById() {
        System.out.println("Enter ID to remove:");
        int id = scanner.nextInt();
        for (int i = 0; i < recordList.size(); i++) {
            Record p = recordList.get(i);
            if (id == p.getId()) {
                recordList.remove(i);
                break;
            }
        }
    }


    private static void printList() {
        for (Record p : recordList) {
            System.out.println(p);
        }
    }

    private static void createPerson() {
        Person p = new Person();
        addRecord(p);
    }

    private static void addRecord(Record p) {
        p.askQuestions();
        recordList.add(p);
        System.out.println(p);
    }

    public static String askString() {
        var result = new ArrayList<String>();
        var word = scanner.next();
        if (word.startsWith("\"")) {

            do {
                result.add(word);
                if (word.endsWith("\"")) {
                    return String.join(" ", result);
                }
                word = scanner.next();
            } while (true);

        } else {
            return word;
        }
    }

    public static LocalDate askDate() {
        String d = askString();
        LocalDate date = LocalDate.parse(d, DATE_FORMATTER);
        return date;
    }

    public static LocalTime askTime() {
        String t = askString();
        LocalTime time = LocalTime.parse(t, DATE_FORMATTER);
        return time;
    }
}

