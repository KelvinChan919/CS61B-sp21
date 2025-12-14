package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> comparator;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.comparator = c;
    }
    public T max(){
        return max(this.comparator);
    }
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }else{
            int maxIndex = 0;
            Comparator<T> maxComparator = c;
            for(int i = 0; i < size(); i++){
                if(maxComparator.compare(get(i),get(maxIndex))>0){
                    maxIndex = i;
                }
            }
            return get(maxIndex);
        }
    }
}
