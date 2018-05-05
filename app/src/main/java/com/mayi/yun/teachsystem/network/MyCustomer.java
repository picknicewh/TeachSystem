package com.mayi.yun.teachsystem.network;

import com.mayi.yun.teachsystem.bean.Common;

import io.reactivex.functions.Consumer;

/**
 * 作者： wh
 * 时间：  2018/2/28
 * 名称：
 * 版本说明：
 * 附加注释：
 * 主要接口：
 */
public class MyCustomer<T> implements Consumer<T> {
    public CallBack callBack;

    public MyCustomer(CallBack callBack) {
        this.callBack = callBack;
    }


    @Override
    public void accept(T t) throws Exception {
        if (t instanceof Common) {
            Common common = (Common) t;
            if (!common.getCode() .equals("200")) {
                callBack.getErrorMessage(common.getDesc());
            } else {
                if (common.getData() instanceof String) {
                    callBack.setResult(common.getDesc());
                }
                if (common.getData()==null){
                    callBack.getErrorMessage(common.getDesc());
                }else {
                    callBack.setResult(common.getData());
                }
            }
        }

    }

    public interface CallBack {
        void getErrorMessage(String message);

        void setResult(Object t);
    }
}