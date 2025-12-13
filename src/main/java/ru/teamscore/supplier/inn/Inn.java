package ru.teamscore.supplier.inn;

public class Inn {
    private final String value;
    private final InnType innType;

    private Inn(String value, InnType innType) {
        this.value = value;
        this.innType = innType;
    }

    public static Inn valueOf(String inn) {
        InnValidator.validateInn(inn);
        if (inn.length() == 10) {
            return new Inn(inn, InnType.ORGANIZATION);
        } else {
            return new Inn(inn, InnType.PHYSICS);
        }
    }

    public String getValue() {
        return value;
    }

    public InnType getInnType() {
        return innType;
    }
}
