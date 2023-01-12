package my.by.transport;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transport implements ITransport{
    private String name;                  //название транспорта
    private double loadCapacity;          //грузоподъёмность в тоннах
    private double transportationPrice;   //цена транспортировки за км в долларах
    private double speed;                 //скорость км/ч
    private int passengers;            //максимальное количество пассажиров
    private boolean isAir;
    private boolean isWater;
    private boolean isGround;
    private double usingSpace;
    private int usingSeats;

    public Transport(String name, double loadCapacity, double transportationPrice,
                     double speed, int passengers) {
        this.name = name;
        this.loadCapacity = loadCapacity;
        this.transportationPrice = transportationPrice;
        this.speed = speed;
        this.passengers = passengers;
        this.isAir = false;
        this.isWater = false;
        this.isGround = false;
        this.usingSpace = 0;
        this.usingSeats = 0;
    }
    @Override
    public void print() {
        System.out.println("Название: " + name +
                "\nМаксимальная грузоподъёмность: " + loadCapacity + " т" +
                "\nЦена транспортировки: " + transportationPrice + " $/км" +
                "\nМаксимальная скорость: " + speed + " км/ч" +
                "\nМаксимальное количество пассажиров: " + passengers +
                "\nЗанято места: " + usingSpace + " т" +
                "\nСвободно места: " + (loadCapacity - usingSpace) + " т" +
                "\nЗанято мест пассажиров: " + usingSeats +
                "\nСвободно мест пассажиров: " + (passengers - usingSeats));
    }
}
