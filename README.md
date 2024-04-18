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
- Abstract Class
  - `AbstractMultiMap`
- Concrete Class
  - `Maps`<Factory>

>Set

- Interface
  - `SetEx`
- Abstract Class
  - `AbstractSetEx` extends AbstractSet implements SetEx
  - `AbstractHybridSet` extends AbstractSetEx implements Serializable
- Concrete Class