package au.com.roadhouse.licensehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import au.com.roadhouse.licensehelper.library.LicenseHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.textViewMain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LicenseHelper licenseHelper = new LicenseHelper(MainActivity.this);
                licenseHelper.displayLicenses();
            }
        });
    }
}
