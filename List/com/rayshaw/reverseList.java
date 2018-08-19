package com.rayshaw;

public class reverseList{
    /**
     * reverse Single List
     */
    public Node reverseLists(Node head){
        if(head==null || head.next==null){
            return head;
        }
        Node pre = null, next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * reverse DoubleNode List
     */
    public DoubleNode reverseDoubleNodeLists(DoubleNode head){
        if(head==null || head.next==null){
            return head;
        }
        DoubleNode pre = null, next = null;
        while(head!=null){
            next = head.next;
            head.last = next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        int a[]={1,2,3,4,5,6,7,8,9};
        DoubleNode head = new DoubleNode(0);
        DoubleNode tmp = new DoubleNode(0);
        head.next = tmp;
        for (int i :a){
            DoubleNode t= new DoubleNode(i);
            tmp.next = t;
            t.last = tmp;
            tmp = tmp.next;
        }
        tmp=head.next;
        while(tmp!=null){
            System.out.println(tmp.value);
            tmp=tmp.next;
        }
        reverseList rl = new reverseList();
        tmp=rl.reverseDoubleNodeLists(head.next.next);
        int i = 1;
        while(tmp!=null){
            System.out.println(tmp.value);
            i=tmp.value==1?-1:1;
            tmp=i>0?tmp.next:tmp.last;

        }
    }

}