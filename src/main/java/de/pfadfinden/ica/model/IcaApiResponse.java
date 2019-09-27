package de.pfadfinden.ica.model;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class IcaApiResponse<ResponseT extends IcaResponse> {
    private String servicePrefix;
    private String methodCall;
    private int statusCode;
    private String statusMessage;
    private int minorNumber;
    private int majorNumber;
    private String apiSessionName;
    private String apiSessionToken;
    private ResponseT response;

    public String getServicePrefix() {
        return servicePrefix;
    }

    public void setServicePrefix(String servicePrefix) {
        this.servicePrefix = servicePrefix;
    }

    public String getMethodCall() {
        return methodCall;
    }

    public void setMethodCall(String methodCall) {
        this.methodCall = methodCall;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getMinorNumber() {
        return minorNumber;
    }

    public void setMinorNumber(int minorNumber) {
        this.minorNumber = minorNumber;
    }

    public int getMajorNumber() {
        return majorNumber;
    }

    public void setMajorNumber(int majorNumber) {
        this.majorNumber = majorNumber;
    }

    public String getApiSessionName() {
        return apiSessionName;
    }

    public void setApiSessionName(String apiSessionName) {
        this.apiSessionName = apiSessionName;
    }

    public String getApiSessionToken() {
        return apiSessionToken;
    }

    public void setApiSessionToken(String apiSessionToken) {
        this.apiSessionToken = apiSessionToken;
    }

    public ResponseT getResponse() {
        return response;
    }

    public void setResponse(ResponseT response) {
        this.response = response;
    }

    public static Type getType(final Type responseT) {
        Type type = new ParameterizedType() {

            @Override
            public Type getRawType() {
                return IcaApiResponse.class;
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
