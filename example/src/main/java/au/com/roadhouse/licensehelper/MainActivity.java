package au.com.roadhouse.licensehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import au.com.roadhouse.licensehelper.library.LicenseHelper;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LicenseHelper licenseHelper = new LicenseHelper(MainActivity.this);
        licenseHelper.displayLicenses();

        return true;
    }
}
