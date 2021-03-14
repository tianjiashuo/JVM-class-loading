package Instrument;

import jdk.internal.org.objectweb.asm.Label;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;
import jdk.internal.org.objectweb.asm.Type;
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的方法体访问类
 */
public class MethodBodyVisit extends AdviceAdapter implements Opcodes {

    private String className;
    private String methodName;
    private boolean isInit;
    private int LineNumber;
    public int MaxIndex;
    public int count = 0;
    public int MaxLocals;
    public int MaxStack;
    public int LabelCount = 0;
    /**
     * 方法栈帧
     */
    private List<Object> stackFrame = new ArrayList<>();
    /**
     * 局部变量表
     */
    private List<Object> LVT = new ArrayList<>();
    public String[] methodNames;
    public int stackCount = 0;
    /**
     * 方法栈
     */
    private static Map<String, List<Object>> branches = new HashMap<>();
    /**
     * 局部变量表变化情况
     */
    private static Map<String, List<Object>> LVT_branches = new HashMap<>();
    /**
     * 局部变量表的对应关系
     */
    public Map<Integer,String> Method_LVT = new HashMap<>();
    /**
     * 局部变量表和方法的对应关系
     */
    public static Map<String,Map> Name_LVT = new HashMap<>();
    public int branchesCount = 0;

    protected MethodBodyVisit(MethodVisitor mv, String className,int access, String name, String desc) {
        super(ASM5, mv, access, name, desc);
        this.className = className;
        this.methodName = name;
        /**
         * 判断是否是init和clinit方法
         */
        if(methodName.equals("<init>") || methodName.equals("<clinit>")){
            isInit = true;
        }
        MaxIndex= Type.getArgumentsAndReturnSizes(desc)+1;
        addBranch(methodName);
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!   "+LVT.size());
    }

//    @Override
//    public void visitLabel(Label label) {
//        super.visitLabel(label);
////        System.out.println();
//        if (LabelCount == 0){
////            System.out.println("[METHOD] " + methodName + "+++++++++++++++++++++++++++++++");
//        }
////        System.out.println(methodName + "'s LABEL" + LabelCount);
//        LabelCount++;
//    }
//
//    @Override
//    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
//        super.visitLocalVariable(name, desc, signature, start, end, index);
//        Method_LVT.put(count,name);
//        count++;
//        if (count == 1){
////            System.out.println(methodName+"'s LVT Structure:");
//        }
////        System.out.println(name + "       " + index);
//        Name_LVT.put(methodName,Method_LVT);
////        LVT_pushIndexValue(name,index);
//    }
//
//    /**
//     *
//     * @param i
//     * @param s
//     * @param s1 方法名
//     * @param s2
//     * @param b
//     */
//    @Override
//    public void visitMethodInsn(int i, String s, String s1, String s2, boolean b) {
//        super.visitMethodInsn(i, s, s1, s2, b);
//        switch (i){
//            case INVOKESTATIC:
////                System.out.println(methodName + "调用了 " + s1);
//            case INVOKEINTERFACE:
//            case INVOKEVIRTUAL:
//            case INVOKESPECIAL:
////                System.out.println("SPECIAL " + s1);
//                break;
////            case INVOKESTATIC:
////                System.out.println("STATIC " + s1);
////                break;
////            case INVOKEINTERFACE:
////                System.out.println("INTERFACE " + s1);
////                break;
////            case INVOKEVIRTUAL:
////                System.out.println("VIRTUAL " + s1);
////                break;
////            default:
////                break;
//        }
//    }
//
//    @Override
//    public void visitLdcInsn(Object cst) {
//        super.visitLdcInsn(cst);
////        System.out.println("常量池的一个类变量从常量池入栈");
//        SF_pushValue("Constant");
////        System.out.println("CONST_POOL 类变量入栈");
//    }
//
//
//    @Override
//    public void visitInsn(int opcode) {
//        super.visitInsn(opcode);
//        switch (opcode){
//            case ICONST_M1:
////                System.out.println("-1值入栈");
//                SF_pushValue(-1);
//                break;
//            case ICONST_0:
////                System.out.println("0值入栈");
//                SF_pushValue(0);
//                break;
//            case ICONST_1:
////                System.out.println("1值入栈");
//                SF_pushValue(1);
//                break;
//            case ICONST_2:
////                System.out.println("2值入栈");
//                SF_pushValue(2);
//                break;
//            case ICONST_3:
////                System.out.println("3值入栈");
//                SF_pushValue(3);
//                break;
//            case ICONST_4:
//                SF_pushValue(4);
////                System.out.println("4值入栈");
//                break;
//            case ICONST_5:
//                SF_pushValue(5);
////                System.out.println("5值入栈");
//                break;
//            case DRETURN:
//            case ARETURN:
//            case IRETURN:
//            case RETURN:
////                System.out.println("void函数返回");
//                if (SF_peekValue() == null){
////                    System.out.println("null");
//                }else {
////                    System.out.println(SF_peekValue());
//                }
//                break;
//            case POP:
//                SF_popValue();
//            case POP2:
//                SF_popValue();
//                break;
//            case DCONST_1:
//                SF_pushValue("0(double)");
//                break;
//            case IADD:
//                int add_result = (int)SF_peekIndexValue(stackFrame.size()-1) + (int)SF_peekIndexValue(stackFrame.size()-2);
//                SF_popValue();
//                SF_popValue();
//                SF_pushValue(add_result);
//                break;
//            case ISUB:
//                int sub_result = (int)SF_peekIndexValue(stackFrame.size()-1) - (int)SF_peekIndexValue(stackFrame.size()-2);
//                SF_popValue();
//                SF_popValue();
//                SF_pushValue(sub_result);
//            case IMUL:
//                int mul1 = (int)SF_peekValue();
//                SF_popValue();
//                int mul2 = (int)SF_peekValue();
//                SF_popValue();
//                SF_pushValue(mul1*mul2);
//        }
//    }
//
//    @Override//opcode 字节码指令 owner:类名
//    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
//        switch (opcode){
//            case GETFIELD:
//                if(!isInit){
//                }
//                mv.visitFieldInsn(opcode,owner,name,desc);
//                SF_peekValue();
//                break;
//            case GETSTATIC:
//                mv.visitFieldInsn(opcode,owner,name,desc);
//                break;
//            case PUTFIELD:
//                mv.visitFieldInsn(opcode,owner,name,desc);
////                System.out.println("给对象字段赋值 " + name);
//                break;
//            case PUTSTATIC:
//                mv.visitFieldInsn(opcode,owner,name,desc);
//                break;
//            default:
//                mv.visitFieldInsn(opcode,owner,name,desc);
//                break;
//        }
//    }
//
//    @Override
//    public void visitVarInsn(int opcode, int var) {
//        super.visitVarInsn(opcode, var);
//        switch (opcode){
//            case ALOAD:
//                if (var == 0){
////                    System.out.println("this指针，代指this.field/method");
//                    SF_pushValue("reference_0");
//                }
//                break;
//            //TODO case ILOAD,LLOAD,FLOAD等等
//            case ILOAD:
////                System.out.println("局部变量表加载" + var + "处的int值入栈");
//                int number = (int)LVT_peekIndexValue(var);
//                SF_pushValue(number);
//                break;
//            case ISTORE:
//                if (LVT.size() < var){
//                    for (int i = 0; i <= var; i++) {
//                        LVT_pushValue(1);
//                    }
//                }
//                //TODO 重点
//                Object o = SF_peekValue();
//                SF_popValue();
//                if (LVT.size() == var){
//                    LVT_pushValue(var);
//                }else {
//                    LVT.set(var,o);
//                }
////                System.out.println("将栈顶int类型值保存到局部变量" + var + "中");
//                break;
//        }
//    }
//
//    @Override
//    public void visitIntInsn(int opcode, int operand) {
//        super.visitIntInsn(opcode, operand);
//        switch (opcode){
//            case BIPUSH:
////                System.out.println(operand + "带符号扩展成int值入栈(BIPUSH)");
//                SF_pushValue(operand);
//        }
//    }
//
//    @Override
//    public void visitLineNumber(int line, Label start) {
//        mv.visitLineNumber(line, start);
//        this.LineNumber = line;
////        System.out.println("line "+ line + "------------------");
////        System.out.println("LineNumber: " + line);
//    }
//
//
//    @Override
//    public void visitMaxs(int maxStack, int maxLocals) {
//        super.visitMaxs(maxStack, maxLocals);
////        System.out.println(methodName + "'s maxStack is: " + maxStack);
////        System.out.println(methodName + "'s maxLocals is: " + maxLocals);
//        MaxStack = maxStack;
//        MaxLocals = maxLocals;
////        System.out.println("局部变量的名称为 " + this.LocalVariableName);
//    }

