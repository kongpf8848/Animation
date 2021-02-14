# Animation
Android各种动画效果,无动画,不精彩:smile::smile::smile:

# Apk
[下载地址](http://d.firim.vip/animation)

![二维码](https://github.com/kongpf8848/Animation/blob/master/screenshots/qrcode.png)

# 逐帧动画（Frame Animation）
一帧一帧进行播放，它的原理与Gif类似，按序播放一组预先定义好的图片序列，如:
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

# 补间动画（Tween Animation）
|动画类型|XML配置方式|Java代码实现方式|
|:---:|:---:|:---:|
| 渐变透明度动画|alpha |AlphaAnimation|
| 缩放动画|scale|ScaleAnimation|
| 旋转动画|rotate |RotateAnimation|
| 平移动画|translate |TranslateAnimation|

# 属性动画（Property Animimation）
直接更改View 的属性来实现的动画。
工作原理：在一定时间间隔内，通过不断调用set方法对值进行改变，并不断将该值赋给对象的属性，从而实现该对象在该属性上的动画效果

# 转场动画 (Transition Animiation)
Google在Android 5.0之后推出的一种动画效果，就是以某种方式从一个场景以动画的形式过渡到另一个场景，可以参考[Material-Animations](https://github.com/lgvalle/Material-Animations)

# Lottie动画
Lottie 是 Airbnb推出的一套跨平台的动画完整解决方案，它能够帮助开发者直接加载json格式的文件在 iOS、Android 和 React Native之上，实现 100% 与设计稿相同的动画效果，而无需关心中间的实现细节。设计师只需要使用 After Effectes 设计出动画之后，通过使用 Lottie 提供的Bodymovin插件将设计好的动画导出成json格式的文件交付给开发即可完成。 [Lottie网站](https://lottiefiles.com) [lottie-android](https://github.com/LottieFiles/lottie-android)

# Tgs动画
IM软件Telegram 推出的新的Sticker贴纸格式，这个全新的 Sticker 贴纸格式为 .tgs，其实就是基于lottie json文件改造而来的一种格式

![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/telegram-sticker.gif)

# 部分截图
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/splash.webp)
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/guide.webp)
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/rotate.webp)
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/autohome.webp)
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/telegram.webp)
![image](https://github.com/kongpf8848/Animation/blob/master/screenshots/cupid.webp)
