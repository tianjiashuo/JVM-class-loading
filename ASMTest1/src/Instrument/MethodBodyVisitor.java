package Instrument;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MethodBodyVisitor extends AdviceAdapter implements Opcodes {
    public static String className;
    public static String methodName;
    private boolean isInit;
    private int lineNumber;

    private int maxIndex;

    protected MethodBodyVisitor(MethodVisitor mv,String className, int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);

        this.className = className;
        this.methodName = name;

        if(methodName.equals("<init") || methodName.equals("<clinit>")){
            isInit = true;
        }

        maxIndex = Type.getArgumentsAndReturnSizes(desc) + 1;
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        this.lineNumber = line;
        mv.visitLineNumber(line, start);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//        System.out.println("opcode" + opcode);
//        System.out.println("owner" + owner);
//        System.out.println("name" + name);
//        System.out.println("desc" + desc);

        switch (opcode){
            case GETFIELD:
                if(!isInit){
                    //String className,String methodName,String opcode, String fieldName, int lineNumber
//                    mv.visitLdcInsn(className);
//                    mv.visitLdcInsn(methodName);
//                    mv.visitLdcInsn("GETFIELD");
//                    mv.visitLdcInsn(name);
//                    mv.visitLdcInsn(new Integer(lineNumber));
//
//                    mv.visitMethodInsn(INVOKESTATIC,
//                            InstrumentHelper.CLASSRECEIVER,
//                            InstrumentHelper.LOGFIELDACCESS,
//                            InstrumentHelper.STRING_INI_V);
                    InsertLogCode(mv,className,methodName,"GETFIELD",name,lineNumber);
                    mv.visitFieldInsn(opcode, owner, name, desc);
                }else {
                    mv.visitFieldInsn(opcode, owner, name, desc);
                }
                //mv.visitFieldInsn(opcode, owner, name, desc);
                break;
            case GETSTATIC:
                mv.visitFieldInsn(opcode, owner, name, desc);
                break;
            case PUTFIELD:
                InsertLogCode(mv,className,methodName,"PUTFIELD",name,lineNumber);
                mv.visitFieldInsn(opcode, owner, name, desc);
                break;
            case PUTSTATIC:
                mv.visitFieldInsn(opcode, owner, name, desc);
                break;
            default:
                mv.visitFieldInsn(opcode, owner, name, desc);
                break;
        }
    }

    public static void InsertLogCode(MethodVisitor mv,String className,String methodName,String opcode, String name, int lineNumber){
        mv.visitLdcInsn(className);
        mv.visitLdcInsn(methodName);
        mv.visitLdcInsn(opcode);
        mv.visitLdcInsn(name);
        mv.visitLdcInsn(new Integer(lineNumber));

        mv.visitMethodInsn(INVOKESTATIC,
                InstrumentHelper.CLASSRECEIVER,
                InstrumentHelper.LOGFIELDACCESS,
                InstrumentHelper.STRING_INI_V);
    }
}
