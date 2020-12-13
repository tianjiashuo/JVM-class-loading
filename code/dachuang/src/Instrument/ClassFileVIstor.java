package Instrument;

import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import javax.xml.soap.SAAJResult;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClassFileVIstor extends ClassVisitor {

    private Map<String,Map> Infomation = new HashMap<>();
    public ClassFileVIstor(ClassVisitor cv){
        super(Opcodes.ASM5,cv);
    }
    public static String className;
    private int MethodNumer = 0;
    private int FieldNumber = 0;
    private StringBuffer sb = new StringBuffer("");

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
//        System.out.println("----------visit---------");
//        System.out.println("version:"+version);
//        System.out.println("access:"+access);
//        System.out.println("name:"+name);
//        System.out.println("signature:"+signature);
//        System.out.println("interfaces:"+interfaces);
        className = name;
        cv.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        System.out.println("----------FieldVisitor---------");
//        System.out.println("access:"+access);
//        System.out.println("name:"+name);
//        System.out.println("value:"+value);
//        System.out.println("desc:"+desc);
        try {
            Map<String,String> Field = new HashMap<>();
            Field.put("access:",access+"");
            Field.put("name:",name);
            Field.put("value:",value.toString());
            Field.put("desc:",desc);
            this.Infomation.put("FieldVisitor"+this.FieldNumber,Field);
            System.out.println("FieldVisitor"+this.FieldNumber + " " + Field.toString());
            String s = "FieldVisitor"+this.FieldNumber + " " + Field.toString() + System.getProperty("line.separator");
            sb.append(s);
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("access: " + access);
//        System.out.println("name: " + name);
//        System.out.println("value: " + value.toString());
//        System.out.println("desc: " + desc);
//        System.out.println(name + " is visited");
        this.FieldNumber++;
        return cv.visitField(access, name, desc, signature, value);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("----------MethodVisitor---------");
//        System.out.println("access:"+access);
//        System.out.println("name:"+name);
//        System.out.println("desc:"+desc);
//        System.out.println("signature:"+signature);
//        System.out.println("clsaaName:"+className);
//        System.out.println("exceptions:"+exceptions);
        Map<String,String> Method = new HashMap<>();
        Method.put("access:",access+"");
        Method.put("name:",name);
        Method.put("desc:",desc);
        Method.put("clsaaName:",className);
        this.Infomation.put("MethodVisitor"+this.MethodNumer,Method);
        System.out.println("MethodVisitor"+this.MethodNumer + " " + Method.toString());
        sb.append("MethodVisitor"+this.MethodNumer + " " + Method.toString() + System.getProperty("line.separator"));
//        System.out.println(name + " is visited");
        this.MethodNumer++;
//        BufferedWriter out = null;
//        try {
//            out = new BufferedWriter(new FileWriter("E:\\大创\\runoob.txt"));
//            out.write(this.Infomation.toString());
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        mv = new MethodBodyVisit(mv,className,access,name,desc);
//        System.out.println(mv.toString());
        return mv;
    }

    public Map<String,Map> getField_MethodInfor(){
        return this.Infomation;
    }
    public String getResult(){
        return this.sb.toString();
    }
}
