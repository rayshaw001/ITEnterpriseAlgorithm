package com.rayshaw;

public class deleteKNode{
    /**
     * single list Solution
     */
    public Node removeLastKNode(Node head,int K){
        if(head == null || K<1){
            return head;
        }
        Node tmp = head;
        while(tmp!=null){
            tmp=tmp.next;
            K--;
        }
        if(K==0){
            return head.next;
        } else if(K<0){
            tmp=head;
            while(K<0){
                tmp=tmp.next;
            }
            tmp.next=tmp.next.next;
        }
        return head;
    }

    /**
     * doubleNode list
     */
    public DoubleNode removeLastKDoubleNode(DoubleNode head,int K){
        if(head == null || K<1){
            return head;
        }
        DoubleNode tmp = head;
        while(tmp!=null){
            tmp=tmp.next;
            K--;
        }
        if(K==0){
            tmp=head.next;
            head.next=null;
            tmp.last=null;
            return tmp;
        } else if(K<0){
            tmp=head;
            while(K<0){
                tmp=tmp.next;
            }
            DoubleNode deleted = tmp.next;
            tmp.next=tmp.next.next;
            deleted.last=null;
        }
        return head;
    }

    /**
     * remove mid node
     */

     public Node removeMidNode(Node node){
         if(node==null || node.next==null){
             return node;
         }
         if(node.next.next==null){
             return node.next;
         }
         Node pre = node,current = node.next.next;
         while(current.next!=null && current.next.next!=null){
             pre=pre.next;
             current=current.next.next;
         }
         pre.next=pre.next.next;
         return node;
     }

     /**
      * remove location m/n node
      */

      public Node removeNodeByradio(Node head,int m,int n){
        if(m<1 || m>n){
            return head;
        }
        int mark = 0;
        Node tmp = head;
        while(tmp != null){
            mark++;
            tmp=tmp.next;
        }
        mark = (int)Math.ceil(((double)(m*mark))/(double)n);
        if(mark==1){
            return head.next;
        }else if(mark > 1){
            tmp=head;
            while(mark>1){
                tmp=tmp.next;
            }
            tmp.next=tmp.next.next;
        }
        return head;
      }
}