import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class Sort{
    int[] sample = new int[]{0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 8, 8, 8, 9, 9, 9};
    public static void main(String args[]){
        Sort sort = new Sort();
        int[] nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.selectionSort(nums);
        System.out.println("selection:  " + Arrays.toString(nums));

        nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.bubleSort(nums);
        System.out.println("buble:      " + Arrays.toString(nums));

        nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.insertionSort(nums);
        System.out.println("insertion:  " + Arrays.toString(nums));

        nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.quickSort(nums);
        System.out.println("quick:      " + Arrays.toString(nums));

        nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.heapSort(nums);
        System.out.println("heap:       " + Arrays.toString(nums));

        nums = Arrays.copyOf(sort.sample,sort.sample.length);
        sort.mergeSort(nums);
        System.out.println("merge:      " + Arrays.toString(nums));

    }

    //selectionSort ************************* start
    public void selectionSort(int []nums){
        for(int i=0;i<nums.length;i++){
            int index=i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[index]>nums[j]){
                    index=j;
                }
            }
            swap(nums,index,i);
        }
    }

    //selectionSort ************************* end

    //bubleSort ************************* start
    public void bubleSort(int[] nums){
        if(nums==null||nums.length<2){
            return;
        }
        boolean flag = false;
        do{
            flag=false;
            for(int i=1;i<nums.length;i++){
                if(nums[i-1]>nums[i]){
                    swap(nums, i, i-1);
                    flag=true;
                }
            }

        }while(flag);
    }

    //bubleSort ************************* end

    //insertionSort ************************* start
    public void insertionSort(int[] nums){
        if(nums==null||nums.length<2){
            return;
        }
        if(nums[1]<nums[0]){
            swap(nums,0,1);
        }     
        for(int i=2;i<nums.length;i++){
            adjust(nums, i);
        }
    }

    private void adjust(int[] nums,int index){
        if(nums[index]>nums[index-1]){
            return;
        }else if(nums[index]<nums[0]){
            int tmp = nums[index];
            while(index>0){
                nums[index]=nums[index-1];
                index--;
            }
            nums[0]=tmp;
            return;
        }else{
            for(int i=1;i<index;i++){
                if(nums[index]>=nums[i-1]&&nums[index]<=nums[i]){
                    int tmp = nums[index];
                    while(index>i){
                        nums[index]=nums[index-1];
                        index--;
                    }
                    nums[i]=tmp;
                    return;
                }
            }
        }

    }

    //insertionSort ************************* end

    // quickSort ************************* start
    public void quickSort(int[] nums){
        quickSort(nums,0,nums.length-1);
    }
    private void quickSort(int[] nums,int start,int end){
        if(end<=start){
            return;
        }
        int tmp=nums[start];
        int s=start,e=end;
        while(end>start){
            while(nums[end]>=tmp&&start<end){
                end--;
            }
            nums[start]=nums[end];
            while(nums[start]<=tmp&&start<end){
                start++;
            }
            nums[end]=nums[start];
        } 
        nums[start]=tmp;
        quickSort(nums, s, start);
        quickSort(nums, start+1, e);
    }
    // quickSort ************************* end

    // mergeSort ************************* start
    private void mergeSort(int[] nums){
        int tmp[] = new int[nums.length];
        divide(nums, 0, nums.length-1, tmp);
    }

    private void divide(int[] nums,int start,int end,int[] tmp){
         if(start<end){
            divide(nums,start,(start+end)/2,tmp);
            divide(nums, (start+end)/2+1, end,tmp);
            merge(nums,start,(end+start)/2,end,tmp);
         }
     }

    private void merge(int[] nums,int start,int mid,int end,int[] tmp){
        int s=start,m=mid+1;
        int index=start;
        while(s<=mid&&m<=end){
            if(nums[s]<nums[m]){
                tmp[index++]=nums[s++];
            }else{
                tmp[index++]=nums[m++];
            }
        }
        while(s<=mid){
            tmp[index++]=nums[s++];
        }
        while(m<=end){
            tmp[index++]=nums[m++];
        }
        for(int i=start;i<=end;i++){
            nums[i]=tmp[i];
        }
     }
     // mergeSort ************************* end

     // heapSort ************************* start
    public void heapSort(int nums[]){
        int length = nums.length;
        int start = (length - 1)/2;
        for(int i=start;i>=0;i--){
            heapSort(nums,i,nums.length);
        }
        for(int i=nums.length-1;i>=0;i--){
            swap(nums, 0, i);
            heapSort(nums, 0, i-1);
        }
    }

    private void heapSort(int[] nums,int index,int length){
        if(index<0){
            return;
        }
        int left = 2*index+1;
        int right = left+1;
        int cMax = left;
        if(left>=length){
            return;
        }else if(right==length){
            cMax = left;
        } else{
            if(nums[right]>nums[left]){
                cMax = right;
            }
        }
        if(nums[index]<nums[cMax]){
            swap(nums, index, cMax);
            heapSort(nums, cMax,length);
        }
    }

    private void swap(int[] nums,int a,int b){
        int tmp = nums[a];
        nums[a]=nums[b];
        nums[b]=tmp;
        
    }

    // heapSort ************************* end


}
