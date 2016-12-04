package com.adsvantage.activepoints.helper;

/**
 * Created by Aris on 11/28/2016.
 */

public class RowItem {

    private int badges_id;
    private String badgeName;
    private String badgeInfo;

    public RowItem(String badgeName, String badgeInfo, int badgesId)
    {
        this.badges_id = badgesId;
        this.badgeName = badgeName;
        this.badgeInfo = badgeInfo;
    }

    public String getBadgeName() { return badgeName;}

    public void setBadgeName(String badge_name) {
        this.badgeName = badge_name;
    }

    public String getBadgeInfo() { return badgeInfo;}

    public void setBadgeInfo(String badge_info) {
        this.badgeInfo = badge_info;
    }

    public int getBadges_id() { return badges_id;}

    public void setBadges_id(int badge_id) {
        this.badges_id = badge_id;
    }
}
