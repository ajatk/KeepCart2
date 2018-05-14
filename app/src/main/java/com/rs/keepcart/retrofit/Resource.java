package com.rs.keepcart.retrofit;

import android.support.annotation.Nullable;

import com.rs.keepcart.utills.ApplicationConstants;


public class Resource<T> {
    public final int status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    private Resource(int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(T data, String msg) {
        return new Resource<>(ApplicationConstants.HTTP_SUCCESS, data, msg);
    }

    public static <T> Resource<T> error(int status, String msg) {
        return new Resource<>(status, null, msg);
    }

    public static <T> Resource<T> loading() {
        return new Resource<>(ApplicationConstants.LOADING, null, null);
    }

    public static <T> Resource<T> transfer(int status, T data, String msg) {
        return new Resource<>(status, data, msg);
    }

    public static <T> Resource<T> message(String msg) {
        return new Resource<>(ApplicationConstants.SHOW_ONLY_MSG, null, msg);
    }


}
