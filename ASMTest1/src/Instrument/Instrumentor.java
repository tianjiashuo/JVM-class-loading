package Instrument;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class Instrumentor {

    public static boolean shouldInstrument(String classname){
        if(classname.contains("test")){
            return true;
        }else {
            return false;
        }
    }

    public static void premain(String agentArgs, Instrumentation inst){
        System.out.println("premain");
        inst.addTransformer(new ClassFileTransformer() {
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                if (shouldInstrument(className)){
                    System.out.println("ClassName:" + className);

                    ClassReader classReader = new ClassReader(classfileBuffer);
                    ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES);

//                    TraceClassVisitor classVisitor = new TraceClassVisitor(classWriter,new PrintWriter(System.out));
//                    classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);

                    ClassFileVisitor cfv = new ClassFileVisitor(classWriter);
                    classReader.accept(cfv,ClassReader.EXPAND_FRAMES);


                    classfileBuffer = classWriter.toByteArray();

                    //保存插桩后文件
                    File file = new File("./src/test/java/genClasses/" + className.replace("/",".") + ".class");
                    FileOutputStream fOutputStream;
                    try {
                        fOutputStream = new FileOutputStream(file);
                        fOutputStream.write(classfileBuffer);
                        fOutputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                return classfileBuffer;
            }
        });
    }

}
