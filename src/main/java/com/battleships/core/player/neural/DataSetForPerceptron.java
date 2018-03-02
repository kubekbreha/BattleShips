package com.battleships.core.player.neural;


import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;

import java.io.*;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Kubo Brehuv with <3 (24.2.2018)
 */
public class DataSetForPerceptron {

    private DataSet trainingSet;

    public DataSetForPerceptron() {
        trainingSet = new DataSet(11, 1);
    }

    public void addDataToCsv(List tableNumbers) {
        PrintWriter pw = null;

        try {
            pw = new PrintWriter(new FileWriter("res/train_data.csv", true));
        } catch (FileNotFoundException e) {
            System.out.println("CSV file not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();

        System.out.println(tableNumbers.size());
        for (int numberInList = 0; numberInList < tableNumbers.size(); numberInList++) {
            if (numberInList % 11 == 0) {
                sb.append('\n');
            }
            sb.append(tableNumbers.get(numberInList));
            sb.append(',');
        }

        if (pw != null) {
            pw.write(sb.toString());
            pw.close();
        }
    }


    public DataSet readFromCsv() throws FileNotFoundException {
        int counter = 0;
        String value;
        double[] boardData = new double[11];
        double[] expectedData = new double[1];

        Scanner scanner = new Scanner(new File("res/train_data.csv"));
        scanner.useDelimiter(",");
        while (scanner.hasNext()) {
            value = scanner.next();
            boardData[counter] = Double.parseDouble(value);
            counter++;
            if (counter % 11 == 0) {
                value = scanner.next();
                expectedData[0] = Double.parseDouble(value);
                trainingSet.addRow(new DataSetRow(boardData, expectedData));
                counter = 0;

                    /*for(double d: boardData){
                        System.out.println(d);
                    }
                    for(double d: expectedData){
                        System.out.println(Util.ANSI_RED + d + Util.ANSI_WHITE);
                    }*/
            }
        }
        scanner.close();
        return trainingSet;
        //System.out.println(trainingSet.size());
    }
}