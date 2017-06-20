package Carousel;


import java.util.List;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

public class PicChangeAdapter extends PagerAdapter {
    private Context con;//上下文
    private List<ImageView> lis;//数据容器
    private ImageLoader il;//加载图片对象
    public PicChangeAdapter(Context con, List<ImageView> lis,ImageLoader il) {
        super();
        this.con = con;
        this.lis = lis;
        this.il = il;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lis.size();
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0==arg1;
    }
    /**
     * 滑动后调用
     */
    @Override
    public void destroyItem(View container, int position, Object object) {
        // TODO Auto-generated method stub
        //获取当前图片
        View view = lis.get(position);
        //移除当前图片
        ((ViewPager)container).removeView(view);
        Log.i("destroyItem", "destroyItem");
    }
    /**
     * 滑动前调用
     */
    @Override
    public Object instantiateItem(View container, int position) {
        // TODO Auto-generated method stub
        Log.i("instantiateItem", "instantiateItem");
        //获取当前图片
        ImageView iv = lis.get(position);
        //通过图片资源路径加载图片
        il.displayImage(iv.getTag()+"", iv);
        //将图片放到pageview中
        ((ViewPager)container).addView(iv);
        return lis.get(position);
    }
}
