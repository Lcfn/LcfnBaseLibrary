package com.leibown.lcfn_library.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;


public class ShowImageUtils {


    /**
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewNoCenter(Context context, int errorimg, String url,
                                     ImageView imgeview) {

        Glide.with(context)
                .load(url)
                .dontAnimate()
                .placeholder(errorimg)
                .error(errorimg)
                .into(imgeview);
    }

    /**
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, int errorimg, String url,
                                     ImageView imgeview) {

        Glide.with(context)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .placeholder(errorimg)
                .error(errorimg)
                .into(imgeview);
    }

    public static void showImageView(Context context, int errorimg, String url,
                                     ImageView imgeview, int width, int height) {

        Glide.with(context)
                .load(url)
                .dontAnimate()
                .centerCrop()
                .placeholder(errorimg)
                .override(width, height)
                .error(errorimg)
                .into(imgeview);
    }

    /**
     * 显示图片Imageview
     *
     * @param context  上下文
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageView(Context context, String url,
                                     ImageView imgeview) {

        Glide.with(context)
                .load(url)
                .dontAnimate()
                .into(imgeview);
    }


    /**
     * 显示图片Imageview
     *
     * @param context    上下文
     * @param errorimg   错误的资源图片
     * @param resourceId 图片链接
     * @param imgeview   组件
     */
    public static void showImageView(Context context, int errorimg, Integer resourceId,
                                     ImageView imgeview) {

        Glide.with(context)
                .load(resourceId)
                .dontAnimate()
                .placeholder(errorimg)
                .error(errorimg)
                .into(imgeview);
    }

    public static void showImageView(Context context, int errorimg, Integer resourceId,
                                     ImageView imgeview, int width, int height) {

        Glide.with(context)
                .load(resourceId)
                .dontAnimate()
                .placeholder(errorimg)
                .override(width, height)
                .error(errorimg)
                .into(imgeview);
    }

    /**
     * 显示图片 圆形显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Context context, int errorimg,
                                             String url, ImageView imgeview) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .error(errorimg)
                .placeholder(errorimg)
                .transform(new GlideCircleTransform(context))
                .into(imgeview);


    }


    public static void showImageViewToCircle(Context context, Drawable errorimg,
                                             String url, ImageView imgeview) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .error(errorimg)
                .placeholder(errorimg)
                .transform(new GlideCircleTransform(context))
                .into(imgeview);


    }

    /**
     * 显示图片 圆形显示  ImageView
     *
     * @param context  上下文
     * @param errorimg 错误的资源图片
     * @param url      图片链接
     * @param imgeview 组件
     */
    public static void showImageViewToCircle(Context context, int errorimg,
                                             Integer url, ImageView imgeview) {
        Glide.with(context)
                .load(url)
                .centerCrop()
                .dontAnimate()
                .error(errorimg)
                .placeholder(errorimg)
                .transform(new GlideCircleTransform(context))
                .into(imgeview);


    }

    /**
     * 圆角
     *
     * @param context
     * @param errorimg
     * @param url
     * @param imgeview
     * @param radiusdp 半径
     */
    public static void showImageViewToRound(Context context, int errorimg,
                                            String url, ImageView imgeview, int radiusdp) {
        Glide.with(context).load(url)
                .dontAnimate()
                .error(errorimg)
                .placeholder(errorimg)
                .transform(new CenterCrop(context), new GlideRoundTransform(context, radiusdp))
                .into(imgeview);

    }
}