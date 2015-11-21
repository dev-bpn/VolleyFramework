package comstoresearchqmobotech.google.httpsplay.volleyframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import org.json.JSONObject;
import comstoresearchqmobotech.google.httpsplay.volleyframework.my_volley.MySingleton;
import comstoresearchqmobotech.google.httpsplay.volleyframework.utils.MyUtils;

public class MainActivity extends AppCompatActivity {

    private final String URL = "http://api.androidhive.info/volley/person_object.json";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);

        myJsonObjectRequest();

    }

    private void myJsonObjectRequest() {

        if(MyUtils.isNetworkConnected(this)){

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            textView.setText("Response: "+ response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("Error: "+ error.toString());
                }
            });

            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
