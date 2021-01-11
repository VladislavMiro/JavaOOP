package com.company;

import java.util.Date;
import java.util.Random;

public class Ship {
    String name; //Имя судна
    Cargo cargo; //Тип груза
    int cargoWeight; //Вес груза
    Date plannedArrivedDate; //Дата прибытия
    Date realArrivedDay; //Реальное время прибытия
    Date wentToACrane;
    Date plannedDepartureDate; // Дата планируемого отбытия
    Date realDepartureDate; // Реальная дата отбытия
    int penalty; //штраф
    int dayWaiting; //дней ожидания

    public Ship(String name, Cargo cargo, int cargoWeight, Date plannedArrivedDate, Date plannedDepartureDate) {
        this.name = name;
        this.cargo = cargo;
        this.cargoWeight = cargoWeight;
        this.plannedArrivedDate = plannedArrivedDate;
        this.realArrivedDay = (Date) plannedArrivedDate.clone();
        this.realArrivedDay.setDate(this.realArrivedDay.getDate() + getRandomOffset(-7, 7));
        this.plannedDepartureDate = plannedDepartureDate;
    }

    private int getRandomOffset(int from, int to) {
        return from + (int) (Math.random() * (to - from + 1));
    }

    public void setRealDepartureDate(Date realDepartureDate) {
        this.realDepartureDate = realDepartureDate;
        this.realDepartureDate.setDate(realDepartureDate.getDate() + getRandomOffset(0, 10));
    }


}
