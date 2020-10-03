package com.tju.classloader;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

public class MyClassLoader extends ClassLoader {

    /**
     * 动态生成类用ClassLoader，通过定义ClassLoader子类defineClass();
     * 
     * @param name
     * @param b
     * @return
     */
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }

    /**
     * 直接生成类
     */
    // Class c = MyClassLoader.defineClass("com.tju.classloader.Comparable",b);

    class StubClassLoader extends ClassLoader {
        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            if (name.endsWith("_Stub")) {
                ClassWriter cw = new ClassWriter(0);
                byte[] b = cw.toByteArray();
                return defineClass(name, b, 0, b.length);
            }
            return super.findClass(name);
        }
    }

    // byte[] b1 = ...;
    // ClassWriter cw = new ClassWriter(0);
    // ClassVisitor cv = new ClassVisitor(ASM4, cw){
        
    // };
    // ClassReader cr = new ClassReader(b1);
    // cr.accept(cv,0);
    // byte[] b2 = cw.toByteArray();
}
