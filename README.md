
# frame-lib 
[![](https://jitpack.io/v/LeWaves/frame-lib.svg)](https://jitpack.io/#LeWaves/frame-lib)
【简易快速开发App框架】

## **描述**
 
这是一个二次封装[okttp3](https://github.com/square/okhttp)、[volley](https://android.googlesource.com/platform/frameworks/volley)、[JCVideoPlayer](https://github.com/lipangit/JieCaoVideoPlayer)个人优化了Okhttp支持https请求，支持单个和多个文件上传，视频播放功能部分手机无法正常显示Dialog问题，修改了框架里面调用系统Dialog采用自定义Dialog。

   特点：快速加载网络数据，比一般框架加载速度快

## **功能说明**

---

###    **1.1集成使用**

  在app build.gradle 中添加：
  

   android{
   
           ...    
           
       allprojects {
       
        repositories {
        
            jcenter()
            
            maven { url 'https://jitpack.io' }
            
         }         
     }     
   }
   

   
   dependencies {   
   
           ...       
       
       compile 'com.github.LeWaves:frame-lib:v1.0.0'       
   }
 
---
###    **1.2 mainfast.xml添加权限**
    
    <uses-permission android:name="android.permission.INTERNET" />

###    **1.3 网络请求**

####     **HttpMethodState 提供GET、POST方式**

     new HttpJSONRequest(context, HttpMethodState.GET, url, new HttpJSONRequest.RequestNetWork() {
                 @Override
                 public void onSuccess(JSONObject response) {
                     
                 }

                 @Override
                 public void onFailure(VolleyError error) {
                    
                 }
             });
        
        第二种 添加tag

       new HttpJSONRequest(context, HttpMethodState.GET, url, new HttpJSONRequest.RequestNetWork() {
                 @Override
                 public void onSuccess(JSONObject response) {
                     
                 }

                 @Override
                 public void onFailure(VolleyError error) {
                    
                 }
             },tag);
             
        网络加载展示效果图
        
        ![image](https://github.com/LeWaves/frame-lib/blob/master/screenshots/loading_http.gif) 
        
             -----
    
####    **1.4文件上传**
     
      
    单个文件
     
    /**
     * 单个文件上传
     *
     * @param  context
     * @param  url 地址
     * @param  fileName 文件夹名称
     * @param  file 文件
     * @param  params 参数
     * @param  listener 监听
     *
     */
    
    HttpUploadFileRequest.uploadFileMultipart(mContext, url, fileName, file, params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                
            }

            @Override
            public void onMyError(VolleyError error) {

            }
        });
        
      
    多个个文件
     
    /**
     * 单个文件上传
     *
     * @param  context
     * @param  url 地址
     * @param  fileName 文件夹名称
     * @param  file 文件 List<File>
     * @param  params 参数
     * @param  listener 监听
     *
     */
    
    HttpUploadFileRequest.uploadFileMultipart(mContext, url, fileName, file, params, new VolleyInterface(mContext) {
            @Override
            public void onMySuccess(String result) {
                
            }

            @Override
            public void onMyError(VolleyError error) {

            }
        });
        
---
####    **1.5图片加载**

     此功能是引用了[gilde](https://github.com/bumptech/glide)框架

     功能使用：
     
     ImageLoader.display(mContext,imageView,url);
     
     第二种自定义loading和error加载View
     
     ImageLoader.display(mContext,imageView,url,loading,error);
     
        
---
####    **1.6视频播放**

    此功能是引用了JCVideoPlayer框架, 这里不再简述。可以详细查看JCVideoPlayer原文。
    
    
#  总结
   
   在这里我非常感谢大神们的分享，在开发中给予了我们很大的帮助，此框架是二次封装，里面借用了大神的框架。
     
