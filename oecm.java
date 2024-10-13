// Notification interface
interface Notification {
    void send(String message);
}

// EmailNotification class implements Notification
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// SMSNotification class implements Notification
class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// NotificationService class handles all notifications
class NotificationService {
    public void sendNotification(Notification notification, String message) {
        notification.send(message);  // Polymorphism in action!
    }
}

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        // Sending Email Notification
        Notification email = new EmailNotification();
        notificationService.sendNotification(email, "Hello User!");

        // Sending SMS Notification
        Notification sms = new SMSNotification();
        notificationService.sendNotification(sms, "Hello User!");
    }
}
