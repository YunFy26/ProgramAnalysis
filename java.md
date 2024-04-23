> 集合

```java
package java.util;
```

- Interface
  - `Collection<E>`  extends Iterable<E> (可迭代)
    - `Set<E>` extends Collection<E> 
      - 实现不可以存储null元素
    - `List<E>` extends Collection<E>
      - 某些实现可以存储null元素
    - `Queue<E>` extends Collection<E>
  - `Map`
    - 
- Abstract class
  - `AbstractCollection`<E> implements Collection<E>
    - `AbstractSet`<E> extends AbstractCollection<E> implements Set<E>
- Concrete class

  - `ArrayList` 
    - 数据规模较小时，使用ArrayList会非常高效，因为使用数组实现，数组在内存中是连续的，所以ArrayList在遍历时非常高效(O(n))，可以通过索引访问元素（O(1)）
    
  - `LinkedList`

Colleciton中的一些基本方法

```java
int size();
boolean isEmpty();
void clear();
Object[] toArray();
<T> T[] toArray(T[] a);


// contains
boolean contains(Object o);
boolean containsAll(Collection<?> c);
// add
boolean add(E e);
boolean addAll(Collection<? extends E> c);
// remove
boolean remove(Object o);
boolean removeAll(Collection<?> c);
boolean retainAll(Collection<?> c);
// 遍历Collection中的元素
Iterator<E> iterator();

// 去重
boolean equals(Object o);
// 当向集合中添加一个元素时，会先调用equals方法检查集合中是否已存在这个元素
int hashCode();
// 首先会使用hashCode值快速判断对象是否相等，只有当两个对象的hashCode值相等时，Set才会调用equals方法比较
```

Map中的一些基本方法

```java
int size();
boolean isEmpty();
void clear();

boolean containsKey(Object key);
boolean containsValues(Object value);

// 获取Map中的元素
V get(Object key); // 获取key对应的value
default V getOrDefault(Object key, V defaultValue); // 如果没有key，则返回defaultValue 
Set<K> keySet(); // 返回Map中key的集合
Collections<V> values(); // 返回Map中所有值的集合
Set<Entry<K, V>> entrySet(); // 返回Map中所有键值对的集合

// 遍历Map中的元素
default forEach()

// 向Map中添加元素
V put(K, V); // 向Map中添加(K, V)的映射
void putAll(Map<? extends K, ? extends V> m);

// 从Map中移除元素
V remove(Object key); // 移除Map中key的映射，并返回key映射的value
default boolean remove(Object key, Object value);

// 更换key映射的value
···
```




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

