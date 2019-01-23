# DoneView

<img src="/art/preview.gif" alt="sample" title="sample" width="320" height="600" align="right" vspace="52" />

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)

A view which can be used to show a task completion. The view comes with lot of customization features.

USAGE
-----
Just add DoneView in your layout XML and DoneView library in your project via Gradle:

```gradle
dependencies {
  implementation 'io.github.himanshu1496.doneView:doneview:0.1.1'
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



## License
```
Copyright 2017 Himanshu Baghel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
