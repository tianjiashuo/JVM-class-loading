package Instrument;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

public class ClassFileVisitor extends ClassVisitor {
    public static String className;
    public ClassFileVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);

//        System.out.println("version:" + version);
//        System.out.println("access:" + access);
//        System.out.println("signature:" + signature);
//        System.out.println("superName:" + superName);
//        System.out.println("interfaces:" + interfaces);
        this.className = name;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {

//        System.out.println("version:" + version);
//        System.out.println("access:" + access);
//        System.out.println("name:" + name);
//        System.out.println("desc:" + desc);
//        System.out.println("signature:" + signature);
//        System.out.println("value" + value);
//        System.out.println("superName:" + superName);
//        System.out.println("interfaces:" + interfaces);
        return super.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        mv = new MethodBodyVisitor(mv,className, access, name, desc);
        return mv;
    }
}
