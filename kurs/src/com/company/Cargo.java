package com.company;

public enum Cargo {
    bulk, //сыпучий
    liquid, //жидкий
    container; //контейнер

    @Override
    public String toString() {
        switch (this){
            case container: return "контейнеры";
            case liquid: return "жидкий груз";
            case bulk: return "сыпучий груз";
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
