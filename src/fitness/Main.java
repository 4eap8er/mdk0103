package fitness;

import java.time.LocalDate;
import java.util.ArrayList;

class Person {
    String firstName;
    String lastName;
    int birthYear;

    public Person(String firstName, String lastName, int birthYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
    }
}

class Membership {
    LocalDate registrationDate;
    LocalDate expirationDate;
    Person owner;

    public Membership(LocalDate registrationDate, LocalDate expirationDate, Person owner) {
        this.registrationDate = registrationDate;
        this.expirationDate = expirationDate;
        this.owner = owner;
    }

    public boolean isValid() {
        return LocalDate.now().isBefore(expirationDate);
    }
}

class FitnessClub {
    final int MAX_CAPACITY = 20;
    ArrayList<Membership> gymMemberships = new ArrayList<>();
    ArrayList<Membership> poolMemberships = new ArrayList<>();
    ArrayList<Membership> groupClassMemberships = new ArrayList<>();

    public void register(Membership membership, String zone) {
        if (!membership.isValid()) {
            System.out.println("Абонемент просрочен.");
            return;
        }

        ArrayList<Membership> zoneMemberships;
        switch (zone) {
            case "gym":
                zoneMemberships = gymMemberships;
                break;
            case "pool":
                zoneMemberships = poolMemberships;
                break;
            case "group":
                zoneMemberships = groupClassMemberships;
                break;
            default:
                System.out.println("Неправильно указана зона.");
                return;
        }

        if (zoneMemberships.size() >= MAX_CAPACITY) {
            System.out.println("В зоне нет свободных мест.");
            return;
        }

        if (isRegisteredInOtherZone(membership, zone)) {
            System.out.println("Абонемент уже зарегистрирован в другой зоне.");
            return;
        }

        zoneMemberships.add(membership);
        System.out.println("Посетитель успешно зарегистрирован в зоне: " + zone);
    }

    private boolean isRegisteredInOtherZone(Membership membership, String currentZone) {
        if (currentZone.equals("gym")) {
            return poolMemberships.contains(membership) || groupClassMemberships.contains(membership);
        } else if (currentZone.equals("pool")) {
            return gymMemberships.contains(membership) || groupClassMemberships.contains(membership);
        } else if (currentZone.equals("group")) {
            return gymMemberships.contains(membership) || poolMemberships.contains(membership);
        }
        return false;
    }

    public void displayVisitors() {
        System.out.println("Посетители зала:");
        displayMemberships(gymMemberships);
        System.out.println("Посетители бассейна:");
        displayMemberships(poolMemberships);
        System.out.println("Посетители групповых занятий:");
        displayMemberships(groupClassMemberships);
    }

    private void displayMemberships(ArrayList<Membership> memberships) {
        for (Membership membership : memberships) {
            System.out.println(membership.owner.firstName + " " + membership.owner.lastName);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        FitnessClub fitnessClub = new FitnessClub();

        Person person1 = new Person("Николай", "Иванов", 1995);
        Membership membership1 = new Membership(LocalDate.now(), LocalDate.now().plusMonths(1), person1);
        fitnessClub.register(membership1, "gym");

        Person person2 = new Person("Олег", "Романов", 1987);
        Membership membership2 = new Membership(LocalDate.now(), LocalDate.now().plusMonths(1), person2);
        fitnessClub.register(membership2, "pool");


        Person person3 = new Person("Елена", "Чкалова", 1998);
        Membership membership3 = new Membership(LocalDate.now(), LocalDate.now().plusMonths(1), person3);
        fitnessClub.register(membership3, "group");

        fitnessClub.displayVisitors();
    }
}
