# AcodeHrLayout
android底部弹出抽屉布局,可以设置抽屉布局默认露出的高度，和预计到达的高度。内部可以嵌套Recyleview,ListView,ScrlloView，NestScrollview和常用的5种布局，无需关心事件分发，内部已经处理，使用方便！

# 使用方式
    <com.acode.hr.layout.HrLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff4400"
        app:defaultHeight="110dp"
        app:realHeight="440dp">
        自己的布局
    </com.acode.hr.layout.HrLayout>
