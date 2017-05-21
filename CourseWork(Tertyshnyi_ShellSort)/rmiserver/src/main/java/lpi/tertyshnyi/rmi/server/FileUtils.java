package lpi.tertyshnyi.rmi.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    private static final String SPACE_SIGN = " ";

    public List<String> convertCSVFileToString(File csvFilePath){
        String readedLine;
        List<String> values = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {

            while ((readedLine = br.readLine()) != null) {
                String[] lineValues = readedLine.split(SPACE_SIGN);
                values.addAll(Arrays.asList(lineValues));
            }

        } catch (Exception e) {
            System.out.println("Could not read CSV file from path: " + csvFilePath + "; cause: " + e);
        }
        return values;

    }

    public File writeValuesToFile(String values, File targetFilePath){
        File targetFile = targetFilePath;
        try (FileWriter fileWriter = new FileWriter(targetFile)) {
            fileWriter.write(values);
        } catch (Exception e) {
            System.out.println("Could not write CSV file to path: " + targetFilePath + "; cause: " + e);
        }
        return targetFile;
    }

    public int[] sort(List<String> values){
        int[] integerArray = values.stream().mapToInt(value -> Integer.valueOf(value)).toArray();

        int inner, outer;
        int temp;
        //find initial value of h
        int h = 1;
        while (h <= integerArray.length / 3)
            h = h * 3 + 1; // (1, 4, 13, 40, 121, ...)

        while (h > 0) // decreasing h, until h=1
        {
            // h-sort the file
            for (outer = h; outer < integerArray.length; outer++) {
                temp = integerArray[outer];
                inner = outer;
                // one subpass (eg 0, 4, 8)
                while (inner > h - 1 && integerArray[inner - h] >= temp) {
                    integerArray[inner] = integerArray[inner - h];
                    inner -= h;
                }
                integerArray[inner] = temp;
            }
            h = (h - 1) / 3; // decrease h
        }

        return integerArray;
    }


}
