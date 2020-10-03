package com.tju.classloader;

import org.objectweb.asm.*;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

public class B_visitContent extends ClassLoader {

    public void visitByCoreAPI() throws Exception {
        String ApplicationClass = "com.tju.classloader.Application";
        ClassReader cr = new ClassReader(ApplicationClass);
        // 使用 new ClassWriter(0) 时，不会自动计算任何东西。必须自行计算帧、局部变量与操作数栈的大小
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM5, cw) {
            @Override
            public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
                System.out.println("field in visitor: " + name);
                return super.visitField(access, name, descriptor, signature, value);
            }

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                System.out.println("method in visitor: " + name);
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };

        /*
            ClassReader.SKIP_DEBUG 跳过类文件中的调试信息，比如行号信息（LineNumberTable）
            ClassReader.SKIP_CODE 跳过方法体中的Code属性，比如（方法字节码、异常表等信息）
            ClassReader.SKIP_DEBUG 展开StackMapTable属性
            ClassReader.SKIP_DEBUG 跳过StackMapTable属性

         */
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
    }
    
    public static void main(String[] args) throws Exception{
        new B_visitContent().visitByCoreAPI();

    }
    
}
