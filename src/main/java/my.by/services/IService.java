package my.by.services;

import my.by.city.City;
import my.by.transport.Transport;

public interface IService
{

    Transport[] checkPassengers(Transport[] transports, int passengers);
    double distanceCount(City departureCity, City arrivalCity);
    Transport[] checkPorts(Transport[] transports, City departureCity, City arrivalCity);
    Transport checkSpeed(Transport[] transports, City departureCity, City arrivalCity);
    Transport checkPrice(Transport[] transports);
    Transport[] checkLoadCapacity(Transport[] transports, double load);
}
