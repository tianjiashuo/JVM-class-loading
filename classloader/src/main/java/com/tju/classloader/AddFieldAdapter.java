package com.tju.classloader;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class AddFieldAdapter extends ClassVisitor{
    private int fAcc;
    private String fName;
    private String fDesc;
    private boolean isFieldPresent;


    public AddFieldAdapter(ClassVisitor cv, int fAcc, String fName, String fDesc) {
        super(ASM4, cv);
        this.fAcc = fAcc;
        this.fName = fName;
        this.fDesc = fDesc;
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor, String signature, Object value) {
        if (name.equals(fName)) {
            isFieldPresent = true;
        }
        return super.visitField(access, name, descriptor, signature, value);
    }

    @Override
    public void visitEnd() {
        if (!isFieldPresent) {
            FieldVisitor fv = cv.visitField(fAcc, fName, fDesc, null, null);
            if (fv != null) {
                fv.visitEnd();
            }
        }
        cv.visitEnd();
    }

    
}
