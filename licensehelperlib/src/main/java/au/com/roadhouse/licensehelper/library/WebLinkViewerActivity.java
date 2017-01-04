package au.com.roadhouse.licensehelper.library;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class WebLinkViewerActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "au.com.roadhouse.licensehelper.library.WebLinkViewerActivity.EXTRA_URL";
    public static final String EXTRA_TITLE = "au.com.roadhouse.licensehelper.library.WebLinkViewerActivity.EXTRA_TITLE";
    public static final String EXTRA_LINK = "au.com.roadhouse.licensehelper.library.WebLinkViewerActivity.EXTRA_LINK";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().hasExtra(LicenseListActivity.EXTRA_THEME_ID)){
            setTheme(getIntent().getIntExtra(LicenseListActivity.EXTRA_THEME_ID, R.style.LicenseHelper_Internal_AppTheme));
        }
        setContentView(R.layout.internal_web_view);
        setSupportActionBar((Toolbar) findViewById(R.id.internalToolbar));

        WebView webView = (WebView) findViewById(R.id.internalWebViewLicense);
        webView.loadUrl(getIntent().getStringExtra(EXTRA_URL));

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra(EXTRA_TITLE));
        }
    }

    public static Intent buildIntent(Context context, String url, String name, String link){
        Intent intent = new Intent(context, WebLinkViewerActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, name);
        intent.putExtra(EXTRA_LINK, link);
        return intent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.internal_web_link_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        } else if (item.getItemId() == R.id.internalMenuItemOpenLink){
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(getIntent().getStringExtra(EXTRA_LINK)));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
