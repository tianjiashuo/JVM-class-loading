package Instrument;

import plugins.JLogger;

public class InstrumentHelper {

    public static String LOGFIELDACCESS = "LogFieldAccess";
    public static String CLASSRECEIVER = JLogger.class.getName().replace(".","/");
    public static String STRING_INI_V = "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V";
}
