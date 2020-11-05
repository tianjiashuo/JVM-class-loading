package loadFromLocal;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class loadFromLocal {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("test.Person");
        ClassWriter classWriter = new ClassWriter(classReader,ClassWriter.COMPUTE_FRAMES);
        TraceClassVisitor classVisitor = new TraceClassVisitor(classWriter,new PrintWriter(System.out));
        classReader.accept(classVisitor,ClassReader.EXPAND_FRAMES);
    }
}
