package de.schlueter;

import de.schlueter.annotations.Argument;
import de.schlueter.annotations.ArgumentImpl;

/**
 * Hello world!
 *
 */
public class App {

    @Argument(shortName = "n", longName = "name", description = "Name of the person")
    private String name;
    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
