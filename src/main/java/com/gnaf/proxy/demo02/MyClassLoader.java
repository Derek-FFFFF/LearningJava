package com.gnaf.proxy.demo02;

import com.gnaf.basic.constructor.demo01.B;

import java.io.*;

/**
 * @author Derek
 * @date 2019/10/6 10:58
 */
public class MyClassLoader extends ClassLoader {

    private File baseDir;

    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();
        this.baseDir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = MyClassLoader.class.getPackage().getName() + "." + name;
        if (baseDir != null) {
            File classFile = new File(baseDir, name.replaceAll("\\.", "/") + ".class");
            if (classFile.exists()) {
                FileInputStream in = null;
                try {
                    in = new FileInputStream(classFile);
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1) {
                        out.write(buff, 0, len);
                    }
                    return defineClass(className, out.toByteArray(), 0, out.size());
                } catch (Exception e) {

                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }
}
