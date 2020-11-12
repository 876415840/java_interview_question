package org.example.io.bytes.input;

import javassist.*;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @Description: BufferedInputStream
 * @Author MengQingHao
 * @Date 2020/7/1 4:28 下午
 */
@Slf4j
public class BufferedInputStreamHandle {


    public static void main(String[] args) throws Exception {
        ClassPool mPool = ClassPool.getDefault();
        CtClass mCtc = mPool.makeClass("ProxyTest");
        mPool.insertClassPath(new ClassClassPath(Test.class));
        mCtc.setSuperclass(mPool.get(Test.class.getName()));
        mCtc.addConstructor(CtNewConstructor.defaultConstructor(mCtc));
        mCtc.addField(CtField.make("public " + Test.class.getName() + " proxyObj;", mCtc));
        String src = "public void test() { "
                + "System.out.println(\"test 方法执行前\");"
                + "proxyObj.test();"
                + "System.out.println(\"test 方法执行后\"); "
                + "}";
        mCtc.addMethod(CtNewMethod.make(src, mCtc));
        Class<?> pc = mCtc.toClass();
        Test bytecodeProxy = (Test) pc.getDeclaredConstructor().newInstance();
        Field filed = bytecodeProxy.getClass().getField("proxyObj");
        filed.set(bytecodeProxy, new Test());
        log.info("call before");
        bytecodeProxy.test();
        log.info("call end");
    }


}
