<div align="center">

# AsBottomDialog

![GitHub tag (latest by date)](https://img.shields.io/github/v/tag/1250422131/AsBottomDialog?label=version)
![GitHub](https://img.shields.io/github/license/1250422131/AsBottomDialog)


</div>

---

### 简介

**AsBottomDialog**是一款纯Kotlin打造的安卓底部对话框（BottomSheetDialog），
此模块是在 [BILIBILIAS](https://github.com/1250422131/bilibilias) 中抽离单独维护的对话框库。
无论是在fragment还是在activity，都可以唤出使用。

### 引入

首先，AsBottomDialog在jitpack发布，因此，你得在项目引入jitpack库的地址。

另外注意，这是测试版本，许多配置还没有加入进去，它不适合现在就参加正式项目。

#### settings.gradle

```
pluginManagement {
    repositories {
        maven { url 'https://jitpack.io' }
        ....
    }
}
dependencyResolutionManagement {
    ....
    repositories {
        maven { url 'https://jitpack.io' }
        ....
    }
}
....
```

如果你没有settings.gradle也不要担心，按照你之前的引入方法，加入在之前有 mavenCentral() 的地方。

#### app 的 build.gradle

在 app 的 build.gradle 中有 dependencies，在这里添加依赖库

```
dependencies {

    implementation 'com.github.1250422131:AsBottomDialog:0.0.1beta08'
    ....
}
```

#### kotlin使用

AsBottomDialog在kotlin的使用中会更加方便，这是由于其本身采用kotlin开发。

```kotlin
 AsDialog.init(this)
    .setTitle("测试")
    .setContent("对话框内容")
    .setCancelable(true) //设置点击外部是否可以关闭
    .setPositiveButton("确定") {
        it.cancel()
    }
    .setNegativeButton("取消") {
        it.cancel()
    }
    .build()
    .show()
```

#### java使用

事实上，我们在使用kotlin带来的方便时，却为Java的使用带来了一些不便，当然，你仍然可以在Java里方便的使用AsBottomDialog， 后面我们可能还会做调整。

```
        AsDialog.INSTANCE.
                init(this)
                .setTitle("标题")
                .setCancelable(true)
                .setContent("没有未来的未来，不是我想要的未来。")
                .setPositiveButton("好的", new Function1<AsDialog, Unit>() {
                    @Override
                    public Unit invoke(AsDialog asDialog) {
                        asDialog.cancel();
                        return null;
                    }
                })
                .build()
                .show();
```

#### java中的问题
可以看到，在Java的使用中，由于AsDialog本身在kotlin是个object类，导致编译后会出现一个INSTANCE对象，这使得调用变得有些麻烦。
并且，由于kotlin在这里使用了高阶函数，导致在Java中会转化为一个匿名类，调用看起来不是那么清晰和好看。

**但是我们会在后面的更新中考虑将这里转换为一个抽象类或者是一个接口类，使其即使是在Java也是清晰的。**

### 主题问题
如果可以请将自己的主题改为
```
Theme.MaterialComponents.DayNight.NoActionBar.Bridge
```
等类似效果主题，因为默认主题会导致无法更改button的颜色。

### 监听器
AsBottomDialog本身是对BottomSheetDialog的使用和封装，因此也支持设置BottomSheetDialog的监听器。
监听对话框情况
```kotlin
 AsDialog.init(this)
            .addListener(object :AsDialogListener{
                override fun onClose(asDialog: AsDialog) {
                  //监听对话框关闭
                }

            })
            .setBottomSheetCallback(object : AsBottomSheetCallback(){
                //监听对话框情况
                override fun onStateChanged(bottomSheet: View, newState: Int): Boolean {
                    return false
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float): Boolean {
                    return false
                }

            })
            .build()
            .show()
```
```
        AsDialog.INSTANCE.
                init(this)
                .setBottomSheetCallback(new AsBottomSheetCallback() {
                    @Override
                    public boolean onStateChanged(@NonNull View bottomSheet, int newState) {
                        return false;
                    }

                    @Override
                    public boolean onSlide(@NonNull View bottomSheet, float slideOffset) {
                        return false;
                    }
                })
                .build()
                .show();
```
如果你尝试一下，发现Java这边会更方便补全代码。



### 问题反馈

企鹅群：703180724

哔哩哔哩：[萌新杰少](https://space.bilibili.com/351201307)

[兔小巢 ](https://support.qq.com/product/337496)
