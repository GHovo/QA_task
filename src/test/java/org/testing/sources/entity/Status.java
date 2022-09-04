package org.testing.sources.entity;

public enum Status {
    ACTIVE("active"), INACTIVE("inactive");

    private final String name;

    Status(String name) {
        this.name = name;
    }
    public String getName() {
        return name.toLowerCase();
    }

    public static Status parse(String type) {
        for (Status status : Status.values()) {
            if (status.getName().equals(type)) {
                return status;
            }
        }
        throw new IllegalArgumentException();
    }
}
