package com.pengmj.bindview

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.element.VariableElement
import javax.tools.Diagnostic

class MyBindViewCompiler : AbstractProcessor() {

    companion object {
        val TAG = MyBindViewCompiler::class.java.simpleName
    }

    private var processingEnv: ProcessingEnvironment? = null

    /**
     * 初始化，processingEnv可以获取到Filer、Messager一些工具类
     */
    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        this.processingEnv = processingEnv
    }

    /**
     * 处理注解，roundEnv获取源码上注解的Element信息
     * @return true当前process处理，false不处理，交给后面的process处理
     */
    override fun process(p0: MutableSet<out TypeElement>?, p1: RoundEnvironment?): Boolean {
        log(TAG,"process执行了")
        // 获取所有被MyBindView注解标记的Element信息
        val elements = p1?.getElementsAnnotatedWith(MyBindView::class.java)
        val map = HashMap<String, ArrayList<VariableElement>>()
        elements?.let {
            for (element in it) {
                // 因为MyBindView注解只用于Field，所以element可以强转为VariableElement
                val variableElement = element as VariableElement
                // enclosingElement表示返回封装此元素（非严格意义上）的最里层元素
                // 通过成员变量元素获取它所属的activity类的名字
                // fix: 这里有一个问题，如果有两个不同包但同名的activity，它们内部的成员变量都会被归为同一个集合里，要是能获取到包名就可以分得更细
                val activityName = variableElement.enclosingElement.simpleName.toString()
                //log(TAG, "activityName: $activityName")
                // 已修复上面归类不够细的问题
                val packageName =
                    processingEnv?.elementUtils?.getPackageOf(variableElement).toString()
                //log(TAG, "packageName: $packageName")
                // 类名全路径
                val qualifiedName = "$packageName.$activityName"
                // 根据activityName拿到对应的集合
                var variableElements = map[qualifiedName]
                if (variableElements == null) {
                    variableElements = ArrayList()
                    map[qualifiedName] = variableElements
                }
                variableElements.add(element)
                //log(TAG, "map size: ${map.size}")
            }
        }
        // 遍历map，开始生成java文件
        for ((qualifiedName, variableElements) in map) {
            val packageName =
                processingEnv?.elementUtils?.getPackageOf(variableElements[0]).toString()
            val activityName = variableElements[0].enclosingElement?.simpleName.toString()
            val javaFileObject =
                processingEnv?.filer?.createSourceFile("${qualifiedName}_BindView")
            val sBuilder = StringBuilder().apply {
                // 生成包名
                append("package $packageName;\n")
                // 导包
                append("import com.pengmj.bindview.IBindView;\n")
                // 写类
                append("public final class ${activityName}_BindView implements IBindView<${qualifiedName}> {\n")
                // 写IBindView的bind方法
                append("public void bind (${qualifiedName} target) {\n")
                for (element in variableElements) {
                    // 控件的名字
                    val viewName = element.simpleName.toString()
                    // 控件的id
                    val viewId = element.getAnnotation(MyBindView::class.java).value
                    // 控件类型
                    val viewType = element.asType()
                    append("target.$viewName = ($viewType) target.findViewById($viewId);\n")
                }
                // 方法结尾
                append("}\n")
                // 类结尾
                append("}\n")
            }
            // notes：这里的Writer写完一定要关闭，否则生成的文件没内容
            javaFileObject?.openWriter()?.use {
                it.write(sBuilder.toString())
            }
        }
        return false
    }

    /**
     * 设置Diagnostic.Kind.ERROR会抛出异常信息：
     * java.lang.reflect.InvocationTargetException (no error message)
     */
    private fun log(tag: String, msg: String) {
        processingEnv?.messager?.printMessage(Diagnostic.Kind.NOTE, "[$tag] $msg")
    }

    /**
     * 声明注解处理器要处理的注解类型
     */
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return hashSetOf(MyBindView::class.java.canonicalName)
    }

    /**
     * 声明注解处理器要支持的源码版本
     */
    override fun getSupportedSourceVersion(): SourceVersion {
        return processingEnv.sourceVersion
    }
}