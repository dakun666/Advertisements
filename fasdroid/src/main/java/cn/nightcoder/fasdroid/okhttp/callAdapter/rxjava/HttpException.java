package cn.nightcoder.fasdroid.okhttp.callAdapter.rxjava;

import retrofit2.Response;

/** @deprecated Use {@link retrofit2.HttpException}. */
@Deprecated
public final class HttpException extends retrofit2.HttpException {
    public HttpException(Response<?> response) {
        super(response);
    }
}