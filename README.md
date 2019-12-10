# AcodeHrLayout
    android底部弹出抽屉布局,可以设置抽屉布局默认露出的高度，和预计到达的高度。内部可以嵌套Recyleview,ListView,ScrlloView，NestScrollview和常用的5种布局，无需关心事件分发，内部已经处理，使用方便！
# APK体验
![Image text](https://github.com/workertao/AcodeHrLayout/blob/master/img/code1.jpg)
# 使用方式
    <com.acode.hr.layout.HrLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff4400"
        app:defaultHeight="110dp"
        app:realHeight="440dp">
        自己的布局
    </com.acode.hr.layout.HrLayout>
# 注意：
     1，HrLayout继承LinearLayout；
     2，内部职能嵌套一个本身带有滚动事件的view；
     3，写的比较仓促，没考虑太多情况，有啥问题可联系我；
# 更新日志
    2019-12-10
    1，新增点击弹起关闭事件     
# 联系方式
    QQ:1240490684