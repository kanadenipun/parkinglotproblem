import com.thoughtworks.nipun.Notifier;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {

    private int capacity;
    private Notifier notifier;
    private Map<Ticket, Car> parkedCars;

    public ParkingLot(int capacity, Notifier notifier) {
        this.capacity = capacity;
        this.notifier = notifier;
        parkedCars = new HashMap<>();
    }


    public Ticket park(Car car) throws ParkingLotFullException,CarAlreadyParkedException {
        if (isParkingFull())
            throw new ParkingLotFullException();


        if(isCarAlreadyParked(car))
            throw new CarAlreadyParkedException();

        Ticket ticket = new Ticket(car.getRegistrationNumber());
        parkedCars.put(ticket,car);

        if(isParkingFull())
            notifier.notifyOwnerOnFullParking("abcd@exmaple.com");

        return ticket;


    }

    private boolean isParkingFull() {
        return parkedCars.size() == capacity;
    }

    public boolean isCarAlreadyParked(Car car) {

        for(Car itrCar : parkedCars.values()){
            if(car.equals(itrCar)) return  true;
        }
        return false;
    }


    public Car unpark(Ticket ticket) throws Exception {
        if(!parkedCars.containsKey(ticket)){
            throw new Exception();
        }
        return parkedCars.get(ticket);
    }
}

