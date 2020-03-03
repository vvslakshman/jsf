package com.htl.jsf.ApiRequest;

/**
 * Created by user102 on 3/5/18.
 */

public class ApiClass {

    //    public static String BASE_URL = "https://ss.roketvending.com/jsf/index.php/Jsf_Api/";
    public static String BASE_URL = "https://ss.roketvending.com/jsfnew/index.php/Jsf_Api/";

    public static String IMAGE_URL = "assets/users/images/";
    private static ApiClass mApiClass;
    public static final String AUTHORIZATION = "Authorization";
    public static final String ID = "id";
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String DeviceType = "device_type";
    public static final String DeviceID = "device_id";
    public static final String FIRSTNAME = "first_name";
    public static final String LASTNAME = "last_name";
    public static final String NAME = "name";
    public static final String USER_NAME = "username";
    public static final String COUNTRY_ID = "country_id";
    public static final String COUNTRY = "country_name";
    public static final String COUNTRYCODE = "country_code";
    public static final String PICTURE = "picture";

    public static final String COUNTRYNAME = "name";
    public static final String MOBILE = "mobile";
    public static final String STATUS = "status";
    public static final String CITY_ID = "city_id";


    public static final String MEMBER_MOBILE = "member_mobile";
    public static final String MEMBER_EMAIL = "member_email";
    public static final String MEMBER_NAME = "member_name";
    public static final String MEMBER_STATUS = "member_status";
    public static final String IMAGE = "image";
    public static final String IMAGE_ID = "image_id";
    public static final String AUTH_KEY = "auth_key";
    public static final String SHOP_ID = "shop_id";
    public static final String COUPON_CODE = "coupon_code";
    public static final String PRICE_TYPE_ID = "price_type_id";
    public static final String COMPANY_TYPE_ID = "company_type_id";
    public static final String ENQUIRY = "enquiry";
    public static final String DETAIL = "detail";
    public static final String PRICE = "price";
    public static final String PRICERANGE = "Price Range";
    public static final String PRICE_OTHER = "Other";
    public static final String CLIENT = "client";
    public static final String CLIENT_ID = "client_id";
    public static final String AWARD_ID = "awards_id";


    public static final String CLIENT_DETAIL = "client_detail";
    public static final String AWARD_DETAIL = "award_detail";


    public static final String OTP = "otp";
    public static final String DESCRIPTION = "description";


    public static final String SOCIAL_ID = "social_id";
    public static final String SOCIAL_TYPE = "social_type";
    public static final String CURRENT_PASSWORD = "currentPassword";
    public static final String NEW_PASSWORD = "newPassword";
    public static final String CONFIRM_NEW_PASSWORD = "confirmNewPassword";
    public static final String DISPLAYNAME = "display_name";
    public static final String DOB = "dob";
    public static final String PICTURE_LOGO = "picture_logo";
    public static final String BANNER = "banner";
    public static final String Type = "type";
    public static final String MEMBER_ID = "member_id";
    public static final String OTHER_MEMBER_ID = "other_member_id";
    public static final String PRODUCT_SERVICE_ID = "product_service_id";
    public static final String KEYWORD = "keyword";
    public static final String COMINGFROM = "ComingFrom";


    public static ApiClass getmApiClass() {
        if (mApiClass == null) {
            mApiClass = new ApiClass();
        }
        return mApiClass;
    }

}
