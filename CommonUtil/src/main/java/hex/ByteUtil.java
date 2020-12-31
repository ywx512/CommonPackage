package hex;

import java.util.Locale;

/**
 * @author yuweixiong
 * @date 2020/12/31 16:51
 * @description
 */
public class ByteUtil {
    private final static char[] mChars = "0123456789ABCDEF".toCharArray();

    /**
     * bytes转换成十六进制字符串
     *
     * @param bytes byte[] byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String bytesToHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(mChars[(b & 0xFF) >> 4]);
            sb.append(mChars[b & 0x0F]);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    /**
     * bytes字符串转换为Byte值
     *
     * @param src String Byte字符串，每个Byte之间没有分隔符(字符范围:0-9 A-F)
     * @return byte[]
     */
    public static byte[] hexStrToBytes(String src) {
        src = src.trim().replace(" ", "").toUpperCase(Locale.US);

        int m = 0, n = 0;
        int iLen = src.length() / 2;
        byte[] ret = new byte[iLen];

        for (int i = 0; i < iLen; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = (byte) (Integer.decode("0x" + src.substring(i * 2, m) + src.substring(m, n)) & 0xFF);
        }
        return ret;
    }

    /**
     * 获取字节位信息,1为true,0为false
     *
     * @param msg
     * @param index: byte从右数起的第index位
     * @return
     */
    public static boolean getBit(byte msg, int index) {
        byte bit = (byte) ((msg >> index) & (byte) 0x01);
        if (bit == 1) {
            return true;
        }
        return false;
    }

    /**
     * 单个byte的十六进制字符串转byte
     *
     * @param hex
     * @return
     */
    public static byte hexStrToOneByte(String hex) {
        return (byte) (Integer.decode(hex) & 0xFF);
    }

    /**
     * 单个byte转成十六进制字符串
     *
     * @param num
     * @return
     */
    public static String oneByteToHexStr(byte num) {
        StringBuilder sb = new StringBuilder();
        sb.append("0X");
        sb.append(mChars[(num & 0xFF) >> 4]);
        sb.append(mChars[num & 0x0F]);
        return sb.toString().toUpperCase(Locale.US);
    }

    /**
     * 从右边算起，截取byte指定区间位，转成十进制输出 最左位为7位，最右位为0位,第7位为高位，第0位为低位
     * startIndex从最右边数起，到endIndex结束
     *
     * @param msg
     * @param startIndex：区间从startIndex算起，包含该位
     * @param endIndex：区间从endIndex结束，包含该位
     * @return
     */
    public static int getBitValueBaseRight(byte msg, int startIndex, int endIndex) {
        int len = endIndex - startIndex + 1;
        return (msg >> startIndex) & (0xff >> (8 - len));
    }

    /**
     * 从左边算起，截取byte指定区间位，转成十进制输出 最左位为7位，最右位为0位，第7位为高位，第0位为低位
     * startIndex从最左边数起，到endIndex结束
     *
     * @param msg
     * @param startIndex：区间从startIndex算起，包含该位,           startIndex取值：0到7
     * @param endIndex：区间从endIndex结束，包含该位，endIndex取值：0到7
     * @return
     */
    public static int getBitValueBaseLeft(byte msg, int startIndex, int endIndex) {
        return (msg >> (7 - endIndex)) & (0xff >> (7 - endIndex + startIndex));
    }

    /**
     * 将byte数组转成int，支持数组长度为1-4
     *
     * @param bytes，数组必须索引值越小，位数越高，bytes[0]为最高位，bytes[len-1]为最低位
     * @return
     */
    public static int byteToInt(byte[] bytes) {
        int result = 0;
        int len = bytes.length;

        for (int i = 0; i < len; i++) {
            result = (result << 8) | bytes[i] & 0xFF;
        }
        return result;
    }

    /**
     * int转成指定长度的byte数组，数组索引值越小，位数越高，bytes[0]为最高位，bytes[len-1]为最低位
     *
     * @param num
     * @param bytesLen 指定生成byte的数量，范围：1-4
     * @return
     */
    public static byte[] intToByteBaseByteNum(int num, int bytesLen) {
        byte[] bytes = new byte[bytesLen];
        for (int i = 0; i < bytesLen; i++) {
            bytes[i] = (byte) (num >> ((bytesLen - i - 1) * 8));
        }
        return bytes;
    }

    /**
     * int转成指定长度的byte数组，数组索引值越大，位数越高，bytes[len-1]为最高位，bytes[0]为最低位
     * @param num
     * @param bytesLen
     * @return
     */
    public static byte[] intToByteBaseByteNumForLittleEndian(int num, int bytesLen){
        byte[] bytes = new byte[bytesLen];
        for (int i = 0; i < bytesLen; i++) {
            bytes[bytesLen - i -1] = (byte) (num >> ((bytesLen - i - 1) * 8));
        }
        return bytes;
    }

    /**
     * 将byte数组转成int，支持数组长度为1-4
     *
     * @param bytes，数组必须索引值越大，位数越高，bytes[len-1]为最高位，bytes[0]为最低位
     * @return
     */
    public static int byteToIntForLittleEndian(byte[] bytes) {
        int result = 0;
        int len = bytes.length;

        for (int i = 0; i < len; i++) {
            result = result + ((bytes[i] & 0xFF) << (8 * i));
        }
        return result;
    }

    static public String ByteToHex(Byte inByte) {
        return String.format("%02x", inByte).toUpperCase();
    }

    static public String ByteArrToHex(byte[] inBytArr) {
        StringBuilder strBuilder = new StringBuilder();
        int j = inBytArr.length;
        for (int i = 0; i < j; i++) {
            strBuilder.append(ByteToHex(inBytArr[i]));
            // strBuilder.append(" ");
        }
        return strBuilder.toString();
    }

    public static int byteToIntForLittleEndian(byte[] bytes, int start, int end){
        int result = 0;

        for (int i = start; i <= end; i++) {
            result = result + ((bytes[i] & 0xFF) << (8 * (i - start)));
        }
        return result;
    }
}
