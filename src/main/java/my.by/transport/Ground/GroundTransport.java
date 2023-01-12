package my.by.transport.Ground;

import lombok.Getter;
import lombok.Setter;
import my.by.transport.Transport;

@Getter
@Setter
public class GroundTransport extends Transport {
    private boolean isGround;
    public GroundTransport(String name, double loadCapacity, double transportationPrice, double speed, int passengers) {
        super(name, loadCapacity, transportationPrice, speed, passengers);
        this.isGround = true;
    }
}
