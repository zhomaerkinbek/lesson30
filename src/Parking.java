import java.util.ArrayList;
import java.util.List;

public class Parking {
    private int parkingCapacity;
    private List<Car> cars;

    public Parking(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
    }

    public Parking() {
        this(20);
        cars = new ArrayList<>();
    }

    public int getParkingCapacity() {
        return parkingCapacity;
    }

    public void dawnParkingCapacity() {
        parkingCapacity = Math.max(parkingCapacity - 1, 0);
    }
    public void upParkingCapacity() {
        parkingCapacity = Math.min(parkingCapacity + 1, 20);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }
    public void removeCar(Car car) {
        cars.remove(car);
    }
}
