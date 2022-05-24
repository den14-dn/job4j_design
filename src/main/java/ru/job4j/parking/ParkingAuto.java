package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingAuto implements Parking {
    private int countCar;
    private int countTruck;
    private List<Auto> autoList;

    public ParkingAuto(int countCar, int countTruck) {
        this.countCar = countCar;
        this.countTruck = countTruck;
        autoList = new ArrayList<>(countCar + countTruck);
    }

    @Override
    public boolean park(Auto auto) {
        boolean rst = false;
        if (auto.getSize() == Car.SIZE_CAR && countCar >= Car.SIZE_CAR) {
            countCar--;
            rst = autoList.add(auto);
        } else if (auto.getSize() > Car.SIZE_CAR && countTruck >= Car.SIZE_CAR) {
            countTruck--;
            rst = autoList.add(auto);
        } else if (auto.getSize() <= countCar) {
            countCar -= auto.getSize();
            rst = autoList.add(auto);
        }
        return rst;
    }
}
