package net.dictionary.api.client;

public enum EndpointURL {
    TRANSLATE ("/translate");
    String path;
    EndpointURL(String path) { this.path = path; }
    public String getPath() {return path;}
    public String addPath(String additionalPath) {return path + additionalPath; }
}
