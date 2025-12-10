package ru.teamscore.shipment;

public class StorageInfo {
    private final String storageName;
    private final String storageOwner;

    private StorageInfo(String storageName, String storageOwner) {
        this.storageName = storageName;
        this.storageOwner = storageOwner;
    }

    public static StorageInfo valueOf(String storageName, String storageOwner) {
        if (storageName.isBlank()) {
            throw new IllegalArgumentException("storageName must not be blank");
        }
        if (storageOwner.isBlank()) {
            throw new IllegalArgumentException("storageOwner must not be blank");
        }

        return new StorageInfo(storageName, storageOwner);
    }

    public String getStorageName() {
        return storageName;
    }

    public String getStorageOwner() {
        return storageOwner;
    }
}
