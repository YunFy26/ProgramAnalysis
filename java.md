> 集合

```java
package java.util;
```

- Interface
  - Collection<E>  extends Iterable<E> (可迭代)
  - Set<E> extends Collection<E>

- Abstract class
  - AbstractCollection<E> implements Collection<E>
  - AbstractSet<E> extends AbstractCollection<E> implements Set<E>
- Concrete class



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

    