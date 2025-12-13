package ru.teamscore.supplier;

import java.math.BigDecimal;

public record ItemModel(String name, String partNumber, BigDecimal price, String supplierName,
                        Address supplierAddress, String producerName) {
}
