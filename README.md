# TDialog
###  Hello,这是一个仿IOS的Android自定义Dialog
  使用简单.一行代码设置参数搞定,然后实现对应监听.监听参数包括两个.一个是对象(在当前类实现接口方法的时候,区分不同的TDialog对象).
  另外一个是位置索引(通过下标区分第几个item被点击).<br>
###  预览图:<br>
  <img width="350"  src="https://github.com/HoldMyOwn/TDialog/blob/master/preview/all.gif"/><br>
###  用法:
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
###   具体细节用法,下载查看Demo
###   模板依赖:&nbsp;&nbsp;项目里面的TDialog模板(可更加灵活扩展)
###   gradle依赖:&nbsp;&nbsp;&nbsp;compile&nbsp;'com.jkt:tdialog:1.0.0'

