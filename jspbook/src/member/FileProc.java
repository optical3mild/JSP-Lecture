package member;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/member/FileProcServlet")
public class FileProc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(FileProc.class);
       
    public FileProc() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		LOG.trace("");
//		
//		FileInputStream fis = null;
//		BufferedOutputStream bos = null;
//		BufferedInputStream bis = null;
//		int length;
//		String sb = null;
//		
//		String action = request.getParameter("action");
//		String fileName = request.getParameter("fileName");
////		System.out.println(fileName);
//		
//		
//		switch(action) {
//		case "bbsList":
//			BbsDAO bmDao = new BbsDAO();
//			sb = bmDao.prepareDownload(fileName);
//			break;
//		case "MemberList":
//			MemberDAO mDao = new MemberDAO();
//			sb = mDao.prepareDownload(fileName);
//			break;
//		default:
//		}
//		
//		String client = request.getHeader("User-Agent");
//		
//		//파일다운로드 헤더지정 : 로컬위치에 다운로드할 파일 정의
//		response.reset();
//		response.setContentType("application/octet-stream");
//		response.setHeader("Content-Description", "JSP Generated Data");
//		
//		if(client.indexOf("MSIE") != -1) { //for IE
//			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".csv");
//		} else {			// IE 이외
//			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName+ ".csv\"");
//			response.setHeader("Content-Type", "application/octet-stream; charset=EUC-KR");
//		}
//		File file = new File("C:/tmp/"+fileName+".csv");
//		response.setHeader ("Content-Length", "" + file.length());
//		try {
//			fis = new FileInputStream(file);
//			bis = new BufferedInputStream(fis);
//			bos = new BufferedOutputStream(response.getOutputStream());
//			byte[] bytes = new byte[1024];
//			//outputStream으로 
//			while ((length = bis.read(bytes)) != -1) {
//				LOG.debug("Length = " + length);
//				bos.write(bytes, 0, length);
//			}
//			bos.flush();
//			bos.close();
//			bis.close();
//			fis.close();
//		} catch (IllegalStateException e1) {
//			LOG.info("doGet(): IllegalStateException Error");
//		} catch (Exception e) {
//			LOG.debug(e.getMessage());
//		}
//		LOG.trace("After try");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
		
		LOG.trace("");
		
		FileInputStream fis = null;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		int length;
		String sb = null;
		
		String action = request.getParameter("action");
		String fileName = request.getParameter("fileName");
//		System.out.println(fileName);
		
		
		switch(action) {
		case "bbsList":
			BbsDAO bmDao = new BbsDAO();
			sb = bmDao.prepareDownload(fileName);
			break;
		case "MemberList":
			MemberDAO mDao = new MemberDAO();
			sb = mDao.prepareDownload(fileName);
			break;
		default:
		}
		
		String client = request.getHeader("User-Agent");
		
		//파일다운로드 헤더지정 : 로컬위치에 다운로드할 파일 정의
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Description", "JSP Generated Data");
		
		if(client.indexOf("MSIE") != -1) { //for IE
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".csv");
		} else {			// IE 이외
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName+ ".csv\"");
			response.setHeader("Content-Type", "application/octet-stream; charset=EUC-KR");
		}
		File file = new File("C:/tmp/"+fileName+".csv");
		response.setHeader ("Content-Length", "" + file.length());
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] bytes = new byte[1024];
			//outputStream으로 
			while ((length = bis.read(bytes)) != -1) {
				LOG.debug("Length = " + length);
				bos.write(bytes, 0, length);
			}
			bos.flush();
			bos.close();
			bis.close();
			fis.close();
		} catch (IllegalStateException e1) {
			LOG.info("doGet(): IllegalStateException Error");
		} catch (Exception e) {
			LOG.debug(e.getMessage());
		}
		LOG.trace("After try");
	}

	
}
