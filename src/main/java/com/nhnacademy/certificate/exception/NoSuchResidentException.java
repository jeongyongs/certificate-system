package com.nhnacademy.certificate.exception;

public class NoSuchResidentException extends RuntimeException {
    public NoSuchResidentException(Integer residentSerialNumber) {
        super("존재하지 않는 주민입니다.(일련번호:" + residentSerialNumber + ")");
    }
}
