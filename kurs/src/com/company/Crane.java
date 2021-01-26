package com.company;

public class Crane {
    private int cost = 30000;
    private int unloadCargoWeightPerDay;
    private Ship currentShip;
    private Cargo cargoType;

    public Crane(Cargo craneCargoType, int unloadCargoWeightPerDay) {
        this.unloadCargoWeightPerDay = unloadCargoWeightPerDay;
        cargoType = craneCargoType;
    }

    public Ship getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }

    public boolean unloadShip() {
        if(currentShip != null) {
            if (currentShip.getCargoWeight() < unloadCargoWeightPerDay) {
                currentShip.setCargoWeight(0);
            } else {
                currentShip.substractTheWeightOfTheCargo(unloadCargoWeightPerDay);
            }

            return currentShip.getCargoWeight() == 0;
        } else  {
            return false;
        }

    }

    public boolean isBusy() {
        return currentShip != null;
    }

    public Cargo getCargoType() {
        return cargoType;
    }
}
