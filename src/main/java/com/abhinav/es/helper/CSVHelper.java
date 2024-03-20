package com.abhinav.es.helper;

import com.abhinav.es.entity.MatchModel;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "ID", "City", "Date", "Season", "Team1", "Team2", "Venue", "TossWinner",
            "TossDecision", "SuperOver", "WinningTeam", "WonBy", "Margin", "Player_Of_Match", "Umpire1", "Umpire2" };


    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<MatchModel> csvToMatches(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<MatchModel> matchModels = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                MatchModel matchModel = new MatchModel(
                        csvRecord.get("ID"),
                        csvRecord.get("City"),
                        csvRecord.get("Date"),
                        csvRecord.get("Season"),
                        csvRecord.get("Team1"),
                        csvRecord.get("Team2"),
                        csvRecord.get("Venue"),
                        csvRecord.get("TossWinner"),
                        csvRecord.get("TossDecision"),
                        csvRecord.get("SuperOver"),
                        csvRecord.get("WinningTeam"),
                        csvRecord.get("WonBy"),
                        csvRecord.get("Margin"),
                        csvRecord.get("Player_of_Match"),
                        csvRecord.get("Umpire1"),
                        csvRecord.get("Umpire2")
                );

                matchModels.add(matchModel);
            }

            return matchModels;
        } catch (IOException e) {
            throw new RuntimeException("Fail to parse CSV file: " + e.getMessage(), e);
        }
    }
}

