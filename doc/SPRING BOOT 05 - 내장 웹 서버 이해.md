# SPRING BOOT 05 - 내장 웹 서버 이해

* 스프링 부트는 웹서버를 내장하고 있다.
  * 기본적으로 관련 라이브러리를 의존하고있다.
  * `org.apache.tomcat.embed:tomcat-embed-core:9.0.36`
  * ` org.apache.tomcat.embed:tomcat-embed-websocket:9.0.36`
* 자바로도 톰켓을 구현할 수 있다.
* 자동 설정으로 구성된다.



예제

```java
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
```

