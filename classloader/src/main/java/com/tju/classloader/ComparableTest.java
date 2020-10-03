package com.tju.classloader;

import org.objectweb.asm.ClassWriter;
import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class ComparableTest {

    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
         "com/tju/classloader/Comparable", null, "java/lang/Object", new String[] {""}); 
        /**visitEnd()方法立即返回FieldVisitor */  
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I", null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I", null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I", null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "compareTo", "(Ljava/lang/object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();
        //Class c = MyClassLoader.defineClass("com.tju.classloader.Comparable",b);
    }
}
