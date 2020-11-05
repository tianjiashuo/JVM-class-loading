package Instrument;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public  class Instrumentor {
    public static boolean shouldInstrument(String className){
        if(className.contains("test"))
            return true;
        else
            return false;
    }
    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("premain");//热部署
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
//                System.out.println("classname:"+className);
                if(shouldInstrument(className)){
                    System.out.println("IN INSTRUMENTOR BEGIN");

                    System.out.println("classname:"+className);
                    ClassReader classReader = new ClassReader(classfileBuffer);
                    ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES);
//                    TraceClassVisitor classVisitor =new TraceClassVisitor(classWriter,new PrintWriter(System.out));//绑定
//                    classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);
                    ClassFileVIstor classVisitor =new ClassFileVIstor(classWriter);//绑定
                    classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);
                    //下面是对比后新增
//                    classfileBuffer = classWriter.toByteArray();
                    System.out.println("IN INSTRUMENTOR END");
//                    System.out.println(classVisitor.getField_MethodInfor().toString());
                    BufferedWriter out = null;
                    try {
                        out = new BufferedWriter(new FileWriter("E:\\大创\\runoob.txt"));
                        out.write(classVisitor.getField_MethodInfor().toString());
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                //保存插桩后文件
                File file = new File("./src/genClasses/" + className.replace("/",".") + ".class");
                FileOutputStream fOutputStream;
                try {
                    fOutputStream = new FileOutputStream(file);
                    fOutputStream.write(classfileBuffer);
                    fOutputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return classfileBuffer;
            }
        });
    }
}
