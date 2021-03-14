package com.leetcode.util.executor;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LWenH
 * @create 2021/3/9 - 21:49
 * <p>
 * 自定义编译用户提交的代码
 */
public class MyCompiler {
    /**
     * 用于存放文件对象
     */
    private static Map<String, JavaFileObject> fileObjectMap = new ConcurrentHashMap<>();

    //预编译正则表达式,用来匹配源码字符串中的类名
    private static Pattern CLASS_PATTERN = Pattern.compile("class\\s+([$_a-zA-Z][$_a-zA-Z0-9]*)\\s*");

    public static byte[] compile(String code, DiagnosticCollector<JavaFileObject> compileCollector) {
        // 获取编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        // 获得自己的JavaFileManager
        JavaFileManager m = compiler.getStandardFileManager(compileCollector, null, null);
        JavaFileManager javaFileManager = new MyJavaFileManger(m);

        //从源码字符串中匹配类名
        Matcher matcher = CLASS_PATTERN.matcher(code);
        String className;
        if (matcher.find()) {
            className = matcher.group(1);
        } else {
            throw new IllegalArgumentException("No valid class");
        }

        // 将源码字符串封装成一个文件对象，供自定义的编译器使用
        JavaFileObject myJavaFileObject = new MyJavaFileObject(className, code);
        /*
         * 1、编译器得到源码，进行编译，得到字节码，源码封装在myJavaFIleObject中
         * 2、通过调用JavaFileManager的getJavaFileForOutput()方法创建一个MyJavaFileObject对象，用于存放编译生成的字节码
         *       然后将存放了字节码的JavaFileObject放在Map<className,JavaFileObject>中，以便后面取用。
         * 3、通过类名从map中获取到存放字节码的MyJavaFileObject
         * 4、通过MyJavaFileObject对象获取到存放编译结果的输出流
         * 5、调用getCompiledBytes()方法将输出流内容转换为字节数组
         */
        // 执行编译，通过传入自己的JavaFileManager为编译器创建存放字节码的JavaFIleObject对象
        Boolean result = compiler.getTask(null, javaFileManager, compileCollector,
                null, null, Arrays.asList(myJavaFileObject)).call();
        JavaFileObject javaFileObject = fileObjectMap.get(className);
        if (result && javaFileObject != null) {
            return ((MyJavaFileObject) javaFileObject).getCompiledBytes();
        }
        return null;
    }

    /**
     * 管理JavaFileObject的管理器
     */
    public static class MyJavaFileManger extends ForwardingJavaFileManager<JavaFileManager> {

        MyJavaFileManger(JavaFileManager fileManager) {
            super(fileManager);
        }

        /**
         * 获取输入的文件对象，它表示给定位置处指定类型的指定类。
         *
         * @param location  位置
         * @param className 类名称
         * @param kind      文件的类型，必须是 SOURCE 或 CLASS
         * @return 文件对象
         * @throws IOException
         */
        @Override
        public JavaFileObject getJavaFileForInput(Location location, String className, JavaFileObject.Kind kind) throws IOException {
            JavaFileObject javaFileObject = fileObjectMap.get(className);
            if (javaFileObject == null) {
                return super.getJavaFileForInput(location, className, kind);
            }
            return javaFileObject;
        }

        /**
         * 获取输出的文件对象，它表示给定位置处指定类型的指定类。
         *
         * @param location  位置
         * @param className 类的名称
         * @param kind      文件的类型，必须是 SOURCE 或 CLASS
         * @param sibling   用作位置提示的文件对象，可以为 null
         * @return 文件对象
         * @throws IOException
         */
        @Override
        public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
            JavaFileObject javaFileObject = new MyJavaFileObject(className, kind);
            fileObjectMap.put(className, javaFileObject);
            return javaFileObject;
        }
    }

    /**
     * 用于将类文件和字节码文件封装成一个文件对象
     */
    public static class MyJavaFileObject extends SimpleJavaFileObject {
        private String code;
        private ByteArrayOutputStream byteArrayOutputStream;

        /**
         * 构建用于存放源代码的JavaFileObject对象
         *
         * @param classname 类名
         * @param code      源代码
         */
        public MyJavaFileObject(String classname, String code) {
            super(URI.create("String:///" + classname + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }


        public MyJavaFileObject(String name, Kind kind) {
            super(URI.create("String:///" + name + Kind.SOURCE.extension), kind);
        }

        /**
         * 返回此文件对象的字符内容
         *
         * @param ignoreEncodingErrors 如果为 true，则忽略编码错误
         * @return 文件对象的字符内容
         * @throws IOException 要执行的code为null则抛出一个异常
         */
        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            if (code == null) {
                throw new RuntimeException("code == null, please check out");
            }
            return code;
        }

        /**
         * 获取文件对象的 OutputStream
         *
         * @return 存放字节码的文件对象的输出流
         */
        @Override
        public OutputStream openOutputStream() {
            byteArrayOutputStream = new ByteArrayOutputStream();
            return byteArrayOutputStream;
        }

        /**
         * 将字节数组输出流转化为byte数组
         *
         * @return
         */
        public byte[] getCompiledBytes() {
            return byteArrayOutputStream.toByteArray();
        }
    }

}


