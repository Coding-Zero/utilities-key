package com.codingzero.utilities.key;

import java.util.UUID;

public class TimeBasedUUIDKey {

    private UUID uuid;
    private byte[] key;

    public TimeBasedUUIDKey(UUID uuid) {
        this.uuid = uuid;
        checkForInvalidUUID();
        this.key = new Hex(generateKey()).getBytes();
    }

    public UUID getUUID() {
        return uuid;
    }

    private void checkForInvalidUUID() {
        String uuidString = getUUID().toString();
        if (Integer.valueOf(uuidString.substring(14, 15)) != 1) {
            throw new IllegalArgumentException("Only version 1 (Time-based) UUID accepted.");
        }
    }

    private String generateKey() {
        String uuidString = getUUID().toString().toLowerCase();
        String orderedUUIDString = uuidString.substring(14, 18)
                + uuidString.substring(9, 13)
                + uuidString.substring(0, 8)
                + uuidString.substring(19, 23)
                + uuidString.substring(24);
        return orderedUUIDString;
    }

    public byte[] getKey() {
        return key.clone();
    }

}
