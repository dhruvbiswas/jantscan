package ca.jantscan;

import ca.jantscan.enums.ExitCodeEnum;
import ca.jantscan.jmanager.JarManager;

public class JAntScanMain {

    public static ExitCodeEnum exitCode = ExitCodeEnum.FAIL;

    public static ExitCodeEnum run(String jarFilePath) {
        ExitCodeEnum exitCodeEnum = ExitCodeEnum.FAIL;
        try {
            JarManager jarManager = new JarManager(jarFilePath);

            // Initialize jarManager
            jarManager.init();

            // Process jarManager
            jarManager.process();

            // Generate report
            jarManager.report();

            exitCodeEnum = ExitCodeEnum.SUCCESS;
        } catch (Exception jmex) {
            System.out.println(jmex.getMessage());
            exitCodeEnum = ExitCodeEnum.FAIL;
        }

        return exitCodeEnum;
    }

    public static void main(String[] args) {
        System.out.println("Starting scan....");

        if (args.length == 1) {
            String jarFilePath = args[0];

            JAntScanMain.exitCode = JAntScanMain.run(jarFilePath);
        } else {
            System.out.println("Usage: java JAntScanMain <absolute-path-to-jarfile>");

            JAntScanMain.exitCode = ExitCodeEnum.SUCCESS;
        }

        System.exit(JAntScanMain.exitCode.getCode());
    }
}
