package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Ship ship1 = new Ship("Корабль с жидкостью1", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));
        Ship ship2 = new Ship("Корабль с песком1", Cargo.bulk, 80, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 3));
        Ship ship7 = new Ship("Корабль с песком2", Cargo.bulk, 80, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 3));
        Ship ship3 = new Ship("Корабль с контейнерами", Cargo.container, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 1));
        Ship ship4 = new Ship("Корабль с жидкостью2", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 4));
        Ship ship5 = new Ship("Корабль с жидкостью3", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));
        Ship ship6 = new Ship("Корабль с жидкостью4", Cargo.liquid, 50, new Date(2020, Calendar.JANUARY, 1), new Date(2020, Calendar.JANUARY, 2));

        ArrayList<Ship> ships = new ArrayList<>(Arrays.asList(ship1, ship2, ship3, ship4, ship5, ship6, ship7));

        Crane craneForLiquid = new Crane(Cargo.liquid, 25);
        Crane craneForBulk = new Crane(Cargo.bulk, 30);
        Crane craneForContainers = new Crane(Cargo.container, 20);

        ArrayList<Crane> cranes = new ArrayList<>(Arrays.asList(craneForLiquid, craneForBulk, craneForContainers));

        Date startDate = new Date(2020, Calendar.JANUARY, 1);

        runModel(ships, cranes, startDate);
    }

    static void runModel(ArrayList<Ship> ships, ArrayList<Crane> cranes, Date startDate) {
        Date currentDate = (Date) startDate.clone();
        ArrayList<Ship> unloadedShips = new ArrayList<>();
        ArrayList<Ship> shipsToUnload = new ArrayList<>(ships);

        Map<Cargo, ArrayList<Integer>> queues = new HashMap<>();
        queues.put(Cargo.bulk, new ArrayList<>());
        queues.put(Cargo.liquid, new ArrayList<>());
        queues.put(Cargo.container, new ArrayList<>());

        //while (shipsToUnload.size() != 0) {
        for(int i = 0; i < 30; i++) {
            //отправляем разгруженные корабли с кранов
            for (Crane crane : cranes) {
                if (crane.isBusy()) {
                    Ship tmp = crane.getCurrentShip();
                    if (tmp.realDepartureDate != null && !currentDate.before(tmp.realDepartureDate)) {
                        crane.setCurrentShip(null);
                        unloadedShips.add(tmp);
                        shipsToUnload.remove(tmp);
                    }
                }
            }

            Map<Cargo, Integer> currentDatQueue = new HashMap<>();

            //устанавливаем корабли пустым кранам
            for (Ship ship : shipsToUnload) {
                if (!currentDate.before(ship.realArrivedDay)) {
                    if (ship.wentToACrane == null) {
                        Optional<Crane> emtyCrane = cranes.stream().filter(crane ->
                                crane.getCargoType() == ship.cargo && !crane.isBusy()
                        ).findFirst();

                        if (emtyCrane.isPresent()) {
                            emtyCrane.get().setCurrentShip(ship);
                            ship.wentToACrane = (Date) currentDate.clone();
                        } else {
                            ship.dayWaiting++;
                            currentDatQueue.put(ship.cargo, currentDatQueue.getOrDefault(ship.cargo, 0) + 1);
                        }
                    }
                }
                if (currentDate.after(ship.plannedDepartureDate)) {
                    ship.penalty += 1000;
                }
            }

            for(Map.Entry<Cargo, Integer> e: currentDatQueue.entrySet()) {
                queues.get(e.getKey()).add(e.getValue());
            }

            //разгружаем корабли на кранах
            for (Crane crane : cranes) {
                if (crane.unloadShip()) {
                    //разгрузка законилась, устанавливаем departure date
                    Ship tmp = crane.getCurrentShip();
                    tmp.setRealDepartureDate(new Date(currentDate.getYear(), currentDate.getMonth(), currentDate.getDate()));
                }
            }

            currentDate.setDate(currentDate.getDate() + 1);
        }

        int averageWaiting = 0;
        Map<Cargo, Integer> penalties = new HashMap<>();
        for (Ship ss: unloadedShips) {
            averageWaiting += ss.dayWaiting;
            penalties.put(ss.cargo, penalties.getOrDefault(ss.cargo, 0) + ss.penalty);
            System.out.println(
                    ss.name + "\nЗапланированное прибытие: " + ss.plannedArrivedDate
                            + "\nРеальное прибытие: " + ss.realArrivedDay
                            + "\nЗапланированное отправление: " + ss.plannedDepartureDate
                            + "\nПрибытие к крану: " + ss.wentToACrane
                            + "\nРеальное отправление: " + ss.realDepartureDate
                            + "\nДней ожидания: " + ss.dayWaiting
                            + "\nШтраф: " + ss.penalty + "\n");
        }
        System.out.println("Среднее время ожидания: "+ averageWaiting/unloadedShips.size());
        System.out.println("Число разгруженных судов: "+ unloadedShips.size());

        for(Map.Entry<Cargo, ArrayList<Integer>> e: queues.entrySet()) {
            int average = 0;
            for(Integer i: e.getValue()) {
                average += i;
            }
            if (e.getValue().size() != 0) {
                System.out.println("Средняя очередь для " + e.getKey() + "  " + average / e.getValue().size());
            } else {
                System.out.println("Средняя очередь для " + e.getKey() + " пустая");
            }
        }

        for(Map.Entry<Cargo, Integer> e: penalties.entrySet()) {
            System.out.println("Штраф для крана " + e.getKey() + " опт. количество кранов +" + e.getValue()/30000);
        }
    }
}
