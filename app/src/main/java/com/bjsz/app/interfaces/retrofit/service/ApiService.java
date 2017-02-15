package com.bjsz.app.interfaces.retrofit.service;

import com.bjsz.app.entity.returndata.CodeReturnData;
import com.bjsz.app.entity.returndata.LoginData;
import com.bjsz.app.entity.returndata.archives.AddFamilyhistoryData;
import com.bjsz.app.entity.returndata.archives.AddMedicalhistoryData;
import com.bjsz.app.entity.returndata.archives.EssentialInformationData;
import com.bjsz.app.entity.returndata.archives.FamilyhistoryData;
import com.bjsz.app.entity.returndata.archives.LifeHabitData;
import com.bjsz.app.entity.returndata.archives.MedicalhistoryData;
import com.bjsz.app.entity.returndata.data.CategoryData;
import com.bjsz.app.entity.returndata.data.CategoryDetailsData;
import com.bjsz.app.entity.returndata.data.HealthyData;
import com.bjsz.app.entity.returndata.my.FeedbackData;
import com.bjsz.app.entity.returndata.my.MechanismData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

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

    /**
     * 获取验证码
     * @param phoneNumber
     * @return
     */
    @GET("send_sms")
    Call<CodeReturnData> getCode(@Query("phoneNumber") String phoneNumber);

    /**
     * 登陆
     */
    @GET("login")
    Call<LoginData> getLogin(@Query("phoneNumber") String phoneNumber, @Query("verCode") String verCode);

    /**
     * 获取个人基本信息
     */
    @GET("user_info")
    Call<EssentialInformationData> getPersonMessage(@Query("uid") String uid);

    /**
     * 获取生活习惯
     */
    @GET("user_habits")
    Call<LifeHabitData> getLifeHabit(@Query("uid") String uid);

    /**
     * 获取个人病史里（既往史）的接口
     */
    @GET("user_illness")
    Call<MedicalhistoryData> getMedicalhistory(@Query("uid") String uid, @Query("key") String key);

    /**
     * 获取个人病史里（家族史）的接口
     */
    @GET("user_illness")
    Call<FamilyhistoryData> getFamilyhistory(@Query("uid") String uid, @Query("key") String key);

    /**
     * 添加既往史
     */
    @GET("user_anamnesis")
    Call<AddMedicalhistoryData> getAddMedicalhistoryMesssage(@Query("uid") String uid, @Query("illness") String illness, @Query("cure") String cure,
                                                             @Query("sicken_time") String sicken_time, @Query("cure_time") String cure_time);

    /**
     * 添加家族史
     */
    @GET("user_family")
    Call<AddFamilyhistoryData> getAddFamilyhistoryMessage(@Query("uid") String uid, @Query("illness") String illness, @Query("cure") String cure,
                                                          @Query("sicken_time") String sicken_time, @Query("cure_time") String cure_time,
                                                          @Query("relation") String relation);

    /**
     * 我的机构
     */
    @GET("user_gov_info")
    Call<MechanismData> getMyMechanism(@Query("phoneNumber") String phoneNumber);

    /**
     * 问题反馈
     */
    @GET("user_problem_feedback")
    Call<FeedbackData> netWorkFeedback(@Query("phoneNumber") String phoneNumber, @Query("content") String content);

    /**
     * 获取首页健康数据
     */
    @GET("user_lately_data")
    Call<HealthyData> getHealthyData(@Query("uid") String uid, @Query("healthyKey") String healthyKey);

    /**
     * 获取检测报告
     */
    @GET("user_category_data")
    Call<CategoryData> getCategoryData(@Query("phoneNumber") String phoneNumber, @Query("dataName") String dataName);

    /**
     * 获取检测报告详情
     */
    @GET("user_data_info")
    Call<CategoryDetailsData> getCategoryDetailsData(@Query("phoneNumber") String phoneNumber, @Query("dataName") String dataName, @Query("key") String key);

}
