class Test {
   public static void main(String[] a){
      System.out.println(7);
   }
}

class Super {
   int a;
   int[] array;
   
   public int foo(Super arg1, int[] arg2) {
      int i;
      return arg2[0];
   }
   
   public int noArgs() {
      int local1;
      int local2;
      local1 = 0;
      return local1;
   }
   
   public int noLocals(int arg1, boolean arg2) {
      return arg1;
   }
}


class Sub extends Super {
   int another;
   
   public int[] makeArray(int size) {
      int actual;
      if (size < 0)
         actual = 0;
      else
         actual = size;
      return new int[actual];
   }
}
