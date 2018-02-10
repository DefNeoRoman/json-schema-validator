package com.groupon.maven.plugin.tree;

import java.util.*;

public class TreeLevel implements Comparable<TreeLevel>{

    private Integer level;
    private List<String> keys;
    private Set<String> uniqueKeys;

    public TreeLevel(Integer level) {
        this.level = level;
        keys = new LinkedList<>();
        uniqueKeys = new HashSet<>();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public Set<String> getUniqueKeys() {
        return uniqueKeys;
    }

    public void setUniqueKeys(Set<String> uniqueKeys) {
        this.uniqueKeys = uniqueKeys;
    }
    public void addKey(String string){
        keys.add(string);
    }
    public void addUniqueKey(String string){
        uniqueKeys.add(string);
    }
    public boolean hasDuplicate(){
        return !(keys.size() == uniqueKeys.size());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TreeLevel treeLevel = (TreeLevel) o;
        return  Objects.equals(level, treeLevel.level) &&
                Objects.equals(keys, treeLevel.keys) &&
                Objects.equals(uniqueKeys, treeLevel.uniqueKeys);
    }

    @Override
    public int hashCode() {

        return Objects.hash(level, keys, uniqueKeys);
    }

    @Override
    public int compareTo(TreeLevel o) {
        return this.getLevel().compareTo(o.getLevel());
    }
}
