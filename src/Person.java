import java.util.Calendar;

public class Person {
    private String firstName; // Имя
    private String lastName; // Фамилия
    private String patronymic; // Отчество
    private Calendar dateOfBirth; // Дата рождения
    private char gender; // Пол
    private String initials; // Инициалы
    private Integer age; // Возраст

    public Person() {
        this.firstName = this.lastName = this.patronymic = this.initials = "";
        this.gender = '?';
        this.age = 0;
    }

    public Person(String firstName, String lastName) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = Person.generateInitials(firstName, lastName, "");
    }

    public Person(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.gender = Person.GenderDetector(patronymic);
        this.initials = Person.generateInitials(firstName, lastName, patronymic);
    }

    public Person(String firstName, String lastName, String patronymic, Calendar dateOfBirth) {
        this(firstName, lastName, patronymic);
        this.dateOfBirth = dateOfBirth;
        this.age = Person.calculateAge(this.dateOfBirth);
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        this.initials = Person.generateInitials(this.firstName, this.lastName, this.patronymic);
        this.gender = Person.GenderDetector(patronymic);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.initials = Person.generateInitials(this.firstName, this.lastName, this.patronymic);
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.initials = Person.generateInitials(this.firstName, this.lastName, this.patronymic);
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        this.age = Person.calculateAge(this.dateOfBirth);
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

    public char getGender() {
        return gender;
    }


    public String getFirstName() {
        return firstName;
    }

    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    public Integer getAge() {
        return this.age;
    }

    // Статический метод для генерации инициалов
    public static String generateInitials(String firstName, String lastName, String patronymic) {
        StringBuilder Initials = new StringBuilder();
        Initials.append(firstName.charAt(0)).append("."); // Первая буква имени
        if (!patronymic.isEmpty() && !patronymic.equals(" ") && !patronymic.equals("-")) {
            Initials.append(patronymic.charAt(0)).append("."); // Первая буква отчества
        }
        return Initials.toString();
    }

    // Статический метод для вычисления возраста
    public static int calculateAge(Calendar dateOfBirth) {
        Calendar currentDate = Calendar.getInstance();

        int age = currentDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if (currentDate.get(Calendar.MONTH) < dateOfBirth.get(Calendar.MONTH) ||
                (currentDate.get(Calendar.MONTH) == dateOfBirth.get(Calendar.MONTH) &&
                        currentDate.get(Calendar.DAY_OF_MONTH) < dateOfBirth.get(Calendar.DAY_OF_MONTH))) {
            --age;
        }

        return age;
    }

    // Статический метод для определения пола по отчеству
    public static char GenderDetector(String patronymic) {
        char lastChar = patronymic.charAt(patronymic.length() - 1);
        if (lastChar == 'ч') {
            return 'M';
        } else if (lastChar == 'а') {
            return 'Ж';
        }
        return '?'; // Неизвестный пол
    }

    // Переопределенный метод toString() для удобного вывода информации о персоне
    public String toString() {
        return "Person{" +
                "firstName='" + (firstName == null ? " " : firstName) + '\'' +
                ", lastName='" + (lastName == null ? " " : lastName) + '\'' +
                ", patronymic='" + (patronymic == null ? " " : patronymic) + '\'' +
                ", dateOfBirth=" + (dateOfBirth != null ? dateOfBirth.getTime() : " ") +
                ", gender=" + gender +
                ", initials='" + (initials == null ? " " : initials) + '\'' +
                ", age=" + (age == null ? " " : age) +
                '}';
    }
}
