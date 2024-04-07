import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введите фамилию: ");
        String lastname = ConsoleInputReader.getConsoleInputRussianString();
        System.out.print("Введите имя: ");
        String firstname = ConsoleInputReader.getConsoleInputRussianString();
        System.out.print("Введите отчество: ");
        String patronymic = ConsoleInputReader.getConsoleInputRussianString();
        Calendar dateOfBirth = ConsoleInputReader.getConsoleInputDate();
        Person person = new Person(firstname, lastname, patronymic, dateOfBirth);
        System.out.println("Фамилия И.О.: " + person.getLastName() + " " + person.getInitials());
        System.out.println("Пол: " + person.getGender());
        System.out.println("Возраст: " + person.getAge());
    }
}
