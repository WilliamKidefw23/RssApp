package com.rss.william.app.rssapp.Model

/** public string status { get; set; }
public Feed feed { get; set; }
public List<Item> items { get; set; }**/

data class RSSObject(val status:String, val feed:Feed, val items:List<Item>)