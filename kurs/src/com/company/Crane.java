package com.company;

import java.util.Date;

public class Crane {
    private int cost = 30000;
    private int unloadCargoWeightPerDay;
    private Date delayTime;
    private Ship currentShip;

    public Crane(int unloadCargoWeightPerDay) {
        this.unloadCargoWeightPerDay = unloadCargoWeightPerDay;
    }

    public Ship getCurrentShip() {
        return currentShip;
    }

    public void setCurrentShip(Ship currentShip) {
        this.currentShip = currentShip;
    }

    public boolean unloadShip() {
        if(currentShip != null) {
            if (currentShip.cargoWeight < unloadCargoWeightPerDay) {
                currentShip.cargoWeight = 0;
            } else {
                currentShip.cargoWeight -= unloadCargoWeightPerDay;
            }

            if (currentShip.cargoWeight == 0) {
                return true;
            } else {
                return false;
            }
        } else  {
            return false;
        }

    }

    public boolean isBusy() {
        return currentShip != null;
    }

}
