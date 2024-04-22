# About the Repo

- Final Goal ：基于Tai-e的IR实现自己的静态分析工具

- Now
  - 学习向，首先复现Tai-e
  - 记录自己开发过程中所学习到的知识点，同步产出关于java和静态分析的学习笔记
  - 分解Tai-e项目，同步产出Tai-e的设计文档（从开发者的角度而非使用者的角度）
  - 记录工作，减少摸鱼次数
- ...

---

# Design

...



# 数据结构

> Views.java

- java.util.function.Function

  函数式接口，有一个方法apply()接受一个参数并返回一个结果

  ```java
  Function<String, Integer> lengthFunction = str -> str.length();
  int length = lengthFunction.apply("Hello");  // length will be 5
  ```

- java.util.function.Predicate

  函数式接口，有一个方法test，接收一个参数并返回布尔值结果

  ```java
  Predicate<Integer> greaterThanZero = num -> num > 0;
  boolean result = greaterThanZero.test(5);  // result will be true
  ```

- 



> Map

- Interface
  - `MultiMap`
    - 类似Map接口，但是提供一对多的映射
- Abstract Class
  - `AbstractMultiMap`
- Concrete Class
  - `Maps`<Factory>
    - 工厂类

>Set

- Interface
  - `SetEx`
    - 对Set接口的扩展，添加了addAllReturnDiff方法
- Abstract Class
  - `AbstractSetEx` extends AbstractSet implements SetEx
    - 对AbstractSet类的扩展
    - AbstractSet 类继承AbstractCollection，实现了Set接口
    - AbstractSetEx类继承AbstractSet ，实现了SetEx接口
  - `AbstractHybridSet` extends AbstractSetEx implements Serializable
    - 抽象类，可根据集合大小自动切换底层数据结构（小：ArraySet，大：HybridHashSet）
- Concrete Class
  - `Sets`<Factory>
    - 工厂类
  - `HybridHashSet` extends AbstratctHybridSet implements Serializable
    - 用于存储规模较大的数据
  - `ArraySet` extends AbstractEx implements Serializable
    - 用于存储规模较小的数据