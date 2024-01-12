package project.backoffice.helper;

import project.backoffice.exception.MessageExceptionEnum;

public class StringHelper {
    public static String format(MessageExceptionEnum messageExceptionEnum, Object... args) {
        return String.format(messageExceptionEnum.getMessage() , args);
    }
}
