package hust;

import java.io.*;
import java.util.*;

/**
 * @Author: lizhaofu
 * @See:
 * @Description:
 * @Date: Created in 23:57 2019/1/4
 * @Modified:
 */
public class Distance {
    private Map<String, String> readLagandlat(String path) {
        Map<String, String> firmsMap = new HashMap<>();


        File file = new File(path);

        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].exists()) {
                System.out.println("file" + "\"" + files[i] + "\"" + "is not exist!");
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(files[i]), "UTF-8"));
                String lineText;
                while ((lineText = bufferedReader.readLine()) != null) {
                    if (!lineText.contains(",,")) {
                        String[] str = lineText.split(",");
                        if (str.length > 3) {
//                        System.out.println(str.length);
                            String strd = str[2] + "," + str[3];
                            firmsMap.put(str[0], strd);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return firmsMap;
    }

    private void  readWriteCSV( Map<String, String> firmsMap) {

        try {

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("geography_firm_20191228.csv"), "UTF-8"));

            List<String> firms = new ArrayList(firmsMap.keySet());
//            System.out.println(firms);
            for (int i = 0; i < firms.size() - 1; i++) {
                for (int j = i + 1; j < firms.size(); j++) {
                    List<String> startList = Arrays.asList(firmsMap.get(firms.get(i)).split(","));
                    List<String> targetList = Arrays.asList(firmsMap.get(firms.get(j)).split(","));
                    Double distance = LngAndLatUtil.getDistance(startList, targetList);

                    if (distance < 5){
                        String firmsDistance = firms.get(i) + "," + firms.get(j) + "," + distance;
                        bw.write(firmsDistance);
                        bw.newLine();
                    }

                }
            }


            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }







    public static void main(String[] args) {
        String lngAndLatPath = "firm_jw_20181228";
        String edgesPath = "20191008";
        Distance distance = new Distance();
        Map<String, String> firmsMap = distance.readLagandlat(lngAndLatPath);
        distance.readWriteCSV(firmsMap);

    }

}
