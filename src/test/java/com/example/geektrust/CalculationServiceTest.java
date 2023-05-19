package com.example.geektrust;

import com.example.geektrust.constants.Charges;
import com.example.geektrust.service.CalculationService;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculationServiceTest {

    CalculationService calculationService;

    @BeforeEach
    void setCalculationService(){
        calculationService=new CalculationService();
    }

    @Test
    void addBalance(){
        calculationService.addBalance("MC1",200);
        Assert.assertEquals(calculationService.getBalanceForCard("MC1"),200);
    }

    @Test
    void calculateFee(){
        calculationService.addBalance("MC1",200);
        calculationService.calculateFees("KID","MC1","CENTRAL");
        Assert.assertEquals(calculationService.getBalanceForCard("MC1"),175);
    }

    @Test
    void printSummery(){
        calculationService.addBalance("MC1",200);
        calculationService.calculateFees("KID","MC1","CENTRAL");
        String printOutPut = calculationService.printSumurry(calculationService.getStationMap(), "CENTRAL");
        String expectedOutPut="TOTAL_COLLECTION CENTRAL 50 0\n" +
                "PASSENGER_TYPE_SUMMARY\n" +
                "KID 1\n";
        Assert.assertEquals(printOutPut,expectedOutPut);
    }

    @Test
    void checkFees(){
        int adultFee = Charges.ADULT.getValue();
        Assert.assertEquals(adultFee,200);

    }

}
