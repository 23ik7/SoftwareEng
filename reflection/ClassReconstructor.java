package reflection;

import java.lang.reflect.*;
import java.util.HashSet;
import java.util.Set;

public class ClassReconstructor implements Reconstructor {
	
	public ClassReconstructor() {

	}

	@Override
	public String reconstructFromClass(Class c) {
			String s = "";
			if (c.getPackage() != null) {
				s += "package " + c.getPackageName() + ";\n\n";
			}

			Set<String> imports = collectImports(c);

			for (String ss : imports) {
				s += "import " + ss + ";\n";
			}

			s += "\n";

			s += Modifier.toString(c.getModifiers()) + " class " + c.getSimpleName();

			if (c.getSuperclass() != null && !c.getSuperclass().equals(Object.class)) {
				s += " extends " + c.getSuperclass().getSimpleName();
			}

			Class<?>[] interfaces = c.getInterfaces();
			if (interfaces.length > 0) {
				s += " implements ";
				for (int i = 0; i < interfaces.length; i++) {
					if (i > 0) {
						s += ", ";
					}
					s += interfaces[i].getSimpleName();
				}
			}

			s += " {\n\n";

			Field[] class_C_Fields = c.getDeclaredFields();
			for (Field field : class_C_Fields) {
				s += "	" + Modifier.toString(field.getModifiers()) + " " + field.getType().getSimpleName() + " " + field.getName() + ";" + "\n";
			}
			s += "\n";

			Constructor<?>[] constructors = c.getConstructors();

			for (Constructor cons : constructors) {
				s += "	" + Modifier.toString(cons.getModifiers()) + " " + c.getSimpleName() + " (";

				Parameter[] parameters = cons.getParameters();

				for (int i = 0; i < parameters.length; i++) {
					if (i > 0) {
						s += ", " + parameters[i].getType().getSimpleName() + " arg";
					}
					else {
						s += parameters[i].getType().getSimpleName() + " arg";
					}
				}
				s += ") {\n	}\n";
			}

			s += "\n\n";

			Method[] methods = c.getDeclaredMethods();

			for (Method meth : methods) {
				s += "	" + Modifier.toString(meth.getModifiers()) + " " + meth.getReturnType().getSimpleName() + " " + meth.getName() + "(";

				Parameter[] parameters = meth.getParameters();

				for (int i = 0; i < parameters.length; i++) {
					if (i > 0) {
						s += ", " + parameters[i].getType().getSimpleName() + " arg";
					}
					else {
						s += parameters[i].getType().getSimpleName() + " arg";
					}
				}
				Class<?>[] exceptions = meth.getExceptionTypes();

				if (exceptions.length == 0) {
					s += ") {\n";
					s += returnValue(meth.getReturnType());
					s += "\n	}\n\n";
				}
				else {
					s += ") throws ";
					for (int i = 0; i < exceptions.length; i++) {
						if (i > 0) {
							s += ", " + exceptions[i].getSimpleName();
						}
						else {
							s += exceptions[i].getSimpleName();
						}
					}
					s += " {\n";
					s += returnValue(meth.getReturnType());
					s += "\n	}\n\n";
				}

			}

			s += "}";

			return s;
	}

	public String returnValue(Class<?> returnType) {
		String s = "";
		if (returnType.equals(void.class)) {
			// Void methods don't return anything
			return "		return;";
		}
		s += "		return ";
		if (returnType.isPrimitive()) {
			if (returnType.equals(boolean.class)) {
				s += "false";
			} else if (returnType.equals(char.class)) {
				s += "'\\0'";
			} else {
				s += "0";
			}
		} else {
			s += "null";
		}
		s += ";";
		return s;
	}

	public Set<String> collectImports(Class c) {
		Set<String> imports = new HashSet<>();
		if (c.getSuperclass() != null) {
			imports.add(c.getSuperclass().getSimpleName());
		}

		Class[] interfaces = c.getInterfaces();
		for (Class<?> inter : interfaces) {
			imports.add(inter.getSimpleName());
		}

		for (Field field : c.getDeclaredFields()) {
			imports.add(field.getType().getName());
		}

		for (Method method : c.getDeclaredMethods()) {
			imports.add(method.getReturnType().getName());


			for (Class<?> param : method.getParameterTypes()) {
				imports.add(param.getName());
			}

			for (Class<?> exceptionType : method.getExceptionTypes()) {
				imports.add(exceptionType.getName());
			}
		}

		for (Constructor<?> constructor : c.getDeclaredConstructors()) {
			for (Class<?> param : constructor.getParameterTypes()) {
				imports.add(param.getName());

			}
			for (Class<?> exceptionType : constructor.getExceptionTypes()) {
				imports.add(exceptionType.getName());
			}
		}
		return imports;
	}

	@Override
	public String reconstructFromClassName(String fullClassname) throws ClassNotFoundException {
		Class<?> myClass = Class.forName(fullClassname);

		return reconstructFromClass(myClass);
	}
	
	//run the main method of your application with: mvn clean compile exec:java
	public static void main(String[] args) throws Exception {

		ClassReconstructor cr = new ClassReconstructor();
		//System.out.println(cr.reconstructFromClass(ArrayListDummy.class));

		System.out.println(cr.reconstructFromClassName("java.util.ArrayList"));




	}

}
