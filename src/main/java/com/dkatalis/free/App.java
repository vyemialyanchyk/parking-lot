package com.dkatalis.free;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Function;

public class App {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Parking Lot application");
            System.out.println("Provide file name (path to) as execution argument");
            System.out.print("Would you like to run tests with files in provided folder 'tests' [Y/n]: ");
            final Scanner input = new Scanner(System.in);
            final char x = input.next().charAt(0);
            if (x == 'Y' || x == 'y') {
                performTests();
            }
            return;
        }
        final String pathName = args[0];
        processFile(pathName, (String output) -> {
            System.out.println(output);
            return output;
        });
    }

    public static void processFile(final String pathName, Function<String, String> callback) {
        final ParkingLotControlPoint parkingLotControlPoint = new ParkingLotControlPoint();
        final File commandsFile = new File(pathName);
        try (final Scanner commandsFileReader = new Scanner(commandsFile);) {
            while (commandsFileReader.hasNextLine()) {
                final String command = commandsFileReader.nextLine();
                try {
                    parkingLotControlPoint.command(command, callback);
                } catch (ParkingLotCmdException ex) {
                    final String msg = String.format("ParkingLotCmdException: %s", ex.getMessage());
                    if (callback != null) {
                        callback.apply(msg);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            final String msg = String.format("FileNotFoundException: %s", ex.getMessage());
            if (callback != null) {
                callback.apply(msg);
            }
        }
    }

    public static String readFile2String(final String pathName) {
        String res = "";
        final File inputFile = new File(pathName);
        try (final Scanner fileReader = new Scanner(inputFile);) {
            while (fileReader.hasNextLine()) {
                final String line = fileReader.nextLine();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                res += line + System.lineSeparator();
            }
        } catch (FileNotFoundException ex) {
            final String msg = String.format("FileNotFoundException: %s", ex.getMessage());
            System.out.println(msg);
        }
        return res;
    }

    public static void performTests() {
        try {
            final HashMap<String, String> commandsFileName2Output = new HashMap<>();
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./tests"))) {
                final String testsOutputSuffix = ".out.txt";
                for (Path path : stream) {
                    if (Files.isDirectory(path)) {
                        continue;
                    }
                    final String fileName = path.getFileName().toString();
                    if (!fileName.endsWith(testsOutputSuffix)) {
                        continue;
                    }
                    final String testName = fileName.substring(0, fileName.length() - testsOutputSuffix.length());
                    final String expectedOutput = readFile2String(path.toAbsolutePath().toString());
                    commandsFileName2Output.put(testName, expectedOutput);
                }
            }
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("./tests"))) {
                final String testsInputSuffix = ".in.txt";
                for (Path path : stream) {
                    if (Files.isDirectory(path)) {
                        continue;
                    }
                    final String fileName = path.getFileName().toString();
                    if (!fileName.endsWith(testsInputSuffix)) {
                        continue;
                    }
                    final String testName = fileName.substring(0, fileName.length() - testsInputSuffix.length());
                    System.out.println();
                    System.out.println("--> " + testName);
                    System.out.println("====================");
                    final String expectedOutput = commandsFileName2Output.get(testName);
                    final StringBuilder output = new StringBuilder();
                    processFile(path.toAbsolutePath().toString(), (String msg) -> {
                        System.out.println(msg);
                        output.append(msg + System.lineSeparator());
                        return msg;
                    });
                    System.out.println("====================");
                    System.out.println("!!!!!!!!!!!!!!!!!!!!");
                    if (expectedOutput == null) {
                        System.out.println("There is no test output for: " + testName);
                    } else if (!expectedOutput.equals(output.toString())) {
                        System.out.println("Test error: " + testName);
                        final String[] expectedLines = StringUtils.split(expectedOutput, System.lineSeparator());
                        final String[] outputLines = StringUtils.split(output.toString(), System.lineSeparator());
                        final int lines2Verify = expectedLines.length < outputLines.length ? expectedLines.length : outputLines.length;
                        for (int i = 0; i < lines2Verify; i++) {
                            if (expectedLines[i].equals(outputLines[i])) {
                                continue;
                            }
                            System.out.println("Line number " + i + " is different: ");
                            System.out.println("Output: " + outputLines[i]);
                            System.out.println("Expected: " + expectedLines[i]);
                            break;
                        }
                        if (expectedLines.length != outputLines.length) {
                            System.out.println("Different lines number in output: " + outputLines.length + ", expected: " + expectedLines.length);
                        }
                    } else {
                        System.out.println("Test verified: " + testName);
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Process './tests' folder exception: " + ex.getMessage());
        }
    }
}
