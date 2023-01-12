package my.by.services;

import my.by.city.City;
import my.by.transport.Transport;

public class Services implements IService{
    public Transport[] checkLoadCapacity(Transport[] transports, double load){     //проверка на грузоподъёмность
        Transport[] checked = new Transport[transports.length];
        int i = 0;
        for (Transport transport : transports){
            try{
                if (transport.getLoadCapacity() - transport.getUsingSpace() >= load){
                    checked[i] = transport;
                }
            }
            catch(NullPointerException e){
                continue;
            }
            i++;
        }
        return checked;
    }

    public Transport checkPrice(Transport[] transports){                           //нахождения дешёвого варианта
        Transport lowestPrice = null;
        for (Transport transport : transports){
            if (transport != null){
                lowestPrice = transport;
            }
        }
        if (lowestPrice == null){
            return null;
        }
        for (Transport transport : transports){
            try{
                if (transport.getTransportationPrice() < lowestPrice.getTransportationPrice()){
                    lowestPrice = transport;
                }
            }
            catch(NullPointerException ignored){
            }
        }
        return lowestPrice;
    }

    public Transport checkSpeed(Transport[] transports, City departureCity, City arrivalCity){      //нахождение быстрого варианта
        Transport fastest = null;
        for (Transport transport : transports){
            if (transport != null){
                fastest = transport;
            }
        }
        if (fastest == null){
            return null;
        }
        int[] none = new int[transports.length];
        for (int i = 0; i < transports.length; i++){
            none[i] = -1;
        }
        if (distanceCount(departureCity, arrivalCity) <= 150){
            for (int i = 0; i < transports.length; i++){
                try{
                    if (transports[i].isAir()){
                        none[i] = i;
                        continue;
                    }
                    fastest = transports[i];
                }
                catch (NullPointerException ignored){
                }
            }
        }
        for (int i = 0; i < transports.length; i++){
            if (i == none[i]){
                continue;
            }
            try{
                if (transports[i].getSpeed() > fastest.getSpeed()){
                    fastest = transports[i];
                }
            }
            catch(NullPointerException ignored){
            }
        }
        return fastest;
    }

    public Transport[] checkPorts(Transport[] transports, City departureCity, City arrivalCity){
        Transport[] checked = new Transport[transports.length];
        int i = 0;
        for (Transport transport : transports){
            try{
                if(transport.isAir() && departureCity.isHasAirport() && arrivalCity.isHasAirport()){
                    checked[i] = transport;
                }
                else if(transport.isWater() && departureCity.isHasPort() && arrivalCity.isHasPort()){
                    checked[i] = transport;
                }
                else if(transport.isGround()){
                    checked[i] = transport;
                }
            }
            catch (NullPointerException ignored){
            }
            i++;
        }
        return checked;
    }

    public Transport[] checkPassengers(Transport[] transports, int passengers){   //проверка на вместимость пассажиров
        Transport[] checked = new Transport[transports.length];
        int i = 0;
        for (Transport transport : transports){
            try{
                if (transport.getPassengers() >= passengers){
                    checked[i] = transport;
                }
            }
            catch(NullPointerException ignored){
            }
            i++;
        }
        return checked;
    }

    public double distanceCount(City departureCity, City arrivalCity){
        double kmToDegreeLatitude = 111.1348611111111;
        double kmToDegreeLongitude = 90 - ((departureCity.getLatitudePosition() + arrivalCity.getLatitudePosition() / 2) * 1.92);
        return Math.sqrt(Math.pow((departureCity.getLatitudePosition() - arrivalCity.getLatitudePosition()) * kmToDegreeLatitude, 2) +
                Math.pow((departureCity.getLongitudePosition() - arrivalCity.getLongitudePosition()) * kmToDegreeLongitude, 2));
    }

    public  Transport[] findingBestTransport(Transport[] transports, double load, int passengers,
                                                   City departureCity, City arrivalCity){  //нахождение лучших вариантов для перевозки
        Transport[] checkedLoad = checkLoadCapacity(transports, load);
        Transport[] checkedPassengers = checkPassengers(checkedLoad, passengers);
        Transport[] checkedPorts = checkPorts(checkedPassengers, departureCity, arrivalCity);
        Transport fastestTransport = checkSpeed(checkedPorts, departureCity, arrivalCity);
        Transport cheapestTransport = checkPrice(checkedPorts);
        return new Transport[]{fastestTransport, cheapestTransport};
    }
}
