import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CustomList<String> list = new CustomList<>();
        list.add("buh");
        list.add("guh");
        list.add("juh");
        System.out.println(list.get(2));
        System.out.println(list.size());
    }
}