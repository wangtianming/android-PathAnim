##PathAnimLib
    #问题
    * 在android开发中使用移动动画对路径的控制稍显麻烦
    * 自己制作高阶曲线（列如贝塞尔）的动画路径不易

    # 解决它
    * android.graphics.Path提供了非常多的API 如quadTo则是二阶贝塞尔 使用它 能够快速实现如加入购物车动画
    * 使用路径更加可观，简捷。
    * 提供Animation和Animator2种实现。

##Gradle：
    ```groovy
    compile 'org.sun.pathanim:library:1.0.0'
    ```

##有问题反馈
    在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流
    * email: asd858061@126.com