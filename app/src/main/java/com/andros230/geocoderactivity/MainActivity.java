package com.andros230.geocoderactivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

public class MainActivity extends Activity implements AMapLocationListener {
    private AMapLocationClient locationClient;
    private AMapLocationClientOption locationClientOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationClient = new AMapLocationClient(this);
        locationClientOption = new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClient.setLocationListener(this);
        locationClientOption.setOnceLocation(true);
        locationClient.setLocationOption(locationClientOption);
        locationClient.startLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            Log.d("---", "经度："+aMapLocation.getLongitude());
            Log.d("---", "纬度："+aMapLocation.getLatitude());
            Log.d("---", "精度："+aMapLocation.getAccuracy());
            Log.d("---", "省份："+aMapLocation.getProvince());
            Log.d("---", "城市："+aMapLocation.getCity());
            Log.d("---", "地区："+aMapLocation.getDistrict());
            Log.d("---", "地址："+aMapLocation.getAddress());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationClient != null) {
            locationClient.onDestroy();
            locationClient = null;
            locationClientOption = null;
        }
    }
}
