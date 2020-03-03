package com.htl.jsf.ApiRequest.WithErrorCode;


import com.htl.jsf.Models.RegisterResponse;

import retrofit.Callback;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Interface {

//    @FormUrlEncoded
//    @POST("/user_registration")
//    void getotp(
//            @Field("name")
//                    String name,
//            @Field("mobile")
//                    String mobile,
//            @Field("city_id")
//                    String city_id,
//            @Field("image")
//                    String image,
//            Callback<RegisterResponse> modelCallback);


    @POST("/user_registration")
    void getotp(@Body String body,
            Callback<RegisterResponse> modelCallback);




//    @FormUrlEncoded
//    @POST("/user_login")
//    void user_login(
//            @Field("mobile")
//                    String mobile,
//            Callback<UserLoginModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/login_verification")
//    void user_login_otp(
//            @Field("mobile")
//                    String mobile,
//            @Field("otp")
//                    String otp,
//            Callback<UserLoginOtpModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/user_profile")
//    void user_profile(
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            Callback<UserProfileModel> callback);
//
//    @FormUrlEncoded
//    @POST("/edit_user_profile")
//    void update_user_profile(
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("user_name")
//                    String user_name,
//            @Field("user_email")
//                    String user_email,
//            @Field("user_mobile")
//                    String user_number,
//            Callback<RegistrationModel> callback);
//
//    @FormUrlEncoded
//    @POST("/edit_user_mobile_verification")
//    void user_edit_otp(
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("mobile")
//                    String mobile,
//            @Field("otp")
//                    String otp,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @FormUrlEncoded
//    @POST("/registration_verification")
//    void otp_verify(
//            @Field("mobile")
//                    String mobile,
//            @Field("otp")
//                    String otp,
//            Callback<RegistrationOtpModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/member_registration_verification")
//    void member_otp_verify(
//            @Field("mobile")
//                    String mobile,
//            @Field("otp")
//                    String otp,
//            Callback<RegistrationOtpModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/member_login")
//    void member_login(
//            @Field("mobile")
//                    String mobile,
//            @Field("password")
//                    String password,
//            Callback<MemberLoginModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/resend_otp")
//    void resend_otp(
//            @Field("mobile")
//                    String mobile,
//            @Field("type")
//                    String type,
//            Callback<UserLoginModel> callback);
//
//    @FormUrlEncoded
//    @POST("/banners_list")
//    void home_banner_list(
//            @Field("user_id")
//                    String user_id,
//            Callback<HomeBannerModel> callback
//    );
//
//    @FormUrlEncoded
//    @POST("/member_list")
//    void get_memberlist(
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            Callback<MembersListModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/most_liked_members_list")
//    void most_liked_members(
//            @Field("type")
//                    String type,
//            Callback<HomeMembersListModel> modelCallback);
//
//
//    @FormUrlEncoded
//    @POST("/most_liked_product_service_list")
//    void most_liked_product_service(
//            @Field("type")
//                    String type,
//            Callback<HomeProductModel> modelCallback);
//
//
//    @FormUrlEncoded
//    @POST("/product_service_list")
//    void get_product_service_list(
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("type")
//                    String type,
//            Callback<HomeProductModel> callback);
//
//    @FormUrlEncoded
//    @POST("/other_member_profile")
//    void show_otherMemberProfile(
//            @Field("member_id")
//                    String member_id,
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("other_member_id")
//                    String other_member_id,
//            Callback<MemberProfileModel> callback);
//
//    @Multipart
//    @POST("/member_registration")
//    void member_signup(
//            @Part("name")
//                    String name,
//            @Part("email")
//                    String email,
//            @Part("mobile")
//                    String mobile,
//            @Part("status")
//                    String status,
//            @Part("user_id")
//                    String user_id,
//            @Part("image")
//                    TypedFile image,
//            Callback<RegistrationModel> modelCallback);
//
//    @Multipart
//    @POST("/add_member_company_detail")
//    void add_company_details(
//            @Part("member_id")
//                    String member_id,
//            @Part("auth_key")
//                    String auth_key,
//            @Part("company_name")
//                    String company_name,
//            @Part("company_email")
//                    String company_email,
//            @Part("company_mobile")
//                    String company_mobile,
//            @Part("address")
//                    String address,
//            @Part("city")
//                    String city,
//            @Part("tag_line")
//                    String tag_line,
//            @Part("bio")
//                    String bio,
//            @Part("gst")
//                    String gst,
//            @Part("cin")
//                    String cin,
//            @Part("logo")
//                    TypedFile logo,
//            Callback<FillCompanyDetailModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/member_profile")
//    void get_memberprofile(
//            @Field("member_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            Callback<MemberProfileModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/service_type")
//    void get_product_or_service_type(
//            @Field("member_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            Callback<GetProductServiceType> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/price_type")
//    void get_price_type(
//            @Field("member_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            Callback<FillPriceTypeModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/add_member_product_service")
//    void add_product_service(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("company_type_id")
//                    String company_type_id,
//            @Field("name")
//                    String name,
//            @Field("detail")
//                    String detail,
//            @Field("price_type_id")
//                    String price_type_id,
//            @Field("price")
//                    String price,
//            @Field("image")
//                    String image,
//            Callback<AddServiceProductModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/product_service_detail")
//    void product_service_details(
//            @Field("member_id")
//                    String member_id,
//            @Field("user_id")
//                    String user_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("product_service_id")
//                    String product_service_id,
//            Callback<MemberProductDataModel> callback);
//
//    @FormUrlEncoded
//    @POST("/add_product_service_images")
//    void add_product_image(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("product_service_id")
//                    String product_service_id,
//            @Field("image")
//                    String image,
//            Callback<AddServiceProductModel> callback);
//
//    @FormUrlEncoded
//    @POST("/delete_member_product_service")
//    void delete_product_service(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("product_service_id")
//                    String product_service_id,
//            Callback<AddServiceProductModel> callback);
//
//    @FormUrlEncoded
//    @POST("/delete_product_service_image")
//    void delete_product_image(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("image_id")
//                    String image,
//            Callback<ImageDeleteModel> callback);
//
//    @FormUrlEncoded
//    @POST("/edit_member_product_service")
//    void edit_product_service(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("product_service_id")
//                    String product_service_id,
//            @Field("company_type_id")
//                    String company_type_id,
//            @Field("name")
//                    String name,
//            @Field("detail")
//                    String detail,
//            @Field("price_type_id")
//                    String price_type_id,
//            @Field("price")
//                    String price,
//            Callback<AddServiceProductModel> callback);
//
//
//    @Multipart
//    @POST("/edit_member_profile")
//    void edit_member_info(
//            @Part("member_id")
//                    String member_id,
//            @Part("auth_key")
//                    String auth_key,
//            @Part("member_name")
//                    String name,
//            @Part("member_email")
//                    String email,
//            @Part("member_mobile")
//                    String mobile,
//            @Part("member_status")
//                    String status,
//            @Part("image")
//                    TypedFile image,
//            Callback<EditMemberInfoModel> modelCallback);
//
//
//    @FormUrlEncoded
//    @POST("/edit_member_mobile_verification")
//    void edit_member_otp_verify(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("mobile")
//                    String mobile,
//            @Field("otp")
//                    String otp,
//            Callback<EditMemberOtpVerifyModel> modelCallback);
//
//    @FormUrlEncoded
//    @POST("/enquiry_product_service")
//    void submit_enquiry(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("user_id")
//                    String user_id,
//            @Field("product_service_id")
//                    String product_service_id,
//            @Field("enquiry")
//                    String enquiry,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @FormUrlEncoded
//    @POST("/like_product_service")
//    void prdct_srvc_like(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("user_id")
//                    String user_id,
//            @Field("product_service_id")
//                    String product_service_id,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @Multipart
//    @POST("/add_member_clients")
//    void add_client(
//            @Part("member_id")
//                    String member_id,
//            @Part("auth_key")
//                    String auth_key,
//            @Part("client")
//                    String name,
//            @Part("description")
//                    String email,
//            @Part("image")
//                    TypedFile image,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @Multipart
//    @POST("/add_member_awards")
//    void add_award(
//            @Part("member_id")
//                    String member_id,
//            @Part("auth_key")
//                    String auth_key,
//            @Part("description")
//                    String email,
//            @Part("image")
//                    TypedFile image,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @Multipart
//    @POST("/edit_member_awards")
//    void edit_award(
//            @Part("auth_key")
//                    String auth_key,
//            @Part("member_id")
//                    String member_id,
//            @Part("description")
//                    String email,
//            @Part("awards_id")
//                    String awards_id,
//            @Part("image")
//                    TypedFile image,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @Multipart
//    @POST("/edit_member_client")
//    void edit_client(
//            @Part("auth_key")
//                    String auth_key,
//            @Part("member_id")
//                    String member_id,
//            @Part("description")
//                    String email,
//            @Part("name")
//                    String name,
//            @Part("client_id")
//                    String client_id,
//            @Part("image")
//                    TypedFile image,
//            Callback<EditMemberOtpVerifyModel> callback);
//
//    @FormUrlEncoded
//    @POST("/offers_list")
//    void offer_list(
//            @Field("user_id")
//                    String user_id,
//            Callback<OffersModel> callback);
//
//    @FormUrlEncoded
//    @POST("/discover")
//    void search_list(
//            @Field("keyword")
//                    String keyword,
//            Callback<SearchListModel> callback);
//
//    @FormUrlEncoded
//    @POST("/device_fcm")
//    void send_device_token(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("user_id")
//                    String user_id,
//            @Field("fcm_regid")
//                    String fcm_regid,
//            Callback<FCMModel> callback);

}
