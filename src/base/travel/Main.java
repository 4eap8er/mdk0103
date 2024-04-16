package base.travel;

import java.util.ArrayList;
import java.util.Scanner;

class User {
    Bank account = new Bank();
    private String currentCurrency;
    ArrayList<Currency> wallet = account.getWallet();


    public String getUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.next();
    }

    private void displayWallet() {
        account.displayWallet();
    }

    public void travel(Country country) {
        currentCurrency = country.getCurrencyName();
        account.addNewCurrency(country.getCurrencyName(), 0);
        System.out.println("Текущая валюта: " + country.getCurrencyName());
        System.out.println("Текущий баланс:");
        displayWallet();
        ArrayList<CurrencyCourse> countryCourses = country.getCourses();

        String currencyToConvertFrom = null;
        double course = 0;

        for (CurrencyCourse courses : countryCourses) {
            if (courses.getName().contains(country.getCurrencyName())) {
                currencyToConvertFrom = courses.getName().substring(0, 3);
            }
            if (courses.getName().equals(currencyToConvertFrom + " to " + country.getCurrencyName())) {
                course = courses.getValue();
            }
        }

        String amountToDeposit = getUserInput("Пополните баланс: ");

        double equivalentInEuro = Double.parseDouble(amountToDeposit) / course;
        account.withdraw(currencyToConvertFrom, equivalentInEuro);
        account.deposit(country.getCurrencyName(), Double.parseDouble(amountToDeposit));

        displayWallet();
    }

}

class Bank {
    private ArrayList<Currency> wallet = new ArrayList<>();

    public Bank() {
        wallet.add(new Currency("USD", 10));
        wallet.add(new Currency("EUR", 5));
        wallet.add(new Currency("JPY", 130));
        wallet.add(new Currency("RUB", 35000));
    }

    public void displayWallet() {
        for (Currency currency : wallet) {
            System.out.println("Валюта: " + currency.getName() + " | Количество: " + currency.getValue());
        }
    }

    public void addNewCurrency(String name, double val) {
        for (Currency currency : wallet) {
            if (currency.getName().equals(name) || name.length() > 3) {
                throw new IllegalArgumentException("Invalid currency name");
            }
        }

        Currency newCurrency = new Currency(name, val);
        wallet.add(newCurrency);
    }

    public void deposit(String currencyName, double value) {
        for (Currency currency : wallet) {
            if (currency.getName().equals(currencyName)) {
                currency.setValue(currency.getValue() + value);
            }
        }
    }

    public void withdraw(String currencyName, double value) {
        for (Currency currency : wallet) {
            if (currency.getName().equals(currencyName)) {
                currency.setValue(currency.getValue() - value);
                if (currency.getValue() < 0) {
                    throw new Error("Вы превысили сумму пополнения! Недостаточно средств на счету!");
                }
            }
        }
    }

    public ArrayList<Currency> getWallet() {
        return wallet;
    }
}

class Currency {
    private String name;
    private double value;

    public Currency(String name, double value) {
        this.value = value;
        this.name = name;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public double getValue() {
        return this.value;
    }
}

class CurrencyCourse {
    private String name;
    private double value;

    public CurrencyCourse(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}

class Country {
    private String name;

    private ArrayList<CurrencyCourse> courses = new ArrayList<>();
    private String currencyName;

    public Country(String name, String courseName, double courseVal, String currencyName) {
        this.currencyName = currencyName;
        this.name = name;
        addCourse(courseName, courseVal);
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void addCourse(String courseName, double courseVal) {
        CurrencyCourse course = new CurrencyCourse(courseName, courseVal);
        courses.add(course);
    }

    public ArrayList<CurrencyCourse> getCourses() {
        return courses;
    }

    public void displayCourses() {
        for (CurrencyCourse course : courses) {
            System.out.println("Название конвертации: " + course.getName() + " | Курс: " + course.getValue());
        }
    }

    public String getName() {
        return name;
    }
}

public class Main {
    public static void main(String[] args) {

        User user = new User();
        Country sweden = new Country("Швеция", "EUR to SEK", 11.40, "SEK");
        Country china = new Country("Китай", "JPY to CNY", 0.048, "CNY");

        String choice = user.getUserInput("Выберите страну отправления: \nШвеция \nКитай \n");

        if (sweden.getName().equals(choice)) {
            user.travel(sweden);
        } else if (china.getName().equals(choice)) {
            user.travel(china);
        }
    }
}
