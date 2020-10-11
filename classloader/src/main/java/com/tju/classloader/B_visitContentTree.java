package com.tju.classloader;

import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

public class B_visitContentTree extends ClassLoader{
    public void visitByTreeAPI() throws Exception {
        String ApplicationClass = "com.tju.classloader.Application";
        ClassReader cr = new ClassReader(ApplicationClass);
        // 使用 new ClassWriter(0) 时，不会自动计算任何东西。必须自行计算帧、局部变量与操作数栈的大小
        ClassNode cn = new ClassNode();
        /*
            ClassReader.SKIP_DEBUG 跳过类文件中的调试信息，比如行号信息（LineNumberTable）
            ClassReader.SKIP_CODE 跳过方法体中的Code属性，比如（方法字节码、异常表等信息）
            ClassReader.SKIP_DEBUG 展开StackMapTable属性
            ClassReader.SKIP_DEBUG 跳过StackMapTable属性
         */
        cr.accept(cn, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG);
        List<FieldNode> fields = cn.fields;

        for (int i = 0; i <fields.size() ; i++) {
            FieldNode fn = fields.get(i);
            System.out.println("field in visitor: " + fn.name);

        }

        List<MethodNode> methods = cn.methods;
        for (int i = 0; i <methods.size() ; i++) {
            MethodNode mn = methods.get(i);
            System.out.println("method in visitor: " + mn.name);
        }
        // 写，非必要
        ClassWriter cw = new ClassWriter(0);
        cr.accept(cw,0);
        byte[] bytesModified = cw.toByteArray();
    }

    public static void main(String[] args) throws Exception{
        new B_visitContentTree().visitByTreeAPI();
    }
}
