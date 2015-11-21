package comstoresearchqmobotech.google.httpsplay.volleyframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import comstoresearchqmobotech.google.httpsplay.volleyframework.my_volley.MySingleton;

public class MainActivity extends AppCompatActivity {

    private String url = "http://i.imgur.com/7spzG.png";
    private final String TAG = "MY_TAG";
    private ImageLoader imageLoader;
    private NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        networkImageView = (NetworkImageView) findViewById(R.id.networkImageView);

        imageLoader = MySingleton.getInstance(this.getApplicationContext()).getImageLoader();
        networkImageView.setImageUrl(url , imageLoader);

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
