package com.tju.classloader;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

import java.io.IOException;
import java.util.Scanner;

public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        System.out.println(name + " extends " + superName + " {");
    }

    @Override
    public void visitSource(String source, String debug) {

    }

    @Override
    public void visitOuterClass(String owner, String name, String descriptor) {

    }

    @Override
    public AnnotationVisitor visitAnnotation(String descriptor, boolean visible) {
        return null;
    }

    @Override
    public void visitAttribute(Attribute attribute) {

    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        System.out.println("   " + descriptor + " " + name);
        // FieldVisitor fv = cv.visitField(ACC_PUBLIC, "a", "int", null, 0);
        // if (fv != null) {
        //     System.out.println("有");
        //     fv.visitEnd();
        // }
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        System.out.println(" " + name + descriptor);
        return null;
    }

    @Override
    public void visitEnd() {
        System.out.println("}");
    }

    public static void main(String[] args) throws IOException {
        String ClassFile = "";
        System.out.println("Please input your .class file fullqualified name:");
        Scanner input = new Scanner(System.in);
        ClassFile = input.next();
        input.close();
        ClassPrinter cp = new ClassPrinter();
        ClassReader cr = new ClassReader(ClassFile);
        cr.accept(cp, 0);
    }
}
