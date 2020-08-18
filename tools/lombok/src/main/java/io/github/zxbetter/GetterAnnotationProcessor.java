package io.github.zxbetter;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Flags;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.*;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

/**
 * {@link Getter} 注解的处理器
 * <p>
 * {@link SupportedAnnotationTypes} 指定该处理器要处理的注解
 * {@link SupportedSourceVersion} 指定该处理器支持的源码版本
 *
 * @author xin.zhang
 */
@SupportedAnnotationTypes({"io.github.zxbetter.Getter"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GetterAnnotationProcessor extends AbstractProcessor {

    /**
     * 用来在编译期打 log
     */
    private Messager messager;

    /**
     * 提供了待处理的抽象语法树
     */
    private JavacTrees javacTrees;

    /**
     * 封装了创建AST节点的一些方法
     */
    private TreeMaker treeMaker;

    /**
     * 提供了创建标识符的方法
     */
    private Names names;

    /**
     * 通过 ProcessingEnvironment 来获取编译阶段的一些环境信息
     * @param processingEnv 编译阶段的环境信息
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.messager = processingEnv.getMessager();
        this.javacTrees = JavacTrees.instance(processingEnv);

        Context context = ((JavacProcessingEnvironment) processingEnv).getContext();
        this.treeMaker = TreeMaker.instance(context);
        this.names = Names.instance(context);
    }

    /**
     * 实现具体逻辑的地方，也就是对AST进行处理的地方
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(Getter.class).forEach(element -> {
            JCTree tree = javacTrees.getTree(element);
            if (tree != null) {
                tree.accept(new TreeTranslator() {
                    @Override
                    public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
                        jcClassDecl.defs.forEach(jcClazz -> {
                            if (Tree.Kind.VARIABLE.equals(jcClazz.getKind())) {
                                JCTree.JCVariableDecl jcVariableDecl = (JCTree.JCVariableDecl) jcClazz;
                                messager.printMessage(Diagnostic.Kind.NOTE, String.format("%s start processing", jcVariableDecl.getName()));
                                jcClassDecl.defs = jcClassDecl.defs.prepend(generateGetterDecl(jcVariableDecl));
                                messager.printMessage(Diagnostic.Kind.NOTE, String.format("%s end processing", jcVariableDecl.getName()));
                            }
                        });
                        super.visitClassDef(jcClassDecl);
                    }
                });
            }
        });
        return true;
    }

    private JCTree.JCMethodDecl generateGetterDecl(JCTree.JCVariableDecl jcVariableDecl) {
        ListBuffer<JCTree.JCStatement> statements = new ListBuffer<>();
        statements.append(treeMaker.Return(treeMaker.Select(treeMaker.Ident(names.fromString("this")), jcVariableDecl.getName())));
        JCTree.JCBlock block = treeMaker.Block(0, statements.toList());
        return treeMaker.MethodDef(treeMaker.Modifiers(Flags.PUBLIC), getMethodName(jcVariableDecl.getName()),
                jcVariableDecl.vartype, List.nil(), List.nil(), List.nil(), block, null);
    }

    private Name getMethodName(Name name) {
        String s = name.toString();
        return names.fromString("get" + s.substring(0, 1).toUpperCase() + s.substring(1, name.length()));
    }
}
