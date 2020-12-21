package myrestaurant.menu.parser;

import java.util.HashSet;
import java.util.Set;

import myrestaurant.menu.ValidationResult;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FilenameUtils;


public class CustomCommandParser {
    public static final String HELP = "help";
    public static final String HELP_DESCRIPTION = "Usage: java -jar <jar file> <input xml/json file> <order>. \n" +
            " Eg. java -jar menu-1.0-SNAPSHOT.jar menu.xml desc";

    Set<String> order = new HashSet<>();
    Set<String> fileNameExtensions = new HashSet<>();

    private org.apache.commons.cli.Options options;
    private CommandLineParser parser;

    public CustomCommandParser() {
        order.add("ASC");
        order.add("DESC");

        fileNameExtensions.add("JSON");
        fileNameExtensions.add("XML");

        options = configOptions();
        parser = new DefaultParser();
    }

    public ValidationResult validateArguments(String[] args) throws ParseException {

        CommandLine cmd = parser.parse(options, args);
        if (cmd.hasOption(HELP)) {
            displayUsage("Main", options);
            return ValidationResult.INVALID;
        }

        if (!validateOptions(args)) {
            displayUsage("Parameters not passed in correct format/combination", options);
            return ValidationResult.INVALID;
        }

        return ValidationResult.VALID;

    }

    private void displayUsage(String message, Options opt) {

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp(message, opt);
    }

    private Options configOptions() {
        Options options = new Options();
        options.addOption(Option.builder(HELP).hasArg(false).desc(HELP_DESCRIPTION).build());
        return options;
    }

    private boolean validateOptions(String[] args) {
        return args.length == 2 &&
                order.contains(args[1].toUpperCase()) &&
                fileNameExtensions.contains(FilenameUtils.getExtension(args[0]).toUpperCase());
    }

}