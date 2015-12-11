/**
 * Created by jhansirk on 12/11/15.
 */
public class Car {
    private String registrationNumber;

    public Car(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String toString() {
        return registrationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        return !(registrationNumber != null ? !registrationNumber.equals(car.registrationNumber) : car.registrationNumber != null);

    }

    @Override
    public int hashCode() {
        return registrationNumber != null ? registrationNumber.hashCode() : 0;
    }
}
