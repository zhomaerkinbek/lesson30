import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Log {
    private Car car;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd MMM yyyy");

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Log(Car car, LocalDateTime checkInTime) {
        this.car = car;
        this.checkInTime = checkInTime;
    }

    @Override
    public String toString() {
        return  car + " Заехал: " + formatter.format(checkInTime) +
                ", Выехал: " + (checkOutTime != null ? formatter.format(checkOutTime) : checkOutTime);
    }
}
