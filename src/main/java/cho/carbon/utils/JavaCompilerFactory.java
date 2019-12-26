package cho.carbon.utils;

import java.io.File;
import java.io.IOException;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.commons.io.FileUtils;

import javax.tools.JavaCompiler.CompilationTask;

public class JavaCompilerFactory {
	
	private static final String JAVA_FILE_SUFFIX = ".java";
	private static final String JAVA_CLAZZ_FILE_SUFFIX = ".class";
	
	
	// 把java文件编译为class
	public static void compiler(File file) throws IOException {
		// 拿到编译器
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		// 文件管理者
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		// 获取文件
		Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjects(file);
		//编译任务
		CompilationTask task = compiler.getTask(null, fileManager, null, null, null, compilationUnits);
		task.call();
		fileManager.close();
	}
	
	/**
	 *  发挥编译文件的Class， 并删除源文件和class文件
	 * 
	 * @param packageName  java包名， 例： aaa.ddd.ccc
	 * @param clazzName   类名
	 * @param javaData  java 完整的数据
	 * @return
	 * @throws Exception
	 */
	public static Class compilerJavaFile(String packageName, String clazzName, byte[] javaData) throws Exception {
		String basePath = System.getProperty("user.dir")+ "/target/classes/";
		String packageNamePath = packageName.replace(".", "/");
		String clzzPath = basePath + packageNamePath + "/" + clazzName;
		
		String javaFilePath = clzzPath + JAVA_FILE_SUFFIX;
		File javaFile = new File(javaFilePath);
		
		FileUtils.writeByteArrayToFile(javaFile, javaData);
		// 编译java文件
		compiler(javaFile);
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<?> loadClass = classLoader.loadClass(packageName + "."+clazzName);
		// java .class 文件没有删除。  测试后确定是否要删除
		String clazzFileName = clzzPath + JAVA_CLAZZ_FILE_SUFFIX;
		File clazzFile = new File(clazzFileName);
//		javaFile.delete();
//		clazzFile.delete();
		
		return loadClass;
	}
	
	/**
	 *  
	 * 
	 * @param packageName  java包名， 例： aaa.ddd.ccc
	 * @param clazzName   类名
	 * @param javaData  java 完整的数据
	 * @return 返回编译文件的Class， 并删除源文件， 不删除class文件
	 * @throws Exception
	 */
	public static File compilerJava(String packageName, String clazzName, byte[] javaData) throws Exception {
		String basePath = System.getProperty("user.dir")+ "/target/classes/";
		String packageNamePath = packageName.replace(".", "/");
		String clzzPath = basePath + packageNamePath + "/" + clazzName;
		
		String javaFilePath = clzzPath + JAVA_FILE_SUFFIX;
		File javaFile = new File(javaFilePath);
		
		FileUtils.writeByteArrayToFile(javaFile, javaData);
		// 编译java文件
		compiler(javaFile);
		
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<?> loadClass = classLoader.loadClass(packageName + "."+clazzName);
		// java .class 文件没有删除。  测试后确定是否要删除
		String clazzFileName = clzzPath + JAVA_CLAZZ_FILE_SUFFIX;
		File clazzFile = new File(clazzFileName);
//		javaFile.delete();
		
		return clazzFile;
	}
	
}
