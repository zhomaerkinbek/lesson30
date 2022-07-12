import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment {
    private Car car;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private int minutes;
    private final int pricePerMinute = 2;
    private int total;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - dd MMM yyyy");


    public Payment(Car car, LocalDateTime checkInTime, LocalDateTime checkOutTime, int minutes) {
        this.car = car;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.minutes = minutes;
        total = this.minutes * pricePerMinute;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getPricePerMinute() {
        return pricePerMinute;
    }

    @Override
    public String toString() {
        return  "Машина с номером: " + car +
                ", заехала в " + formatter.format(checkInTime) +
                ", выехала в " + formatter.format(checkOutTime) +
                ", минуты на парковке: " + minutes +
                ", стоимость за парковку: " + total;
    }
}
