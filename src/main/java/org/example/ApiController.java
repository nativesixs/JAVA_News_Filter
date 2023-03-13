package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiController {


    public ObservableList getData(int option,String API_KEY, String keywords,String country,String category) throws IOException, ClassNotFoundException {
        ObservableList results = FXCollections.observableArrayList();
        URL url = null;
        if(option==1) {
            url = new URL("https://newsapi.org/v2/top-headlines?" + keywords + country + category + "pageSize=100&apiKey=" + API_KEY);
        } else if (option==2) {
            url = new URL("https://newsapi.org/v2/everything?" + keywords + country + category + "pageSize=100&apiKey=" + API_KEY);
        }
        System.out.println(url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();

        int responseCode = con.getResponseCode();
        System.out.println(responseCode+" response code");
        if (responseCode == 200) {
            InputStream stream = url.openStream();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(stream);
            JsonNode articles=jsonNode.get("articles");
            articles.forEach(a->{
                    results.add(a.get("title")+"\n");
                    results.add(a.get("url")+"\n");
            });
        } else {
            results.add("Nothing was found..");
            return results;
        }
        return results;
    }








}
