import flows.MainFlow;

public class Main {
    public static void main(String[] args) {

        if (args.length == 0) {
            new MainFlow(false);
            return;
        }
        if (args[0].equals("-g") || args[0].equals("--gui")) {
            new MainFlow(true);
            return;
        }

        System.out.println("Usage: java Main [--gui] [-cp path] | [-h]\n\n\nOptions:\n-g, --gui\t start the program in gui mode\n-cp,\t<path>\t path to dist folder where Main.class exists\n\n-h, --help\t show help message");
    }

}