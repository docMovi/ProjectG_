package tile;
import main.LevelHandler;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ColorPicker{
    File textFile = new File("res/level/data/colorData.txt");
    Scanner reader;
    String data;
    String[] tmp;
    String[] values = new String[50];
    int t = 0;
    public ColorPicker(LevelHandler lh) {
        try {
            reader = new Scanner(textFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while (reader.hasNextLine()) {
            data = reader.nextLine();
            tmp = data.split(",");
            for(int i = 0; i < tmp.length; i++) {
                values[i + t] = tmp[i];
            }
            t += tmp.length;
            //values[t] = tmp[1];if(tmp[2]!= null) {values[t] = tmp[2];}if(tmp[3]!= null) {values[t] = tmp[3];}if(tmp[4]!= null) {values[t] = tmp[4];}if(tmp[5]!= null) {values[t] = tmp[5];}
        }
        reader.close();

        for(int i = 0; i < values.length; i++) {
            if(values[i] != null) {
                if(values[i].startsWith("#")){
                    if(!values[i + 1].startsWith("#")){
                        lh.newColor(values[i], Integer.valueOf(values[i + 1]));
                    }
                     {
                        System.out.println("Error CpFORI01+02");
                    }

                }
            }
        }


    }
}



