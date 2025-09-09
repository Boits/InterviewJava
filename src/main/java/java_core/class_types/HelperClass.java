package java_core.class_types;

/**
 * Вспомогательный класс
 */
public class HelperClass {
    private String senderEmail;
    private String senderName;

    // Конструктор для инициализации данных отправителя
    public HelperClass(String senderEmail, String senderName) {
        this.senderEmail = senderEmail;
        this.senderName = senderName;
    }

    // Статический метод для валидации email
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    // Нестатический метод для отправки email
    public void sendEmail(String recipient, String subject, String message) {
        if (!isValidEmail(recipient)) {
            throw new IllegalArgumentException("Invalid recipient email address");
        }
        // Логика отправки email (условная, для примера)
        System.out.printf("Sending email from %s (%s) to %s\nSubject: %s\nMessage: %s\n",
                senderName, senderEmail, recipient, subject, message);
    }
}