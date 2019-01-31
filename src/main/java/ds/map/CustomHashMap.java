package main.java.ds.map;

public class CustomHashMap<K,V> {
    private Entry<K,V>[] table;   //Array of Entry.
    private int capacity= 4;  //Initial capacity of HashMap

    public CustomHashMap(int capacity) {
        this.table = new Entry[capacity];
    }

    public void put(K newKey, V data){
        if(newKey==null)
            return;    //does not allow to store null.

        Entry<K,V> newEntry = new Entry<K,V>(newKey, data, null);
        int hash=hash(newKey);
        if(table[hash] == null){
            table[hash] = newEntry;
        } else {
            Entry<K,V> previous = null;
            Entry<K,V> current = table[hash];

            while(current != null){ //we have reached last entry of bucket.
                if(current.key.equals(newKey)){
                    if(previous==null){  //node has to be insert on first of bucket.
                        newEntry.next=current.next;
                        table[hash]=newEntry;
                        return;
                    }
                    else{
                        newEntry.next=current.next;
                        previous.next=newEntry;
                        return;
                    }
                }
                previous=current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }

    public V get(K key){
        int hash = hash(key);
        if(table[hash] == null){
            return null;
        }else{
            Entry<K,V> temp = table[hash];
            while(temp!= null){
                if(temp.key.equals(key))
                    return temp.value;
                temp = temp.next; //return value corresponding to key.
            }
            return null;   //returns null if key is not found.
        }
    }

    private int hash(K key){
        return Math.abs(key.hashCode()) % capacity;
    }
}

class Entry<K, V> {
    public K key;
    public V value;
    public Entry<K,V> next;

    public Entry(K key, V value, Entry<K,V> next){
        this.key = key;
        this.value = value;
        this.next = next;
    }
}
