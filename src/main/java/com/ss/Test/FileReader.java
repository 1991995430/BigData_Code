package com.ss.Test;

import java.io.*;
import java.util.*;

public class FileReader {

    public static List<String> getDataList(String path) {

        List<String> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {

            String line = null;

            while ((line = br.readLine()) != null) {
                data.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}
