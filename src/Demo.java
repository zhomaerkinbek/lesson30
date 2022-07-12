import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Demo {
    static final Random ran = new Random();
    static Scanner sc = new Scanner(System.in);
    static final Car[] cars = new Car[200];
    static Map<Integer, Log> journal = new HashMap<>();
    static Parking parking = new Parking();
    static Map<Integer, Payment> check = new HashMap<>();


    static LocalDateTime ldt = LocalDateTime.now();

    static {
        for(int i = 0; i < 200; i++){
            cars[i] = new Car();
        }
    }

    public static void main(String[] args){
        for(LocalDateTime i = ldt; i.isBefore(ldt.plusDays(30)); i = i.plusMinutes(5)){
            for(Car car:cars){
                if(ran.nextInt(100) + 1 <= 3){
                    car.changeState(parking, car, journal, i);
                }
            }
        }
        createPayment();
        menu();
    }
    public static int returnMinutes(Log log){
        LocalTime time1 = LocalTime.of(9, 0);
        LocalTime time2 = LocalTime.of(21, 0);
        int minutes = (int) minutesDifference(log.getCheckInTime(), log.getCheckOutTime());

        if(log.getCheckInTime().toLocalTime().isBefore(time1) | log.getCheckInTime().toLocalTime().equals(time1)){
            if(log.getCheckOutTime().toLocalTime().isBefore(time1) & minutesDifference(log.getCheckInTime(), log.getCheckOutTime()) < 720){
                minutes -= minutesDifference(log.getCheckInTime(), log.getCheckOutTime());
            }else if(log.getCheckOutTime().toLocalTime().isBefore(time2) | log.getCheckOutTime().toLocalTime().equals(time2) & minutesDifference(log.getCheckInTime(), log.getCheckOutTime()) < 720){
                minutes -= minutesDifference(log.getCheckInTime().toLocalTime(), time1);
            }else{
                minutes -= minutesDifference(log.getCheckInTime().toLocalTime(), time1) + minutesDifference(time2, log.getCheckOutTime().toLocalTime());
            }
        }else if(log.getCheckInTime().toLocalTime().isAfter(time1) & log.getCheckInTime().toLocalTime().isBefore(time2)){
            if((log.getCheckOutTime().toLocalTime().isBefore(time1) | log.getCheckOutTime().toLocalTime().equals(time1) & minutesDifference(log.getCheckInTime(), log.getCheckOutTime()) > 720)){
                minutes = (int) minutesDifference(log.getCheckInTime().toLocalTime(), time2);
            }else if((log.getCheckOutTime().toLocalTime().isAfter(time1) & log.getCheckOutTime().toLocalTime().isBefore(time2)) & minutesDifference(log.getCheckInTime(), log.getCheckOutTime()) > 720){
                minutes = (int) minutesDifference(log.getCheckInTime().toLocalTime(), time2) + (int) minutesDifference(time1, log.getCheckOutTime().toLocalTime());
            }else if((log.getCheckOutTime().toLocalTime().isAfter(time1) & log.getCheckOutTime().toLocalTime().isAfter(time2)) & minutesDifference(log.getCheckInTime(), log.getCheckOutTime()) > 720){
                minutes = (int) minutesDifference(log.getCheckInTime().toLocalTime(), time2) + (int) minutesDifference(time1, log.getCheckOutTime().toLocalTime()) - (int) minutesDifference(time2, log.getCheckOutTime().toLocalTime());
            }else if(log.getCheckOutTime().toLocalTime().isAfter(time2)){
                minutes = (int) minutesDifference(log.getCheckInTime().toLocalTime(), time2);
            }
        } else if(log.getCheckInTime().toLocalTime().isAfter(time2)){
            if(log.getCheckOutTime().toLocalTime().isBefore(time1) | log.getCheckOutTime().toLocalTime().equals(time1)){
                minutes -= (int) minutesDifference(log.getCheckInTime(), log.getCheckOutTime());
            }else if(log.getCheckOutTime().toLocalTime().isAfter(time2)){
                minutes -= (int) minutesDifference(log.getCheckInTime(), log.getCheckOutTime());
            }else if((log.getCheckOutTime().toLocalTime().isAfter(time1) | log.getCheckOutTime().toLocalTime().isBefore(time2))){
                minutes = (int) minutesDifference(time1, log.getCheckOutTime().toLocalTime());
            }
        }
        return minutes;
    }

    private static long minutesDifference(LocalDateTime ldt1, LocalDateTime ldt2) {
        return ChronoUnit.MINUTES.between(ldt1, ldt2);
    }
    private static long minutesDifference(LocalTime ldt1, LocalTime ldt2) {
        return ChronoUnit.MINUTES.between(ldt1, ldt2);
    }

    public static void createPayment(){
        for(int i = 1; i < journal.size(); i++){
            if(journal.get(i).getCheckOutTime() != null){
                if(returnMinutes(journal.get(i)) >= 30){
                    check.put(check.size() + 1, new Payment(journal.get(i).getCar(), journal.get(i).getCheckInTime(), journal.get(i).getCheckOutTime(), returnMinutes(journal.get(i))));
                }
            }
        }
    }
    public static void menu(){
        System.out.println("-------------------------Добро пожаловать в меню системы парковки--------------------------");
        int number;
        do{
            System.out.println("1. Посмотреть журнал парковки\n" +
                               "2. Посмотреть чеки.");
            try {
                System.out.print("Введите номер с действием: ");
                number = Integer.parseInt(sc.nextLine());
            }catch (Exception e){
                System.out.println("Введите цифру");
                continue;
            }
            if(number < 1 | number > 2){
                continue;
            }
            events(number);
            System.out.println("Хотите еще раз? (Y/N)");
        }while (sc.nextLine().equalsIgnoreCase("Y"));
    }

    public static void events(int number){
        switch (number){
            case 1 -> printJournal();
            case 2 ->printCheck();
            default -> System.out.println("Такого действия нету!");
        }
    }
    public static void printJournal(){
        journal.forEach((k, v) -> {
            System.out.printf("%s : %s%n", k, v);
        });
    }
    public static void printCheck(){
        check.forEach((k, v) -> {
            System.out.printf("%s : %s%n", k, v);
        });
    }
}
