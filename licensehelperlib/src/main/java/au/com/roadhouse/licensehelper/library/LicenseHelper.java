package au.com.roadhouse.licensehelper.library;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;

public class LicenseHelper {

    private final Activity mActivity;
    private final Fragment mFragment;
    private int mThemeId = R.style.LicenseHelper_Internal_AppTheme;
    private boolean mDoFinishOnUpNav;

    public LicenseHelper(Activity activity){
        mActivity = activity;
        mFragment = null;
    }

    public LicenseHelper(Fragment fragment){
        mActivity = null;
        mFragment = fragment;
    }

    public void setTheme(@StyleRes int themeId){
        mThemeId = themeId;
    }

    public void doFinishAppOnUpNavigation(boolean doFinishOnUpNav){
        mDoFinishOnUpNav = doFinishOnUpNav;
    }

    public void displayLicenses(){
        if(mActivity != null){
            Intent intent = new Intent(mActivity, LicenseListActivity.class);
            intent.putExtra(LicenseListActivity.EXTRA_THEME_ID, mThemeId);
            intent.putExtra(LicenseListActivity.EXTRA_DO_FINISH_ON_UP, mDoFinishOnUpNav);
            mActivity.startActivity(intent);
        } else if (mFragment != null){
            Intent intent = new Intent(mFragment.getContext(), LicenseListActivity.class);
            intent.putExtra(LicenseListActivity.EXTRA_THEME_ID, mThemeId);
            intent.putExtra(LicenseListActivity.EXTRA_DO_FINISH_ON_UP, mDoFinishOnUpNav);
            mFragment.startActivity(intent);
        }
    }
}
