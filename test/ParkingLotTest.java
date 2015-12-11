import com.thoughtworks.nipun.Notifier;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ParkingLotTest {

    @Test
    public void shouldParkTheCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(100, null);
        String registrationNumber = "AP 11A 1234";
        Car car = new Car(registrationNumber);
        Ticket ticket = parkingLot.park(car);
        assertEquals(ticket.getCarNumber(), registrationNumber);
    }

    @Test(expected = ParkingLotFullException.class)
    public void shouldThrowExceptionWhenSlotIsNotAvailable() throws Exception {
        ParkingLot parkingLot = new ParkingLot(0, null);
        String registrationNumber = "MH 11AA 8394";
        Car car = new Car(registrationNumber);
        Ticket ticket = parkingLot.park(car);
    }

    @Test(expected = CarAlreadyParkedException.class)
    public void shouldThrowCarAlreadyParkedExceptionWhenTryingToParkSameCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10, null);
        Car car1 = new Car("1111");
        Car car2 = new Car("1111");
        Ticket ticket1 = parkingLot.park(car1);
        Ticket ticket2 = parkingLot.park(car2);
    }

    @Test
    public void shouldUnparkAParkedCar() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10, null);
        Car car1 = new Car("1111");
        Ticket ticket = parkingLot.park(car1);
        Car unparkedCar = parkingLot.unpark(ticket);
        Assert.assertEquals(car1,unparkedCar);
    }

    @Test(expected = Exception.class)
    public void shouldBeNotAbleToParkIfCarIsNotParked() throws Exception {
        ParkingLot parkingLot = new ParkingLot(10, null);
        Car car1 = new Car("1111");
        parkingLot.unpark(new Ticket("dummy"));
    }

    @Test
    public void shouldNotifyTheOwnerWhenTheParkingLotisFull() throws Exception {
        Notifier notifier = Mockito.mock(Notifier.class);
        ParkingLot parkingLot = new ParkingLot(1, notifier);
        String registrationNumber = "MH 11AA 8394";
        Car car = new Car(registrationNumber);
        parkingLot.park(car);
        Mockito.verify(notifier).notifyOwnerOnFullParking("abcd@exmaple.com");
    }
}
