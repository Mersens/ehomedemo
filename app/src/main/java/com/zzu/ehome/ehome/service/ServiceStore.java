package com.zzu.ehome.ehome.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Mersens on 2016/10/31.
 */

public interface ServiceStore {

    @Headers({
            "Content-Type: text/xml; charset=utf-8",
            "SOAPAction: http://tempuri.org/HospitalInquiryByTopmd"
    })
    @POST("WebServices/EhomeWebservice.asmx")
    Call<ResponseBody> getHospitalInfo(@retrofit2.http.Body String str);
}
