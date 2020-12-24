package Instrument;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

public class MethodBodyVisit extends AdviceAdapter implements Opcodes {
    private String className;
    private String methodName;
    private boolean isInit;
    private int LineNumber;
    public int MaxIndex;
    protected MethodBodyVisit(MethodVisitor mv, String className,int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
        this.className = className;
        this.methodName =name;
        if(methodName.equals("<init>")||methodName.equals("<cinit>")){
            isInit = true;
        }
        MaxIndex= Type.getArgumentsAndReturnSizes(desc)+1;
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        mv.visitLineNumber(line, start);
        this.LineNumber = line;
    }

    @Override//opcode 字节码指令 owner:类名
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//        System.out.println("opcode:"+opcode);
//        System.out.println("owner:"+owner);
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
                    insertLogCode(mv,className,methodName,"GETFIELD",name,LineNumber);
                }
                else {
                    mv.visitFieldInsn(opcode,owner,name,desc);
                }
                break;
            case GETSTATIC:
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            case PUTFIELD:
//                if(!isInit){
//                    insertLogCode(mv,className,methodName,"PUTFIELD",name,LineNumber);
//                }
//                else {
//                    mv.visitFieldInsn(opcode,owner,name,desc);
//                }
                insertLogCode(mv,className,methodName,"PUTFIELD",name,LineNumber);
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            case PUTSTATIC:
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            default:
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
        }
        //mv.visitFieldInsn(opcode, owner, name, desc);
    }
    public static void insertLogCode(MethodVisitor mv,String className,String methodName,String opcode,String fileName,int lineNumber){

            //String className,String methodNmae,String opcode,int lineNumber
            mv.visitLdcInsn(className);//ldc:
            mv.visitLdcInsn(methodName);
            mv.visitLdcInsn(opcode);
            mv.visitLdcInsn(fileName);
            mv.visitLdcInsn(new Integer(lineNumber));

            mv.visitMethodInsn(INVOKESTATIC,
                    InstrumentHelper.CLASSRECIVER,
                    InstrumentHelper.LOGFILEACCESS,
                    InstrumentHelper.STRING4_INT1);
    }
}
