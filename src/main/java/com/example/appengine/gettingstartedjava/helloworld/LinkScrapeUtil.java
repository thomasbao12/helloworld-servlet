/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.appengine.gettingstartedjava.helloworld;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 *
 * @author thomasbao
 */
public class LinkScrapeUtil {
    public static JSONObject getLinkShare(String urlStr) throws Exception{
        Document doc = getDoc(urlStr);
        String title = getTitle(doc);
        String imageUrl = getImageUrl(doc);
        String description = getDescription(doc);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("imageUrl", imageUrl);
        jsonObject.put("description", description);
        return jsonObject;
    }
    
    public static Document getDoc(String urlStr) throws Exception {
        //con.userAgent(BROWSER_USER_AGENT);
        return Jsoup.connect(urlStr).get();
    }
    
    public static String getTitle(Document doc) {
        Elements metaOgTitle = doc.select("meta[property=og:title]");
        if (metaOgTitle!=null) {
            return metaOgTitle.attr("content");
        }
        else {
            return doc.title();
        }
    }
    
    public static String getImageUrl(Document doc) {
        Elements metaOgImage = doc.select("meta[property=og:image]");
        if (metaOgImage!=null) {
            return metaOgImage.attr("content");
        } else {
            return "";
        }
    }
    
    public static String getDescription(Document doc) {
        Elements metaOgDescription = doc.select("meta[property=og:description]");
        if (metaOgDescription!=null) {
            return metaOgDescription.attr("content");
        } else {
            return "";
        }
    }
}

