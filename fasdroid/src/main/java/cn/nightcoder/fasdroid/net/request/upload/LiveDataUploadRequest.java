package cn.nightcoder.fasdroid.net.request.upload;

import androidx.lifecycle.LifecycleOwner;

import java.io.File;
import java.util.HashMap;

import cn.nightcoder.fasdroid.net.callback.UploadCallback;
import cn.nightcoder.fasdroid.net.observer.LiveDataUploadObserver;
import cn.nightcoder.fasdroid.net.request.service.ILiveDataService;
import cn.nightcoder.fasdroid.okhttp.callAdapter.livedata.LiveDataCallAdapterFactory;

/**
 * Created by xuedakun on 2019/12/13 16:53
 * 上传实现，使用multipart/form-data表单上传，可携带参数，post请求。
 *
 * @version : v1.0
 * @project : fasdroid
 * @Email : dakun611@Gmail.com
 */
public abstract class LiveDataUploadRequest extends AbsUploadRequest<ILiveDataService> {

    protected LiveDataUploadRequest() {
        super(ILiveDataService.class, new LiveDataCallAdapterFactory());
    }

    public <T> void upload(LifecycleOwner lifecycleOwner, String fileKey, File file, HashMap<String, Object> params, Class<T> clazz, UploadCallback<T> uploadCallback) {
        service.doUpload(getUploadBodyPart(fileKey, file), params).observe(lifecycleOwner, observer(uploadCallback, clazz));
    }

    protected abstract LiveDataUploadObserver<?> observer(UploadCallback uploadCallback, Class clazz);
}