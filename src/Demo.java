import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Demo {
    static final Car[] cars = new Car[200];
    static final Random ran = new Random();
    static Map<Integer, Log> journal = new HashMap<>();
    static Parking parking = new Parking();
    static String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    static LocalDateTime ldt = LocalDateTime.now();

    static {
        for(int i = 0; i < 200; i++){
            cars[i] = new Car(String.format("0%skg%s%s%s%s%s%s",ran.nextInt(9) + 1, ran.nextInt(10), ran.nextInt(10), ran.nextInt(10), text.charAt(ran.nextInt(text.length())), text.charAt(ran.nextInt(text.length())), text.charAt(ran.nextInt(text.length()))));
        }
    }

    public static void main(String[] args){
        for(LocalDateTime i = ldt; i.isBefore(ldt.plusDays(1)); i = i.plusMinutes(5)){
            for(Car car:cars){
//                System.out.println(car);
                if(ran.nextInt(100) + 1 <= 3){
                    System.out.println("Шанс");
                    car.changeState(parking, car, journal, i);
                }
            }
        }
        Map<String, Integer> check = new HashMap<>();
        journal.forEach((k, v) -> {
            System.out.printf("%s : %s%n", k, v);
        });

        for(var log: journal.values()){
            if(log.getCheckOutTime() != null) {
                check.put(log.getCar().getRegistrationNumber(), returnMinutes(log.getCar().getRegistrationNumber(), journal.values()));
            }
        }

        check.forEach((k, v) -> {
            System.out.printf("%s : %s%n", k, v);
        });
    }
    public static int returnMinutes(String getRegistrationNumber, Collection<Log> logs){
        LocalTime time1 = LocalTime.of(9, 0);
        LocalTime time2 = LocalTime.of(21, 0);

        int minutes = 0;
        for (var log: logs){
            if(log.getCar().getRegistrationNumber().equals(getRegistrationNumber) & log.getCheckOutTime() != null){
                if(log.getCheckInTime().toLocalTime().isAfter(time1) & log.getCheckOutTime().toLocalTime().isBefore(time2)){
                    minutes += ChronoUnit.MINUTES.between(log.getCheckInTime(), log.getCheckOutTime());
                }
            }
        }
        return minutes;
    }
}
