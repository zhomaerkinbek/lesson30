import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

public class Car {
    static final Random ran = new Random();

    static String text = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String registrationNumber;
    private State stateEnum;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public State getStateEnum() {
        return stateEnum;
    }

    Car(){
        this.registrationNumber = String.format("0%skg%s%s%s%s%s%s",ran.nextInt(9) + 1, ran.nextInt(10), ran.nextInt(10), ran.nextInt(10), text.charAt(ran.nextInt(text.length())), text.charAt(ran.nextInt(text.length())), text.charAt(ran.nextInt(text.length())));
        stateEnum = State.ON_THE_WAY;
    }

    public void setStateEnum(State stateEnum) {
        this.stateEnum = stateEnum;
    }

    @Override
    public String toString() {
        return  registrationNumber;
    }
    public void changeState(Parking parking, Car car, Map<Integer, Log> journal, LocalDateTime time){
        try {
            stateEnum.changeState(parking, car, journal, time);
        }catch (Exception e){
            System.out.print("");
        }
    }
}
