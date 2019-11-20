# AcodeHrLayout
android底部弹出抽屉布局,可以设置抽屉布局默认露出的高度，和预计到达的高度。内部可以嵌套Recyleview,ListView,ScrlloView，无需关心事件分发，内部已经处理，使用方便！

# 效果图
https://github.com/workertao/AcodeHrLayout/tree/master/img/a.gif

# 嵌套RecyclerView使用
    <com.acode.hr.layout.HrLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff4400"
        app:defaultHeight="110dp"
        app:realHeight="440dp">
        <androidx.recyclerview.widget.RecyclerView
            android:id="@+id/rv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    </com.acode.hr.layout.HrLayout>

# 嵌套ScrollView使用
    <com.acode.hr.layout.HrLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff4400"
        app:defaultHeight="110dp"
        app:realHeight="440dp">
        <ScrollView
            android:id="@+id/rv"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
    </com.acode.hr.layout.HrLayout>