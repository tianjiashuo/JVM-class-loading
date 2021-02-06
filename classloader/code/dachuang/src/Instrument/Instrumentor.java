package Instrument;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public  class Instrumentor {
    private static int a = 0;

    public static boolean shouldInstrument(String className){
        if(className.contains("test"))
            return true;
        else
            return false;
    }

    public static void printTrack() {
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
            if ((!(e.toString().contains("StackTrace"))) && (!(e.toString().contains("printTrack")))){
                sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}", e.getClassName(), e.getMethodName(),
                        e.getLineNumber()));
            }
        }
        result = sbf.toString();
        System.out.println(result);
    }

    public static void premain(String agentArgs, Instrumentation inst){
        StringBuffer realString = new StringBuffer("");
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if(shouldInstrument(className)){

                    System.out.println("IN INSTRUMENTOR BEGIN");

                    System.out.println("classname:"+className);
                    ClassReader classReader = new ClassReader(classfileBuffer);
                    ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES);
                    ClassFileVisitor classVisitor = new ClassFileVisitor(classWriter);//绑定
                    classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);

                    classfileBuffer = classWriter.toByteArray();
                    System.out.println("IN INSTRUMENTOR END");


                    //保存插桩后文件
                    File file = new File("./src/genClasses/" + className.replace("/",".") + ".class");
                    FileOutputStream fOutputStream;
                    try {
                        fOutputStream = new FileOutputStream(file);
                        fOutputStream.write(classfileBuffer);
                        fOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                return classfileBuffer;
            }
        });
    }
}
