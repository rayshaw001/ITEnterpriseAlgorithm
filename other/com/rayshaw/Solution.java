

import java.util.Set;
import java.util.HashMap;
import java.util.Map;
public class Solution{
    public static void main(String[] args){
        int[] array={1,2,3,4,5,6,7,8,9};
        Solution s = new Solution();
        System.out.print(s.twoSum(array, 5));
    }

    public int[] twoSum(int[] nums, int target){
        Map<Integer,Integer> map = new HashMap();
        for(int n=0;n<nums.length;n++){
            map.put(nums[n],n);
        }
        for(int n=0;n<nums.length;n++){
            if(map.containsKey(target-nums[n])){
                result=new int[]{n,map.get(target-nums[n])};
            }
        }
    }
}