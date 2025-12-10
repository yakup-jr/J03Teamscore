package ru.teamscore.shipment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StorageInfoTest {

    @Test
    void valueOf_whenStorageNameBlank_throwInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class,
            () -> StorageInfo.valueOf("    ", "someOwner"), "storageName must not be blank");
    }

    @Test
    void valueOf_whenStorageOwnerBlank_thrownInvalidArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> StorageInfo.valueOf("someName",
            "    "), "storageOwner must not be blank");
    }

    @Test
    void valueOf_whenValid_returnStorageInfo() {
        assertDoesNotThrow(() -> StorageInfo.valueOf("someName", "someOwner"));
    }
}
