/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1wansongsong;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author SONGSONG
 */
public class Censusdata {

    public static void highestgrowth() throws FileNotFoundException, IOException {
        BufferedReader population = null; //loading the topics file
        String[] top5 = new String[5]; //the array store the top five cities' information
        int max = 0;
        int min = 0;
        int mark = 0;
        int[] top = new int[5];  //the array store the top five cities' population  growth
        for (int i = 0; i < 5; i++) {
            top[i] = 0;
            top5[i] = "";
        }
        String[] tmp = null;
        String[] tmp1 = null;
        int[] populate = new int[3];
        for (int i = 0; i < 3; i++) {
            populate[i] = 0;
        }
        population = new BufferedReader(new InputStreamReader(new FileInputStream("75f647c2ac77-Metropolitan_Populations_2010-2012_.csv")));  //read the raw data line by line
        String line;
        line = population.readLine();
        while ((line = population.readLine()) != null) {
            tmp = line.split("\"");
            tmp1 = tmp[2].split(",");
            populate[0] = Integer.parseInt(tmp1[1]);
            populate[1] = Integer.parseInt(tmp1[2]);
            populate[2] = Integer.parseInt(tmp1[3]);
            if (populate[0] >= 50000 && populate[1] >= 50000 && populate[2] >= 50000) {
                max = (populate[2] * 10000) / populate[0] - 10000;
                min = top[0];
                mark = 0;
                for (int i = 0; i < 5; i++) {
                    if (min > top[i]) {
                        min = top[i];
                        mark = i;
                    }
                }
                if (min < max) {
                    top[mark] = max;
                    top5[mark] = line;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(top5[i] + "  " + top[i] / 100.0 + "%");
        }
    }

    public static void mostshrinking() throws FileNotFoundException, IOException {
        BufferedReader population = null; //loading the topics file
        String[] top5 = new String[5];  //the array store the top five shrinking cities' information
        int max = 0;
        int min = 0;
        int mark = 0;
        int[] top = new int[5];   //the array store the top five shrinking cities' population
        for (int i = 0; i < 5; i++) {
            top[i] = 0;
            top5[i] = "";
        }
        String[] tmp = null;
        String[] tmp1 = null;
        int[] populate = new int[3];
        for (int i = 0; i < 3; i++) {
            populate[i] = 0;
        }
        population = new BufferedReader(new InputStreamReader(new FileInputStream("75f647c2ac77-Metropolitan_Populations_2010-2012_.csv"))); //read the raw data line by line
        String line;
        line = population.readLine();
        while ((line = population.readLine()) != null) {
            tmp = line.split("\"");
            tmp1 = tmp[2].split(",");
            populate[0] = Integer.parseInt(tmp1[1]);
            populate[1] = Integer.parseInt(tmp1[2]);
            populate[2] = Integer.parseInt(tmp1[3]);
            if (populate[0] >= 50000 && populate[1] >= 50000 && populate[2] >= 50000) {
                max = 10000 * (populate[0] - populate[2]) / populate[0];
                min = top[0];
                mark = 0;
                for (int i = 0; i < 5; i++) {
                    if (min > top[i]) {
                        min = top[i];
                        mark = i;
                    }
                }
                if (min < max) {
                    top[mark] = max;
                    top5[mark] = line;
                }
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(top5[i] + "  -" + top[i] / 100.0 + "%");
        }
    }

    public static void highestcumulativegrowth() throws FileNotFoundException, IOException {
        BufferedReader population = null; //loading the topics file
        String[] top5 = new String[5]; //the array store the top five states' information
        double max = 0;
        double min = 0;
        int mark = 0;
        double[] top = new double[5]; //the array store the top five states' population growth
        for (int i = 0; i < 5; i++) {
            top[i] = 0;
            top5[i] = "";
        }
        String[] tmp = null;
        String[] tmp1 = null;
        String[] tmp2 = null;
        int[] populate = new int[3];
        for (int i = 0; i < 3; i++) {
            populate[i] = 0;
        }
        population = new BufferedReader(new InputStreamReader(new FileInputStream("75f647c2ac77-Metropolitan_Populations_2010-2012_.csv")));  //read the raw data line by line
        String line;
        String statepre = "";
        String state = "";
        line = population.readLine();
        line = population.readLine();
        //System.out.println(line); //debug
        tmp = line.split("\"");
        tmp1 = tmp[2].split(",");
        tmp2 = tmp[1].split(", ");
        statepre = tmp2[1];
        //System.out.println(statepre); //debug
        populate[0] = Integer.parseInt(tmp1[1]);
        populate[1] = Integer.parseInt(tmp1[2]);
        populate[2] = Integer.parseInt(tmp1[3]);
        //System.out.println(populate[0]+" "+populate[1]+" "+populate[2]); //debug
        while ((line = population.readLine()) != null) {
            tmp = line.split("\"");
            tmp1 = tmp[2].split(",");
            tmp2 = tmp[1].split(", ");
            state = tmp2[1];
            if (state.equals(statepre)) {
                populate[0] = populate[0] + Integer.parseInt(tmp1[1]);
                populate[1] = populate[1] + Integer.parseInt(tmp1[2]);
                populate[2] = populate[2] + Integer.parseInt(tmp1[3]);
            } else {
                if (populate[0] != 0) {
                    max = (populate[2] * 100) / populate[0] - 100;
                }
                min = top[0];
                mark = 0;
                for (int i = 0; i < 5; i++) {
                    if (min > top[i]) {
                        min = top[i];
                        mark = i;
                    }
                }
                if (min < max) {
                    top[mark] = max;
                    top5[mark] = statepre + "," + populate[0] + "," + populate[1] + "," + populate[2];
                }
                statepre = state;
                populate[0] = Integer.parseInt(tmp1[1]);
                populate[1] = Integer.parseInt(tmp1[2]);
                populate[2] = Integer.parseInt(tmp1[3]);
            }
        }
        if (populate[0] != 0) {
            max = (populate[2] * 100) / populate[0] - 100;
        }
        min = top[0];
        mark = 0;
        for (int i = 0; i < 5; i++) {
            if (min > top[i]) {
                min = top[i];
                mark = i;
            }
        }
        if (min < max) {
            top[mark] = max;
            top5[mark] = statepre + "," + populate[0] + "," + populate[1] + "," + populate[2];
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(top5[i] + "  " + top[i] + "%");
        }
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Top five cities to target based on highest population growth (% change) between 2010-2012. (50,000 population minimum): ");
        highestgrowth();
        System.out.println();
        System.out.println("Top five cities to avoid based on the most shrinking population (% change) between 2010-2012. (50,000 population minimum): ");
        mostshrinking();
        System.out.println();
        System.out.println("Top five states with highest cumulative growth (% change combined across all metropolitan areas) between 2010-2012.: ");
        highestcumulativegrowth();
    }
}
