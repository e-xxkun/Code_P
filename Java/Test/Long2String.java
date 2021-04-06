public class Long2String {

  public static long stringToLong(String str) {
    long tmp = 0;
    tmp += str.charAt(0);
    tmp += (long)str.charAt(1) << 16;
    tmp += (long)str.charAt(2) << 32;
    tmp += (long)str.charAt(3) << 48;
    return tmp;
  }

  public static String longToString(long value) {
    char[] charArr = new char[4];
    charArr[0] = (char) (value & 0xFFFF);
    charArr[1] = (char) (value >> 16 & 0xFFFF);
    charArr[2] = (char) (value >> 32 & 0xFFFF);
    charArr[3] = (char) (value >> 48 & 0xFFFF);
    return new String(charArr);
  }

  public static void main(String[] args) {

    long startTime=System.currentTimeMillis();
    long value = 0L;
    for (int i = 0;i < 100000000;i ++) {
      String str = longToString(value);
      value = stringToLong(str);
      value += 4;
    }
    long endTime=System.currentTimeMillis();

    System.out.println("function 1 running time： "+(endTime-startTime)+"ms"); 
    
    long startTime2=System.currentTimeMillis();
    long value2 = 0L;
    for (int i = 0;i < 100000000;i ++) {
      String str2 = Long.toString(value2);
      value2 = Long.parseLong(str2);
      value2 += 4;
    }
    long endTime2=System.currentTimeMillis();
    
    System.out.println("function 2 running time： "+(endTime2-startTime2)+"ms"); 
  }
}