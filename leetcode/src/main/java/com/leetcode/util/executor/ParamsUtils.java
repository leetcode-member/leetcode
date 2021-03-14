package com.leetcode.util.executor;

import com.leetcode.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author LWenH
 * @create 2021/3/10 - 21:49
 */
@Slf4j
public class ParamsUtils {

    /**
     * 构建参数
     *
     * @param strs   代表参数的字符串数组
     * @param method 方法对象
     * @return 返回实际的参数
     */
    public static List buildParams(String[] strs, Method method) {
        // 参数的class信息
        Class[] parameterTypes = method.getParameterTypes();
        // 创建一个list，用来装所有的参数并返回
        List<Object> paramList = new ArrayList<>(strs.length);
        for (int i = 0; i < strs.length; i++) {
            // 参数信息
            String typeInfo = parameterTypes[i].getSimpleName();
            // 具体参数对应的字符串
            String paramStr = strs[i];
            /*
                Leetcode上的题目参数基本上有基本类型，String，一维、二维基本类型数组，List类型
                参数这块确实很难做，就只好不断的判断匹配来实现了。
             */
            if ("String".equals(typeInfo)) {
                // 如果参数本身就是String类型，去掉两边的引号
                paramList.add(paramStr.replace("\"", ""));
            } else if (typeInfo.contains("[]")) {
                /*
                    如果类型信息中有"[]"，说明是数组，区分一维数组和多维数组
                    只实现了基本类型的一维和二维数组，因为包装类型的一维二维数组在leetcode上基本没看到
                 */
                int subCount = StringUtil.getSubCount(typeInfo, "[]");
                if (subCount == 1) {
                    /*
                        一维数组的情况：现将字符串转为字符串数组
                        然后调用方法将字符串数组转化为指定类型的数组。
                    */
                    String[] strArray = ArrayUtils.text2StrArray(paramStr);
                    switch (typeInfo) {
                        case "String[]":
                            paramList.add(ArrayUtils.str2StrArray(paramStr));
                            break;
                        case "int[]":
                            paramList.add(ArrayUtils.str2IntArray(strArray));
                            break;
                        case "byte[]":
                            paramList.add(ArrayUtils.str2ByteArray(strArray));
                            break;
                        case "short[]":
                            paramList.add(ArrayUtils.str2ShortArray(strArray));
                            break;
                        case "long[]":
                            paramList.add(ArrayUtils.str2LongArray(strArray));
                            break;
                        case "float[]":
                            paramList.add(ArrayUtils.str2FloatArray(strArray));
                            break;
                        case "double[]":
                            paramList.add(ArrayUtils.str2DoubleArray(strArray));
                            break;
                        case "char[]":
                            /*
                                关于char数组，leetcode中测试用例的char元素都是用""来表示的
                            */
                            paramList.add(ArrayUtils.str2CharArray(paramStr));
                            break;
                        case "boolean[]":
                            paramList.add(ArrayUtils.str2BooleanArray(strArray));
                            break;
                        default:
                            log.info("处理参数时出现异常：不支持的一维数组");
                            break;
                    }

                } else if (subCount == 2) {
                    /*
                        二维数组的情况
                     */
//                    String[] noArr = paramStr.split("],\\[");
//                    for (int j = 0; j < noArr.length; j++) {
//                        noArr[j] = noArr[j].replace("[", "").replace("]", "");
//                    }
                    String[] noArr = ArrayUtils.text2StrArray2(paramStr);
                    switch (typeInfo) {
                        case "String[][]":
                            paramList.add(ArrayUtils.str2StrArray2(noArr));
                            break;
                        case "int[][]":
                            paramList.add(ArrayUtils.str2IntArray2(noArr));
                            break;
                        case "byte[][]":
                            paramList.add(ArrayUtils.str2ByteArray2(noArr));
                            break;
                        case "short[][]":
                            paramList.add(ArrayUtils.str2ShortArray2(noArr));
                            break;
                        case "long[][]":
                            paramList.add(ArrayUtils.str2LongArray2(noArr));
                            break;
                        case "float[][]":
                            paramList.add(ArrayUtils.str2FloatArray2(noArr));
                            break;
                        case "double[][]":
                            paramList.add(ArrayUtils.str2DoubleArray2(noArr));
                            break;
                        case "char[][]":
                            /*
                                关于char数组，leetcode中测试用例的char元素都是用""来表示的
                            */
                            paramList.add(ArrayUtils.str2CharArray2(noArr));
                            break;
                        case "boolean[][]":
                            paramList.add(ArrayUtils.str2BooleanArray2(noArr));
                            break;
                        default:
                            log.info("处理参数时出现异常：不支持的二维数组");
                            break;
                    }
                }
            } else if ("List".equals(typeInfo)) {
                    /*
                        还有List套List的情况，分情况讨论.
                    */
                Parameter[] parameters = method.getParameters();
                String typeName = parameters[i].getParameterizedType().getTypeName();
                String simpleTypeName = typeName.replace("java.util.", "").replace("java.lang.", "");
                int count = StringUtil.getSubCount(simpleTypeName, "<");
                if (count == 1) {
                    // 如果只有一个"<"，说明就是单层集合
                    String[] strArray = ArrayUtils.text2StrArray(paramStr);
                    switch (simpleTypeName) {
                        case "List<String>":
                            String[] strings = ArrayUtils.str2StrArray(paramStr);
                            List<String> stringList = Arrays.asList(strings);
                            paramList.add(stringList);
                            break;
                        case "List<Integer>":
                            int[] ints = ArrayUtils.str2IntArray(strArray);
                            Integer[] integers = ArrayUtils.toWrap(ints);
                            List<Integer> integerList = Arrays.asList(integers);
                            paramList.add(integerList);
                            break;
                        case "List<Byte>":
                            byte[] bytes = ArrayUtils.str2ByteArray(strArray);
                            Byte[] bytes1 = ArrayUtils.toWrap(bytes);
                            List<Byte> byteList = Arrays.asList(bytes1);
                            paramList.add(byteList);
                            break;
                        case "List<Short>":
                            short[] shorts = ArrayUtils.str2ShortArray(strArray);
                            Short[] shorts1 = ArrayUtils.toWrap(shorts);
                            List<Short> shortList = Arrays.asList(shorts1);
                            paramList.add(shortList);
                            break;
                        case "List<Long>":
                            long[] longs = ArrayUtils.str2LongArray(strArray);
                            Long[] longs1 = ArrayUtils.toWrap(longs);
                            List<Long> longList = Arrays.asList(longs1);
                            paramList.add(longList);
                            break;
                        case "List<Float>":
                            float[] floats = ArrayUtils.str2FloatArray(strArray);
                            Float[] floats1 = ArrayUtils.toWrap(floats);
                            List<Float> floatList = Arrays.asList(floats1);
                            paramList.add(floatList);
                            break;
                        case "List<Double>":
                            double[] doubles = ArrayUtils.str2DoubleArray(strArray);
                            Double[] doubles1 = ArrayUtils.toWrap(doubles);
                            List<Double> doubleList = Arrays.asList(doubles1);
                            paramList.add(doubleList);
                            break;
                        case "List<Boolean>":
                            boolean[] booleans = ArrayUtils.str2BooleanArray(strArray);
                            Boolean[] booleans1 = ArrayUtils.toWrap(booleans);
                            List<Boolean> booleanList = Arrays.asList(booleans1);
                            paramList.add(booleanList);
                            break;
                        case "List<Character>":
                            char[] chars = ArrayUtils.str2CharArray(paramStr);
                            Character[] characters = ArrayUtils.toWrap(chars);
                            List<Character> characterList = Arrays.asList(characters);
                            paramList.add(characterList);
                            break;
                        default:
                            break;
                    }
                } else if (count == 2) {
                    // 如果有两个"<"，则说明是嵌套集合
                    String[] noArr = ArrayUtils.text2StrArray2(paramStr);
                    switch (simpleTypeName) {
                        case "List<List<String>>":
                            paramList.add(ArrayUtils.buildStringList(noArr));
                            break;
                        case "List<List<Integer>>":
                            paramList.add(ArrayUtils.buildIntegerList(noArr));
                            break;
                        case "List<List<Byte>>":
                            paramList.add(ArrayUtils.buildByteList(noArr));
                            break;
                        case "List<List<Short>>":
                            paramList.add(ArrayUtils.buildShortList(noArr));
                            break;
                        case "List<List<Long>>":
                            paramList.add(ArrayUtils.buildLongList(noArr));
                            break;
                        case "List<List<Float>>":
                            paramList.add(ArrayUtils.buildFloatList(noArr));
                            break;
                        case "List<List<Double>>":
                            paramList.add(ArrayUtils.buildDoubleList(noArr));
                            break;
                        case "List<List<Boolean>>":
                            paramList.add(ArrayUtils.buildBooleanList(noArr));
                            break;
                        case "List<List<Character>>":
                            paramList.add(ArrayUtils.buildCharacterList(noArr));
                            break;
                        default:
                            break;
                    }
                } else {
                    log.info("处理参数时出现异常：不支持的嵌套集合");
                    throw new RuntimeException("处理参数时出现异常：不支持的嵌套集合");
                }
            } else {
                /*
                    剩下的就认为是基本类型和String了
                    LeetCode里大部分题目的参数类型**基本**就限定以上这些了
                    当然肯定还有其他的类型，就不做了。
                 */
                switch (typeInfo) {
                    case "int":
                    case "Integer":
                        Integer integer = Integer.parseInt(paramStr);
                        paramList.add(integer);
                        break;
                    case "byte":
                    case "Byte":
                        Byte byt = Byte.parseByte(paramStr);
                        paramList.add(byt);
                        break;
                    case "short":
                    case "Short":
                        Short sht = Short.parseShort(paramStr);
                        paramList.add(sht);
                        break;
                    case "long":
                    case "Long":
                        Long l = Long.parseLong(paramStr);
                        paramList.add(l);
                        break;
                    case "float":
                    case "Float":
                        Float f = Float.parseFloat(paramStr);
                        paramList.add(f);
                        break;
                    case "double":
                    case "Double":
                        Double d = Double.parseDouble(paramStr);
                        paramList.add(d);
                        break;
                    case "char":
                    case "Character":
                        String s = paramStr.replace("\"", "");
                        Character character = s.charAt(0);
                        paramList.add(character);
                        break;
                    case "boolean":
                    case "Boolean":
                        Boolean b = Boolean.parseBoolean(paramStr);
                        paramList.add(b);
                        break;
                    default:
                        throw new RuntimeException("反射执行方法时参数传递出现问题");
                }
            }
        }
        return paramList;
    }


