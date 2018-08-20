package golos.seminarski.hci.nekreatnine_hci.Helper.volley;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.Map;

import golos.seminarski.hci.nekreatnine_hci.Helper.MyGson;
import golos.seminarski.hci.nekreatnine_hci.Model.Korisnici;
import golos.seminarski.hci.nekreatnine_hci.MyApp;


public class MyVolley {
    public static <T> void get (String urlStr, Class<T> responseType, Response.Listener<T> listener, Response.ErrorListener errorListener, String param1, String param2) {
        String url = urlStr + "/" + param1 + "/" + param2;
       // String url  ="http://httpbin.org/get?param1=hello";

        GsonRequest<T> gsonRequest = new GsonRequest<>(url, responseType, null, null, listener, errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);


    }

    public static void getNekreatnine (String urlStr, JSONArray responseType, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener,  String param1, Boolean param2, Boolean param3, Boolean param4, Boolean param5, int param6)
    {
        String urlParam = "/";


        String url = urlStr + urlParam + param1 + urlParam + param2 + urlParam + param3 + urlParam + param4 + urlParam + param5 + urlParam + param6 + urlParam;

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, responseType, listener, errorListener );
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(jsonRequest);

    }



    public static <T> void get3 (String urlStr, JSONArray responseType, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener, int param1) {
        String url = urlStr + "/" + param1 + "/";
        // String url  ="http://httpbin.org/get?param1=hello";


        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, responseType, listener, errorListener );
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(jsonRequest);


    }

    public static <T> void get (String urlStr, Class<T> responseType, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        String url = urlStr;
        GsonRequest<T> gsonRequest = new GsonRequest<>(url, responseType, null, null, listener, errorListener, Request.Method.GET);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);


    }

    public static void get2 (String urlStr, JSONArray responseType, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener, Map<String, String>... inputParams)
    {
        String urlParam = "/";

        for (Map<String, String> map : inputParams) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                urlParam += entry.getValue() + "/";
            }
        }

        String url = urlStr + urlParam;

        JsonArrayRequest jsonRequest = new JsonArrayRequest(Request.Method.GET, url, responseType, listener, errorListener );
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(jsonRequest);

    }


    public static <T> void post (String urlStr, Class<T> responseType,final Response.Listener<T> listener, Response.ErrorListener errorListener,  Object postObject)
    {
        String jsonStr = MyGson.build().toJson(postObject);
        GsonRequest<T> gsonRequest = new GsonRequest<>(urlStr, responseType, null, jsonStr, listener, errorListener, Request.Method.POST);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static <T> void put(String urlStr, Class<T> responseType, final Response.Listener<T> listener, Response.ErrorListener errorListener, Object postObject)
    {
        String jsonStr = MyGson.build().toJson(postObject);
        GsonRequest<T> gsonRequest = new GsonRequest<>(urlStr,  responseType, null,  jsonStr, listener, errorListener, Request.Method.PUT);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }

    public static <T> void putKupac(String urlStr, Class<T> responseType,final Response.Listener<T> listener, Response.ErrorListener errorListener, Korisnici postObject)
    {
        String jsonStr = MyGson.build().toJson(postObject);
        GsonRequest<T> gsonRequest = new GsonRequest<>(urlStr, responseType, null, jsonStr, listener, errorListener, Request.Method.PUT);
        MySingleton.getInstance(MyApp.getContext()).addToRequestQueue(gsonRequest);
    }
}