package project.backoffice.Helper;

import project.backoffice.Exception.MessageExceptionEnum;

public class StringHelper {
    public static String format(MessageExceptionEnum messageExceptionEnum, Object... args) {
        return String.format(messageExceptionEnum.getMessage() , args);
    }
}
