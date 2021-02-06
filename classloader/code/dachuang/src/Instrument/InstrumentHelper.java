package Instrument;

import test.runtime.JLogger;

public class InstrumentHelper {
    public static String CLASSRECIVER = JLogger.class.getName().replace(".","/");
    public static String LOGFILEACCESS="logFileAccess";
    public static String LOGMETHODACCESS = "logMethodAccess";

    public static String STRING4_INT1="(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V";
    public static String V_V = "()V";
}
