package com.rss.william.app.rssapp.Model

/** public string title { get; set; }
public string pubDate { get; set; }
public string link { get; set; }
public string guid { get; set; }
public string author { get; set; }
public string thumbnail { get; set; }
public string description { get; set; }
public string content { get; set; }
public Enclosure enclosure { get; set; }
public List<string> categories { get; set; }**/

data class Item(val title:String,val pubDate:String,val link:String,val guid:String,val author:String,val thumbail:String,val description:String,val content:String,val enclosure:Object,val categories:List<String>)
