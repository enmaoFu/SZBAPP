package com.bjsz.app.interfaces.retrofit.service;

/**
 * 将HTTP API改造成java接口
 * 更多示例与官方资料
 * http://www.open-open.com/lib/view/open1473584426435.html
 * http://square.github.io/retrofit/
 *
 * @author enmaoFu
 * @date 2016-12-23
 */
public interface ApiService {

    /**
     * get请求 Call<User> User表示返回JSON所对应的实体类，在成功的时候反序列化自动解析
     * @GET
     *      是代表get请求，括号里面是地址，根地址在调用的地方传入
     * @Query
     *      是表示设置参数
     * @param start
     * @param count
     * @return
     */
  /* @GET("top250")
   Call<User> getTest(@Query("start") int start, @Query("count") int count);*/

    /**
     * 基于对象的post请求，传递对象数据
     * @POST
     *      是代表post请求，括号里面是地址，根地址在调用的地方传入
     * @Body
     *      在基于对象post请求的时候需要用这个来设置对象参数
     * @param user
     * @return
     */
    /*@POST("users/new")
    Call<User> createUser(@Body User user);*/

    /**
     * post请求
     * @POST
     *      是代表post请求，括号里面是地址，根地址在调用的地方传入
     * @Query
     *      是表示设置参数
     * @param type
     * @param postid
     * @return
     */
    //@POST("query")
    //Call<PostQueryInfo> postQueryTest(@Query("type") String type, @Query("postid") String postid);

    /**
     * from表单的post请求
     * @POST
     *      是代表post请求，括号里面是地址，根地址在调用的地方传入
     * @FormUrlEncoded
     *      表示这是from表单的post请求
     * @Field
     *      在表单请求的时候需要用这个来设置参数
     * @param type
     * @param postidl
     * @return
     */
    /*@FormUrlEncoded
    @POST("query")
    Call<PostQueryInfo> postFromTest(@Field("type") String type, @Field("postid") String postid);*/

}
