// 26 ms | 100.2 MB
class RandomizedSet {
    
    private ArrayList<Integer> list;
    private HashMap<Integer, Integer> map;

    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }

        list.add(val);

        map.put(val, list.size() - 1);

        return true;
    }
    
    public boolean remove(int val) {
        
        if(!map.containsKey(val)){
            return false;
        }

        int index = map.get(val);

        int lastValue = list.get(list.size() - 1);

        list.set(index, lastValue);

        map.put(lastValue, index);

        list.remove(list.size() - 1);

        map.remove(val);

        return true;
    }

    
    public int getRandom() {
        int index = (int) (Math.random() * list.size());

        return list.get(index);
    }
}
