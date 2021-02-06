package Instrument;

import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MethodVisitor extends AdviceAdapter implements Opcodes {

    /**
     *
     * @param api asm实现版本
     * @param mv 方法访问
     * @param access 访问标志
     * @param name 方法名称
     * @param desc 方法修饰符
     */
    protected MethodVisitor(int api, MethodVisitor mv, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
    }

    /**
     *
     * @param opcode 操作码
     * @param owner 方法所在类
     * @param name 方法名称
     * @param desc 修饰符
     */
    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        System.out.println("opcode:"+opcode);
        System.out.println("owner:"+owner);
        mv.visitFieldInsn(opcode, owner, name, desc);
    }
}
