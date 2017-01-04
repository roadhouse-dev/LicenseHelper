package au.com.roadhouse.licensehelper.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class LicenseListActivity extends AppCompatActivity implements LicenseAdapter.OnLicenseSelectedListener {

    public static final String EXTRA_THEME_ID = "au.com.roadhouse.licensehelper.library.LicenseListActivity.EXTRA_THEME_ID";
    public static final String EXTRA_DO_FINISH_ON_UP = "au.com.roadhouse.licensehelper.library.LicenseListActivity.EXTRA_DO_FINISH_ON_UP";

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getIntent().hasExtra(EXTRA_THEME_ID)){
            setTheme(getIntent().getIntExtra(EXTRA_THEME_ID, R.style.LicenseHelper_Internal_AppTheme));
        }

        setContentView(R.layout.internal_license_list_view);
        setSupportActionBar((Toolbar) findViewById(R.id.internalToolbar));
        if(getSupportActionBar() != null && getIntent().getBooleanExtra(EXTRA_DO_FINISH_ON_UP, false)){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.internalRecyclerViewLicenseList);

        LicenseParser licenseParser = new LicenseParser(getAssets());
        LicenseAdapter adapter = new LicenseAdapter(this, licenseParser.getLicenses());
        adapter.setOnLicenseSelectedListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new VerticalDividerItemDecorator(this, R.dimen.divider_height, R.color.divider));
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLicenseSelected(LicenseParser.License license) {
        Intent intent = WebLinkViewerActivity.buildIntent(this, license.getLicenseLink(), license.getName(), license.getLink());
        if(getIntent().hasExtra(EXTRA_THEME_ID)){
            intent.putExtra(EXTRA_THEME_ID, getIntent().getIntExtra(EXTRA_THEME_ID, R.style.LicenseHelper_Internal_AppTheme));
        }

        startActivity(intent);
    }
}
