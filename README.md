# AcodeHrLayout
    android底部弹出抽屉布局,可以设置抽屉布局默认露出的高度，和预计到达的高度，还可增加headerview，titleview。
    内部可以嵌套Recyleview,ListView,ScrlloView，NestScrollview和常用的5种布局。
    无需关心事件分发，内部已经处理，使用方便！
# APK体验（安装密码：666）
![安装密码：666](https://github.com/workertao/AcodeHrLayout/blob/master/img/code1.png)
# 使用方式
    <com.acode.hr.layout.HrLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#ff4400"
        app:defaultHeight="110dp"
        app:realHeight="440dp">
        自己的布局
    </com.acode.hr.layout.HrLayout>
    
    如果需要动态设置高度可以使用以下代码
    //设置默认露出的高度，参数单位是px
    setDefaultHeight(float defaultHeight)；
    //设置最终露出的高度，参数单位是px
    setRealHeight(float defaultHeight)；
# 注意：
     1，HrLayout继承LinearLayout；
     2，内部只能嵌套一个本身带有滚动事件的view；
     3，新增addTouchView(View view)方法内的view会自己消费touch事件；
     4，新增addHeaderView(View view)方法内的view会自己消费touch事件;因为headerView不属于HrLayout,
        所以也不响应HrLayout的touch事件，他跟随滑动只是坐标的变化；
     5，新增addTitleView(View... view)方法内的view数组会自己消费touch事件;因为titleView不属于HrLayout,
        所以也不响应HrLayout的touch事件，他跟随滑动只是坐标的变化；
     6，titleView可以是多个，但这多个view必须与HrLayout平级；
     7，写的比较仓促，没考虑太多情况，有啥问题可联系我；
# 更新日志
     2019-12-10
     1，新增自己消费touch事件的方法
        hrLayout.setTouchView(btnTouchView);
     2，新增滚动到顶部方法   
        hrLayout.toTop();
     3，新增滚动到底部方法
        hrLayout.toBottom();
        
     2019-12-11
      //headerview只能添加一个，headerview将自己消费touch事件
      public void addHeaderView(View view) {
          this.headerView = view;
      }
     
      //titleview可以添加多个，必须是在activity布局内声明的view，且不在hrlayout内部，titleview将自己消费touch事件
      public void addTitleView(View... views) {
          this.titleViews = views;
      }
     
      //touchView只能添加一个，必须是在activity布局内声明的view，且必须在hrlayout内部，touchview内部的子view将自己消费touch事件
      public HrLayout addTouchView(View touchView) {
          this.touchView = touchView;
          return this;
      }
      
      2019-12-12
       public interface OnScrollStateListener {
     
          /**
            * @param state>0 顶部  state<0底部
            */
          void onState(int state);
       }
# 联系方式
     QQ:1240490684