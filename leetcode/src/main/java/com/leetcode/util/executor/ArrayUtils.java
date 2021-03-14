package com.leetcode.util.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author LWenH
 * @create 2021/3/11 - 20:46
 * <p>
 * 关于数组的工具类
 */
public class ArrayUtils {

    /**
     * 将前端传来的代表二维数组的字符串中的元素字符串拿到
     * 功能描述不清楚，忘了功能的话去leetcode找个二维数组的测试用例试一试（写给自己怕忘了）
     *
     * @param paramStr
     * @return
     */
    public static String[] text2StrArray2(String paramStr) {
        String[] noArr = paramStr.split("],\\[");
        for (int j = 0; j < noArr.length; j++) {
            noArr[j] = noArr[j].replace("[", "").replace("]", "");
        }
        return noArr;
    }

    /**
     * 将前端传来的代表一维数组的字符串转换成String数组
     *
     * @param paramStr
     * @return
     */
    public static String[] text2StrArray(String paramStr) {
        String[] strArray;
        String arrEle = paramStr.replace("[", "").replace("]", "");
        if ("".equals(arrEle)) {
            // 如果参数是空数组
            strArray = new String[0];
        } else {
            strArray = paramStr.replace("[", "").replace("]", "").split(",");
        }
        return strArray;
    }

    /*
        构造一维数组的方法
     */

    public static String[] str2StrArray(String paramStr) {
        return paramStr.replace("[", "").replace("]", "").replace("\"", "").split(",");

    }

