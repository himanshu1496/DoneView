# DoneView

<img src="/art/preview.gif" alt="sample" title="sample" width="320" height="600" align="right" vspace="52" />

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)

A view which can be used to show a task completion. The view comes with lot of customization features.

USAGE
-----

Just add DoneView in your layout XML and DoneView library in your project via Gradle:

```gradle
dependencies {
  implementation 'com.bitvale:lightprogress:1.0.0'
}
```

XML
-----

```xml
<com.himanshubaghel.dev.doneview.DoneView
        android:id="@+id/done_view"
        android:layout_width="100dp"
        android:layout_height="100dp"
        app:circleStrokeColor="@color/colorPrimary"
        app:iconStrokeColor="@color/colorAccent"
        app:circleStrokeWidth="2dp"
        app:iconStrokeWidth="2dp"
        app:iconPadding="10dp"
        app:repeatCount="2" />

```

You must use the following properties in your XML to change your DoneView.


##### Properties:

* `app:circleStrokeColor`           (color)     -> default  "0000ff"
* `app:iconStrokeColor`             (color)     -> default  "ff00ff"
* `app:circleStrokeWidth`           (dimension) -> default  5px
* `app:iconStrokeWidth`             (dimension) -> default  5px
* `app:iconPadding`                 (dimension) -> default  20px
* `app:repeatCount`                 (integer)   -> default  -1 (For infinite repetition)

Default value of `repeatCount` is -1 which means the animation will repeat infinte times until `stopAnimation` funtion is called.

Java
-----
To start the animation:
```Java
doneView.startAnimation();

```

To stop the animation:
```Java
doneView.stopAnimation();

```
