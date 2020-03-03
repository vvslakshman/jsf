package com.htl.jsf.ApiRequest;


import com.google.gson.JsonObject;
import com.htl.jsf.Models.CityModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {


    @GET("City_list")
    Call<CityModel> get_citydata();

    @POST("register")
    @FormUrlEncoded
    Call<JsonObject> addGetOtp(@FieldMap Map<String, String> map);

    @POST("register")
    Call<JsonObject> addGetOtpfromRow(@Body JsonObject map);

    @POST("reg_otp_verify")
    Call<JsonObject> getRegister_verify(@Body JsonObject jsonObject);


    @POST("otp_verify")
    Call<JsonObject> getLogin_verify(@Body JsonObject jsonObject);


    @POST("login")
    Call<JsonObject> getlogin(@Body JsonObject jsonObject);

    @POST("event_list")
    Call<JsonObject> getevenList(@Body JsonObject jsonObject);

    @POST("shop_list")
    Call<JsonObject> getShopList(@Body JsonObject jsonObject);

    @POST("todays_event")
    Call<JsonObject> getToday_event(@Body JsonObject jsonObject);

    @POST("winner_list")
    Call<JsonObject> geWinner_list(@Body JsonObject jsonObject);

    @POST("partner")
    Call<JsonObject> getsponserPartnerList(@Body JsonObject jsonObject);


    @POST("coupons")
    Call<JsonObject> getCouponsList(@Body JsonObject jsonObject);

    @POST("apply_coupon")
    Call<JsonObject> getApplyCoupons(@Body JsonObject jsonObject);


    @POST("resendOtp")
    Call<JsonObject> getResend(@Body JsonObject jsonObject);

    @POST("resendOtpRegister")
    Call<JsonObject> getResendRegister(@Body JsonObject jsonObject);


    @POST("device_fcm")
    Call<JsonObject> getdevice(@Body JsonObject jsonObject);

  /*  @POST("auth/get-api-key")
    @FormUrlEncoded
    Call<Accesstoken> getaccessToken(@FieldMap Map<String, String> params);

    @POST("getVerifyOtp")
     @Multipart
     Call<LoginResponse> getVerifyOtpApi(@PartMap Map<String, RequestBody> params);

    @POST("getVerifyOtp")
    @Multipart
    Call<LoginResponse> getVerifyOtpApi(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part profile_picture);
*/

//    @POST("member_login")
//    @FormUrlEncoded
//    Call<LoginMemberResponse> getSignin(@FieldMap Map<String, String> params);
//
//    @POST("user_login")
//    @FormUrlEncoded
//    Call<JsonObject> getuserlogin(@FieldMap Map<String, String> params);
//
//    @POST("user_profile")
//    @FormUrlEncoded
//    Call<JsonObject> getuserprofile(@FieldMap Map<String, String> params);
//
//    @POST("user_registration")
//    @FormUrlEncoded
//    Call<UserRegistrationResponse> getregister_User(@FieldMap Map<String, String> params);
//
//
//    @POST("edit_user_profile")
//    @FormUrlEncoded
//    Call<UserRegistrationResponse> editprofileGetregister_User(@FieldMap Map<String, String> params);
//    @POST("member_registration")
//    @FormUrlEncoded
//    Call<UserRegistrationResponse> getMemberRegister(@FieldMap Map<String, String> params);
//
//    @POST("member_registration")
//    Call<UserRegistrationResponse> getmemberRegisteration(@Body RequestBody requestBody);
//
//
//    @POST("registration_verification")
//    @FormUrlEncoded
//    Call<JsonObject> getlogin_User(@FieldMap Map<String, String> params);
//
//    @POST("forget_password")
//    @FormUrlEncoded
//    Call<JsonObject> getforgotpassword(@FieldMap Map<String, String> params);
//
//    @POST("reset_password")
//    @FormUrlEncoded
//    Call<JsonObject> getresedtpassword(@FieldMap Map<String, String> params);
//
//
//    @POST("wallet")
//    @FormUrlEncoded
//    Call<WalletResponse> getwallet(@FieldMap Map<String, String> params);
//
//    @POST("resend_otp")
//    @FormUrlEncoded
//    Call<JsonObject> getresendotp(@FieldMap Map<String, String> params);
//
//
//    @POST("member_registration_verification")
//    @FormUrlEncoded
//    Call<LoginMemberResponse> getmember_registration_verification(@FieldMap Map<String, String> params);
//
//    @POST("forget_password_verification")
//    @FormUrlEncoded
//    Call<LoginMemberResponse> getforgotPasswordverification(@FieldMap Map<String, String> params);
//
//
//    @POST("edit_user_mobile_verification")
//    @FormUrlEncoded
//    Call<JsonObject> getEdituserMobileverification(@FieldMap Map<String, String> params);
//
//    @POST("member_list")
//    @FormUrlEncoded
//    Call<MembersListResponse> getmeberslist(@FieldMap Map<String, String> params);
//
//    @POST("banners_list")
//    @FormUrlEncoded
//    Call<BennerListResponse> getbennenrlist(@FieldMap Map<String, String> params);
//
//    @POST("most_liked_members_list")
//    @FormUrlEncoded
//    Call<MembersListResponse> getmostlikeMeberslist(@FieldMap Map<String, String> params);
//
//    @POST("most_liked_product_service_list")
//    @FormUrlEncoded
//    Call<MostLikedProducts_ServicesResponse> getMostLikeproduct_services_list(@FieldMap Map<String, String> params);
//
//    @POST("most_liked_product_service_list")
//    @FormUrlEncoded
//    Call<MostLikedProducts_ServicesResponse> getMostLike_product_offers_list(@FieldMap Map<String, String> params);
//
//
//    @POST("other_member_profile")
//    @FormUrlEncoded
//    Call<OtherMembersResponse> getOtherMember_profile(@FieldMap Map<String, String> params);
//
//    @POST("member_profile")
//    @FormUrlEncoded
//    Call<OtherMembersResponse> getMember_profile(@FieldMap Map<String, String> params);
//
//    @POST("user_profile")
//    @FormUrlEncoded
//    Call<OtherMembersResponse> getuser_profile(@FieldMap Map<String, String> params);
//
//    @POST("enquiry_product_service")
//    @FormUrlEncoded
//    Call<LikeResponse> getEnquirySerice(@FieldMap Map<String, String> params);
//
//    @POST("like_product_service")
//    @FormUrlEncoded
//    Call<LikeResponse> getLikeProduct(@FieldMap Map<String, String> params);
//
//    @POST("offers_list")
//    @FormUrlEncoded
//    Call<OffersResponse> getOfferslist(@FieldMap Map<String, String> params);
//
//    @POST("offer_purchase")
//    @FormUrlEncoded
//    Call<JsonObject> getOffersPurchase(@FieldMap Map<String, String> params);
//
//    @POST("product_service_detail")
//    @FormUrlEncoded
//    Call<ProductsDetailResponse> getProductServicesDetial(@FieldMap Map<String, String> params);
//
//    @POST("delete_product_service_image")
//    @FormUrlEncoded
//    Call<JsonObject> getProductServicesDeleteImage(@FieldMap Map<String, String> params);
//
//    @POST("delete_member_product_service")
//    @FormUrlEncoded
//    Call<JsonObject> getProductServicesDelete(@FieldMap Map<String, String> params);
//
//
//    @POST("add_product_service_images")
//    @FormUrlEncoded
//    Call<JsonObject> addProductServicesImages(@FieldMap Map<String, String> params);
//
//
//    @POST("product_service_detail")
//    @FormUrlEncoded
//    Call<JsonObject> getProductServicesDetailPro(@FieldMap Map<String, String> params);
//
//    @POST("edit_member_product_service")
//    @FormUrlEncoded
//    Call<JsonObject> getEditProductService(@FieldMap Map<String, String> params);
//
//    @POST("add_member_product_service")
//    @FormUrlEncoded
//    Call<JsonObject> getAddProductService(@FieldMap Map<String, String> params);
//
//    @POST("add_member_clients")
//    Call<JsonObject> getAddClientInfo(@Body RequestBody params);
//
//    @POST("add_member_awards")
//    Call<JsonObject> getAddAwadsInfo(@Body RequestBody params);
//
//    @POST("edit_member_awards")
//    Call<JsonObject> getUpdateAwardsInfo(@Body RequestBody params);
//
//    @POST("edit_member_client")
//    Call<JsonObject> getUpdateClientInfo(@Body RequestBody params);
//
//    @POST("product_service_list")
//    @FormUrlEncoded
//    Call<ProductsListResponse> getProductList(@FieldMap Map<String, String> params);
//
//    @POST("discover")
//    @FormUrlEncoded
//    Call<ProductsListResponse> getDiscoverList(@FieldMap Map<String, String> params);
//
//    //    @FormUrlEncoded
//    @Headers({"Content-Type: application/json;charset=UTF-8"})
//    @POST("edit_member_profile")
//    Call<JsonObject> updateProfile(@Body JSONObject requestBody);
//
//
//    @POST("edit_member_profile")
//    @FormUrlEncoded
//    Call<JsonObject> add_product_service(@FieldMap Map<String, String> map);
//
//    @POST("add_member_company_detail")
//    @FormUrlEncoded
//    Call<JsonObject> add_membercompanyDetial(@FieldMap Map<String, String> map);
//
//
//    @POST("add_member_company_detail")
//    Call<JsonObject> add_membercompanyDetial(@Body RequestBody params);
//
//
//
//    @POST("member_profile")
//    @FormUrlEncoded
//    Call<OtherMembersResponse> getMemberProfile(@FieldMap Map<String, String> map);


//
//    @POST("Userwebservice/register")
//    Call<JsonObject> signup(@Body JsonObject jsonObject);
//
//    @POST("Userwebservice/register")
//    @FormUrlEncoded
//    Call<JsonObject> signupmap(@FieldMap Map<String, String> jsonObject);
//
//
//    @POST("Userwebservice/forgotpasswordsendmail")
//    @FormUrlEncoded
//    Call<JsonObject> get_forgotpass(@FieldMap Map<String, String> params);
//
//    @POST("Userwebservice/login_check_id_basis")
//    @FormUrlEncoded
//    Call<LoginResponse> getUseId(@FieldMap Map<String, String> params);
//
////    @GET("Userwebservice/{login_check}")
////    Call<LoginResponse> getVin(@Path("login_check") String input);
//
//    @POST("Userwebservice/login_with_google")
//    @FormUrlEncoded
//    Call<LoginResponse> SocialUserlogin(@FieldMap Map<String, RequestBody> params);
////
////
////    @POST("auth/get-api-key")
////    @FormUrlEncoded
////    Call<SplashResponce> getsplash(@FieldMap Map<String, RequestBody> params);
//
//    @GET("get-countries")
//    Call<CountryResponce> getCountryList();
//
//    @POST("Userwebservice/forgotpasswordsendmail_otp_verify")
//    @FormUrlEncoded
//    Call<JsonObject> updatepassword(@FieldMap Map<String, String> map);
//
//    @POST("userwebservice/otp_send")
//    @FormUrlEncoded
//    Call<JsonObject> getotp(@FieldMap Map<String, String> map);
//
//    @POST("userwebservice/edit_profile")
//    @Multipart
//    Call<LoginResponse> getupdateProfile(@FieldMap Map<String, RequestBody> mapupdate, @Part MultipartBody.Part body);
//


//    @FormUrlEncoded
//    @POST("/edit_member_profile")
//    void add_product_service(
//            @Field("member_id")
//                    String member_id,
//            @Field("auth_key")
//                    String auth_key,
//            @Field("member_name")
//                    String member_name,
//            @Field("member_email")
//                    String member_email,
//            @Field("member_mobile")
//                    String member_mobile,
//            @Field("member_status")
//                    String member_status,
//            @Field("image")
//                    String image);


//    @POST("verify-for-signup-and-get-otp")
//    @FormUrlEncoded
//    Call<OtpResponse> getOtp(@FieldMap Map<String, RequestBody> params);


//    @POST("sign-up")
//    @FormUrlEncoded
//    Call<SignUpResponse> getSginup(@FieldMap Map<String, RequestBody> params);

//    @POST("get-user-profile")
//    @FormUrlEncoded
//    Call<GetProfileResponse> getuserProfile(@FieldMap Map<String, RequestBody> params);


//    @POST("update-profile")
//    @Multipart
//    Call<UpdateProfileResponse> updateprofile(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part profile_picture);


//    @POST("change-password")
//    @FormUrlEncoded
//    Call<ChangePasswordResponse> changepassword(@FieldMap Map<String, RequestBody> params);


//    @POST("sign-up")
//    @FormUrlEncoded
//    Call<String> getSginup(@FieldMap Map<String, RequestBody> params);


//    @GET("getProfile/{user_id}")
//    Call<SignINResponce> getProfile(@Path("user_id") String userid);
//
//
//    @POST("logout")
//    @FormUrlEncoded
//    Call<LogoutResponse> logout(@FieldMap Map<String, Object> params);
//
//
//    @POST("update-password-by-phone-number")
//    @FormUrlEncoded
//    Call<ForgotResponse> resetpassword(@FieldMap Map<String, RequestBody> params);
//
//
//    @POST("checkuser")
//    @FormUrlEncoded
//    Call<CheckUserResponse> chekc_user(@FieldMap Map<String, Object> params);



   /* @POST("getLogin")
    @FormUrlEncoded
    Call<LoginResponse> getLogin(@FieldMap Map<String, String> params);




    @POST("updateProfile")
    @Multipart
    Call<ProfileResponse> updateProfile(@PartMap Map<String, RequestBody> params, @Part MultipartBody.Part profile_picture);


    @POST("updateProfile")
    @Multipart
    Call<ProfileResponse> updateProfile(@PartMap Map<String, RequestBody> params);


    @POST("forgotpassword")
    @FormUrlEncoded
    Call<Basic_Response> forgot(@FieldMap Map<String, Object> params);

    @POST("changePassword")
    @FormUrlEncoded
    Call<Basic_Response> changePassword(@FieldMap Map<String, Object> params);

    @POST("getCategoryList")
    @FormUrlEncoded
    Call<CategoryList> getCategory(@FieldMap Map<String, Object> params);

    @POST("getMenuList")
    @FormUrlEncoded
    Call<MenuList> getMenuList(@FieldMap Map<String, Object> params);

    @POST("getMenuList")
    @FormUrlEncoded
    Call<OfferThemeResponse> getOfferTheme(@FieldMap Map<String, Object> params);

    @POST("getSubmenuLIst")
    @FormUrlEncoded
    Call<SubMenuResponse> getSubmenuLIst(@FieldMap Map<String, Object> params);



    @POST("getAppetizer")
    @FormUrlEncoded
    Call<AppetizerResponse> getAppetizerList(@FieldMap Map<String, Object> params);


    @POST("getTableList")
    @FormUrlEncoded
    Call<TableListResponse> getTableList(@FieldMap Map<String, Object> params);


    @POST("tableStatus")
    @FormUrlEncoded
    Call<Basic_Response> tableStatus(@FieldMap Map<String, Object> params);

    @POST("getdiscounts")
    @FormUrlEncoded
    Call<CalculationDiscount> getRestaurantDiscountList(@FieldMap Map<String, Object> params);

    @POST("getAppetizerdetail")
    @FormUrlEncoded
    Call<AppetizerdetailResponse> getAppetizerdetail(@FieldMap Map<String, Object> params);

    @POST("getSubmenuLIstInAlcohol")
    @FormUrlEncoded
    Call<SubMenuResponse> getSubmenuLIstInAlcohol(@FieldMap Map<String, Object> params);

    @POST("getCheckout")
    @FormUrlEncoded
    Call<Basic_Response> getCheckout_Detauil(@FieldMap Map<String, Object> params);

    @POST("getMyOrders")
    @FormUrlEncoded
    Call<MyOrderResponse> getMyOrders(@FieldMap Map<String, Object> params);

    @POST("getAllWaiterRelatedToOrder")
    @FormUrlEncoded

    Call<WaiterListResponse> getAllWaiterRelatedToOrder(@FieldMap Map<String, Object> params);

    @POST("addWaiterTip")
    @FormUrlEncoded
    Call<Basic_Response> addWaiterTip(@FieldMap Map<String, Object> params);


    @GET("getLoyaltyPoint/{user_id}")
    Call<LoyaltyPointsResponse> getLoyaltyPoint(@Path("user_id") String userid);


    @POST("getRatingTypes")
    @FormUrlEncoded
    Call<FeedbackResponse> getRatingTypes(@FieldMap Map<String, Object> params);


    @POST("setRatingByUser")
    @FormUrlEncoded
    Call<Basic_Response> setRatingByUser(@FieldMap Map<String, Object> params);


    @POST("getNotification")
    @FormUrlEncoded
    Call<NotificationResponse> getNotification(@FieldMap Map<String, Object> params);

    @POST("setNotificationSeen")
    @FormUrlEncoded
    Call<Basic_Response> setNotificationSeen(@FieldMap Map<String, Object> params);

    @POST("LipaNaMpesaOnlineBrew")
    @FormUrlEncoded
    Call<MpesaResponse> mpessapi(@FieldMap Map<String, Object> params);

    *//*@GET("searchItem")
    Call<String> getSearchitem(@Query("user_id") String userid, @Query("search_text") String search_text);
*//*

    @POST("searchItem")
    @FormUrlEncoded
    Call<SearchResponse> getSearchitem(@FieldMap Map<String, Object> params);*/

    /*@GET("getProfile")
    Call<String> getGulfProfile(@QueryMap Map <String, Object>params);



*/


}