    @Override
    protected void onMethodEnter() {
        if (!isInit) { mv.visitMethodInsn(INVOKESTATIC,
                    InstrumentHelper.CLASSRECIVER,
                    InstrumentHelper.LOGMETHODACCESS,
                    InstrumentHelper.V_V);
        }
        super.onMethodEnter();
    }

    @Override
    protected void onMethodExit(int opcode) {
        super.onMethodExit(opcode);
//        print();
    }

    private void addBranches(final String methodName, final String[] methodNames) {
        addBranch(methodName);
        for (int i = 0; i < methodNames.length; i++) {
            addBranch(methodNames[i]);
        }
    }

    private void addBranch(String methodName) {
        if (branches.containsKey(methodName)) {
            return;
        }
        branches.put(methodName, new ArrayList<Object>(stackFrame));
    }

    /**
     * 方法栈出栈
     * @return
     */
    private Object SF_popValue() {
        branches.put(methodName, stackFrame);
        return stackFrame.remove(stackFrame.size() - 1);
    }

    /**
     * 局部变量表出栈
     * @return
     */
    private Object LVT_popValue() {
        LVT_branches.put(methodName,LVT);
        return LVT.remove(LVT.size() - 1);
    }

    /**
     * 方法栈得到栈顶元素
     * @return
     */
    private Object SF_peekValue() {
        return stackFrame.get(stackFrame.size() - 1);
    }

    /**
     * 方法栈得到特定位置栈元素
     * @return
     */
    private Object SF_peekIndexValue(int index) {
        return stackFrame.get(index);
    }

    /**
     * 局部变量表得到栈顶元素
     * @return
     */
    private Object LVT_peekValue(){
        return LVT.get(LVT.size() - 1);
    }

    /**
     * 局部变量表得到特定位置栈元素
     * @return
     */
    private Object LVT_peekIndexValue(int index){
        return LVT.get(index);
    }

    /**
     * 方法栈压栈
     * @param o
     */
    private void SF_pushValue(final Object o) {
        stackFrame.add(o);
        branches.put(methodName, stackFrame);
    }

    /**
     * 局部变量表压栈
     * @param o
     */
    private void LVT_pushIndexValue(final Object o,int index){
        LVT.add(index,o);
        LVT_branches.put(methodName,LVT);
    }

    private void LVT_pushValue(final Object o){
        LVT.add(o);
        LVT_branches.put(methodName,LVT);
    }

    public void print(){
        for (Object sf:
             stackFrame) {
            System.out.println(sf.toString());
        }
    }

    public void log(){

    }

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
