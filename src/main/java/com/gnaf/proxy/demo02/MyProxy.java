package com.gnaf.proxy.demo02;

import sun.management.CompilerThreadStat;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author Derek
 * @date 2019/10/6 10:58
 */
public class MyProxy {

    private static final String LN = "\r\n";

    public static Object newProxyInstance(MyClassLoader cl, Class<?>[] interfaces, MyInvocationHandler h) {

        // 1. generate source code
        String proxySrc = generateSrc(interfaces[0]);
        // 2. write source code to disk like .java
        try {
            String filePath = MyProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Gnaf0.java");
            FileWriter fw = new FileWriter(file);
            fw.write(proxySrc);
            fw.flush();
            fw.close();

            // 3. compile source code to .class
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable<? extends JavaFileObject> inter = manager.getJavaFileObjects(file);
            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, manager, null, null, null, inter);
            task.call();
            manager.close();
            file.delete();
            // 4. load .class by jvm dynamic
            Class<?> proxyClass = cl.findClass("$Gnaf0");
            Constructor<?> c = proxyClass.getConstructor(MyInvocationHandler.class);

            // 5. return proxy object
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String generateSrc(Class<?> inter) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.gnaf.proxy.demo02;").append(LN);
        sb.append("import java.lang.reflect.Method;").append(LN);
        sb.append("import com.gnaf.proxy.demo02.MyProxy;").append(LN);
        sb.append("import com.gnaf.proxy.Person;").append(LN);
        sb.append("public class $Gnaf0 extends MyProxy implements").append(" ").append(inter.getName());
        sb.append("{").append(LN);

        sb.append("MyInvocationHandler h;").append(LN);
        sb.append("public $Gnaf0(MyInvocationHandler h) {").append(LN);
        sb.append("this.h = h;").append(LN);
        sb.append("}").append(LN);

        // generate method
        for (Method m : inter.getMethods()) {
            sb.append("public ").append(m.getReturnType().getName()).append(" ").append(m.getName()).append("(){").append(LN);
            sb.append("try {").append(LN);
            sb.append("Method m = ").append(inter.getName()).
                    append(".class.getMethod(\"").append(m.getName()).append("\",new Class[]{});").append(LN);
            sb.append("this.h.invoke(this,m,null);").append(LN);
            sb.append("} catch (Throwable e) {}").append(LN);
            sb.append("}").append(LN);

        }

        sb.append("}");

        return sb.toString();
    }

}
