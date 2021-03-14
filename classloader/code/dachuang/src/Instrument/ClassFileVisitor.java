package Instrument;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * ClassFileVisitor是写class文件的类
 */
public class ClassFileVisitor extends ClassVisitor {
    public ClassFileVisitor(ClassVisitor cv){
        super(Opcodes.ASM5,cv);
    }
    public static String className;

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        className = name;
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        /**
         * TODO:这里判断记录各种类型的Field
         */
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        /**
         * 选用自己的方法访问类MethodBodyVisit
         */
        mv = new MethodBodyVisit(mv,className,access,name,desc);
        return mv;
    }

}
