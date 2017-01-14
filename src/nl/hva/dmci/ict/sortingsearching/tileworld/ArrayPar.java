package nl.hva.dmci.ict.sortingsearching.tileworld;

/**
 * Created by mark on 19-12-2016.
 */
class ArrayPar {

    private long[] theArray = {12,37,27,16,11,41,7,20} ;
    private int nElems = 8;
    //--------------------------------------------------------------
    public int partitionIt(int left, int right) {
        int leftPtr = left - 1;
        int rightPtr = right;
        long pivot = theArray[right];

        while(true) {
            while(theArray[++leftPtr] < pivot)
                ; // LET OP: leeg block!
            while(rightPtr > left && theArray[--rightPtr] > pivot)
                ; // LET OP: leeg block!
            if(leftPtr >= rightPtr) break ;
            else swap(leftPtr, rightPtr);
        }
        swap(leftPtr, right); // vergeet deze niet te tellen!
        return leftPtr;
    }

    public void swap(int dex1, int dex2) { // swap two elements
        System.out.println(" SWAP WITH: " + dex1 +" | "+ dex2 );
        long temp = theArray[dex1];
        theArray[dex1] = theArray[dex2];
        theArray[dex2] = temp;
    }
} // end class ArrayPar

class PartitionApp {

    public static void main(String[] args){
        int maxSize = 8;
        ArrayPar arr = new ArrayPar();
        int partition = arr.partitionIt(0, maxSize-1);
    }
}
