package de.schlueter;

import de.schlueter.annotations.Argument;
import de.schlueter.annotations.ArgumentImpl;
import lombok.Getter;

/**
 * Hello world!
 */
@Getter
public class App {

    @Argument(shortName = "n", longName = "name", description = "Name of the person")
    private String name;

    public static void main(String[] args) throws Exception {
        App app = new App();
        ArgumentImpl argument = new ArgumentImpl(app);
        argument.parseArgs(args);
        System.out.println("Hello " + app.getName() + "!");
    }

}
