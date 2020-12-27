package xxkun;; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import static org.junit.Assert.assertEquals;

/** 
* LeetCode22 Tester. 
* 
* @author <Authors name> 
* @since <pre>10ÔÂ 11, 2020</pre> 
* @version 1.0 
*/ 
public class LeetCode22Test { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: generateParenthesis(int n) 
* 
*/ 
@Test
public void testGenerateParenthesis() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: _generate(int left, int right, int n, String parenthesis, List<String> res) 
* 
*/ 
@Test
public void test_generate() throws Exception {
    assertEquals("Fail","[((())), (()()), (())(), ()(()), ()()()]",LeetCode22.generateParenthesis(3));
/* 
try { 
   Method method = LeetCode22.getClass().getMethod("_generate", int.class, int.class, int.class, String.class, List<String>.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
