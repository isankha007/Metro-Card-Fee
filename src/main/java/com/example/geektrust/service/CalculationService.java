package com.example.geektrust.service;

import com.example.geektrust.constants.Charges;
import com.example.geektrust.entity.MetroCard;
import com.example.geektrust.entity.Station;

import java.util.*;

public class CalculationService {
    private static final Map<String, MetroCard> balanceMap=new HashMap<>();
    private  Map<String, Station> stationMap=new HashMap<>();
    private static final HashSet<String> destinationSet=new HashSet<>();

    public Map<String, Station> getStationMap() {
        return stationMap;
    }

    public void setStationMap(Map<String, Station> stationMap) {
        this.stationMap = stationMap;
    }

    public void parseAndProcess(String commandString){
        String[] commands=commandString.split(" ");
        if(commands[0].equals("BALANCE")){

            addBalance(commands[1],Integer.parseInt(commands[2]));

        }
        else if (commands[0].equals("CHECK_IN"))
        {

            calculateFees(commands[2],commands[1],commands[3]);

        }else {
            List<String> stationList=new ArrayList<>(Arrays.asList("CENTRAL","AIRPORT"));
            for (String s:stationList){
                System.out.println(printSumurry(stationMap,s));
                //printSumurry(stationMap,s);

            }
        }

    }

    public void addBalance(String name,int amount){
        MetroCard metroCard=new MetroCard(name,amount);
        balanceMap.put(name,metroCard);
    }

    public int getBalanceForCard(String name){
        return balanceMap.get(name).getBlanceRem();
    }
    public void calculateFees(String passengerType,String cardId,String destination){
        int actualFee=0;
        int discount=0;
        if(passengerType.equals("SENIOR_CITIZEN")){
            actualFee = Charges.SENIOR_CITIZEN.getValue();
        } else if (passengerType.equals("ADULT")) {
            actualFee =Charges.ADULT.getValue();
        }else{
            actualFee =Charges.KID.getValue();
        }
        //calculate discount if return with card
        if(destinationSet.contains(cardId)){
            discount=actualFee/2;
            actualFee=discount;
            destinationSet.remove(cardId);//removing destination
        }else{
            destinationSet.add(cardId);
        }
        //check if balance available
        int remBalance =  getBalanceForCard(cardId)-actualFee;
        MetroCard metroCard = balanceMap.get(cardId);
        int amountCharged=actualFee;
        if(remBalance>0){
            metroCard.setBlanceRem(remBalance);
            //  balanceMap.put(commands[1],balanceMap.get(commands[1]).setBlanceRem(remBalance));

        }else{
            metroCard.setBlanceRem(0);
            // balanceMap.put(commands[1],0);
            amountCharged+=(int)(Math.abs(remBalance) * 0.02);
        }
        balanceMap.put(cardId,metroCard);
        //updating passenger type count
        Station stObj=null;
        if(stationMap.containsKey(destination)) {
            stObj = stationMap.get(destination);
        }else {
            stObj = new Station();
            stObj.setName(destination);
            stObj.setPassengerMap(new TreeMap<>());

        }
        SortedMap<String, Integer> passengerMap = stObj.getPassengerMap();

        passengerMap.put(passengerType,passengerMap.getOrDefault(passengerType,0)+1);

        stObj.setPassengerMap(passengerMap);

        //updating amount collected by stations
        stObj.setTotalAmount(stObj.getTotalAmount()+ amountCharged);
        stObj.setDiscountGiven(stObj.getDiscountGiven()+discount);
        stationMap.put(destination, stObj);
    }

    public String printSumurry(Map<String,Station> stationMap,String Key){
        StringBuilder sb=new StringBuilder();
        sb.append("TOTAL_COLLECTION "+ Key+ " "+stationMap.get(Key).getTotalAmount()+" "+stationMap.get(Key).getDiscountGiven()).append("\n");
        //.out.println("TOTAL_COLLECTION "+ Key+ " "+stationMap.get(Key).getTotalAmount()+" "+stationMap.get(Key).getDiscountGiven()) ;
       // System.out.println("PASSENGER_TYPE_SUMMARY");
       // sb.append(System.getProperty("line.separator"));
        sb.append("PASSENGER_TYPE_SUMMARY").append("\n");
        for(Map.Entry<String,Integer> pm:stationMap.get(Key).getPassengerMap().entrySet()){
          // System.out.println(pm.getKey()+" "+pm.getValue());
            sb.append(pm.getKey()).append(" ").append(pm.getValue()).append("\n");
           // sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

}
