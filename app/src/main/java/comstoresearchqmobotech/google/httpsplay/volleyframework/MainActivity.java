package comstoresearchqmobotech.google.httpsplay.volleyframework;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import comstoresearchqmobotech.google.httpsplay.volleyframework.my_volley.MySingleton;
import comstoresearchqmobotech.google.httpsplay.volleyframework.utils.MyUtils;

public class MainActivity extends AppCompatActivity {

    private String url = "http://i.imgur.com/7spzG.png";
    private ImageView imageView;
    private final String TAG = "MY_TAG";
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        myImageRequestUsingSingleton();

    }


    private void myImageRequestUsingSingleton(){

        if(MyUtils.isNetworkConnected(this)){

            requestQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

            final ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    imageView.setImageBitmap(response);
                }
            }, 800, 500, null, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.mipmap.ic_launcher);
                }
            });
            MySingleton.getInstance(this).addToRequestQueue(imageRequest);

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
