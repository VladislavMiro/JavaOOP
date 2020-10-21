package com.company;

public enum Commands {
    LS("LS"),
    CD("CD"),
    EXIT("EXIT"),
    ERROR("ERROR"),
    CLEAR("CLEAR"),
    CAT("CAT"),
    TOUCH("TOUCH"),
    RM("RM"),
    TEE("TEE"),
    ;

    private String command;

    Commands(String command) {
        this.command = command.toUpperCase();
    }

    public String getCommand() {
        return  command;
    }
}