    /**
     * 将反射执行方法后得到的Object类型的返回值转为String类型
     *
     * @param returnType  返回值类型信息
     * @param returnValue Object类型的返回值
     * @return String类型的返回信息
     */
    public static String returnValue2Str(String returnType, Object returnValue) {
        String returnStr = "not null";
        /*
            返回值还是**大致**有以下几种情况：
                基本类型及其包装类型，String，一维、二维数组，单层、嵌套List
            将返回值转为字符串的处理方法：
                String，List（不管是单层还是嵌套），包装类型都可以直接调用toString()方法获取到字符串信息
                一维数组需要转为相应的数组类型，然后调用Arrays.toString()方法；二维数组转为Object[]类型，然后调用Arrays.deepToString()方法。
                基本类型需要调用String.valueOf()方法或者拼接空字符串转换。---> 好像不用，由于是Object类型的返回值，直接toString()就好了
         */
        if ("String".equals(returnType) || "List".equals(returnType) ||
                "Integer".equals(returnType) || "int".equals(returnType) ||
                "Byte".equals(returnType) || "byte".equals(returnType) ||
                "Short".equals(returnType) || "short".equals(returnType) ||
                "Long".equals(returnType) || "long".equals(returnType) ||
                "Float".equals(returnType) || "float".equals(returnType) ||
                "Double".equals(returnType) || "double".equals(returnType) ||
                "Boolean".equals(returnType) || "boolean".equals(returnType) ||
                "Character".equals(returnType) || "char".equals(returnType)
        ) {
            returnStr = returnValue.toString();
        } else if (returnType.contains("[]")) {
            int subCount = StringUtil.getSubCount(returnType,"[]");
            if (subCount == 1) {
                /*
                    一维数组的情况
                 */
                switch (returnType) {
                    case "String[]":
                        returnStr = Arrays.toString((String[]) returnValue);
                        break;
                    case "int[]":
                        returnStr = Arrays.toString((int[]) returnValue);
                        break;
                    case "Integer[]":
                        returnStr = Arrays.toString((Integer[]) returnValue);
                        break;
                    case "byte[]":
                        returnStr = Arrays.toString((byte[]) returnValue);
                        break;
                    case "Byte[]":
                        returnStr = Arrays.toString((Byte[]) returnValue);
                        break;
                    case "short[]":
                        returnStr = Arrays.toString((short[]) returnValue);
                        break;
                    case "Short[]":
                        returnStr = Arrays.toString((Short[]) returnValue);
                        break;
                    case "long[]":
                        returnStr = Arrays.toString((long[]) returnValue);
                        break;
                    case "Long[]":
                        returnStr = Arrays.toString((Long[]) returnValue);
                        break;
                    case "float[]":
                        returnStr = Arrays.toString((float[]) returnValue);
                        break;
                    case "Float[]":
                        returnStr = Arrays.toString((Float[]) returnValue);
                        break;
                    case "double[]":
                        returnStr = Arrays.toString((double[]) returnValue);
                        break;
                    case "Double[]":
                        returnStr = Arrays.toString((Double[]) returnValue);
                        break;
                    case "char[]":
                        returnStr = Arrays.toString((char[]) returnValue);
                        break;
                    case "Character[]":
                        returnStr = Arrays.toString((Character[]) returnValue);
                        break;
                    case "boolean[]":
                        returnStr = Arrays.toString((boolean[]) returnValue);
                        break;
                    case "Boolean[]":
                        returnStr = Arrays.toString((Boolean[]) returnValue);
                        break;
                    default:
                        break;
                }
            } else if (subCount == 2) {
                /*
                    二维数组的情况
                 */
                returnStr = Arrays.deepToString((Object[]) returnValue);
            } else {
                throw new RuntimeException("处理返回值时出现异常：不支持的多维数组");
            }
        } else if ("void".equals(returnType)) {
            returnStr = "void";
        } else {
            throw new RuntimeException("处理返回值时出现异常：不支持的返回值类型");
        }
        return returnStr;
    }
}
