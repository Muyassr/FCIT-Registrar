
public class shifitng {

    public static void main(String[] args) {

//    String arr[] = { "A","w","f","g","q",null,null };
//    
//        System.out.println("before shifting: ");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+" ");
//        }
//        System.out.println("\n");
//        
//        int ind = 4;
//        
//        for (int i = ind; i < arr.length-1; i++) {
//            arr[i]=arr[i+1];
//        }
//        arr[arr.length-1]=null;
//        
//        System.out.println("after shifting: ");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+" ");
//        }
//        
//        System.out.println("");
        //3
        int low = 0 , high = 3-1, mid = 0, found = 0 ;
        int key = 32;
        int arr[] = {12,32,3,85,0,0,0};
        
        while(low<=high) {
            mid = (low+high)/2;
            if(arr[mid]==(key)) {
                found= 0;
                
            } else if(key<arr[mid]) {
                    high=mid-1;
            } else if(key>arr[mid]) {
                low = mid+1;
            }
        }
        
        
        System.out.println(found);
    }
    
}
