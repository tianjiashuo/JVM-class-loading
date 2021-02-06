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

        if(methodName.equals("<init>") || methodName.equals("<clinit>")){
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
        switch (opcode){

            case GETFIELD:
                if(!isInit){
//                    System.out.println("opcode: " + "GETFIELD");
//                    System.out.println("owner " + owner);
//                    System.out.println("name " + name);
//                    System.out.println("desc " + desc);
//                    insertLogCode(mv,className,methodName,"GETFIELD",name,LineNumber);
                }
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            case GETSTATIC:
//                    System.out.println("opcode: " + "GETSTATIC");
//                    System.out.println("owner " + owner);
//                    System.out.println("name " + name);
//                    System.out.println("desc " + desc);
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            case PUTFIELD:
//                System.out.println("opcode: " + "PUTFIELD");
//                System.out.println("owner " + owner);
//                System.out.println("name " + name);
//                System.out.println("desc " + desc);
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            case PUTSTATIC:
//                System.out.println("opcode: PUTSTATIC" );
//                System.out.println("owner " + owner);
//                System.out.println("name " + name);
//                System.out.println("desc " + desc);
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
            default:
                mv.visitFieldInsn(opcode,owner,name,desc);
                break;
        }
//        mv.visitFieldInsn(opcode, owner, name, desc);
    }

    @Override
    protected void onMethodEnter() {
        if (!isInit) {
            mv.visitMethodInsn(INVOKESTATIC,
                    InstrumentHelper.CLASSRECIVER,
                    InstrumentHelper.LOGMETHODACCESS,
                    InstrumentHelper.V_V);
        }
        super.onMethodEnter();
    }

    //    @Override
//    public void visitMethodInsn(int opcode, String className, String methodName, String sign, boolean b) {
//
//        System.out.println(this.methodName);
//        switch (opcode){
//
//            case INVOKEVIRTUAL:
//
//                System.out.println("INVOKEVIRTUAL " + className + " " + methodName);
//                mv.visitMethodInsn(opcode, className, methodName, sign, b);
//                break;
//            case INVOKEINTERFACE:
//
//                System.out.println("INVOKEINTERFACE " + className + " " + methodName);
//                mv.visitMethodInsn(opcode, className, methodName, sign, b);
//                break;
//            case INVOKESPECIAL:
//
//                System.out.println("INVOKESPECIAL " + className + " " + methodName);
//                mv.visitMethodInsn(opcode, className, methodName, sign, b);
//                break;
//            case INVOKESTATIC:
//
//                mv.visitMethodInsn(INVOKESTATIC,
//                        InstrumentHelper.CLASSRECIVER,
//                        InstrumentHelper.LOGMETHODACCESS,
//                        InstrumentHelper.V_V);
//
//                System.out.println("INVOKESTATIC " + className + " " + methodName);
//                mv.visitMethodInsn(opcode, className, methodName, sign, b);
//                break;
//            default:
//
//                mv.visitMethodInsn(opcode, className, methodName, sign, b);
//                break;
//        }
//    }

    public static void insertLogCode(MethodVisitor mv, String className, String methodName, String opcode, String fileName, int lineNumber){

            //String className,String methodName,String opcode,int lineNumber
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
