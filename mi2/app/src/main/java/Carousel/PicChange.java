package Carousel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.example.em.mi.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class PicChange extends FrameLayout {

    ImageLoader il = ImageLoader.getInstance();
    //定义轮播图片的数量
    private final static int Image_count = 5;
    //定义图片自动播放的时间间隔
    private final static int TIME_INTERVAL = 10;
    //自动轮播是否开启
    private static boolean isAutoPlay = true;
    //定义图片资源的路径字符串数组
    private String[] imageUrls;
    //定义保存ImageView的list表
    private List<ImageView> ivList;
    //定义保存进度提示的小圆点的list表
    private List<View> vList;
    //定义ViewPager适配器
    private ViewPager vp;
    //当前播放页面的id
    private int currentid = 0;
    //定时任务
    private Context con;
    //定义传递消息的句柄handler
    private ScheduledExecutorService ses;
    //定义上下文
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            vp.setCurrentItem(currentid);

        };
    };

    public PicChange(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.con = context;
        initData();
        initImageLoader(context);
        if(isAutoPlay){
            startPlay();
        }
    }
    /**
     *初始化相关的数据容器
     */
    private void initData() {
        // TODO Auto-generated method stub
        ivList = new ArrayList<ImageView>();
        vList = new ArrayList<View>();
        new GetListTask().execute("");
    }
    /**
     * 初始化相关组件
     */
    private void initUI(){
        //判断图片资源的路径是否存在
        if(imageUrls == null ||imageUrls.length==0){
            return;
        }
        //1、获取图片轮补布局
        View v= LayoutInflater.from(con).inflate(R.layout.fg_one_picchange, this,true);
        //2、获取布局中的圆点的布局
        LinearLayout dotll = (LinearLayout) v.findViewById(R.id.ll_dot);
        //3、清理圆点的布局里的内容
        dotll.removeAllViews();
        //根据图片资源数组的长度向圆点布局中动态添加小圆点和向ivList中添加图片
        for(int i=0;i<imageUrls.length;i++){
            //创建一个iv对象
            ImageView iv= new ImageView(con);
            //设置iv对象中图片资源的路径
            iv.setTag(imageUrls[i]);
            if(i==0){//设置默认图片
                iv.setBackgroundResource(R.mipmap.appmain_subject_1);
            }
            iv.setScaleType(ScaleType.FIT_XY);
            ivList.add(iv);
            //添加圆点
            //设置布局格式宽高
            ImageView iv_dot = new ImageView(con);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            //设置小圆点左右两边空隙
            params.leftMargin = 4;
            params.rightMargin = 4;
            dotll.addView(iv_dot,params);
            vList.add(iv_dot);

        }
        vp = (ViewPager) v.findViewById(R.id.vp_banner);
        vp.setFocusable(true);
        //设置viewpager的适配器
        PicChangeAdapter pcad= new PicChangeAdapter(con, ivList, il);
        vp.setAdapter(pcad);
        //添加滑动监听事件
        vp.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                currentid = arg0;
                for(int i=0;i<vList.size();i++){
                    if(i==arg0){
                        vList.get(arg0).setBackgroundResource(R.mipmap.dot_focus);
                    }else{
                        vList.get(arg0).setBackgroundResource(R.mipmap.dot_blur);
                    }
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                switch(arg0){
                    case 1://手势滑动，自动播放暂停
                        isAutoPlay = false;
                        break;
                    case 2://界面切换中
                        isAutoPlay = true;
                        break;
                    case 0://滑动结束，即切换或者加载结束，当前为最后一张此时从右向左滑，则切换第一张
                        if(vp.getCurrentItem() == vp.getAdapter().getCount() -1 && !isAutoPlay){
                            vp.setCurrentItem(0);
                        }else if(vp.getCurrentItem() == 0 && !isAutoPlay){//当前是第一张图，此时从左向右滑，则切换到最后一张
                            vp.setCurrentItem(vp.getAdapter().getCount() -1 );
                        }
                        break;

                }

            }
        });
    }

    private void initImageLoader(Context context) {
        // TODO Auto-generated method stub
        ImageLoaderConfiguration config = new
                ImageLoaderConfiguration.Builder(context).//新建一个图片加载配置对象
                threadPriority(Thread.NORM_PRIORITY-2).    ///设置线程优先级
                denyCacheImageMultipleSizesInMemory().    //计算图片所占内存大小
                discCacheFileNameGenerator(new Md5FileNameGenerator()).//设置图片文件名称
                tasksProcessingOrder(QueueProcessingType.LIFO).//设置队列的格式
                writeDebugLogs().//编写调试信息
                build();//创建加载对象
        ImageLoader.getInstance().init(config);
    }

    public PicChange(Context context, AttributeSet attrs) {
        this(context,attrs,0);

    }

    public PicChange(Context context) {
        this(context,null);
        // TODO Auto-generated constructor stub
    }
    /**
     * 开始轮播图片切换
     */
    private void startPlay(){//开始图片轮播
        //初始化线程工具
        ses = Executors.newSingleThreadScheduledExecutor();
        //启动线程
        ses.scheduleAtFixedRate(new SlideShowTask(), 1,4, TimeUnit.SECONDS);
    }

    /**
     * 停止图片轮播
     */
    private void stopPlay(){
        ses.shutdown();
    }


    private class SlideShowTask implements Runnable{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            synchronized(vp){
                //计算图片的id
                currentid =(currentid+1)%ivList.size();
                //发送消息，让handlerMessage更新界面
                handler.obtainMessage().sendToTarget();
            }
        }
    }
    /**
     * 回收资源方法
     */
    private void destoryBitmaps(){
        for(int i=0;i<Image_count;i++){//遍历每一个imageview对象
            ImageView iv = ivList.get(i);
            Drawable d = iv.getDrawable();
            if(d!=null){//如果不为空则设置为空
                d.setCallback(null);
            }
        }
    }
    /**
     * 异步线程
     * @author MAY
     *
     */
  private  class GetListTask extends AsyncTask<String, Integer, Boolean>{
        /**
         * 线程执行的功能
         */
        @Override
        protected Boolean doInBackground(String... arg0) {
            // TODO Auto-generated method stub
            try{
                imageUrls = new String[]{
                        "http://s10.mogucdn.com/mlcdn/c45406/170428_1k67l98d7h9cc6cdi812965g4278c_778x440.png",
                        "http://img.alicdn.com/tfs/TB12qBRQVXXXXcPXpXXXXXXXXXX-520-280.jpg_q90",
                        "https://img.alicdn.com/simba/img/TB1H3WrQFXXXXcwXpXXSutbFXXX.jpg",
                        "http://img.alicdn.com/tfs/TB1a2EpQFXXXXbIXFXXXXXXXXXX-520-280.jpg_q90",
                        "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"
                };
                return true;
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }
        /**
         * 线程执行之后的处理
         */
        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if(result){//先判断图片资源数组是否初始化成功
                initUI();//初始化界面组件
            }

        }
    }


}