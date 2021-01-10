package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Ship ship1 = new Ship("Корабль с жидкостью", Cargo.liquid, 50, new Date(2020, 12, 1), new Date(2020, 12, 2));
        Ship ship2 = new Ship("Корабль с песком", Cargo.bulk, 80, new Date(2020, 12, 1), new Date(2020, 12, 3));
        Ship ship3 = new Ship("Корабль с контейнерами", Cargo.container, 50, new Date(2020, 12, 1), new Date(2020, 12, 1));
        Ship ship4 = new Ship("Корабль корабль с нефтью (жидкость)", Cargo.liquid, 50, new Date(2020, 12, 1), new Date(2020, 12, 4));
        ArrayList<Ship> shipsToUnload = new ArrayList<>();
        ArrayList<Ship> unloadedShips = new ArrayList<>();

        shipsToUnload.addAll(Arrays.asList(ship1, ship2, ship3, ship4));

        Crane craneForLiquid = new Crane(25);
        Crane craneForBulk = new Crane(30);
        Crane craneForContainers = new Crane(20);

        ArrayList<Crane> cranes = new ArrayList<>();

        cranes.addAll(Arrays.asList(craneForLiquid, craneForBulk, craneForContainers));

        Date curDate = new Date(2020, 12, 1);

        while (shipsToUnload.size() != 0) {
            for(Ship ship: shipsToUnload) {
                if (ship.realArrivedDay.getYear() <= curDate.getYear() &&
                        ship.realArrivedDay.getMonth() <= curDate.getMonth() &&
                        ship.realArrivedDay.getDate() <= curDate.getDate()) {

                    Crane tmpCrane = null;

                    switch (ship.cargo) {
                        case bulk:
                            tmpCrane = craneForBulk.isBusy() ? null : craneForBulk;
                            break;
                        case liquid:
                            tmpCrane = craneForLiquid.isBusy() ? null : craneForLiquid;
                            break;
                        case container:
                            tmpCrane = craneForContainers.isBusy() ? null : craneForContainers;
                            break;
                    }

                    if(tmpCrane != null) {
                        tmpCrane.setCurrentShip(ship);
                    }

                }

            }

            for (Crane crane: cranes) {
                if(crane.unloadShip()) {
                    Ship tmp = crane.getCurrentShip();
                    tmp.realDepartureDate = new Date(curDate.getYear(), curDate.getMonth(), curDate.getDate());
                    crane.setCurrentShip(null);
                    unloadedShips.add(tmp);
                    shipsToUnload.remove(tmp);
                }
            }
            curDate.setDate(curDate.getDate()+1);
        }

        for (Ship ss: unloadedShips)
            System.out.println(ss.name +"\t"+ ss.realArrivedDay +"\t"+ ss.realDepartureDate);

    }
}
