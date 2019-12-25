package com.wantupai.advertisements.main.model;

/**
 * Created by xuedakun on 2019-12-24 17:27
 *
 * @version : v1.0
 * @project : 广告制作
 * @Email : dakun611@Gmail.com
 */
public class ShareVideoModel {

    private String coverUrl;

    private String videoUrl;

    private String title;

    private String shareSurplusTimesHint;

    private String commission;

    public ShareVideoModel(String coverUrl, String videoUrl, String title, String shareSurplusTimesHint, String commission) {
        this.coverUrl = coverUrl;
        this.videoUrl = videoUrl;
        this.title = title;
        this.shareSurplusTimesHint = shareSurplusTimesHint;
        this.commission = commission;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getShareSurplusTimesHint() {
        return shareSurplusTimesHint;
    }

    public String getCommission() {
        return commission;
    }
}
