package args.secondDraft;


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
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
