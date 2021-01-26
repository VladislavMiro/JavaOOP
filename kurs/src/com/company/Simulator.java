package com.company;

import java.util.*;

public class Simulator {

    private ArrayList<Ship> ships;
    private ArrayList<Crane> cranes;
    private Date startDate;
    private Date currentDate;

    private ArrayList<Ship> unloadedShips = new ArrayList<>();
    private ArrayList<Ship> shipsToUnload;
    private Map<Cargo, ArrayList<Integer>> queues = new HashMap<>();

    public Simulator(ArrayList<Ship> ships, ArrayList<Crane> cranes, Date startDate) {
        this.ships = ships;
        this.cranes = cranes;
        this.startDate = startDate;
        currentDate = (Date) startDate.clone();
        queues.put(Cargo.bulk, new ArrayList<>());
        queues.put(Cargo.liquid, new ArrayList<>());
        queues.put(Cargo.container, new ArrayList<>());
        shipsToUnload = new ArrayList<>(ships);
    }


    private void queueToCrane () {
        Map<Cargo, Integer> currentDateQueue = new HashMap<>();
        //устанавливаем новые корабли пустым кранам
        for (Ship ship : shipsToUnload) {
            if (!currentDate.before(ship.getRealArrivedDay())) { //если прибыл
                if (ship.getDateOfWentToACrane() == null) { //если нет даты когда пришел на кран
                    //находим пустой кран для груза
                    Optional<Crane> emptyCrane = cranes.stream().filter(crane ->
                            crane.getCargoType() == ship.getCargo() && !crane.isBusy()
                    ).findFirst();
                    //если есть
                    if (emptyCrane.isPresent()) {
                        emptyCrane.get().setCurrentShip(ship);//устонавливаем путому крану корабль
                        ship.setDateOfWentToACrane((Date) currentDate.clone());
                    } else {
                        ship.setDayWaiting(1);//иначе прибавляем день ожидания
                        //за данный день в очереди добавляется корабль
                        currentDateQueue.put(ship.getCargo(), currentDateQueue.getOrDefault(ship.getCargo(), 0) + 1);
                    }
                }
            }
            if (currentDate.after(ship.getPlannedDepartureDate())) {
                ship.setPenalty(1000); // начисление штрафа
            }
        }
        //сколько в очереди за текующую дату кораблей
        for(Map.Entry<Cargo, Integer> e: currentDateQueue.entrySet()) {
            queues.get(e.getKey()).add(e.getValue());
        }

    }

    public void unloadShip(int days) {

        //while (shipsToUnload.size() != 0) {
        for(int i = 0; i < days; i++) {
            //отправляем разгруженные корабли с кранов
            for (Crane crane : cranes) {
                if (crane.isBusy()) {
                    Ship tmp = crane.getCurrentShip();
                    if (tmp.getRealDepartureDate() != null && !currentDate.before(tmp.getRealDepartureDate())) { //текущая дата не раньше чем дата отправления
                        crane.setCurrentShip(null); //освобождаем кран
                        unloadedShips.add(tmp); //корабль разгружен
                        shipsToUnload.remove(tmp); // удалаем из очереди на разгрузку
                    }
                }
            }


            //устонавливаем корабли на пустые краны и запоминаем день ожидания, если корабль не попал на кран
            queueToCrane();

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

    }

    public void getResults() {
        int averageWaiting = 0;//среднее время ожидания

        Map<Cargo, Integer> penalties = new HashMap<>();

        System.out.println("Информация о судах:");
        for (Ship ship: unloadedShips) {
            averageWaiting += ship.getDayWaiting();
            penalties.put(ship.getCargo(), penalties.getOrDefault(ship.getCargo(), 0) + ship.getPenalty());

            System.out.println("Название судна: " + ship.getName()
                                + "\nВремя прихода в порт: " + ship.getRealArrivedDay()
                                + "\nЗапланированное отправление: " + ship.getPlannedDepartureDate()
                                + "\nВремя ожидания очереди на разгрузку: " + (int) ((ship.getDateOfWentToACrane().getTime() - ship.getRealArrivedDay().getTime())/8.64e+7)
                                + "\nДата начала разгрузки: " + ship.getDateOfWentToACrane()
                                + "\nПродолжительность разгрузки: " + (int) ((ship.getRealDepartureDate().getTime() - ship.getDateOfWentToACrane().getTime())/8.64e+7)
                                + "\nРеальное отправление: " + ship.getRealDepartureDate()
                                + "\nШтраф: " + ship.getPenalty() + "\n");
        }

        for (Ship ship: shipsToUnload) {
            penalties.put(ship.getCargo(), penalties.getOrDefault(ship.getCargo(), 0) + ship.getPenalty());
        }

        System.out.println("Среднее время ожидания: "+ averageWaiting/(shipsToUnload.size() + unloadedShips.size()));
        System.out.println("Число разгруженных судов: "+ unloadedShips.size());

        for(Map.Entry<Cargo, ArrayList<Integer>> e: queues.entrySet()) {
            OptionalDouble average = e.getValue().stream().mapToInt(Integer::intValue).average();

            if (average.isPresent()) {
                System.out.println("Средняя очередь на разгрузку для " + e.getKey() + ":  " + average.getAsDouble());
            } else {
                System.out.println("Средняя очередь на разгрузку для " + e.getKey() + ": пусто");
            }
        }

        System.out.println("Штраф на кране для " + Cargo.container + ": " + penalties.get(Cargo.container));
        System.out.println("Штраф на кране для " + Cargo.liquid + ": " + penalties.get(Cargo.liquid));
        System.out.println("Штраф на кране для " + Cargo.bulk + ": " + penalties.get(Cargo.bulk));

        for(Map.Entry<Cargo, Integer> e: penalties.entrySet()) {
            System.out.println("необходимое кол-во кранов для " + e.getKey() + ": " + e.getValue()/30000);
        }

        int pen = unloadedShips.stream().mapToInt(s -> s.getPenalty()).sum();
        pen += shipsToUnload.stream().mapToInt(s -> s.getPenalty()).sum();

        System.out.println("Общая сумма штрафа: " + pen);

    }

}
