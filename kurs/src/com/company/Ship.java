package com.company;

import java.util.Date;

public class Ship {
    String name; //Имя судна
    Cargo cargo; //Тип груза
    int cargoWeight; //Вес груза
    Date plannedArrivedDate; //Дата прибытия
    Date realArrivedDay; //Реальное время прибытия
    Date plannedDepartureDate; // Дата планируемого отбытия
    Date realDepartureDate; // Реальная дата отбытия

    public Ship(String name, Cargo cargo, int cargoWeight, Date plannedArrivedDate, Date plannedDepartureDate) {
        this.name = name;
        this.cargo = cargo;
        this.cargoWeight = cargoWeight;
        this.plannedArrivedDate = plannedArrivedDate;
        this.plannedDepartureDate = plannedDepartureDate;
        this.realArrivedDay = plannedArrivedDate;
    }

    public void setRealDepartureDate(Date realDepartureDate) {
        this.realDepartureDate = realDepartureDate;
    }


}
