package allen.statedatalayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String url = "https://api.myjson.com/bins/16uaq";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_content);
        if (fragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.frame_content, new TestFragment(), "Test").commit();
        }
    }
}
