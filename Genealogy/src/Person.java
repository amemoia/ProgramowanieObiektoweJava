import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Person implements Comparable<Person> {
    private String name, surname;
    private LocalDate birthday;
    private LocalDate deathDate;

    private Set<Person> children;
    private Set<Person> parents = new HashSet<>();

    public Person(String name, String surname, LocalDate birthday, LocalDate deathDate) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.deathDate = deathDate;
        this.children = new HashSet<>();
    }
    public static Person fromCsvLine(String line) throws NegativeLifespanException {
        String[] args = line.split(",");
        String[] splitName = args[0].split(" ");
        String[] birthDateStr = args[1].split("\\.");
        LocalDate birthDate = LocalDate.parse(args[1], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate deathDate = null;
        if (args.length > 2 && !args[2].isEmpty()) {
            deathDate = LocalDate.parse(args[2], DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }
        if (deathDate != null && deathDate.isBefore(birthDate)) {
            throw new NegativeLifespanException("Data śmierci jest wcześniejsza niz data urodzin");
        }
        return new Person(splitName[0], splitName[1], birthDate, deathDate);
    }
    public static List<Person> fromCsv(String path) throws AmbiguousPersonException {
        List<Person> people = new ArrayList<>();
        Set<String> names = new HashSet<>();
        Map<String, Person> peopleMap = new HashMap<>();
        boolean columnNamesSkipped = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!columnNamesSkipped) {
                    columnNamesSkipped = true;
                    continue;
                }
                String[] lineparts = line.split(",");
                Person person = Person.fromCsvLine(line);
                String name = person.getName() + " " + person.getSurname();
                if (names.contains(name)) {
                    throw new AmbiguousPersonException("Istnieje juz osoba z nazwa " + name);
                }
                names.add(name);
                peopleMap.put(name, person);
                if (lineparts.length > 3) {
                    Person parent1 = peopleMap.get(lineparts[3]);
                    try {
                        //checkParentingAge(parent1, person);
                        if (parent1 != null) parent1.adopt(person);
                    } catch (ParentingAgeException e) {
                        System.out.println("Error " + e.getMessage());
                        System.out.println("Dodac? (Y/N)");
                        Scanner scanner = new Scanner(System.in);
                        String choice = scanner.nextLine();
                        if (choice.equalsIgnoreCase("y")) {
                            parent1.adopt(person);
                        }
                    }

                    if (lineparts.length > 4) {
                        Person parent2 = peopleMap.get(lineparts[4]);
                        try {
                            //checkParentingAge(parent2, person);
                            if (parent2 != null) parent2.adopt(person);
                        } catch (ParentingAgeException e) {
                            System.out.println("Error " + e.getMessage());
                            System.out.println("Dodac? (Y/N)");
                            Scanner scanner = new Scanner(System.in);
                            String choice = scanner.nextLine();
                            if (choice.equalsIgnoreCase("y")) {
                                parent2.adopt(person);
                            }
                        }
                    }
                }
                people.add(person);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Set<Person> getParents() {
        return parents;
    }
    public boolean addParent(Person parent) {
        if (parent == null || parent == this) return false;
        for (Person existingParent : this.parents) {
            if (parent == existingParent) return false;
        }
        return this.parents.add(parent);
    }

    public boolean adopt(Person child) {
        if (child == null || child == this) return false;
        for (Person existingChild : this.children) {
            if (child == existingChild) return false;
        }
        boolean adopted = this.children.add(child);
        if (adopted) {
            child.addParent(this);
        }
        return adopted;
    }

    public Person getYoungestChild() {
        if (this.children.isEmpty()) return null;
        else {
            Person youngest = this.children.iterator().next();
            for (Person child : this.children) {
                if (child.compareTo(youngest) == -1) {
                    youngest = child;
                }
            }
            return youngest;
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", children=" + getChildren() +
                '}';
    }


    public List<Person> getChildren() {
        return this.children.stream().sorted(Person::compareTo).toList();
    }

    @Override
    public int compareTo(Person o) {
        if (this.birthday.isAfter(o.birthday)) return 1; //older
        else if (this.birthday.isBefore(o.birthday)) return -1; //younger
        else return 0; //equal
    }
    private String generatePumlObject (int personIndex) {
        return String.format(
                """
                object p%d {
                    %s %s
                }
                
                """, personIndex, this.name, this.surname);
    }
    private String generatePumlRelation(int index1, int index2, String relation) {
        return String.format(
                """
                p%d --> p%d : %s
                
                """, index1, index2, relation);
    }
    private String generatePumlFamily() {
        String data = "";
        int personIndex = 1;
        data += this.generatePumlObject(personIndex++);
        if (this.parents != null) {
            int childIndex = personIndex - 1;
            for (Person parent : this.parents) {
                data += parent.generatePumlObject(personIndex);
                data += generatePumlRelation(personIndex, childIndex, "parent");
                personIndex++;
            }
        }
        return data;
    }
    public String generateFullPumlData() {
        String data = "";
        data += "@startuml\n";
        data += generatePumlFamily();
        data += "@enduml";
        return data;
    }

    public static Person returnOldestLivingFromList(Set<Person> people) {
        Person oldest = null;
        if (people.isEmpty()) { return null; }
        for (Person person : people) {
            if (oldest == null) { oldest = person; }
            else if (person.deathDate == null) {
                if (person.birthday.isBefore(oldest.birthday)) {
                    oldest = person;
                }
            }
        }
        return null;
    }
}
