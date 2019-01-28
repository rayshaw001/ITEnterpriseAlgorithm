import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Node{
    int val;
    public Node left,right;
    public Node(int value){
        this.val=value;
    }


//                 0
//                / \
//               1   2
//              / 
//             3   
//              \     
//               4     
//                \
//                  5
    public static void main(String args[]){
        Node target = rebuildTree(new int[]{0,1,3,4,5,2},new int[]{3,4,5,1,0,2});
        for(Node node:new Node(0).travelTreeByPre(target)){
            System.out.print(node.val+"\t");
        }
        System.out.println();
        for(Node node:new Node(0).travelTreeByMid(target)){
            System.out.print(node.val+"\t");
        }
        System.out.println();
        for(Node node:new Node(0).travelTreeByLast(target)){
            System.out.print(node.val+"\t");
        }

    }

    //last
    public List<Node> travelTreeByLast(Node head){        
        Queue<Node> q = new LinkedList<Node>();
        travelTreeByLast(head,q);
        List<Node> result = new LinkedList<Node>();
        while(!q.isEmpty()){
            result.add(q.poll());
        }
        return result;
    } 

    private void travelTreeByLast(Node head,Queue q){
        if(head==null){
            return;
        }
        if(head.left!=null){
            travelTreeByLast(head.left,q);
        }
        if(head.right!=null){
            travelTreeByLast(head.right,q);
        }
        q.add(head);
    }

    //mid
    public List<Node> travelTreeByMid(Node head){        
        Queue<Node> q = new LinkedList<Node>();
        travelTreeByMid(head,q);
        List<Node> result = new LinkedList<Node>();
        while(!q.isEmpty()){
            result.add(q.poll());
        }
        return result;
    } 

    private void travelTreeByMid(Node head,Queue q){
        if(head==null){
            return;
        }
        if(head.left!=null){
            travelTreeByMid(head.left,q);
        }
        q.add(head);
        if(head.right!=null){
            travelTreeByMid(head.right,q);
        }
    }

    //pre
    public List<Node> travelTreeByPre(Node head){        
        Queue<Node> q = new LinkedList<Node>();
        travelTreeByPre(head,q);
        List<Node> result = new LinkedList<Node>();
        while(!q.isEmpty()){
            result.add(q.poll());
        }
        return result;
    } 

    private void travelTreeByPre(Node head,Queue q){
        if(head==null){
            return;
        }
        q.add(head);
        if(head.left!=null){
            travelTreeByPre(head.left,q);
        }
        if(head.right!=null){
            travelTreeByPre(head.right,q);
        }
    }

    //maxDepth
    public static int maxX(Node head){
        if(head==null){
            return 0;
        }

        return Math.max(maxX(head.left)+1, maxX(head.right)+1);
    }

    public static List<List<Node>> travelByLevel(Node head){
        Queue<Node> q1 = new LinkedList<Node>();
        Queue<Node> q2 = new LinkedList<Node>();
        Queue<Node> q;
        List<List<Node>> results = new LinkedList<List<Node>>();
        List<Node> result;
        Node tmp;
        int size = 0;
        if(head==null){
            return Collections.emptyList();
        }
        q1.add(head);
        while(!q1.isEmpty()){
            result = new LinkedList<Node>();
            while(!q1.isEmpty()){
                tmp = q1.poll(); 
                result.add(tmp);
                if(tmp.left!=null){
                    q2.add(tmp.left);
                }
                if(tmp.right!=null){
                    q2.add(tmp.right);
                }
            }
            results.add(result);
            q=q2;
            q2=q1;
            q1=q;
        }
        return results;
    }

    public static List<List<Node>> travelByLevelUsingflag(Node head){
        Queue<Node> q = new LinkedList<Node>();
        List<List<Node>> results = new LinkedList<List<Node>>();
        List<Node> result = new LinkedList<Node>();
        Node tmp;
        int size = 0,newSize=0;
        if(head==null){
            return Collections.emptyList();
        }
        size=1;
        q.add(head);
        while(!q.isEmpty()){
            tmp = q.poll();
            result.add(tmp);
            size--;
            if(tmp.left!=null){
                q.add(tmp.left);
                newSize++;
            }
            if(tmp.right!=null){
                q.add(tmp.right);
                newSize++;
            }
            if(size==0){
                size=newSize;
                newSize=0;
                results.add(result);
                result = new LinkedList<Node>();
            }
        }
        return results;
    }

    public static Node rebuildTree(int[] pre,int[] mid){
        return rebuildTree(pre, 0, pre.length-1, mid, 0, mid.length-1);
    }
    private static Node rebuildTree(int[] pre,int ps,int pe,int[] mid, int ms, int me){
        Node result = null;
        if(pe>=ps&&me>=ms){
            int head = pre[ps];
            result = new Node(head);
            int mid_index = findValue(mid, head);
            result.left = rebuildTree(pre, ps+1, mid_index-ms+ps, mid, ms, mid_index-1);                  //mid_index-1-ms=target-ps-1
            result.right = rebuildTree(pre, pe-me+mid_index+1, pe, mid, mid_index+1, me);                 //pe-target=me-mid_index-1;    target=pe-me+mid_index+1
        }
        return result;
    }

    private static int findValue(int []values, int target){
        int index = 0;
        for(int i=0;i<values.length;i++){
            if(values[i]==target){
                index=i;
                break;
            }   
        }
        return index;
    }

    
}
