<div align="center">
  
<h1 align="center">Animation</h1>

[![最新版本](https://img.shields.io/badge/最新版本-1.1.16-brightgreen.svg)](https://play.google.com/store/apps/details?id=com.github.kongpf8848.animation)
[![License](https://img.shields.io/badge/License-Apache%202-brightgreen.svg)](https://www.apache.org/licenses/LICENSE-2.0)

中文 | [English](./README_EN.md)

Android各种动画效果合集，项目包含了丰富的动画实例，体验动画之美，让Android动起来😊😄😎

</div>

## 下载

[<img src="https://github.com/kongpf8848/Animation/blob/master/images/google-play.png"
     alt="Get it on Google Play" 
     height="80">](https://play.google.com/store/apps/details?id=com.github.kongpf8848.animation)
[<img src="https://github.com/kongpf8848/Animation/blob/master/images/free-download.png?raw=true"
     alt="Free Download"
     height="80">](http://hey.scandown.com/animation)

## 截图
|  |  |  |
|:-:|:-:|:-:|
|![image](https://github.com/kongpf8848/Animation/blob/master/images/splash.webp) | ![image](https://github.com/kongpf8848/Animation/blob/master/images/guide.webp) | ![image](https://github.com/kongpf8848/Animation/blob/master/images/kmail.webp) |
|![image](https://github.com/kongpf8848/Animation/blob/master/images/autohome.webp) | ![image](https://github.com/kongpf8848/Animation/blob/master/images/telegram.webp) | ![image](https://github.com/kongpf8848/Animation/blob/master/images/pdj_guide.webp) |


## 逐帧动画

逐帧动画是一帧一帧进行播放的，它的原理与GIF类似，按照预先定义好的图片序列顺序进行播放。
```xml
<?xml version="1.0" encoding="utf-8"?>
<animation-list android:oneshot="false" xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:duration="100" android:drawable="@drawable/loading01" />
    <item android:duration="100" android:drawable="@drawable/loading02" />
    <item android:duration="100" android:drawable="@drawable/loading03" />
    <item android:duration="100" android:drawable="@drawable/loading04" />
    <item android:duration="100" android:drawable="@drawable/loading05" />
    <item android:duration="100" android:drawable="@drawable/loading06" />
    <item android:duration="100" android:drawable="@drawable/loading07" />
    <item android:duration="100" android:drawable="@drawable/loading08" />
    <item android:duration="100" android:drawable="@drawable/loading09" />
    <item android:duration="100" android:drawable="@drawable/loading10" />
    <item android:duration="100" android:drawable="@drawable/loading11" />
    <item android:duration="100" android:drawable="@drawable/loading12" />
</animation-list>
```

## 补间动画

|动画类型|XML配置方式|Java代码实现方式|
|:---:|:---:|:---:|
| 透明度动画|alpha |AlphaAnimation|
| 缩放动画|scale|ScaleAnimation|
| 旋转动画|rotate |RotateAnimation|
| 平移动画|translate |TranslateAnimation|

## 属性动画

直接更改对象的属性来实现的动画。工作原理：在一定时间间隔内，通过不断调用set方法对值进行改变，并不断将该值赋给对象的属性，从而实现该对象在该属性上的动画效果，详细的属性动画介绍可参考🔗[官方文档](https://developer.android.google.cn/guide/topics/graphics/prop-animation)

![image](https://github.com/kongpf8848/Animation/blob/master/images/intro_property.png)

## 转场动画

Google在Android 5.0之后推出的一种动画效果，就是以动画的形式从一个场景过渡到另一个场景，可以参考🔗[Material-Animations](https://github.com/lgvalle/Material-Animations)

## Lottie动画

Lottie是Airbnb推出的一套跨平台的动画完整解决方案，它能够帮助开发者直接加载json格式的动画文件在Android、iOS 和 Web平台上，实现100%与设计稿相同的动画效果，而无需关心中间的实现细节。设计师只需要使用 After Effectes 设计出动画之后，通过使用Lottie提供的Bodymovin插件将设计好的动画导出成json格式的文件交付给开发即可。 🔗[Lottie网站](https://lottiefiles.com)

## Tgs动画

IM软件Telegram推出的全新的Sticker贴纸格式，贴纸文件格式为.tgs，基于Lottie json文件改造而来。🔗[Telegram官网](https://telegram.org)，🔗[GitHub](https://github.com/DrKLO/Telegram)
| 1 | 2 |
|:-:|:-:|
|![image](https://github.com/kongpf8848/Animation/blob/master/images/intro_telegram_1.png)|![image](https://github.com/kongpf8848/Animation/blob/master/images/intro_telegram_2.png)|

## GIF动画

GIF(Graphics Interchange Format)是由CompuServe公司开发的一种图像文件格式，可以将多幅图像保存到一个图像文件，展示的时候将多幅图像数据逐帧读出并显示到屏幕上，从而形成动画效果。在Android中播放GIF通常有以下几种方式：
* ~~使用Android SDK中自带的android.graphics.Movie类(已过时)~~
* 使用[Glide](https://github.com/bumptech/glide)，[Fresco](https://github.com/facebook/fresco)等图片加载类库，Glide支持加载本地和网络上的GIF图片
* 使用[giflib](https://android.googlesource.com/platform/external/giflib/+/android-9.0.0_r16)类库在native层解码GIF，使用[FrameSequenceDrawable](https://android.googlesource.com/platform/frameworks/ex/+/android-9.0.0_r16/framesequence)的双缓冲机制进行绘制展示GIF中的每一帧图像
* 使用[android-gif-drawable](https://github.com/koral--/android-gif-drawable)类库，其底层也是使用giflib进行GIF解码

## SVGA动画

SVGA是一种同时兼容Android/iOS/Flutter/Web平台的动画格式。🔗[SVGA官网](http://svga.io/)

![image](https://github.com/kongpf8848/Animation/blob/master/images/intro_svga.jpg)
