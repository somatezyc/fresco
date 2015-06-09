package com.facebook.samples.comparison;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.GridView;

import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.samples.comparison.adapters.LocalImgsAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyanchao on 2015/6/9.
 */
public class Main2Activity extends Activity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Fresco.initialize(this);
        FLog.setMinimumLoggingLevel(FLog.VERBOSE);

        this.gridView = (GridView) findViewById(R.id.imgs);
        List<String> imgs = initData();
        Log.d("aaaa", "imgs" + imgs);
        if (imgs != null && imgs.size() > 0) {
            LocalImgsAdapter adapter = new LocalImgsAdapter(this, imgs);
            gridView.setAdapter(adapter);
        }


    }

    private List<String> initData(){
        String cameraPath =  Environment.getExternalStorageDirectory().getAbsolutePath()+"/DCIM/Camera/";
        File cameraFolder = new File(cameraPath);

        Log.d("aaaa", "cameraFolder=" + cameraFolder.getAbsolutePath());

        if (!cameraFolder.exists()) {
            return null;
        }
        List<String> imgsPath = new ArrayList<>();

        String[] path = cameraFolder.list();
        if (path != null && path.length > 0) {
            for (int i = 0; i < path.length; i++) {
                if (!TextUtils.isEmpty(path[i]) && (path[i].toLowerCase().endsWith(".png") || path[i].toLowerCase().endsWith(".jpg"))) {
                    imgsPath.add(cameraPath+path[i]);
                }
            }
        }
        return  imgsPath;
    }
}
