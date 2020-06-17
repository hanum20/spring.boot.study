package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws LifecycleException {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(8080);

		Context context = tomcat.addContext("/temp", "/temp");

		HttpServlet servlet = new HttpServlet() {
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				PrintWriter writer = resp.getWriter();
				writer.println("<html><head><title>");
				writer.println("Hey tomcat");
				writer.println("</title></head>");
				writer.println("<body><h1>Hello Tomcat</h1></body>");
				writer.println("</html>");
			}
		};

		String servletName = "helloServlet";

		/*
			servlet container에 servlet을 등록한다.
			contextPath가 temp인 servlet container에는 여러 servlet이 추가될 수 있지만,
			여기서는 helloServlet 하나만 추가되었다.
		* */
		tomcat.addServlet("/temp", servletName, servlet);

		// temp context에서 /hello로 요청이 올 경우, 등록 된 서블릿들 중에서 helloServlet으로 매핑한다.
		context.addServletMappingDecoded("/hello", servletName);

		tomcat.start();
		tomcat.getServer().await();

	}

}
