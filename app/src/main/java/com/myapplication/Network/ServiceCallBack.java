package com.myapplication.Network;


import retrofit2.Response;

/**
 * Created by  on 10/1/2015.
 */
public interface ServiceCallBack
{
    /**
     * On success.
     *
     * @param tag          the tag
     * @param baseResponse the base response
     */
    public void onSuccess(int tag, Response<BaseResponse> baseResponse);

    /**
     * On fail.
     *  @param t   the t
     * @param tag the tag
     * @param msg
     */
    public void onFail(Throwable t, int tag, String msg);
}
