package my.by.city;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class City implements ICity{
    private String name;
    private boolean hasAirport;                  //наличие аэропорта
    private boolean hasPort;                     //наличие порта
    private double latitudePosition;             //координата города по широте
    private double longitudePosition;            //координата города по долготе

    @Override
    public void print() {
        System.out.println("Название города: " + name +
                "\nНаличие аэропорта = " + hasAirport +
                "\nНаличие порта = " + hasPort +
                "\nШирота = " + latitudePosition +
                "\nДолгота = " + longitudePosition);
    }
}
