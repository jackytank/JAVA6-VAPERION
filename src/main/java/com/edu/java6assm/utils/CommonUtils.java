package com.edu.java6assm.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CommonUtils {
    @Value("${apilayer.apikey}")
    private static String apikey;
    private static double result = 0.0;

    public static String getSiteURL(HttpServletRequest req) {
        String siteURL = req.getRequestURL().toString();
        return siteURL.replace(req.getServletPath(), "");
    }

    public static double convertCurrency(String from, String to, Double amount) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url("https://api.apilayer.com/exchangerates_data/convert?to="
                        + to.toUpperCase() + "&from="
                        + from.toUpperCase() + "&amount="
                        + String.format("%.0f", amount))
                .addHeader("apikey", "6h7fgfiZtBDuie6LxtyKp5cqVxwjaKnV")
                .build();
        // sync request
        Response response = client.newCall(request).execute(); 

        JsonNode rootNode = mapper.readTree(response.body().string());
        System.out.println(rootNode.toString());
        result = rootNode.get("result").asDouble();
        System.out.println("Gia tien la: " + rootNode.get("result"));
        return result;
    }
}
