package golos.seminarski.hci.nekreatnine_hci;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import golos.seminarski.hci.nekreatnine_hci.Helper.Global;

public class ImageSlider extends PagerAdapter {

    Context mContext;

    ImageSlider(Context context) {
        this.mContext = context;
    }



    @Override
    public int getCount() {
        return Global.slike.size();
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        byte[] decodedBytes = Base64.decode(Global.slike.get(i).Slika, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

        mImageView.setImageBitmap(bitmap);

        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }

}
