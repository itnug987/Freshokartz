package com.freshokartz.connection.callbacks;

import com.freshokartz.model.NewsInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CallbackFeaturedNews implements Serializable {

    public String status = "";
    public List<NewsInfo> news_infos = new ArrayList<>();

}
