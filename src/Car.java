import java.time.LocalDateTime;
import java.util.Map;

public class Car {
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

    Car(String registrationNumber){
        this.registrationNumber = registrationNumber;
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
            System.out.println(e.getMessage());
        }
    }
}
