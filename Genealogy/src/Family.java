import java.util.*;

public class Family {
    private Map<String, List<Person>> familyMap;
    public Family() {
        this.familyMap = new HashMap<>();
    }

    public void add(Person... persons) {
        for (Person person : persons) {
            if (person != null) {
                this.familyMap.put(person.getName() + " " + person.getSurname(), Arrays.stream(persons).toList());
            }
        }
    }
    public List<Person> get(String... keys) {
        List<Person> values = new ArrayList<>();
        for (String key : keys) {
            values = familyMap.get(key);
        }
        values.sort(Person::compareTo);
        return values;
    }
}
