package com.rayshaw;

public class deleteKNode{
    /**
     * single list Solution
     */
    public Node removeLastKNode(Node node,int K){
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
     * 
     */
    public Node removeLastKDoubleNode(DoubleNode head,int K){
        if(head == null || K<1){
            return head;
        }
        Node tmp = head;
        while(tmp!=null){
            tmp=tmp.next;
            K--;
        }
        if(K==0){
            tmp=head.next;
            head.next=null;
            tmp.last=null;
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
}