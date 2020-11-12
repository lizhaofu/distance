package hust; /**
 * @Author: lizhaofu
 * @See:
 * @Description:
 * @Date: Created in 23:23 2018/12/21
 * @Modified:
 */


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class LngAndLatUtil {

    /**
     * 根据地址获得经纬度
     */

    public static List<String> getCoordinate(String address) {
        if (address != null && !"".equals(address)) {
            List<String> lo = new ArrayList<>();
            address = address.replaceAll("\\s*", "").replace("#", "栋");
            String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=bTCEROz8AzL90Eli7t7o1IK745reG7BA";
            String json = loadJSON(url);
            if (json != null && !"".equals(json)) {
                JSONObject obj = JSONObject.fromObject(json);
                if ("0".equals(obj.getString("status"))) {
                    double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng"); // 经度
                    double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat"); // 纬度
                    DecimalFormat df = new DecimalFormat("#.######");
                    lo.add(df.format(lng));
                    lo.add(df.format(lat));
                    return lo;
                }
            }
        }
        return null;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            URLConnection uc = urlObj.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ((inputLine = br.readLine()) != null) {
                json.append(inputLine);
            }
            br.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }




    /**
     * 补充：计算两点之间真实距离
     * @return 千米
     */
    public static double getDistance(List<String>list_1,  List<String>list_2) {

        double longitude1 = Double.valueOf(list_1.get(0));
        double longitude2 = Double.valueOf(list_2.get(0));
        double latitude1 = Double.valueOf(list_1.get(1));
        double latitude2 = Double.valueOf(list_2.get(1));


        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;

        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d ;
    }

    /**
     * 测试方法 说明：把代码中的ak值（红色字部分）更改为你自己的ak值，在百度地图API中注册一下就有。
     * 百度路径：http://lbsyun.baidu.com/index.php?title=webapi/guide/changeposition
     */
    public static void main(String[] args) {
        List<String> latAndLng1 = LngAndLatUtil.getCoordinate("中国人民大学");
        List<String> latAndLng2 = LngAndLatUtil.getCoordinate("清华大学");

        System.out.println("人民大学经度：" + latAndLng1.get(0) + "---纬度：" + latAndLng1.get(1));
        System.out.println("清华大学经度：" + latAndLng2.get(0) + "---纬度：" + latAndLng2.get(1));

        double dis = getDistance(latAndLng1,latAndLng2);
        System.out.println(dis);
    }

}


