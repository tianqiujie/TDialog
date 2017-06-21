# TDialog
###  Hello,这是一个仿IOS的Android自定义Dialog
  使用简单.一行代码设置参数搞定,然后实现对应监听.监听参数包括两个.一个是对象(在当前类实现接口方法的时候,区分不同的TDialog对象).
  另外一个是位置索引(通过下标区分第几个item被点击).<br>
###  预览图:
  <img width="350"  src="https://github.com/HoldMyOwn/TDialog/blob/master/preview/all.gif" /><br>
###  用法:
<pre>
       //参数:1:activity:需要获取窗口以及作为上下文参数
             2:风格(目前有中间显示和底部显示.包含有个字动画,而且可自己设定)
             3:对话框的条目
             4:标题(标题居中,如果不传则显示默认标题)
             5:消息:显示的提示文本
             6:监听对象:条目点击回调接口,推荐当前类实现.接口方法有对象参数可以识别是哪个TDialog.

      mTDialog = new TDialog(MainActivity.this, TDialog.Style.Center, {"取消", "确认"},
                 "中间对话框", "点击外部区域不可取消", this);
      //------可选操作,文本样式、消失监听、更改动画等......
      mTDialog.setItemTextColor(getResources().getColor(R.color.bgColor_overlay));
      mTDialog.setMsgTextColor(getResources().getColor(R.color.colorAccent));
      mTDialog.setItemTextColorAt(1, getResources().getColor(R.color.colorPrimary));
      mTDialog.setItemTextColorAt(2, getResources().getColor(R.color.colorAccent));
      mTDialog.setItemTextColorAt(10, getResources().getColor(R.color.colorAccent));
      mTDialog.setCancelable(false);
      mTDialog.setInAnim(AnimationUtils.loadAnimation(this,R.anim.slide_in_bottom1));
      mTDialog.setOutAnim(AnimationUtils.loadAnimation(this,R.anim.slide_out_bottom1))
      //---------------
      mTDialog.show();
 </pre>
###   事件回调:
<pre>
    //创建对象必须实现item点击监听,第一个参数是对象,区分哪个TDialog,
    //第二个参数是点击的位置,位置就是创建对象时候传入数组的下标.
    @Override
    public void onItemClick(Object object, int position) {
        if (object == mTDialog) {
            Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
            if (position == 0) {
            //任何item点击都会正常消失TDialog对象(内部后续调用dismiss方法),dismissImmediately方法
            //则会迅速消失掉TDialog对象,区别:dismiss()方法有消失动画,动画完毕之后真正消失
                mTDialog.dismissImmediately();
            }
        }
    }

    //可选监听,在Tdialog消失的时候触发,如果是点击Item则只触发itemClick回调,不会触发消失回调
    //当点击对话框内容外区域,如果设置监听并且控件是点击外边区域可取消类型.那么触发回调.
    @Override
    public void onDismissClick(Object object) {
        if (object == mTDialog) {
            Toast.makeText(this, "消失", Toast.LENGTH_SHORT).show();
        }
    }
</pre>
###  扩展功能: </br>
<table >
    <tr>
        <td><img width="230" src="https://github.com/HoldMyOwn/TDialog/blob/master/preview/a.jpg"/></td>
        <td><img width="230" src="https://github.com/HoldMyOwn/TDialog/blob/master/preview/b.jpg"/></td>
    </tr>
</table>
<pre>
    //一行代码添加View,软键盘事件动态调整对话框位置
    View inflate = LayoutInflater.from(this).inflate(R.layout.alertext_from, null);
    mTDialog.addView(inflate);
</pre>

###   具体细节用法,下载查看Demo
###   模板依赖:&nbsp;&nbsp;项目里面的tdialog模板(可更加灵活扩展)
###   gradle依赖:&nbsp;&nbsp;&nbsp;compile&nbsp;'com.jkt:tdialog:1.0.5'

