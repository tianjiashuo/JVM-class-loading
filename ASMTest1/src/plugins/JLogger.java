package plugins;

public class JLogger {
    public static void LogFieldAccess(String className,String methodName,String opcode, String fieldName, int lineNumber){
        System.out.println("LogFieldAccess" );
    }
}
