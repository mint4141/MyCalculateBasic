package pattaraporn.kerrykfm.com.mycalculatebasic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // savedInstanceState save ค่าเริ่มต้น
        setContentView(R.layout.activity_main);

//        Add Fragment to Activity
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainFragment,new MainFragment()).commit();
        }
    }   //Main Method

}   // Main Class
