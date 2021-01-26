package com.company;

import java.util.Date;
import java.util.Random;

public class Ship {
    private String name; //Имя судна
    private Cargo cargo; //Тип груза
    private Date plannedArrivedDate; //Дата прибытия
    private Date realArrivedDay; //Реальное время прибытия
    private Date wentToACrane; //Дата отправления на кран для разгрузки
    private Date plannedDepartureDate; // Дата планируемого отбытия
    private Date realDepartureDate; // Реальная дата отбытия
    private int penalty = 0; //штраф
    private int dayWaiting = 0; //дней ожидания
    private int cargoWeight; //Вес груза

    public Ship(String name, Cargo cargo, int cargoWeight, Date plannedArrivedDate, Date plannedDepartureDate) {
        this.name = name;
        this.cargo = cargo;
        this.cargoWeight = cargoWeight;
        this.plannedArrivedDate = plannedArrivedDate;
        this.realArrivedDay = (Date) plannedArrivedDate.clone();
        this.realArrivedDay.setDate(this.realArrivedDay.getDate() + getRandom(-7, 7));
        this.plannedDepartureDate = plannedDepartureDate;
    }

    public String getName() { return name; }

    public Cargo getCargo() { return cargo; }

    public Date getRealArrivedDay() { return realArrivedDay; }

    public Date getDateOfWentToACrane() { return wentToACrane; }

    public Date getPlannedDepartureDate() { return plannedDepartureDate; }

    public Date getRealDepartureDate() { return realDepartureDate; }

    public int getPenalty() { return penalty; }

    public int getDayWaiting() { return dayWaiting; }

    public int getCargoWeight() { return cargoWeight; }

    public void setDateOfWentToACrane(Date date) { wentToACrane = date; }

    public void setPenalty(int penalty) { this.penalty += penalty; }

    public void setDayWaiting(int day) { dayWaiting += day; }

    public void setCargoWeight(int weight) { cargoWeight = weight; }

    public void substractTheWeightOfTheCargo(int weight) { cargoWeight -= weight; }

    private int getRandom(int from, int to) {
        return from + (int) (Math.random() * (to - from + 1));
    }

    public void setRealDepartureDate(Date realDepartureDate) {
        this.realDepartureDate = realDepartureDate;
        this.realDepartureDate.setDate(realDepartureDate.getDate() + getRandom(0, 10));
    }


}
