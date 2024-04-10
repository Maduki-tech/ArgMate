package de.schlueter.annotations;

public interface ArgumentInterface {
    /**
     * Get the argument from the object and set it to the field
     *
     * @param args The arguments to parse
     * @throws Exception If the argument could not be set
     */
    void parseArgs(String[] args) throws Exception;
}
