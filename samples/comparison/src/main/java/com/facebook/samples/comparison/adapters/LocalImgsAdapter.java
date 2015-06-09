package com.facebook.samples.comparison.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.samples.comparison.R;

import java.util.List;

/**
 * 本地图片列表
 */
public class LocalImgsAdapter extends BaseAdapter {
    private LayoutInflater inflater;

    private List<String> imgs;
    private int imgSize;

    public LocalImgsAdapter(Context context, List<String> imgs) {
        this.imgs = imgs;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return imgs == null ? 0 : imgs.size();
    }

    @Override
    public String getItem(int position) {//position = 0固定拍照位置
        if (position >= 0 && imgs != null && imgs.size() > position) {
            return imgs.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_img, parent, false);
        }
            if (imgSize <= 0) {
                Point p = new Point();
                ((Activity)parent.getContext()).getWindowManager().getDefaultDisplay().getSize(p);
                imgSize = p.x / 3;
            }

        String item = getItem(position);
        if (item != null && !TextUtils.isEmpty(item)) {
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse("file://" + item))
                    .setResizeOptions(new ResizeOptions(imgSize,imgSize))
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(((SimpleDraweeView) convertView).getController())
                    .setImageRequest(request)
                    .build();
            ((SimpleDraweeView) convertView).setController(controller);
        } else {
            ((SimpleDraweeView) convertView).setImageURI(null);
        }
        return convertView;
    }
}
