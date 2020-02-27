package com.example.coccocapp.asysTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.coccocapp.models.New;
import com.example.coccocapp.util.AsyncResponse;
import com.example.coccocapp.util.XMLDOMParser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ReadRss extends AsyncTask<String, Void, String> {

    private Context myContext;
    private ArrayList<New> news = new ArrayList<>();
    private AsyncResponse asyncResponse = null;

    public AsyncResponse getAsyncResponse() {
        return asyncResponse;
    }

    public void setAsyncResponse(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public ReadRss(Context context) {
        myContext = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content_builder = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                content_builder.append(line);
            }

            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content_builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        XMLDOMParser xmldomParser = new XMLDOMParser();
        Document document = xmldomParser.getDocument(s);
        NodeList nodeList = document.getElementsByTagName("item");
        for (int i = 0; i < nodeList.getLength(); i++) {
            New aNew = new New();
            Element element = (Element) nodeList.item(i);
            aNew.setTitle(xmldomParser.getValue(element, "title"));
            aNew.setDescription(xmldomParser.getValue(element, "description"));
            aNew.setPubDate(xmldomParser.getValue(element, "pubDate"));
            aNew.setLink(xmldomParser.getValue(element, "link"));
            aNew.setGuid(xmldomParser.getValue(element, "guid"));
            aNew.setComments(xmldomParser.getValue(element, "comments"));
            news.add(aNew);
        }
        asyncResponse.processFinish(news);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public ArrayList<New> getNew(){
        if (news.size() > 0){
            return news;
        }
        return null;
    }
}
