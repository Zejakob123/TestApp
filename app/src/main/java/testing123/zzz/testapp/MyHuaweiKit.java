package testing123.zzz.testapp;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyHuaweiKit extends Application {

    private static List<kit> kitList = new ArrayList<kit>();
//    private static int nextId = 10;

    public MyHuaweiKit() {
        fillPresidentList();
    }

    private void fillPresidentList() {

        kit k1 = new kit(0, "HMS Location Kit", "HMS Kit", "Week 2", R.drawable.huawei_location);
        kit k2 = new kit(1, "HMS Map Kit", "HMS Kit", "Week 2", R.drawable.huawei_map);
        kit k3 = new kit(2, "HMS Site Kit", "HMS Kit", "Week 2", R.drawable.huawei_site);

        kitList.addAll(Arrays.asList(new kit[]{k1, k2, k3}));

    }

    public static List<kit> getKitList() {
        return kitList;
    }

    public static void setPresidentList(List<kit> kitList) {
        MyHuaweiKit.kitList = kitList;
    }

    /*public static int getNextId() {
        return nextId;
    }*/

    /*public static void setNextId(int nextId) {
        MyHuaweiKit.nextId = nextId;
    }*/
}
