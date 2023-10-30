package args.firstDraft;

import args.finalVersion.exception.ArgsException;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            Args arg = new Args("l,p#,d*", args);

            // Check if arguments exist and get their values
            boolean logging = arg.getBoolean('l');
            int port = arg.getInt('p');
            String directory = arg.getString('d');

            System.out.println("Logging: " + logging);
            System.out.println("Port: " + port);
            System.out.println("Directory: " + directory);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof ArgsException) {
                ArgsException argsException = (ArgsException) e.getCause();
                try {
                    System.out.println("Error: " + argsException.errorMessage());
                } catch (Exception ex) {
                    System.out.println("Error: Unexpected error occurred.");
                }
            } else {
                System.out.println("Error: " + e.getMessage());
            }
        } catch (ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
