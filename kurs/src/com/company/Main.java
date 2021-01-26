package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Ship ship1 = new Ship("Корабль с жидкостью1", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));
        Ship ship2 = new Ship("Корабль с песком1", Cargo.bulk, 80, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 3));
        Ship ship3 = new Ship("Корабль с контейнерами", Cargo.container, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 1));
        Ship ship4 = new Ship("Корабль с жидкостью2", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 4));
        Ship ship5 = new Ship("Корабль с жидкостью3", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));
        Ship ship6 = new Ship("Корабль с жидкостью4", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));
        Ship ship7 = new Ship("Корабль с песком2", Cargo.bulk, 80, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 3));
        //Ship ship8 = new Ship("Корабль с песком3", Cargo.bulk, 80, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 3));

        ArrayList<Ship> ships = new ArrayList<>(Arrays.asList(ship1, ship2, ship3, ship4, ship5, ship6, ship7));

        Crane craneForLiquid = new Crane(Cargo.liquid, 25);
        Crane craneForBulk = new Crane(Cargo.bulk, 30);
        Crane craneForContainers = new Crane(Cargo.container, 20);
        Crane craneForLiquid2 = new Crane(Cargo.liquid, 25);
        Crane craneForLiquid3 = new Crane(Cargo.liquid, 25);


        ArrayList<Crane> cranes = new ArrayList<>(Arrays.asList(craneForLiquid, craneForLiquid2, craneForLiquid2, craneForBulk, craneForContainers));

        Simulator simulator = new Simulator(ships, cranes, new Date(2020, Calendar.JANUARY, 1));

        simulator.unloadShip(30);
        simulator.getResults();

    }
}
