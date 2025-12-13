package ru.teamscore.supplier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.teamscore.supplier.inn.Inn;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        Address address1 = new Address("Russia", "Samara", "Street 1", "Building 1");
        Address address2 = new Address("Russia", "Samara", "Street 2", "Building 2");

        Producer producer = new Producer(Inn.valueOf("1111111117"), "Producer", address1);
        Dealer dealer = Dealer.valueOf(Inn.valueOf("1111111117"), "Dealer", address2,
            new BigDecimal("10"), producer);

        Item item1 = Item.valueOf("Item A", new BigDecimal("100"), "001", producer);
        Item item2 = Item.valueOf("Item B", new BigDecimal("200"), "002", dealer);
        Item item3 = Item.valueOf("Item C", new BigDecimal("300"), "003", producer);

        itemService = new ItemService(List.of(item1, item2, item3));
    }

    @Test
    void findByItemName_whenNameNull_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findByItemName(null),
            "Name is empty");
    }

    @Test
    void findByItemName_whenNameBlank_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findByItemName("   "),
            "Name is empty");
    }

    @Test
    void findByItemName_whenValid_returnList() {
        List<ItemModel> result = itemService.findByItemName("Item");
        assertEquals(3, result.size());
    }

    @ParameterizedTest
    @MethodSource("provideFindByItemName")
    void findByItemName_whenPartialMatch_returnMatchingItems(String searchName, int expectedSize,
                                                             String expectedFirstName,
                                                             BigDecimal expectedFirstPrice) {
        List<ItemModel> result = itemService.findByItemName(searchName);
        assertEquals(expectedSize, result.size());
        assertEquals(expectedFirstName, result.getFirst().name());
        assertEquals(expectedFirstPrice, result.getFirst().price());
    }

    private static Stream<Arguments> provideFindByItemName() {
        return Stream.of(
            Arguments.of("Item A", 1, "Item A", new BigDecimal("100")),
            Arguments.of("Item B", 1, "Item B", new BigDecimal("220.00"))
        );
    }

    @Test
    void findByItemPartNumber_whenPartNumberNull_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findByItemPartNumber(null),
            "Part number is empty");
    }

    @Test
    void findByItemPartNumber_whenPartNumberBlank_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> itemService.findByItemPartNumber("   "),
            "Part number is empty");
    }

    @ParameterizedTest
    @MethodSource("provideFindByItemPartNumber")
    void findByItemPartNumber_whenValid_returnOptionalWithItem(String partNumber,
                                                               String expectedName,
                                                               BigDecimal expectedPrice) {
        Optional<ItemModel> result = itemService.findByItemPartNumber(partNumber);
        assertTrue(result.isPresent());
        assertEquals(expectedName, result.get().name());
        assertEquals(expectedPrice, result.get().price());
    }

    private static Stream<Arguments> provideFindByItemPartNumber() {
        return Stream.of(
            Arguments.of("001", "Item A", new BigDecimal("100")),
            Arguments.of("002", "Item B", new BigDecimal("220.00")),
            Arguments.of("003", "Item C", new BigDecimal("300"))
        );
    }

    @Test
    void findByItemPartNumber_whenNotFound_returnEmptyOptional() {
        Optional<ItemModel> result = itemService.findByItemPartNumber("999");
        assertTrue(result.isEmpty());
    }
}
