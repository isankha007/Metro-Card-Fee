package com.example.geektrust;

import com.example.geektrust.entity.Station;
import com.example.geektrust.service.CalculationService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main{

    public static void main(String[] args) {

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            CalculationService calculationService=new CalculationService();
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String str=sc.nextLine();
                calculationService.parseAndProcess(str);
            }
            sc.close(); // closes the scanner
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
