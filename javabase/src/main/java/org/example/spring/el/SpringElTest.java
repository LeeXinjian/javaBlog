package org.example.spring.el;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.common.CompositeStringExpression;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.expression.ParserContext.TEMPLATE_EXPRESSION;

public class SpringElTest {

    public static void main(String[] args) {

        CompositeStringExpression expression = (CompositeStringExpression) new SpelExpressionParser()
                .parseExpression("#{ (#a+#b)/#c }", TEMPLATE_EXPRESSION);

        //解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
        Expression[] expressions = expression.getExpressions();

        // 解析的EL表达式结果ch
        List<String> formulaElementList = Arrays.stream(expressions)
                .filter(entity -> entity instanceof SpelExpression)
                .map(entity -> entity.getExpressionString().replaceAll("#", ""))
                .collect(Collectors.toList());

        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("a", 2);
        context.setVariable("b", 4);
        context.setVariable("c", 3);
        System.out.println(expression.getValue(context));

//
//        //测试SpringEL解析器
//        String template = "#user+#student-#formula";//设置文字模板,其中#{}表示表达式的起止，#user是表达式字符串，表示引用一个变量。
//        ExpressionParser paser = new SpelExpressionParser();//创建表达式解析器
//
//        //通过evaluationContext.setVariable可以在上下文中设定变量。
//        EvaluationContext context = new StandardEvaluationContext();
//        context.setVariable("user", "黎明");
//
//        //解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
//        CompositeStringExpression expression = (CompositeStringExpression) paser.parseExpression(template);
//        Expression[] expressions = expression.getExpressions();
//
//        // 解析的EL表达式结果
//        List<String> formulaElementList = Arrays.stream(expressions)
//                .filter(entity -> entity instanceof SpelExpression)
//                .map(entity -> entity.getExpressionString().replaceAll("#", ""))
//                .collect(Collectors.toList());
//
//        formulaElementList.forEach(System.out::println);
//
//        //使用Expression.getValue()获取表达式的值，这里传入了Evalution上下文，第二个参数是类型参数，表示返回值的类型。
//        System.out.println(expression.getValue(context, String.class));
    }
}
