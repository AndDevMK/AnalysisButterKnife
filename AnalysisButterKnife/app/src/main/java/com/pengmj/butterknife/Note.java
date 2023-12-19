package com.pengmj.butterknife;


/**
 * APT全称为Annotation Processing Tool（注解处理工具），它是javac的一个工具。APT可以用来在编译时扫描和处理注解。
 * 通过APT可以获取到注解和被注解对象的相关信息，在拿到这些信息后我们可以根据需求来自动的生成一些代码，省去了手动编写。
 * 注意，获取注解及生成代码都是在代码编译时候完成的，相比反射在运行时处理注解大大提高了程序性能。
 * <br>
 * <br>
 * <img src="../../../../../../../imgs/java编译过程.png" />
 * <br>
 * <br>
 *
 * Element介绍：它是一个接口，表示一个程序元素。可以理解为java文件已经被拆分为各种元素包装起来，方便获取使用。它主要有以下重要的实现类： <br>
 *    a.PackageElement：包节点对象，封装包的信息，提供对有关包及其成员的信息的访问。<br>
 *    b.ExecutableElement：表示某个类或接口的方法、构造方法。<br>
 *    c.TypeElement：表示一个类或接口程序元素。提供对有关类型及其成员的信息的访问。<br>
 *    d.VariableElement：表示一个字段、enum常量、方法或构造方法参数、局部变量。<br>
 * <br>
 * <br>
 * 自定义注解处理器步骤：<br>
 * 1、创建一个java/kotlin Library，定义注解类 <br>
 * 2、创建一个java/kotlin Library，定义注解处理器，新建一个类继承AbstractProcessor，实现相关方法，
 * 主要在process方法中处理java文件的生成，其生成方式主要有两种： <br>
 *    a.使用第三方JavaPoet <br>
 *    b.使用自带JavaWriter <br>
 * 3、注册注解处理器： <br>
 *    a.通过第三方AutoService自动注册 <br>
 *    b.手动添加META-INF信息 <br>
 * <br>
 * <br>
 * SPI全称为Service Provider Interface（服务提供者接口），它是JDK内置的一种服务提供发现机制， <br>
 * 可以用来加载框架扩展和替换组件，主要是被框架的开发人员使用。能将接口与实现分离，达到一个解耦的情况 <br>
 * 主要思想是将装配的控制权移到程序之外，是“基于接口的编程＋策略模式＋配置文件”组合实现的动态加载机制 <br>
 * 缺点： <br>
 * 不能按需加载，需要遍历所有的实现，并实例化，然后在循环中才能找到我们需要的实现。 <br>
 * 如果不想用某些实现类，或者某些类实例化很耗时，它也被载入并实例化了，这就造成了浪费。 <br>
 * <br>
 * <br>
 * <img src="../../../../../../../imgs/SPI运行机制.png" />
 * <br>
 * <br>
 * 本文源码分析基于ButterKnife 10.2.3
 */
public class Note {

}
