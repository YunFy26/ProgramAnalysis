> 集合

```java
package java.util;
```

- Interface
  - `Collection<E>`  extends Iterable<E> (可迭代)
    - `Set<E>` extends Collection<E>
    - `List<E>` extends Collection<E>
    - `Queue<E>` extends Collection<E>
  
- Abstract class
  - `AbstractCollection`<E> implements Collection<E>
    - `AbstractSet`<E> extends AbstractCollection<E> implements Set<E>
  
- Concrete class

  - `ArrayList` 

    <details><summary>当数据规模较小时，使用ArrayList会非常高效</summary>
        使用数组实现，数组在内存中是连续的，所以ArrayList在遍历时非常高效(O(n))<br>
        可以通过索引访问元素（O(1)）
    </details>

  - `LinkedList`




> 字符串

```java
package java.util;
```

- Interface

- 

- Abstract class

- 

- Concrete class

  - StringJoiner

    ```java
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}"); // 分隔符，前缀，后缀
        for (K key : keySet()){
            joiner.add(key + "=" + get(key));
        }
        return joiner.toString();
    }
    // 键为 "One" 和 "Two"，对应的值的集合分别为 "[1, 2]" 和 "[3, 4]"
    // 那么这个方法将返回 "{One=[1, 2], Two=[3, 4]}"
    ```


