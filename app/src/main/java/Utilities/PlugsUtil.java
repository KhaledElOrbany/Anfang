package Utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import Globals.Globals;

public class PlugsUtil {
    String url = Globals.BaseUrl;

    public void setPlugName(Context context, int type, int plugId, String name) {
        url += type + "/" + plugId;

        try {
            final JSONArray jsonBody = new JSONArray(
                    "[{\"op\":\"replace\", \"path\":\"NodeName\",\"value\":\"" + name + "\"}]"
            );
            Volley.newRequestQueue(context).add(new JsonArrayRequest(Request.Method.PATCH, url,
                            jsonBody, response -> {
                    }, error -> Log.e("Name Error!!", String.valueOf(error)))
            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setPlugsState(Context context, int type, int plugId, boolean state) {
        url += type + "/" + plugId;

        try {
            final JSONArray jsonBody = new JSONArray(
                    "[{\"op\":\"replace\", \"path\":\"NodeState\",\"value\":" + state + "}]"
            );
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.PATCH, url,
                    jsonBody, response -> {
            }, error -> Log.e("State Error!!", String.valueOf(error)));
            Volley.newRequestQueue(context).add(jsonArrayRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
