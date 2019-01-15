import java.util.Map;
import java.util.HashMap;
class LRUCache {
    private int capacity;
    Map<Integer,Node> map;
    Node head,tail;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<Integer,Node>();
    }
    
    public int get(int key) {
        int result = -1;
        Node tmp = map.get(key);
        if(tmp!=null){
            result = tmp.value;
            moveToTail(tmp);
        }
        return result;
    }
    
    public void put(int key, int value) {
        Node update = map.get(key);
        if(map.size()==0){
            update = new Node(key,value);
            head=update;
            tail=update;
            map.put(key,update);
            return;
        } else if(update == null){
            update = new Node(key,value);
            tail.next=update;
            update.prev=tail;
            tail = update;
            map.put(key,update);
            if(map.size()>capacity){
                Node tmp = head;
                map.remove(head.key);
                head=head.next;
                tmp.next=null;
                head.prev=null;
            }
        } else {
            update.value=value;
            moveToTail(update);
        }
    }
    private void moveToTail(Node update){
        if(update.equals(head)){
            update.prev=tail;
            tail.next=update;
            tail=update;
            head=update.next;
            update.next=null;
            return;
        }
        if(update.equals(tail)){
            return;
        }
        update.next.prev=update.prev;
        update.prev.next=update.next;
        tail.next=update;
        update.prev=tail;
        update.next=null;
        tail=update;
    }

    public static void main(String args[]){
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1,1);//1
        lruCache.put(2,2);//1 2
        lruCache.put(3,3);//1 2 3
        lruCache.put(4,4);//2 3 4
        lruCache.put(2,2);//3 4 2
        lruCache.put(1,1);//4 2 1
        lruCache.put(2,8);//4 1 2
        lruCache.put(3,5);//1 2 3
        lruCache.put(5,9);//2 3 5
        lruCache.put(4,5);//2 1 5
        lruCache.put(2,88);//2 1 5
        lruCache.put(15,3);//2 1 5
        lruCache.put(32,25);//2 1 5
        lruCache.put(46,72);//2 1 5
        lruCache.put(12,3);//2 1 5
        lruCache.put(7,12);//2 1 5
        lruCache.put(23,5);//2 1 5
        lruCache.put(5,6);//2 1 5
        lruCache.put(78,54);//2 1 5
    }
}

class Node{
    public Node next,prev;
    public int key;
    public int value;
    public Node(int key,int value){
        this.key=key;
        this.value=value;
    }
    
    public boolean equals(Node node){
        return node!=null?this.key==node.key:false;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */