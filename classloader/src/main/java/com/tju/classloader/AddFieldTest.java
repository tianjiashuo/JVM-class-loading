package com.tju.classloader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Opcodes;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM5;

public class AddFieldTest {
    public void addFieldByCoreAPI() throws Exception {
        String ApplicationClass = "com.tju.classloader.Application";
        ClassReader cr = new ClassReader(ApplicationClass);
        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM5, cw) {
            @Override
            public void visitEnd() {
                // visitEnd是访问类，访问的最后一个事件。在这个事件前添加变量就可以
                super.visitEnd();
                /**
                 * field的模板  public List<String> list = null;
                 *
                 * Opcodes.ACC_PUBLIC   public 权限标志
                 * "name" 变量名
                 * "Ljava/lang/String" 变量返回值。字节码中，类型都是以L开头+类的全名（用/代替.）
                 * null 泛型的标识
                 * "demo" 值
                 */
                FieldVisitor fv = cv.visitField(Opcodes.ACC_PUBLIC, "name", "Ljava/lang/String;", null, "demo");
                if (fv != null) {
                    System.out.println("add");
                    fv.visitEnd();
                }
            }
        };
        //cr.accept(cv,ClassReader.SKIP_CODE|ClassReader.SKIP_DEBUG);
        cr.accept(cv,0);
        byte[] bytesModifield = cw.toByteArray();

       // cr.save(this.getClass(),Application.class,bytesModifield);

    }

    public void addFiledByTreeAPI() {

    }

    public static void main(String[] args) throws Exception {
        new AddFieldTest().addFieldByCoreAPI();
    }
}
