package error404.gfg.healthcare;

import java.util.ArrayList;
import java.util.List;

public class Item {

    String title;
    String description;
    int image;
    String type;
    String webLink;
    String ytLink;



    String Do;
    String Donts;
    String effectedPart;
    String call;

    public Item(String title, String description, int image, String type, String webLink, String ytLink, String effectedPart, String call,String Do,String Donts) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.type = type;
        this.webLink = webLink;
        this.ytLink = ytLink;
        this.effectedPart = effectedPart;
        this.call = call;
        this.Do=Do;
        this.Donts=Donts;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    public String getYtLink() {
        return ytLink;
    }

    public void setYtLink(String ytLink) {
        this.ytLink = ytLink;
    }

    public String getEffectedPart() {
        return effectedPart;
    }

    public void setEffectedPart(String effectedPart) {
        this.effectedPart = effectedPart;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getDo() {
        return Do;
    }

    public void setDo(String aDo) {
        Do = aDo;
    }

    public String getDonts() {
        return Donts;
    }

    public void setDonts(String donts) {
        Donts = donts;
    }
}
