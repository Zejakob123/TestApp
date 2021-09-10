package testing123.zzz.testapp;

import android.widget.ImageView;

public class kit {
    private int id;
    private String kitName;
    private String kitType;
    private String ass_week;
    private int kit_pic;

    public kit(int id, String kitName, String kitType, String ass_week, int kit_pic) {
        this.id = id;
        this.kitName = kitName;
        this.kitType = kitType;
        this.ass_week = ass_week;
        this.kit_pic = kit_pic;
    }

    @Override
    public String toString() {
        return "kit{" +
                "id=" + id +
                ", kitName='" + kitName + '\'' +
                ", kitType='" + kitType + '\'' +
                ", ass_week='" + ass_week + '\'' +
                ", kit_pic=" + kit_pic +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKitName() {
        return kitName;
    }

    public void setKitName(String kitName) {
        this.kitName = kitName;
    }

    public String getKitType() {
        return kitType;
    }

    public void setKitType(String kitType) {
        this.kitType = kitType;
    }

    public String getAss_week() {
        return ass_week;
    }

    public void setAss_week(String ass_week) {
        this.ass_week = ass_week;
    }

    public int getKit_pic() {
        return kit_pic;
    }

    public void setKit_pic(int kit_pic) {
        this.kit_pic = kit_pic;
    }
}
