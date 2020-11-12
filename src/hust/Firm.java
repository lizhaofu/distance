package hust;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: lizhaofu
 * @See:
 * @Description:
 * @Date: Created in 23:38 2019/1/4
 * @Modified:
 */
public class Firm {
    private String name;
    private String adress;
    private List<String> lagandlat;
    public Firm(){
        name = "";
        adress = "";
        lagandlat = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setLagandlat(String name) {

        this.lagandlat = LngAndLatUtil.getCoordinate(name);
    }

    public List<String> getLagandlat() {
        return lagandlat;
    }
}
