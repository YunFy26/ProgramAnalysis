package org.example.utils.dataStructure;

import lombok.NonNull;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Views {
    /**
     * 给定一个Collection和Mapper，返回一个不可变的Collection
     * @param c 原Collection
     * @param mapper 映射函数
     * @param contains 检查新的Collection视图中是否包含一个给定的对象
     * @return 返回一个不可变的Collection
     * @param <T> 原集合中的元素类型
     * @param <R> 新集合中的元素类型
     */
    public static <T, R> Collection<R> toMappedCollection(Collection<T> c, Function<T, R> mapper, Predicate<Object> contains){
        return Collections.unmodifiableCollection(new MappedCollectionView<>(c, mapper, contains));
    }

    public static <T, R> Collection<R> toMappedCollection(Collection<T> c, Function<T, R> mapper){
        return Collections.unmodifiableCollection(new MappedCollectionView<>(c, mapper));
    }

    private static class MappedCollectionView<T, R> extends AbstractCollection<R> {

        private final Collection<T> oldCollection;

        private final Function<T, R> mapper;

        private final Predicate<Object> contains;

        public MappedCollectionView(Collection<T> collection, Function<T, R> mapper, Predicate<Object> contains) {
            this.oldCollection = collection;
            this.mapper = mapper;
            this.contains = contains;
        }

        public MappedCollectionView(Collection<T> collection, Function<T, R> mapper) {
            this.oldCollection = collection;
            this.mapper = mapper;
            this.contains = super::contains;
        }

        @Override
        public boolean contains(Object o) {
            return contains.test(o);
        }

        @Override
        @NonNull
        public Iterator<R> iterator() {
            return new Iterator<>() {

                private final Iterator<T> iterator = oldCollection.iterator();

                @Override
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                @Override
                public R next() {
                    return mapper.apply(iterator.next());
                }
            };
        }

        @Override
        public int size() {
            return oldCollection.size();
        }
    }

    /**
     * 给定一个Collection和Mapper，返回一个不可变的Set
     * @param c 原Collection
     * @param mapper 映射函数
     * @param contains 检查新的Set视图中是否包含一个给定的对象
     * @return 返回一个不可变的Set
     * @param <T> 原集合中的元素类型
     * @param <R> 新集合中的元素类型
     */
    public static <T, R> Collection<R> toMappedSet(Collection<T> c, Function<T, R> mapper, Predicate<Object> contains){
        return Collections.unmodifiableCollection(new MappedSetView<>(c, mapper, contains));
    }

    public static <T, R> Collection<R> toMappedSet(Collection<T> c, Function<T, R> mapper){
        return Collections.unmodifiableCollection(new MappedSetView<>(c, mapper));
    }

    private static class MappedSetView<T, R> extends MappedCollectionView<T, R> implements Set<R> {

        public MappedSetView(Collection<T> collection, Function<T, R> mapper, Predicate<Object> contains) {
            super(collection, mapper, contains);
        }

        public MappedSetView(Collection<T> collection, Function<T, R> mapper) {
            super(collection, mapper);
        }

        /**
         * 比较两个集合是否相等
         * 如果obj == this，返回true
         * 如果obj不是Set实例，返回false（因为当前对象是Set）
         * 把obj强转为Collection，如果size不相等，返回false
         * 调用containsAll方法判断当前集合是否包含c中的所有元素，是则返回true，如果抛出异常，返回false
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Set)) {
                return false;
            }

            Collection<?> c = (Collection<?>) obj;
            if (c.size() != size()) {
                return false;
            }
            try{
                return containsAll(c);
            } catch (ClassCastException | NullPointerException e){
                return false;

            }
        }

        @Override
        public int hashCode() {
            int h = 0;
            for (R r : this) {
                h += Objects.hashCode(r);
            }
            return h;
        }
    }

}
