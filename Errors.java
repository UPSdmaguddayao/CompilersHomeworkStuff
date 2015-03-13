class Main {
    public static void main(String[] a){
        System.out.println(0);
    }
}

class Errors {
    int[] ints;
    int n;
    int[] ints;  // Local var redeclaration.  Delete and retry

    // Formal param redeclaration (delete one to continue tests)
    public int pointless(int arg, int arg) {
        return 7;
    }

    // Method redeclaration (no overloading in MiniJava)
    // Delete this method to continue tests
    public int pointless(int i, int j, int k) {
        return 7;
    }

    // Formal clashes with local
    public int localFormalClash(int arg) {
        boolean arg;
        return 7;
    }
}

// Shouldn't be able to have TWO classes named Errors
class Errors {
    int foo;
}