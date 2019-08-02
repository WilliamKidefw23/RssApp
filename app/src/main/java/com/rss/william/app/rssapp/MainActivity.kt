package com.rss.william.app.rssapp

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rss.william.app.rssapp.Adapter.FeedAdapter
import com.rss.william.app.rssapp.Common.HttpDataHandler
import com.rss.william.app.rssapp.Model.RSSObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val RRS_link = "https://depor.com/feed";
    private val RRS_to_JSON_API = " https://api.rss2json.com/v1/api.json?rss_url=";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var linearLayoutManager = LinearLayoutManager(baseContext,LinearLayoutManager.VERTICAL,false)
        rvLista.layoutManager = linearLayoutManager
        swRefresh.setOnRefreshListener {
            readRSS()
        }
    }

    private fun readRSS() { showLoading()
       val loadRSSAsync = object:AsyncTask<String,String,String>(){

           override fun onPostExecute(result: String?) {
               var rssObject:RSSObject
               rssObject = Gson().fromJson<RSSObject>(result,RSSObject::class.java!!)
               val adapter = FeedAdapter(rssObject,baseContext)
               rvLista.adapter = adapter
               adapter.notifyDataSetChanged()

           }

           override fun doInBackground(vararg params: String?): String {
               val result:String
               val http = HttpDataHandler()
               result = http.getHttpDataHandler(params[0])
               return result
           }

           override fun onPreExecute() {
           }
       }

        val url_get_data = StringBuilder(RRS_to_JSON_API)
        url_get_data.append(RRS_link)
        loadRSSAsync.execute(url_get_data.toString())
    }

    private fun showLoading(){
        swRefresh.post {
            Handler(Looper.getMainLooper()).postDelayed({
                swRefresh.isRefreshing = false
            },300)
        }
    }

    override fun onResume() {
        super.onResume()
        readRSS()
    }

}
