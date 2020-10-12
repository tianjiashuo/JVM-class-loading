package com.tju.classloader;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;


/**
 *  去除一个方法或者一个属性
 */
public class RemoveMethodAdapter extends ClassVisitor{
    private String mName;
    private String mDesc;

    public RemoveMethodAdapter(ClassVisitor cv, String mName, String mDesc){
        super(ASM4, cv);
        this.mName = mName;
        this.mDesc = mDesc;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature,
            String[] exceptions) {
        if (name.equals(mName) && descriptor.equals(mDesc)) {
            return null;
        }
        return cv.visitMethod(access, name, descriptor, signature, exceptions);
    }
}
