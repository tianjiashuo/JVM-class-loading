package loadFromLocal;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class loadFromLocal {

    public static void main(String[] args) throws IOException {
        //ClassLoader classLoader = new ClassLoader() {"asm.B"};
        ClassReader classReader = new ClassReader("asm.add");
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES);//解析
        TraceClassVisitor classVisitor =new TraceClassVisitor(classWriter,new PrintWriter(System.out));//绑定
        classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);
    }
}
