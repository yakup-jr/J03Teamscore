package ru.teamscore.supplier;

import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;

public class Producer extends Supplier {
    public Producer(Inn inn, String name, Address address) {
        super(inn, name, address);
    }

    @Override
    public BigDecimal getFinalPrice(BigDecimal price) {
        return price;
    }
}
