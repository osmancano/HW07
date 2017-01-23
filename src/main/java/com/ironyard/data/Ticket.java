package com.ironyard.data;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by osmanidris on 1/18/17.
 */
public class Ticket {
    private final int MAXNUM = 53;
    private final int MINNUM = 1;
    private int noOfMatches = 0;
    private String status = "";
    private ArrayList<Integer> numbers = new ArrayList<Integer>();
    public Ticket(){
        int randomNum;
        for (int i=0;i < 6; i++){
            do {
                randomNum = randInt(MINNUM, MAXNUM);
            }while(this.numbers.contains(randomNum));
            this.numbers.add(randomNum);
        }
    }

    public Ticket(ArrayList<Integer> myNumbers) throws Exception {
        if(myNumbers.size()!=6){
            throw new Exception("Exactly 6 numbers should be sent to new Ticket");
        }
        numbers.addAll(myNumbers);
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isWinningTicket(Ticket ticket){
        for(int i =0 ; i < this.numbers.size(); i++){
            for(int j = 0; j < ticket.numbers.size(); j++){
                if(this.numbers.get(i) == ticket.numbers.get(j)){
                    this.noOfMatches = this.noOfMatches + 1;
                }
            }
        }
        if(this.noOfMatches > 0) {
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        String result = "";
        result = result +String.format("%02d",this.numbers.get(0));
       for(int i = 1; i < this.numbers.size(); i++){
           result = result +"-"+String.format("%02d",this.numbers.get(i));
       }
       return result;
    }
    private int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
