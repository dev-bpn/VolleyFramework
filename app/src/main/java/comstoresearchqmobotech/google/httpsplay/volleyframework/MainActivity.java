package comstoresearchqmobotech.google.httpsplay.volleyframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import comstoresearchqmobotech.google.httpsplay.volleyframework.my_volley.MySingleton;
import comstoresearchqmobotech.google.httpsplay.volleyframework.utils.MyUtils;

public class MainActivity extends AppCompatActivity {

    private String url = "https://www.google.com";
    private TextView textView;
    private RequestQueue requestQueue;
    private final String TAG = "MY_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        myStringRequestUsingSingleton();

    }

    private void myStringRequestUsingSingleton(){

        if(MyUtils.isNetworkConnected(this)){

            requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    textView.setText("Response: " + response.substring(0 , 200));
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    textView.setText("Error: "+ error.toString());
                }
            });
            stringRequest.setTag(TAG);
            MySingleton.getInstance(this).addToRequestQueue(stringRequest);

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(requestQueue != null){
            requestQueue.cancelAll(TAG);
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
