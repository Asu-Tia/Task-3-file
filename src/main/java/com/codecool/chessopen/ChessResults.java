package com.codecool.chessopen;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName){

        Map<String, Integer> chessCupData = new HashMap<>();

        try (Stream<String> data = Files.lines(Paths.get(fileName))) {
            data.map(line -> line.split(","))
                    .forEach(arr -> chessCupData.put(arr[0], sumPoints(arr))
                    );

        } catch (IOException e) {
            System.out.println("File not found!");
        }

        List<String> competitorsInOrder = new ArrayList<>(chessCupData.keySet());

        competitorsInOrder.sort(Comparator.comparing(chessCupData::get).reversed());

        return competitorsInOrder;
    }

    private int sumPoints(String[] data) {
        int sum = 0;
        for (int i = 1; i < data.length; i++) {
            sum += Integer.parseInt(data[i]);
        }
        return sum;
    }

}
