class RangeModule {
    
    TreeMap<Integer,Integer> treeMap;
    public RangeModule() {
        treeMap = new TreeMap<>();
    }
    
    public void addRange(int left, int right) {
        if(left>=right)  return ;
        
        Integer start = treeMap.floorKey(left);
        Integer end   = treeMap.floorKey(right);
        
        if(start == null && end == null){
            treeMap.put(left, right);
            
        }else if(start != null && left <= treeMap.get(start)){
            treeMap.put(start, Math.max(right,Math.max(treeMap.get(start),treeMap.get(end))));
        }else{
            treeMap.put(left, Math.max(right,treeMap.get(end)));
        }
        
        Map<Integer,Integer> subMap = treeMap.subMap(left,false,right,true);
        HashSet<Integer> set = new HashSet<>(subMap.keySet());
        treeMap.keySet().removeAll(set);
    }       
        
        
    
    public boolean queryRange(int left, int right) {
        if(left>= right) return false;
        
        Integer start = treeMap.floorKey(left);
        if(start == null ) return false;
        
        Integer end = treeMap.get(start);
        return right<=end;
    }
    
    public void removeRange(int left, int right) {
        if(left>= right) return;
        
        Integer start = treeMap.floorKey(left);
        Integer end   = treeMap.floorKey(right);
        
        if(end != null && right < treeMap.get(end)){
            treeMap.put(right, treeMap.get(end));
            
        }
        if(start != null && left <= treeMap.get(start)){
            treeMap.put(start,left);
        }
        
        
        Map<Integer,Integer> subMap = treeMap.subMap(left,true,right,false);
        HashSet<Integer> set = new HashSet<>(subMap.keySet());
        treeMap.keySet().removeAll(set);
        
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
