package ru.job4j.parking;

import static org.junit.Assert.*;
import org.junit.Test;

public class ParkingAutoTest {
    @Test
    public void whenParkCar() {
        Parking parking = new ParkingAuto(1, 0);
        assertTrue(parking.park(new Car()));
    }

    @Test
    public void whenParkTruckOfSize5() {
        Parking parking = new ParkingAuto(2, 1);
        parking.park(new Car());
        parking.park(new Car());
        assertTrue(parking.park(new Truck(5)));
    }

    @Test
    public void whenParkTruckAndCar() {
        Parking parking = new ParkingAuto(2, 1);
        parking.park(new Truck(2));
        assertTrue(parking.park(new Car()));
    }

    @Test
    public void whenDoNotParkTruckOfSize2() {
        Parking parking = new ParkingAuto(2, 1);
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Car()));
        assertTrue(parking.park(new Truck(5)));
        assertFalse(parking.park(new Truck(2)));
    }

    @Test
    public void whenDoNotParkCar() {
        Parking parking = new ParkingAuto(0, 1);
        assertTrue(parking.park(new Truck(5)));
        assertFalse(parking.park(new Car()));
    }

    @Test
    public void whenDoNotParkTruckOnSpaceCars() {
        Parking parking = new ParkingAuto(3, 0);
        assertFalse(parking.park(new Truck(4)));
    }
}
