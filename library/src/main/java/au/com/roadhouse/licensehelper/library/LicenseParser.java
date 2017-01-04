package au.com.roadhouse.licensehelper.library;

import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LicenseParser {

    private final AssetManager mAssetManager;

    public LicenseParser(AssetManager assetManager){
        mAssetManager = assetManager;
    }

    public List<License> getLicenses(){
        List<License> licenseList = new ArrayList<>();
        try {
            String[] files = mAssetManager.list("license");
            for (int i = 0; i < files.length; i++) {
                InputStream inputStream = mAssetManager.open("license" + "/" + files[i]);
                licenseList.add(buildLicense(inputStream));
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return licenseList;
    }

    private License buildLicense(InputStream inputStream) throws JSONException {

        try {
            JSONObject jsonObject = new JSONObject(convertStreamToString(inputStream));
            inputStream.close();
            return new License(jsonObject.getString("name"), jsonObject.getString("link"), jsonObject.getString("license_link"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static class License {
        private final String mName;
        private final String mLink;
        private final String mLicenseLink;

        License(String name, String link, String licenseLink) {
            mName = name;
            mLink = link;
            mLicenseLink = licenseLink;
        }

        public String getName() {
            return mName;
        }

        public String getLink() {
            return mLink;
        }

        public String getLicenseLink() {
            return mLicenseLink;
        }
    }
}
