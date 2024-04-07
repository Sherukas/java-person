import java.io.BufferedReader;
import java.util.Calendar;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ConsoleInputReader {
    // Метод для получения ввода пользователя на русском языке
    static public String getConsoleInputRussianString() {
        String str = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                str = reader.readLine();
                // Проверка, что все введенные символы являются русскими
                if (str.matches("[а-яА-Я- ]+")) {
                    break;
                } else {
                    // Повторный запрос, если введены нерусские символы
                    System.out.print("Не все введенные символы являются русскими, попробуйте снова: ");
                }
            }
        } catch (IOException e) {
            // Обработка ошибки ввода-вывода
            System.out.println("Ошибка при попытке чтения входных данных: " + e.getMessage());
        }
        return str;
    }

    // Метод для получения ввода даты от пользователя
    static public Calendar getConsoleInputDate() {
        int[] date;
        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("Введите дату в формате дд.мм.гггг: ");
            try {
                // Разбиение введенной строки на массив чисел
                date = Arrays.stream(reader.readLine().split("\\.")).mapToInt(Integer::parseInt).toArray();
            } catch (IOException e) {
                // Обработка ошибки ввода-вывода
                System.out.println("Ошибка при попытке чтения входных данных: " + e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                // Обработка ошибки, если введены нечисловые значения
                System.out.println("Ошибка: введены не числовые значения. Попробуйте снова.");
                continue;
            }
            // Проверка корректности введенного формата даты
            if (date == null || date.length != 3) {
                System.out.println("Данные введены в неправильном формате. Попробуйте снова.");
            } else if ((date[1] <= 0 || date[1] > 12)) {
                // Проверка корректности месяца
                System.out.println("Месяц введен некорректно, попробуйте снова.");
            } else {
                // Установка значений для объекта Calendar
                calendar.set(Calendar.YEAR, date[2]);
                calendar.set(Calendar.MONTH, date[1] - 1); // Месяцы в Calendar начинаются с 0
                // Проверка корректности введенного дня для данного месяца
                if (date[0] >= calendar.getActualMinimum(Calendar.DAY_OF_MONTH) && date[0] <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                    calendar.set(Calendar.DAY_OF_MONTH, date[0]);
                    break;
                } else {
                    // Повторный запрос, если введенный день некорректен
                    System.out.println("День введен некорректно, попробуйте снова.");
                }
            }
        }
        return calendar;
    }
}
