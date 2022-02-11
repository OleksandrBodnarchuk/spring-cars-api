package pl.alex.cars.entity.modification;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Bodywork {
    private String bodyType;
    private int numberOfDoors;
    private int numberOfSeats;
    private String luggageCapacity;

    @Override
    public String toString() {
        return "Body Type: " + bodyType + '\n' +
                "Number Of Doors: " + numberOfDoors + '\n' +
                "Number Of Seats: " + numberOfSeats + '\n' +
                "Luggage Capacity: " + luggageCapacity + '\n' +
                '}';
    }
}
