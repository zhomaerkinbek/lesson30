import java.time.LocalDateTime;
import java.util.Map;

public enum State {
    ON_PARKING("На парковке"){
        @Override
        public void changeState(Parking parking, Car car, Map<Integer, Log> journal, LocalDateTime time) throws Exception {
            car.setStateEnum(State.ON_THE_WAY);
            parking.upParkingCapacity();
            parking.removeCar(car);
            for(int i = journal.size(); i > 0; i--){
                if(journal.get(i).getCar().equals(car)){
                    journal.get(i).setCheckOutTime(time);
                    break;
                }
            }

            System.out.println("Выехал из парковки");
            System.out.println(parking.getParkingCapacity());

        }
    },
    ON_THE_WAY("В пути"){
        @Override
        public void changeState(Parking parking, Car car, Map<Integer, Log> journal, LocalDateTime time) throws Exception {
            if(parking.getParkingCapacity() > 0){
                car.setStateEnum(State.ON_PARKING);
                parking.dawnParkingCapacity();
                parking.addCar(car);
                journal.put(journal.size() + 1, new Log(car, time));
                System.out.println("Заехал в парковку");
                System.out.println(parking.getParkingCapacity());


            }else {
                throw new Exception("Парковка заполнена!");
            }
        }
    };


    private String value;

    State(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public abstract void changeState(Parking parking, Car car, Map<Integer, Log> journal, LocalDateTime i) throws Exception;
}
