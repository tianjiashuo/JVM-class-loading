package test.runtime;

/**
 * JLogger类，记录日志，插入方法
 * JLogger.method();
 */
public class JLogger {
    public static void logFileAccess(String className,String methodName,String opcode,String fileName,int lineNumber){

    }

    /**
     * 答应栈帧信息
     */
    public static void logMethodAccess(){
        String result = null;
        StackTraceElement[] st = Thread.currentThread().getStackTrace();

        if (st == null) {
            System.out.println("无栈...");
            return;
        }
        StringBuffer sbf = new StringBuffer();
        for (StackTraceElement e : st) {
            if (sbf.length() > 0) {
                sbf.append(" <- ");
            }
            if ((!(e.toString().contains("StackTrace"))) && (!(e.toString().contains("printTrack"))) && (!(e.toString().contains("JLogger")))){
                    sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}", e.getClassName(), e.getMethodName(),e.getLineNumber()));
            }
        }
        result = sbf.toString();
        /**
         * 测试输出，以后改为return result；
         */
        System.out.println(result);
    }
}
