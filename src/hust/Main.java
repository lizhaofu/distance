package hust;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhaofu
 * @See:
 * @Description:
 * @Date: Created in 23:57 2019/1/4
 * @Modified:
 */
public class Main {
    private List<Firm> readPapers(String path) {

        List<Firm> firmsList = new ArrayList<>();
        File file = new File(path);

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].exists()) {
                System.out.println("file" + "\"" + files[i] + "\"" + "is not exist!");
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF-8"));
                String lineText;
                Firm firm = new Firm();

                while ((lineText = bufferedReader.readLine()) != null) {
                    if (!lineText.contains(",,")) {
                        String[] str = lineText.split(",");
                        String s = str[0];

                        if (str[0] != null && str[0].length() > 3) {
                            firm = new Firm();
                            firm.setName(str[0]);
                            firm.setAdress(str[0]);
                            firm.setLagandlat(str[0]);
                            if (firm.getLagandlat() != null) {
                                firmsList.add(firm);
                                System.out.println(firmsList.size());
                            }

                        }
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        System.out.println(firmsList.size());

        return firmsList;
    }

    private List<Firm> readCommunity(String path) {

        List<Firm> firmsList = new ArrayList<>();
        File file = new File(path);

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].exists()) {
                System.out.println("file" + "\"" + files[i] + "\"" + "is not exist!");
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF-8"));
                String lineText;
                Firm firm = new Firm();

                while ((lineText = bufferedReader.readLine()) != null) {
                    if (!lineText.contains(",,")) {
                        String[] str = lineText.split(";");
                        String[] temp = str[1].split(",");
                        String s = str[0];
                        for (int j = 0; j < temp.length; j++) {
                            if (temp[j] != null && temp[j].length() > 3) {
                                firm = new Firm();
                                firm.setName(temp[j]);
                                firm.setAdress(temp[j]);
                                firm.setLagandlat(temp[j]);
                                if (firm.getLagandlat() != null) {
                                    firmsList.add(firm);
                                }

                            }

                        }


                    }


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return firmsList;
    }

    private void  readWriteCSV(String path) {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("firms_address/only_firm_address.csv"), "UTF-8"));
            String lineText;
            while ((lineText = bufferedReader.readLine()) != null) {
                String[] str = lineText.split(";");

                bw.write(str[1]);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void writerList(List<String> list, String output){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
            for (String str : list){
                bw.write(str);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }


    }

    public void writerNingboFirm(List<Firm> firmsList, String output){
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
            for (int i = 0; i < firmsList.size(); i++) {

                double lagDouble = Double.parseDouble(firmsList.get(i).getLagandlat().get(0));
                double latDouble = Double.parseDouble(firmsList.get(i).getLagandlat().get(1));
                if (lagDouble > 120.91 && lagDouble < 122.27 && latDouble > 28.85 && latDouble < 30.55){
                    String name = firmsList.get(i).getName();
                    String adress = firmsList.get(i).getAdress();
                    String writString = name + "," + adress + "," + lagDouble + "," + latDouble;
//                    String writString = lagDouble + "," + latDouble;

                    bw.write(writString);
                    bw.newLine();
                }


            }

            bw.close();
        } catch (IOException e) {
            //
            e.printStackTrace();
        }




    }



    public static void main(String[] args) {
        String path = "20191230_50";
        List<String> distanceList = new ArrayList<>();
//        List<Firm> firmList = new Main().readPapers(path);
        List<Firm> firmList = new Main().readPapers(path);
        System.out.println(firmList.size());
//        new Main().writerNingboFirm(firmList,"ningbo_business_geography_20191228.csv");

//        for (int i = 0; i < ; i++) {
//
//        }
        for (int i = 0; i < firmList.size() - 1; i++) {

            for (int j = i + 1; j < firmList.size(); j++) {
                String  distan = "";
                double distance = LngAndLatUtil.getDistance(firmList.get(i).getLagandlat(),
                        firmList.get(j).getLagandlat());
                if (distance < 5){
                    distan = firmList.get(i).getName() + "," +firmList.get(j).getName() + "," + distance;
                    distanceList.add(distan);
                }




            }
//
//
//
        }
        new Main().writerList(distanceList,"geography_1643_70_20191230.csv");
//        new Main().writerNingboFirm(firmList, "firms_address/firm_address.csv");
//        new Main().readWriteCSV("firms_address/firm_address.csv");



    }

}
