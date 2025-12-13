package ru.teamscore.supplier;

import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;

public abstract class Supplier {
    private final Inn inn;
    private String name;
    private Address address;

    protected Supplier(Inn inn, String name, Address address) {
        this.inn = inn;
        this.name = name;
        this.address = address;
    }

    public Inn getInn() {
        return inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public abstract BigDecimal getFinalPrice(BigDecimal price);
}
