package com.addressbook.customexception;

public class AddressBookCustomException extends  Exception {

    public enum ExceptionType {
        NO_SUCH_FILE,NO_SUCH_DATA,NO_SUCH_FIELD
    }

    public ExceptionType type;

    public AddressBookCustomException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public AddressBookCustomException(ExceptionType type, Throwable cause) {
        super(cause);
        this.type = type;
    }

    public AddressBookCustomException(ExceptionType type, String message, Throwable cause) {
        super(message, cause);
        this.type = type;
    }
}
