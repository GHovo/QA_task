package org.testing.sources.entity;

public enum Endpoint {
    USERS("users/"),
    POSTS("posts/"),
    COMMENTS("comments/"),
    TODOS("todos/");


    private final String path;

    Endpoint(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}
