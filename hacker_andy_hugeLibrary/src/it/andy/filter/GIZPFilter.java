package it.andy.filter;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class GIZPFilter implements Filter {

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		//获取文本内容
		MyHttpServletResponse myresponse = new MyHttpServletResponse((HttpServletResponse)response);
		
		//放行
		chain.doFilter(request, myresponse);
		
		//获取正文
		char[] result=myresponse.getContent();
		System.out.println(result.length);
		
		//压缩文件
		ByteArrayOutputStream bas = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(bas);
		////////////gzip.write(result.toString().getBytes());
		gzip.write(new String(result).getBytes());
		gzip.finish();
		byte[] str = bas.toByteArray();
		
		//告诉浏览器
		myresponse.setHeader("content-encoding", "gzip");
		
		response.getOutputStream().write(str);
		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	public void destroy() {
		// TODO Auto-generated method stub

	}


}







class  MyHttpServletResponse extends HttpServletResponseWrapper{

	private HttpServletResponse response;
	private CharArrayWriter writer =new CharArrayWriter();//////////////////////
	
	public MyHttpServletResponse(HttpServletResponse response) {
		super(response);
		this.response = response;
	}
	
	public char[] getContent(){
				
		return writer.toCharArray();
	}
	
	
	
	@Override
	public PrintWriter getWriter() throws IOException {
		// TODO Auto-generated method stub
		return new PrintWriter(writer);
	}
	
	
	
	
}
