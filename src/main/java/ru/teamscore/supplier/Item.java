package ru.teamscore.supplier;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class Item {
    private String name;
    private BigDecimal price;
    private String partNumber;
    private Supplier supplier;

    private Item(String name, BigDecimal price, String partNumber, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.partNumber = partNumber;
        this.supplier = supplier;
    }

    public static Item valueOf(String name, BigDecimal price, String partNumber,
                               Supplier supplier) {
        if (!Pattern.compile("^\\d{10,15}$").matcher(partNumber).find()) {
            throw new IllegalArgumentException("Part number must contains only 10-15 digit");
        }
        return new Item(name, supplier.getFinalPrice(price), partNumber, supplier);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