    public static int[] str2IntArray(String[] strArr) {
        int[] intArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    public static byte[] str2ByteArray(String[] strArr) {
        byte[] byteArr = new byte[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            byteArr[i] = Byte.parseByte(strArr[i]);
        }
        return byteArr;
    }

    public static short[] str2ShortArray(String[] strArr) {
        short[] shortArr = new short[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            shortArr[i] = Short.parseShort(strArr[i]);
        }
        return shortArr;
    }

    public static long[] str2LongArray(String[] strArr) {
        long[] longArr = new long[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            longArr[i] = Long.parseLong(strArr[i]);
        }
        return longArr;
    }

    public static float[] str2FloatArray(String[] strArr) {
        float[] floatArr = new float[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            floatArr[i] = Float.parseFloat(strArr[i]);
        }
        return floatArr;
    }

    public static double[] str2DoubleArray(String[] strArr) {
        double[] doubleArr = new double[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            doubleArr[i] = Double.parseDouble(strArr[i]);
        }
        return doubleArr;
    }

    public static char[] str2CharArray(String paramStr) {
        String str = paramStr.replace("[", "").replace("]", "").replace("\"", "").replace(",", "");
        return str.toCharArray();
    }

    public static boolean[] str2BooleanArray(String[] strArr) {
        boolean[] booleanArr = new boolean[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            booleanArr[i] = Boolean.parseBoolean(strArr[i]);
        }
        return booleanArr;
    }

    /*
        构造二维数组的方法
     */

    public static String[][] str2StrArray2(String[] noArr) {
        String[][] strArray2 = new String[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            strArray2[i] = str2StrArray(noArr[i]);
        }
        return strArray2;
    }

    public static int[][] str2IntArray2(String[] noArr) {
        int[][] intArr2 = new int[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            intArr2[i] = str2IntArray(strArr);
        }
        return intArr2;
    }

    public static byte[][] str2ByteArray2(String[] noArr) {
        byte[][] byteArr2 = new byte[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            byteArr2[i] = str2ByteArray(strArr);
        }
        return byteArr2;
    }

    public static short[][] str2ShortArray2(String[] noArr) {
        short[][] shortArr2 = new short[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            shortArr2[i] = str2ShortArray(strArr);
        }
        return shortArr2;
    }

    public static long[][] str2LongArray2(String[] noArr) {
        long[][] longArr2 = new long[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            longArr2[i] = str2LongArray(strArr);
        }
        return longArr2;
    }

    public static float[][] str2FloatArray2(String[] noArr) {
        float[][] floatArr2 = new float[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            floatArr2[i] = str2FloatArray(strArr);
        }
        return floatArr2;
    }

    public static double[][] str2DoubleArray2(String[] noArr) {
        double[][] doubleArr2 = new double[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            doubleArr2[i] = str2DoubleArray(strArr);
        }
        return doubleArr2;
    }

    public static boolean[][] str2BooleanArray2(String[] noArr) {
        boolean[][] booleanArr2 = new boolean[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            String[] strArr = noArr[i].split(",");
            booleanArr2[i] = str2BooleanArray(strArr);
        }
        return booleanArr2;
    }

    public static char[][] str2CharArray2(String[] noArr) {
        for (int j = 0; j < noArr.length; j++) {
            noArr[j] = noArr[j].replace("\"", "").replace(",", "");
        }
        char[][] charArr2 = new char[noArr.length][];
        for (int i = 0; i < noArr.length; i++) {
            charArr2[i] = str2CharArray(noArr[i]);
        }
        return charArr2;
    }

    /*
        基本类型数组转换为包装类数组
     */

    public static Byte[] toWrap(byte[] original) {
        int length = original.length;
        Byte[] dest = new Byte[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }

    public static Float[] toWrap(float[] original) {
        int length = original.length;
        Float[] dest = new Float[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }

    public static Double[] toWrap(double[] original) {
        int length = original.length;
        Double[] dest = new Double[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }


    public static Boolean[] toWrap(boolean[] original) {
        int length = original.length;
        Boolean[] dest = new Boolean[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }


    public static Long[] toWrap(long[] original) {
        int length = original.length;
        Long[] dest = new Long[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }


    public static Character[] toWrap(char[] original) {
        int length = original.length;
        Character[] dest = new Character[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }

    public static Integer[] toWrap(int[] original) {
        int length = original.length;
        Integer[] dest = new Integer[length];
        for (int i = 0; i < length; i++) {
            dest[i] = original[i];
        }
        return dest;
    }

    public static Short[] toWrap(short[] original) {
        int len = original.length;
        Short[] dest = new Short[len];
        for (int i = 0; i < len; i++) {
            dest[i] = original[i];
        }
        return dest;
    }

    /*
        构造嵌套List集合
     */

    public static List<List<String>> buildStringList(String[] noArr) {
        List<List<String>> stringList2 = new ArrayList<>();
        String[][] strArray2 = str2StrArray2(noArr);
        for (String[] strings : strArray2) {
            List<String> stringList = Arrays.asList(strings);
            stringList2.add(stringList);
        }
        return stringList2;
    }

    public static List<List<Integer>> buildIntegerList(String[] noArr) {
        List<List<Integer>> integerList2 = new ArrayList<>();
        int[][] ints = str2IntArray2(noArr);
        for (int[] intArr : ints) {
            Integer[] integers = toWrap(intArr);
            List<Integer> integerList = Arrays.asList(integers);
            integerList2.add(integerList);
        }
        return integerList2;
    }

    public static List<List<Byte>> buildByteList(String[] noArr) {
        List<List<Byte>> byteList2 = new ArrayList<>();
        byte[][] bytes = str2ByteArray2(noArr);
        for (byte[] byteArr : bytes) {
            Byte[] bys = toWrap(byteArr);
            List<Byte> byteList = Arrays.asList(bys);
            byteList2.add(byteList);
        }
        return byteList2;
    }

    public static List<List<Short>> buildShortList(String[] noArr) {
        List<List<Short>> shortList2 = new ArrayList<>();
        short[][] shorts = str2ShortArray2(noArr);
        for (short[] shortArr : shorts) {
            Short[] shs = toWrap(shortArr);
            List<Short> shortList = Arrays.asList(shs);
            shortList2.add(shortList);
        }
        return shortList2;
    }

    public static List<List<Long>> buildLongList(String[] noArr) {
        List<List<Long>> longList2 = new ArrayList<>();
        long[][] longs = str2LongArray2(noArr);
        for (long[] longArr : longs) {
            List<Long> longList = Arrays.asList(toWrap(longArr));
            longList2.add(longList);
        }
        return longList2;
    }

    public static List<List<Float>> buildFloatList(String[] noArr) {
        List<List<Float>> floatList2 = new ArrayList<>();
        float[][] floats = str2FloatArray2(noArr);
        for (float[] floatArr : floats) {
            List<Float> floatList = Arrays.asList(toWrap(floatArr));
            floatList2.add(floatList);
        }
        return floatList2;
    }

    public static List<List<Double>> buildDoubleList(String[] noArr) {
        List<List<Double>> doubleList2 = new ArrayList<>();
        double[][] doubles = str2DoubleArray2(noArr);
        for (double[] doubleArr : doubles) {
            List<Double> doubleList = Arrays.asList(toWrap(doubleArr));
            doubleList2.add(doubleList);
        }
        return doubleList2;
    }

    public static List<List<Boolean>> buildBooleanList(String[] noArr) {
        List<List<Boolean>> booleanList2 = new ArrayList<>();
        boolean[][] booleans = str2BooleanArray2(noArr);
        for (boolean[] booleanArr : booleans) {
            List<Boolean> booleanList = Arrays.asList(toWrap(booleanArr));
            booleanList2.add(booleanList);
        }
        return booleanList2;
    }

    public static List<List<Character>> buildCharacterList(String[] noArr) {
        List<List<Character>> characterList2 = new ArrayList<>();
        char[][] chars = str2CharArray2(noArr);
        for (char[] charArr : chars) {
            List<Character> characterList = Arrays.asList(toWrap(charArr));
            characterList2.add(characterList);
        }
        return characterList2;
    }
}
