package Instrument;


import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MethodVistor extends AdviceAdapter implements Opcodes {

    protected MethodVistor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
    }

    @Override//opcode 字节码指令 owner:类名
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//        System.out.println("opcode:"+opcode);
//        System.out.println("owner:"+owner);
        mv.visitFieldInsn(opcode, owner, name, desc);
    }
}
