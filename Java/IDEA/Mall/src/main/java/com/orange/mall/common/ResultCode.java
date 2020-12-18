package com.orange.mall.common;

/**
 * ���󷵻���ö�ٻ���
 * Create by xxkun on 2020/12/17
 */
public enum ResultCode implements IResultCode {

    SUCCESS(200, "����ɹ�"),
    FAILED(500, "����ʧ��"),
    VALIDATE_FAILED(404, "��������ʧ��"),
    UNAUTHORIZED(401, "��δ��¼��token�Ѿ�����"),
    FORBIDDEN(403, "û�����Ȩ��");;

    private final long code;
    private final String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
