package ru.teamscore.supplier;

import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;

public class Dealer extends Supplier {
    private BigDecimal marginPercent;
    private final Producer producer;

    private Dealer(Inn inn, String name, Address address, BigDecimal marginPercent,
                   Producer producer) {
        super(inn, name, address);
        this.marginPercent = marginPercent;
        this.producer = producer;
    }

    public static Dealer valueOf(Inn inn, String name, Address address, BigDecimal marginPercent,
                                 Producer producer) {
        if (marginPercent.compareTo(new BigDecimal("0.0")) < 0) {
            throw new IllegalArgumentException("Margin percent must be positive");
        }

        return new Dealer(inn, name, address, marginPercent, producer);
    }

    @Override
    public BigDecimal getFinalPrice(BigDecimal price) {
        return price.add(price.multiply(marginPercent.multiply(new BigDecimal("0.01"))));
    }

    public BigDecimal getMarginPercent() {
        return marginPercent;
    }

    public void setMarginPercent(BigDecimal marginPercent) {
        this.marginPercent = marginPercent;
    }

    public Producer getProducer() {
        return producer;
    }
}
