package ru.teamscore.supplier;

import java.util.List;
import java.util.Optional;

public class ItemService {
    private final List<Item> items;

    public ItemService(List<Item> items) {
        this.items = items;
    }

    public List<ItemModel> findByItemName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is empty");
        }

        return items.stream().filter(item -> item.getName().contains(name)).map(this::mapTo)
            .toList();
    }

    public Optional<ItemModel> findByItemPartNumber(String partNumber) {
        if (partNumber == null || partNumber.isBlank()) {
            throw new IllegalArgumentException("Part number is empty");
        }

        return items.stream().filter(item -> item.getPartNumber().equals(partNumber))
            .map(this::mapTo).findFirst();
    }

    private ItemModel mapTo(Item item) {
        String supplierName;

        if (item.getSupplier() instanceof Dealer dealer) {
            supplierName = dealer.getProducer().getName();
        } else {
            supplierName = item.getSupplier().getName();
        }

        return new ItemModel(
            item.getName(),
            item.getPartNumber(),
            item.getPrice(),
            item.getSupplier().getName(),
            item.getSupplier().getAddress(),
            supplierName
        );
    }
}
