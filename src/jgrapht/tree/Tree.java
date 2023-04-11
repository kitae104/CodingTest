package jgrapht.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tree<T> {
    public final T value;
    private List<Tree<T>> children;

    private Tree(T value){
        this.value=value;
        this.children = new ArrayList<>();
    }

    public static <T> Tree<T> of(T value){
        return new Tree<>(value);
    }

    public Tree<T> addChild(T value){
        Tree<T> newChild = new Tree<>(value);
        children.add(newChild);
        return newChild;
    }

    public String toString(){
        String s ="node: "+value+" - children: ("+
                children.stream().map(child -> child.value.toString()).collect(Collectors.joining(","))+
                ")\n";
        for(Tree<T> child : children)
            s+=child.toString();
        return s;
    }
}