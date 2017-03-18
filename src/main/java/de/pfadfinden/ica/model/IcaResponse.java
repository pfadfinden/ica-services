package de.pfadfinden.ica.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class IcaResponse<DataT> {
    private boolean success;
    private String responseType;
    private String message;
    private String title;
    private DataT data;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DataT getData() {
        return data;
    }

    public void setData(DataT data) {
        this.data = data;
    }

    public static Type getType(final Type responseT) {
        Type type = new ParameterizedType() {

            @Override
            public Type getRawType() {
                return IcaResponse.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }

            @Override
            public Type[] getActualTypeArguments() {
                return new Type[] {responseT};
            }
        };
        return type;
    }

}
