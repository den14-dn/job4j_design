package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingAuto implements Parking {
    private int countCar;
    private int countTruck;
    private List<Auto> autoList = new ArrayList<>();

    public ParkingAuto(int countCar, int countTruck) {
        this.countCar = countCar;
        this.countTruck = countTruck;
    }

    @Override
    public boolean park(Auto auto) {
        boolean rst = false;
        if (auto.getSize() == 1 && countCar > 0) {
            countCar--;
            rst = autoList.add(auto);
        } else if (auto.getSize() > 1 && countTruck > 0) {
            countTruck--;
            rst = autoList.add(auto);
        } else if (auto.getSize() <= countCar) {
            countCar -= auto.getSize();
            rst = autoList.add(auto);
        }
        return rst;
    }
}
