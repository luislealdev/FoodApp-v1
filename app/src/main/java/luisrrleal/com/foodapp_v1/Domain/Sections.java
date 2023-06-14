package luisrrleal.com.foodapp_v1.Domain;

import android.widget.ImageView;

public class Sections {

    int sectionIcon;
    String sectionName;

    public Sections(int sectionIcon, String sectionName) {
        this.sectionIcon = sectionIcon;
        this.sectionName = sectionName;
    }

    public int getSectionIcon() {
        return sectionIcon;
    }

    public void setSectionIcon(int sectionIcon) {
        this.sectionIcon = sectionIcon;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}
