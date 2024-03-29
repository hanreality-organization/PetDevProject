package com.punuo.sys.sip.service;

import android.text.TextUtils;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.punuo.sys.sdk.util.HandlerExceptionUtils;
import com.punuo.sys.sip.model.VideoData;
import com.punuo.sys.sip.request.BaseSipRequest;
import com.punuo.sys.sip.video.H264Config;

import org.greenrobot.eventbus.EventBus;
import org.zoolu.sip.message.Message;

/**
 * Created by han.chen.
 * Date on 2019-10-17.
 **/
@Route(path = ServicePath.PATH_START_VIDEO)
public class StartVideoService extends NormalRequestService<VideoData> {
    @Override
    protected String getBody() {
        return null;
    }

    @Override
    protected void onSuccess(Message msg, VideoData result) {
        if (result == null) {
            return;
        }
        onResponse(msg);
        H264Config.RTMP_STREAM = TextUtils.isEmpty(result.mVideoUrl) ? H264Config.RTMP_STREAM : result.mVideoUrl;
        EventBus.getDefault().post(result);
    }

    @Override
    protected void onError(Exception e) {
        HandlerExceptionUtils.handleException(e);
    }

    @Override
    public void handleTimeOut(BaseSipRequest baseSipRequest) {

    }
}
