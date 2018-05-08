package com.mayi.yun.teachsystem.network;


import com.mayi.yun.teachsystem.bean.AttendUserInfo;
import com.mayi.yun.teachsystem.bean.AttendVo;
import com.mayi.yun.teachsystem.bean.ClassVo;
import com.mayi.yun.teachsystem.bean.Common;
import com.mayi.yun.teachsystem.bean.Common2;
import com.mayi.yun.teachsystem.bean.CourseVo;
import com.mayi.yun.teachsystem.bean.ScheduleVo;
import com.mayi.yun.teachsystem.bean.UserInfo;
import com.mayi.yun.teachsystem.bean.VacationVo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 作者： wh
 * 时间：  2018/2/27
 * 名称：网络请求地址
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public interface ApiService {
    String HOST = "http://101.132.138.122:8080/";


    /**
     * 上传图片
     */
    @Multipart
    @POST("/member/uploadFile")
    Observable<Common<String>> uploadFile(@Part MultipartBody.Part part);

    /**
     * 登录
     *
     * @param username username
     * @param password password
     */
    @POST("member/login")
    @FormUrlEncoded
    Observable<Common<UserInfo>> login(@Field("userSn") String username, @Field("password") String password);

    /**
     * 根据班级和用户类型 查询所有的人员 （userType 默认3        1 班主任 2 普通老师 3 学生 4 管理员）
     *
     * @param classId
     * @param userType
     */
    @POST("member/selectUserByClassId")
    @FormUrlEncoded
    Observable<Common2<List<UserInfo>>> getUserByClassId(@Field("classId") String classId, @Field("userType") String userType);

    /**
     * 根据班级和用户类型 查询所有的人员 （userType 默认3        1 班主任 2 普通老师 3 学生 4 管理员）
     *
     * @param userType
     */
    @POST("member/selectUserByClassId")
    @FormUrlEncoded
    Observable<Common2<List<UserInfo>>> getUserByClassId(@Field("userType") String userType);

    /**
     * 查看当前课程未考勤的学生列表
     */
    @POST("member/selectUserList4Sign")
    @FormUrlEncoded
    Observable<Common<List<UserInfo>>> getUserListSign(@Field("scheduleId") int scheduleId);


    @POST("member/add")
    @FormUrlEncoded
    Observable<Common<String>> addMember(@FieldMap Map<String, Object> params);

    /**
     * 查看所有的班级列表
     */
    @POST("member/selectClassList")
    @FormUrlEncoded
    Observable<Common<List<ClassVo>>> getClassList(@Field("teacherId") String teacherId);

    /**
     * 添加或修改课表
     */
    @POST("member/addSchedule")
    @FormUrlEncoded
    Observable<Common<String>> addSchedule(@FieldMap Map<String, Object> params);

    /**
     * 删除课程表
     */
    @POST(" member/deleteSchedule")
    @FormUrlEncoded
    Observable<Common<String>> deleteSchedule(@Field("id") int id);

    /**
     * 获取课表
     */
    @POST("member/selectScheduleList")
    @FormUrlEncoded
    Observable<Common<List<CourseVo>>> getScheduleList(@Field("classId") String classId);


    /**
     * 老师查看当天所有的课表
     */
    @POST("member/selectScheduleListByTeacherId")
    @FormUrlEncoded
    Observable<Common<List<CourseVo>>> getScheduleListByTeacherId(@Field("teacherId") String teacherId);


    /**
     * 添加考勤（isSign  0 缺席 1 签到）
     */
    @POST("member/addSign")
    @FormUrlEncoded
    Observable<Common<String>> addSign(@FieldMap Map<String, Object> params);
    //userId   userName   scheduleId  isSign

    /**
     * 老师查看当前课表的学生的考勤
     */
    @POST("member/selectSignListByParams")
    @FormUrlEncoded
    Observable<Common<List<AttendUserInfo>>> getSignListByParams(@FieldMap Map<String, Object> params);
    //teacherId   classId   scheduleId

    /**
     * 根据学生id查看所有的考勤记录
     */
    @POST("member/selectSignListByUserId")
    @FormUrlEncoded
    Observable<Common<List<ScheduleVo>>> getSignListByUserId(@Field("userId") String userId);

    /**
     * 根据学生id查看当天的考勤记录（班主任查看用同一个）
     */
    @POST("member/selectSignListByUserId4Day")
    @FormUrlEncoded
    Observable<Common<List<AttendVo>>> getSignListByUserIdDay(@Field("userId") String userId);

    /**
     * 学生请假
     */
    @POST("member/addLeave")
    @FormUrlEncoded
    Observable<Common<String>> addLeave(@FieldMap Map<String, Object> params);

    /**
     * 学生查看自己请假列表
     */
    @POST("member/selectLeaveList4User")
    @FormUrlEncoded
    Observable<Common<List<VacationVo>>> getLeaveListUser(@Field("userId") int userId);

    /**
     * 班主任查看班级学生请假
     */
    @POST("member/selectLeaveList4Teacher")
    @FormUrlEncoded
    Observable<Common<List<VacationVo>>> getLeaveListTeacher(@Field("teacherId") int teacherId);

    /**
     * 老师审批请假状态
     */
    @POST("member/updateLeave")
    @FormUrlEncoded
    Observable<Common<String>> updateLeave(@FieldMap Map<String, Object> params);

    /**
     * 修改密码
     */
    @POST("/member/updateUser")
    @FormUrlEncoded
    Observable<Common<String>> updateUser(@FieldMap Map<String, Object> params);

    /**
     * 删除信息
     */
    @POST("/member/deleteUser")
    @FormUrlEncoded
    Observable<Common<String>> deleteUser(@Field("userId") int userId);



}
